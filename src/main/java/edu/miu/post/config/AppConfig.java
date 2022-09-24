package edu.miu.post.config;

import eye2web.modelmapper.ModelMapper;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppConfig {

    @Value("${minio.access.name}")
    private String minioAccessKey;
    @Value("${minio.access.secret}")
    private String minioSecretKey;
    @Value("${minio.url}")
    private String minioUrl;

    @Value("${minio.bucket.name}")
    private String minioBucketName;


    @Bean
    MinioClient minioClient() {
        MinioClient minioClient =
                MinioClient.builder()
                        .endpoint(minioUrl)
                        .credentials(minioAccessKey, minioSecretKey)
                        .build();
        return minioClient;
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
