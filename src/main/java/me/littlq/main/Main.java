//This file was created in 2021

package me.littlq.main;

import me.littlq.commands.cmd_report;
import me.littlq.config.ConfigManager;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Main extends Plugin {

    private boolean configisrunning;
    private Thread ConfigThread;

    public static File file;
    public static Configuration config;

    public static ConfigManager cm;

    public static String prefix;

    public void onEnable() {

        configisrunning = !configisrunning;

        if (configisrunning) {
            new Thread() {
                @Override
                public void run() {
                    super.run();

                    while (configisrunning) {

                        try {
                            Thread.sleep(100);
                        }catch(InterruptedException e) {
                            Thread.currentThread().interrupt();
                            System.out.println("[ReportSystem] Thread was interrupted, Failed to complete operation");
                        }

                        ConfigThread = Thread.currentThread();

                        try {

                            if(!getDataFolder().exists()){
                                getDataFolder().mkdir();
                            }

                            file = new File(getDataFolder().getParent(), "ReportSystem/config.yml");

                            if(!file.exists()) {
                                file.createNewFile();
                                config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);

                                ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, file);
                            }
                        }catch (IOException ex) {
                            System.out.println("Es gab einen Fehler beim erstellen der Config.yml");
                        }


                        cm = new ConfigManager();
                        cm.register();

                        ConfigThread.interrupt();
                        configisrunning = false;

                        System.out.println(ConfigThread.getId());

                    }
                }
            }.start();
        }

        getProxy().getPluginManager().registerCommand(this, new cmd_report());

        //Bukkit.getPluginManager().registerEvents(new LISTENER(), this);

// -------------------------------
        System.out.println("----------[ReportSystem]----------");
        System.out.println("Plugin aktiviert");
        System.out.println("Version: 1.0");
        System.out.println("Author: einMarius");
        System.out.println("----------[ReportSystem]----------");
// -------------------------------
    }

    public void onDisable() {

        cm.saveCfg();

// -------------------------------
        System.out.println("----------[ReportSystem]----------");
        System.out.println("Plugin deaktiviert");
        System.out.println("Version: 1.0");
        System.out.println("Author: einMarius");
        System.out.println("----------[ReportSystem]----------");
// -------------------------------

    }

}