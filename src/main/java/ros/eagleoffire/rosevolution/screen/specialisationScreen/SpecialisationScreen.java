package ros.eagleoffire.rosevolution.screen.specialisationScreen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import ros.eagleoffire.rosevolution.ROSEvolution;
import ros.eagleoffire.rosevolution.client.ClientHooks;

public class SpecialisationScreen extends Screen {

     private static final Component TITLE =
            Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

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
        int posCursor = getSectorID(mouseX, this.width);
        if (posCursor == 1) {
            graphics.blit(TexturesScreen.SPECIALISATION_SCREEN_NINJUTSU_SELECTED, this.leftPos, this.topPos, 0, 0, this.width, this.height,this.width,this.height);
        } else if (posCursor == 2) {
            graphics.blit(TexturesScreen.SPECIALISATION_SCREEN_MEDICAL_SELECTED, this.leftPos, this.topPos, 0, 0, this.width, this.height,this.width,this.height);
        } else if (posCursor == 3) {
            graphics.blit(TexturesScreen.SPECIALISATION_SCREEN_GENJUTSU_SELECTED, this.leftPos, this.topPos, 0, 0, this.width, this.height,this.width,this.height);
        } else if (posCursor == 4) {
            graphics.blit(TexturesScreen.SPECIALISATION_SCREEN_KENJUTSU_SELECTED, this.leftPos, this.topPos, 0, 0, this.width, this.height,this.width,this.height);
        } else if (posCursor == 5) {
            graphics.blit(TexturesScreen.SPECIALISATION_SCREEN_TAIJUTSU_SELECTED, this.leftPos, this.topPos, 0, 0, this.width, this.height,this.width,this.height);
        } else if (posCursor == 6) {
            graphics.blit(TexturesScreen.SPECIALISATION_SCREEN_SPE_CLAN_SELECTED, this.leftPos, this.topPos, 0, 0, this.width, this.height,this.width,this.height);
        } else {
            graphics.blit(TexturesScreen.SPECIALISATION_SCREEN, this.leftPos, this.topPos, 0, 0, this.width, this.height,this.width,this.height);
        }
        super.render(graphics, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int pButton) {
        int posCursor = getSectorID(mouseX, this.width);
        if (posCursor == 1) {
            Minecraft.getInstance().setScreen(null);
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementSelectScreen(player));
        } else if (posCursor == 2) {
            Minecraft.getInstance().setScreen(null);
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openMedicalScreen(player,"D"));
        } else if (posCursor == 3) {
            Minecraft.getInstance().setScreen(null);
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openGenjutsuScreen(player,"D"));
        } else if (posCursor == 4) {
            Minecraft.getInstance().setScreen(null);
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openKenjutsuScreen(player,"D"));
        } else if (posCursor == 5) {
            Minecraft.getInstance().setScreen(null);
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openTaijutsuScreen(player,"D"));
        } else if (posCursor == 6) {
            Minecraft.getInstance().setScreen(null);
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openClanSpecialisationScreen(player,"D"));
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
