package com.hypernite.mc.hnmc.core.builders.sql;

import com.hypernite.mc.hnmc.core.builders.sql.builder.Delete;
import com.hypernite.mc.hnmc.core.builders.sql.builder.Insert;
import com.hypernite.mc.hnmc.core.builders.sql.builder.Select;
import com.hypernite.mc.hnmc.core.builders.sql.builder.Update;
import com.hypernite.mc.hnmc.core.main.HyperNiteMC;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * 資料庫查詢
 */
public class SQL {

    private String sql = null; // SQL命令
    private Statement statement = null; // 資料庫控制庫
    private ResultSet result_set = null; // 返回結果控制庫
    private int amount = -1; // 查詢數量


    /**
     * 新建資料庫查詢方法,並存入SQL命令
     *
     * @param sql_cmd SQL命令
     * @see SQL#question()
     * @see SQL#question(String)
     * @see SQL#Q()
     * @see SQL#Q(String)
     * @see SQL#update()
     * @see SQL#update(String)
     * @see SQL#U()
     * @see SQL#U(String)
     */
    public SQL(String sql_cmd) {
        //if ( sql_cmd == null ) throw new NullPointerException(); // 不能為空值
        sql = sql_cmd; // 保存到數據

        try {
            statement = HyperNiteMC.getAPI().getSQLDataSource().getConnection().createStatement();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 新建資料庫查詢方法
     *
     * @see SQL#question()
     * @see SQL#question(String)
     * @see SQL#Q()
     * @see SQL#Q(String)
     * @see SQL#update()
     * @see SQL#update(String)
     * @see SQL#U()
     * @see SQL#U(String)
     */
    public SQL() {
        try {
            statement = HyperNiteMC.getAPI().getSQLDataSource().getConnection().createStatement();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    /**
     * 查詢數據庫資料,並緩存查詢結果、符合行數,可供後續操作
     *
     * @param select 選擇資料生成器
     * @return 是否成功
     * @throws NullPointerException SQL命令不能為null
     * @throws Error                SQL發生錯誤
     * @see SQL#next()
     * @see SQL#N()
     * @see SQL#amount()
     * @see SQL#A()
     */
    public boolean Q(Select select) {
        sql = select.sql();
        return question();
    }

    /**
     * 查詢數據庫資料,並緩存查詢結果、符合行數,可供後續操作
     *
     * @param sql_cmd SQL命令
     * @return 是否成功
     * @throws NullPointerException SQL命令不能為null
     * @throws Error                SQL發生錯誤
     * @see SQL#next()
     * @see SQL#N()
     * @see SQL#amount()
     * @see SQL#A()
     */
    public boolean Q(String sql_cmd) {
        sql = sql_cmd;
        return question();
    }

    /**
     * 查詢數據庫資料,並緩存查詢結果、符合行數,可供後續操作
     *
     * @return 是否成功
     * @throws NullPointerException SQL命令不能為null
     * @throws Error                SQL發生錯誤
     * @see SQL#next()
     * @see SQL#N()
     * @see SQL#amount()
     * @see SQL#A()
     * @see SQL#setSQL(String)
     */
    public boolean Q() {
        return question();
    }

    /**
     * 查詢數據庫資料,並緩存查詢結果、符合行數,可供後續操作
     *
     * @param select 選擇資料生成器
     * @return 是否成功
     * @throws NullPointerException SQL命令不能為null
     * @throws Error                SQL發生錯誤
     * @see SQL#next()
     * @see SQL#N()
     * @see SQL#amount()
     * @see SQL#A()
     */
    public boolean question(Select select) {
        sql = select.sql();
        return question();
    }

    /**
     * 查詢數據庫資料,並緩存查詢結果、符合行數,可供後續操作
     *
     * @param sql_cmd SQL命令
     * @return 是否成功
     * @throws NullPointerException SQL命令不能為null
     * @throws Error                SQL發生錯誤
     * @see SQL#next()
     * @see SQL#N()
     * @see SQL#amount()
     * @see SQL#A()
     */
    public boolean question(String sql_cmd) {
        sql = sql_cmd;
        return question();
    }

    /**
     * 查詢數據庫資料,並緩存查詢結果、符合行數,可供後續操作
     *
     * @return 是否成功
     * @throws NullPointerException SQL命令不能為null
     * @throws Error                SQL發生錯誤
     * @see SQL#next()
     * @see SQL#N()
     * @see SQL#amount()
     * @see SQL#A()
     * @see SQL#setSQL(String)
     */
    public boolean question() {
        try {
            if (sql == null) throw new NullPointerException(); // 不能為空值
            if (statement == null) return false; // 不能為空值

            if (result_set != null) result_set.close(); // 先關閉舊的查詢結果

            // 取得資料結果
            result_set = statement.executeQuery(sql); // 查詢

            // 取得資料數目
            result_set.last(); // 跳到最後一行資料
            amount = result_set.getRow(); // 取得行數
            result_set.beforeFirst(); // 回歸初始行數

            return true; // 返回成功

        } catch (Exception ex) {
            // 出錯

            close(); // 關閉返回結果控制庫
            amount = -1;

            ex.printStackTrace();
            throw new Error("SQL發生錯誤 命令: " + sql);
        }
    }


    /**
     * 更新數據庫資料,並緩存成功行數
     *
     * @param update 更新資料生成器
     * @return 成功行數
     * @throws NullPointerException SQL命令不能為null
     * @throws Error                SQL發生錯誤
     * @see SQL#amount()
     * @see SQL#A()
     */
    public int U(Update update) {
        sql = update.sql();
        return update();
    }

    /**
     * 更新數據庫資料,並緩存成功行數
     *
     * @param insert 插入資料生成器
     * @return 成功行數
     * @throws NullPointerException SQL命令不能為null
     * @throws Error                SQL發生錯誤
     * @see SQL#amount()
     * @see SQL#A()
     */
    public int U(Insert insert) {
        sql = insert.sql();
        return update();
    }

    /**
     * 更新數據庫資料,並緩存成功行數
     *
     * @param delete 刪除資料生成器
     * @return 成功行數
     * @throws NullPointerException SQL命令不能為null
     * @throws Error                SQL發生錯誤
     * @see SQL#amount()
     * @see SQL#A()
     */
    public int U(Delete delete) {
        sql = delete.sql();
        return update();
    }

    /**
     * 更新數據庫資料,並緩存成功行數
     *
     * @param sql_cmd SQL命令
     * @return 成功行數
     * @throws NullPointerException SQL命令不能為null
     * @throws Error                SQL發生錯誤
     * @see SQL#amount()
     * @see SQL#A()
     */
    public int U(String sql_cmd) {
        sql = sql_cmd;
        return update();
    }

    /**
     * 更新數據庫資料,並緩存成功行數
     *
     * @return 成功行數
     * @throws NullPointerException SQL命令不能為null
     * @throws Error                SQL發生錯誤
     * @see SQL#amount()
     * @see SQL#A()
     * @see SQL#setSQL(String)
     */
    public int U() {
        return update();
    }

    /**
     * 更新數據庫資料,並緩存成功行數
     *
     * @param update 更新資料生成器
     * @return 成功行數
     * @throws NullPointerException SQL命令不能為null
     * @throws Error                SQL發生錯誤
     * @see SQL#amount()
     * @see SQL#A()
     */
    public int update(Update update) {
        sql = update.sql();
        return update();
    }

    /**
     * 更新數據庫資料,並緩存成功行數
     *
     * @param insert 插入資料生成器
     * @return 成功行數
     * @throws NullPointerException SQL命令不能為null
     * @throws Error                SQL發生錯誤
     * @see SQL#amount()
     * @see SQL#A()
     */
    public int update(Insert insert) {
        sql = insert.sql();
        return update();
    }

    /**
     * 更新數據庫資料,並緩存成功行數
     *
     * @param delete 刪除資料生成器
     * @return 成功行數
     * @throws NullPointerException SQL命令不能為null
     * @throws Error                SQL發生錯誤
     * @see SQL#amount()
     * @see SQL#A()
     */
    public int update(Delete delete) {
        sql = delete.sql();
        return update();
    }

    /**
     * 更新數據庫資料,並緩存成功行數
     *
     * @param sql_cmd SQL命令
     * @return 成功行數
     * @throws NullPointerException SQL命令不能為null
     * @throws Error                SQL發生錯誤
     * @see SQL#amount()
     * @see SQL#A()
     */
    public int update(String sql_cmd) {
        sql = sql_cmd;
        return update();
    }

    /**
     * 更新數據庫資料,並緩存成功行數
     *
     * @return 成功行數
     * @throws NullPointerException SQL命令不能為null
     * @throws Error                SQL發生錯誤
     * @see SQL#amount()
     * @see SQL#A()
     * @see SQL#setSQL(String)
     */
    public int update() {
        try {
            if (sql == null) throw new NullPointerException(); // 不能為空值
            if (statement == null) return -1; // 不能為空值

            amount = statement.executeUpdate(sql); // 更新資料

            return amount; // 返回成功行數

        } catch (Exception ex) {
            // 出錯

            close(); // 關閉返回結果控制庫
            amount = -1;

            ex.printStackTrace();
            throw new Error("SQL發生錯誤 命令: " + sql);
        }
    }


    /**
     * 取得{@link ResultSet}以供資料取值用
     *
     * @return SQL查詢結果, 可能為null
     */
    public ResultSet R() {
        return result();
    } // 快速使用

    /**
     * 取得{@link ResultSet}以供資料取值用
     *
     * @return SQL查詢結果, 可能為null
     */
    public ResultSet result() {
        return result_set;
    }


    /**
     * 取得符合行數
     *
     * @return 符合行數, 失敗則-1
     */
    public int A() {
        return amount();
    } // 快速使用

    /**
     * 取得符合行數
     *
     * @return 符合行數, 失敗則-1
     */
    public int amount() {
        return amount;
    }


    /**
     * 資料查詢結果跳下一行
     *
     * @return 是否還擁有下一行
     * @see SQL#B()
     * @see SQL#before()
     */
    public boolean N() {
        return next();
    } // 快速使用

    /**
     * 資料查詢結果跳下一行
     *
     * @return 是否還擁有下一行
     * @see SQL#B()
     * @see SQL#before()
     */
    public boolean next() {

        if (result_set == null) return false; // 還沒有使用查詢過

        try {
            return result_set.next();

        } catch (Exception ex) {
            // 出錯
            ex.printStackTrace();
            return false;
        }
    }


    /**
     * 資料查詢結果跳最初行
     *
     * @see SQL#N()
     * @see SQL#next()
     */
    public void B() {
        before();
    } // 快速使用

    /**
     * 資料查詢結果跳最初行
     *
     * @see SQL#N()
     * @see SQL#next()
     */
    public void before() {
        if (result_set == null) return; // 不能為空值

        try {
            result_set.beforeFirst(); // 回歸初始行數

        } catch (Exception ex) {
            // 出錯
            ex.printStackTrace();
        }
    }


    /**
     * 資料查詢結果中,取得指定資料型態
     *
     * @param field 資料欄位名稱
     * @return 資料查詢結果
     * @throws Error SQL取得資料值錯誤
     */
    public Object getObject(String field) {
        if (result_set == null) throw new NullPointerException(); // 不能為空值
        try {
            return result_set.getObject(field);

        } catch (Exception ex) {
            // 出錯
            ex.printStackTrace();
            throw new Error("SQL取得資料值錯誤 欄位:" + field + " SQL命令:" + sql);
        }
    }

    /**
     * 資料查詢結果中,取得指定資料型態
     *
     * @param field 資料欄位名稱
     * @return 資料查詢結果
     * @throws Error SQL取得資料值錯誤
     */
    public String getString(String field) {
        if (result_set == null) throw new NullPointerException(); // 不能為空值
        try {
            return result_set.getString(field);

        } catch (Exception ex) {
            // 出錯
            ex.printStackTrace();
            throw new Error("SQL取得資料值錯誤 欄位:" + field + " SQL命令:" + sql);
        }
    }

    /**
     * 資料查詢結果中,取得指定資料型態
     *
     * @param field 資料欄位名稱
     * @return 資料查詢結果
     * @throws Error SQL取得資料值錯誤
     */
    public long getLong(String field) {
        if (result_set == null) throw new NullPointerException(); // 不能為空值
        try {
            return result_set.getLong(field);

        } catch (Exception ex) {
            // 出錯
            ex.printStackTrace();
            throw new Error("SQL取得資料值錯誤 欄位:" + field + " SQL命令:" + sql);
        }
    }

    /**
     * 資料查詢結果中,取得指定資料型態
     *
     * @param field 資料欄位名稱
     * @return 資料查詢結果
     * @throws Error SQL取得資料值錯誤
     */
    public int getInt(String field) {
        if (result_set == null) throw new NullPointerException(); // 不能為空值
        try {
            return result_set.getInt(field);

        } catch (Exception ex) {
            // 出錯
            ex.printStackTrace();
            throw new Error("SQL取得資料值錯誤 欄位:" + field + " SQL命令:" + sql);
        }
    }

    /**
     * 資料查詢結果中,取得指定資料型態
     *
     * @param field 資料欄位名稱
     * @return 資料查詢結果
     * @throws Error SQL取得資料值錯誤
     */
    public double getDouble(String field) {
        if (result_set == null) throw new NullPointerException(); // 不能為空值
        try {
            return result_set.getDouble(field);

        } catch (Exception ex) {
            // 出錯
            ex.printStackTrace();
            throw new Error("SQL取得資料值錯誤 欄位:" + field + " SQL命令:" + sql);
        }
    }

    /**
     * 資料查詢結果中,取得指定資料型態
     *
     * @param field 資料欄位名稱
     * @return 資料查詢結果
     * @throws Error SQL取得資料值錯誤
     */
    public float getFloat(String field) {
        if (result_set == null) throw new NullPointerException(); // 不能為空值
        try {
            return result_set.getFloat(field);

        } catch (Exception ex) {
            // 出錯
            ex.printStackTrace();
            throw new Error("SQL取得資料值錯誤 欄位:" + field + " SQL命令:" + sql);
        }
    }

    /**
     * 資料查詢結果中,取得指定資料型態
     *
     * @param field 資料欄位名稱
     * @return 資料查詢結果
     * @throws Error SQL取得資料值錯誤
     */
    public Time getTime(String field) {
        if (result_set == null) throw new NullPointerException(); // 不能為空值
        try {
            return result_set.getTime(field);

        } catch (Exception ex) {
            // 出錯
            ex.printStackTrace();
            throw new Error("SQL取得資料值錯誤 欄位:" + field + " SQL命令:" + sql);
        }
    }

    /**
     * 資料查詢結果中,取得指定資料型態
     *
     * @param field 資料欄位名稱
     * @return 資料查詢結果
     * @throws Error SQL取得資料值錯誤
     */
    public Timestamp getTimestamp(String field) {
        if (result_set == null) throw new NullPointerException(); // 不能為空值
        try {
            return result_set.getTimestamp(field);

        } catch (Exception ex) {
            // 出錯
            ex.printStackTrace();
            throw new Error("SQL取得資料值錯誤 欄位:" + field + " SQL命令:" + sql);
        }
    }

    /**
     * 資料查詢結果中,取得指定資料型態
     *
     * @param field 資料欄位名稱
     * @return 資料查詢結果
     * @throws Error SQL取得資料值錯誤
     */
    public boolean getBoolean(String field) {
        if (result_set == null) throw new NullPointerException(); // 不能為空值
        try {
            return result_set.getBoolean(field);

        } catch (Exception ex) {
            // 出錯
            ex.printStackTrace();
            throw new Error("SQL取得資料值錯誤 欄位:" + field + " SQL命令:" + sql);
        }
    }

    /**
     * 資料查詢結果中,取得指定資料型態
     *
     * @param field 資料欄位名稱
     * @return 資料查詢結果
     * @throws Error SQL取得資料值錯誤
     */
    public int getBooleanByInt(String field) {
        if (result_set == null) throw new NullPointerException(); // 不能為空值
        try {
            if (result_set.getBoolean(field)) {
                return 1;
            } else {
                return 0;
            }

        } catch (Exception ex) {
            // 出錯
            ex.printStackTrace();
            throw new Error("SQL取得資料值錯誤 欄位:" + field + " SQL命令:" + sql);
        }
    }

    /**
     * 資料查詢結果中,取得指定資料型態
     *
     * @param field 資料欄位名稱
     * @return 資料查詢結果
     * @throws Error SQL取得資料值錯誤
     */
    public byte getByte(String field) {
        if (result_set == null) throw new NullPointerException(); // 不能為空值
        try {
            return result_set.getByte(field);

        } catch (Exception ex) {
            // 出錯
            ex.printStackTrace();
            throw new Error("SQL取得資料值錯誤 欄位:" + field + " SQL命令:" + sql);
        }
    }

    /**
     * 資料查詢結果中,取得指定資料型態
     *
     * @param field 資料欄位名稱
     * @return 資料查詢結果
     * @throws Error SQL取得資料值錯誤
     */
    public byte[] getBytes(String field) {
        if (result_set == null) throw new NullPointerException(); // 不能為空值
        try {
            return result_set.getBytes(field);

        } catch (Exception ex) {
            // 出錯
            ex.printStackTrace();
            throw new Error("SQL取得資料值錯誤 欄位:" + field + " SQL命令:" + sql);
        }
    }

    /**
     * 取得SQL命令
     *
     * @return SQL命令, 可能為null
     */
    public String getSQL() {
        return sql;
    }

    /**
     * 設置SQL命令
     *
     * @param sql_cmd SQL命令
     */
    // 設置 SQL 命令
    public void setSQL(String sql_cmd) {
        sql = sql_cmd;
    }

    /**
     * 關閉連線,不可再進行數據庫操作
     * 不使用SQL時,一定要使用
     * 否則可能記憶體溢出
     */
    public void C() {
        close();
    } // 快速使用

    /**
     * 關閉連線,不可再進行數據庫操作
     * 不使用SQL時,一定要使用
     * 否則可能記憶體溢出
     */
    public void close() {
        try {
            if (result_set != null) result_set.close(); // 關閉查詢
            result_set = null;

        } catch (Exception ex) {
            // 出錯
            ex.printStackTrace();
        }
    }


}