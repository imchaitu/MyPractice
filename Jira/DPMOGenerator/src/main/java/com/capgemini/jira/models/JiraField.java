package com.capgemini.jira.models;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.fields.CustomField;
import java.util.List;

public class JiraField {

  private final CustomFieldManager customMan = ComponentAccessor.getCustomFieldManager();

  public enum GeneralFieldType {
    SYSTEMFIELD,
    CUSTOMFIELD,
    UNKNOWN
  }

  private final String fieldName;

  private final GeneralFieldType generalFieldType;
  private FieldType fieldType;

  private Object rawFieldValue;
  private String fieldValue;

  public JiraField(String fieldName, Issue issue) {
    this.fieldName = fieldName;
    this.generalFieldType = this.isCustomField(issue) ? GeneralFieldType.CUSTOMFIELD
        : (this.isSystemField() ? GeneralFieldType.SYSTEMFIELD : GeneralFieldType.UNKNOWN);
    this.initFieldType();
    this.initFieldValue(issue);
  }

  private boolean isCustomField(Issue issue) {
    List<CustomField> customFields = customMan.getCustomFieldObjects(issue);
    for (CustomField custField : customFields) {
      if (custField.getName().equals(this.fieldName)) {
        return true;
      }
    }

    return false;
  }

  private void initFieldType() {
    this.fieldType = FieldType.UNKNOWN;
    if (this.generalFieldType.equals(GeneralFieldType.SYSTEMFIELD)) {
      this.fieldType = SystemField.getSystemFieldByValue(this.fieldName).getFieldType();
    } else if (this.generalFieldType.equals(GeneralFieldType.CUSTOMFIELD)) {
      CustomField cusField = customMan.getCustomFieldObjectByName(this.fieldName);
      this.fieldType = FieldType.getFieldTypeByValue(cusField.getCustomFieldType().getName());
    }
  }

  private void initFieldValue(Issue issue) {
    switch (this.generalFieldType) {
      case SYSTEMFIELD:
        this.fieldValue = this.getSystemFieldValue(issue);
        break;
      case CUSTOMFIELD:
        this.fieldValue = this.getCustomFieldValue(issue);
        break;
      case UNKNOWN:
        break;
    }
  }

  private String getSystemFieldValue(Issue issue) {
    String sysFieldVal = null;
    SystemField sysField = SystemField.getSystemFieldByValue(this.fieldName);
    if (sysField != null) {
      switch (sysField) {
        case SUMMARY:
          sysFieldVal = issue.getSummary();
          break;
        case ASSIGNEE:
          if (issue.getAssignee() != null) {
            sysFieldVal = issue.getAssignee().getName();
          }
          break;
        case REPORTER:
          if (issue.getReporter() != null) {
            sysFieldVal = issue.getReporter().getName();
          }
          break;
        case DUEDATE:
          if (issue.getDueDate() != null) {
            sysFieldVal = issue.getDueDate().toString();
          }
          break;
        case ISSUETYPE:
          sysFieldVal = issue.getIssueTypeObject().getName();
          break;
        case DESCRIPTION:
          sysFieldVal = issue.getDescription();
          break;
        case PROJECT:
          sysFieldVal = issue.getProjectObject().getKey();
      }
    }

    return sysFieldVal;
  }

  private String getCustomFieldValue(Issue issue) {
    CustomField custField = customMan.getCustomFieldObjectByName(this.fieldName);
    Object custFieldVal = issue.getCustomFieldValue(custField);
    this.rawFieldValue = custFieldVal;
    return custFieldVal != null ? custFieldVal.toString() : null;
  }

  private boolean isSystemField() {
    return SystemField.containsValue(this.fieldName);
  }

  public boolean isValid() {
    return this.fieldName != null && this.generalFieldType != null && this.fieldValue != null;
  }

  public Object getRawFieldValue() {
    return this.rawFieldValue;
  }

  public String getFieldValue() {
    return this.fieldValue;
  }

  public FieldType getFieldType() {
    return this.fieldType;
  }
}

