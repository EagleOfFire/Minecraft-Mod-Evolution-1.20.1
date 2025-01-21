package ros.eagleoffire.rosevolution.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
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

public class GetNinjutsuLevelCommand {

    public GetNinjutsuLevelCommand(CommandDispatcher<CommandSourceStack> Dispatcher) {
        Dispatcher.register(Commands.literal("ninjutsustats")
                .then(Commands.argument("target", StringArgumentType.string())
                .executes(commandContext ->
                GetProgressionVoiesNinjas(commandContext.getSource(),
                        StringArgumentType.getString(commandContext, "target")))
                ));
    }

    private int GetProgressionVoiesNinjas(CommandSourceStack source, String target) throws CommandSyntaxException {
        PlayerList OnlinePlayer = source.getServer().getPlayerList();
        @NotNull
        ServerPlayer TargetedPlayer = Objects.requireNonNull(OnlinePlayer.getPlayerByName(target));

        TargetedPlayer.getCapability(PlayerNinjutsuProvider.PLAYER_NINJUTSU).ifPresent(progression -> {
            source.sendSuccess(() -> {
                return Component.literal("Player " + target + " has:\n")
                        .append(Component.literal("- Total Experience: " + progression.getExperience() + "\n"))
                        .append(Component.literal("- Total Level: " + progression.getLevel() + "\n"))
                        .append(Component.literal("- Total Max Health: " + TargetedPlayer.getMaxHealth() + "\n")
                                .withStyle(style -> style.withColor(ChatFormatting.RED)))
                        .append(Component.literal("- Total Max Chakra: " + progression.getMaxChakra() + "\n")
                                .withStyle(style -> style.withColor(ChatFormatting.BLUE)));
            }, true);
            ModMessages.sendToPlayer(new NinjutsuDataSyncS2CPacket(progression), TargetedPlayer);
        });
        return 1;
    }
}
