package org.example.request;

import org.example.session.Session;

import java.util.Map;

public class Request {
    private String route;
    private String function;
    private Map<String, String> parameters;
    private Session session;

    public Request(String route, String function, Map<String, String> parameters) {
        this.route = route;
        this.function = function;
        this.parameters = parameters;
    }

    public String getRoute() {
        return route;
    }

    public String getFunction() {
        return function;
    }

    public String getParameter(String key) {
        return parameters.get(key);
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
