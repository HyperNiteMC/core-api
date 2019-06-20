package com.hypernite.mc.hnmc.core.builders.sql.builder;

import com.hypernite.mc.hnmc.core.builders.sql.SQL;
import com.hypernite.mc.hnmc.core.builders.sql.SQLConversion;

import java.util.HashMap;
import java.util.Map;

/**
 * SQL命令生成器
 * 選擇資料
 */
public class Select {

    private Map<String, Return> strings = new HashMap<String, Return>();
    private String from = null;
    private String where = null;
    private Integer limit = null;
    private Integer offset = null;
    private Order order = null;


    /**
     * 選擇資料,不支持群組選擇
     *
     * @param selects 要回傳的資料表清單
     */
    public Select(String... selects) {
        for (String select : selects) {
            strings.put(select, Return.NOT);
        }
    }

    /**
     * 選擇資料
     */
    public Select() {
    }


    /**
     * 生成SQL命令字串
     *
     * @return SQL命令
     * @throws NullPointerException 如果缺少必要的數值
     */
    public String sql() {
        StringBuilder sql = new StringBuilder();

        // 組合 strings 成 SELECT `A`,`B`
        if (strings == null) {
            sql.append("SELECT *");

        } else {
            sql.append("SELECT ");
            StringBuilder all_string = null;

            for (String field : strings.keySet()) {
                Return group = strings.get(field);

                switch (group) {
                    case NOT:
                        if (all_string == null) {
                            all_string = new StringBuilder("`" + SQLConversion.safety(field) + "`");
                        } else {
                            all_string.append(",`").append(SQLConversion.safety(field)).append("`");
                        }
                        break;

                    default:
                        if (all_string == null) {
                            all_string = new StringBuilder(group.getValue() + "(`" + SQLConversion.safety(field) + "`)");
                        } else {
                            all_string.append(",").append(group.getValue()).append("(`").append(SQLConversion.safety(field)).append("`)");
                        }
                        break;

                }
            }
            if (all_string != null) sql.append(all_string);
        }

        // 組合 from
        if (from == null) throw new NullPointerException("table is null"); // 不能為空值
        sql.append(" FROM `").append(SQLConversion.safety(from)).append("`");

        // 組合 where
        if (where != null) {
            sql.append(" WHERE ").append(where);
        }

        // 組合 order
        if (order != null) {
            sql.append(" ").append(order.part());
        }

        // 組合 offset
        if (offset != null) {
            sql.append(" OFFSET ").append(offset.toString());
        }

        // 組合 limit
        if (limit != null) {
            sql.append(" LIMIT ").append(limit.toString());
        }

        sql.append(";");

        return sql.toString();
    }


    /**
     * 直接使用此SQL命令查詢數據庫,並返回結果控制庫
     *
     * @return SQL命令
     * @see SQL#next()
     * @see SQL#N()
     * @see SQL#update() ()
     * @see SQL#update(String)
     * @see SQL#U()
     * @see SQL#U(String)
     */
    public SQL sqlUpdate() {
        SQL sql = new SQL(sql());
        sql.update();

        return sql;
    }

    /**
     * 直接使用此SQL命令查詢數據庫,並返回結果控制庫
     *
     * @return SQL命令
     * @see SQL#next()
     * @see SQL#N()
     * @see SQL#question()
     * @see SQL#question(String)
     * @see SQL#Q()
     * @see SQL#Q(String)
     */
    public SQL sqlQuestion() {
        SQL sql = new SQL(sql());
        sql.question();

        return sql;
    }


    /**
     * 選擇要回傳的資料欄位
     *
     * @param selects 資料欄位清單
     * @return 選擇資料生成器
     */
    public Select select(String... selects) {
        for (String select : selects) {
            strings.put(select, Return.NOT);
        }
        return this;
    }

    /**
     * 選擇要回傳的資料欄位
     *
     * @param select 資料欄位清單
     * @return 選擇資料生成器
     */
    public Select select(String select) {
        strings.put(select, Return.NOT);
        return this;
    }

    /**
     * 選擇要回傳的資料欄位
     *
     * @param group  查詢分組類型
     * @param select 選擇資料指定返回類型
     * @return 選擇資料生成器
     */
    public Select select(Return group, String select) {
        strings.put(select, group);
        return this;
    }


    /**
     * 選擇資料表
     *
     * @param from 資料表名稱
     * @return 選擇資料生成器
     */
    public Select from(String from) {
        this.from = from;
        return this;
    }


    /**
     * 判斷式
     *
     * @param where 判斷式
     * @return 選擇資料生成器
     */
    public Select where(Where where) {
        this.where = where.part();
        return this;
    }

    /**
     * 括號
     *
     * @param brackets 括號
     * @return 選擇資料生成器
     */
    public Select brackets(Brackets brackets) {
        this.where = brackets.part();
        return this;
    }


    /**
     * 限制查詢回傳行數上限
     *
     * @param limit 查詢回傳行數上限
     * @return 選擇資料生成器
     */
    public Select limit(int limit) {
        this.limit = limit;
        return this;
    }

    /**
     * 限制查詢回傳從第幾行開始
     *
     * @param offset 查詢回傳從第幾行開始
     * @return 選擇資料生成器
     */
    public Select offset(int offset) {
        this.offset = offset;
        return this;
    }


    /**
     * 排序
     *
     * @param order 排序
     * @return 選擇資料生成器
     */
    public Select order(Order order) {
        this.order = order;
        return this;
    }


}
