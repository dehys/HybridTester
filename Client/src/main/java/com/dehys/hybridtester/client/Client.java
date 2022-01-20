package com.dehys.hybridtester.client;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.Objects;

import static com.dehys.hybridtester.client.methods.Disable.disable;
import static com.dehys.hybridtester.client.methods.Enable.enable;
import static com.dehys.hybridtester.client.methods.Load.load;
import static com.dehys.hybridtester.client.methods.Reload.reload;
import static com.dehys.hybridtester.client.methods.Unload.unload;

public class Client {

    public static final CloseableHttpClient httpClient = HttpClients.createDefault();

    public static void main(String[] args) throws IOException {
        Method method = null;
        String url = null, plugin = null;
        boolean enableAfter = false;

        for (int i = 0; i < args.length; i++) {
            switch (i) {
                case 0 -> method = (args[i] != null ? Method.fromString(args[i]) : null);
                case 1 -> url = args[i] != null ? (args[i].equalsIgnoreCase("null") ? "http://localhost:8787" : args[i]) : "http://localhost:8787";
                case 2 -> plugin = args[i] != null ? args[i] : null;
                case 3 -> enableAfter = args[3] != null && Boolean.parseBoolean(args[3]);
            }
        }

        if (method == null || url == null || plugin == null) {
            throw new IllegalArgumentException("Invalid arguments");
        }

        switch (method) {
            case DISABLE -> disable(url, plugin);
            case ENABLE -> enable(url, plugin);
            case LOAD -> load(url, plugin, enableAfter);
            case RELOAD -> reload(url, plugin);
            case UNLOAD -> unload(url, plugin);
            default -> throw new IllegalArgumentException("Unknown method: " + method);
        }


    }

}
