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
        getCommand("wb").setExecutor(new WBCommand());
    }

    @Override
    public void onDisable() {
    }
    
    private void createConfig() {
        config.addDefault("welcome-message", "Welcome back, %player%!");
        config.options().copyDefaults(true);
        saveConfig();
    }
}
