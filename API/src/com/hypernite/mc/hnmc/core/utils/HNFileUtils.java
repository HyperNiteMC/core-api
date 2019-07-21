package com.hypernite.mc.hnmc.core.utils;

import com.comphenix.protocol.ProtocolLibrary;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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

    @SuppressWarnings("unchecked")
    private static <T, E extends Throwable> T invokeMethod(@Nonnull Method method, @Nullable Class<T> returnType, Class<E>[] excpetions, Object... parameters) throws E {
        try {
            final Object result = method.invoke(null, parameters);
            if (returnType == null) return null;
            return returnType.cast(result);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            final Throwable throwable = e.getTargetException();
            for (Class<E> excpetion : excpetions) {
                if (throwable.getClass() != excpetion) continue;
                throw (E) throwable;
            }
        }
        return null;
    }

    /**
     *
     * @param method 方法名稱
     * @param returnType 返回結果
     * @param excpetions 有機會扔出的 Exceptions
     * @param parameters 參數
     * @param <T> 返回類型
     * @param <E> Exception Type
     * @return 執行結果
     * @throws E Exception Type
     */
    @Nullable
    public static <T, E extends Throwable> T doFileUtilsMethod(String method, @Nullable Class<T> returnType, Class<E>[] excpetions, Object... parameters) throws E {
        Class<?>[] para = Arrays.stream(parameters).map(Object::getClass).toArray(Class[]::new);
        try {
            Method m = getFileUtilsMethod(method, para);
            return invokeMethod(m, returnType, excpetions, parameters);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param method 方法名稱
     * @param returnType 返回類型
     * @param excpetions 有機會扔出的 exception
     * @param parameters 參數
     * @param <T> 返回類型
     * @param <E> Exception Type
     * @return 執行結果
     * @throws E Exception
     */
    @Nullable
    public static <T, E extends Throwable> T doFileNameUtilsMethod(String method, @Nullable Class<T> returnType, Class<E>[] excpetions, Object... parameters) throws E {
        Class<?>[] para = Arrays.stream(parameters).map(Object::getClass).toArray(Class[]::new);
        try {
            Method m = getFileNameUtilsMethod(method, para);
            return invokeMethod(m, returnType, excpetions, parameters);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
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
     * @throws IOException 讀寫錯誤
     */
    public static void forceDelete(File file) throws IOException {
        try {
            Method method = getFileUtilsMethod("forceDelete", File.class);
            method.invoke(null, file);
        } catch (Exception e) {
            if (e instanceof InvocationTargetException) {
                InvocationTargetException ex = (InvocationTargetException) e;
                if (ex.getTargetException() instanceof IOException) {
                    throw (IOException) ex.getTargetException();
                }
            }
            e.printStackTrace();
        }
    }

    /**
     * 離開後強制刪除
     *
     * @param file 文件
     * @throws IOException 讀寫錯誤
     */
    public static void forceDeleteOnExit(File file) throws IOException {
        try {
            Method method = getFileUtilsMethod("forceDeleteOnExit", File.class);
            method.invoke(null, file);
        } catch (Exception e) {
            if (e instanceof InvocationTargetException) {
                InvocationTargetException ex = (InvocationTargetException) e;
                if (ex.getTargetException() instanceof IOException) {
                    throw (IOException) ex.getTargetException();
                }
            }
            e.printStackTrace();
        }
    }

}
