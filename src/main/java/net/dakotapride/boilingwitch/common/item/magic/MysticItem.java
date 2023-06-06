package net.dakotapride.boilingwitch.common.item.magic;

import net.dakotapride.boilingwitch.common.register.content.ItemRegister;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MysticItem extends Item {
    public static int mEnergy;

    public MysticItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;

        if (player.getEquippedStack(EquipmentSlot.HEAD).isOf(ItemRegister.MASK_OF_PURITY)) {
            tooltip.add(Text.literal("Hello! My ME is currently hidden :)"));
        }
    }

    public int ME(int energy) {
        mEnergy = energy;

        return energy;
    }

    public int getMysticEnergy() {
        return ME(mEnergy);
    }
}
