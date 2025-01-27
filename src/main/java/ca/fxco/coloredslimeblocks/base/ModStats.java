package ca.fxco.coloredslimeblocks.base;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;

import static ca.fxco.coloredslimeblocks.ColoredSlimeBlocks.id;

public class ModStats {

    public static final ResourceLocation CLEAN_SLIME_BLOCK = makeCustomStat("clean_slime_block");

    private static ResourceLocation makeCustomStat(String name) {
        ResourceLocation id = id(name);
        Registry.register(BuiltInRegistries.CUSTOM_STAT, id, id);
        Stats.CUSTOM.get(id, StatFormatter.DEFAULT);
        return id;
    }

    public static void bootstrap() { }
}
