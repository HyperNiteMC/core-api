package com.hypernite.mc.hnmc.core.misc.commands;

import com.hypernite.mc.hnmc.core.misc.commands.exception.NotExecutableException;
import com.hypernite.mc.hnmc.core.misc.commands.exception.NotTabCompletableException;
import com.hypernite.mc.hnmc.core.misc.commands.functional.CmdExecution;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * 指令節點建造器
 *
 * @author Eric Lam
 * @see CommandNode
 */
public class CommandNodeBuilder {

    private String command;
    private String permission;
    private String description;
    private String placeholder;
    private CommandNode parent;
    private CmdExecution<List<String>> tabCompleter;
    private CmdExecution<Boolean> cmdExecutor;

    /**
     * @param command 指令
     */
    public CommandNodeBuilder(String command) {
        this.command = command;
    }

    /**
     * @param permission 權限
     * @return this
     */
    public CommandNodeBuilder permission(String permission) {
        this.permission = permission;
        return this;
    }

    /**
     * @param description 介紹
     * @return this
     */
    public CommandNodeBuilder description(String description) {
        this.description = description;
        return this;
    }

    /**
     * @param placeholder 用法
     * @return this
     */
    public CommandNodeBuilder placeholder(String placeholder) {
        this.placeholder = placeholder;
        return this;
    }

    /**
     * @param parent 父類指令
     * @return this
     */
    public CommandNodeBuilder parent(CommandNode parent) {
        this.parent = parent;
        return this;
    }

    /**
     * @param executor tab 執行
     * @return this
     */
    public CommandNodeBuilder tabComplete(CmdExecution<List<String>> executor) {
        this.tabCompleter = executor;
        return this;
    }

    /**
     * @param cmdExecutor 指令執行
     * @return this
     */
    public CommandNodeBuilder execute(CmdExecution<Boolean> cmdExecutor) {
        this.cmdExecutor = cmdExecutor;
        return this;
    }

    /**
     * @return 指令節點
     * @throws NotExecutableException     指令無法執行
     * @throws NotTabCompletableException 沒有返回 Tab 列
     */
    public CommandNode build() {
        if (cmdExecutor == null) throw new NotExecutableException(command);
        if (tabCompleter == null) throw new NotTabCompletableException(command);
        return new CommandNode(parent, command, permission, description, placeholder) {
            @Override
            public boolean executeCommand(@Nonnull CommandSender sender, @Nonnull List<String> args) {
                return cmdExecutor.execute(sender, args);
            }

            @Override
            public List<String> executeTabCompletion(@Nonnull CommandSender sender, @Nonnull List<String> args) {
                return tabCompleter.execute(sender, args);
            }
        };
    }


}
//如何使用
/*
class CommandNodeUse{
    CommandNode getNode(){
        return new CommandNodeBuilder("hello")
                .description("向一個人 say hello")
                .parent(null)
                .placeholder("<player>")
                .tabComplete((sender, args) -> null)
                .permission("say.hello").execute((sender, args) -> {
                    String name = args.get(0);
                    Player target = Bukkit.getPlayer(name);
                    if (!target.isOnline()){
                        sender.sendMessage("對方不在線!");
                        return true;
                    }
                    sender.sendMessage("已向 "+name+" say hello !");
                    target.sendMessage(sender.getName()+" 向你 say hello 了!");
                    return true;
                }).build();
    }
}

 */
