package com.zzf.Util;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * @author zzf
 * @date 2021-03-14
 */
public class MD5Util {
    public static String getMD5String(String str) {
        try {
            // ����һ��MD5���ܼ���ժҪ
            MessageDigest md = MessageDigest.getInstance("MD5");
            // ����md5����
            md.update(str.getBytes());
            // digest()���ȷ������md5 hashֵ������ֵΪ8λ�ַ�������Ϊmd5 hashֵ��16λ��hexֵ��ʵ���Ͼ���8λ���ַ�
            // BigInteger������8λ���ַ���ת����16λhexֵ�����ַ�������ʾ���õ��ַ�����ʽ��hashֵ
            //һ��byte�ǰ�λ�����ƣ�Ҳ����2λʮ�������ַ���2��8�η�����16��2�η���
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
