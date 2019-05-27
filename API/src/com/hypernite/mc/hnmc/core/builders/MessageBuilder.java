package com.hypernite.mc.hnmc.core.builders;

import com.hypernite.mc.hnmc.core.builders.function.ChatRunner;
import com.hypernite.mc.hnmc.core.main.HyperNiteMC;
import com.hypernite.mc.hnmc.core.utils.NMSUtils;
import net.md_5.bungee.api.chat.*;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class MessageBuilder {
    private ComponentBuilder componentBuilder;

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
        for (String omsg : msgs) {
            String msg = ChatColor.translateAlternateColorCodes('&', omsg);
            componentBuilder.append(TextComponent.fromLegacyText(msg));
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
        for (String omsg : msgs) {
            String msg = ChatColor.translateAlternateColorCodes('&', omsg);
            componentBuilder.append(TextComponent.fromLegacyText(msg));
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
        for (String omsg : texts) {
            String msg = ChatColor.translateAlternateColorCodes('&', omsg);
            builder.append(msg);
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
     * @param runner 運行函式
     * @return this
     */
    public MessageBuilder run(ChatRunner runner) {
        UUID id = UUID.randomUUID();
        this.command("/command-run_" + id.toString());
        HyperNiteMC.getAPI().getChatRunnerManager().register(id, runner);
        return this;
    }

    /**
     * @param timeoutSeconds 多少秒後失效
     * @param runner         運行函式
     * @return this
     */
    public MessageBuilder run(int timeoutSeconds, ChatRunner runner) {
        UUID id = UUID.randomUUID();
        this.command("/command-run_" + id.toString());
        HyperNiteMC.getAPI().getChatRunnerManager().register(id, runner, timeoutSeconds);
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
        return componentBuilder.create();
    }

    public void sendPlayer(Player player) {
        player.sendMessage(componentBuilder.create());
    }


}
//如何使用
/*
class MessageBuilderUse{
    void use(Player sender){
        BaseComponent[] msg = new MessageBuilder("&e[ 公告全世界你是女裝大佬 ]").hoverText("&a來啊點我啊").run(player -> {
            Bukkit.broadcastMessage(net.md_5.bungee.api.ChatColor.AQUA + player.getName()+" 是女裝大佬！！");
            BaseComponent[] tp = new MessageBuilder("&e[ 點我傳送到「她」的位置 ]").hoverText("&a點啊快點啊").run(player1 -> {
                player1.teleport(player);
            }).build();
            Bukkit.getOnlinePlayers().forEach(p->p.sendMessage(tp));
        }).build();
        sender.sendMessage(msg);
    }
}

 */

