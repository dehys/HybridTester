package com.dehys.hybridtester.plugin.http;

import fi.iki.elonen.NanoHTTPD;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class HttpServer extends NanoHTTPD {

    public HttpServer(String host, int port) {
        super(host, port);
    }

    public HttpServer(int port) {
        super(port);
    }

    public void start() throws IOException {
        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        System.out.println("\nRunning on http://" + (super.getHostname() == null ? "localhost" : super.getHostname()) + ":" + super.getListeningPort() + "\n");
    }

    public void stop() {
        super.stop();
    }

    @Override
    public Response serve(IHTTPSession session) {
        Map<String, List<String>> decodedQueryParameters =
                NanoHTTPD.decodeParameters(session.getQueryParameterString());
        CallInfo callInfo = new CallInfo(session.getMethod(), session.getUri(), decodedQueryParameters);
        switch (session.getMethod()) {
            case Method.GET:
                HttpHandler.h_Get(callInfo);
                break;
            case Method.POST:
                HttpHandler.h_Post(callInfo);
                break;
            case Method.PUT:
                HttpHandler.h_Put(callInfo);
                break;
            case Method.DELETE:
                HttpHandler.h_Delete(callInfo);
                break;
            default: throw new RuntimeException("Unsupported method: " + session.getMethod());
        }
        return NanoHTTPD.newFixedLengthResponse("<h3>HybridTester API</h3>");
    }
}