package com.hubu.aspirin.util;

import com.hubu.aspirin.common.KnownException;
import com.hubu.aspirin.enums.ExceptionEnum;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 七牛云工具类
 * @author alex
 */
public class QiniuUtils {

    private static String ACCESS_KEY = "JjdzmIiQdIMab9opiMa5qr_Jcp11U9VQQGvCYbav";

    private static String SECRET_KEY = "ixDpPaUVRgbSr7OknyCEhRx_RW9r-cyFBX43INft";

    private static String BUCKET_NAME = "hubu-aspirin";

    private static String BASE_URL = "http://qiniu-cdn.alexyan.cn";

    /**
     * 上传凭证有效期 10min
     */
    private static long expireSeconds = 600;

    /**
     * 默认下载路径
     */
    private static String DEFAULT_DOWNLOAD_PATH = "D:/photos/";


    /**
     * 生成文件上传凭证
     */
    public static String createUploadToken() {
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        String upToken = auth.uploadToken(BUCKET_NAME, null, expireSeconds, null);
        return upToken;
    }

    /**
     * 上传图片
     *
     * @param file 文件
     * @return 图片存储的url
     */
    public static String uploadPhoto(File file) {
        Configuration cfg = new Configuration(Region.region0());
        String name = file.getName();
        UploadManager uploadManager = new UploadManager(cfg);
        try {
            InputStream inputStream = new FileInputStream(file);
            Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
            String upToken = auth.uploadToken(BUCKET_NAME, name, expireSeconds, null);
            Response response = uploadManager.put(inputStream, name, upToken, null, null);
        } catch (IOException e) {
            throw new KnownException(ExceptionEnum.FILE_IO_EXCEPTION);
        }
        return BASE_URL + name;
    }

    /**
     *  字节数组上传文件时调用该方法
     * @param key: 文件在七牛云中的存储索引
     */
    public static String uploadPhoto(byte[] bytes,String key) {
        Configuration cfg = new Configuration(Region.region0());
        UploadManager uploadManager = new UploadManager(cfg);
        try {
            Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
            String upToken = auth.uploadToken(BUCKET_NAME, key, expireSeconds, null);
            Response response = uploadManager.put(bytes, key, upToken);
        }catch (IOException e) {
            throw new KnownException(ExceptionEnum.FILE_IO_EXCEPTION);
        }
        return BASE_URL + key;
    }

    /**
     * 删除存储空间的文件
     *
     * @param key:待删除的文件在存储空间的索引
     */
    public static void deletePhoto(String key) {
        Configuration config = new Configuration(Region.region0());
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        BucketManager bucketManager = new BucketManager(auth, config);
        try {
            bucketManager.delete(BUCKET_NAME, key);
        } catch (QiniuException e) {
            throw new KnownException(ExceptionEnum.FILE_IO_EXCEPTION);
        }
        System.out.println("删除成功");
    }

    /**
     * 批量删除文件
     */
    public static void deletePhotos(String[] names) {
        Configuration cfg = new Configuration(Region.region0());
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        BucketManager.BatchOperations batchOperations = new BucketManager.BatchOperations();
        batchOperations.addDeleteOp(BUCKET_NAME, names);
        try {
            bucketManager.batch(batchOperations);
        } catch (QiniuException e) {
            throw new KnownException(ExceptionEnum.FILE_IO_EXCEPTION);
        }
        System.out.println("删除成功");
    }
}
