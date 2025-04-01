package ros.eagleoffire.rosevolution.screen.specialisationScreen.elementScreen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import org.jetbrains.annotations.NotNull;
import ros.eagleoffire.rosevolution.ROSEvolution;
import ros.eagleoffire.rosevolution.client.ClientHooks;
import ros.eagleoffire.rosevolution.config.ModClientConfigs;
import ros.eagleoffire.rosevolution.screen.specialisationScreen.TexturesScreen;

import java.lang.reflect.Method;
import java.util.List;

public class DotonScreens {
    private static final int leftPos = 0;
    private static final int topPos = 0;

    private static int rightButtonLeftPos;
    private static int rightButtonTopPos;
    private static int rightButtonHeight;
    private static int rightButtonWidth;
    private static int leftButtonLeftPos;
    private static int leftButtonTopPos;
    private static int leftButtonHeight;
    private static int leftButtonWidth;
    private static int backButtonLeftPos;
    private static int backButtonTopPos;
    private static int backButtonHeight;
    private static int backButtonWidth;

    private static LocalPlayer player;
    private static Button buttonRight;
    private static Button buttonLeft;
    private static Button buttonBack;
    private static ImageButton imageButtonBack;
    private static String element = "Doton";

    private static ResourceLocation cachedTextureLocation;

    private static void init(int width, int height, Minecraft minecraft) {
        rightButtonWidth = 20;
        rightButtonHeight = (int) (1.56 * rightButtonWidth);
        rightButtonLeftPos = width - rightButtonWidth;
        rightButtonTopPos = (height / 2) - (rightButtonHeight / 2);

        leftButtonWidth = 20;
        leftButtonHeight = (int) (1.56 * leftButtonWidth);
        leftButtonLeftPos = 0;
        leftButtonTopPos = (height / 2) - (leftButtonHeight / 2);

        backButtonWidth = 40;
        backButtonHeight = (int) (0.625 * backButtonWidth);
        backButtonLeftPos = backButtonWidth / 2;
        backButtonTopPos = height - backButtonHeight - (backButtonWidth / 2);

        imageButtonBack = new ImageButton(
                backButtonLeftPos, backButtonTopPos, // X and Y position
                backButtonWidth, backButtonHeight, // Width and Height
                0, 0, // Texture X and Y (start of image)
                0, // Amount to shift Y on click
                TexturesScreen.BACK_ARROW, // Texture location
                backButtonWidth, backButtonHeight, // Total texture size (width, height of full image)
                (button) -> {
                    if (minecraft != null && minecraft.player != null) {
                        Minecraft.getInstance().setScreen(null);
                        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementSelectScreen(player));
                    }
                });
    }

    private static void executeCommand(String command, Minecraft minecraft) {
        if (minecraft != null && minecraft.player != null && !command.isEmpty()) {
            minecraft.player.connection.sendCommand(command);
        }
    }

    public static void loadButtonsFromConfig(Screen screen, ResourceLocation cachedTextureLocation, int buttonX, int buttonY, int buttonWidth, String command) {
        ImageButton button = new ImageButton(
                buttonX, buttonY, // X and Y position
                buttonWidth, buttonWidth, // Width and Height
                0, 0, // Texture X and Y (start of image)
                0, // Amount to shift Y on click
                cachedTextureLocation, // Texture location
                buttonWidth, buttonWidth, // Total texture size (width, height of full image)
                (b) -> {
                    screen.getMinecraft();
                    if (screen.getMinecraft().player != null) {
                        String playerName = screen.getMinecraft().player.getGameProfile().getName();
                        String processedCommand = command.replace("@s", playerName);
                        executeCommand(processedCommand, screen.getMinecraft());
                        Minecraft.getInstance().setScreen(null);
                    }
                });

        try {
            Method addWidgetMethod = Screen.class.getDeclaredMethod("addRenderableWidget", GuiEventListener.class);
            addWidgetMethod.setAccessible(true);
            addWidgetMethod.invoke(screen, button);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void butonBackHover(GuiGraphics graphics) {
        if (buttonBack.isHoveredOrFocused()) {
            graphics.pose().pushPose(); // Save previous render state
            graphics.pose().translate(0, 0, 500); // Move it to the top layer
            graphics.blit(TexturesScreen.BACK_ARROW_HOVER,
                    backButtonLeftPos, backButtonTopPos,
                    0, 0,
                    backButtonWidth, backButtonHeight,
                    backButtonWidth, backButtonHeight);
            graphics.pose().popPose(); // Restore previous state
        }
    }

    private static void buttonRightHover(GuiGraphics graphics) {
        // Ensure the hover texture is drawn above everything
        if (buttonRight.isHoveredOrFocused()) {
            graphics.pose().pushPose(); // Save previous render state
            graphics.pose().translate(0, 0, 500); // Move it to the top layer
            graphics.blit(TexturesScreen.RIGHT_BUTTON_TEXTURE_HOVER,
                    rightButtonLeftPos, rightButtonTopPos,
                    0, 0,
                    rightButtonWidth, rightButtonHeight,
                    rightButtonWidth, rightButtonHeight);
            graphics.pose().popPose(); // Restore previous state
        }
    }

    private static void buttonLeftHover(GuiGraphics graphics) {
        if (buttonLeft.isHoveredOrFocused()) {
            graphics.pose().pushPose(); // Save previous render state
            graphics.pose().translate(0, 0, 500); // Move it to the top layer
            graphics.blit(TexturesScreen.LEFT_BUTTON_TEXTURE_HOVER,
                    leftButtonLeftPos, leftButtonTopPos,
                    0, 0,
                    leftButtonWidth, leftButtonHeight,
                    leftButtonWidth, leftButtonHeight);
            graphics.pose().popPose(); // Restore previous state
        }
    }

    public static class DotonRangSScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public DotonRangSScreen(LocalPlayer player) {
            super(TITLE);
        }

        @Override
        protected void init() {
            super.init();
            DotonScreens.init(this.width, this.height, this.minecraft);

            buttonLeft = addRenderableWidget(new ImageButton(
                    leftButtonLeftPos, leftButtonTopPos, // X and Y position
                    leftButtonWidth, leftButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.LEFT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    leftButtonWidth, leftButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, DotonScreens.element, "A"));
                        }
                    }));

            buttonBack = addRenderableWidget(imageButtonBack);
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_S_SCREEN, DotonScreens.leftPos, DotonScreens.topPos, 0, 0, this.width, this.height, this.width, this.height);

            buttonLeftHover(graphics);
            butonBackHover(graphics);
            super.render(graphics, mouseX, mouseY, partialTicks);
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }

    public static class DotonRangAScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public DotonRangAScreen(LocalPlayer player) {
            super(TITLE);
        }

        @Override
        protected void init() {
            super.init();
            DotonScreens.init(this.width, this.height, this.minecraft);

            buttonRight = addRenderableWidget(new ImageButton(
                    rightButtonLeftPos, rightButtonTopPos, // X and Y position
                    rightButtonWidth, rightButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.RIGHT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    rightButtonWidth, rightButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, DotonScreens.element, "S"));
                        }
                    }));

            buttonLeft = addRenderableWidget(new ImageButton(
                    leftButtonLeftPos, leftButtonTopPos, // X and Y position
                    leftButtonWidth, leftButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.LEFT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    leftButtonWidth, leftButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, DotonScreens.element, "B"));
                        }
                    }));

            buttonBack = addRenderableWidget(imageButtonBack);
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_A_SCREEN, leftPos, topPos, 0, 0, this.width, this.height, this.width, this.height);
            buttonRightHover(graphics);
            buttonLeftHover(graphics);
            butonBackHover(graphics);
            super.render(graphics, mouseX, mouseY, partialTicks);
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }

    public static class DotonRangBScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public DotonRangBScreen(LocalPlayer player) {
            super(TITLE);
        }

        @Override
        protected void init() {
            super.init();
            DotonScreens.init(this.width, this.height, this.minecraft);

            buttonRight = addRenderableWidget(new ImageButton(
                    rightButtonLeftPos, rightButtonTopPos, // X and Y position
                    rightButtonWidth, rightButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.RIGHT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    rightButtonWidth, rightButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, DotonScreens.element, "A"));
                        }
                    }));

            buttonLeft = addRenderableWidget(new ImageButton(
                    leftButtonLeftPos, leftButtonTopPos, // X and Y position
                    leftButtonWidth, leftButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.LEFT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    leftButtonWidth, leftButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, DotonScreens.element, "C"));
                        }
                    }));

            buttonBack = addRenderableWidget(imageButtonBack);
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_B_SCREEN, leftPos, topPos, 0, 0, this.width, this.height, this.width, this.height);

            // Ensure the hover texture is drawn above everything
            buttonRightHover(graphics);
            buttonLeftHover(graphics);
            butonBackHover(graphics);
            super.render(graphics, mouseX, mouseY, partialTicks);
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }

    public static class DotonRangCScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public DotonRangCScreen(LocalPlayer player) {
            super(TITLE);
        }

        @Override
        protected void init() {
            super.init();
            DotonScreens.init(this.width, this.height, this.minecraft);

            buttonRight = addRenderableWidget(new ImageButton(
                    rightButtonLeftPos, rightButtonTopPos, // X and Y position
                    rightButtonWidth, rightButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.RIGHT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    rightButtonWidth, rightButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, DotonScreens.element, "B"));
                        }
                    }));

            buttonLeft = addRenderableWidget(new ImageButton(
                    leftButtonLeftPos, leftButtonTopPos, // X and Y position
                    leftButtonWidth, leftButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.LEFT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    leftButtonWidth, leftButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, DotonScreens.element, "D"));
                        }
                    }));

            buttonBack = addRenderableWidget(imageButtonBack);
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_C_SCREEN, leftPos, topPos, 0, 0, this.width, this.height, this.width, this.height);
            buttonRightHover(graphics);
            buttonLeftHover(graphics);
            butonBackHover(graphics);
            super.render(graphics, mouseX, mouseY, partialTicks);
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }

    public static class DotonRangDScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public DotonRangDScreen(LocalPlayer player) {
            super(TITLE);
        }

        @Override
        protected void init() {
            super.init();
            DotonScreens.init(this.width, this.height, this.minecraft);

            buttonRight = addRenderableWidget(new ImageButton(
                    rightButtonLeftPos, rightButtonTopPos, // X and Y position
                    rightButtonWidth, rightButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.RIGHT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    rightButtonWidth, rightButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, DotonScreens.element, "C"));
                        }
                    }));


            buttonBack = addRenderableWidget(imageButtonBack);

            List<String> buttonTexts = ModClientConfigs.SPELLS_NAMES.get();
            List<String> buttonCommands = ModClientConfigs.SPELLS_COMMAND.get();
            List<String> buttonTextures = ModClientConfigs.SPELLS_ICON_RAW.get();

            int buttonWidth = this.width / 10;
            int buttonX = (this.width - (5 * buttonWidth)) / 6;
            int buttonY = this.height / 3;

            for (int i = 0; i < buttonTexts.size() && i < buttonCommands.size(); i++) {

                String textureName = buttonTextures.get(i);
                DynamicTexture cachedTexture = ModClientConfigs.getButtonImages().get(textureName);

                if (cachedTexture != null) {
                    cachedTextureLocation = new ResourceLocation("modid", "dynamic_textures/" + textureName);
                    Minecraft.getInstance().getTextureManager().register(cachedTextureLocation, cachedTexture);
                }

                String command = buttonCommands.get(i);

                if (cachedTextureLocation != null) {
                    loadButtonsFromConfig(this, cachedTextureLocation, buttonX, buttonY, buttonWidth, command);
                }
                if ((i + 1) % 5 == 0 && i != 0) {  // Every 5th iteration (excluding 0)
                    buttonX = 0;
                    buttonY += buttonWidth + ((this.height - (this.height / 3)) - (3 * buttonWidth)) / 4; // Adjust the increment as needed
                } else {
                    buttonX += buttonWidth + (this.width - (5 * buttonWidth)) / 6; // Adjust the increment as needed
                }
            }
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_D_SCREEN, leftPos, topPos, 0, 0, this.width, this.height, this.width, this.height);
            buttonRightHover(graphics);
            butonBackHover(graphics);
            super.render(graphics, mouseX, mouseY, partialTicks);
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }
}
