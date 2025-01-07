package ros.eagleoffire.rosevolution.ninjutsu;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerNinjutsuProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<PlayerNinjutsu> PLAYER_NINJUTSU = CapabilityManager.get(new CapabilityToken<PlayerNinjutsu>() {});

    private PlayerNinjutsu ninjutsu = null;
    private final LazyOptional<PlayerNinjutsu> optional = LazyOptional.of(this::createPlayerNinjutsu);

    private PlayerNinjutsu createPlayerNinjutsu(){
        if(this.ninjutsu == null){
            this.ninjutsu = new PlayerNinjutsu();
        }
        return this.ninjutsu;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction direction) {
        if(capability == PLAYER_NINJUTSU){
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerNinjutsu().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerNinjutsu().loadNBTData(nbt);
    }
}
