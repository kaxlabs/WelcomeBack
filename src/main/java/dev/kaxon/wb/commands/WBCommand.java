package dev.kaxon.wb.commands;

import dev.kaxon.wb.Main;
import dev.kaxon.wb.listeners.JoinListener;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class WBCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        PluginCommand wb = Main.INSTANCE.getCommand("wb");
        if (wb == null || wb.getPermission() == null) return true;
        if (args.length > 0 && sender.isOp() && args[0].equalsIgnoreCase("reload")) {
            Main.INSTANCE.reloadConfig();
            sender.sendMessage(ChatColor.GREEN + "Reloaded config.");
            return true;
        }
        if (!sender.hasPermission(wb.getPermission())) {
            return false;
        }
        if (!(sender instanceof Player player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }
        if (JoinListener.getLastPlayer() == null) {
            sender.sendMessage(ChatColor.GRAY + "No player has joined yet.");
            return true;
        }
        String message;
        if (!JoinListener.getLastPlayer().hasPlayedBefore()) {
            message = Objects.requireNonNull(Main.INSTANCE.getConfig().getString("first-join-message")).replaceAll("%player%", JoinListener.getLastPlayer().getName());
        } else {
            message = Objects.requireNonNull(Main.INSTANCE.getConfig().getString("welcome-back-message")).replaceAll("%player%", JoinListener.getLastPlayer().getName());
        }
        if (player.getUniqueId() == JoinListener.getLastPlayer().getUniqueId()) {
            sender.sendMessage(message);
            return true;
        }
        if (!JoinListener.getLastPlayer().isOnline()) {
            sender.sendMessage(ChatColor.GRAY + "That player is no longer online.");
            return true;
        }
        player.chat(message);
        return true;
    }
}
