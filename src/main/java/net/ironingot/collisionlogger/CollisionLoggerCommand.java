package net.ironingot.collisionlogger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CollisionLoggerCommand implements CommandExecutor {
    private CollisionLogger plugin;
    private String pluginName;
    private String pluginVersion;

    public CollisionLoggerCommand(CollisionLogger plugin){
        this.plugin = plugin;
        pluginName = plugin.getDescription().getName();
        pluginVersion = plugin.getDescription().getVersion();
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        String command;
        String option;

        if (args.length == 0) {
            command = new String();
            option = new String();
        } else if (args.length == 1) {
            command = new String();
            option = args[0].toLowerCase();
        } else {
            command = args[0].toLowerCase();
            option = args[1].toLowerCase();
        }

        plugin.logger.info("Sender: " + sender + " lbl: " + commandLabel + " cmd: " + command + " opt:" + option);

        return executeCommand(sender, commandLabel, command, option);
    }

    private boolean executeCommand(CommandSender sender, String label, String subcmd, String option) {

        if (!sender.isOp() && !sender.hasPermission(plugin.getPermissionName("command"))) {
            sender.sendMessage(ChatColor.RED + "You do not have permission.");
            return true;
        }

        if (subcmd.equals("bc") || subcmd.equals("broadcast")) {
            if (option.equals("on") || option.equals("true")) {
                plugin.enableBroadcast();
                return true;
            }
            if (option.equals("off") || option.equals("false")) {
                plugin.disableBroadcast();
                return true;
            }
        }

        if (option.equals("on") || option.equals("true")) {
            plugin.enable();
            sender.sendMessage(ChatColor.GOLD + pluginName + " is enabled.");
            return true;
        }

        if (option.equals("off") || option.equals("false")) {
            plugin.disable();
            sender.sendMessage(ChatColor.GOLD + pluginName + " is disabled.");
            return true;
        }

        sender.sendMessage(ChatColor.GOLD + label + " [on / off]");
        sender.sendMessage(ChatColor.GOLD + label + " broadcast  [on / off]");
        return true;
    }
}