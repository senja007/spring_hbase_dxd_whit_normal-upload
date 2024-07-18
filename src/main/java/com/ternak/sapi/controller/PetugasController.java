package com.ternak.sapi.controller;

import com.ternak.sapi.payload.DefaultResponse;
import com.ternak.sapi.payload.PagedResponse;
import com.ternak.sapi.payload.ApiResponse;
import com.ternak.sapi.payload.PetugasRequest;
import com.ternak.sapi.model.Petugas;
import com.ternak.sapi.model.Petugas;
import com.ternak.sapi.repository.PetugasRepository;
import com.ternak.sapi.service.PetugasService;
import com.ternak.sapi.service.PetugasService;
import com.ternak.sapi.util.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/api/petugas")
public class PetugasController {
    private PetugasService petugasService = new PetugasService();

    PetugasRepository petugasRepository;

    @GetMapping
    public PagedResponse<Petugas> getPetugass(@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                              @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size,
                                              @RequestParam(value = "userID", defaultValue = "*") String userID) throws IOException {
        return petugasService.getAllPetugas(page, size, userID);
    }

    @PostMapping
    public ResponseEntity<?> createPetugas(@Valid @RequestBody PetugasRequest petugasRequest) throws IOException {
        Petugas petugas = petugasService.createPetugas(petugasRequest);

        if(petugas == null){
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "User ID not found"));
        }else{
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{petugasId}")
                    .buildAndExpand(petugas.getNikPetugas()).toUri();

            return ResponseEntity.created(location)
                    .body(new ApiResponse(true, "Petugas Created Successfully"));
        }
    }

    @GetMapping("/{petugasId}")
    public DefaultResponse<Petugas> getPetugasById(@PathVariable String petugasId) throws IOException {
        return petugasService.getPetugasById(petugasId);
    }


    @PutMapping("/{petugasId}")
    public ResponseEntity<?> updatePetugas(@PathVariable String petugasId,
                                           @Valid @RequestBody PetugasRequest petugasRequest) throws IOException {
        Petugas petugas = petugasService.updatePetugas(petugasId, petugasRequest);

        if(petugas == null){
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "User ID not found"));
        }else{
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{petugasId}")
                    .buildAndExpand(petugas.getNikPetugas()).toUri();

            return ResponseEntity.created(location)
                    .body(new ApiResponse(true, "Petugas Updated Successfully"));
        }
    }

    @DeleteMapping("/{petugasId}")
    public HttpStatus deletePetugas(@PathVariable (value = "petugasId") String petugasId) throws IOException {
        petugasService.deletePetugasById(petugasId);
        return HttpStatus.FORBIDDEN;
    }
}
