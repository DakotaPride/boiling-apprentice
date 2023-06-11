package net.dakotapride.boilingwitch.common.item;

import net.minecraft.text.Text;

import java.util.List;

public interface IWIP {

    default void getWorkInProgressTooltip(List<Text> tooltip) {
        tooltip.add(Text.literal("Apologies, looks like this feature is unfinished!"));
    }

}
