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
    private static final long FIVE_MINUTES_MILLIS = 5 * 60 * 1000; // 5 minutes in milliseconds

    @SubscribeEvent
    public static void onPlayerDamage(LivingDamageEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer serverPlayer)) return;

        UUID playerId = serverPlayer.getUUID();
        PlayerQuestData data = playerData.computeIfAbsent(playerId, k -> new PlayerQuestData());

        // Accumulate damage dealt and received
        data.addDamageDealt(event.getAmount());
        data.addDamageReceived(event.getEntity().getLastHurtByMob() == serverPlayer ? event.getAmount() : 0);

        // Check if quest progress meets the requirement
        if (data.getTotalDamage() >= 100 && !data.isOnCooldown()) {
            grantRewards(serverPlayer);
            data.resetDamage();
            data.setCooldown(System.currentTimeMillis());
        }
    }

    private static void grantRewards(ServerPlayer player) {
        player.getCapability(PlayerNinjutsuProvider.PLAYER_NINJUTSU).ifPresent(ninjutsu -> {
            ninjutsu.addExperienceHealth(1); // Reward 1 XP
            PlayerLevelManager.checkLevelUps(player, ninjutsu);
            player.sendSystemMessage(Component.literal("Quest Complete! You gained 1 XP for dealing and receiving damage."));
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

    // Inner class for tracking player quest data
    private static class PlayerQuestData {
        private double damageDealt = 0;
        private double damageReceived = 0;
        private long cooldownEndTime = 0;

        public void addDamageDealt(double damage) {
            damageDealt += damage;
        }

        public void addDamageReceived(double damage) {
            damageReceived += damage;
        }

        public double getTotalDamage() {
            return damageDealt + damageReceived;
        }

        public void resetDamage() {
            damageDealt = 0;
            damageReceived = 0;
        }

        public boolean isOnCooldown() {
            return System.currentTimeMillis() < cooldownEndTime;
        }

        public void setCooldown(long currentTimeMillis) {
            cooldownEndTime = currentTimeMillis + FIVE_MINUTES_MILLIS; // 5 minutes cooldown
        }
    }
}
