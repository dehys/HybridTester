package com.dehys.hybridtester.client.methods;

import com.dehys.hybridtester.client.Client;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;

public abstract class Load {

    public static void load(String p0, String p1, boolean p2) throws IOException {
        HttpGet request = new HttpGet(p0+"/load?name=" + p1 + "&enableAfter=" + p2);
        request.addHeader(HttpHeaders.USER_AGENT, "HybridTesterHTTPClient.load");
        Client.httpClient.execute(request);
    }

}
