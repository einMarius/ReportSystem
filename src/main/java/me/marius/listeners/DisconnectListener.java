//This file was created in 2021

package me.marius.listeners;

import me.marius.main.Main;
import me.marius.report.Report;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class DisconnectListener implements Listener {

    private Main plugin;
    public DisconnectListener(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onReportedPlayerDisconnect(PlayerDisconnectEvent e) {
        ProxiedPlayer p = e.getPlayer();

        if(Report.reported.containsKey(p))
            Report.reported.remove(p);

        for(ProxiedPlayer team : plugin.getCmd_report().loggin){
            String reportedplayernowoffline = plugin.getConfigManager().reportedplayeroffline.replace("%PLAYER%", p.getName());
            team.sendMessage(plugin.getConfigManager().prefix + reportedplayernowoffline);
        }

    }

}