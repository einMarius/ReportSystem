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
    public String alreadyreported;
    public String reportedplayeroffline;

    public static Configuration config;
    public static File file;

    public void register() throws IOException {

        try {

            if(!Main.getPlugin().getDataFolder().exists()){
                Main.getPlugin().getDataFolder().mkdir();
            }

            file = new File(Main.getPlugin().getDataFolder().getParent(), "ReportSystem/config.yml");

            if(!file.exists()) {
                file.createNewFile();

                config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);

                config.set("Einstellungen.Prefix", "&c&lReport &8&l✕ &7");
                config.set("Einstellungen.Falsche Argumentenkette", "Benutze /report <Spieler> <Grund>");
                config.set("Report.Spieler reportet", "Du hast den Spieler &c%PLAYER% &7erfolgreich reportet!");
                config.set("Report.Spieler nicht online", "Der Spieler ist nicht online!");
                config.set("Report.Selfreport", "Du kannst dich nicht selbst reporten!");
                config.set("Report.Login", "Du hast dich eingeloggt!");
                config.set("Report.Bereits eingeloggt", "Du bist bereits eingeloggt!");
                config.set("Report.Logout", "Du hast dich ausgeloggt!");
                config.set("Report.Bereits ausgeloggt", "Du bist bereits ausgeloggt!");
                config.set("Report.Anderer User Login", "Der Spieler %PLAYER% hat sich eingeloggt!");
                config.set("Report.Anderer User Logout", "Der Spieler %PLAYER% hat sich ausgeloggt!");
                config.set("Report.Muss eingeloggt sein", "Du musst eingeloggt sein!");
                config.set("Report.Keine Reports", "Es sind derzeit keine Reports offen!");
                config.set("Report.Neuer Report", "&c%PLAYER% &7wurde wegen &c%GRUND% &7reportet!");
                config.set("Report.Nicht reportet", "Es existiert kein Report von diesem Spieler!");
                config.set("Report.Schon reportet", "Es existiert bereits ein Report von diesem Spieler!");
                config.set("Report.Reported Spieler offlien", "Der Spieler &c%PLAYER% &7ist nun offline und wurde aus der Liste gelöscht!");
                config.set("Report.Reported Spieler offlien", "Der Spieler &c%PLAYER% &7ist nun offline und wurde aus der Liste gelöscht!");

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

        alreadyreported = config.getString("Report.Schon reportet");
        alreadyreported = ChatColor.translateAlternateColorCodes('&', alreadyreported);

        reportedplayeroffline = config.getString("Report.Reported Spieler offlien");
        reportedplayeroffline = ChatColor.translateAlternateColorCodes('&', reportedplayeroffline);
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
