package net.dakotapride.boilingwitch.common.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GaldorstoneItem extends Item {
    public GaldorstoneItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {

        if (stack.hasNbt()) {
            tooltip.add(Text.literal("Uses: " + stack.getNbt().getInt("riftUses")).formatted(Formatting.ITALIC).formatted(Formatting.GRAY));
        }

        // tooltip.add(Text.translatable(Letter.allowForTooltip(stack)));
    }
}
