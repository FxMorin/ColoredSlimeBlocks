package ca.fxco.coloredslimeblocks.base;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BlockItemStateProperties;

import java.util.Map;
import java.util.function.Function;

import static ca.fxco.coloredslimeblocks.ColoredSlimeBlocks.id;

public class ModItems {

    public static final Item COLORED_SLIME_BLOCK = register(
            id("colored_slime_block"),
            properties -> new BlockItem(ModBlocks.COLORED_SLIME_BLOCK, properties)
    );

    public static ItemStack createColoredSlimeBlockStack(DyeColor color) {
        ItemStack stack = new ItemStack(ModItems.COLORED_SLIME_BLOCK);
        stack.applyComponents(DataComponentMap.builder()
                .set(DataComponents.ITEM_NAME, Component.translatable(
                        "block.coloredslimeblocks.colored_slime_block." + color.getName()
                ))
                .set(DataComponents.BLOCK_STATE, new BlockItemStateProperties(Map.of("color", color.getName())))
                .build());
        return stack;
    }

    private static <T extends Item> T register(ResourceLocation id, Function<Item.Properties, T> item) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, id);
        return Registry.register(BuiltInRegistries.ITEM, key, item.apply((new Item.Properties()).setId(key)));
    }

    public static void bootstrap() { }
}
