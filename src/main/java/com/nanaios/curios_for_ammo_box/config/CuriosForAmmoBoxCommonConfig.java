package com.nanaios.curios_for_ammo_box.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class CuriosForAmmoBoxCommonConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue ENABLE_CURIO_SLOT_MODIFICATION;
    public static final ModConfigSpec.IntValue CURIO_SLOT_MODIFICATION;

    public static final ModConfigSpec SPEC;

    static {
        ENABLE_CURIO_SLOT_MODIFICATION = BUILDER
                .comment("""
                Whether to use this config to change the number of curio slots.
                When changing the number using datapack etc. with modpack etc., please set this config to false.""")
                .define("enableCurioSlotModification", false);

        CURIO_SLOT_MODIFICATION = BUILDER
                .comment("""
                        Increases or decreases the number of curio slots by the specified amount.
                        This does not set the actual number of slots, but rather represents a relative change.
                        The actual number of slots will be the default number of slots plus (or minus) the amount specified in this config.
                        Since the number of slots can be changed by other mods or datapacks, the actual number of slots may not always follow this rule.""")
                .defineInRange("curioSlotModification", 0, Integer.MIN_VALUE, Integer.MAX_VALUE);

        SPEC = BUILDER.build();
    }
}
