package com.hypernite.mc.hnmc.core.utils;

import com.comphenix.protocol.ProtocolLibrary;

import javax.annotation.Nullable;
import java.io.File;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * FileUtils with Reflection
 */
public class HNFileUtils {


    private static Method getFileNameUtilsMethod(String method, Class<?>... parameters) throws ClassNotFoundException, NoSuchMethodException {
        final String version = ProtocolLibrary.getProtocolManager().getMinecraftVersion().getVersion();
        final String three = "org.apache.commons.io.FilenameUtils";
        final String four = "org.bukkit.craftbukkit.libs.org.apache.commons.io.FilenameUtils";
        final String zlassName = version.startsWith("1.13") ? three : four;
        Class<?> utilclass = Class.forName(zlassName);
        return utilclass.getMethod(method, parameters);
    }

    private static Method getFileUtilsMethod(String method, Class<?>... parameters) throws ClassNotFoundException, NoSuchMethodException {
        final String version = ProtocolLibrary.getProtocolManager().getMinecraftVersion().getVersion();
        final String three = "org.apache.commons.io.FileUtils";
        final String four = "org.bukkit.craftbukkit.libs.org.apache.commons.io.FileUtils";
        final String zlassName = version.startsWith("1.13") ? three : four;
        Class<?> utilclass = Class.forName(zlassName);
        return utilclass.getMethod(method, parameters);
    }

    /**
     * @param method     方法名稱
     * @param returnType 返回類型
     * @param parameters 參數
     * @param <T>        返回類型
     * @return 方法返回 object
     */
    @Nullable
    public static <T> T doFileUtilsMethod(String method, @Nullable Class<T> returnType, Object... parameters) {
        Class<?>[] para = Arrays.stream(parameters).map(Object::getClass).toArray(Class[]::new);
        try {
            Method m = getFileUtilsMethod(method, para);
            Object result = m.invoke(null, parameters);
            if (returnType == null) return null;
            return returnType.cast(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param method     方法
     * @param returnType 返回類型
     * @param parameters 參數
     * @param <T>        返回類型
     * @return 方法返回 object
     */
    @Nullable
    public static <T> T doFileNameUtilsMethod(String method, @Nullable Class<T> returnType, Object... parameters) {
        Class<?>[] para = Arrays.stream(parameters).map(Object::getClass).toArray(Class[]::new);
        try {
            Method m = getFileNameUtilsMethod(method, para);
            Object result = m.invoke(null, parameters);
            if (returnType == null) return null;
            return returnType.cast(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param name 文件名稱
     * @return 沒有格式的文件名稱
     */
    public static String getBaseName(String name) {
        try {
            Method method = getFileNameUtilsMethod("getBaseName", String.class);
            return (String) method.invoke(null, name);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * @param name 文件名稱
     * @return 文件格式
     */
    public static String getExtension(String name) {
        try {
            Method method = getFileNameUtilsMethod("getExtension", String.class);
            return (String) method.invoke(null, name);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 強制刪除
     *
     * @param file 文件
     */
    public static void forceDelete(File file) {
        try {
            Method method = getFileUtilsMethod("forceDelete", File.class);
            method.invoke(null, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 離開後強制刪除
     *
     * @param file 文件
     */
    public static void forceDeleteOnExit(File file) {
        try {
            Method method = getFileUtilsMethod("forceDeleteOnExit", File.class);
            method.invoke(null, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
