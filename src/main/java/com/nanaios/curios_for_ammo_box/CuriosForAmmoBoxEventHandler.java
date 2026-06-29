package com.nanaios.curios_for_ammo_box;

import com.nanaios.curios_for_ammo_box.config.CuriosForAmmoBoxCommonConfig;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;

@EventBusSubscriber(modid = CuriosForAmmoBox.MODID)
public class CuriosForAmmoBoxEventHandler {
    private static final String CURIO_SLOT_ID = "ammo_box";
    private static final ResourceLocation ATTRIBUTE_MODIFIER_ID = ResourceLocation.fromNamespaceAndPath(CuriosForAmmoBox.MODID, "curio_slot_modifier");

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        if (player.level().isClientSide) return;

        CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
            ICurioStacksHandler stacksHandler = handler.getCurios().get(CURIO_SLOT_ID);
            stacksHandler.removeModifier(ATTRIBUTE_MODIFIER_ID);

            if (!CuriosForAmmoBoxCommonConfig.ENABLE_CURIO_SLOT_MODIFICATION.get()) return;

            int targetSlotCount = CuriosForAmmoBoxCommonConfig.CURIO_SLOT_MODIFICATION.get();
            AttributeModifier modifier = new AttributeModifier(ATTRIBUTE_MODIFIER_ID, targetSlotCount, AttributeModifier.Operation.ADD_VALUE);
            stacksHandler.addPermanentModifier(modifier);
        });
    }
}
