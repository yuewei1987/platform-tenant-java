package com.cs.platform.framework.util;


/**
 * 密码工具类
 */
public class Passwrods {
    public static final String HASH_ALGORITHM = "SHA-1";

    public static final int HASH_INTERATIONS = 128;

    public static final int SALT_SIZE = 8;

    /**
     * 生成安全的密码，生成随机的16位salt并经过128次 sha-1 hash
     *
     * @param plainPassword 未加密密码
     * @return 加密后密码
     */
    public static String entryptPassword(String plainPassword) {
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
        return Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword);
    }

    /**
     * 验证密码
     *
     * @param plainPassword 明文密码
     * @param password      密文密码
     * @return 验证成功返回true
     */
    public static boolean validatePassword(String plainPassword, String password) {
        byte[] salt = Encodes.decodeHex(password.substring(0, 16));
        byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
        return password.equals(Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword));
    }

    public static void main(String[] args) {
        String plainPassword = "123";
        String md5 = MD5.GetMD5Code(plainPassword);
        // byte[] salt = Encodes.decodeHex("ad3ccf3f3c2c7c42");
        // byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
        // String res = Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword);
        System.out.println(validatePassword(md5, "54c820a4c737cd635b83e3e42af94fb4d75405d38d376a15102cc007"));
        System.out.println(entryptPassword(md5));
    }
}
