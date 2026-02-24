package com.nanaios.curios_for_ammo_box.util;

import com.google.common.collect.Multimap;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.neoforged.neoforge.items.IItemHandlerModifiable;
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
    @Override
    public Map<String, ICurioStacksHandler> getCurios() {
        return Map.of();
    }

    @Override
    public void setCurios(Map<String, ICurioStacksHandler> map) {

    }

    @Override
    public int getSlots() {
        return 0;
    }

    @Override
    public void reset() {

    }

    @Override
    public Optional<ICurioStacksHandler> getStacksHandler(String identifier) {
        return Optional.empty();
    }

    @Override
    public IItemHandlerModifiable getEquippedCurios() {
        return null;
    }

    @Override
    public void setEquippedCurio(String identifier, int index, ItemStack stack) {

    }

    @Override
    public Optional<SlotResult> findFirstCurio(Item item) {
        return Optional.empty();
    }

    @Override
    public Optional<SlotResult> findFirstCurio(Predicate<ItemStack> filter) {
        return Optional.empty();
    }

    @Override
    public Optional<SlotResult> findFirstCurio(Predicate<ItemStack> filter, String cacheKey) {
        return Optional.empty();
    }

    @Override
    public List<SlotResult> findCurios(Item item) {
        return List.of();
    }

    @Override
    public List<SlotResult> findCurios(Predicate<ItemStack> filter) {
        return List.of();
    }

    @Override
    public List<SlotResult> findCurios(String... identifiers) {
        return List.of();
    }

    @Override
    public Optional<SlotResult> findCurio(String identifier, int index) {
        return Optional.empty();
    }

    @Override
    public LivingEntity getWearer() {
        return null;
    }

    @Override
    public void loseInvalidStack(ItemStack stack) {

    }

    @Override
    public void handleInvalidStacks() {

    }

    @Override
    public int getFortuneLevel(@Nullable LootContext lootContext) {
        return 0;
    }

    @Override
    public int getLootingLevel(@Nullable LootContext lootContext) {
        return 0;
    }

    @Override
    public ListTag saveInventory(boolean clear) {
        return null;
    }

    @Override
    public void loadInventory(ListTag data) {

    }

    @Override
    public Set<ICurioStacksHandler> getUpdatingInventories() {
        return Set.of();
    }

    @Override
    public void addTransientSlotModifiers(Multimap<String, AttributeModifier> modifiers) {

    }

    @Override
    public void addPermanentSlotModifiers(Multimap<String, AttributeModifier> modifiers) {

    }

    @Override
    public void removeSlotModifiers(Multimap<String, AttributeModifier> modifiers) {

    }

    @Override
    public void clearSlotModifiers() {

    }

    @Override
    public Multimap<String, AttributeModifier> getModifiers() {
        return null;
    }

    @Override
    public Tag writeTag() {
        return null;
    }

    @Override
    public void readTag(Tag tag) {

    }

    @Override
    public void clearCachedSlotModifiers() {

    }

    @Override
    @SuppressWarnings("removal")
    public void growSlotType(String identifier, int amount) {

    }

    @Override
    @SuppressWarnings("removal")
    public void shrinkSlotType(String identifier, int amount) {

    }
}
