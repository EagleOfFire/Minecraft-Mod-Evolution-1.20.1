package ros.eagleoffire.rosevolution.quest;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStoppingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ros.eagleoffire.rosevolution.ninjutsu.PlayerLevelManager;
import ros.eagleoffire.rosevolution.ninjutsu.PlayerNinjutsuProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber
public class DamageQuestTracker {
    private static final Map<UUID, PlayerQuestData> playerData = new HashMap<>();

    @SubscribeEvent
    public static void onPlayerDamage(LivingDamageEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer serverPlayer)) return;

        UUID playerId = serverPlayer.getUUID();
        PlayerQuestData data = playerData.computeIfAbsent(playerId, k -> new PlayerQuestData());

        // Accumulate damage dealt and received
        data.addDamageDealt(event.getAmount());
        data.addDamageReceived(event.getEntity().getLastHurtByMob() == serverPlayer ? event.getAmount() : 0);

        // Check if quest progress meets the requirement
        if (data.getTotalDamage() >= 100 && !data.isDamageOnCooldown()) {
            grantRewards(serverPlayer);
            data.resetDamage();
            data.setDamageCooldown(System.currentTimeMillis());
        }
    }

    private static void grantRewards(ServerPlayer player) {
        player.getCapability(PlayerNinjutsuProvider.PLAYER_NINJUTSU).ifPresent(ninjutsu -> {
            ninjutsu.addExperienceHealth(1); // Reward 1 XP
            PlayerLevelManager.checkLevelUps(player, ninjutsu);
        });
    }

    @SubscribeEvent
    public static void onPlayerLeave(PlayerEvent.PlayerLoggedOutEvent event) {
        playerData.remove(event.getEntity().getUUID()); // Clean up data on logout
    }

    @SubscribeEvent
    public static void onServerStop(ServerStoppingEvent event) {
        playerData.clear(); // Clear data when the server stops
    }
}
