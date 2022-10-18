package ren.shuaipeng.minio.demo;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {

    @Bean
    public MinioClient client(@Value("${minio.url}") String url,
                              @Value("${minio.key}") String key,
                              @Value("${minio.secret}") String secret
                              ) {
        MinioClient minioClient =
                MinioClient.builder()
                        .endpoint(url)
                        .credentials(key, secret)
                        .build();
        return minioClient;
    }
}
