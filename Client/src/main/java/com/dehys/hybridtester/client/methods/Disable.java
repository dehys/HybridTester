package com.dehys.hybridtester.client.methods;

import com.dehys.hybridtester.client.Client;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;

public abstract class Disable {

    public static void disable(String p0, String p1) throws IOException {
        HttpGet request = new HttpGet(p0+"/disable?name=" + p1);
        request.addHeader(HttpHeaders.USER_AGENT, "HybridTesterHTTPClient.disable");
        Client.httpClient.execute(request);
    }

}
