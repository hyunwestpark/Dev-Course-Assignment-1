package org.example.request;

import org.example.session.Session;

import java.util.Arrays;
import java.util.HashMap;

public class RequestParser {
    private Session session;

    public RequestParser(Session session) {
        this.session = session;
    }
    public Request parse(String url) {
        if (url == null || !url.startsWith("/")) {
            throw new RuntimeException("유효하지 않은 URL 형식입니다.");
        }

        String path = url;
        HashMap<String, String> params = new HashMap<>();

        if (url.contains("?")) {
            String[] parts = url.split("\\?");
            path = parts[0];

            if (parts.length > 1) {
                String[] queryParams = parts[1].split("&");
                for (String queryParam : queryParams) {
                    String[] keyValue = queryParam.split("=");
                    if (keyValue.length == 2) {
                        params.put(keyValue[0], keyValue[1]);
                    }
                }
            }
        }
        String[] routes = path.split("/");
        if (routes.length < 3) {
            throw new RuntimeException("URL 형식이 올바르지 않습니다.");
        }

        String route = routes[1];
        String function = routes[2];

        Request request = new Request(route, function, params);
        request.setSession(this.session);

        return request;
    }

}
