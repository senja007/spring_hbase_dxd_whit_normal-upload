package com.ternak.sapi.service;

import com.ternak.sapi.repository.UserRepository;
import com.ternak.sapi.repository.PetugasRepository;
import com.ternak.sapi.model.User;
import com.ternak.sapi.model.Petugas;
import com.ternak.sapi.exception.BadRequestException;
import com.ternak.sapi.exception.ResourceNotFoundException;
import com.ternak.sapi.payload.DefaultResponse;
import com.ternak.sapi.payload.PetugasRequest;
import com.ternak.sapi.payload.PagedResponse;
import com.ternak.sapi.repository.PetugasRepository;
import com.ternak.sapi.util.AppConstants;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PetugasService {
    private PetugasRepository petugasRepository = new PetugasRepository();
    private UserRepository userRepository = new UserRepository();

    private static final Logger logger = LoggerFactory.getLogger(PetugasService.class);


    public PagedResponse<Petugas> getAllPetugas(int page, int size, String userID) throws IOException {
        validatePageNumberAndSize(page, size);
        List<Petugas> petugasResponse = new ArrayList<>();

        if(userID.equalsIgnoreCase("*")) petugasResponse = petugasRepository.findAll(size);
        if(!userID.equalsIgnoreCase("*")) petugasResponse = petugasRepository.findAllByUserID(userID, size);

        return new PagedResponse<>(petugasResponse, petugasResponse.size(), "Successfully get data", 200);
    }


    public Petugas createPetugas(PetugasRequest petugasRequest) throws IOException {
        Petugas petugas = new Petugas();
            petugas.setNikPetugas(petugasRequest.getNikPetugas());
            petugas.setNamaPetugas(petugasRequest.getNamaPetugas());
            petugas.setNoTelp(petugasRequest.getNoTelp());
            petugas.setEmail(petugasRequest.getEmail());
            
            return petugasRepository.save(petugas);
        
    }
    
//    public Petugas createPetugas(PetugasRequest petugasRequest) throws IOException {
//        Petugas petugas = new Petugas();
//        if(petugasRepository.existsByUserID(petugasRequest.getUser_id())) {
//            return null;
//        }
//        User userResponse = userRepository.findById(petugasRequest.getUser_id() != "" ? petugasRequest.getUser_id() : "0");
//        if (userResponse.getName() != null) {
//            petugas.setNikPetugas(petugasRequest.getNikPetugas());
//            petugas.setNamaPetugas(petugasRequest.getNamaPetugas());
//            petugas.setNoTelp(petugasRequest.getNoTelp());
//            petugas.setEmail(petugasRequest.getEmail());
//            petugas.setUser(userResponse);
//            return petugasRepository.save(petugas);
//        } else {
//            return null;
//        }
//    }

    public DefaultResponse<Petugas> getPetugasById(String petugasId) throws IOException {
        // Retrieve Petugas
        Petugas petugasResponse = petugasRepository.findById(petugasId);
        return new DefaultResponse<>(petugasResponse.isValid() ? petugasResponse : null, petugasResponse.isValid() ? 1 : 0, "Successfully get data");
    }

    public Petugas updatePetugas(String petugasId, PetugasRequest petugasRequest) throws IOException {
        Petugas petugas = new Petugas();
        
            petugas.setNamaPetugas(petugasRequest.getNamaPetugas());
            petugas.setNoTelp(petugasRequest.getNoTelp());
            petugas.setEmail(petugasRequest.getEmail());
            return petugasRepository.update(petugasId, petugas);
        
    }

    public void deletePetugasById(String petugasId) throws IOException {
        Petugas petugasResponse = petugasRepository.findById(petugasId);
        if(petugasResponse.isValid()){
            petugasRepository.deleteById(petugasId);
        }else{
            throw new ResourceNotFoundException("Petugas", "id", petugasId);
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
