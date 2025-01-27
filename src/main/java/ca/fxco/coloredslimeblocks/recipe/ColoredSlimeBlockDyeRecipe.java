package ca.fxco.coloredslimeblocks.recipe;

import ca.fxco.coloredslimeblocks.base.ModItems;
import ca.fxco.coloredslimeblocks.base.ModRecipeSerializers;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import static ca.fxco.coloredslimeblocks.base.ModBlockStateProperties.COLOR_PROPERTY;
import static net.minecraft.core.component.DataComponents.BLOCK_STATE;

// You can't have components in your input items using json, so we have to make a custom recipe instead
public class ColoredSlimeBlockDyeRecipe extends CustomRecipe {

    public ColoredSlimeBlockDyeRecipe(CraftingBookCategory craftingBookCategory) {
        super(craftingBookCategory);
    }

    @Override
    public boolean matches(CraftingInput craftingInput, Level level) {
        if (craftingInput.ingredientCount() < 2) {
            return false;
        }

        DyeColor itemColor = null;
        DyeColor slimeBlockColor = null;
        for(int i = 0; i < craftingInput.size(); ++i) {
            ItemStack itemStack = craftingInput.getItem(i);
            if (!itemStack.isEmpty()) {
                if (itemStack.getItem() instanceof DyeItem dyeItem) {
                    if (itemColor != null) {
                        return false;
                    }
                    itemColor = dyeItem.getDyeColor();
                } else if (itemStack.is(Items.SLIME_BLOCK)) {
                    if (slimeBlockColor != null) {
                        return false;
                    }
                    slimeBlockColor = DyeColor.WHITE;
                } else if (itemStack.is(ModItems.COLORED_SLIME_BLOCK)) {
                    if (slimeBlockColor != null) {
                        return false;
                    }
                    DataComponentMap components = itemStack.getComponents();
                    if (components.has(BLOCK_STATE)) {
                        slimeBlockColor = components.get(BLOCK_STATE).get(COLOR_PROPERTY);
                    } else {
                        slimeBlockColor = DyeColor.WHITE;
                    }
                }
            }
        }
        if (itemColor == null || slimeBlockColor == null || itemColor == DyeColor.WHITE) {
            return false;
        }
        return itemColor != slimeBlockColor;
    }

    @Override
    public ItemStack assemble(CraftingInput craftingInput, HolderLookup.Provider provider) {
        for(int i = 0; i < craftingInput.size(); ++i) {
            ItemStack itemStack = craftingInput.getItem(i);
            if (!itemStack.isEmpty() && itemStack.getItem() instanceof DyeItem dyeItem) {
                return ModItems.createColoredSlimeBlockStack(dyeItem.getDyeColor());
            }
        }
        return ItemStack.EMPTY; // Should never be reached
    }

    @Override
    public RecipeSerializer<ColoredSlimeBlockDyeRecipe> getSerializer() {
        return ModRecipeSerializers.COLORED_SLIME_BLOCK_DYE;
    }
}
