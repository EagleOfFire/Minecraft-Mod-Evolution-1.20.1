package ros.eagleoffire.rosevolution.quest;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStoppingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import ros.eagleoffire.rosevolution.ninjutsu.PlayerLevelManager;
import ros.eagleoffire.rosevolution.ninjutsu.PlayerNinjutsuProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LoginQuestTracker {
    private static final Map<UUID, PlayerQuestData> playerData = new HashMap<>();
    private static final long COOLDOWN_HOURS = 13;
    private static final long COOLDOWN_MILLIS = COOLDOWN_HOURS * 3600000;

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event){
        ServerPlayer player = (ServerPlayer) event.getEntity();
        UUID playerId = player.getUUID();
        PlayerQuestData data = playerData.computeIfAbsent(playerId, k -> new PlayerQuestData());

        // Check if the  player is eligible for the reward
        if (!data.isLoginOnCooldown()){
            grantRewards(player);
            data.setLoginCooldown(System.currentTimeMillis());
        } else {
            long remainingTime = data.getRemainingCooldownTime();
            long hours = remainingTime / 3600000;
            long minutes = (remainingTime % 3600000) / 3600000;
        }
    }

    private static void grantRewards(ServerPlayer player) {
        player.getCapability(PlayerNinjutsuProvider.PLAYER_NINJUTSU).ifPresent(ninjutsu -> {
            ninjutsu.addExperienceHealth(5);
            ninjutsu.addExperienceChakra(5);
            PlayerLevelManager.checkLevelUps(player, ninjutsu);
        });
    }

    @SubscribeEvent
    public static void onPlayerLeave(PlayerEvent.PlayerLoggedOutEvent event){
        playerData.remove(event.getEntity().getUUID()); //clean up data on logout
    }

    @SubscribeEvent
    public static void onServerStop(ServerStoppingEvent event){
        playerData.clear();
    }

    public static long getCooldownMillis(){return COOLDOWN_MILLIS;}
}
