package net.dakotapride.boilingwitch.common.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class GlyphEnhancingRecipe implements Recipe<SimpleInventory> {
    private final Identifier id;
    private final ItemStack output;
    private final DefaultedList<Ingredient> input;

    public GlyphEnhancingRecipe(Identifier id, ItemStack output, DefaultedList<Ingredient> recipeItems) {
        this.id = id;
        this.output = output;
        this.input = recipeItems;
    }



    @Override
    public DefaultedList<Ingredient> getIngredients() {
        return input;
    }

    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        List<ItemStack> checklist = new ArrayList<>();
        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.getStack(i);
            if (!stack.isEmpty()) {
                checklist.add(stack);
            }
        }
        if (input.size() != checklist.size()) {
            return false;
        }

        if (!(input.get(0).test(inventory.getStack(1)))) { return false; }
        if (!(input.get(1).test(inventory.getStack(2)))) { return false; }
        if (!(input.get(2).test(inventory.getStack(3)))) { return false; }
        if (!(input.get(3).test(inventory.getStack(4)))) { return false; }

        for (Ingredient ingredient : input) {
            boolean found = false;
            for (ItemStack stack : checklist) {
                if (ingredient.test(stack)) {
                    found = true;
                    checklist.remove(stack);
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack craft(SimpleInventory inventory) {
        return output;
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getOutput() {
        return output.copy();
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<GlyphEnhancingRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "enhancement";
    }

    public static class Serializer implements RecipeSerializer<GlyphEnhancingRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "enhancement";
        // this is the name given in the json file

        @Override
        public GlyphEnhancingRecipe read(Identifier id, JsonObject json) {
            DefaultedList<Ingredient> ingredients = readIngredients(JsonHelper.getArray(json, "ingredients"));

            if (ingredients.isEmpty()) {
                throw new JsonParseException("No ingredients for enhancement recipe");
            } else if (ingredients.size() > 5) {
                throw new JsonParseException("Too many ingredients for this recipe! Maximum input is 5");
            }

            ItemStack output = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "output"));
            return new GlyphEnhancingRecipe(id, output, ingredients);
        }

        private static DefaultedList<Ingredient> readIngredients(JsonArray elements) {
            DefaultedList<Ingredient> list = DefaultedList.of();
            IntStream.range(0, elements.size())
                    .mapToObj(i -> Ingredient.fromJson(elements.get(i)))
                    .filter(ingredient -> !ingredient.isEmpty())
                    .forEach(list::add);
            return list;
        }

        @Nullable
        @Override
        public GlyphEnhancingRecipe read(Identifier recipeId, PacketByteBuf buffer) {
            DefaultedList<Ingredient> list = DefaultedList.ofSize(buffer.readInt(), Ingredient.EMPTY);
            IntStream.range(0, list.size()).forEach(i -> list.set(i, Ingredient.fromPacket(buffer)));
            return new GlyphEnhancingRecipe(recipeId, buffer.readItemStack(), list);
        }

        @Override
        public void write(PacketByteBuf buffer, GlyphEnhancingRecipe recipe) {
            buffer.writeInt(recipe.input.size());
            recipe.input.forEach(ingredient -> ingredient.write(buffer));
            buffer.writeItemStack(recipe.output);
        }
    }
}