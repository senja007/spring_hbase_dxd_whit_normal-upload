package com.ternak.sapi.controller;


import com.ternak.sapi.model.Berita;
import com.ternak.sapi.model.Berita;
import com.ternak.sapi.payload.BeritaRequest;
import com.ternak.sapi.payload.ApiResponse;
import com.ternak.sapi.payload.DefaultResponse;
import com.ternak.sapi.payload.BeritaRequest;
import com.ternak.sapi.payload.PagedResponse;
import com.ternak.sapi.service.BeritaService;
import com.ternak.sapi.util.AppConstants;
import java.io.File;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.UUID;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/berita")
public class BeritaController {
    private BeritaService beritaService;
    
    @Autowired
    public BeritaController(BeritaService beritaService) {
        this.beritaService = beritaService;
    }

    @GetMapping
    public PagedResponse<Berita> getBeritas(@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                    @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) throws IOException {
        return beritaService.getAllBerita(page, size);
    }

    @PostMapping
    public ResponseEntity<?> createBerita(@Valid @ModelAttribute BeritaRequest beritaRequest,
                                          @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            Berita berita = beritaService.createBerita(beritaRequest, file);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{beritaId}")
                    .buildAndExpand(berita.getIdBerita()).toUri();
            return ResponseEntity.created(location)
                    .body(new ApiResponse(true, "Berita Created Successfully"));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Error Creating Berita"));
        }
    }


    @GetMapping("/{beritaId}")
    public DefaultResponse<Berita> getBeritaById(@PathVariable String beritaId) throws IOException {
        return beritaService.getBeritaById(beritaId);
    }


    @PutMapping("/{beritaId}")
    public ResponseEntity<?> updateBerita(@PathVariable String beritaId,
                                          @RequestParam("file") MultipartFile file, @ModelAttribute BeritaRequest beritaRequest) throws IOException {
        // upload file
        try {
            Berita berita = beritaService.updateBerita(beritaId, beritaRequest, file);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{beritaId}")
                .buildAndExpand(berita.getIdBerita()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Berita Updated Successfully"));
        } catch (IOException e) {
            // Penanganan kesalahan saat menyimpan file
            e.printStackTrace();
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Cannot Upload File into Hadoop"));
        }


    }

    @DeleteMapping("/{beritaId}")
    public HttpStatus deleteBerita(@PathVariable (value = "beritaId") String beritaId) throws IOException {
        beritaService.deleteBeritaById(beritaId);
        return HttpStatus.FORBIDDEN;
    }
}
