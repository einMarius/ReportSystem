//This file was created in 2021

package me.littlq.main;

import me.littlq.commands.cmd_report;
import me.littlq.config.ConfigManager;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Main extends Plugin {

    private boolean configisrunning;
    private Thread ConfigThread;

    public static ConfigManager cm;

    private static Main plugin;

    public void onEnable() {

        plugin = this;

        //CONFIG
        cm = new ConfigManager();

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
                            cm.register();
                        } catch (IOException e) {
                        }

                        ConfigThread.interrupt();
                        configisrunning = false;

                        System.out.println(ConfigThread.getId());

                    }
                }
            }.start();
        }

        //ENDE CONFIG

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

    public static Main getPlugin(){
        return plugin;
    }

}