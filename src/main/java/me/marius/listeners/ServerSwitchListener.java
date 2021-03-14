package me.marius.listeners;

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

        for(ProxiedPlayer team : ProxyServer.getInstance().getPlayers()){
            if(team.hasPermission("report.team")){
                if(team.getServer().getInfo().getName() == p.getServer().getInfo().getName()) {
                    if (!plugin.getCmd_report().reportmodus.contains(team)) {
                        team.sendMessage(plugin.getConfigManager().prefix + p.getName() + " hat den Server im Reportmodus betreten!");
                    }
                }
            }
        }
    }

}
