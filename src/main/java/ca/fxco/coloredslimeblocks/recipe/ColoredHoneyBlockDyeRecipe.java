package ca.fxco.coloredslimeblocks.recipe;

import ca.fxco.coloredslimeblocks.base.ModItems;
import ca.fxco.coloredslimeblocks.base.ModRecipeSerializers;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

import static ca.fxco.coloredslimeblocks.base.ModBlockStateProperties.COLOR_PROPERTY;
import static net.minecraft.core.component.DataComponents.BLOCK_STATE;

// You can't have components in your input items using json, so we have to make a custom recipe instead
public class ColoredHoneyBlockDyeRecipe extends CustomRecipe {

    public ColoredHoneyBlockDyeRecipe(CraftingBookCategory craftingBookCategory) {
        super(craftingBookCategory);
    }

    @Override
    public boolean matches(CraftingInput craftingInput, Level level) {
        if (craftingInput.ingredientCount() < 2) {
            return false;
        }

        DyeColor itemColor = null;
        DyeColor honeyBlockColor = null;
        for(int i = 0; i < craftingInput.size(); ++i) {
            ItemStack itemStack = craftingInput.getItem(i);
            if (!itemStack.isEmpty()) {
                if (itemStack.getItem() instanceof DyeItem dyeItem) {
                    if (itemColor != null) {
                        return false;
                    }
                    itemColor = dyeItem.getDyeColor();
                } else if (itemStack.is(Items.HONEY_BLOCK)) {
                    if (honeyBlockColor != null) {
                        return false;
                    }
                    honeyBlockColor = DyeColor.WHITE;
                } else if (itemStack.is(ModItems.COLORED_HONEY_BLOCK)) {
                    if (honeyBlockColor != null) {
                        return false;
                    }
                    DataComponentMap components = itemStack.getComponents();
                    if (components.has(BLOCK_STATE)) {
                        honeyBlockColor = components.get(BLOCK_STATE).get(COLOR_PROPERTY);
                    } else {
                        honeyBlockColor = DyeColor.WHITE;
                    }
                }
            }
        }
        if (itemColor == null || honeyBlockColor == null || itemColor == DyeColor.WHITE) {
            return false;
        }
        return itemColor != honeyBlockColor;
    }

    @Override
    public ItemStack assemble(CraftingInput craftingInput, HolderLookup.Provider provider) {
        for(int i = 0; i < craftingInput.size(); ++i) {
            ItemStack itemStack = craftingInput.getItem(i);
            if (!itemStack.isEmpty() && itemStack.getItem() instanceof DyeItem dyeItem) {
                return ModItems.createColoredHoneyBlockStack(dyeItem.getDyeColor());
            }
        }
        return ItemStack.EMPTY; // Should never be reached
    }

    @Override
    public RecipeSerializer<ColoredHoneyBlockDyeRecipe> getSerializer() {
        return ModRecipeSerializers.COLORED_HONEY_BLOCK_DYE;
    }
}
