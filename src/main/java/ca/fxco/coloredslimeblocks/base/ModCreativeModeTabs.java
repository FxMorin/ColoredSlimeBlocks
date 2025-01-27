package ca.fxco.coloredslimeblocks.base;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;

import static ca.fxco.coloredslimeblocks.ColoredSlimeBlocks.id;

public class ModCreativeModeTabs {

    public static final ResourceKey<CreativeModeTab> GENERAL = ResourceKey.create(
            Registries.CREATIVE_MODE_TAB, id("general")
    );

    public static void bootstrap() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, GENERAL, FabricItemGroup.builder()
                .icon(() -> new ItemStack(ModItems.COLORED_SLIME_BLOCK))
                .title(Component.translatable("itemGroup.coloredslimeblocks.general"))
                .displayItems((displayParameters, output) -> {
                    for (DyeColor color : DyeColor.values()) {
                        if (color != DyeColor.WHITE) { // Don't show the white version
                            output.accept(ModItems.createColoredSlimeBlockStack(color));
                        }
                    }
                    for (DyeColor color : DyeColor.values()) {
                        if (color != DyeColor.WHITE) { // Don't show the white version
                            output.accept(ModItems.createColoredHoneyBlockStack(color));
                        }
                    }
                })
                .build());
    }

}
