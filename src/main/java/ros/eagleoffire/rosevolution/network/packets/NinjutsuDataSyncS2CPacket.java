package ros.eagleoffire.rosevolution.network.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import ros.eagleoffire.rosevolution.client.ClientNinjutsuData;
import ros.eagleoffire.rosevolution.ninjutsu.PlayerNinjutsu;

import java.util.function.Supplier;

public class NinjutsuDataSyncS2CPacket {
    private final int experience;
    private final int level;
    private final int chakra;

    public NinjutsuDataSyncS2CPacket(PlayerNinjutsu ninjutsu) {
        this.experience = ninjutsu.getExperience();
        this.level = ninjutsu.getLevel();
        this.chakra = ninjutsu.getMaxChakra();
    }

    public NinjutsuDataSyncS2CPacket(FriendlyByteBuf buf) {
        this.experience = buf.readInt();
        this.level = buf.readInt();
        this.chakra = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(this.experience);
        buf.writeInt(this.level);
        buf.writeInt(this.chakra);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ClientNinjutsuData.set(experience,level,chakra);
        });
        return true;
    }
}
