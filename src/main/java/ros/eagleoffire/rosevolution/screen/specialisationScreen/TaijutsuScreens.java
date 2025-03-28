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

public class TaijutsuScreens {
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
    private static String element = "Taijutsu";

    private static void init(int width, int height) {
        TaijutsuScreens.rightButtonWidth = 20;
        TaijutsuScreens.rightButtonHeight = (int) (1.56 * TaijutsuScreens.rightButtonWidth);
        TaijutsuScreens.rightButtonleftPos = width - TaijutsuScreens.rightButtonWidth;
        TaijutsuScreens.rightButtonTopPos = (height / 2) - (TaijutsuScreens.rightButtonHeight / 2);

        TaijutsuScreens.leftButtonWidth = 20;
        TaijutsuScreens.leftButtonHeight = (int) (1.56 * TaijutsuScreens.leftButtonWidth);
        TaijutsuScreens.leftButtonleftPos = 0;
        TaijutsuScreens.leftButtonTopPos = (height / 2) - (TaijutsuScreens.leftButtonHeight / 2);
    }


    public static class TaijutsuRangSScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public TaijutsuRangSScreen(LocalPlayer player) {
            super(TITLE);

            TaijutsuScreens.player = player;
        }

        @Override
        protected void init() {
            super.init();
            TaijutsuScreens.init(this.width, this.height);

            TaijutsuScreens.buttonLeft = addRenderableWidget(new ImageButton(
                    TaijutsuScreens.leftButtonleftPos, TaijutsuScreens.leftButtonTopPos, // X and Y position
                    TaijutsuScreens.leftButtonWidth, TaijutsuScreens.leftButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.LEFT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    TaijutsuScreens.leftButtonWidth, TaijutsuScreens.leftButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openTaijutsuScreen(player, "A"));
                        }
                    }));
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_S_SCREEN, TaijutsuScreens.leftPos, TaijutsuScreens.topPos, 0, 0, this.width, this.height, this.width, this.height);

            // Render buttons and other UI first
            super.render(graphics, mouseX, mouseY, partialTicks);

            // Ensure the hover texture is drawn above everything
            if (TaijutsuScreens.buttonLeft.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.LEFT_BUTTON_TEXTURE_HOVER,
                        TaijutsuScreens.leftButtonleftPos, TaijutsuScreens.leftButtonTopPos,
                        0, 0,
                        TaijutsuScreens.leftButtonWidth, TaijutsuScreens.leftButtonHeight,
                        TaijutsuScreens.leftButtonWidth, TaijutsuScreens.leftButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }

    public static class TaijutsuRangAScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public TaijutsuRangAScreen(LocalPlayer player) {
            super(TITLE);

            TaijutsuScreens.player = player;
        }

        @Override
        protected void init() {
            super.init();
            TaijutsuScreens.init(this.width, this.height);

            TaijutsuScreens.buttonRight = addRenderableWidget(new ImageButton(
                    TaijutsuScreens.rightButtonleftPos, TaijutsuScreens.rightButtonTopPos, // X and Y position
                    TaijutsuScreens.rightButtonWidth, TaijutsuScreens.rightButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.RIGHT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    TaijutsuScreens.rightButtonWidth, TaijutsuScreens.rightButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openTaijutsuScreen(player, "S"));
                        }
                    }));

            TaijutsuScreens.buttonLeft = addRenderableWidget(new ImageButton(
                    TaijutsuScreens.leftButtonleftPos, TaijutsuScreens.leftButtonTopPos, // X and Y position
                    TaijutsuScreens.leftButtonWidth, TaijutsuScreens.leftButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.LEFT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    TaijutsuScreens.leftButtonWidth, TaijutsuScreens.leftButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openTaijutsuScreen(player, "B"));
                        }
                    }));
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_A_SCREEN, TaijutsuScreens.leftPos, TaijutsuScreens.topPos, 0, 0, this.width, this.height, this.width, this.height);

            // Render buttons and other UI first
            super.render(graphics, mouseX, mouseY, partialTicks);

            // Ensure the hover texture is drawn above everything
            if (TaijutsuScreens.buttonRight.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.RIGHT_BUTTON_TEXTURE_HOVER,
                        TaijutsuScreens.rightButtonleftPos, TaijutsuScreens.rightButtonTopPos,
                        0, 0,
                        TaijutsuScreens.rightButtonWidth, TaijutsuScreens.rightButtonHeight,
                        TaijutsuScreens.rightButtonWidth, TaijutsuScreens.rightButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
            if (TaijutsuScreens.buttonLeft.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.LEFT_BUTTON_TEXTURE_HOVER,
                        TaijutsuScreens.leftButtonleftPos, TaijutsuScreens.leftButtonTopPos,
                        0, 0,
                        TaijutsuScreens.leftButtonWidth, TaijutsuScreens.leftButtonHeight,
                        TaijutsuScreens.leftButtonWidth, TaijutsuScreens.leftButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }

    public static class TaijutsuRangBScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public TaijutsuRangBScreen(LocalPlayer player) {
            super(TITLE);

            TaijutsuScreens.player = player;
        }

        @Override
        protected void init() {
            super.init();
            TaijutsuScreens.init(this.width, this.height);

            TaijutsuScreens.buttonRight = addRenderableWidget(new ImageButton(
                    TaijutsuScreens.rightButtonleftPos, TaijutsuScreens.rightButtonTopPos, // X and Y position
                    TaijutsuScreens.rightButtonWidth, TaijutsuScreens.rightButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.RIGHT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    TaijutsuScreens.rightButtonWidth, TaijutsuScreens.rightButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openTaijutsuScreen(player, "A"));
                        }
                    }));

            TaijutsuScreens.buttonLeft = addRenderableWidget(new ImageButton(
                    TaijutsuScreens.leftButtonleftPos, TaijutsuScreens.leftButtonTopPos, // X and Y position
                    TaijutsuScreens.leftButtonWidth, TaijutsuScreens.leftButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.LEFT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    TaijutsuScreens.leftButtonWidth, TaijutsuScreens.leftButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openTaijutsuScreen(player, "C"));
                        }
                    }));
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_B_SCREEN, TaijutsuScreens.leftPos, TaijutsuScreens.topPos, 0, 0, this.width, this.height, this.width, this.height);

            // Render buttons and other UI first
            super.render(graphics, mouseX, mouseY, partialTicks);

            // Ensure the hover texture is drawn above everything
            if (TaijutsuScreens.buttonRight.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.RIGHT_BUTTON_TEXTURE_HOVER,
                        TaijutsuScreens.rightButtonleftPos, TaijutsuScreens.rightButtonTopPos,
                        0, 0,
                        TaijutsuScreens.rightButtonWidth, TaijutsuScreens.rightButtonHeight,
                        TaijutsuScreens.rightButtonWidth, TaijutsuScreens.rightButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
            if (TaijutsuScreens.buttonLeft.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.LEFT_BUTTON_TEXTURE_HOVER,
                        TaijutsuScreens.leftButtonleftPos, TaijutsuScreens.leftButtonTopPos,
                        0, 0,
                        TaijutsuScreens.leftButtonWidth, TaijutsuScreens.leftButtonHeight,
                        TaijutsuScreens.leftButtonWidth, TaijutsuScreens.leftButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }

    public static class TaijutsuRangCScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public TaijutsuRangCScreen(LocalPlayer player) {
            super(TITLE);

            TaijutsuScreens.player = player;
        }

        @Override
        protected void init() {
            super.init();
            TaijutsuScreens.init(this.width, this.height);

            TaijutsuScreens.buttonRight = addRenderableWidget(new ImageButton(
                    TaijutsuScreens.rightButtonleftPos, TaijutsuScreens.rightButtonTopPos, // X and Y position
                    TaijutsuScreens.rightButtonWidth, TaijutsuScreens.rightButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.RIGHT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    TaijutsuScreens.rightButtonWidth, TaijutsuScreens.rightButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openTaijutsuScreen(player, "B"));
                        }
                    }));

            TaijutsuScreens.buttonLeft = addRenderableWidget(new ImageButton(
                    TaijutsuScreens.leftButtonleftPos, TaijutsuScreens.leftButtonTopPos, // X and Y position
                    TaijutsuScreens.leftButtonWidth, TaijutsuScreens.leftButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.LEFT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    TaijutsuScreens.leftButtonWidth, TaijutsuScreens.leftButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openTaijutsuScreen(player, "D"));
                        }
                    }));
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_C_SCREEN, TaijutsuScreens.leftPos, TaijutsuScreens.topPos, 0, 0, this.width, this.height, this.width, this.height);

            // Render buttons and other UI first
            super.render(graphics, mouseX, mouseY, partialTicks);

            // Ensure the hover texture is drawn above everything
            if (TaijutsuScreens.buttonRight.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.RIGHT_BUTTON_TEXTURE_HOVER,
                        TaijutsuScreens.rightButtonleftPos, TaijutsuScreens.rightButtonTopPos,
                        0, 0,
                        TaijutsuScreens.rightButtonWidth, TaijutsuScreens.rightButtonHeight,
                        TaijutsuScreens.rightButtonWidth, TaijutsuScreens.rightButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
            if (TaijutsuScreens.buttonLeft.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.LEFT_BUTTON_TEXTURE_HOVER,
                        TaijutsuScreens.leftButtonleftPos, TaijutsuScreens.leftButtonTopPos,
                        0, 0,
                        TaijutsuScreens.leftButtonWidth, TaijutsuScreens.leftButtonHeight,
                        TaijutsuScreens.leftButtonWidth, TaijutsuScreens.leftButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }

    public static class TaijutsuRangDScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public TaijutsuRangDScreen(LocalPlayer player) {
            super(TITLE);

            TaijutsuScreens.player = player;
        }

        @Override
        protected void init() {
            super.init();
            TaijutsuScreens.init(this.width, this.height);

            TaijutsuScreens.buttonRight = addRenderableWidget(new ImageButton(
                    TaijutsuScreens.rightButtonleftPos, TaijutsuScreens.rightButtonTopPos, // X and Y position
                    TaijutsuScreens.rightButtonWidth, TaijutsuScreens.rightButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.RIGHT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    TaijutsuScreens.rightButtonWidth, TaijutsuScreens.rightButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openTaijutsuScreen(player, "C"));
                        }
                    }));
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_D_SCREEN, TaijutsuScreens.leftPos, TaijutsuScreens.topPos, 0, 0, this.width, this.height, this.width, this.height);

            // Render buttons and other UI first
            super.render(graphics, mouseX, mouseY, partialTicks);

            // Ensure the hover texture is drawn above everything
            if (TaijutsuScreens.buttonRight.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.RIGHT_BUTTON_TEXTURE_HOVER,
                        TaijutsuScreens.rightButtonleftPos, TaijutsuScreens.rightButtonTopPos,
                        0, 0,
                        TaijutsuScreens.rightButtonWidth, TaijutsuScreens.rightButtonHeight,
                        TaijutsuScreens.rightButtonWidth, TaijutsuScreens.rightButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }
}
