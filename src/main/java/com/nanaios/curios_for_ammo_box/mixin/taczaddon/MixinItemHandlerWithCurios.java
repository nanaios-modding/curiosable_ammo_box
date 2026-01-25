package com.nanaios.curios_for_ammo_box.mixin.taczaddon;

import com.mafuyu404.taczaddon.init.VirtualInventory;
import com.nanaios.curios_for_ammo_box.util.ItemHandlerWithCurios;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.items.IItemHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value= ItemHandlerWithCurios.class,remap = false)
public class MixinItemHandlerWithCurios {
    @Inject(method = "create",at = @At("HEAD"),cancellable = true)
    private static void mixinCreate(IItemHandler itemHandler, CallbackInfoReturnable<IItemHandler> cir) {
        if(itemHandler instanceof VirtualInventory.ItemHandler handler) {
            Inventory inventory = ((MixinVirtualInventory$ItemHandler) handler).mixinCfAB$getVirtualInventory();
            cir.setReturnValue(new ItemHandlerWithCurios(itemHandler,inventory.player));
        }
    }
}
