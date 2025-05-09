package ros.eagleoffire.rosevolution.screen;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.context.UseOnContext;
import org.jetbrains.annotations.NotNull;
import ros.eagleoffire.rosevolution.ROSEvolution;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.entity.player.Player;
import ros.eagleoffire.rosevolution.screen.specialisationScreen.TexturesScreen;
import ros.eagleoffire.rosevolution.sound.ModSounds;

public class LevelUpScreen extends Screen {
    private static final Component TITLE =
            Component.translatable("gui." + ROSEvolution.MODID + ".example_block_screen");

    private static final ResourceLocation LEVEL_UP_GIF = new ResourceLocation(ROSEvolution.MODID, "textures/gui/evosys_levelup.png");

    private int imageWidth;
    private int imageHeight;
    private final Object Context;
    private int ticksRemaining;
    private int leftPos, topPos;
    private Player player;
    private static final int DISPLAY_DURATION = 40; // Duration in ticks (20 ticks per second)
    private static SimpleSoundInstance levelUpSoundInstance;
    private static final int DISPLAY_DURATION = 75; // Duration in ticks (5 seconds, 20 ticks per second)
    private boolean soundPlayed = false; // Add this flag


    public LevelUpScreen() {
        super(TITLE);
        this.Context = null;
        this.player = null;
        this.ticksRemaining = DISPLAY_DURATION;
    }

    public LevelUpScreen(Player Player) {
        super(TITLE);
        this.Context = null;
        this.player = Player;
        this.ticksRemaining = DISPLAY_DURATION;
    }

    public LevelUpScreen(UseOnContext pContext) {
        super(TITLE);
        this.Context = pContext;
        this.ticksRemaining = DISPLAY_DURATION;
    }

    @Override
    public void tick() {
        super.tick();
        ticksRemaining--;

        if (ticksRemaining <= 0) {
            onClose();
        }
    }

    @Override
    protected void init() {
        super.init();

        this.width = this.minecraft.getWindow().getGuiScaledWidth();
        this.height = this.minecraft.getWindow().getGuiScaledHeight();
        this.imageWidth = this.width;
        this.imageHeight = this.height;

        this.leftPos = 0;
        this.topPos = 0;
    }

    @Override
    public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        super.renderBackground(graphics);

        RenderSystem.setShaderTexture(0, LEVEL_UP_GIF);
        graphics.blit(LEVEL_UP_GIF, 0, 0, 0, 0, this.imageWidth, this.imageHeight, this.width, this.height);
        playLevelUpSound();
        super.render(graphics, mouseX, mouseY, partialTicks);
    }

    private void playLevelUpSound() {
        if (levelUpSoundInstance == null
                || !Minecraft.getInstance().getSoundManager().isActive(levelUpSoundInstance)) {

            levelUpSoundInstance = SimpleSoundInstance.forUI(
                    ModSounds.LEVEL_UP_SOUND.get(), 1f, 1f);
            Minecraft.getInstance()
                    .getSoundManager()
                    .play(levelUpSoundInstance);
        }
    }


    @Override
    public boolean isPauseScreen() {
        return false; // Don't pause the game when this screen is open
    }

    @Override
    public void onClose() {
        Minecraft.getInstance().setScreen(null); // Close the screen
    }

}

