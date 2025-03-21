package ros.eagleoffire.rosevolution.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import ros.eagleoffire.rosevolution.ROSEvolution;

public class SpecialisationScreen extends Screen {

     private static final Component TITLE =
            Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

    private static final ResourceLocation SPECIALISATION_SCREEN =
            new ResourceLocation(ROSEvolution.MODID, "textures/gui/screen/specialisation_screen.png");
    private final int imageWidth;
    private final int imageHeight;
    private int leftPos;
    private int topPos;
    private LocalPlayer player;

    public SpecialisationScreen(LocalPlayer player) {
        super(TITLE);
        this.imageWidth = 1272;
        this.imageHeight = 701;
        this.player = player;
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        super.render(graphics, mouseX, mouseY, partialTicks);
        renderBackground(graphics);
        graphics.blit(SPECIALISATION_SCREEN, this.leftPos, this.topPos, 0, 0, this.width, this.height,this.width,this.height);
        super.render(graphics, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int pButton) {
        int posCursor = getSectorID(mouseX, this.width);
        if (posCursor == 1) {
            Minecraft.getInstance().setScreen(null);
            this.player.sendSystemMessage(Component.literal("You choose elemental ninjutsu"));
            //TODO add next screen
        } else if (posCursor == 2) {
            Minecraft.getInstance().setScreen(null);
            this.player.sendSystemMessage(Component.literal("You choose medical ninjutsu"));
            //TODO add next screen
        } else if (posCursor == 3) {
            Minecraft.getInstance().setScreen(null);
            this.player.sendSystemMessage(Component.literal("You choose genjutsu"));
            //TODO add next screen
        } else if (posCursor == 4) {
            Minecraft.getInstance().setScreen(null);
            this.player.sendSystemMessage(Component.literal("You choose kenjutsu"));
            //TODO add next screen
        } else if (posCursor == 5) {
            Minecraft.getInstance().setScreen(null);
            this.player.sendSystemMessage(Component.literal("You choose taijutsu"));
            //TODO add next screen
        } else if (posCursor == 6) {
            Minecraft.getInstance().setScreen(null);
            this.player.sendSystemMessage(Component.literal("You choose your clan specialisation"));
            //TODO add next screen
        } else {
            Minecraft.getInstance().setScreen(null);
            this.player.sendSystemMessage(Component.literal("You didn't choose :("));
            //TODO add next screen
        }
        return super.mouseClicked(mouseX, mouseY, pButton);
    }

    public static int getSectorID(double mouseX,int width) {
        if (mouseX < ((double) width / 6)) {
            return 1;
        } else if (((double) width / 6) < mouseX && mouseX < ((double) width / 6)*2) {
            return 2;
        } else if (((double) width / 6)*2 < mouseX && mouseX < ((double) width / 6)*3) {
            return 3;
        } else if (((double) width / 6)*3 < mouseX && mouseX < ((double) width / 6)*4) {
            return 4;
        } else if (((double) width / 6)*4 < mouseX && mouseX < ((double) width / 6)*5) {
            return 5;
        } else {
            return 6;
        }
    }


    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
