package ca.fxco.coloredslimeblocks.client;

import ca.fxco.coloredslimeblocks.base.ModBlocks;
import ca.fxco.coloredslimeblocks.client.tint.ColorStateTint;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.item.ItemTintSources;
import net.minecraft.client.renderer.RenderType;

import static ca.fxco.coloredslimeblocks.ColoredSlimeBlocks.id;
import static ca.fxco.coloredslimeblocks.base.ModBlockStateProperties.COLOR_PROPERTY;

public class ColoredSlimeBlocksClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        // Make sure colored slime block is translucent
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.translucent(), ModBlocks.COLORED_SLIME_BLOCK);

        // Dynamically tint the colored slime block
        ColorProviderRegistry.BLOCK.register(
                (state, v, p, t) -> state.getValue(COLOR_PROPERTY).getTextureDiffuseColor(),
                ModBlocks.COLORED_SLIME_BLOCK
        );

        // Dynamically tint the colored slime block item
        ItemTintSources.ID_MAPPER.put(id("color_state"), ColorStateTint.MAP_CODEC);
    }
}
