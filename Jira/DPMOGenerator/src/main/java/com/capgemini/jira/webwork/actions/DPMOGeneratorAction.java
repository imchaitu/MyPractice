package com.capgemini.jira.webwork.actions;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.config.properties.APKeys;
import com.atlassian.jira.datetime.DateTimeStyle;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.IssueFactory;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.issue.customfields.manager.OptionsManager;
import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.FieldManager;
import com.atlassian.jira.issue.fields.screen.FieldScreenRenderLayoutItem;
import com.atlassian.jira.issue.fields.screen.FieldScreenRenderTab;
import com.atlassian.jira.issue.fields.screen.FieldScreenRenderer;
import com.atlassian.jira.issue.fields.screen.FieldScreenRendererFactory;
import com.atlassian.jira.issue.issuetype.IssueType;
import com.atlassian.jira.issue.operation.IssueOperations;
import com.atlassian.jira.issue.operation.ScreenableIssueOperation;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.web.action.JiraWebActionSupport;
import com.capgemini.jira.models.JiraField;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


class DPMOGeneratorAction extends JiraWebActionSupport {

    @Override
    public String execute() throws Exception {

        long issueId = Long.parseLong(this.getHttpRequest().getParameter("issueId"));
        Issue issue = ComponentAccessor.getIssueManager().getIssueObject(issueId);

        String parentType = issue.getIssueType().getName();
        String childType = this.getHttpRequest().getParameter("child");

        Project project = issue.getProjectObject();
        long projectId = project.getId();

        String parentTypeId = getIssueTypeId(parentType, project);
        String childTypeId = getIssueTypeId(childType, project);

        if (childTypeId == null || parentTypeId == null) {
          return null;
        }

        // retrieve fields common in both parent and child
        List<String> relFields = getRelevantFields(projectId, parentTypeId, childTypeId);

        // map names with values from parent fields
        HashMap<String, String> mappedValues = mapFieldValues(issue, relFields);

        // add new values to map, they are not on the screens of either of them
        mappedValues.put("pid", String.valueOf(projectId));
        mappedValues.put("issuetype", childTypeId);
        mappedValues.put("reporter", ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser().getName());

        // this one will be utilized in a script to link parent and child
        if (getHiddenFieldId() != null) {
            mappedValues.put(getHiddenFieldId(), String.valueOf(issue.getId()));
        }

        return getRedirect(createURL(mappedValues), true);
    }

    private List<String> getRelevantFields(long projectId, String parentTypeId, String childTypeId) {
        IssueFactory issFact = ComponentAccessor.getIssueFactory();
        MutableIssue iss = issFact.getIssue();
        iss.setProjectId(projectId);

        // view and edit screen of parent usually brings most common fields
        iss.setIssueTypeId(parentTypeId);
        List<String> parentEditFields = getIssueScreenFields(iss, IssueOperations.EDIT_ISSUE_OPERATION);
        List<String> parentViewFields = getIssueScreenFields(iss, IssueOperations.VIEW_ISSUE_OPERATION);

        // merge fields from issue operation screens without duplicates
        List<String> parentFields = mergeLists(parentEditFields, parentViewFields);

        // for the child only the create screen is relevant of course
        iss.setIssueTypeId(childTypeId);
        List<String> childFields = getIssueScreenFields(iss, IssueOperations.CREATE_ISSUE_OPERATION);

        FieldManager fieldMan = ComponentAccessor.getFieldManager();
        List<String> eqFields = new ArrayList<>();
        for (String field : parentFields) {
            if (childFields.contains(field)) {
                eqFields.add(fieldMan.getField(field).getName());
            }
        }

        return eqFields;
    }

    private List<String> mergeLists(List<String> list1, List<String> list2) {
        List<String> mergedList = new ArrayList<>();
        for (String element : list1) {
            mergedList.add(element);
        }

        for (String element : list2) {
            if (!mergedList.contains(element)) {
                mergedList.add(element);
            }
        }

        return mergedList;
    }

    private HashMap<String, String> mapFieldValues(Issue issue, List<String> fields) {
        HashMap<String, String> mappedValues = new HashMap<>();
        CustomFieldManager customMan = ComponentAccessor.getCustomFieldManager();
        List<CustomField> customFields = customMan.getCustomFieldObjects(issue);
        for (String field : fields) {
            String fieldValue = getSystemFieldValue(issue, field);
            if (fieldValue == null) {
                for (CustomField customField : customFields) {
                    if (field.equals(customField.getName())) {
                        if (issue.getCustomFieldValue(customField) != null) {
                            mappedValues.put(customField.getId(), getTypedValue(issue, customField));
                        }
                        break;
                    }
                }
            } else {
                mappedValues.put(field.toLowerCase().replaceAll("\\s",""), fieldValue);
            }
        }

        return mappedValues;
    }

    private String getTypedValue(Issue issue, CustomField customField) {
        String typedValue;
        switch (customField.getCustomFieldType().getName()) {
            case "Date Picker":
                Date date = (Date)issue.getCustomFieldValue(customField);
                typedValue = getDmyDateFormatter().withStyle(DateTimeStyle.DATE_PICKER).format(date);
                break;
            case "Select List (single choice)":
                OptionsManager optMan = ComponentAccessor.getOptionsManager();
                List<Option> opts = optMan.getOptions(customField.getRelevantConfig(issue));
                Option selectedOpt = (Option) issue.getCustomFieldValue(customField);
                int optIndex = opts.indexOf(selectedOpt);
                typedValue = String.valueOf(opts.get(optIndex).getOptionId());
                break;
            case "Number Field":
                typedValue = String.valueOf(((Double) issue.getCustomFieldValue(customField)).doubleValue());
                break;
            default:
                typedValue = issue.getCustomFieldValue(customField).toString();
                break;
        }

        return typedValue;
    }

    private String getSystemFieldValue(Issue issue, String field) {
        switch (field) {
            case "Summary":
                JiraField jField = new JiraField("Project Type", issue);
                if (jField.getFieldValue().equals("FP")) {
                    return issue.getSummary().replace("PR_", "MS_");
                }
                else if (jField.getFieldValue().equals("T&M")) {
                    return issue.getSummary().replace("PR_", "TS_");
                }
                return issue.getSummary();
            case "Assignee":
                if (issue.getAssignee() != null) {
                    return issue.getAssignee().getName();
                }
                break;
            case "Description":
                return issue.getDescription();
            case "Due Date":
                if (issue.getDueDate() != null) {
                    return getDmyDateFormatter().withStyle(DateTimeStyle.DATE_PICKER).format(issue.getDueDate());
                }
                break;
            case "Priority":
                if (issue.getPriority() != null) {
                    return issue.getPriority().getId();
            }
        }

        return null;
    }

    private String createURL(HashMap<String, String> urlValues) {
        String url = ComponentAccessor.getApplicationProperties().getString(APKeys.JIRA_BASEURL);
        url += "/secure/CreateIssueDetails!Init.jspa?";
        for (Map.Entry<String, String> entry : urlValues.entrySet()) {
          String key = entry.getKey();
          String value = entry.getValue();
          try {
            key = URLEncoder.encode(key, "UTF-8");
            value = URLEncoder.encode(value, "UTF-8");
          } catch (UnsupportedEncodingException e) {}
            url += key + "=" + value + "&"; 
        }

        url = url.replaceAll(" ","%20");

        return url;
    }

    private String getHiddenFieldId() {
        List<CustomField> custCol = ComponentAccessor.getCustomFieldManager().getCustomFieldObjects();
        for (CustomField cusField : custCol) {
            if (cusField.getName().equals("HiddenLinkID")) {
                return cusField.getId();
            }
        }
        return null;
    }

    private String getIssueTypeId(String issueType, Project project) {
        Collection<IssueType> issueTypes = project.getIssueTypes();
        String typeId;
        for (IssueType type : issueTypes) {
            if (type.getName().equals(issueType)) {
                typeId = type.getId();
                return typeId;
            }
        }

        return null;
    }

    private List<String> getIssueScreenFields(Issue issue, ScreenableIssueOperation issueOperation) {
        FieldScreenRendererFactory renderFact = ComponentAccessor.getFieldScreenRendererFactory();
        FieldScreenRenderer screenRend = renderFact.getFieldScreenRenderer(issue, issueOperation);
        FieldScreenRenderTab screenTab = screenRend.getFieldScreenRenderTabs().get(0);
        List<String> screenFields = new ArrayList<>();
        List<FieldScreenRenderLayoutItem> layoutItems = screenTab.getFieldScreenRenderLayoutItems();
        for (FieldScreenRenderLayoutItem layoutItem : layoutItems) {
            screenFields.add(layoutItem.getFieldScreenLayoutItem().getOrderableField().getId());
        }

        return screenFields;
    }
}
