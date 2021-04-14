package cn.oranger;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.HMac;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;

/**
 * @author Oranger
 * @date 2021/3/28
 */
public class md5Test {
    public static void main(String[] args) {
        String content = "1jhhhsd";
        //随机生成密钥
        String key1 = SymmetricAlgorithm.AES.getValue();
        String AESKEY ="AES";
        byte[] key = SecureUtil.generateKey(AESKEY).getEncoded();

        //构建
        AES aes = SecureUtil.aes(key);

        //加密
        byte[] encrypt = aes.encrypt(content);
        //解密
        byte[] decrypt = aes.decrypt(encrypt);

        //加密为16进制表示
        String encryptHex = aes.encryptHex(content);
        System.out.println(encryptHex);
        //解密为原字符串
        String decryptStr = aes.decryptStr(encryptHex);

        System.out.println("解密"+decryptStr);

    }
}
