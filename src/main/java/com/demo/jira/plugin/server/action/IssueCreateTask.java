package com.demo.jira.plugin.server.action;

import com.atlassian.core.task.longrunning.AbstractLongRunningTask;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.IssueManager;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.issue.issuetype.IssueType;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.project.ProjectManager;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.jira.user.util.DefaultUserManager;

import java.util.Collection;
import java.util.ResourceBundle;

public class IssueCreateTask extends AbstractLongRunningTask {

    private ProjectManager projectManager;

    public IssueCreateTask(ProjectManager projectManager) {
        this.projectManager = projectManager;
    }


    @Override
    protected ResourceBundle getResourceBundle() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }


    public Issue createIssue() {

        ApplicationUser user = null;
        IssueManager issueManager = null;
        try {
            user = ComponentAccessor.getComponent(DefaultUserManager.class).getUserByName("admin");
            issueManager = ComponentAccessor.getIssueManager();
        } catch (Exception e) {
        }
        MutableIssue newJiraIssue = ComponentAccessor.getIssueFactory().getIssue();

        IssueType issueType2 = getJiraIssueType("Story");
        Project project2 = projectManager.getProjectObjByName("IN");
        newJiraIssue.setProjectObject(project2);
        newJiraIssue.setIssueTypeObject(issueType2);
        newJiraIssue.setSummary("Ticket-" + System.currentTimeMillis());
        newJiraIssue.setReporter(user);
        try {
            assert issueManager != null;
            return issueManager.createIssueObject(user, newJiraIssue);
        } catch (Exception e) {
            return null;
        }
    }

    public IssueType getJiraIssueType(String issueTypeName) {
        return findJiraIssueTypeByName(issueTypeName,
                ComponentAccessor.getConstantsManager().getRegularIssueTypeObjects());
    }

    private static IssueType findJiraIssueTypeByName(String issueTypeName, final Collection<IssueType> issueTypes) {
        if (null == issueTypeName || issueTypeName.isEmpty() || (null == issueTypes || issueTypes.size() == 0)) {
            return null;
        }

        IssueType result = null;
        for (IssueType issueType : issueTypes) {
            if (issueType.getName().equalsIgnoreCase(issueTypeName)) {
                result = issueType;
                break;
            }
        }
        return result;
    }

}
