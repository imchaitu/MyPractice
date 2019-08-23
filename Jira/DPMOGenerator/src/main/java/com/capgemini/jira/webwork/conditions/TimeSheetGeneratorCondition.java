package com.capgemini.jira.webwork.conditions;

import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.plugin.webfragment.conditions.AbstractIssueWebCondition;
import com.atlassian.jira.plugin.webfragment.model.JiraHelper;
import com.atlassian.jira.user.ApplicationUser;
import com.capgemini.jira.models.JiraField;

public class TimeSheetGeneratorCondition extends AbstractIssueWebCondition {

    @Override
    public boolean shouldDisplay(ApplicationUser user, Issue issue, JiraHelper jiraHelper) {
        JiraField jField = new JiraField("Project Type", issue);
        String projectKey = issue.getProjectObject().getKey();
        return jField.getFieldValue().equals("T&M") && projectKey.equals("DPMO");
    }

}