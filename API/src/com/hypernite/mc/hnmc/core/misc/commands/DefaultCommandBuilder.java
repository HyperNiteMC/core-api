package com.hypernite.mc.hnmc.core.misc.commands;

/**
 * @author Eric Lam
 * @see DefaultCommand
 */
public class DefaultCommandBuilder {
    private String command;
    private String description;
    private String permission;
    private CommandNode parent;
    private CommandNode[] children;

    /**
     * @param command 指令
     */
    public DefaultCommandBuilder(String command) {
        this.command = command;
    }

    /**
     * @param description 介紹
     * @return this
     */
    public DefaultCommandBuilder description(String description) {
        this.description = description;
        return this;
    }

    /**
     * @param permission 權限
     * @return this
     */
    public DefaultCommandBuilder permission(String permission) {
        this.permission = permission;
        return this;
    }

    /**
     * @param parent 父類指令
     * @return this
     */
    public DefaultCommandBuilder parent(CommandNode parent) {
        this.parent = parent;
        return this;
    }

    /**
     * DefaultCommand 將為 它其下的分支指令 製作幫助訊息
     *
     * @param children 其下分支指令
     * @return this
     */
    public DefaultCommandBuilder children(CommandNode... children) {
        this.children = children;
        return this;
    }

    /**
     * @return 返回分支指令的幫助訊息的指令
     */
    public DefaultCommand build() {
        DefaultCommand defaultCommand = new DefaultCommand(parent, command, permission, description);
        for (CommandNode node : children) {
            node.setParent(defaultCommand);
            defaultCommand.addSub(node);
        }
        return defaultCommand;
    }
}
// 如何使用
/*
class DefaultCommandBuilderUse{
    DefaultCommand getDefaultCommand(){
        CommandNode node1 = new CommandNodeBuilder("setting").description("get setting help").tabComplete((sender, args) -> null)
                .execute((sender, args) -> {
                    sender.sendMessage("help settings");
                    return true;
                }).build();

        CommandNode node2 = new CommandNodeBuilder("player").description("get player help").tabComplete((sender, args) -> null)
                .execute((sender, args) -> {
                    sender.sendMessage("help player");
                    return true;
                }).build();
        return new DefaultCommandBuilder("help").permission("help.use").description("get help message").children(node1,node2).build();
    }
}

 */
