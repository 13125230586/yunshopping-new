package com.yunshen.yunshoppingbackend.manager;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectResult;
import com.yunshen.yunshoppingbackend.config.OssClientConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 阿里云OSS管理类
 */
@Component
@Slf4j
public class OssManager {

    @Resource
    private OssClientConfig ossClientConfig;

    @Resource
    private OSS ossClient;

    /**
     * 上传文件
     *
     * @param key  唯一键
     * @param file 文件
     * @return 上传结果
     */
    public PutObjectResult putObject(String key, File file) {
        try {
            InputStream inputStream = new FileInputStream(file);
            PutObjectResult result = ossClient.putObject(
                    ossClientConfig.getBucketName(),
                    key,
                    inputStream
            );
            log.info("OSS文件上传成功 bucketName:{} key:{}", ossClientConfig.getBucketName(), key);
            return result;
        } catch (Exception e) {
            log.error("OSS文件上传失败 bucketName:{} key:{}", ossClientConfig.getBucketName(), key, e);
            throw new RuntimeException("文件上传失败", e);
        }
    }

    /**
     * 下载文件
     *
     * @param key 唯一键
     * @return OSS对象
     */
    public OSSObject getObject(String key) {
        try {
            OSSObject ossObject = ossClient.getObject(ossClientConfig.getBucketName(), key);
            log.info("OSS文件下载成功 bucketName:{} key:{}", ossClientConfig.getBucketName(), key);
            return ossObject;
        } catch (Exception e) {
            log.error("OSS文件下载失败 bucketName:{} key:{}", ossClientConfig.getBucketName(), key, e);
            throw new RuntimeException("文件下载失败", e);
        }
    }

    /**
     * 删除文件
     *
     * @param key 文件key
     */
    public void deleteObject(String key) throws OSSException {
        try {
            ossClient.deleteObject(ossClientConfig.getBucketName(), key);
            log.info("OSS文件删除成功 bucketName:{} key:{}", ossClientConfig.getBucketName(), key);
        } catch (Exception e) {
            log.error("OSS文件删除失败 bucketName:{} key:{}", ossClientConfig.getBucketName(), key, e);
            throw e;
        }
    }
}