//This file was created in 2021

package me.littlq.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class cmd_report extends Command {

    public cmd_report() {
        super("report");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if (sender instanceof ProxiedPlayer) {

            ProxiedPlayer p = (ProxiedPlayer) sender;

            if(p.hasPermission("report.team")){

                if(args.length == 1){

                }

            } else {

                if(args.length == 1){



                }

            }

        } else
            sender.sendMessage("§4Must be a player!");

    }
}