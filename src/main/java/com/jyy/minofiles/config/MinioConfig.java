package com.jyy.minofiles.config;

import io.minio.MinioClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "minio")
public class MinioConfig {
    // 服务器地址
    private String server;

    //服务器端口
    private int port;

    // minio 用户名
    private String accessKey;

    // minio 密码
    private String secretKey;

    // 桶名
    private String bucket;

    // 统一前缀
    private String urlPrefix;

    /**
     * 创建minio连接对象
     * @return
     */
    @Bean
    public MinioClient minioClient(){
        return  MinioClient.builder()
                .endpoint(server,port,false)
                .credentials(accessKey,secretKey)
                .build();
    }

    public void setServer(String server) {
        this.server = server;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }


    public void setUrlPrefix(String urlPrefix) { this.urlPrefix = urlPrefix; }

    public String getUrlPrefix() {
        return urlPrefix;
    }

    public String getServer() {
        return server;
    }

    public int getPort() {
        return port;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public String getBucket() {
        return bucket;
    }
}
