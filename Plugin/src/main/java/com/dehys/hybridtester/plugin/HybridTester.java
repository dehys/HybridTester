package com.dehys.hybridtester.plugin;


import com.dehys.hybridtester.plugin.cmd.TplCmd;
import com.dehys.hybridtester.plugin.http.HttpServer;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class HybridTester extends JavaPlugin {

    @Getter private static HybridTester instance;
    @Getter private static com.dehys.hybridtester.plugin.http.HttpServer HttpServer;
    @Getter private static File pluginFolder;

    @SneakyThrows
    public void onEnable() {
        instance = this;
        HttpServer = new HttpServer(8787);
        HttpServer.start();

        pluginFolder = new File(getDataFolder(), "plugins");
        if (!pluginFolder.exists()) {
            boolean result = pluginFolder.mkdirs();
            if (!result) {
                throw new Exception("Could not create plugin folder");
            }
        }

        //PluginHandler.loadDir(true, pluginFolder);

        TplCmd.register();
    }

    public void onDisable() {
        PluginHandler.getPlugins().forEach(PluginHandler::disable);
        HttpServer.stop();
    }
}
