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

public class GenjutsuScreens {
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
    private static String element = "Genjutsu";

    private static void init(int width, int height) {
        GenjutsuScreens.rightButtonWidth = 20;
        GenjutsuScreens.rightButtonHeight = (int) (1.56 * GenjutsuScreens.rightButtonWidth);
        GenjutsuScreens.rightButtonleftPos = width - GenjutsuScreens.rightButtonWidth;
        GenjutsuScreens.rightButtonTopPos = (height / 2) - (GenjutsuScreens.rightButtonHeight / 2);

        GenjutsuScreens.leftButtonWidth = 20;
        GenjutsuScreens.leftButtonHeight = (int) (1.56 * GenjutsuScreens.leftButtonWidth);
        GenjutsuScreens.leftButtonleftPos = 0;
        GenjutsuScreens.leftButtonTopPos = (height / 2) - (GenjutsuScreens.leftButtonHeight / 2);
    }


    public static class GenjutsuRangSScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public GenjutsuRangSScreen(LocalPlayer player) {
            super(TITLE);

            GenjutsuScreens.player = player;
        }

        @Override
        protected void init() {
            super.init();
            GenjutsuScreens.init(this.width, this.height);

            GenjutsuScreens.buttonLeft = addRenderableWidget(new ImageButton(
                    GenjutsuScreens.leftButtonleftPos, GenjutsuScreens.leftButtonTopPos, // X and Y position
                    GenjutsuScreens.leftButtonWidth, GenjutsuScreens.leftButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.LEFT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    GenjutsuScreens.leftButtonWidth, GenjutsuScreens.leftButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openGenjutsuScreen(player, "A"));
                        }
                    }));
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_S_SCREEN, GenjutsuScreens.leftPos, GenjutsuScreens.topPos, 0, 0, this.width, this.height, this.width, this.height);

            // Render buttons and other UI first
            super.render(graphics, mouseX, mouseY, partialTicks);

            // Ensure the hover texture is drawn above everything
            if (GenjutsuScreens.buttonLeft.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.LEFT_BUTTON_TEXTURE_HOVER,
                        GenjutsuScreens.leftButtonleftPos, GenjutsuScreens.leftButtonTopPos,
                        0, 0,
                        GenjutsuScreens.leftButtonWidth, GenjutsuScreens.leftButtonHeight,
                        GenjutsuScreens.leftButtonWidth, GenjutsuScreens.leftButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }

    public static class GenjutsuRangAScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public GenjutsuRangAScreen(LocalPlayer player) {
            super(TITLE);

            GenjutsuScreens.player = player;
        }

        @Override
        protected void init() {
            super.init();
            GenjutsuScreens.init(this.width, this.height);

            GenjutsuScreens.buttonRight = addRenderableWidget(new ImageButton(
                    GenjutsuScreens.rightButtonleftPos, GenjutsuScreens.rightButtonTopPos, // X and Y position
                    GenjutsuScreens.rightButtonWidth, GenjutsuScreens.rightButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.RIGHT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    GenjutsuScreens.rightButtonWidth, GenjutsuScreens.rightButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openGenjutsuScreen(player, "S"));
                        }
                    }));

            GenjutsuScreens.buttonLeft = addRenderableWidget(new ImageButton(
                    GenjutsuScreens.leftButtonleftPos, GenjutsuScreens.leftButtonTopPos, // X and Y position
                    GenjutsuScreens.leftButtonWidth, GenjutsuScreens.leftButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.LEFT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    GenjutsuScreens.leftButtonWidth, GenjutsuScreens.leftButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openGenjutsuScreen(player, "B"));
                        }
                    }));
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_A_SCREEN, GenjutsuScreens.leftPos, GenjutsuScreens.topPos, 0, 0, this.width, this.height, this.width, this.height);

            // Render buttons and other UI first
            super.render(graphics, mouseX, mouseY, partialTicks);

            // Ensure the hover texture is drawn above everything
            if (GenjutsuScreens.buttonRight.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.RIGHT_BUTTON_TEXTURE_HOVER,
                        GenjutsuScreens.rightButtonleftPos, GenjutsuScreens.rightButtonTopPos,
                        0, 0,
                        GenjutsuScreens.rightButtonWidth, GenjutsuScreens.rightButtonHeight,
                        GenjutsuScreens.rightButtonWidth, GenjutsuScreens.rightButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
            if (GenjutsuScreens.buttonLeft.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.LEFT_BUTTON_TEXTURE_HOVER,
                        GenjutsuScreens.leftButtonleftPos, GenjutsuScreens.leftButtonTopPos,
                        0, 0,
                        GenjutsuScreens.leftButtonWidth, GenjutsuScreens.leftButtonHeight,
                        GenjutsuScreens.leftButtonWidth, GenjutsuScreens.leftButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }

    public static class GenjutsuRangBScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public GenjutsuRangBScreen(LocalPlayer player) {
            super(TITLE);

            GenjutsuScreens.player = player;
        }

        @Override
        protected void init() {
            super.init();
            GenjutsuScreens.init(this.width, this.height);

            GenjutsuScreens.buttonRight = addRenderableWidget(new ImageButton(
                    GenjutsuScreens.rightButtonleftPos, GenjutsuScreens.rightButtonTopPos, // X and Y position
                    GenjutsuScreens.rightButtonWidth, GenjutsuScreens.rightButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.RIGHT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    GenjutsuScreens.rightButtonWidth, GenjutsuScreens.rightButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openGenjutsuScreen(player, "A"));
                        }
                    }));

            GenjutsuScreens.buttonLeft = addRenderableWidget(new ImageButton(
                    GenjutsuScreens.leftButtonleftPos, GenjutsuScreens.leftButtonTopPos, // X and Y position
                    GenjutsuScreens.leftButtonWidth, GenjutsuScreens.leftButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.LEFT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    GenjutsuScreens.leftButtonWidth, GenjutsuScreens.leftButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openGenjutsuScreen(player, "C"));
                        }
                    }));
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_B_SCREEN, GenjutsuScreens.leftPos, GenjutsuScreens.topPos, 0, 0, this.width, this.height, this.width, this.height);

            // Render buttons and other UI first
            super.render(graphics, mouseX, mouseY, partialTicks);

            // Ensure the hover texture is drawn above everything
            if (GenjutsuScreens.buttonRight.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.RIGHT_BUTTON_TEXTURE_HOVER,
                        GenjutsuScreens.rightButtonleftPos, GenjutsuScreens.rightButtonTopPos,
                        0, 0,
                        GenjutsuScreens.rightButtonWidth, GenjutsuScreens.rightButtonHeight,
                        GenjutsuScreens.rightButtonWidth, GenjutsuScreens.rightButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
            if (GenjutsuScreens.buttonLeft.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.LEFT_BUTTON_TEXTURE_HOVER,
                        GenjutsuScreens.leftButtonleftPos, GenjutsuScreens.leftButtonTopPos,
                        0, 0,
                        GenjutsuScreens.leftButtonWidth, GenjutsuScreens.leftButtonHeight,
                        GenjutsuScreens.leftButtonWidth, GenjutsuScreens.leftButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }

    public static class GenjutsuRangCScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public GenjutsuRangCScreen(LocalPlayer player) {
            super(TITLE);

            GenjutsuScreens.player = player;
        }

        @Override
        protected void init() {
            super.init();
            GenjutsuScreens.init(this.width, this.height);

            GenjutsuScreens.buttonRight = addRenderableWidget(new ImageButton(
                    GenjutsuScreens.rightButtonleftPos, GenjutsuScreens.rightButtonTopPos, // X and Y position
                    GenjutsuScreens.rightButtonWidth, GenjutsuScreens.rightButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.RIGHT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    GenjutsuScreens.rightButtonWidth, GenjutsuScreens.rightButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openGenjutsuScreen(player, "B"));
                        }
                    }));

            GenjutsuScreens.buttonLeft = addRenderableWidget(new ImageButton(
                    GenjutsuScreens.leftButtonleftPos, GenjutsuScreens.leftButtonTopPos, // X and Y position
                    GenjutsuScreens.leftButtonWidth, GenjutsuScreens.leftButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.LEFT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    GenjutsuScreens.leftButtonWidth, GenjutsuScreens.leftButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openGenjutsuScreen(player, "D"));
                        }
                    }));
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_C_SCREEN, GenjutsuScreens.leftPos, GenjutsuScreens.topPos, 0, 0, this.width, this.height, this.width, this.height);

            // Render buttons and other UI first
            super.render(graphics, mouseX, mouseY, partialTicks);

            // Ensure the hover texture is drawn above everything
            if (GenjutsuScreens.buttonRight.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.RIGHT_BUTTON_TEXTURE_HOVER,
                        GenjutsuScreens.rightButtonleftPos, GenjutsuScreens.rightButtonTopPos,
                        0, 0,
                        GenjutsuScreens.rightButtonWidth, GenjutsuScreens.rightButtonHeight,
                        GenjutsuScreens.rightButtonWidth, GenjutsuScreens.rightButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
            if (GenjutsuScreens.buttonLeft.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.LEFT_BUTTON_TEXTURE_HOVER,
                        GenjutsuScreens.leftButtonleftPos, GenjutsuScreens.leftButtonTopPos,
                        0, 0,
                        GenjutsuScreens.leftButtonWidth, GenjutsuScreens.leftButtonHeight,
                        GenjutsuScreens.leftButtonWidth, GenjutsuScreens.leftButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }

    public static class GenjutsuRangDScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public GenjutsuRangDScreen(LocalPlayer player) {
            super(TITLE);

            GenjutsuScreens.player = player;
        }

        @Override
        protected void init() {
            super.init();
            GenjutsuScreens.init(this.width, this.height);

            GenjutsuScreens.buttonRight = addRenderableWidget(new ImageButton(
                    GenjutsuScreens.rightButtonleftPos, GenjutsuScreens.rightButtonTopPos, // X and Y position
                    GenjutsuScreens.rightButtonWidth, GenjutsuScreens.rightButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.RIGHT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    GenjutsuScreens.rightButtonWidth, GenjutsuScreens.rightButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openGenjutsuScreen(player, "C"));
                        }
                    }));
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_D_SCREEN, GenjutsuScreens.leftPos, GenjutsuScreens.topPos, 0, 0, this.width, this.height, this.width, this.height);

            // Render buttons and other UI first
            super.render(graphics, mouseX, mouseY, partialTicks);

            // Ensure the hover texture is drawn above everything
            if (GenjutsuScreens.buttonRight.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.RIGHT_BUTTON_TEXTURE_HOVER,
                        GenjutsuScreens.rightButtonleftPos, GenjutsuScreens.rightButtonTopPos,
                        0, 0,
                        GenjutsuScreens.rightButtonWidth, GenjutsuScreens.rightButtonHeight,
                        GenjutsuScreens.rightButtonWidth, GenjutsuScreens.rightButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }
}
