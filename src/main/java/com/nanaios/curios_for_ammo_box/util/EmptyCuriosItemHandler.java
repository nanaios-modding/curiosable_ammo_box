package com.nanaios.curios_for_ammo_box.util;

import com.google.common.collect.Multimap;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotResult;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

public class EmptyCuriosItemHandler implements ICuriosItemHandler {

    public Map<String, ICurioStacksHandler> getCurios() {
        return Map.of();
    }

    public void setCurios(Map<String, ICurioStacksHandler> map) {

    }

    public int getSlots() {
        return 0;
    }

    public void reset() {

    }

    public Optional<ICurioStacksHandler> getStacksHandler(String identifier) {
        return Optional.empty();
    }

    public IItemHandlerModifiable getEquippedCurios() {
        return null;
    }

    public void setEquippedCurio(String identifier, int index, ItemStack stack) {

    }

    public Optional<SlotResult> findFirstCurio(Item item) {
        return Optional.empty();
    }

    public Optional<SlotResult> findFirstCurio(Predicate<ItemStack> filter) {
        return Optional.empty();
    }

    public List<SlotResult> findCurios(Item item) {
        return List.of();
    }

    public List<SlotResult> findCurios(Predicate<ItemStack> filter) {
        return List.of();
    }

    public List<SlotResult> findCurios(String... identifiers) {
        return List.of();
    }

    public Optional<SlotResult> findCurio(String identifier, int index) {
        return Optional.empty();
    }

    public LivingEntity getWearer() {
        return null;
    }

    public void loseInvalidStack(ItemStack stack) {

    }

    public void handleInvalidStacks() {

    }

    public int getFortuneLevel(@Nullable LootContext lootContext) {
        return 0;
    }

    public int getLootingLevel(DamageSource source, LivingEntity target, int baseLooting) {
        return 0;
    }

    public ListTag saveInventory(boolean clear) {
        return null;
    }

    public void loadInventory(ListTag data) {

    }

    public Set<ICurioStacksHandler> getUpdatingInventories() {
        return Set.of();
    }

    public void addTransientSlotModifiers(Multimap<String, AttributeModifier> modifiers) {

    }

    public void addPermanentSlotModifiers(Multimap<String, AttributeModifier> modifiers) {

    }

    public void removeSlotModifiers(Multimap<String, AttributeModifier> modifiers) {

    }

    public void clearSlotModifiers() {

    }

    public Multimap<String, AttributeModifier> getModifiers() {
        return null;
    }

    public Tag writeTag() {
        return null;
    }

    public void readTag(Tag tag) {

    }

    public void clearCachedSlotModifiers() {

    }

    @SuppressWarnings("removal")
    @Override
    public void growSlotType(String identifier, int amount) {

    }

    @SuppressWarnings("removal")
    @Override
    public void shrinkSlotType(String identifier, int amount) {

    }
}
