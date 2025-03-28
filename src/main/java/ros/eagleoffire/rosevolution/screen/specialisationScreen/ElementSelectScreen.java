package ros.eagleoffire.rosevolution.screen.specialisationScreen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import ros.eagleoffire.rosevolution.ROSEvolution;
import ros.eagleoffire.rosevolution.client.ClientHooks;

public class ElementSelectScreen extends Screen {

     private static final Component TITLE =
            Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

    private static final ResourceLocation ELEMENT_BACKGROUND =
        new ResourceLocation(ROSEvolution.MODID, "textures/gui/screen/ninjutsu_choix_element/evosys_background_elements.png");
    private static final ResourceLocation ELEMENT_KATON =
        new ResourceLocation(ROSEvolution.MODID, "textures/gui/screen/ninjutsu_choix_element/evosys_elements_katon_selected.png");
    private static final ResourceLocation ELEMENT_SUITON =
        new ResourceLocation(ROSEvolution.MODID, "textures/gui/screen/ninjutsu_choix_element/evosys_elements_suiton_selected.png");
    private static final ResourceLocation ELEMENT_FUTON =
        new ResourceLocation(ROSEvolution.MODID, "textures/gui/screen/ninjutsu_choix_element/evosys_elements_futon_selected.png");
    private static final ResourceLocation ELEMENT_DOTON =
        new ResourceLocation(ROSEvolution.MODID, "textures/gui/screen/ninjutsu_choix_element/evosys_elements_doton_selected.png");
    private static final ResourceLocation ELEMENT_RAITON =
        new ResourceLocation(ROSEvolution.MODID, "textures/gui/screen/ninjutsu_choix_element/evosys_elements_raiton_selected.png");

    private final int imageWidth;
    private final int imageHeight;
    private int leftPos;
    private int topPos;
    private LocalPlayer player;

    public ElementSelectScreen(LocalPlayer player) {
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
        int posCursor = getSectorID(mouseX,mouseY, this.width,this.height);
        if (posCursor == 1) {
            graphics.blit(ELEMENT_KATON, this.leftPos, this.topPos, 0, 0, this.width, this.height,this.width,this.height);
        } else if (posCursor == 2) {
            graphics.blit(ELEMENT_SUITON, this.leftPos, this.topPos, 0, 0, this.width, this.height,this.width,this.height);
        } else if (posCursor == 3) {
            graphics.blit(ELEMENT_FUTON, this.leftPos, this.topPos, 0, 0, this.width, this.height,this.width,this.height);
        } else if (posCursor == 4) {
            graphics.blit(ELEMENT_DOTON, this.leftPos, this.topPos, 0, 0, this.width, this.height,this.width,this.height);
        } else if (posCursor == 5) {
            graphics.blit(ELEMENT_RAITON, this.leftPos, this.topPos, 0, 0, this.width, this.height,this.width,this.height);
        } else {
            graphics.blit(ELEMENT_BACKGROUND, this.leftPos, this.topPos, 0, 0, this.width, this.height,this.width,this.height);
        }
        super.render(graphics, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int pButton) {
        int posCursor = getSectorID(mouseX,mouseY, this.width,this.height);
        if (posCursor == 1) {
            Minecraft.getInstance().setScreen(null);
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, "Katon", "D"));
        } else if (posCursor == 2) {
            Minecraft.getInstance().setScreen(null);
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, "Suiton", "D"));
        } else if (posCursor == 3) {
            Minecraft.getInstance().setScreen(null);
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, "Futon", "D"));
        } else if (posCursor == 4) {
            Minecraft.getInstance().setScreen(null);
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, "Doton", "D"));
        } else if (posCursor == 5) {
            Minecraft.getInstance().setScreen(null);
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, "Raiton", "D"));
        }
        return super.mouseClicked(mouseX, mouseY, pButton);
    }

    public static int getSectorID(double mouseX,double mouseY,int width, int height) {
        if (mouseY < ((double) height / 2) || mouseY > ((double) height / 4)*3) {
            return 0;
        }
        if (mouseX < ((double) width / 4)) {
            return 1;
        } else if (((double) width / 4) < mouseX && mouseX < ((double) width / 5)*2) {
            return 2;
        } else if (((double) width / 5)*2 < mouseX && mouseX < ((double) width / 5)*3) {
            return 3;
        } else if (((double) width / 5)*3 < mouseX && mouseX < ((double) width / 4)*3) {
            return 4;
        } else {
            return 5;
        }
    }


    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
