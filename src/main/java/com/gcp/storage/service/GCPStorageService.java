package com.gcp.storage.service;

import com.gcp.storage.model.FPDetails;
import com.gcp.storage.repository.GCPStorageRepository;
import com.google.api.gax.paging.Page;
import com.google.cloud.storage.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.springframework.web.servlet.function.AsyncServerResponse.create;

@Service
public class GCPStorageService {

    private static String BUCKET_NAME = "fp_bucket_i2";
    @Autowired
    private Storage storage;

    @Autowired
    GCPStorageRepository gcpStorageRepository;

    @Value("${spring.cloud.gcp.project-id}")
    private String projectId;

    public Bucket createBucket(String bucketName) {
        Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();

        return storage.create(BucketInfo.newBuilder(bucketName).build());
    }

    public String uploadFile(String bucketName, MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        BlobId blobId = BlobId.of(bucketName, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

        byte[] arr = multipartFile.getBytes();
        storage.create(blobInfo, arr);
        return "File " + fileName + " has been successfully uploaded to GCP";
    }

    public String downloadFile(String fileName) {
        Bucket bucket = storage.get(BUCKET_NAME);
        Page<Blob> blobs = bucket.list();
        for (Blob blob: blobs.getValues()) {
            System.out.println("file name -> " + blob.getName());
            if (fileName.equals(blob.getName())) {
                return new String(blob.getContent());
            }
        }
        return "file not found";
    }

    public String saveData(FPDetails fpDetails) {
        FPDetails fpDetailsSaved = (FPDetails) gcpStorageRepository.save(fpDetails);
        return fpDetailsSaved.toString();
    }

    public List<FPDetails> getDataByUpdatedBy(String updatedBy) {
        return gcpStorageRepository.findAllByUpdatedBy(updatedBy);
    }
}
