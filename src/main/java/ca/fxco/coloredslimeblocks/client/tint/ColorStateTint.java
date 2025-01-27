package ca.fxco.coloredslimeblocks.client.tint;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.util.ARGB;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import static ca.fxco.coloredslimeblocks.base.ModBlockStateProperties.COLOR_PROPERTY;
import static net.minecraft.core.component.DataComponents.BLOCK_STATE;

@Environment(EnvType.CLIENT)
public record ColorStateTint(int defaultColor) implements ItemTintSource {

    public static final MapCodec<ColorStateTint> MAP_CODEC = RecordCodecBuilder.mapCodec(
            (instance) -> instance.group(ExtraCodecs.RGB_COLOR_CODEC
                    .fieldOf("default")
                    .forGetter(ColorStateTint::defaultColor)
            ).apply(instance, ColorStateTint::new));

    @Override
    public int calculate(ItemStack itemStack, @Nullable ClientLevel clientLevel, @Nullable LivingEntity livingEntity) {
        DataComponentMap components = itemStack.getComponents();
        if (components.has(BLOCK_STATE)) {
            DyeColor color = components.get(BLOCK_STATE).get(COLOR_PROPERTY);
            return ARGB.opaque(color == null ? defaultColor : color.getTextureDiffuseColor());
        }
        return ARGB.opaque(defaultColor);
    }

    @Override
    public MapCodec<ColorStateTint> type() {
        return MAP_CODEC;
    }

    @Override
    public int defaultColor() {
        return this.defaultColor;
    }
}
