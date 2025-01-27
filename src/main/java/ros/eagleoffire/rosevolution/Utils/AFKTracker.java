package ros.eagleoffire.rosevolution.Utils;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.server.level.ServerPlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AFKTracker {
    private static final Map<UUID, Long> lastActivity = new HashMap<>();
    private static final long AFK_THRESHOLD = 600_000; // 10 minutes in milliseconds

    @SubscribeEvent
    public void onPlayerActivity(PlayerEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            lastActivity.put(player.getUUID(), System.currentTimeMillis());
        }
    }

    public static boolean isPlayerAFK(ServerPlayer player) {
        UUID playerUUID = player.getUUID();
        long currentTime = System.currentTimeMillis();
        long lastActiveTime = lastActivity.getOrDefault(playerUUID, 0L);
        return currentTime - lastActiveTime > AFK_THRESHOLD;
    }
}
