package com.nanaios.curios_for_ammo_box;

import com.nanaios.curios_for_ammo_box.config.CuriosForAmmoBoxConfig;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(CuriosForAmmoBox.MODID)
public class CuriosForAmmoBox {
    public static final String MODID = "curios_for_ammo_box";
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public CuriosForAmmoBox(FMLJavaModLoadingContext context) {
        context.registerConfig(ModConfig.Type.SERVER, CuriosForAmmoBoxConfig.SPEC);
    }
}
