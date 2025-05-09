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
import ros.eagleoffire.rosevolution.sound.ModSounds;

public class LevelUpScreen extends Screen {
    private static final Component TITLE =
            Component.translatable("gui." + ROSEvolution.MODID + ".example_block_screen");

    private static final ResourceLocation LEVEL_UP_GIF = new ResourceLocation(ROSEvolution.MODID, "textures/gui/evosys_levelup.png");

    private final int imageWidth, imageHeight;
    private final Object Context;
    private int ticksRemaining;
    private int leftPos, topPos;
    private Player player;
    private static final int DISPLAY_DURATION = 75; // Duration in ticks (5 seconds, 20 ticks per second)
    private boolean soundPlayed = false; // Add this flag


    public LevelUpScreen() {
        super(TITLE);
        this.Context = null;
        this.player = null;
        this.imageWidth = 1920;
        this.imageHeight = 1920;
        this.ticksRemaining = DISPLAY_DURATION;
    }

    public LevelUpScreen(Player Player) {
        super(TITLE);
        this.Context = null;
        this.player = Player;
        this.imageWidth = 1920;
        this.imageHeight = 1920;
        this.ticksRemaining = DISPLAY_DURATION;
    }

    public LevelUpScreen(UseOnContext pContext) {
        super(TITLE);

        this.Context = pContext;
        this.imageWidth = 1920;
        this.imageHeight = 1920;
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

        this.leftPos = (this.width - this.imageWidth) / 2;
        this.topPos = (this.height - this.imageHeight) / 2;
    }

    @Override
    public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
                RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.blendFuncSeparate(
                GlStateManager.SourceFactor.SRC_ALPHA,
                GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA,
                GlStateManager.SourceFactor.ONE,
                GlStateManager.DestFactor.ZERO
        );
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        int screenWidth = this.width;
        int screenHeight = this.height;
        int gifWidth = this.width; // Example size, adjust as needed
        int gifHeight = 128;

        RenderSystem.setShaderTexture(0, LEVEL_UP_GIF);
        graphics.blit(LEVEL_UP_GIF, (screenWidth - gifWidth) / 2, (screenHeight - gifHeight) / 2, 0, 0, gifWidth, gifHeight, gifWidth, gifHeight);
        // Only play the sound once
        if (!soundPlayed) {
            Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(ModSounds.LEVEL_UP_SOUND.get(), 1f, 1f));
            soundPlayed = true;
        }

        RenderSystem.depthMask(true);
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
        RenderSystem.disableBlend();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        super.render(graphics, mouseX, mouseY, partialTicks);
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

