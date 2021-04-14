package cn.oranger.cs.utils;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;

/**
 * @author Oranger
 * @date 2021/3/28
 */
public class AESUtil {

    private static final byte[] KEY = {-44,65,-34,55,97,-67,37,66,-31,77,9,38,-97,-83,95,51};


    public String generatePublicKey(String content){
        System.out.println(KEY);
        //构建

        AES aes = SecureUtil.aes(KEY);

        //加密为16进制表示
        String publicKey = aes.encryptHex(content);
        return publicKey;
    }

    public String parsePublicKey(String publicKey){
        //构建
        AES aes = SecureUtil.aes(KEY);

        //解密
        String content = aes.decryptStr(publicKey);
        return content;
    }

}
