package com.hubu.aspirin.util;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.hubu.aspirin.common.KnownException;
import com.hubu.aspirin.enums.ExceptionEnum;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * 七牛云工具类
 * @author alex
 */
@Slf4j
public class QiniuUtils {
    public static String BASE_URL = "http://qiniu-cdn.alexyan.cn/";

    private static String ACCESS_KEY = "JjdzmIiQdIMab9opiMa5qr_Jcp11U9VQQGvCYbav";

    private static String SECRET_KEY = "ixDpPaUVRgbSr7OknyCEhRx_RW9r-cyFBX43INft";

    private static String BUCKET_NAME = "hubu-aspirin";


    /**
     * 上传凭证有效期 10min
     */
    private static long expireSeconds = 600;

    /**
     * 默认下载路径
     */
    private static String DEFAULT_DOWNLOAD_PATH = "D:/photos/";

    private static Auth auth;
    private static UploadManager uploadManager;
    private static Configuration config;

    static {
        auth = Auth.create(ACCESS_KEY,SECRET_KEY);
        // Region.region2是指华南
        config = new Configuration(Region.autoRegion());
        uploadManager = new UploadManager(config);
    }



    /**
     * 上传文件
     *
     * @param file 文件
     * @return 图片存储的url
     */
    public static String uploadFile(File file) {
        String fileName = file.getName();
        try {
            InputStream inputStream = new FileInputStream(file);
            String upToken = auth.uploadToken(BUCKET_NAME, fileName, expireSeconds, null);
            Response response = uploadManager.put(inputStream, fileName, upToken, null, null);
        } catch (QiniuException e) {
            log.error(e.response.toString());
            throw new KnownException(ExceptionEnum.FILE_IO_EXCEPTION);
        } catch (IOException e) {
            log.error(Arrays.toString(e.getStackTrace()));
            throw new KnownException(ExceptionEnum.FILE_IO_EXCEPTION);
        }
        return BASE_URL + fileName;
    }

    /**
     *  字节数组上传文件时调用该方法
     * @param uploadKey: 文件在七牛云中的存储索引
     */
    public static String uploadFile(byte[] bytes, String uploadKey) {
        try {
            String upToken = auth.uploadToken(BUCKET_NAME, uploadKey, expireSeconds, null);
            Response response = uploadManager.put(bytes, uploadKey, upToken);
        }catch (QiniuException e) {
            log.error(e.response.toString());
            throw new KnownException(ExceptionEnum.FILE_IO_EXCEPTION);
        }
        return BASE_URL + uploadKey;
    }

    /**
     * 删除存储空间的文件
     *
     * @param key:待删除的文件在存储空间的索引
     */
    public static boolean deleteFile(String key) {
        BucketManager bucketManager = new BucketManager(auth, config);
        try {
            bucketManager.delete(BUCKET_NAME, key);
        } catch (QiniuException e) {
            throw new KnownException(ExceptionEnum.FILE_IO_EXCEPTION);
        }
        return true;
    }

    /**
     * 批量删除文件
     */
    public static boolean deleteFiles(String[] names) {
        BucketManager bucketManager = new BucketManager(auth, config);
        BucketManager.BatchOperations batchOperations = new BucketManager.BatchOperations();
        batchOperations.addDeleteOp(BUCKET_NAME, names);
        try {
            bucketManager.batch(batchOperations);
        } catch (QiniuException e) {
            throw new KnownException(ExceptionEnum.FILE_IO_EXCEPTION);
        }
        return true;
    }
}
