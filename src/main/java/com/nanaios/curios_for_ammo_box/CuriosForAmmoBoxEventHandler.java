package com.nanaios.curios_for_ammo_box;

import com.nanaios.curios_for_ammo_box.config.CuriosForAmmoBoxCommonConfig;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;

@Mod.EventBusSubscriber(modid = CuriosForAmmoBox.MODID,bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CuriosForAmmoBoxEventHandler {
    public static final String CURIO_SLOT_ID = "ammo_box";

    @SuppressWarnings({"removal", "UnstableApiUsage"})
    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if(!CuriosForAmmoBoxCommonConfig.ENABLE_CURIO_SLOT_COUNT_MODIFICATION.get()) return;

        Player player = event.getEntity();
        if (player.level().isClientSide) return;

        int slotCount = CuriosForAmmoBoxCommonConfig.CURIO_SLOT_COUNT.get();

        CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
            ICurioStacksHandler stacksHandler = handler.getCurios().get(CURIO_SLOT_ID);
            int modification = slotCount - stacksHandler.getSlots();

            CuriosForAmmoBox.LOGGER.debug("player[{}]'s slot modification = {}", player.getName(), modification);
            if (modification > 0) {
                handler.growSlotType(CURIO_SLOT_ID, modification);
            } else if (modification < 0) {
                handler.shrinkSlotType(CURIO_SLOT_ID, -modification);
            }
        });
    }
}
