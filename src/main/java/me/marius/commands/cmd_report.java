//This file was created in 2021

package me.marius.commands;

import me.marius.main.Main;
import me.marius.report.Report;
import me.marius.report.ReportCause;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.*;

public class cmd_report extends Command {

    private Main plugin;
    public cmd_report(Main plugin){
        super("report");
        this.plugin = plugin;
    }

    public cmd_report() {
        super("report");
    }

    public static List<ProxiedPlayer> loggin = new ArrayList<>();
    public static List<ProxiedPlayer> reportmodus = new ArrayList<>();

    public String cause;

    public ProxiedPlayer reportedplayer;

    private boolean isrunningGetList;
    private boolean isrunningSetList;
    private boolean isrunningAcceptReport;
    private boolean isrunningGetReportInfo;

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
                                    String otheruserloginmsg = plugin.getConfigManager().otheruserlogin.replace("%PLAYER%", p.getName());
                                    team.sendMessage(plugin.getConfigManager().prefix + otheruserloginmsg);
                                }
                            }
                            loggin.add(p);
                            p.sendMessage(plugin.getConfigManager().prefix + plugin.getConfigManager().login);

                        }else
                            p.sendMessage(plugin.getConfigManager().prefix + plugin.getConfigManager().alreadyloggedin);

                    } else if(args[0].equalsIgnoreCase("logout")){
                        if(loggin.contains(p)){
                            if(!reportmodus.contains(p)) {
                                loggin.remove(p);
                                p.sendMessage(plugin.getConfigManager().prefix + plugin.getConfigManager().logout);
                                for (ProxiedPlayer team : ProxyServer.getInstance().getPlayers()) {
                                    if (loggin.contains(team)) {
                                        //team message
                                        String otheruserlogoutmsg = plugin.getConfigManager().otheruserloggout.replace("%PLAYER%", p.getName());
                                        team.sendMessage(plugin.getConfigManager().prefix + otheruserlogoutmsg);
                                    }
                                }
                            }else
                                p.sendMessage(plugin.getConfigManager().prefix + plugin.getConfigManager().mustreportfinish);
                        }else
                            p.sendMessage(plugin.getConfigManager().prefix + plugin.getConfigManager().alreadyloggedin);

                    } else if(args[0].equalsIgnoreCase("list")){
                        if(loggin.contains(p)){
                            isrunningGetList = !isrunningGetList;

                            new Thread(){
                                @Override
                                public void run(){
                                    super.run();

                                    while (isrunningGetList) {
                                        if (!Report.reported.isEmpty()) {
                                            p.sendMessage("");
                                            p.sendMessage(plugin.getConfigManager().prefix + "----------= §c§lReports §7=----------");
                                            for (ProxiedPlayer reportet : Report.reported.keySet()) {
                                                p.sendMessage("§7Spieler: §c" + reportet.getName() + " §8┃ §7wegen: §c" +
                                                        Report.reported_cause.get(reportet) + " §8┃ §7von: §c" + plugin.getReport().reported.get(reportet) +
                                                        " §8┃ §7aktuell auf §c" + reportet.getServer().getInfo().getName());
                                                continue;
                                            }
                                            p.sendMessage(plugin.getConfigManager().prefix + "----------= §c§lReports §7=----------");

                                        }else
                                            p.sendMessage(plugin.getConfigManager().prefix + plugin.getConfigManager().noreportsopen);

                                        try{
                                            Thread.sleep(250);
                                        }catch (InterruptedException ex){
                                            ex.printStackTrace();
                                        }
                                        isrunningGetList = false;
                                    }
                                }
                            }.start();

                        }else
                            p.sendMessage(plugin.getConfigManager().prefix + plugin.getConfigManager().mustbeloggedin);

                    } else if(args[0].equalsIgnoreCase("next")){
                        if(loggin.contains(p)){
                            if(!reportmodus.contains(p)) {
                                isrunningAcceptReport = !isrunningAcceptReport;
                                new Thread() {
                                    @Override
                                    public void run() {
                                        super.run();

                                        while (isrunningAcceptReport) {
                                            if (!Report.reported.isEmpty()) {
                                                for (ProxiedPlayer reportet : Report.reported.keySet()) {
                                                    if (Report.reported.values().iterator().hasNext()) {
                                                        String reportaccept = plugin.getConfigManager().reportaccept.replace("%PLAYER%", reportet.getName());
                                                        String reportcause = plugin.getConfigManager().reportcause.replace("%GRUND%", Report.reported_cause.get(reportet));
                                                        String reportetfrom = plugin.getConfigManager().reportfrom.replace("%PLAYER%", Report.reported.get(reportet).getName());
                                                        String reportetauf = plugin.getConfigManager().reportetauf.replace("%SERVER%", reportet.getServer().getInfo().getName());
                                                        p.sendMessage("");
                                                        p.sendMessage(plugin.getConfigManager().prefix + reportaccept);
                                                        p.sendMessage(plugin.getConfigManager().prefix + reportcause);
                                                        p.sendMessage(plugin.getConfigManager().prefix + reportetfrom);
                                                        p.sendMessage(plugin.getConfigManager().prefix + reportetauf);
                                                        p.sendMessage("");
                                                        p.connect(ProxyServer.getInstance().getServerInfo(reportet.getServer().getInfo().getName()));
                                                        Report.reported.remove(reportet);
                                                        Report.reported_cause.remove(reportet);
                                                        reportmodus.add(p);
                                                        break;
                                                    }
                                                }
                                            } else
                                                p.sendMessage(plugin.getConfigManager().prefix + plugin.getConfigManager().noreportsopen);
                                            try {
                                                Thread.sleep(250);
                                            } catch (InterruptedException ex) {
                                                ex.printStackTrace();
                                            }
                                            isrunningAcceptReport = false;
                                        }
                                    }
                                }.start();
                            }else
                                p.sendMessage(plugin.getConfigManager().prefix + plugin.getConfigManager().alreadymakingreport);
                        }else
                            p.sendMessage(plugin.getConfigManager().prefix + plugin.getConfigManager().mustbeloggedin);
                    }else if(args[0].equalsIgnoreCase("info")){
                        if(loggin.contains(p)){
                            if(reportmodus.contains(p)){
                                isrunningGetReportInfo = !isrunningGetReportInfo;
                                new Thread(){
                                    @Override
                                    public void run(){
                                        super.run();
                                        while(isrunningGetReportInfo){

                                            for(ProxiedPlayer reportet : Report.reported.keySet()) {
                                                String reportaccept = plugin.getConfigManager().reportaccept.replace("%PLAYER%", reportet.getName());
                                                String reportcause = plugin.getConfigManager().reportcause.replace("%GRUND%", Report.reported_cause.get(reportet));
                                                String reportetfrom = plugin.getConfigManager().reportfrom.replace("%PLAYER%", Report.reported.get(reportet).getName());
                                                String reportetauf = plugin.getConfigManager().reportetauf.replace("%SERVER%", reportet.getServer().getInfo().getName());
                                                p.sendMessage(plugin.getConfigManager().prefix + reportaccept);
                                                p.sendMessage(plugin.getConfigManager().prefix + reportcause);
                                                p.sendMessage(plugin.getConfigManager().prefix + reportetfrom);
                                                p.sendMessage(plugin.getConfigManager().prefix + reportetauf);
                                            }
                                            try{
                                                Thread.sleep(250);
                                            }catch (InterruptedException ex){
                                                ex.printStackTrace();
                                            }
                                            isrunningGetReportInfo = false;
                                        }
                                    }
                                }.start();
                            }else
                                p.sendMessage(plugin.getConfigManager().prefix + plugin.getConfigManager().noreport);
                        }else
                            p.sendMessage(plugin.getConfigManager().prefix + plugin.getConfigManager().mustbeloggedin);
                    }else if(args[0].equalsIgnoreCase("finish")){
                        if(loggin.contains(p)){
                            if(reportmodus.contains(p)){
                                reportmodus.remove(p);
                                p.sendMessage(plugin.getConfigManager().prefix + plugin.getConfigManager().reportfinish);
                            }else
                                p.sendMessage(plugin.getConfigManager().prefix + plugin.getConfigManager().noreport);
                        }else
                            p.sendMessage(plugin.getConfigManager().prefix + plugin.getConfigManager().mustbeloggedin);
                    }else
                        plugin.getUtils().sendTeamHelpMessage(p);
                } else if(args.length == 2){
                        if (args[0].equalsIgnoreCase("accept")) {
                            if(loggin.contains(p)) {
                                if(!reportmodus.contains(p)) {
                                    reportedplayer = ProxyServer.getInstance().getPlayer(args[1]);
                                    if (Report.reported.containsKey(reportedplayer)) {
                                        String reportaccept = plugin.getConfigManager().reportaccept.replace("%PLAYER%", reportedplayer.getName());
                                        String reportcause = plugin.getConfigManager().reportcause.replace("%GRUND%", Report.reported_cause.get(reportedplayer));
                                        String reportetfrom = plugin.getConfigManager().reportfrom.replace("%PLAYER%", Report.reported.get(reportedplayer).getName());
                                        String reportetauf = plugin.getConfigManager().reportetauf.replace("%SERVER%", reportedplayer.getServer().getInfo().getName());
                                        p.sendMessage("");
                                        p.sendMessage(plugin.getConfigManager().prefix + reportaccept);
                                        p.sendMessage(plugin.getConfigManager().prefix + reportcause);
                                        p.sendMessage(plugin.getConfigManager().prefix + reportetfrom);
                                        p.sendMessage(plugin.getConfigManager().prefix + reportetauf);
                                        p.sendMessage("");
                                        p.connect(ProxyServer.getInstance().getServerInfo(reportedplayer.getServer().getInfo().getName()));
                                        Report.reported.remove(reportedplayer);
                                        Report.reported_cause.remove(reportedplayer);
                                        reportmodus.add(p);
                                    } else
                                        p.sendMessage(plugin.getConfigManager().prefix + plugin.getConfigManager().noreportforthisplayer);
                                }else
                                    p.sendMessage(plugin.getConfigManager().prefix + plugin.getConfigManager().alreadymakingreport);
                            } else
                                p.sendMessage(plugin.getConfigManager().prefix + plugin.getConfigManager().mustbeloggedin);
                        } else
                            plugin.getUtils().sendTeamHelpMessage(p);
                }else
                    plugin.getUtils().sendTeamHelpMessage(p);

            } else {
                ProxiedPlayer reporter = (ProxiedPlayer) sender;
                if(args.length == 2){
                    ProxiedPlayer reportedplayer = ProxyServer.getInstance().getPlayer(args[0]);
                    cause = args[1];
                    cause = ReportCause.getReportCause(cause);

                    if(!(reportedplayer == null)) {
                        if(!Report.reported.containsKey(reportedplayer)) {
                            //if(!(reportedplayer == p)) {
                            if (cause == null) {
                                reporter.sendMessage(plugin.getConfigManager().prefix + "Gültige Reportgründe: " + ReportCause.getNames(ReportCause.class));
                                return;
                            }

                            isrunningSetList = !isrunningSetList;

                            new Thread() {
                                @Override
                                public void run() {
                                    super.run();

                                    while (isrunningSetList) {
                                        plugin.getReport().newReport(reportedplayer, p, cause);
                                        try {
                                            Thread.sleep(250);
                                        } catch (InterruptedException ex) {
                                            ex.printStackTrace();
                                        }
                                        isrunningSetList = false;
                                    }

                                }
                            }.start();

                            String reportsuccesplayer = plugin.getConfigManager().reportsucces.replace("%PLAYER%", args[0]);
                            reporter.sendMessage(plugin.getConfigManager().prefix + reportsuccesplayer);

                            for (ProxiedPlayer team : loggin) {
                                String report = plugin.getConfigManager().newreport.replace("%PLAYER%", args[0]);
                                String reportuse = report.replace("%GRUND%", cause);
                                team.sendMessage(plugin.getConfigManager().prefix + reportuse);
                            }

                            //}else
                            //p.sendMessage(ConfigManager.prefix + ConfigManager.selfreport);
                        } else {
                            String reportsuccesplayer = plugin.getConfigManager().reportsucces.replace("%PLAYER%", args[0]);
                            reporter.sendMessage(plugin.getConfigManager().prefix + reportsuccesplayer);

                            for (ProxiedPlayer team : loggin) {
                                String report = plugin.getConfigManager().newreport.replace("%PLAYER%", args[0]);
                                String reportuse = report.replace("%GRUND%", cause);
                                team.sendMessage(plugin.getConfigManager().prefix + reportuse);
                            }
                        }
                    }else
                        reporter.sendMessage(plugin.getConfigManager().prefix + plugin.getConfigManager().usernotonline);
                } else
                     reporter.sendMessage(plugin.getConfigManager().prefix + plugin.getConfigManager().wrongusage);
            }

        } else
            sender.sendMessage("§4Must be a player!");

    }
}