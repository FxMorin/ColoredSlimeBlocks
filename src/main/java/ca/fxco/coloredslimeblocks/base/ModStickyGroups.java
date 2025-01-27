package ca.fxco.coloredslimeblocks.base;

import ca.fxco.pistonlib.api.pistonLogic.sticky.StickRules;
import ca.fxco.pistonlib.api.pistonLogic.sticky.StickyGroup;
import ca.fxco.pistonlib.api.pistonLogic.sticky.StickyGroups;
import net.minecraft.world.item.DyeColor;

import static ca.fxco.coloredslimeblocks.ColoredSlimeBlocks.id;

public class ModStickyGroups {

    public static final StickyGroup[] COLORED_SLIME;
    public static final StickyGroup[] COLORED_HONEY;

    public static void bootstrap() { }

    static {
        DyeColor[] colors = DyeColor.values();
        COLORED_SLIME = new StickyGroup[colors.length];
        COLORED_HONEY = new StickyGroup[colors.length];
        for (int i = 0; i < colors.length; i++) {
            COLORED_SLIME[i] = StickyGroups.register(
                    id("coloredslime_" + colors[i].getName()),
                    new StickyGroup(StickRules.STRICT_SAME)
            );
            COLORED_HONEY[i] = StickyGroups.register(
                    id("coloredhoney_" + colors[i].getName()),
                    new StickyGroup(StickRules.STRICT_SAME)
            );
        }
    }
}
