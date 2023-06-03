package net.dakotapride.boilingwitch.common.integration.rei.enhancement;

import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.dakotapride.boilingwitch.common.integration.rei.BoilingWitchPlugin;
import net.dakotapride.boilingwitch.common.register.content.BlockRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class EnhancementCategory implements DisplayCategory<EnhancementDisplay> {
    public static final Text TITLE = Text.translatable("text.boilingwitch.enhancement");
    public static final EntryStack<ItemStack> ICON = EntryStacks.of(BlockRegister.GLYPH_ENHANCEMENT_TABLE.asItem());

    @Override
    public Renderer getIcon() {
        return ICON;
    }

    @Override
    public Text getTitle() {
        return TITLE;
    }

    @Override
    public int getDisplayHeight() {
        return 70;
    }

    @Override
    public CategoryIdentifier<? extends EnhancementDisplay> getCategoryIdentifier() {
        return BoilingWitchPlugin.ENHANCEMENT;
    }

    @Override
    public List<Widget> setupDisplay(EnhancementDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.getCenterX() - 64, bounds.getCenterY() - 16);
        Point outputPoint = new Point(startPoint.x + 95, startPoint.y + 9);

        List<Widget> widgets = new ArrayList<>();

        widgets.add(Widgets.createRecipeBase(bounds));

        widgets.add(Widgets.createArrow(new Point(startPoint.x + 63, startPoint.y + 9)));


        widgets.add(Widgets.createSlot(new Point(startPoint.x + 21, startPoint.y))
                .entries(display.getInputEntries().get(4)).markInput());
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 39, startPoint.y))
                .entries(display.getInputEntries().get(0)).markInput());
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 21, startPoint.y + 18))
                .entries(display.getInputEntries().get(1)).markInput());
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 39, startPoint.y + 18))
                .entries(display.getInputEntries().get(2)).markInput());
        widgets.add(Widgets.createSlot(new Point(startPoint.x, startPoint.y + 18))
                .entries(display.getInputEntries().get(3)).markInput());


        // widgets.add(Widgets.createResultSlotBackground(outputPoint));
        widgets.add(Widgets.createSlot(outputPoint).entries(display.getOutputEntries().get(0)).markOutput());

        return widgets;
    }
}