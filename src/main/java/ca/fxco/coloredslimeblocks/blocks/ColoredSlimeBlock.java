package ca.fxco.coloredslimeblocks.blocks;

import ca.fxco.coloredslimeblocks.base.ModItems;
import ca.fxco.coloredslimeblocks.base.ModStickyGroups;
import ca.fxco.pistonlib.api.block.BlockPistonStickiness;
import ca.fxco.pistonlib.api.pistonLogic.sticky.StickyGroup;
import ca.fxco.pistonlib.api.pistonLogic.sticky.StickyType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlimeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

import static ca.fxco.coloredslimeblocks.base.ModBlockStateProperties.COLOR_PROPERTY;

public class ColoredSlimeBlock extends SlimeBlock implements BlockPistonStickiness {

    public ColoredSlimeBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(COLOR_PROPERTY);
    }

    @Override
    protected ItemStack getCloneItemStack(LevelReader levelReader, BlockPos blockPos,
                                          BlockState blockState, boolean bl) {
        DyeColor color = blockState.getValue(COLOR_PROPERTY);
        return ModItems.createColoredSlimeBlockStack(color);
    }

    @Override
    public @Nullable StickyGroup pl$getStickyGroup(BlockState state) {
        return ModStickyGroups.COLORED_SLIME[state.getValue(COLOR_PROPERTY).getId()];
    }

    // TODO: Remove the need to set these methods in PistonLib

    @Override
    public boolean pl$usesConfigurablePistonStickiness() {
        return false;
    }

    @Override
    public boolean pl$isSticky(BlockState blockState) {
        return false;
    }

    @Override
    public Map<Direction, StickyType> pl$stickySides(BlockState blockState) {
        return Map.of();
    }

    @Override
    public StickyType pl$sideStickiness(BlockState blockState, Direction direction) {
        return null;
    }

    @Override
    public boolean pl$matchesStickyConditions(BlockState blockState, BlockState blockState1, Direction direction) {
        return false;
    }
}
