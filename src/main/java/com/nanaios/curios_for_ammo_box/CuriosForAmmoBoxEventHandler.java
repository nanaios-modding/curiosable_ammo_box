package com.nanaios.curios_for_ammo_box;

import com.nanaios.curios_for_ammo_box.config.CuriosForAmmoBoxCommonConfig;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;

import java.util.UUID;

@Mod.EventBusSubscriber(modid = CuriosForAmmoBox.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CuriosForAmmoBoxEventHandler {
    private static final String CURIO_SLOT_ID = "ammo_box";
    private static final String ATTRIBUTE_MODIFIER_NAME = "CuriosForAmmoBoxSlotModifier";
    private static final UUID ATTRIBUTE_MODIFIER_UUID = UUID.fromString("256d9f52-7c7f-4d1b-bdcd-cea2e4534d38");

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        if (player.level().isClientSide) return;

        CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
            ICurioStacksHandler stacksHandler = handler.getCurios().get(CURIO_SLOT_ID);
            stacksHandler.removeModifier(ATTRIBUTE_MODIFIER_UUID);

            if (!CuriosForAmmoBoxCommonConfig.ENABLE_CURIO_SLOT_MODIFICATION.get()) return;

            int targetSlotCount = CuriosForAmmoBoxCommonConfig.CURIO_SLOT_MODIFICATION.get();
            AttributeModifier modifier = new AttributeModifier(ATTRIBUTE_MODIFIER_UUID, ATTRIBUTE_MODIFIER_NAME, targetSlotCount, AttributeModifier.Operation.ADDITION);
            stacksHandler.addPermanentModifier(modifier);
        });
    }
}
