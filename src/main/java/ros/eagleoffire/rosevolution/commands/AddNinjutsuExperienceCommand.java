package ros.eagleoffire.rosevolution.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import org.jetbrains.annotations.NotNull;
import ros.eagleoffire.rosevolution.network.ModMessages;
import ros.eagleoffire.rosevolution.network.packets.NinjutsuDataSyncS2CPacket;
import ros.eagleoffire.rosevolution.ninjutsu.PlayerLevelManager;
import ros.eagleoffire.rosevolution.ninjutsu.PlayerNinjutsuProvider;
import net.minecraft.network.chat.Component;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class AddNinjutsuExperienceCommand {
    public enum Side {
        Chakra,
        Health
    }

    public AddNinjutsuExperienceCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("add_ninjutsu_experience")
                .requires(commandSourceStack -> commandSourceStack.hasPermission(2))
                .then(Commands.argument("side", StringArgumentType.string())
                        .suggests(this::suggestSide)
                        .then(Commands.argument("target", StringArgumentType.string())
                                .suggests(this::suggestTargets)
                                .then(Commands.argument("quantité", IntegerArgumentType.integer())
                                        .executes(commandContext ->
                                                AddNinjutsuExperience(commandContext.getSource(),
                                                        StringArgumentType.getString(commandContext, "side"),
                                                        StringArgumentType.getString(commandContext, "target"),
                                                        IntegerArgumentType.getInteger(commandContext, "quantité")
                                                ))))));
    }

    private CompletableFuture<Suggestions> suggestSide(CommandContext<CommandSourceStack> source, SuggestionsBuilder builder) {
        for (Side side : Side.values()) {
            builder.suggest(side.name());
        }
        return builder.buildFuture();
    }

    private CompletableFuture<Suggestions> suggestTargets(CommandContext<CommandSourceStack> source, SuggestionsBuilder builder) {
        PlayerList playerList = source.getSource().getServer().getPlayerList();
        for (ServerPlayer player : playerList.getPlayers()) {
            builder.suggest(player.getName().getString());
        }
        return builder.buildFuture();
    }

    private int AddNinjutsuExperience(CommandSourceStack source, String side, String target, int qts) throws CommandSyntaxException {
        PlayerList OnlinePlayer = source.getServer().getPlayerList();
        @NotNull
        ServerPlayer TargetedPlayer = Objects.requireNonNull(OnlinePlayer.getPlayerByName(target));

        TargetedPlayer.getCapability(PlayerNinjutsuProvider.PLAYER_NINJUTSU).ifPresent(ninjutsu -> {
            source.sendSuccess(() -> {
                try {
                    Side selectedSide = Side.valueOf(side);
                    switch (selectedSide) {
                        case Chakra:
                            ninjutsu.addExperienceChakra(qts);
                            PlayerLevelManager.checkLevelUps(TargetedPlayer,ninjutsu);
                            return Component.literal("Successfully added " + qts + " XP to Chakra for player " + target);
                        case Health:
                            ninjutsu.addExperienceHealth(qts);
                            PlayerLevelManager.checkLevelUps(TargetedPlayer,ninjutsu);
                            return Component.literal("Successfully added " + qts + " XP to Health for player " + target);
                    }
                } catch (IllegalArgumentException e) {
                    return Component.literal("Invalid side. Please use Chakra or Health.");
                }
                return Component.literal("An error occurred.");
            }, true);
            ModMessages.sendToPlayer(new NinjutsuDataSyncS2CPacket(ninjutsu), TargetedPlayer);
        });
        return 1;
    }
}
