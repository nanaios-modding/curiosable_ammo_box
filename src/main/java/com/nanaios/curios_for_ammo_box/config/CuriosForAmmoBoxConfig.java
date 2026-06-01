package com.nanaios.curios_for_ammo_box.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CuriosForAmmoBoxConfig {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.BooleanValue ENABLE_CURIO_SLOT_COUNT_MODIFICATION;
    public static final ForgeConfigSpec.IntValue CURIO_SLOT_COUNT;

    public static final ForgeConfigSpec SPEC;

    static {
        ENABLE_CURIO_SLOT_COUNT_MODIFICATION = BUILDER
                .comment("Whether to use this config to change the number of curio slots. When changing the number using datapack etc. with modpack etc., please set this config to false.")
                .define("enableCurioSlotCountModification", true);

        CURIO_SLOT_COUNT = BUILDER
                .comment("The number of curio slots. This config is only effective when the above config is set to true.")
                .defineInRange("curioSlotCount", 4, 0, Integer.MAX_VALUE);

        SPEC = BUILDER.build();
    }
}
