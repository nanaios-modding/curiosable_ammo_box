package com.nanaios.curiosable_ammo_box.client;

import com.nanaios.curiosable_ammo_box.CuriosableAmmoBox;
import com.nanaios.curiosable_ammo_box.client.renderer.CuriosableAmmoBoxCurioRenderer;
import com.tacz.guns.init.ModItems;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT, modid = CuriosableAmmoBox.MODID)
public class CuriosableAmmoBoxClient {
    public CuriosableAmmoBoxClient(final IEventBus eventBus) {
    }

    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent evt) {
        CuriosableAmmoBox.LOGGER.info("Rendering Ammo Box Curio");
        CuriosRendererRegistry.register(ModItems.AMMO_BOX.get(), CuriosableAmmoBoxCurioRenderer::new);
    }
}
