package com.ternak.sapi.controller;

import com.ternak.sapi.model.Vaksin;
import com.ternak.sapi.payload.ApiResponse;
import com.ternak.sapi.payload.DefaultResponse;
import com.ternak.sapi.payload.VaksinRequest;
import com.ternak.sapi.payload.PagedResponse;
import com.ternak.sapi.service.VaksinService;
import com.ternak.sapi.util.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/api/vaksin")
public class VaksinController {
    private VaksinService vaksinService = new VaksinService();

    @GetMapping
    public PagedResponse<Vaksin> getVaksins(@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                    @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size,
                                                    @RequestParam(value = "peternakID", defaultValue = "*") String peternakID,
                                                    @RequestParam(value = "petugasID", defaultValue = "*") String petugasID,
                                                    @RequestParam(value = "hewanID", defaultValue = "*") String hewanID) throws IOException {
        return vaksinService.getAllVaksin(page, size, peternakID, petugasID, hewanID);
    }

//    @PostMapping
//    public ResponseEntity<?> createVaksin(@Valid @RequestBody VaksinRequest vaksinRequest) throws IOException {
//        Vaksin vaksin = vaksinService.createVaksin(vaksinRequest);
//
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest().path("/{vaksinId}")
//                .buildAndExpand(vaksin.getIdVaksin()).toUri();
//
//        return ResponseEntity.created(location)
//                .body(new ApiResponse(true, "Vaksin Created Successfully"));
//    }
    
    @PostMapping
    public ResponseEntity<?> createVaksin(@Valid @RequestBody VaksinRequest vaksinRequest) throws IOException {
        Vaksin vaksin = vaksinService.createVaksin(vaksinRequest);

        if(vaksin == null){
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Vaksin ID / User ID not found"));
        }else{
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{vaksinId}")
                    .buildAndExpand(vaksin.getIdVaksin()).toUri();

            return ResponseEntity.created(location)
                    .body(new ApiResponse(true, "vaksin Created Successfully"));
        }
    }

    @GetMapping("/{vaksinId}")
    public DefaultResponse<Vaksin> getVaksinById(@PathVariable String vaksinId) throws IOException {
        return vaksinService.getVaksinById(vaksinId);
    }


    @PutMapping("/{vaksinId}")
    public ResponseEntity<?> updateVaksin(@PathVariable String vaksinId,
                                              @Valid @RequestBody VaksinRequest vaksinRequest) throws IOException {
        Vaksin vaksin = vaksinService.updateVaksin(vaksinId, vaksinRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{vaksinId}")
                .buildAndExpand(vaksin.getIdVaksin()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Vaksin Updated Successfully"));
    }

    @DeleteMapping("/{vaksinId}")
    public HttpStatus deleteVaksin(@PathVariable (value = "vaksinId") String vaksinId) throws IOException {
        vaksinService.deleteVaksinById(vaksinId);
        return HttpStatus.FORBIDDEN;
    }
}
