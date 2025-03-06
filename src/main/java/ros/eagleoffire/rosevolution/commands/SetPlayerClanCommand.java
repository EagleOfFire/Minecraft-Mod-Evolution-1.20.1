package ros.eagleoffire.rosevolution.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import org.jetbrains.annotations.NotNull;
import ros.eagleoffire.rosevolution.network.ModMessages;
import ros.eagleoffire.rosevolution.network.packets.NinjutsuDataSyncS2CPacket;
import ros.eagleoffire.rosevolution.ninjutsu.PlayerNinjutsuProvider;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class SetPlayerClanCommand {
    public enum Clan {
        Senju,
        Uchiha,
        Sarutobi,
        Uzumaki,
        Hyuga,
        Aburame,
        Kaguya,
        Karatachi,
        Hozuki,
        Yuki,
        Momochi
    }

    public SetPlayerClanCommand(CommandDispatcher<CommandSourceStack> Dispatcher) {
        Dispatcher.register(Commands.literal("clan_set")
                .requires(commandSourceStack -> commandSourceStack.hasPermission(2))
                .then(Commands.argument("target", StringArgumentType.string())
                        .suggests(this::suggestTargets)
                        .then(Commands.argument("clan", StringArgumentType.word())
                                .suggests(this::suggestClan)
                                .executes(context -> {
                                    String clanName = StringArgumentType.getString(context, "clan");
                                    if (!isValidClan(clanName)) {
                                        throw new SimpleCommandExceptionType(
                                                Component.literal("Invalid clan name: " + clanName)
                                        ).create();
                                    }
                                    return SetPlayerClan(
                                            context.getSource(),
                                            StringArgumentType.getString(context, "target"),
                                            clanName
                                    );
                                })
                        )
                )
        );

    }

    private CompletableFuture<Suggestions> suggestTargets(CommandContext<CommandSourceStack> source, SuggestionsBuilder builder) {
        PlayerList playerList = source.getSource().getServer().getPlayerList();
        for (ServerPlayer player : playerList.getPlayers()) {
            builder.suggest(player.getName().getString());
        }
        return builder.buildFuture();
    }

    private CompletableFuture<Suggestions> suggestClan(CommandContext<CommandSourceStack> source, SuggestionsBuilder builder) {
        for (SetPlayerClanCommand.Clan clan : SetPlayerClanCommand.Clan.values()) {
            builder.suggest(clan.name());
        }
        return builder.buildFuture();
    }

    private int SetPlayerClan(CommandSourceStack source, String target, String clan) throws CommandSyntaxException {
        PlayerList OnlinePlayer = source.getServer().getPlayerList();
        @NotNull
        ServerPlayer TargetedPlayer = Objects.requireNonNull(OnlinePlayer.getPlayerByName(target));

        TargetedPlayer.getCapability(PlayerNinjutsuProvider.PLAYER_NINJUTSU).ifPresent(ninjutsu -> {
            source.sendSuccess(() -> Component.literal("The Player " + target + " is now part of the clan " + clan)
                    .withStyle(style -> style.withColor(ChatFormatting.BLUE)), true);
            //TODO set player clan in capabilities
            ModMessages.sendToPlayer(new NinjutsuDataSyncS2CPacket(ninjutsu), TargetedPlayer);
        });
        return 1;
    }

    private boolean isValidClan(String name) {
        for (Clan clan : Clan.values()) {
            if (clan.name().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
}
