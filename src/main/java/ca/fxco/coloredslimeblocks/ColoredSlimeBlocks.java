package ca.fxco.coloredslimeblocks;

import ca.fxco.coloredslimeblocks.base.*;
import ca.fxco.pistonlib.api.PistonLibInitializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;

public class ColoredSlimeBlocks implements ModInitializer, PistonLibInitializer {

    public static final String MOD_ID = "coloredslimeblocks";

    @Override
    public void onInitialize() {}

    @Override
    public void initialize() {}

    @Override
    public void registerPistonFamilies() {}

    @Override
    public void registerStickyGroups() {
        ModStickyGroups.bootstrap();
    }

    @Override
    public void bootstrap() {
        ModBlocks.bootstrap();
        ModItems.bootstrap();
        ModCreativeModeTabs.bootstrap();
        ModRecipeSerializers.bootstrap();
        ModStats.bootstrap();

        CauldronInteraction.WATER.map().put(
                ModItems.COLORED_SLIME_BLOCK,
                ColoredSlimeBlocks::cleanColoredSlimeBlockIteration
        );
    }

    public static ResourceLocation id(String name) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
    }

    private static InteractionResult cleanColoredSlimeBlockIteration(BlockState blockState, Level level,
                                                                     BlockPos blockPos, Player player,
                                                                     InteractionHand interactionHand,
                                                                     ItemStack itemStack) {
        if (!itemStack.is(ModItems.COLORED_SLIME_BLOCK)) {
            return InteractionResult.TRY_WITH_EMPTY_HAND;
        } else if (!itemStack.has(DataComponents.BLOCK_STATE)) {
            return InteractionResult.TRY_WITH_EMPTY_HAND;
        } else {
            if (!level.isClientSide) {
                player.setItemInHand(interactionHand, new ItemStack(Items.SLIME_BLOCK, itemStack.getCount()));
                player.awardStat(ModStats.CLEAN_SLIME_BLOCK);
                LayeredCauldronBlock.lowerFillLevel(blockState, level, blockPos);
            }
            return InteractionResult.SUCCESS;
        }
    }
}
