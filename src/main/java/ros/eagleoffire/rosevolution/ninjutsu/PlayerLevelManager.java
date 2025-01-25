package ros.eagleoffire.rosevolution.ninjutsu;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import ros.eagleoffire.rosevolution.network.ModMessages;
import ros.eagleoffire.rosevolution.network.packets.NinjutsuDataSyncS2CPacket;
import ros.eagleoffire.rosevolution.network.packets.OpenLevelUpScreenS2CPacket;

import java.awt.*;
import java.util.UUID;

public class PlayerLevelManager {
    private static final UUID HEALTH_BONUS_UUID = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

    public static void checkChakraLevelUp(Player player, PlayerNinjutsu cap) {
        int requiredXp = getXpForNextLevel(cap.getLevelChakra());
        if (cap.getExperienceChakra() >= requiredXp) {
            cap.setLevelChakra(cap.getLevelChakra() + 1);
            cap.addExperienceChakra(-requiredXp);
            applyStatUpgrades(player, cap.getLevelChakra());
            ModMessages.sendToPlayer(new NinjutsuDataSyncS2CPacket(cap), (ServerPlayer) player);
            //TODO change custom screen to definitive one
            ModMessages.sendToPlayer(new OpenLevelUpScreenS2CPacket(), (ServerPlayer) player);
            player.sendSystemMessage(Component.literal("Level Up!"));
        }
    }

    public static void checkHealthLevelUp(Player player, PlayerNinjutsu cap) {
        int requiredXp = getXpForNextLevel(cap.getLevelHealth());
        if (cap.getExperienceHealth() >= requiredXp) {
            cap.setLevelHealth(cap.getLevelHealth() + 1);
            cap.addExperienceHealth(-requiredXp);
            applyStatUpgrades(player, cap.getLevelHealth());
            ModMessages.sendToPlayer(new NinjutsuDataSyncS2CPacket(cap), (ServerPlayer) player);
            //TODO change custom screen to definitive one
            ModMessages.sendToPlayer(new OpenLevelUpScreenS2CPacket(), (ServerPlayer) player);
            player.sendSystemMessage(Component.literal("Level Up!"));
        }
    }

    private static int getXpForNextLevel(int level) {
        return level * 100; // Example: XP required increases linearly
        //TODO change equation to match excel sheet
    }

    private static void applyStatUpgrades(Player player, int level) {
        AttributeInstance healthAttribute = player.getAttribute(Attributes.MAX_HEALTH);

        if (healthAttribute != null) {
            // Remove the existing modifier if it exists
            healthAttribute.removeModifier(HEALTH_BONUS_UUID);

            // Calculate bonus health (e.g., +2 health per level)
            double bonusHealth = (level - 1) * 2.0;

            // Create and add the new modifier
            AttributeModifier healthModifier = new AttributeModifier(
                HEALTH_BONUS_UUID,
                "Level-Up Bonus Health",
                bonusHealth,
                AttributeModifier.Operation.ADDITION
            );

            healthAttribute.addPermanentModifier(healthModifier);
            //TODO add chakra modifier
        }
    }
}

