package com.hypernite.mc.hnmc.core.managers;

import com.hypernite.mc.hnmc.core.misc.world.WorldNonExistException;
import com.hypernite.mc.hnmc.core.misc.world.WorldProperties;
import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldType;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.generator.ChunkGenerator;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * 用於取代 Multiverse-Core
 */
public interface WorldManager {

    /**
     * @param world Bukkit世界
     * @return 世界設定
     */
    WorldProperties getWorldProperties(World world);

    /**
     * @param name 世界名稱
     * @return yaml 資料
     * @throws WorldNonExistException 找不到世界
     */
    FileConfiguration getWorldFile(String name) throws WorldNonExistException;

    /**
     * @param world       世界
     * @param type        類型
     * @param environment 環境
     * @return 加載是否成功
     */
    CompletableFuture<Boolean> futureCreateWorld(@Nonnull String world, WorldType type, World.Environment environment);

    /**
     * @param world              世界
     * @param type               類型
     * @param environment        環境
     * @param generateStructures 生成建築
     * @return 加載是否成功
     */
    CompletableFuture<Boolean> futureCreateWorld(@Nonnull String world, WorldType type, World.Environment environment, boolean generateStructures);

    /**
     * @param world              世界
     * @param environment        環境
     * @param generator          Chunk 生成器
     * @param generateStructures 生成建築
     * @param type               地形
     * @param seed               種子碼
     * @return 加載是否成功
     */
    CompletableFuture<Boolean> futureCreateWorld(@Nonnull String world, World.Environment environment, ChunkGenerator generator, boolean generateStructures, WorldType type, long seed);

    /**
     * @param world 世界名稱
     * @return 加載是否成功
     */
    CompletableFuture<Boolean> futureLoadWorld(@Nonnull String world);

    /**
     * @param world  世界
     * @param sender 指令發送者
     * @throws WorldNonExistException 世界不存在
     */
    void loadWorld(@Nonnull String world, CommandSender sender) throws WorldNonExistException;

    /**
     * 保存所有世界設定
     */
    void saveAll();

    /**
     * @param world 世界名稱
     * @return 是否成功
     */
    boolean unloadWorld(@Nonnull String world);

    /**
     * @param world 世界名稱
     * @return 是否成功
     * @throws WorldNonExistException 世界不存在
     */
    boolean deleteWorld(String world) throws WorldNonExistException;

    /**
     * @param world 世界名稱
     * @return 出生點
     */
    Location getWorldSpawn(String world);

    /**
     * @param world 世界名稱
     * @return 是否成功
     * @throws WorldNonExistException 世界不存在
     */
    boolean disableWorld(String world) throws WorldNonExistException;

    /**
     * @param world 世界
     * @return 是否被禁用
     * @throws WorldNonExistException 世界不存在
     */
    boolean isNotAutoLoad(String world) throws WorldNonExistException;

    /**
     * @param world  實際
     * @param sender 指令發送者
     * @return 是否成功
     * @throws WorldNonExistException 世界不存在
     */
    boolean enableWorld(String world, CommandSender sender) throws WorldNonExistException;

    /**
     * @return 帶有前綴和顏色辨別的世界列表
     */
    String[] listWorlds();

    /**
     * @return 純世界列表
     */
    List<String> listRealWorlds();

    /**
     * @param world 世界名稱
     * @param name  Chunk 生成器 Id
     * @return Chunk 生成器
     */
    Optional<ChunkGenerator> getChunkGenerator(String world, String name);

    /**
     * @param rules 遊戲規則
     * @param world 世界
     * @param <T>   設置類型, boolean 和 int 等等
     */
    <T> void applyGameRules(Map<GameRule<T>, T> rules, @Nonnull World world);

}
