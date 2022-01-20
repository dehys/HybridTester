package com.dehys.hybridtester.plugin.http;

import fi.iki.elonen.NanoHTTPD;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class CallInfo {
    private NanoHTTPD.Method method;
    private String URI;
    private Map<String, List<String>> parameters;

    public CallInfo(NanoHTTPD.Method method, String uri, Map<String, List<String>> parameters) {
        this.method = method;
        this.URI = uri;
        this.parameters = parameters;
    }
}
