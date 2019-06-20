package com.hypernite.mc.hnmc.core.builders.sql;

import com.hypernite.mc.hnmc.core.builders.sql.builder.Delete;
import com.hypernite.mc.hnmc.core.builders.sql.builder.Insert;
import com.hypernite.mc.hnmc.core.builders.sql.builder.Select;
import com.hypernite.mc.hnmc.core.builders.sql.builder.Update;

/**
 * 快速取得SQL命令生成器
 */
public class SQLB {


    /**
     * 選擇資料
     *
     * @param strings 要回傳的資料表清單
     * @return 選擇資料生成器
     */
    public static Select select(String... strings) {
        return new Select(strings);
    }

    /**
     * 選擇資料
     *
     * @return 選擇資料生成器
     * @see SQL#Q(Select)
     * @see SQL#question(Select)
     */
    public static Select select() {
        return new Select();
    }


    /**
     * 刪除資料
     *
     * @return 刪除資料生成器
     * @see SQL#U(Delete)
     * @see SQL#update(Delete)
     */
    public static Delete delete() {
        return new Delete();
    }


    /**
     * 插入資料
     *
     * @return 插入資料生成器
     * @see SQL#U(Insert)
     * @see SQL#update(Insert)
     */
    public static Insert insert() {
        return new Insert();
    }


    /**
     * 更新資料
     *
     * @return 更新資料生成器
     * @see SQL#U(Update)
     * @see SQL#update(Update)
     */
    public static Update update() {
        return new Update();
    }


}
