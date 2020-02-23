package com.hypernite.mc.hnmc.core.managers;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface PlayerSkinManager {

    /**
     * @param uuid 玩家UUID
     * @return 有皮膚頭顱
     */
    ItemStack getSkull(UUID uuid);

    /**
     * @param uuid 玩家UUID
     * @param name 玩家名稱
     * @return 有皮膚的頭顱
     */
    ItemStack getSkull(UUID uuid, String name);

    /**
     * @param b64  base64 Texture value
     * @param head 頭顱
     */
    void setSkullMeta(String b64, ItemStack head);

    /**
     * @param uuid 玩家UUID
     * @param head 頭顱
     */
    void setSkullMeta(UUID uuid, ItemStack head);

    /**
     * @param uuid 玩家UUID
     * @param name 玩家名稱
     * @param head 頭顱
     */
    void setSkullMeta(UUID uuid, String name, ItemStack head);

    /**
     * @param uuid  玩家UUID
     * @param block 方塊
     * @param wall  是否貼墻
     * @param face  頭顱方向
     */
    void setHeadBlock(UUID uuid, Block block, boolean wall, BlockFace face);

    /**
     * @param uuid   玩家UUID
     * @param player 玩家名稱
     * @param block  方塊
     * @param wall   是否貼墻
     * @param face   頭顱方向
     */
    void setHeadBlock(UUID uuid, String player, Block block, boolean wall, BlockFace face);

    /**
     * @param b64Value Base64 Texture Value
     * @param block    方塊
     * @param wall     是否貼墻
     * @param face     頭顱方向
     */
    void setHeadBlock(String b64Value, Block block, boolean wall, BlockFace face);

    /**
     * @param uuid   玩家UUID
     * @param player 玩家名稱
     * @param block  方塊
     */
    void updateHeadBlock(UUID uuid, String player, Block block);

    /**
     * @param uuid  玩家UUID
     * @param block 玩家名稱
     */
    void updateHeadBlock(UUID uuid, Block block);

    /**
     * @param newB64Value Base64 Texture Value
     * @param block       方塊
     */
    void updateHeadBlock(String newB64Value, Block block);

    /**
     * @param uuid 玩家UUID
     * @return Base64 Texture Value or IOException
     */
    CompletableFuture<String> getTextureValue(UUID uuid);

}
