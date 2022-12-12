package com.jyy.minofiles.control;

import com.jyy.minofiles.template.MinioTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/minio")
public class MinioController {
    private final MinioTemplate minioTemplate;

    @Autowired
    public MinioController(MinioTemplate minioTemplate) {
        this.minioTemplate = minioTemplate;
    }

    @Value("${minio.server}")
    private String server;

    @Value("${minio.port}")
    private Integer port;

    @Value("${minio.bucket}")
    private String bucket;

    @GetMapping(value = "/hello")
    public String Hello() {
        return "hello, minio files service";
    }

    @PostMapping(value = "/uploadone")
    public Object uploadOne(MultipartFile file) {
        return minioTemplate.upload(file);
    }

    /**
     * 单文件下载
     *
     * @param fileName 文件
     */
    @GetMapping(value = "/download")
    public void download(@RequestParam(name = "fileName") String fileName, HttpServletResponse response) {
        minioTemplate.fileDownload(fileName, response);
    }

    /**
     * 查看存储的文件列表
     *
     * @param bucket 桶
     * @return
     */
    @GetMapping(value = "/list")
    public Object fileList(@RequestParam(name = "bucket") String bucket) {
        return minioTemplate.listObjects(bucket);
    }

    /**
     * 删除文件
     *
     * @param bucket 桶
     * @param list   文件名列表
     * @return
     */
    @DeleteMapping(value = "/remove")
    public Object fileremove(@RequestParam String bucket, @RequestParam List<String> list) {
        return minioTemplate.removeObjects(bucket, list);
    }
}
