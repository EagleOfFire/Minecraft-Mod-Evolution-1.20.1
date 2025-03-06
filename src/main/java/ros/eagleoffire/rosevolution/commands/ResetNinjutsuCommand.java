package ros.eagleoffire.rosevolution.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import ros.eagleoffire.rosevolution.network.ModMessages;
import ros.eagleoffire.rosevolution.network.packets.NinjutsuDataSyncS2CPacket;
import ros.eagleoffire.rosevolution.ninjutsu.PlayerLevelManager;
import ros.eagleoffire.rosevolution.ninjutsu.PlayerNinjutsuProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ResetNinjutsuCommand {

    private static final Map<String, String> pendingConfirmations = new HashMap<>();

    public ResetNinjutsuCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("rpk")
                .requires(commandSourceStack -> commandSourceStack.hasPermission(2))
                .then(Commands.argument("target", StringArgumentType.string())
                        .suggests(this::suggestTargets)
                        .executes(context -> initiateReset(context)))
                .then(Commands.literal("confirm")
                        .executes(context -> confirmReset(context))));
    }

    private int initiateReset(CommandContext<CommandSourceStack> context) {
        String target = StringArgumentType.getString(context, "target");
        pendingConfirmations.put(context.getSource().getTextName(), target);
        context.getSource().sendSuccess(() -> Component.literal("Please confirm by typing /rpk confirm to proceed with resetting " + target)
                .withStyle(ChatFormatting.YELLOW), false);
        return 1;
    }

    private int confirmReset(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        String playerName = context.getSource().getTextName();
        if (!pendingConfirmations.containsKey(playerName)) {
            context.getSource().sendFailure(Component.literal("No reset operation pending.").withStyle(ChatFormatting.RED));
            return 0;
        }

        String target = pendingConfirmations.remove(playerName);
        return ResetPlayerNinjutsu(context.getSource(), target);
    }

    private CompletableFuture<Suggestions> suggestTargets(CommandContext<CommandSourceStack> source, SuggestionsBuilder builder) {
        PlayerList playerList = source.getSource().getServer().getPlayerList();
        for (ServerPlayer player : playerList.getPlayers()) {
            builder.suggest(player.getName().getString());
        }
        return builder.buildFuture();
    }

    private int ResetPlayerNinjutsu(CommandSourceStack source, String target) throws CommandSyntaxException {
        PlayerList OnlinePlayer = source.getServer().getPlayerList();
        ServerPlayer TargetedPlayer = OnlinePlayer.getPlayerByName(target);

        if (TargetedPlayer == null) {
            source.sendFailure(Component.literal("Player not found.").withStyle(ChatFormatting.RED));
            return 0;
        }

        TargetedPlayer.getCapability(PlayerNinjutsuProvider.PLAYER_NINJUTSU).ifPresent(ninjutsu -> {
            ninjutsu.reset();
            PlayerLevelManager.checkLevelUps(TargetedPlayer, ninjutsu);
            ModMessages.sendToPlayer(new NinjutsuDataSyncS2CPacket(ninjutsu), TargetedPlayer);
            source.sendSuccess(() -> Component.literal("The Player " + target + " was successfully reset by " + source.getEntity().getDisplayName().getString())
                    .withStyle(ChatFormatting.RED), true);
        });
        return 1;
    }
}
