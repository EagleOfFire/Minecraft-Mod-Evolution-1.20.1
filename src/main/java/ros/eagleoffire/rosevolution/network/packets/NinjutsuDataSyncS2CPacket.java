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

    public NinjutsuDataSyncS2CPacket(PlayerNinjutsu source) {
        this.experienceChakra = source.getExperienceChakra();
        this.levelChakra = source.getLevelChakra();
        this.experienceHealth = source.getExperienceHealth();
        this.levelHealth = source.getLevelHealth();
    }

    public NinjutsuDataSyncS2CPacket(FriendlyByteBuf buf) {
        this.experienceChakra = buf.readInt();
        this.levelChakra = buf.readInt();
        this.experienceHealth = buf.readInt();
        this.levelHealth = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(this.experienceChakra);
        buf.writeInt(this.levelChakra);
        buf.writeInt(this.experienceHealth);
        buf.writeInt(this.levelHealth);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ClientNinjutsuData.set(experienceChakra,levelChakra,experienceHealth,levelHealth);
        });
        return true;
    }
}
