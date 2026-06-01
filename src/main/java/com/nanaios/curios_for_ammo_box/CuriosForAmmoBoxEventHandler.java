package com.nanaios.curios_for_ammo_box;

import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CuriosForAmmoBox.MODID,bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CuriosForAmmoBoxEventHandler {
    public static final String CURIO_SLOT_ID = "ammo_box";

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {

    }
}
