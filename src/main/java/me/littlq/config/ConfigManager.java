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

    private static Configuration config = Main.config;
    private static File file = Main.file;

    public static void register() {

        config.set("Einstellungen.Prefix", "§c§lReport §8§l× §7");


        saveCfg();

        //REGISTER

        Main.prefix = config.getString("Einstellungen.Prefix");
        Main.prefix = ChatColor.translateAlternateColorCodes('&', Main.prefix);

    }

    public static void saveCfg() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, file);
        } catch (IOException e) {
            System.out.println("Es gab einen Fehler beim Speichern der config.yml!");
        }
    }
}
