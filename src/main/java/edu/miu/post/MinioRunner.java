package edu.miu.post;

import io.minio.ListObjectsArgs;
import io.minio.MinioClient;
import io.minio.Result;
import io.minio.UploadObjectArgs;
import io.minio.messages.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class MinioRunner implements CommandLineRunner {

    @Autowired
    private MinioClient minioClient;

    @Override
    public void run(String... args) throws Exception {
        ListObjectsArgs objectsArgs = ListObjectsArgs.builder()
                .bucket("postlist")
                .build();
        var results = minioClient.listObjects(objectsArgs);
        for (Result<Item> result : results) {
            System.out.println("Bucket items: ");
            System.out.println("Etag: " + result.get().etag() +
                    "Object name: " + result.get().objectName() +
                    "Storage Class: " + result.get().storageClass());
        }
    }


    void uploadFile() throws Exception {
        var response = minioClient.uploadObject(
                UploadObjectArgs.builder()
                        .bucket("postlist")
                        .object("myfile1.zip")
                        .filename("/Users/jenish/Desktop/postman.zip")
                        .build());
        System.out.println("Etag: " + response.etag() +
                "Object: " + response.object() +
                "" + response.region());

    }
}