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

        TargetedPlayer.getCapability(PlayerNinjutsuProvider.PLAYER_NINJUTSU).ifPresent(ninjutsu -> {
            source.sendSuccess(() -> {
                return Component.literal("Player " + target + " has:\n")
                        .append(Component.literal("- Total Experience Chakra: " + ninjutsu.getExperienceChakra() + "\n")
                                .withStyle(style -> style.withColor(ChatFormatting.BLUE)))
                        .append(Component.literal("- Total Level Chakra: " + ninjutsu.getLevelChakra() + "\n")
                                .withStyle(style -> style.withColor(ChatFormatting.BLUE)))
                        .append(Component.literal("- Total Experience Health: " + ninjutsu.getExperienceHealth() + "\n")
                                .withStyle(style -> style.withColor(ChatFormatting.RED)))
                        .append(Component.literal("- Total Level Health: " + ninjutsu.getLevelHealth() + "\n")
                                .withStyle(style -> style.withColor(ChatFormatting.RED)))
                        .append(Component.literal("- Total Max Health: " + TargetedPlayer.getMaxHealth() + "\n")
                                .withStyle(style -> style.withColor(ChatFormatting.RED)));
            }, true);
            ModMessages.sendToPlayer(new NinjutsuDataSyncS2CPacket(ninjutsu), TargetedPlayer);
        });
        return 1;
    }
}
