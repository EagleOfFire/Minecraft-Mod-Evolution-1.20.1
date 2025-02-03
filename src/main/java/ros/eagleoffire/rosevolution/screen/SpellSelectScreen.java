package ros.eagleoffire.rosevolution.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class SpellSelectScreen extends Screen {
    private final Minecraft minecraft;

    protected SpellSelectScreen() {
        super(Component.literal("Spell Select Menu"));
        this.minecraft= Minecraft.getInstance();
    }

    protected void init() {
        //TODO Initialize elements here
    }

    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks){
        super.render(graphics, mouseX, mouseY, partialTicks);
        //TODO draw the background
        //TODO draw nodes in a tree-like structure similar to advancements
    }
}
