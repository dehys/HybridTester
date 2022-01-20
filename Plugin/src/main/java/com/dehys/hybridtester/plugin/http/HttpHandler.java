package com.dehys.hybridtester.plugin.http;

import com.dehys.hybridtester.plugin.HybridTester;
import com.dehys.hybridtester.plugin.PluginHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class HttpHandler {

    public static void h_Get(CallInfo callInfo) {
        String URI = callInfo.getURI();
        Map<String, List<String>> params = callInfo.getParameters();

        String name = "";
        boolean enableAfter = false;

        for (Map.Entry<String, List<String>> entry : params.entrySet()) {
            String key = entry.getKey();
            List<String> value = entry.getValue();
            switch (key) {
                case "name":
                    name = value.get(0);
                    break;
                case "enableAfter":
                    enableAfter = Boolean.parseBoolean(value.get(0));
                    break;
                default: break;
            }
        }

        if (URI.equalsIgnoreCase("/load")){
            if (name.isBlank()) return;
            try {
                PluginHandler.load(enableAfter, new File(HybridTester.getPluginFolder(), name + ".jar"));
            } catch (InvalidPluginException | InvalidDescriptionException e) {
                e.printStackTrace();
            }
            return;
        }

        if (URI.equalsIgnoreCase("/unload")){
            if (name.isBlank()) return;
            PluginHandler.unload(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin(name.split("-")[0])));
        }

        if (URI.equalsIgnoreCase("/enable")){
            if (name.isBlank()) return;
            PluginHandler.enable(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin(name.split("-")[0])));
        }

        if (URI.equalsIgnoreCase("/disable")){
            if (name.isBlank()) return;
            PluginHandler.disable(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin(name.split("-")[0])));
        }

    }

    public static void h_Post(CallInfo callInfo) {

    }

    public static void h_Put(CallInfo callInfo) {
    }

    public static void h_Delete(CallInfo callInfo) {
    }
}
