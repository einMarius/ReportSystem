package me.marius.listeners;

import me.marius.commands.cmd_report;
import me.marius.main.Main;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ServerSwitchListener implements Listener {

    private Main plugin;
    public ServerSwitchListener(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onServerSwitch(ServerSwitchEvent e){

        ProxiedPlayer p = e.getPlayer();

        for(ProxiedPlayer team : cmd_report.loggin){
            if (cmd_report.reportmodus.contains(p)) {
                if (team.getServer().getInfo().getName() == p.getServer().getInfo().getName()) {
                    team.sendMessage(plugin.getConfigManager().prefix + p.getName() + " hat den Server im Reportmodus betreten!");
                }
            }
        }
    }
}
