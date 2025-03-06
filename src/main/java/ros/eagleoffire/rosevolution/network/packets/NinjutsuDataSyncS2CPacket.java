package ros.eagleoffire.rosevolution.network.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import ros.eagleoffire.rosevolution.client.ClientNinjutsuData;
import ros.eagleoffire.rosevolution.ninjutsu.PlayerNinjutsu;

import java.util.function.Supplier;

public class NinjutsuDataSyncS2CPacket {
    private final int experienceChakra;
    private final int levelChakra;
    private final int experienceHealth;
    private final int levelHealth;
    private final String clan;

    // Tracking daily experience caps
    private final int dailyExperienceChakraGained;
    private final int dailyExperienceHealthGained;
    private final long lastResetTimestamp;


    public NinjutsuDataSyncS2CPacket(PlayerNinjutsu source) {
        this.experienceChakra = source.getExperienceChakra();
        this.levelChakra = source.getLevelChakra();
        this.experienceHealth = source.getExperienceHealth();
        this.levelHealth = source.getLevelHealth();
        this.clan = source.getClan();
        this.dailyExperienceChakraGained = source.getDailyExperienceChakraGained();
        this.dailyExperienceHealthGained = source.getDailyExperienceHealthGained();
        this.lastResetTimestamp = source.getLastResetTimestamp();
    }

    public NinjutsuDataSyncS2CPacket(FriendlyByteBuf buf) {
        this.experienceChakra = buf.readInt();
        this.levelChakra = buf.readInt();
        this.experienceHealth = buf.readInt();
        this.levelHealth = buf.readInt();
        this.clan = buf.readUtf();
        this.dailyExperienceChakraGained = buf.readInt();
        this.dailyExperienceHealthGained = buf.readInt();
        this.lastResetTimestamp = buf.readLong();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(this.experienceChakra);
        buf.writeInt(this.levelChakra);
        buf.writeInt(this.experienceHealth);
        buf.writeInt(this.levelHealth);
        buf.writeUtf(this.clan);
        buf.writeInt(this.dailyExperienceChakraGained);
        buf.writeInt(this.dailyExperienceHealthGained);
        buf.writeLong(this.lastResetTimestamp);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ClientNinjutsuData.set(experienceChakra,levelChakra,experienceHealth,levelHealth,clan,dailyExperienceChakraGained,dailyExperienceHealthGained,lastResetTimestamp);
        });
        return true;
    }
}
