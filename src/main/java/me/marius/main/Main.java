//This file was created in 2021

package me.marius.main;

import me.marius.commands.cmd_report;
import me.marius.config.ConfigManager;
import me.marius.listeners.DisconnectListener;
import me.marius.listeners.ServerSwitchListener;
import me.marius.report.Report;
import me.marius.utils.Utils;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.IOException;

public class Main extends Plugin {

    private boolean configisrunning;
    private Thread ConfigThread;

    private ConfigManager configManager;
    private cmd_report cmd_report;
    private Utils utils;
    private Report report;
    private DisconnectListener disconnectListener;
    private ServerSwitchListener serverSwitchListener;

    private static Main plugin;

    public void onEnable() {

        configManager = new ConfigManager(this);
        cmd_report = new cmd_report(this);
        utils = new Utils(this);
        report = new Report(this);
        disconnectListener = new DisconnectListener(this);
        serverSwitchListener = new ServerSwitchListener(this);

        plugin = this;

        //CONFIG

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
                            configManager.register();
                        } catch (IOException e) {
                        }

                        ConfigThread.interrupt();
                        configisrunning = false;
                    }
                }
            }.start();
        }
        //ENDE CONFIG

        getProxy().getPluginManager().registerCommand(this, new cmd_report());
        getProxy().getPluginManager().registerListener(this, new DisconnectListener(this));
        getProxy().getPluginManager().registerListener(this, new ServerSwitchListener(this));

// -------------------------------
        System.out.println("----------[ReportSystem]----------");
        System.out.println("Plugin aktiviert");
        System.out.println("Version: 1.0");
        System.out.println("Author: einMarius");
        System.out.println("----------[ReportSystem]----------");
// -------------------------------
    }

    public void onDisable() {

        configManager.saveCfg();

// -------------------------------
        System.out.println("----------[ReportSystem]----------");
        System.out.println("Plugin deaktiviert");
        System.out.println("Version: 1.0");
        System.out.println("Author: einMarius");
        System.out.println("----------[ReportSystem]----------");
// -------------------------------

    }

    public ServerSwitchListener getServerSwitchListener(){ return serverSwitchListener; }
    public DisconnectListener getDisconnectListener(){ return disconnectListener; }
    public Report getReport(){ return report; }
    public Utils getUtils() { return utils; }
    public static Main getPlugin(){ return plugin; }
    public ConfigManager getConfigManager(){ return configManager; }
    public cmd_report getCmd_report(){ return cmd_report; }
}