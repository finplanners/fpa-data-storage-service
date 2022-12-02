package com.gcp.storage.controller;

import com.gcp.storage.model.FPDetails;
import com.gcp.storage.service.GCPStorageService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping ("/gcp")
public class GCPStorageController {

    @Autowired
    private GCPStorageService gcpStorageService;

    @PostMapping("/save-data")
    public String saveData(
            @RequestBody FPDetails fpDetails
    ) {
        return gcpStorageService.saveData(fpDetails);
    }

    @GetMapping ("/get-data/{updatedBy}")
    public List<FPDetails> getData(
            @PathVariable String updatedBy
    ) {
        return gcpStorageService.getDataByUpdatedBy(updatedBy);
    }

    @PostMapping("/{bucketName}/upload-file")
    public String uploadFile(
            @PathVariable String bucketName,
            @RequestParam("file") MultipartFile multipartFile
    ) throws IOException {
        return gcpStorageService.uploadFile(bucketName, multipartFile);
    }

    @GetMapping ("/download-file/{fileName}")
    public String download(
            @PathVariable String fileName
    ) {
        return gcpStorageService.downloadFile(fileName);
    }
}