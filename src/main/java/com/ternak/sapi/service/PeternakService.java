package com.ternak.sapi.service;

import com.ternak.sapi.repository.UserRepository;
import com.ternak.sapi.repository.PetugasRepository;
import com.ternak.sapi.model.User;
import com.ternak.sapi.model.Petugas;
import com.ternak.sapi.model.Peternak;
import com.ternak.sapi.exception.BadRequestException;
import com.ternak.sapi.exception.ResourceNotFoundException;
import com.ternak.sapi.payload.DefaultResponse;
import com.ternak.sapi.payload.PeternakRequest;
import com.ternak.sapi.payload.PagedResponse;
import com.ternak.sapi.repository.PeternakRepository;
import com.ternak.sapi.util.AppConstants;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PeternakService {
    private PeternakRepository peternakRepository = new PeternakRepository();
    private PetugasRepository petugasRepository = new PetugasRepository();
    private UserRepository userRepository = new UserRepository();

    private static final Logger logger = LoggerFactory.getLogger(PeternakService.class);


    public PagedResponse<Peternak> getAllPeternak(int page, int size, String userID) throws IOException {
        validatePageNumberAndSize(page, size);
        List<Peternak> peternakResponse = new ArrayList<>();

        if(userID.equalsIgnoreCase("*")) peternakResponse = peternakRepository.findAll(size);
        if(!userID.equalsIgnoreCase("*")) peternakResponse = peternakRepository.findAllByUserID(userID, size);

        return new PagedResponse<>(peternakResponse, peternakResponse.size(), "Successfully get data", 200);
    }

    public Peternak createPeternak(PeternakRequest peternakRequest) throws IOException {
        Peternak peternak = new Peternak();
        
        Petugas petugasResponse = petugasRepository.findById(peternakRequest.getPetugas_id()!= "" ? peternakRequest.getPetugas_id() : "0");
        if (petugasResponse.getNamaPetugas()!= null ) {
            peternak.setIdPeternak(peternakRequest.getIdPeternak());
            peternak.setNikPeternak(peternakRequest.getNikPeternak());
            peternak.setNamaPeternak(peternakRequest.getNamaPeternak());
            peternak.setIdISIKHNAS(peternakRequest.getIdISIKHNAS());
            peternak.setLokasi(peternakRequest.getLokasi());
            peternak.setTanggalPendaftaran(peternakRequest.getTanggalPendaftaran());
            peternak.setPetugas(petugasResponse);
            return peternakRepository.save(peternak);
        } else {
            return null;
        }
    }

    public DefaultResponse<Peternak> getPeternakById(String peternakId) throws IOException {
        // Retrieve Peternak
        Peternak peternakResponse = peternakRepository.findById(peternakId);
        return new DefaultResponse<>(peternakResponse.isValid() ? peternakResponse : null, peternakResponse.isValid() ? 1 : 0, "Successfully get data");
    }

    public Peternak updatePeternak(String peternakId, PeternakRequest peternakRequest) throws IOException {
        Peternak peternak = new Peternak();
        Petugas petugasResponse = petugasRepository.findById(peternakRequest.getPetugas_id()!= "" ? peternakRequest.getPetugas_id() : "0");

        if (petugasResponse.getNamaPetugas()!= null ) {
            peternak.setNikPeternak(peternakRequest.getNikPeternak());
            peternak.setNamaPeternak(peternakRequest.getNamaPeternak());
            peternak.setIdISIKHNAS(peternakRequest.getIdISIKHNAS());
            peternak.setLokasi(peternakRequest.getLokasi());
            peternak.setTanggalPendaftaran(peternakRequest.getTanggalPendaftaran());
            peternak.setPetugas(petugasResponse);
            return peternakRepository.update(peternakId, peternak);
        } else {
            return null;
        }
    }

    public void deletePeternakById(String peternakId) throws IOException {
        Peternak peternakResponse = peternakRepository.findById(peternakId);
        if(peternakResponse.isValid()){
            peternakRepository.deleteById(peternakId);
        }else{
            throw new ResourceNotFoundException("Peternak", "id", peternakId);
        }
    }

    private void validatePageNumberAndSize(int page, int size) {
        if(page < 0) {
            throw new BadRequestException("Page number cannot be less than zero.");
        }

        if(size > AppConstants.MAX_PAGE_SIZE) {
            throw new BadRequestException("Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
        }
    }

}
