//This file was created in 2021

package me.marius.config;

import java.io.File;
import java.io.IOException;

import me.marius.main.Main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class ConfigManager {

    private Main plugin;
    public ConfigManager(Main plugin) {
        this.plugin = plugin;
    }

    public String prefix;
    public String wrongusage;

    public String reportsucces;
    public String usernotonline;
    public String selfreport;
    public String login;
    public String alreadyloggedin;
    public String logout;
    public String alreadyloggedout;
    public String otheruserlogin;
    public String otheruserloggout;
    public String mustbeloggedin;
    public String noreportsopen;
    public String newreport;
    public String noreportforthisplayer;
    public String reportedplayeroffline;
    public String noreport;
    public String alreadymakingreport;
    public String mustreportfinish;
    public String reportfinish;
    public String reportaccept;
    public String reportcause;
    public String reportfrom;
    public String reportetauf;

    public static Configuration config;
    public static File file;

    public void register() throws IOException {

        try {

            if(!plugin.getDataFolder().exists()){
                plugin.getDataFolder().mkdir();
            }

            file = new File(plugin.getDataFolder().getParent(), "ReportSystem/config.yml");

            if(!file.exists()) {
                file.createNewFile();

                config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);

                config.set("Einstellungen.Prefix", "&c&lReport &8&l✕ &7");
                config.set("Einstellungen.Falsche Argumentenkette", "Benutze /report <Spieler> <Grund>");
                config.set("Report.Spieler reportet", "Du hast den Spieler &c%PLAYER% &7erfolgreich reportet.");
                config.set("Report.Spieler nicht online", "Der Spieler ist nicht online!");
                config.set("Report.Selfreport", "Du kannst dich nicht selbst reporten!");
                config.set("Report.Login", "Du hast dich eingeloggt.");
                config.set("Report.Bereits eingeloggt", "Du bist bereits eingeloggt!");
                config.set("Report.Logout", "Du hast dich ausgeloggt.");
                config.set("Report.Bereits ausgeloggt", "Du bist bereits ausgeloggt!");
                config.set("Report.Anderer User Login", "Der Spieler %PLAYER% hat sich eingeloggt.");
                config.set("Report.Anderer User Logout", "Der Spieler %PLAYER% hat sich ausgeloggt.");
                config.set("Report.Muss eingeloggt sein", "Du musst eingeloggt sein!");
                config.set("Report.Keine Reports", "Es sind derzeit keine Reports offen.");
                config.set("Report.Neuer Report", "&c%PLAYER% &7wurde wegen &c%GRUND% &7reportet!");
                config.set("Report.Nicht reportet", "Es existiert kein Report von diesem Spieler!");
                config.set("Report.Reported Spieler offline", "Der Spieler &c%PLAYER% &7ist nun offline und wurde aus der Liste gelöscht!");
                config.set("Report.Kein Report", "Du bearbeitest derzeit keinen &cReport&7!");
                config.set("Report.Bearbeitet bereits", "Du bearbeitest derzeit schon einen &cReport &7➡ /&creport finish&7!");
                config.set("Report.Muss Report schließen", "Du musst zuerst deinen &cReport &7erledigen ➡ /&creport finish&7!");
                config.set("Report.Report angenommen", "Du hast den Report von &c%PLAYER% &7angenommen.");
                config.set("Report.Report Grund", "Grund: &c%GRUND%");
                config.set("Report.Report Von", "Reportet von: &c%PLAYER%");
                config.set("Report.Reportet-Spieler auf", "Aktuell auf: &c%SERVER%");
                config.set("Report.Report finish", "Du hast den &cReport &7geschlossen.");

                saveCfg();
            }
        }catch (IOException ex) {
            System.out.println("Es gab einen Fehler beim erstellen der Config.yml");
        }
        //REGISTER
        config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);

        //Einstellungen

        prefix = config.getString("Einstellungen.Prefix");
        prefix = ChatColor.translateAlternateColorCodes('&', prefix);

        wrongusage = config.getString("Einstellungen.Falsche Argumentenkette");
        wrongusage = ChatColor.translateAlternateColorCodes('&', wrongusage);

        //Ende Einstellungen
        //Report

        reportsucces = config.getString("Report.Spieler reportet");
        reportsucces = ChatColor.translateAlternateColorCodes('&', reportsucces);

        usernotonline = config.getString("Report.Spieler nicht online");
        usernotonline = ChatColor.translateAlternateColorCodes('&', usernotonline);

        selfreport = config.getString("Report.Selfreport");
        selfreport = ChatColor.translateAlternateColorCodes('&', selfreport);

        login = config.getString("Report.Login");
        login = ChatColor.translateAlternateColorCodes('&', login);

        alreadyloggedin = config.getString("Report.Bereits eingeloggt");
        alreadyloggedin = ChatColor.translateAlternateColorCodes('&', alreadyloggedin);

        logout = config.getString("Report.Logout");
        logout = ChatColor.translateAlternateColorCodes('&', logout);

        alreadyloggedout = config.getString("Report.Bereits ausgeloggt");
        alreadyloggedout = ChatColor.translateAlternateColorCodes('&', alreadyloggedout);

        otheruserlogin = config.getString("Report.Anderer User Login");
        otheruserlogin = ChatColor.translateAlternateColorCodes('&', otheruserlogin);

        otheruserloggout = config.getString("Report.Anderer User Logout");
        otheruserloggout = ChatColor.translateAlternateColorCodes('&', otheruserloggout);

        mustbeloggedin = config.getString("Report.Muss eingeloggt sein");
        mustbeloggedin = ChatColor.translateAlternateColorCodes('&', mustbeloggedin);

        noreportsopen = config.getString("Report.Keine Reports");
        noreportsopen = ChatColor.translateAlternateColorCodes('&', noreportsopen);

        newreport = config.getString("Report.Neuer Report");
        newreport = ChatColor.translateAlternateColorCodes('&', newreport);

        noreportforthisplayer = config.getString("Report.Nicht reportet");
        noreportforthisplayer = ChatColor.translateAlternateColorCodes('&', noreportforthisplayer);

        reportedplayeroffline = config.getString("Report.Reported Spieler offline");
        reportedplayeroffline = ChatColor.translateAlternateColorCodes('&', reportedplayeroffline);

        noreport = config.getString("Report.Kein Report");
        noreport = ChatColor.translateAlternateColorCodes('&', noreport);

        alreadymakingreport = config.getString("Report.Bearbeitet bereits");
        alreadymakingreport = ChatColor.translateAlternateColorCodes('&', alreadymakingreport);

        mustreportfinish = config.getString("Report.Muss Report schließen");
        mustreportfinish = ChatColor.translateAlternateColorCodes('&', mustreportfinish);

        reportaccept = config.getString("Report.Report angenommen");
        reportaccept = ChatColor.translateAlternateColorCodes('&', reportaccept);

        reportcause = config.getString("Report.Report Grund");
        reportcause = ChatColor.translateAlternateColorCodes('&', reportcause);

        reportfrom = config.getString("Report.Report Von");
        reportfrom = ChatColor.translateAlternateColorCodes('&', reportfrom);

        reportetauf = config.getString("Report.Reportet-Spieler auf");
        reportetauf = ChatColor.translateAlternateColorCodes('&', reportetauf);

        reportfinish = config.getString("Report.Report finish");
        reportfinish = ChatColor.translateAlternateColorCodes('&', reportfinish);
        //Ende Report
    }

    public static void saveCfg() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, file);
        } catch (IOException e) {
            System.out.println("Es gab einen Fehler beim Speichern der config.yml!");
        }
    }
}
