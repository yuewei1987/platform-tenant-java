/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.cs.platform.framework.util;

import org.apache.commons.lang3.Validate;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;

/**
 * 支持SHA-1/MD5消息摘要的工具类.
 *
 * 返回ByteSource，可进一步被编码为Hex, Base64或UrlSafeBase64
 *
 * @author calvin
 */
public class Digests {

    /** FIXME */
    private static final String SHA1 = "SHA-1";

    /** FIXME */
    private static final String MD5 = "MD5";

    /** FIXME */
    private static SecureRandom random = new SecureRandom();

    /**
     * 对输入字符串进行sha1散列.
     *
     * @param input
     *            输入字节
     * @return 散列字节
     */
    public static byte[] sha1(byte[] input) {
        return digest(input, SHA1, null, 1);
    }

    /**
     * 对输入字符串进行sha1散列
     *
     * @param input
     *            输入字节
     * @param salt
     *            加盐
     * @return 散列字节
     */
    public static byte[] sha1(byte[] input, byte[] salt) {
        return digest(input, SHA1, salt, 1);
    }

    /**
     * 对输入字符串进行sha1散列
     *
     * @param input
     *            输入字节
     * @param salt
     *            加盐
     * @param iterations
     *            迭代次数
     * @return 散列字节
     */
    public static byte[] sha1(byte[] input, byte[] salt, int iterations) {
        return digest(input, SHA1, salt, iterations);
    }

    /**
     * 对字符串进行散列, 支持md5与sha1算法.
     *
     * @param input
     *            输入字节
     * @param algorithm
     *            算法
     * @param salt
     *            加盐
     * @param iterations
     *            迭代次数
     * @return 散列字节
     */
    private static byte[] digest(byte[] input, String algorithm, byte[] salt, int iterations) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);

            if (salt != null) {
                digest.update(salt);
            }

            byte[] result = digest.digest(input);

            for (int i = 1; i < iterations; i++) {
                digest.reset();
                result = digest.digest(result);
            }
            return result;
        } catch (GeneralSecurityException e) {
            throw Exceptions.unchecked(e);
        }
    }

    /**
     * 生成随机的Byte[]作为salt.
     *
     * @param numBytes
     *            byte数组的大小
     * @return 随机的Byte
     */
    public static byte[] generateSalt(int numBytes) {
        Validate.isTrue(numBytes > 0, "numBytes argument must be a positive integer (1 or larger)", numBytes);

        byte[] bytes = new byte[numBytes];
        random.nextBytes(bytes);
        return bytes;
    }

    /**
     * 对文件进行md5散列.
     *
     * @param input
     *            输入流
     * @return md5散列字节数组
     * @throws IOException
     *             io异常
     */
    public static byte[] md5(InputStream input) throws IOException {
        return digest(input, MD5);
    }

    /**
     * 对文件进行sha1散列.
     *
     * @param input
     *            输入流
     * @return sha1散列字节数组
     * @throws IOException
     *             io异常
     */
    public static byte[] sha1(InputStream input) throws IOException {
        return digest(input, SHA1);
    }

    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param input
     *            输入流
     * @param algorithm
     *            算法
     * @return 散列字节数组
     * @throws IOException
     *             io异常
     */
    private static byte[] digest(InputStream input, String algorithm) throws IOException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            int bufferLength = 8 * 1024;
            byte[] buffer = new byte[bufferLength];
            int read = input.read(buffer, 0, bufferLength);

            while (read > -1) {
                messageDigest.update(buffer, 0, read);
                read = input.read(buffer, 0, bufferLength);
            }

            return messageDigest.digest();
        } catch (GeneralSecurityException e) {
            throw Exceptions.unchecked(e);
        }
    }

}
