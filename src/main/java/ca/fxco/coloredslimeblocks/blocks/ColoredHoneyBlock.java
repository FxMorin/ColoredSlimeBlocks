package ca.fxco.coloredslimeblocks.blocks;

import ca.fxco.coloredslimeblocks.base.ModItems;
import ca.fxco.coloredslimeblocks.base.ModStickyGroups;
import ca.fxco.pistonlib.api.block.BlockPistonStickiness;
import ca.fxco.pistonlib.api.pistonLogic.sticky.StickyGroup;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HoneyBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.jetbrains.annotations.Nullable;

import static ca.fxco.coloredslimeblocks.base.ModBlockStateProperties.COLOR_PROPERTY;

public class ColoredHoneyBlock extends HoneyBlock implements BlockPistonStickiness {

    public ColoredHoneyBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(COLOR_PROPERTY);
    }

    @Override
    protected ItemStack getCloneItemStack(LevelReader levelReader, BlockPos blockPos,
                                          BlockState blockState, boolean bl) {
        return ModItems.createColoredHoneyBlockStack(blockState.getValue(COLOR_PROPERTY));
    }

    @Override
    public @Nullable StickyGroup pl$getStickyGroup(BlockState state) {
        return ModStickyGroups.COLORED_HONEY[state.getValue(COLOR_PROPERTY).getId()];
    }
}
