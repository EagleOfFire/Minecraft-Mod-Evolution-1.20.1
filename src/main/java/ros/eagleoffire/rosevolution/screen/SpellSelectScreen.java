package ros.eagleoffire.rosevolution.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import ros.eagleoffire.rosevolution.ROSEvolution;
import ros.eagleoffire.rosevolution.client.ClientHooks;
import ros.eagleoffire.rosevolution.config.ModClientConfigs;

import java.util.ArrayList;
import java.util.List;


public class SpellSelectScreen extends Screen {
    private static final Component TITLE =
            Component.translatable("gui." + ROSEvolution.MODID + ".spell_select_screen");

    private static final ResourceLocation TEXTURE = new ResourceLocation(ROSEvolution.MODID, "textures/gui/spell_select_screen.png");

    private final LocalPlayer player;
    private final int imageWidht, imageHeight;
    private int leftPos, topPos;

    private DynamicTexture cachedTexture;
    ;
    private ResourceLocation cachedTextureLocation;

    public SpellSelectScreen(LocalPlayer player) {
        super(TITLE);
        this.player = player;
        this.imageWidht = 176;
        this.imageHeight = 166;
    }

    @Override
    protected void init() {
        super.init();

        this.leftPos = (this.width - this.imageWidht) / 2;
        this.topPos = (this.height - this.imageHeight) / 2;

        String textureName = "button.png";
        cachedTexture = ModClientConfigs.getButtonImages().get(textureName);

        if (cachedTexture != null) {
            cachedTextureLocation = new ResourceLocation("modid", "dynamic_textures/" + textureName);
            Minecraft.getInstance().getTextureManager().register(cachedTextureLocation, cachedTexture);
        }
        loadButtonsFromConfig();

    }

    private void loadButtonsFromConfig() {
        List<String> buttonTexts = ModClientConfigs.SPELLS_NAMES.get();
        List<String> buttonCommands = ModClientConfigs.SPELLS_COMMAND.get();

        for (int i = 0; i < buttonTexts.size() && i < buttonCommands.size(); i++) {
            int buttonX = this.leftPos + 10;
            int buttonY = this.topPos + 30 + (i * 20);
            String text = buttonTexts.get(i);
            String command = buttonCommands.get(i);

            if (cachedTextureLocation != null) {
                addRenderableWidget(new ImageButton(
                        buttonX, buttonY + (i * 20), // X and Y position
                        20, 20, // Width and Height
                        0, 0, // Texture X and Y (start of image)
                        20, // Amount to shift Y on click
                        cachedTextureLocation, // Texture location
                        20, 40, // Total texture size (width, height of full image)
                        (button) -> {
                            if (this.minecraft != null && this.minecraft.player != null) {
                                String playerName = this.minecraft.player.getGameProfile().getName();
                                String processedCommand = command.replace("@s", playerName);
                                //executeCommand(processedCommand);
                                DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openKatonJutsuScreen(minecraft.player));
                            }
                        }));
            }
        }
    }

    private void executeCommand(String command) {
        if (this.minecraft != null && this.minecraft.player != null && !command.isEmpty()) {
            this.minecraft.player.connection.sendCommand(command);
        }
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        renderBackground(graphics);
        graphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidht, this.imageHeight);
        super.render(graphics, mouseX, mouseY, partialTicks);

        graphics.drawString(this.font, TITLE, this.leftPos + 8, this.topPos + 8, 0x404040, false);
    }


    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
