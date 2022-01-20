package com.dehys.hybridtester.plugin;

import com.rylinaux.plugman.util.PluginUtil;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PluginHandler {

    @Getter private static final List<Plugin> plugins = new ArrayList<>(); //enabled & disabled plugins
    @Getter private static final List<File> pluginFiles = new ArrayList<>(); //loaded plugins

    public static Plugin load(boolean enableAfter, File ... files) throws InvalidPluginException, InvalidDescriptionException {
        for (File file : files) {
            String pluginName = file.getName().replace(".jar", "");
            PluginUtil.load("HybridTester\\plugins\\"+pluginName);
            Plugin plugin = Bukkit.getPluginManager().getPlugin(pluginName);
            if (plugin != null) {
                if (enableAfter) enable(plugin);
            }
            return plugin;
        }
        return null;
    }

    public static void loadDir(boolean enableAfter, File pluginDirectory) {
        if (!pluginDirectory.exists()) {
            HybridTester.getInstance().getLog4JLogger().error("Plugin directory does not exist: " + pluginDirectory.getName());
            return;
        }
        File[] files = pluginDirectory.listFiles();
        if (files == null) {
            HybridTester.getInstance().getLog4JLogger().error("Failed to list plugin directory: " + pluginDirectory.getName());
            return;
        }
        for (File file : files) {
            if (file.getName().endsWith(".jar")) {
                try {
                    load(enableAfter, file);
                } catch (InvalidPluginException | InvalidDescriptionException e) {
                    HybridTester.getInstance().getLog4JLogger().error("Failed to load plugin: " + file.getName());
                }
            }
        }
    }

    public static void unload(Plugin plugin){
        plugins.remove(plugin);
        PluginUtil.unload(plugin);
    }

    public static void enable(Plugin plugin){
        if (plugins.stream().noneMatch(p -> p.getName().equals(plugin.getName()))) plugins.add(plugin);
        PluginUtil.enable(plugin);
    }

    public static void enable(File file) throws InvalidPluginException, InvalidDescriptionException {
        load(true, file);
    }

    public static void enable(String name){
        Plugin plugin = Bukkit.getPluginManager().getPlugin(name);
        if (plugin != null) {
            enable(plugin);
        }
    }

    public static void disable(Plugin plugin){
        PluginUtil.disable(plugin);
    }

}
