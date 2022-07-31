package dev.kaxon.wb;

import dev.kaxon.wb.commands.WBCommand;
import dev.kaxon.wb.listeners.JoinListener;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    public static Main INSTANCE;
    private final FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        INSTANCE = this;
        createConfig();
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getCommand("welcomeback").setExecutor(new WBCommand());
    }

    @Override
    public void onDisable() {
    }
    
    private void createConfig() {
        config.addDefault("first-join-message", "Welcome, %player%!");
        config.addDefault("welcome-back-message", "Welcome back, %player%!");
        config.options().copyDefaults(true);
        saveConfig();
    }
}
