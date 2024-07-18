package com.ternak.sapi.controller;

import com.ternak.sapi.model.Hewan;
import com.ternak.sapi.model.Peternak;
import com.ternak.sapi.payload.ApiResponse;
import com.ternak.sapi.payload.DefaultResponse;
import com.ternak.sapi.payload.HewanRequest;
import com.ternak.sapi.payload.PagedResponse;
import com.ternak.sapi.service.HewanService;
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
@RequestMapping("/api/hewan")
public class HewanController {
    private HewanService hewanService;
    
    @Autowired
    public HewanController(HewanService hewanService) {
        this.hewanService = hewanService;
    }

    @GetMapping
    public PagedResponse<Hewan> getHewans(@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size,
                                            @RequestParam(value = "peternakID", defaultValue = "*") String peternakID,
                                            @RequestParam(value = "petugasID", defaultValue = "*") String petugasID,
                                            @RequestParam(value = "kandangID", defaultValue = "*") String kandangID) throws IOException {
        return hewanService.getAllHewan(page, size, peternakID, petugasID, kandangID);
    }

    @PostMapping
    public ResponseEntity<?> createHewan(@RequestPart(value = "file", required = false) MultipartFile file, @ModelAttribute HewanRequest hewanRequest) throws IOException {

        try {
          Hewan hewan = hewanService.createHewan(hewanRequest, file);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{hewanId}")
                .buildAndExpand(hewan.getKodeEartagNasional()).toUri();
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Hewan Created Successfully"));
        } catch (IOException e) {
            // Penanganan kesalahan saat menyimpan file
            e.printStackTrace();
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Cannot Upload File into Hadoop"));
        }
    }

    @GetMapping("/{hewanId}")
    public DefaultResponse<Hewan> getHewanById(@PathVariable String hewanId) throws IOException {
        return hewanService.getHewanById(hewanId);
    }

    @PutMapping("/{hewanId}")
    public ResponseEntity<?> updateHewan(@PathVariable String hewanId,
                                          @RequestParam("file") MultipartFile file, @ModelAttribute HewanRequest hewanRequest) throws IOException {
        // upload file
        
        try {
            Hewan hewan = hewanService.updateHewan(hewanId, hewanRequest, file);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{hewanId}")
                .buildAndExpand(hewan.getKodeEartagNasional()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Hewan Updated Successfully"));
        } catch (IOException e) {
            // Penanganan kesalahan saat menyimpan file
            e.printStackTrace();
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Cannot Upload File into Hadoop"));
        }

    }

    @DeleteMapping("/{hewanId}")
    public HttpStatus deleteHewan(@PathVariable (value = "hewanId") String hewanId) throws IOException {
        hewanService.deleteHewanById(hewanId);
        return HttpStatus.FORBIDDEN;
    }
}
