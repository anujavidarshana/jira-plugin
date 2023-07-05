package com.demo.jira.plugin.server.action;

import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.security.request.RequestMethod;
import com.atlassian.jira.security.request.SupportedMethods;
import com.atlassian.jira.web.action.JiraWebActionSupport;
import webwork.action.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SupportedMethods({RequestMethod.GET, RequestMethod.POST})
public class MonitoringAndTroubleshootingAction extends JiraWebActionSupport {

    private static final long serialVersionUID = 1L;

    public MonitoringAndTroubleshootingAction() {
    }

    @Override
    public String doExecute() {

        return "monitoringAndTroubleshootingDisplay";
    }


    @SupportedMethods({RequestMethod.GET, RequestMethod.POST})
    public String doClearcache() {
        try {
            IssueCreateTask issueCreateTask = new IssueCreateTask(getProjectManager());
            Issue issue = issueCreateTask.createIssue();
            ServletActionContext.getResponse().setContentType("application/json");
            ServletActionContext.getResponse().setStatus(HttpServletResponse.SC_OK);
            ServletActionContext.getResponse().getWriter().write(issue.getSummary());
            ServletActionContext.getResponse().flushBuffer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
