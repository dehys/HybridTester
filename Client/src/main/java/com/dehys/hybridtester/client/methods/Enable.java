package com.dehys.hybridtester.client.methods;

import com.dehys.hybridtester.client.Client;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;

public abstract class Enable {

    public static void enable(String p0, String p1) throws IOException {
        HttpGet request = new HttpGet(p0+"/enable?name=" + p1);
        request.addHeader(HttpHeaders.USER_AGENT, "HybridTesterHTTPClient.enable");
        Client.httpClient.execute(request);
    }

}
