package com.hypernite.mc.hnmc.core.builders;

import com.hypernite.mc.hnmc.core.main.HyperNiteMC;
import com.hypernite.mc.hnmc.core.utils.NMSUtils;
import net.md_5.bungee.api.chat.*;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;
import java.util.function.Consumer;

public class MessageBuilder {
    private ComponentBuilder componentBuilder;
    private UUID id;
    private Consumer<Player> runner;
    private int timeoutSeconds = -1;
    private int timeoutClicks = -1;

    /**
     * @param msg 原始訊息
     */
    public MessageBuilder(String msg) {
        String amsg = ChatColor.translateAlternateColorCodes('&', msg);
        componentBuilder = new ComponentBuilder(amsg);
    }

    public MessageBuilder() {
        componentBuilder = new ComponentBuilder("");
    }


    /**
     * @param msgs 訊息串
     */
    public MessageBuilder(String... msgs) {
        componentBuilder = new ComponentBuilder("");
        for (int i = 0; i < msgs.length; i++) {
            String msg = ChatColor.translateAlternateColorCodes('&', msgs[i]);
            componentBuilder.append(TextComponent.fromLegacyText(msg));
            if (i != msgs.length - 1) {
                componentBuilder.append("\n");
            }
        }
    }

    /**
     * @param msg 訊息
     * @return this
     */
    public MessageBuilder add(String msg) {
        String amsg = ChatColor.translateAlternateColorCodes('&', msg);
        componentBuilder.append(TextComponent.fromLegacyText(amsg));
        return this;
    }

    /**
     * @param msgs 訊息串
     * @return this
     */
    public MessageBuilder add(String... msgs) {
        for (int i = 0; i < msgs.length; i++) {
            String msg = ChatColor.translateAlternateColorCodes('&', msgs[i]);
            componentBuilder.append(TextComponent.fromLegacyText(msg));
            if (i != msgs.length - 1) {
                componentBuilder.append("\n");
            }
        }
        return this;
    }

    /**
     * @param website 可點擊連結
     * @return this
     */
    public MessageBuilder url(String website) {
        componentBuilder.event(new ClickEvent(ClickEvent.Action.OPEN_URL, website));
        return this;
    }

    /**
     * @param command 指令輸入
     * @return this
     */
    public MessageBuilder suggest(String command) {
        componentBuilder.event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, command));
        return this;
    }

    /**
     * @param command 指令執行
     * @return this
     */
    public MessageBuilder command(String command) {
        componentBuilder.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
        return this;
    }

    /**
     * @param page 頁數
     * @return this
     */
    public MessageBuilder page(String page) {
        componentBuilder.event(new ClickEvent(ClickEvent.Action.CHANGE_PAGE, page));
        return this;
    }

    /**
     * @param texts 文字氣泡
     * @return this
     */
    public MessageBuilder hoverText(String... texts) {
        ComponentBuilder builder = new ComponentBuilder("");
        for (int i = 0; i < texts.length; i++) {
            String msg = ChatColor.translateAlternateColorCodes('&', texts[i]);
            builder.append(msg);
            if (i != texts.length - 1) {
                builder.append("\n");
            }
        }
        componentBuilder.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, builder.create()));
        return this;
    }

    /**
     * 顯示物品資訊
     *
     * @param item 物品
     * @return this
     */
    public MessageBuilder showItem(ItemStack item) {
        String json = NMSUtils.convertItemStackToJson(item);
        if (json == null) return this;
        componentBuilder.event(new HoverEvent(HoverEvent.Action.SHOW_ITEM, TextComponent.fromLegacyText(json)));
        return this;
    }

    /**
     * 顯示實體資訊
     *
     * @param entity 實體
     * @return this
     */
    public MessageBuilder showEntity(Entity entity) {
        String json = NMSUtils.convertEntityToJson(entity);
        componentBuilder.event(new HoverEvent(HoverEvent.Action.SHOW_ENTITY, TextComponent.fromLegacyText(json)));
        return this;
    }

    /**
     * 顯示成就資訊
     *
     * @param achievementNode 成就節點
     * @return this
     */
    public MessageBuilder showAdvancement(String achievementNode) {
        String value = "\"value\":" + achievementNode;
        componentBuilder.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(value)));
        return this;
    }

    /**
     * @param insert 訊息
     * @return this
     */
    public MessageBuilder insertWhenShiftClick(String insert) {
        componentBuilder.insertion(insert);
        return this;
    }

    /**
     * 默認為 十分鐘 之後自動過期
     *
     * @param runner 運行函式
     * @return this
     */
    public MessageBuilder run(Consumer<Player> runner) {
        this.id = UUID.randomUUID();
        this.runner = runner;
        this.timeoutSeconds = 600;
        this.timeoutClicks = -1;
        return this;
    }

    /**
     * @param timeoutClicks 點擊多少次後自動過期
     * @param runner 運行函式
     * @return this
     */
    public MessageBuilder runClicks(int timeoutClicks, Consumer<Player> runner) {
        this.id = UUID.randomUUID();
        this.runner = runner;
        this.timeoutClicks = timeoutClicks;
        this.timeoutSeconds = -1;
        return this;
    }

    /**
     * @param timeoutSeconds 多少秒後失效
     * @param runner         運行函式
     * @return this
     */
    public MessageBuilder runTimeout(int timeoutSeconds, Consumer<Player> runner) {
        this.id = UUID.randomUUID();
        this.runner = runner;
        this.timeoutSeconds = timeoutSeconds;
        this.timeoutClicks = -1;
        return this;
    }

    /**
     * 隔行
     *
     * @return this
     */
    public MessageBuilder nextLine() {
        componentBuilder.append("\n");
        return this;
    }

    /**
     * @return 訊息
     */
    public BaseComponent[] build() {
        if (this.runner != null) {
            this.command("/command-run_" + id.toString());
            if (timeoutSeconds > 0) {
                HyperNiteMC.getAPI().getChatRunnerManager().registerTimeout(id, runner, timeoutSeconds);
            } else if (timeoutClicks > 0) {
                HyperNiteMC.getAPI().getChatRunnerManager().registerClicks(id, runner, timeoutClicks);
            }
        }
        return componentBuilder.create();
    }

    public void sendPlayer(Player player) {
        player.sendMessage(this.build());
    }


}

