package com.ternak.sapi.controller;

import com.ternak.sapi.payload.DefaultResponse;
import com.ternak.sapi.payload.PagedResponse;
import com.ternak.sapi.payload.ApiResponse;
import com.ternak.sapi.payload.PeternakRequest;
import com.ternak.sapi.model.Peternak;
import com.ternak.sapi.model.Peternak;
import com.ternak.sapi.repository.PeternakRepository;
import com.ternak.sapi.service.PeternakService;
import com.ternak.sapi.service.PeternakService;
import com.ternak.sapi.util.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/api/peternak")
public class PeternakController {
    private PeternakService peternakService = new PeternakService();

    PeternakRepository peternakRepository;

    @GetMapping
    public PagedResponse<Peternak> getPeternaks(@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                              @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size,
                                              @RequestParam(value = "userID", defaultValue = "*") String userID) throws IOException {
        return peternakService.getAllPeternak(page, size, userID);
    }

    @PostMapping
    public ResponseEntity<?> createPeternak(@Valid @RequestBody PeternakRequest peternakRequest) throws IOException {
        Peternak peternak = peternakService.createPeternak(peternakRequest);

        if(peternak == null){
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Petugas ID / User ID not found"));
        }else{
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{peternakId}")
                    .buildAndExpand(peternak.getIdPeternak()).toUri();

            return ResponseEntity.created(location)
                    .body(new ApiResponse(true, "Peternak Created Successfully"));
        }
    }

    @GetMapping("/{peternakId}")
    public DefaultResponse<Peternak> getPeternakById(@PathVariable String peternakId) throws IOException {
        return peternakService.getPeternakById(peternakId);
    }


    @PutMapping("/{peternakId}")
    public ResponseEntity<?> updatePeternak(@PathVariable String peternakId,
                                           @Valid @RequestBody PeternakRequest peternakRequest) throws IOException {
        Peternak peternak = peternakService.updatePeternak(peternakId, peternakRequest);

        if(peternak == null){
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Petugas ID / User ID not found"));
        }else{
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{peternakId}")
                    .buildAndExpand(peternak.getIdPeternak()).toUri();

            return ResponseEntity.created(location)
                    .body(new ApiResponse(true, "Peternak Updated Successfully"));
        }
    }

    @DeleteMapping("/{peternakId}")
    public HttpStatus deletePeternak(@PathVariable (value = "peternakId") String peternakId) throws IOException {
        peternakService.deletePeternakById(peternakId);
        return HttpStatus.FORBIDDEN;
    }
}
