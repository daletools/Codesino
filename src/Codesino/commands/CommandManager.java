package Codesino.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

import java.awt.*;

public class CommandManager extends ListenerAdapter {

    @Override
    public void onGuildReady(GuildReadyEvent event) {
        event.getGuild().updateCommands().addCommands(
                Commands.slash("ping", "pongs your ping"),
                Commands.slash("blackjack", "Double down!")
        ).queue();
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        String commandName = event.getName();

        switch (commandName) {
            case "ping": {
                ping(event);
                break;
            }
            case "blackjack": {
                blackjack(event);
            }
        }
    }

    private void ping(SlashCommandInteractionEvent event) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Pong");
        embed.setDescription("Pong Card");
        embed.setColor(new Color(255, 128, 255));
        embed.setFooter("Foot");
        event.replyEmbeds(embed.build()).queue();
    }

    private void blackjack(SlashCommandInteractionEvent event) {
        Blackjack blackjack = new Blackjack(event);
    }
}
