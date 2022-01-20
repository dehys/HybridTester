package com.dehys.hybridtester.client.methods;

import com.dehys.hybridtester.client.Client;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;

public abstract class Reload {

    public static void reload(String p0, String p1) throws IOException {
        HttpGet request = new HttpGet(p0+"/reload?name=" + p1);
        request.addHeader(HttpHeaders.USER_AGENT, "HybridTesterHTTPClient.reload");
        Client.httpClient.execute(request);
    }

}
