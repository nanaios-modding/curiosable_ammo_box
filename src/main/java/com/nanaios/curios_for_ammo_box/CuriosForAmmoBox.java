package com.nanaios.curios_for_ammo_box;

import com.nanaios.curios_for_ammo_box.config.CuriosForAmmoBoxCommonConfig;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.javafmlmod.FMLModContainer;
import net.neoforged.fml.common.Mod;

@Mod(CuriosForAmmoBox.MODID)
public class CuriosForAmmoBox {
    public static final String MODID = "curios_for_ammo_box";
    public CuriosForAmmoBox(FMLModContainer container) {
        container.registerConfig(ModConfig.Type.COMMON, CuriosForAmmoBoxCommonConfig.SPEC);
    }
}
