package ros.eagleoffire.rosevolution.screen.specialisationScreen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import org.jetbrains.annotations.NotNull;
import ros.eagleoffire.rosevolution.ROSEvolution;
import ros.eagleoffire.rosevolution.client.ClientHooks;

public class ClanSpecialisationScreen {
    private static final int leftPos = 0;
    private static final int topPos = 0;

    private static int rightButtonleftPos;
    private static int rightButtonTopPos;
    private static int rightButtonHeight;
    private static int rightButtonWidth;
    private static int leftButtonleftPos;
    private static int leftButtonTopPos;
    private static int leftButtonHeight;
    private static int leftButtonWidth;

    private static LocalPlayer player;
    private static Button buttonRight;
    private static Button buttonLeft;
    private static String element = "ClanSpecialisation";

    private static void init(int width, int height) {
        ClanSpecialisationScreen.rightButtonWidth = 20;
        ClanSpecialisationScreen.rightButtonHeight = (int) (1.56 * ClanSpecialisationScreen.rightButtonWidth);
        ClanSpecialisationScreen.rightButtonleftPos = width - ClanSpecialisationScreen.rightButtonWidth;
        ClanSpecialisationScreen.rightButtonTopPos = (height / 2) - (ClanSpecialisationScreen.rightButtonHeight / 2);

        ClanSpecialisationScreen.leftButtonWidth = 20;
        ClanSpecialisationScreen.leftButtonHeight = (int) (1.56 * ClanSpecialisationScreen.leftButtonWidth);
        ClanSpecialisationScreen.leftButtonleftPos = 0;
        ClanSpecialisationScreen.leftButtonTopPos = (height / 2) - (ClanSpecialisationScreen.leftButtonHeight / 2);
    }


    public static class ClanSpecialisationRangSScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public ClanSpecialisationRangSScreen(LocalPlayer player) {
            super(TITLE);

            ClanSpecialisationScreen.player = player;
        }

        @Override
        protected void init() {
            super.init();
            ClanSpecialisationScreen.init(this.width, this.height);

            ClanSpecialisationScreen.buttonLeft = addRenderableWidget(new ImageButton(
                    ClanSpecialisationScreen.leftButtonleftPos, ClanSpecialisationScreen.leftButtonTopPos, // X and Y position
                    ClanSpecialisationScreen.leftButtonWidth, ClanSpecialisationScreen.leftButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.LEFT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    ClanSpecialisationScreen.leftButtonWidth, ClanSpecialisationScreen.leftButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openClanSpecialisationScreen(player,  "A"));
                        }
                    }));
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_S_SCREEN, ClanSpecialisationScreen.leftPos, ClanSpecialisationScreen.topPos, 0, 0, this.width, this.height, this.width, this.height);

            // Render buttons and other UI first
            super.render(graphics, mouseX, mouseY, partialTicks);

            // Ensure the hover texture is drawn above everything
            if (ClanSpecialisationScreen.buttonLeft.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.LEFT_BUTTON_TEXTURE_HOVER,
                        ClanSpecialisationScreen.leftButtonleftPos, ClanSpecialisationScreen.leftButtonTopPos,
                        0, 0,
                        ClanSpecialisationScreen.leftButtonWidth, ClanSpecialisationScreen.leftButtonHeight,
                        ClanSpecialisationScreen.leftButtonWidth, ClanSpecialisationScreen.leftButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }

    public static class ClanSpecialisationRangAScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public ClanSpecialisationRangAScreen(LocalPlayer player) {
            super(TITLE);

            ClanSpecialisationScreen.player = player;
        }

        @Override
        protected void init() {
            super.init();
            ClanSpecialisationScreen.init(this.width, this.height);

            ClanSpecialisationScreen.buttonRight = addRenderableWidget(new ImageButton(
                    ClanSpecialisationScreen.rightButtonleftPos, ClanSpecialisationScreen.rightButtonTopPos, // X and Y position
                    ClanSpecialisationScreen.rightButtonWidth, ClanSpecialisationScreen.rightButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.RIGHT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    ClanSpecialisationScreen.rightButtonWidth, ClanSpecialisationScreen.rightButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openClanSpecialisationScreen(player,  "S"));
                        }
                    }));

            ClanSpecialisationScreen.buttonLeft = addRenderableWidget(new ImageButton(
                    ClanSpecialisationScreen.leftButtonleftPos, ClanSpecialisationScreen.leftButtonTopPos, // X and Y position
                    ClanSpecialisationScreen.leftButtonWidth, ClanSpecialisationScreen.leftButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.LEFT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    ClanSpecialisationScreen.leftButtonWidth, ClanSpecialisationScreen.leftButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openClanSpecialisationScreen(player,  "B"));
                        }
                    }));
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_A_SCREEN, ClanSpecialisationScreen.leftPos, ClanSpecialisationScreen.topPos, 0, 0, this.width, this.height, this.width, this.height);

            // Render buttons and other UI first
            super.render(graphics, mouseX, mouseY, partialTicks);

            // Ensure the hover texture is drawn above everything
            if (ClanSpecialisationScreen.buttonRight.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.RIGHT_BUTTON_TEXTURE_HOVER,
                        ClanSpecialisationScreen.rightButtonleftPos, ClanSpecialisationScreen.rightButtonTopPos,
                        0, 0,
                        ClanSpecialisationScreen.rightButtonWidth, ClanSpecialisationScreen.rightButtonHeight,
                        ClanSpecialisationScreen.rightButtonWidth, ClanSpecialisationScreen.rightButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
            if (ClanSpecialisationScreen.buttonLeft.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.LEFT_BUTTON_TEXTURE_HOVER,
                        ClanSpecialisationScreen.leftButtonleftPos, ClanSpecialisationScreen.leftButtonTopPos,
                        0, 0,
                        ClanSpecialisationScreen.leftButtonWidth, ClanSpecialisationScreen.leftButtonHeight,
                        ClanSpecialisationScreen.leftButtonWidth, ClanSpecialisationScreen.leftButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }

    public static class ClanSpecialisationRangBScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public ClanSpecialisationRangBScreen(LocalPlayer player) {
            super(TITLE);

            ClanSpecialisationScreen.player = player;
        }

        @Override
        protected void init() {
            super.init();
            ClanSpecialisationScreen.init(this.width, this.height);

            ClanSpecialisationScreen.buttonRight = addRenderableWidget(new ImageButton(
                    ClanSpecialisationScreen.rightButtonleftPos, ClanSpecialisationScreen.rightButtonTopPos, // X and Y position
                    ClanSpecialisationScreen.rightButtonWidth, ClanSpecialisationScreen.rightButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.RIGHT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    ClanSpecialisationScreen.rightButtonWidth, ClanSpecialisationScreen.rightButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openClanSpecialisationScreen(player,  "A"));
                        }
                    }));

            ClanSpecialisationScreen.buttonLeft = addRenderableWidget(new ImageButton(
                    ClanSpecialisationScreen.leftButtonleftPos, ClanSpecialisationScreen.leftButtonTopPos, // X and Y position
                    ClanSpecialisationScreen.leftButtonWidth, ClanSpecialisationScreen.leftButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.LEFT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    ClanSpecialisationScreen.leftButtonWidth, ClanSpecialisationScreen.leftButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openClanSpecialisationScreen(player,  "C"));
                        }
                    }));
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_B_SCREEN, ClanSpecialisationScreen.leftPos, ClanSpecialisationScreen.topPos, 0, 0, this.width, this.height, this.width, this.height);

            // Render buttons and other UI first
            super.render(graphics, mouseX, mouseY, partialTicks);

            // Ensure the hover texture is drawn above everything
            if (ClanSpecialisationScreen.buttonRight.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.RIGHT_BUTTON_TEXTURE_HOVER,
                        ClanSpecialisationScreen.rightButtonleftPos, ClanSpecialisationScreen.rightButtonTopPos,
                        0, 0,
                        ClanSpecialisationScreen.rightButtonWidth, ClanSpecialisationScreen.rightButtonHeight,
                        ClanSpecialisationScreen.rightButtonWidth, ClanSpecialisationScreen.rightButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
            if (ClanSpecialisationScreen.buttonLeft.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.LEFT_BUTTON_TEXTURE_HOVER,
                        ClanSpecialisationScreen.leftButtonleftPos, ClanSpecialisationScreen.leftButtonTopPos,
                        0, 0,
                        ClanSpecialisationScreen.leftButtonWidth, ClanSpecialisationScreen.leftButtonHeight,
                        ClanSpecialisationScreen.leftButtonWidth, ClanSpecialisationScreen.leftButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }

    public static class ClanSpecialisationRangCScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public ClanSpecialisationRangCScreen(LocalPlayer player) {
            super(TITLE);

            ClanSpecialisationScreen.player = player;
        }

        @Override
        protected void init() {
            super.init();
            ClanSpecialisationScreen.init(this.width, this.height);

            ClanSpecialisationScreen.buttonRight = addRenderableWidget(new ImageButton(
                    ClanSpecialisationScreen.rightButtonleftPos, ClanSpecialisationScreen.rightButtonTopPos, // X and Y position
                    ClanSpecialisationScreen.rightButtonWidth, ClanSpecialisationScreen.rightButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.RIGHT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    ClanSpecialisationScreen.rightButtonWidth, ClanSpecialisationScreen.rightButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openClanSpecialisationScreen(player,  "B"));
                        }
                    }));

            ClanSpecialisationScreen.buttonLeft = addRenderableWidget(new ImageButton(
                    ClanSpecialisationScreen.leftButtonleftPos, ClanSpecialisationScreen.leftButtonTopPos, // X and Y position
                    ClanSpecialisationScreen.leftButtonWidth, ClanSpecialisationScreen.leftButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.LEFT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    ClanSpecialisationScreen.leftButtonWidth, ClanSpecialisationScreen.leftButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openClanSpecialisationScreen(player,  "D"));
                        }
                    }));
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_C_SCREEN, ClanSpecialisationScreen.leftPos, ClanSpecialisationScreen.topPos, 0, 0, this.width, this.height, this.width, this.height);

            // Render buttons and other UI first
            super.render(graphics, mouseX, mouseY, partialTicks);

            // Ensure the hover texture is drawn above everything
            if (ClanSpecialisationScreen.buttonRight.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.RIGHT_BUTTON_TEXTURE_HOVER,
                        ClanSpecialisationScreen.rightButtonleftPos, ClanSpecialisationScreen.rightButtonTopPos,
                        0, 0,
                        ClanSpecialisationScreen.rightButtonWidth, ClanSpecialisationScreen.rightButtonHeight,
                        ClanSpecialisationScreen.rightButtonWidth, ClanSpecialisationScreen.rightButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
            if (ClanSpecialisationScreen.buttonLeft.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.LEFT_BUTTON_TEXTURE_HOVER,
                        ClanSpecialisationScreen.leftButtonleftPos, ClanSpecialisationScreen.leftButtonTopPos,
                        0, 0,
                        ClanSpecialisationScreen.leftButtonWidth, ClanSpecialisationScreen.leftButtonHeight,
                        ClanSpecialisationScreen.leftButtonWidth, ClanSpecialisationScreen.leftButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }

    public static class ClanSpecialisationRangDScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public ClanSpecialisationRangDScreen(LocalPlayer player) {
            super(TITLE);

            ClanSpecialisationScreen.player = player;
        }

        @Override
        protected void init() {
            super.init();
            ClanSpecialisationScreen.init(this.width, this.height);

            ClanSpecialisationScreen.buttonRight = addRenderableWidget(new ImageButton(
                    ClanSpecialisationScreen.rightButtonleftPos, ClanSpecialisationScreen.rightButtonTopPos, // X and Y position
                    ClanSpecialisationScreen.rightButtonWidth, ClanSpecialisationScreen.rightButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.RIGHT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    ClanSpecialisationScreen.rightButtonWidth, ClanSpecialisationScreen.rightButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openClanSpecialisationScreen(player,  "C"));
                        }
                    }));
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_D_SCREEN, ClanSpecialisationScreen.leftPos, ClanSpecialisationScreen.topPos, 0, 0, this.width, this.height, this.width, this.height);

            // Render buttons and other UI first
            super.render(graphics, mouseX, mouseY, partialTicks);

            // Ensure the hover texture is drawn above everything
            if (ClanSpecialisationScreen.buttonRight.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.RIGHT_BUTTON_TEXTURE_HOVER,
                        ClanSpecialisationScreen.rightButtonleftPos, ClanSpecialisationScreen.rightButtonTopPos,
                        0, 0,
                        ClanSpecialisationScreen.rightButtonWidth, ClanSpecialisationScreen.rightButtonHeight,
                        ClanSpecialisationScreen.rightButtonWidth, ClanSpecialisationScreen.rightButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }
}
