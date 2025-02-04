package ros.eagleoffire.rosevolution.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import ros.eagleoffire.rosevolution.ROSEvolution;


public class SpellSelectScreen extends Screen {
    private static final Component TITLE =
            Component.translatable("gui." + ROSEvolution.MODID + ".spell_select_screen");
    private static final Component EXAMPLE_BUTTON =
            Component.translatable("gui." + ROSEvolution.MODID + ".spell_select_screen.button.example_button");

    private static final ResourceLocation TEXTURE = new ResourceLocation(ROSEvolution.MODID, "textures/gui/spell_select_screen.png");

    private final LocalPlayer player;
    private final int imageWidht, imageHeight;
    private int leftPos,topPos;
    private Button button;

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

        if(this.minecraft == null) return;
        Level level = this.minecraft.level;
        if(level == null) return;

        this.button = addRenderableWidget(
                //ImageButton
                Button.builder(
                        EXAMPLE_BUTTON,
                        this::handleExampleButton)
                        .bounds(this.leftPos + 8, this.topPos + 20, 80,20)
                        .tooltip(Tooltip.create(EXAMPLE_BUTTON))
                        .build());
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        renderBackground(graphics);
        graphics.blit(TEXTURE, this.leftPos, this.topPos, 0,0, this.imageWidht, this.imageHeight);
        super.render(graphics, mouseX, mouseY, partialTicks);

        graphics.drawString(this.font,
                TITLE,
                this.leftPos + 8,
                this.topPos + 8,
                0x404040,
                false);

        graphics.drawString(this.font,
                "Player Name: " + this.player.getScoreboardName(),
                this.leftPos + 8,
                this.topPos + 45,
                0xFF0000,
                false);

        //TODO draw the background
        //TODO draw nodes in a tree-like structure similar to advancements
    }

    private void handleExampleButton(Button button){}

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
