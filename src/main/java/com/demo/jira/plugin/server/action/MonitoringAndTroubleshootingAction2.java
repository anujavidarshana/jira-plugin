package com.demo.jira.plugin.server.action;

import com.atlassian.jira.security.request.RequestMethod;
import com.atlassian.jira.security.request.SupportedMethods;
import com.atlassian.jira.web.action.JiraWebActionSupport;
import webwork.action.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SupportedMethods({RequestMethod.GET, RequestMethod.POST})
public class MonitoringAndTroubleshootingAction2 extends JiraWebActionSupport {

    private static final long serialVersionUID = 1L;

    public MonitoringAndTroubleshootingAction2() {
    }

    @Override
    @SupportedMethods({RequestMethod.GET, RequestMethod.POST})
    public String execute() {
        try {
            ServletActionContext.getResponse().setContentType("application/json");
            ServletActionContext.getResponse().setStatus(HttpServletResponse.SC_BAD_REQUEST);
            ServletActionContext.getResponse().getWriter().write("POST call success but received an error.");
            ServletActionContext.getResponse().flushBuffer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    


}
