package com.ternak.sapi.controller;

import com.ternak.sapi.model.Kelahiran;
import com.ternak.sapi.payload.ApiResponse;
import com.ternak.sapi.payload.DefaultResponse;
import com.ternak.sapi.payload.KelahiranRequest;
import com.ternak.sapi.payload.PagedResponse;
import com.ternak.sapi.service.KelahiranService;
import com.ternak.sapi.util.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/api/kelahiran")
public class KelahiranController {
    private KelahiranService kelahiranService = new KelahiranService();

    @GetMapping
    public PagedResponse<Kelahiran> getKelahirans(@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                    @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size,
                                                    @RequestParam(value = "peternakID", defaultValue = "*") String peternakID,
                                                    @RequestParam(value = "petugasID", defaultValue = "*") String petugasID,
                                                    @RequestParam(value = "hewanID", defaultValue = "*") String hewanID) throws IOException {
        return kelahiranService.getAllKelahiran(page, size, peternakID, petugasID, hewanID);
    }

    @PostMapping
    public ResponseEntity<?> createKelahiran(@Valid @RequestBody KelahiranRequest kelahiranRequest) throws IOException {
        Kelahiran kelahiran = kelahiranService.createKelahiran(kelahiranRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{kelahiranId}")
                .buildAndExpand(kelahiran.getIdKejadian()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Kelahiran Created Successfully"));
    }

    @GetMapping("/{kelahiranId}")
    public DefaultResponse<Kelahiran> getKelahiranById(@PathVariable String kelahiranId) throws IOException {
        return kelahiranService.getKelahiranById(kelahiranId);
    }


    @PutMapping("/{kelahiranId}")
    public ResponseEntity<?> updateKelahiran(@PathVariable String kelahiranId,
                                              @Valid @RequestBody KelahiranRequest kelahiranRequest) throws IOException {
        Kelahiran kelahiran = kelahiranService.updateKelahiran(kelahiranId, kelahiranRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{kelahiranId}")
                .buildAndExpand(kelahiran.getIdKejadian()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Kelahiran Updated Successfully"));
    }

    @DeleteMapping("/{kelahiranId}")
    public HttpStatus deleteKelahiran(@PathVariable (value = "kelahiranId") String kelahiranId) throws IOException {
        kelahiranService.deleteKelahiranById(kelahiranId);
        return HttpStatus.FORBIDDEN;
    }
}
