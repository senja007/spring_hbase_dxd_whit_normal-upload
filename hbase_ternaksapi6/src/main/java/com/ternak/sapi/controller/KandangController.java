package com.ternak.sapi.controller;


import com.ternak.sapi.model.Kandang;
import com.ternak.sapi.model.Peternak;
import com.ternak.sapi.payload.ApiResponse;
import com.ternak.sapi.payload.DefaultResponse;
import com.ternak.sapi.payload.KandangRequest;
import com.ternak.sapi.payload.PagedResponse;
import com.ternak.sapi.service.KandangService;
import com.ternak.sapi.util.AppConstants;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/kandang")
public class KandangController {
    private KandangService kandangService;
    
    @Autowired
    public KandangController(KandangService kandangService) {
        this.kandangService = kandangService;
    }

    @GetMapping
    public PagedResponse<Kandang> getKandangs(@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size,
                                            @RequestParam(value = "peternakID", defaultValue = "*") String peternakID) throws IOException {
        return kandangService.getAllKandang(page, size, peternakID);
    }

    @PostMapping
    public ResponseEntity<?> createKandang(@RequestPart(value = "file", required = false) MultipartFile file, @ModelAttribute KandangRequest kandangRequest) throws IOException {

        try {
            Kandang kandang = kandangService.createKandang(kandangRequest, file);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{kandangId}")
                    .buildAndExpand(kandang.getIdKandang()).toUri();
            return ResponseEntity.created(location)
                    .body(new ApiResponse(true, "Kandang Created Successfully"));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Error Creating Kandang"));
        }
    }

    @GetMapping("/{kandangId}")
    public DefaultResponse<Kandang> getKandangById(@PathVariable String kandangId) throws IOException {
        return kandangService.getKandangById(kandangId);
    }

    @PutMapping("/{kandangId}")
    public ResponseEntity<?> updateKandang(@PathVariable String kandangId,
                                          @RequestParam("file") MultipartFile file, @ModelAttribute KandangRequest kandangRequest) throws IOException {
        // upload file
        try {
            Kandang kandang = kandangService.updateKandang(kandangId, kandangRequest, file);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{kandangId}")
                .buildAndExpand(kandang.getIdKandang()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Kandang Updated Successfully"));
        } catch (IOException e) {
            // Penanganan kesalahan saat menyimpan file
            e.printStackTrace();
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Cannot Upload File into Hadoop"));
        }


    }

    @DeleteMapping("/{kandangId}")
    public HttpStatus deleteKandang(@PathVariable (value = "kandangId") String kandangId) throws IOException {
        kandangService.deleteKandangById(kandangId);
        return HttpStatus.FORBIDDEN;
    }
}
