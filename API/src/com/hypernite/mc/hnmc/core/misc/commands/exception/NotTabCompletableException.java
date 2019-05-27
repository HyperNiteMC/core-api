package com.hypernite.mc.hnmc.core.misc.commands.exception;

import com.hypernite.mc.hnmc.core.misc.commands.functional.CmdExecution;

/**
 * @see com.hypernite.mc.hnmc.core.misc.commands.CommandNodeBuilder#tabComplete(CmdExecution)
 */
public class NotTabCompletableException extends RuntimeException {
    private String command;

    public NotTabCompletableException(String command) {
        super("Tab 返回為 null");
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
