//This file was created in 2021

package me.littlq.commands;

import com.google.common.collect.HashBasedTable;
import me.littlq.config.ConfigManager;
import me.littlq.report.ReportCause;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class cmd_report extends Command {

    public cmd_report() {
        super("report");
    }

    private static HashMap<ProxiedPlayer, String> reportlist = new HashMap<>();
    public static List<ProxiedPlayer> loggin = new ArrayList<>();

    @Override
    public void execute(CommandSender sender, String[] args) {

        if (sender instanceof ProxiedPlayer) {

            ProxiedPlayer p = (ProxiedPlayer) sender;

            if(p.hasPermission("report.team")){

                if(args.length == 1){

                    if(args[0].equalsIgnoreCase("login")){
                        if(!loggin.contains(p)){
                            for(ProxiedPlayer team : ProxyServer.getInstance().getPlayers()){
                                if(loggin.contains(team)){
                                    //team message
                                    String otheruserloginmsg = ConfigManager.otheruserlogin.replace("%PLAYER%", p.getName());
                                    team.sendMessage(ConfigManager.prefix + otheruserloginmsg);
                                }
                            }
                            loggin.add(p);
                            p.sendMessage(ConfigManager.prefix + ConfigManager.login);

                        }else
                            p.sendMessage(ConfigManager.prefix + ConfigManager.alreadyloggedin);

                    } else if(args[0].equalsIgnoreCase("logout")){
                        if(loggin.contains(p)){
                            loggin.remove(p);
                            p.sendMessage(ConfigManager.prefix + ConfigManager.logout);
                            for(ProxiedPlayer team : ProxyServer.getInstance().getPlayers()){
                                if(loggin.contains(team)){
                                    //team message
                                    String otheruserlogoutmsg = ConfigManager.otheruserloggout.replace("%PLAYER%", p.getName());
                                    team.sendMessage(ConfigManager.prefix + otheruserlogoutmsg);
                                }
                            }

                        }else
                            p.sendMessage(ConfigManager.prefix + ConfigManager.alreadyloggedin);

                    } else if(args[0].equalsIgnoreCase("list")){
                        if(loggin.contains(p)){
                            if(!reportlist.isEmpty()) {
                                p.sendMessage("");
                                p.sendMessage(ConfigManager.prefix + "§7----------= §cReports §7=----------");
                                for (ProxiedPlayer team : reportlist.keySet()) {
                                    p.sendMessage(ConfigManager.prefix + team.getName() + " §8| §7von " + reportlist.get(team));
                                    continue;
                                }
                            }else
                                p.sendMessage(ConfigManager.prefix + ConfigManager.openreports);
                        }else
                            p.sendMessage(ConfigManager.prefix + ConfigManager.mustbeloggedin);

                    }
                } else {
                    p.sendMessage("Wrong args");
                }

            } else {

                if(args.length == 2){

                    ProxiedPlayer reportedplayer = ProxyServer.getInstance().getPlayer(args[0]);
                    String cause = args[1];
                    cause = ReportCause.getReportCause(cause);

                    if(!(reportedplayer == null)) {
                        if(!(reportedplayer == p)) {


                            if (cause == null) {
                                p.sendMessage(ConfigManager.prefix + "Gültige Reportgründe: Hacking, Teaming, Bugusing, Boosting, Name, Skin, Clan, Trolling, Hunting");
                                return;
                            }

                            String reportsuccesplayer = ConfigManager.reportsucces.replace("%PLAYER%", args[0]);
                            p.sendMessage(ConfigManager.prefix + reportsuccesplayer);
                            reportlist.put(reportedplayer, cause);

                            for(ProxiedPlayer team : loggin){
                                String report = ConfigManager.newreport.replace("%PLAYER%", args[0]);
                                String reportuse = report.replace("%GRUND%", args[1]);
                                team.sendMessage(ConfigManager.prefix + reportuse);
                            }

                        }else
                            p.sendMessage(ConfigManager.prefix + ConfigManager.selfreport);
                    }else
                        p.sendMessage(ConfigManager.prefix + ConfigManager.usernotonline);
                } else
                     p.sendMessage(ConfigManager.prefix + ConfigManager.wrongusage);
            }

        } else
            sender.sendMessage("§4Must be a player!");

    }
}