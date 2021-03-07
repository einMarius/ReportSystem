//This file was created in 2021

package me.littlq.config;

import java.io.File;
import java.io.IOException;

import me.littlq.main.Main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class ConfigManager {

    public static String prefix;
    public static String wrongusage;

    public static String host;
    public static int port;
    public static String database;
    public static String username;
    public static String password;

    public static String reportsucces;
    public static String usernotonline;
    public static String selfreport;
    public static String login;
    public static String alreadyloggedin;
    public static String logout;
    public static String alreadyloggedout;
    public static String otheruserlogin;
    public static String otheruserloggout;
    public static String mustbeloggedin;
    public static String openreports;
    public static String newreport;

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
                config.set("MySQL.Host", "localhost");
                config.set("MySQL.Port", "3306");
                config.set("MySQL.Database", "reportsystem");
                config.set("MySQL.Username", "root");
                config.set("MySQL.Password", "");
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
        //MySQL

        host = config.getString("MySQL.Host");
        port = config.getInt("MySQL.Port");
        database = config.getString("MySQL.Database");
        username = config.getString("MySQL.Username");
        password = config.getString("MySQL.Password");

        //Ende MySQL
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

        openreports = config.getString("Report.Keine Reports");
        openreports = ChatColor.translateAlternateColorCodes('&', openreports);

        newreport = config.getString("Report.Neuer Report");
        newreport = ChatColor.translateAlternateColorCodes('&', newreport);

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
