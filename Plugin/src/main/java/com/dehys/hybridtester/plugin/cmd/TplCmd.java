package com.dehys.hybridtester.plugin.cmd;

import com.dehys.hybridtester.plugin.HybridTester;
import com.dehys.hybridtester.plugin.PluginHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Objects;

public class TplCmd implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("§d§m        §r§d[ §aHybridTester Plugins §d]§m        §r\n");

        for (Plugin plugin : PluginHandler.getPlugins()) {
            sb
                    .append("§e")
                    .append(plugin.getName())
                    .append("§7 -> ")
                    .append(plugin.isEnabled() ? "§aEnabled" : "§cDisabled")
                    .append("§r\n");
        }

        sb.append("\n");

        for (File file : PluginHandler.getPluginFiles()) {
            sb
                    .append("§e")
                    .append(file.getName())
                    .append(".jar")
                    .append("§7 -> ")
                    .append(file.exists() ? "§aExists" : "§cDoesn't exist")
                    .append("§r\n");
        }

        sender.sendMessage(sb.toString());

        return true;
    }

    public static void register() {
        Objects.requireNonNull(HybridTester.getInstance().getCommand("tpl")).setExecutor(new TplCmd());
    }

}
