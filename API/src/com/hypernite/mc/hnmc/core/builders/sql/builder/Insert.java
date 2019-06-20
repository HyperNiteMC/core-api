package com.hypernite.mc.hnmc.core.builders.sql.builder;

import com.hypernite.mc.hnmc.core.builders.sql.SQL;
import com.hypernite.mc.hnmc.core.builders.sql.SQLConversion;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * SQL命令生成器
 * 插入資料
 */
public class Insert {

    private Map<String, String> values = new HashMap<String, String>(); // 欄位對應值清單
    private Map<String, String> updates = new HashMap<String, String>(); // 如果資料存在則改成更新清單
    private Map<String, Algorithm> algorithms = new HashMap<String, Algorithm>(); // 如果資料存在則改成運算法更新清單
    private String from = null;


    /**
     * 對數據庫插入資料
     */
    public Insert() {
    }

    static String complieUpdates(Map<String, String> updates, Map<String, Algorithm> algorithms) { //提取成方法防止重複代碼
        StringBuilder all_update = null; // 字串緩存 `A` = `B`

        for (String field : updates.keySet()) {
            String value = updates.get(field);
            Algorithm algorithm = algorithms.get(field);

            if (all_update == null) {
                switch (algorithm) {
                    case EQUAL:
                        all_update = new StringBuilder("`" + SQLConversion.safety(field) + "` = " + SQLConversion.brackets(value) + "");
                        break; // =
                    case ADDITION:
                        all_update = new StringBuilder("`" + SQLConversion.safety(field) + "` = `" + SQLConversion.safety(field) + "` + " + SQLConversion.brackets(value) + "");
                        break; // +
                    case SUBTRACTION:
                        all_update = new StringBuilder("`" + SQLConversion.safety(field) + "` = `" + SQLConversion.safety(field) + "` - " + SQLConversion.brackets(value) + "");
                        break; // -
                }
            } else {
                switch (algorithm) {
                    case EQUAL:
                        all_update.append(",`").append(SQLConversion.safety(field)).append("` = ").append(SQLConversion.brackets(value));
                        break; // =
                    case ADDITION:
                        all_update.append(",`").append(SQLConversion.safety(field)).append("` = `").append(SQLConversion.safety(field)).append("` + ").append(SQLConversion.brackets(value));
                        break; // +
                    case SUBTRACTION:
                        all_update.append(",`").append(SQLConversion.safety(field)).append("` = `").append(SQLConversion.safety(field)).append("` - ").append(SQLConversion.brackets(value));
                        break; // -
                }
            }
        }
        return Optional.ofNullable(all_update).map(StringBuilder::toString).orElse("");
    }

    /**
     * 生成SQL命令字串
     *
     * @return SQL命令
     * @throws NullPointerException 如果缺少必要的數值
     */
    public String sql() {
        StringBuilder sql = new StringBuilder();

        // 組合 from
        if (from == null) throw new NullPointerException("Table is null"); // 不能為空值
        sql.append("INSERT INTO `").append(SQLConversion.safety(from)).append("`");

        // 組合 values
        if (values.size() <= 0) {
            sql.append(" () VALUES ()");
        } else {
            StringBuilder all_field = null; // 字串緩存 `A`,`B`
            StringBuilder all_value = null; // 字串緩存 'A','B'

            for (String field : values.keySet()) {
                String value = values.get(field);

                if (all_field == null) {
                    all_field = new StringBuilder("`" + SQLConversion.safety(field) + "`");
                } else {
                    all_field.append(",`").append(SQLConversion.safety(field)).append("`");
                }
                if (all_value == null) {
                    all_value = new StringBuilder("" + SQLConversion.brackets(value) + "");
                } else {
                    all_value.append(",").append(SQLConversion.brackets(value));
                }
            }

            sql.append(" (").append(all_field).append(") VALUES (").append(all_value).append(")");
        }

        // 組合 updates
        if (updates.size() > 0) {
            String all_update = complieUpdates(updates, algorithms);
            if (!all_update.isBlank()) sql.append(" ON DUPLICATE KEY UPDATE ").append(all_update);
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
     * 選擇資料表
     *
     * @param from 資料表名稱
     * @return 插入資料生成器
     */
    public Insert from(String from) {
        this.from = from;
        return this;
    }


    /**
     * 插入資料
     *
     * @param field 欄位名稱
     * @param value 值
     * @return 插入資料生成器
     */
    public Insert insert(String field, String value) {
        values.put(field, value);

        return this;
    }

    /**
     * 插入資料
     *
     * @param field 欄位名稱
     * @param value 值
     * @return 插入資料生成器
     */
    public Insert insert(String field, Integer value) {
        values.put(field, value.toString());

        return this;
    }

    /**
     * 插入資料
     *
     * @param field 欄位名稱
     * @param value 值
     * @return 插入資料生成器
     */
    public Insert insert(String field, Boolean value) {
        values.put(field, value.toString());

        return this;
    }


    /**
     * 插入資料
     *
     * @param field            欄位名稱
     * @param value            值
     * @param duplicate_update 如果資料存在則改成更新
     * @return 插入資料生成器
     */
    public Insert insert(String field, String value, String duplicate_update) {
        values.put(field, value);
        algorithms.put(field, Algorithm.EQUAL); // =
        updates.put(field, duplicate_update);

        return this;
    }

    /**
     * 插入資料
     *
     * @param field            欄位名稱
     * @param value            值
     * @param duplicate_update 如果資料存在則改成更新
     * @return 插入資料生成器
     */
    public Insert insert(String field, Boolean value, Boolean duplicate_update) {
        values.put(field, value.toString());
        algorithms.put(field, Algorithm.EQUAL); // =
        updates.put(field, duplicate_update.toString());

        return this;
    }

    /**
     * 插入資料
     *
     * @param field            欄位名稱
     * @param value            值
     * @param duplicate_update 如果資料存在則改成更新
     * @param algorithm        運算法
     * @return 插入資料生成器
     */
    public Insert insert(String field, Integer value, Integer duplicate_update, Algorithm algorithm) {
        values.put(field, value.toString());
        algorithms.put(field, algorithm); // 運算法
        updates.put(field, duplicate_update.toString());

        return this;
    }


    /**
     * 如果資料存在則改成更新
     *
     * @param field 欄位名稱
     * @param value 值
     * @return 插入資料生成器
     */
    public Insert duplicateUpdate(String field, String value) {
        algorithms.put(field, Algorithm.EQUAL); // =
        updates.put(field, value);

        return this;
    }

    /**
     * 如果資料存在則改成更新
     *
     * @param field 欄位名稱
     * @param value 值
     * @return 插入資料生成器
     */
    public Insert duplicateUpdate(String field, Insert value) {
        algorithms.put(field, Algorithm.EQUAL); // =
        updates.put(field, value.toString());

        return this;
    }

    /**
     * 如果資料存在則改成更新
     *
     * @param field 欄位名稱
     * @param value 值
     * @return 插入資料生成器
     */
    public Insert duplicateUpdate(String field, Boolean value) {
        algorithms.put(field, Algorithm.EQUAL); // =
        updates.put(field, value.toString());

        return this;
    }


    /**
     * 如果資料存在則改成更新
     * 依賴欄位,並相加原本數據
     *
     * @param field 欄位名稱
     * @param value 值
     * @return 更新資料生成器
     */
    public Insert duplicateAddition(String field, Integer value) {
        algorithms.put(field, Algorithm.ADDITION); // +
        updates.put(field, value.toString());

        return this;
    }

    /**
     * 如果資料存在則改成更新
     * 依賴欄位,並相減原本數據
     *
     * @param field 欄位名稱
     * @param value 值
     * @return 更新資料生成器
     */
    public Insert duplicateSubtraction(String field, Integer value) {
        algorithms.put(field, Algorithm.SUBTRACTION); // -
        updates.put(field, value.toString());

        return this;
    }


}
