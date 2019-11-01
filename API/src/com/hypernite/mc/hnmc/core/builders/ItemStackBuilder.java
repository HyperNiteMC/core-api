package com.hypernite.mc.hnmc.core.builders;

import com.comphenix.protocol.utility.MinecraftReflection;
import com.comphenix.protocol.wrappers.nbt.NbtCompound;
import com.comphenix.protocol.wrappers.nbt.NbtFactory;
import com.comphenix.protocol.wrappers.nbt.NbtWrapper;
import com.hypernite.mc.hnmc.core.main.HyperNiteMC;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ItemStackBuilder {
    private String onClickId;
    private String onInteractId;
    private ItemStack item;
    private Consumer<InventoryClickEvent> clickAction;
    private Consumer<PlayerInteractEvent> interactAction;

    /**
     * @param m 物品類型
     */
    public ItemStackBuilder(Material m) {
        item = new ItemStack(m);
    }

    public ItemStackBuilder() {
        item = new ItemStack(Material.STONE);
    }

    /**
     * @param item 現有物品
     */
    public ItemStackBuilder(ItemStack item) {
        this.item = item;
    }

    /**
     * @param m 物品類型
     * @return this
     */
    public ItemStackBuilder material(Material m) {
        item.setType(m);
        return this;
    }

    /**
     * @param dur 設置耐久度
     * @return this
     */
    public ItemStackBuilder durability(int dur) {
        ItemMeta meta = item.getItemMeta();
        Damageable damageable = (Damageable) meta;
        damageable.setDamage(dur);
        item.setItemMeta(meta);
        return this;
    }

    /**
     * @param enchantment 附魔
     * @param level       等級
     * @return this
     */
    public ItemStackBuilder enchant(Enchantment enchantment, int level) {
        item.addEnchantment(enchantment, level);
        return this;
    }

    /**
     * @param enchantmentMap 附魔 Map
     * @return this
     */
    public ItemStackBuilder enchant(Map<Enchantment, Integer> enchantmentMap) {
        item.addEnchantments(enchantmentMap);
        return this;
    }

    /**
     * @param inventorySupplier 背包界面
     * @return this
     */
    public ItemStackBuilder openGui(Supplier<Inventory> inventorySupplier) {
        this.onClick(e -> HyperNiteMC.getAPI().getCoreSchelder().runTask(() -> e.getWhoClicked().openInventory(inventorySupplier.get())));
        return this;
    }

    /**
     * @param s 數量
     * @return this
     */
    public ItemStackBuilder stack(int s) {
        item.setAmount(s);
        return this;
    }

    /**
     * @param name 顯示名稱
     * @return this
     */
    public ItemStackBuilder displayName(String name) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        item.setItemMeta(meta);
        return this;
    }

    /**
     * @param lore 敘述
     * @return this
     */
    public ItemStackBuilder lore(String lore) {
        ItemMeta meta = item.getItemMeta();
        List<String> loreList = meta.getLore();
        if (loreList == null) loreList = new ArrayList<>();
        loreList.add(ChatColor.translateAlternateColorCodes('&', lore));
        meta.setLore(loreList);
        item.setItemMeta(meta);
        return this;
    }

    /**
     * @param lore 敘述
     * @return this
     */
    public ItemStackBuilder lore(List<String> lore) {
        ItemMeta meta = item.getItemMeta();
        // lore = lore.stream().map(s -> ChatColor.translateAlternateColorCodes('&', s)).collect(Collectors.toList());
        for (int i = 0; i < lore.size(); i++) {
            lore.set(i, ChatColor.translateAlternateColorCodes('&', lore.get(i)));
        }

        meta.setLore(lore);
        item.setItemMeta(meta);
        return this;
    }

    /**
     * @param action 點擊事件
     * @return this
     */
    public ItemStackBuilder onClick(Consumer<InventoryClickEvent> action) {
        this.onClickId = UUID.randomUUID().toString();
        this.clickAction = action;
        return this;
    }

    /**
     * @param action 物品交互事件
     * @return this
     */
    public ItemStackBuilder onInteract(Consumer<PlayerInteractEvent> action) {
        this.onInteractId = UUID.randomUUID().toString();
        this.interactAction = action;
        return this;
    }

    /**
     * 設置頭顱皮膚
     *
     * @param uuid 玩家UUID
     * @return this
     */
    public ItemStackBuilder head(UUID uuid) {
        if (this.item.getType() != Material.PLAYER_HEAD && this.item.getType() != Material.PLAYER_WALL_HEAD) {
            throw new IllegalStateException("Cannot set the head skin in " + this.item.getType().toString());
        }
        HyperNiteMC.getAPI().getPlayerSkinManager().setSkullMeta(uuid, this.item);
        return this;
    }

    /**
     * 設置頭顱皮膚
     *
     * @param uuid   玩家 UUID
     * @param player 玩家名稱
     * @return this
     */
    public ItemStackBuilder head(UUID uuid, String player) {
        if (this.item.getType() != Material.PLAYER_HEAD && this.item.getType() != Material.PLAYER_WALL_HEAD) {
            throw new IllegalStateException("Cannot set the head skin in " + this.item.getType().toString());
        }
        HyperNiteMC.getAPI().getPlayerSkinManager().setSkullMeta(uuid, player, this.item);
        return this;
    }

    /**
     * @return 物品
     */
    public ItemStack build() {
        ItemStack itemStack = MinecraftReflection.getBukkitItemStack(item);
        if (item == null || item.getType() == Material.AIR) return itemStack;
        Optional<NbtWrapper<?>> wrapper = NbtFactory.fromItemOptional(itemStack);
        if (wrapper.isEmpty()) return itemStack;
        NbtCompound compound = NbtFactory.asCompound(wrapper.get());
        if (clickAction != null) compound.put("onClick", onClickId);
        if (interactAction != null) compound.put("onInteract", onInteractId);
        NbtFactory.setItemTag(itemStack, compound);
        if (clickAction != null) HyperNiteMC.getAPI().getItemEventManager().registerItem(onClickId, clickAction);
        if (interactAction != null)
            HyperNiteMC.getAPI().getItemEventManager().registerItem(onInteractId, interactAction);
        return itemStack;
    }
}