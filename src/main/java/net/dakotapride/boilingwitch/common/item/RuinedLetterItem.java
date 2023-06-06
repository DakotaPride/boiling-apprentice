package net.dakotapride.boilingwitch.common.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RuinedLetterItem extends Item {

    public RuinedLetterItem(Settings settings) {
        super(settings);
    }

    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player) {
        int random = Random.create().nextBetween(1, 4);
        NbtCompound nbtCompound = new NbtCompound();

        nbtCompound.putInt("letterValue", random);

        if (!stack.hasNbt()) {
            stack.setNbt(nbtCompound);
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {

        if (stack.hasNbt()) {
            tooltip.add(Text.literal("Hello, this is the text for Letter " + stack.getNbt().getInt("letterValue") + "!"));
        }

        // tooltip.add(Text.translatable(Letter.allowForTooltip(stack)));
    }
}
