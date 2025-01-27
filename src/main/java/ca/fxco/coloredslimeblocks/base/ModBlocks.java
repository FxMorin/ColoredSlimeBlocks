package ca.fxco.coloredslimeblocks.base;

import ca.fxco.coloredslimeblocks.blocks.ColoredSlimeBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;

import static ca.fxco.coloredslimeblocks.ColoredSlimeBlocks.id;

public class ModBlocks {

    public static final Block COLORED_SLIME_BLOCK = register(
            "colored_slime_block",
            ColoredSlimeBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.SLIME_BLOCK)
    );

    private static <T extends Block> T register(String name, Function<BlockBehaviour.Properties, T> function,
                                                BlockBehaviour.Properties properties) {
        ResourceKey<Block> resourceKey = ResourceKey.create(Registries.BLOCK, id(name));
        return Registry.register(BuiltInRegistries.BLOCK, resourceKey, function.apply(properties.setId(resourceKey)));
    }

    public static void bootstrap() { }
}
