package ca.fxco.coloredslimeblocks.base;

import ca.fxco.coloredslimeblocks.recipe.ColoredHoneyBlockDyeRecipe;
import ca.fxco.coloredslimeblocks.recipe.ColoredSlimeBlockDyeRecipe;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;

import static ca.fxco.coloredslimeblocks.ColoredSlimeBlocks.id;

public class ModRecipeSerializers {

    public static final RecipeSerializer<ColoredSlimeBlockDyeRecipe> COLORED_SLIME_BLOCK_DYE = register(
            "crafting_special_colored_slime_blocks",
            new CustomRecipe.Serializer<>(ColoredSlimeBlockDyeRecipe::new)
    );

    public static final RecipeSerializer<ColoredHoneyBlockDyeRecipe> COLORED_HONEY_BLOCK_DYE = register(
            "crafting_special_colored_honey_blocks",
            new CustomRecipe.Serializer<>(ColoredHoneyBlockDyeRecipe::new)
    );

    private static <S extends RecipeSerializer<T>, T extends Recipe<?>> S register(String name, S serializer) {
        return Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, id(name), serializer);
    }

    public static void bootstrap() { }
}
