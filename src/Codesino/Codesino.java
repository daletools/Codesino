package Codesino;

import Codesino.commands.CommandManager;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

public class Codesino {
    private final ShardManager shardManager;
    private final Dotenv config;
    public Codesino() {
        config = Dotenv.configure().load();
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(config.get("TOKEN"));
        builder.setActivity(Activity.playing("casino games"));
        shardManager = builder.build();

        shardManager.addEventListener(new CommandManager());
    }

    public ShardManager getShardManager() {
        return shardManager;
    }

    public Dotenv getConfig() {
        return config;
    }

    public static void main(String[] args) {
        Codesino codesino = new Codesino();
    }
}
