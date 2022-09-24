package edu.miu.post.service;

import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import org.apache.commons.compress.utils.FileNameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class MinioFileStorage implements Storage {
    private Logger logger = Logger.getLogger(MinioFileStorage.class.getSimpleName());

    @Value("${minio.bucket.name}")
    String bucketName;
    @Autowired
    private MinioClient minioClient;

    @Override
    public String uploadFile(MultipartFile file) throws Exception {


        String extension = FileNameUtils.getExtension(file.getOriginalFilename());

        logger.info("Uploading file " + file.getName() + " to Minio Bucket");

        String name = UUID.randomUUID().toString() + "." + extension;

        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-type", file.getContentType());


        PutObjectArgs putObjectArgs = PutObjectArgs
                .builder()
                .bucket(bucketName)
                .object(name)
                .userMetadata(metadata)
                .stream(file.getInputStream(), file.getSize(), 5 * 1024 * 1024 * 8)
                .build();
        ObjectWriteResponse response = minioClient.putObject(putObjectArgs);
        return  bucketName + "/" + name;
    }
}
