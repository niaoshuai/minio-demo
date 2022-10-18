package ren.shuaipeng.minio.demo;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.UploadObjectArgs;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@RestController
@RequestMapping("/minio")
@RequiredArgsConstructor
public class UploadController {

    private final MinioClient minioClient;

    @PostMapping("/upload")
    public String upload(MultipartFile file) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        // mini上传
        String originalFilename = file.getOriginalFilename();
        if (!StringUtils.hasText(originalFilename)) {
            throw new RuntimeException();
        }
        String fileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
        String objectName = "20221018" + "/" + fileName;
        PutObjectArgs objectArgs = PutObjectArgs.builder().bucket("test").object(objectName).stream(file.getInputStream(), file.getSize(), -1).contentType(file.getContentType()).build();
        minioClient.putObject(objectArgs);
        return objectName;
    }
}
