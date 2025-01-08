package ros.eagleoffire.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import org.jetbrains.annotations.NotNull;
import ros.eagleoffire.rosevolution.ninjutsu.PlayerNinjutsuProvider;
import net.minecraft.network.chat.Component;

import java.util.Objects;

public class AddNinjutsuExperienceCommand {
    public AddNinjutsuExperienceCommand(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("ninjutsu")
                .then(Commands.argument("operation", StringArgumentType.string())
                .then(Commands.argument("target", StringArgumentType.string())
                .then(Commands.argument("quantité", IntegerArgumentType.integer())
                .executes(commandContext ->
                        AddNinjutsuExperience(commandContext.getSource(),
                                StringArgumentType.getString(commandContext, "operation"),
                                StringArgumentType.getString(commandContext, "target"),
                                IntegerArgumentType.getInteger(commandContext, "quantité")
                        ))))));
    }

    private int AddNinjutsuExperience(CommandSourceStack source, String target, String voie, int qts) throws CommandSyntaxException {
        PlayerList OnlinePlayer = source.getServer().getPlayerList();
        @NotNull
        ServerPlayer TargetedPlayer = Objects.requireNonNull(OnlinePlayer.getPlayerByName(target));

        TargetedPlayer.getCapability(PlayerNinjutsuProvider.PLAYER_NINJUTSU).ifPresent(ninjutsu -> {
            source.sendSuccess(() -> {
                ninjutsu.addExperience(qts);
                return Component.literal("Successfully added " + qts + "XP to " + voie + " for player " + target);
            }, true);
            //ModMessages.sendToPlayer(new FuinjutsuDataSyncS2CPacket(progression.getByName("Fuinjutsu")), TargetedPlayer);
        });
        return 1;
    }
}
