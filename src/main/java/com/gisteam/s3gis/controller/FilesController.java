package com.gisteam.s3gis.controller;

import com.gisteam.s3gis.service.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/fileService")
public class FilesController {

    @Autowired
    private FilesService service;

    @PostMapping("/uploadFile")
    public ResponseEntity<String> uploadFile(@RequestParam(value = "target") MultipartFile file) {
        return new ResponseEntity<>(service.uploadFile(file), HttpStatus.OK);
    }

    @GetMapping("/downloadFile/{targetName}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String targetName) {
        byte[] data = service.downloadFile(targetName);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; targetName=\"" + targetName + "\"")
                .body(resource);
    }

    @DeleteMapping("/deleteFile/{targetName}")
    public ResponseEntity<String> deleteFile(@PathVariable String targetName) {
        return new ResponseEntity<>(service.deleteFile(targetName), HttpStatus.OK);
    }
}
