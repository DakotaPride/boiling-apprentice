package net.dakotapride.boilingwitch.common.item;

import net.dakotapride.boilingwitch.common.item.magic.ISpellStoring;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GlyphItem extends Item implements ISpellStoring {
    public GlyphItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (isFireRelatedSpell(stack.getItem())) {
            setSpellTooltip(tooltip, stack, glyphMedium, fireModifier);
        } else if (isPlantRelatedSpell(stack.getItem())) {
            setSpellTooltip(tooltip, stack, glyphMedium, plantModifier);
        } else if (isIceRelatedSpell(stack.getItem())) {
            setSpellTooltip(tooltip, stack, glyphMedium, iceModifier);
        } else if (isLightRelatedSpell(stack.getItem())) {
            setSpellTooltip(tooltip, stack, glyphMedium, lightModifier);
        }

        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return super.use(world, user, hand);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        ItemStack itemStack = context.getStack();
        PlayerEntity player = context.getPlayer();

        if (player.isSneaking()) {
            if (isPlantGlyph(itemStack.getItem())) {
                createRestingNettleFromGlyphOrParchment(context);
            } else if (isIceGlyph(itemStack.getItem())) {
                createWallOfIceFromGlyphOrParchment(context);
            } else if (isFireGlyph(itemStack.getItem())) {
                createLineOfFireFromGlyphOrParchment(context);
            } else if (isLightGlyph(itemStack.getItem())) {

            }
        }

        return super.useOnBlock(context);
    }

    //  @Override
    //  public int ME(int energy) {
    //      return 16;
    //  }
}
