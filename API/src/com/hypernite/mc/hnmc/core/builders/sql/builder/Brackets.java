package com.hypernite.mc.hnmc.core.builders.sql.builder;

import com.hypernite.mc.hnmc.core.builders.sql.SQLConversion;

/**
 * SQL命令生成器
 * 括號
 * 用於將多個{@link Where}組合
 */
public class Brackets {

    private StringBuilder wheres; // 判斷式組合串


    /**
     * 括號內至少要有一個判斷條件
     *
     * @param where 判斷式生成器
     */
    public Brackets(Where where) {
        wheres = new StringBuilder(where.part());
    }

    /**
     * 括號內至少要有一個判斷條件
     *
     * @param field 判斷欄位
     * @param judge 判斷方式
     * @param value 判斷值
     */
    public Brackets(String field, Judge judge, String value) {
        if (judge == Judge.IS_NULL || judge == Judge.IS_NOT_NULL) {
            throw new Error("判斷條件方式 " + judge.name() + " 不需要判斷值");
        } else {
            wheres = new StringBuilder("`").append(SQLConversion.safety(field)).append("` ").append(judge.getValue()).append(" ").append(SQLConversion.brackets(value));
        }
    }

    /**
     * 括號內至少要有一個判斷條件
     *
     * @param field 判斷欄位
     * @param judge 判斷方式
     * @param value 判斷值
     */
    public Brackets(String field, Judge judge, Integer value) {
        if (judge == Judge.IS_NULL || judge == Judge.IS_NOT_NULL) {
            throw new Error("判斷條件方式 " + judge.name() + " 不需要判斷值");
        } else {
            wheres = new StringBuilder("`").append(SQLConversion.safety(field)).append("` ").append(judge.getValue()).append(" ").append(SQLConversion.brackets(value.toString()));
        }
    }

    /**
     * 括號內至少要有一個判斷條件
     *
     * @param field 判斷欄位
     * @param judge 判斷方式
     * @param value 判斷值
     */
    public Brackets(String field, Judge judge, Boolean value) {
        if (judge == Judge.IS_NULL || judge == Judge.IS_NOT_NULL) {
            throw new Error("判斷條件方式 " + judge.name() + " 不需要判斷值");
        } else {
            wheres = new StringBuilder("`").append(SQLConversion.safety(field)).append("` ").append(judge.getValue()).append(" ").append(SQLConversion.brackets(value.toString()));
        }
    }

    /**
     * 括號內至少要有一個判斷條件
     *
     * @param field 判斷欄位
     * @param judge 判斷方式
     */
    public Brackets(String field, Judge judge) {
        if (judge == Judge.IS_NULL || judge == Judge.IS_NOT_NULL) {
            wheres = new StringBuilder("`").append(SQLConversion.safety(field)).append("` ").append(judge.getValue());
        } else {
            throw new IllegalArgumentException("判斷條件方式 " + judge.name() + " 需要判斷值");
        }
    }


    /**
     * 轉成SQL可用於拼湊的字符串
     *
     * @return 可用於拼湊的字符串
     */
    public String part() {
        if (wheres == null) return "";

        return "(" + wheres + ")";
    }


    /**
     * 必須與另一判斷式同時符合
     *
     * @param where 判斷式生成器
     * @return 括號生成器
     */
    public Brackets and(Where where) {
        if (where == null) return this; // 不能為空

        wheres.append(" ").append(Mutual.AND.getValue()).append(" ").append(where.part());
        return this;
    }

    /**
     * 必須與另一判斷式同時符合
     *
     * @param field 判斷欄位
     * @param judge 判斷方式
     * @param value 判斷值
     * @return 括號生成器
     */
    public Brackets and(String field, Judge judge, String value) {
        if (judge == Judge.IS_NULL || judge == Judge.IS_NOT_NULL) {
            throw new Error("判斷條件方式 " + judge.name() + " 不需要判斷值");
        } else {
            wheres.append(" ").append(Mutual.AND.getValue()).append(" `").append(SQLConversion.safety(field)).append("` ").append(judge.getValue()).append(" ").append(SQLConversion.brackets(value));
        }
        return this;
    }

    /**
     * 必須與另一判斷式同時符合
     *
     * @param field 判斷欄位
     * @param judge 判斷方式
     * @param value 判斷值
     * @return 括號生成器
     */
    public Brackets and(String field, Judge judge, Integer value) {
        if (judge == Judge.IS_NULL || judge == Judge.IS_NOT_NULL) {
            throw new Error("判斷條件方式 " + judge.name() + " 不需要判斷值");
        } else {
            wheres.append(" ").append(Mutual.AND.getValue()).append(" `").append(SQLConversion.safety(field)).append("` ").append(judge.getValue()).append(" ").append(SQLConversion.brackets(value.toString()));
        }
        return this;
    }

    /**
     * 必須與另一判斷式同時符合
     *
     * @param field 判斷欄位
     * @param judge 判斷方式
     * @param value 判斷值
     * @return 括號生成器
     */
    public Brackets and(String field, Judge judge, Boolean value) {
        if (judge == Judge.IS_NULL || judge == Judge.IS_NOT_NULL) {
            throw new Error("判斷條件方式 " + judge.name() + " 不需要判斷值");
        } else {
            wheres.append(" ").append(Mutual.AND.getValue()).append(" `").append(SQLConversion.safety(field)).append("` ").append(judge.getValue()).append(" ").append(SQLConversion.brackets(value.toString()));
        }
        return this;
    }

    /**
     * 必須與另一判斷式同時符合
     *
     * @param field 判斷欄位
     * @param judge 判斷方式
     * @return 括號生成器
     */
    public Brackets and(String field, Judge judge) {
        if (judge == Judge.IS_NULL || judge == Judge.IS_NOT_NULL) {
            wheres.append(" ").append(Mutual.AND.getValue()).append(" `").append(SQLConversion.safety(field)).append("` ").append(judge.getValue());
        } else {
            throw new Error("判斷條件方式 " + judge.name() + " 需要判斷值");
        }
        return this;
    }

    /**
     * 必須與另一括號同時符合
     *
     * @param brackets 括號生成器
     * @return 括號生成器
     */
    public Brackets and(Brackets brackets) {
        if (brackets == null) return this; // 不能為空

        wheres.append(" ").append(Mutual.AND.getValue()).append(" ").append(brackets.part());
        return this;
    }


    /**
     * 或是符合另一判斷式
     *
     * @param where 判斷式生成器
     * @return 括號生成器
     */
    public Brackets or(Where where) {
        if (where == null) return this; // 不能為空

        wheres.append(" ").append(Mutual.OR.getValue()).append(" ").append(where.part());
        return this;
    }

    /**
     * 或是符合另一判斷式
     *
     * @param field 判斷欄位
     * @param judge 判斷方式
     * @param value 判斷值
     * @return 括號生成器
     */
    public Brackets or(String field, Judge judge, String value) {
        if (judge == Judge.IS_NULL || judge == Judge.IS_NOT_NULL) {
            throw new Error("判斷條件方式 " + judge.name() + " 不需要判斷值");
        } else {
            wheres.append(" ").append(Mutual.OR.getValue()).append(" `").append(SQLConversion.safety(field)).append("` ").append(judge.getValue()).append(" ").append(SQLConversion.brackets(value));
        }
        return this;
    }

    /**
     * 或是符合另一判斷式
     *
     * @param field 判斷欄位
     * @param judge 判斷方式
     * @param value 判斷值
     * @return 括號生成器
     */
    public Brackets or(String field, Judge judge, Integer value) {
        if (judge == Judge.IS_NULL || judge == Judge.IS_NOT_NULL) {
            throw new Error("判斷條件方式 " + judge.name() + " 不需要判斷值");
        } else {
            wheres.append(" ").append(Mutual.OR.getValue()).append(" `").append(SQLConversion.safety(field)).append("` ").append(judge.getValue()).append(" ").append(SQLConversion.brackets(value.toString()));
        }
        return this;
    }

    /**
     * 或是符合另一判斷式
     *
     * @param field 判斷欄位
     * @param judge 判斷方式
     * @param value 判斷值
     * @return 括號生成器
     */
    public Brackets or(String field, Judge judge, Boolean value) {
        if (judge == Judge.IS_NULL || judge == Judge.IS_NOT_NULL) {
            throw new Error("判斷條件方式 " + judge.name() + " 不需要判斷值");
        } else {
            wheres.append(" ").append(Mutual.OR.getValue()).append(" `").append(SQLConversion.safety(field)).append("` ").append(judge.getValue()).append(" ").append(SQLConversion.brackets(value.toString()));
        }
        return this;
    }

    /**
     * 或是符合另一判斷式
     *
     * @param field 判斷欄位
     * @param judge 判斷方式
     * @return 括號生成器
     */
    public Brackets or(String field, Judge judge) {
        if (judge == Judge.IS_NULL || judge == Judge.IS_NOT_NULL) {
            wheres.append(" ").append(Mutual.OR.getValue()).append(" `").append(SQLConversion.safety(field)).append("` ").append(judge.getValue());
        } else {
            throw new Error("判斷條件方式 " + judge.name() + " 需要判斷值");
        }
        return this;
    }

    /**
     * 或是符合另一括號
     *
     * @param brackets 括號生成器
     * @return 括號生成器
     */
    public Brackets or(Brackets brackets) {
        if (brackets == null) return this; // 不能為空

        wheres.append(" ").append(Mutual.OR.getValue()).append(" ").append(brackets.part());
        return this;
    }


}
