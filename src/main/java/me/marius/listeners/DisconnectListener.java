//This file was created in 2021

package me.marius.listeners;

import me.marius.commands.cmd_report;
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

        //Teammitglied leavt im Reportmodus
        if(plugin.getCmd_report().reportmodus.contains(p))
            plugin.getCmd_report().reportmodus.remove(p);

        //Reportet Spieler leavt

        if(Report.reported.containsKey(p)) {
            for (ProxiedPlayer team : cmd_report.loggin) {
                String reportedplayernowoffline = plugin.getConfigManager().reportedplayeroffline.replace("%PLAYER%", p.getName());
                team.sendMessage(plugin.getConfigManager().prefix + reportedplayernowoffline);
            }
        }
        if(Report.reported.containsKey(p))
            Report.reported.remove(p);
        if(Report.reported_cause.containsKey(p))
            Report.reported_cause.remove(p);

        //Teammitglied leavt eingeloggt
        if(cmd_report.loggin.contains(p)){
            cmd_report.loggin.remove(p);
            for(ProxiedPlayer team : cmd_report.loggin){
                String otheruserloggout = plugin.getConfigManager().otheruserloggout.replace("%PLAYER%", p.getName());
                team.sendMessage(plugin.getConfigManager().prefix + otheruserloggout);
            }
        }
    }

}