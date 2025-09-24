package com.nanaios.curiosable_ammo_box.util;

import com.mafuyu404.taczaddon.init.VirtualInventory;
import net.minecraftforge.fml.loading.LoadingModList;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;

public class TaczAddonConnector {
    public static @Nullable Field virtualInventoryField;
    public static boolean isLoaded() {
        if(LoadingModList.get().getModFileById("taczaddon") == null) {
            return false;
        } else {
            return  true;
        }
    }

    public static VirtualInventory getVirtualInventory(IItemHandler itemHandler) throws IllegalAccessException {
        if(virtualInventoryField != null) {
            return  (VirtualInventory) virtualInventoryField.get(itemHandler);
        }
        for (var f : itemHandler.getClass().getDeclaredFields()) {
            if (f.getType() == VirtualInventory.class) {
                virtualInventoryField = f;
                f.setAccessible(true);
                return (VirtualInventory) f.get(itemHandler);
            }
        }
        throw new IllegalAccessException("Could not access VirtualInventory field");
    }

    public static IItemHandler tryAccessVirtualInventory(IItemHandler itemHandler) throws IllegalAccessException {
        if(itemHandler instanceof VirtualInventory.ItemHandler virtualHandler) {
            VirtualInventory virtualInventory = getVirtualInventory(virtualHandler);
            return new PlayerInvWrapperWithCurios(virtualInventory);
        }
        return itemHandler;
    }
}
