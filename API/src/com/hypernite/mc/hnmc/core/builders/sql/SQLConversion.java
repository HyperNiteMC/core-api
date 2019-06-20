package com.hypernite.mc.hnmc.core.builders.sql;

/**
 * 安全轉換字符串,以供給SQL使用
 */
public class SQLConversion {


    /**
     * SQL欄位字符串,安全處裡
     * `=``
     * null=NULL
     *
     * @param value 值
     * @return 安全修改過的值
     */
    public static String safety(String value) {
        if (value == null) {
            return "NULL";
        } else {
            value = value.replaceAll("`", "``");
            //value = value.replaceAll("\\\\","\\\\");
            //value = value.replaceAll("'","'");
            //value = value.replaceAll("\"","\\\\\"");
            return value;
        }
    }


    /**
     * SQL資料字符串,安全處裡
     * \=\\
     * '=\'
     * "=\"
     * null=NULL
     *
     * @param value 值
     * @return 安全修改過並加括號的值
     */
    public static String brackets(String value) {
        if (value == null) {
            return "NULL";
        } else {
            //value = value.replaceAll("`","``");
            value = value.replaceAll("\\\\", "\\\\\\\\");
            value = value.replaceAll("'", "\\\\\'");
            value = value.replaceAll("\"", "\\\\\"");
            return "'" + value + "'";
        }
    }


}
