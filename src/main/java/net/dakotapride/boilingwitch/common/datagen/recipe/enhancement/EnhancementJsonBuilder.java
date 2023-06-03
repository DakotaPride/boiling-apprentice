package net.dakotapride.boilingwitch.common.datagen.recipe.enhancement;

import net.minecraft.advancement.criterion.CriterionConditions;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.Consumer;

public interface EnhancementJsonBuilder {
    Identifier ROOT = new Identifier("recipes/root");

    EnhancementJsonBuilder criterion(String name, CriterionConditions conditions);

    Item getOutputItem();

    void offerTo(Consumer<RecipeJsonProvider> exporter, Identifier recipeId);

    default void offerTo(Consumer<RecipeJsonProvider> exporter) {
        this.offerTo(exporter, getItemId(this.getOutputItem()));
    }

    default void offerTo(Consumer<RecipeJsonProvider> exporter, String recipePath) {
        Identifier identifier = getItemId(this.getOutputItem());
        Identifier identifier2 = new Identifier(recipePath);
        if (identifier2.equals(identifier)) {
            throw new IllegalStateException("Recipe " + recipePath + " should remove its 'save' argument as it is equal to default one");
        } else {
            this.offerTo(exporter, identifier2);
        }
    }

    static Identifier getItemId(ItemConvertible item) {
        return Registry.ITEM.getId(item.asItem());
    }
}
