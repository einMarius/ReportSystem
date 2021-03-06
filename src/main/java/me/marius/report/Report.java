package me.marius.report;

import me.marius.main.Main;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.*;

public class Report {

    public Main plugin;
    public Report(Main plugin){
        this.plugin = plugin;
    }

    //1. Reported player, 2. Reporter
    public static HashMap<ProxiedPlayer, ProxiedPlayer> reported = new HashMap<>();
    //1. Reported player, String cause
    public static HashMap<ProxiedPlayer, String> reported_cause = new LinkedHashMap<>();
    private ProxiedPlayer reportet;
    private ProxiedPlayer reporter;
    private String reason;

    public void newReport(ProxiedPlayer reportet, ProxiedPlayer reporter, String reason){
        this.reported.put(reportet, reporter);
        this.reported_cause.put(reportet, reason);
    }

    public ProxiedPlayer getReportet(ProxiedPlayer reportet){
        if(this.reported.containsKey(reportet))
            return reportet;
        return null;
    }

    public ProxiedPlayer getReporter(ProxiedPlayer reporter){
        if(this.reported.containsValue(reporter))
            return reporter;
        return null;
    }

}
