package com.ternak.sapi.service;

import com.ternak.sapi.exception.BadRequestException;
import com.ternak.sapi.exception.ResourceNotFoundException;
import com.ternak.sapi.model.Vaksin;
import com.ternak.sapi.model.Hewan;
import com.ternak.sapi.model.Peternak;
import com.ternak.sapi.model.Petugas;
import com.ternak.sapi.payload.DefaultResponse;
import com.ternak.sapi.payload.VaksinRequest;
import com.ternak.sapi.payload.PagedResponse;
import com.ternak.sapi.repository.VaksinRepository;
import com.ternak.sapi.repository.HewanRepository;
import com.ternak.sapi.repository.PeternakRepository;
import com.ternak.sapi.repository.PetugasRepository;
import com.ternak.sapi.util.AppConstants;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class VaksinService {
    private VaksinRepository vaksinRepository = new VaksinRepository();
    private PeternakRepository peternakRepository = new PeternakRepository();
    private PetugasRepository petugasRepository = new PetugasRepository();
    private HewanRepository hewanRepository = new HewanRepository();

    private static final Logger logger = LoggerFactory.getLogger(VaksinService.class);


    public PagedResponse<Vaksin> getAllVaksin(int page, int size, String peternakId, String petugasId, String hewanId) throws IOException {
        validatePageNumberAndSize(page, size);
        List<Vaksin> vaksinResponse;
        if(peternakId.equalsIgnoreCase("*")){
            vaksinResponse = vaksinRepository.findAll(size);
//        }else if(hewanId != null && !hewanId.isEmpty()) {
//            vaksinResponse = vaksinRepository.findVaksinByHewan(hewanId, size);
//        }else if(petugasId != null && !petugasId.isEmpty()){
//            vaksinResponse = vaksinRepository.findVaksinByPetugas(petugasId, size);
        }else{
            vaksinResponse = vaksinRepository.findVaksinByPeternak(peternakId, size);
        }

        // Retrieve Polls


        return new PagedResponse<>(vaksinResponse, vaksinResponse.size(), "Successfully get data", 200);
    }

    public Vaksin createVaksin(VaksinRequest vaksinRequest) throws IOException {
        Vaksin vaksin = new Vaksin();
      Peternak peternakResponse = peternakRepository.findById(vaksinRequest.getPeternak_id().toString());
Petugas petugasResponse = petugasRepository.findById(vaksinRequest.getPetugas_id().toString());
Hewan hewanResponse = hewanRepository.findById(vaksinRequest.getHewan_id().toString());
        if (peternakResponse.getNamaPeternak()!= null && petugasResponse.getNamaPetugas()!= null && hewanResponse.getAlamat()!= null) {
            vaksin.setIdVaksin(vaksinRequest.getIdVaksin());
            vaksin.setTanggalIB(vaksinRequest.getTanggalIB());
            vaksin.setLokasi(vaksinRequest.getTanggalIB());
            vaksin.setIb1(vaksinRequest.getIb1());
            vaksin.setIb2(vaksinRequest.getIb2());
            vaksin.setIb3(vaksinRequest.getIb3());
            vaksin.setIbLain(vaksinRequest.getIbLain());
            vaksin.setIdPejantan(vaksinRequest.getIdPejantan());
            vaksin.setIdPembuatan(vaksinRequest.getIdPembuatan());
            vaksin.setBangsaPejantan(vaksinRequest.getBangsaPejantan());
            vaksin.setProdusen(vaksinRequest.getProdusen());
            vaksin.setPeternak(peternakResponse);
            vaksin.setPetugas(petugasResponse);
            vaksin.setHewan(hewanResponse);

          return vaksinRepository.save(vaksin);
        } else {
            return null;
        }
    }

    public DefaultResponse<Vaksin> getVaksinById(String vaksinId) throws IOException {
        // Retrieve Vaksin
        Vaksin vaksinResponse = vaksinRepository.findVaksinById(vaksinId);
        return new DefaultResponse<>(vaksinResponse.isValid() ? vaksinResponse : null, vaksinResponse.isValid() ? 1 : 0, "Successfully get data");
    }

    public Vaksin updateVaksin(String vaksinId, VaksinRequest vaksinRequest) throws IOException {
        Vaksin vaksin = new Vaksin();
       Peternak peternakResponse = peternakRepository.findById(vaksinRequest.getPeternak_id().toString());
Petugas petugasResponse = petugasRepository.findById(vaksinRequest.getPetugas_id().toString());
Hewan hewanResponse = hewanRepository.findById(vaksinRequest.getHewan_id().toString());
        if (peternakResponse.getNamaPeternak()!= null && petugasResponse.getNamaPetugas()!= null && hewanResponse.getAlamat()!= null) {
            vaksin.setTanggalIB(vaksinRequest.getTanggalIB());
            vaksin.setLokasi(vaksinRequest.getTanggalIB());
            vaksin.setIb1(vaksinRequest.getIb1());
            vaksin.setIb2(vaksinRequest.getIb2());
            vaksin.setIb3(vaksinRequest.getIb3());
            vaksin.setIbLain(vaksinRequest.getIbLain());
            vaksin.setIdPejantan(vaksinRequest.getIdPejantan());
            vaksin.setIdPembuatan(vaksinRequest.getIdPembuatan());
            vaksin.setBangsaPejantan(vaksinRequest.getBangsaPejantan());
            vaksin.setProdusen(vaksinRequest.getProdusen());
            vaksin.setPeternak(peternakResponse);
            vaksin.setPetugas(petugasResponse);
            vaksin.setHewan(hewanResponse);

            return vaksinRepository.update(vaksinId, vaksin);
        } else {
            return null;
        }
    }

    public void deleteVaksinById(String vaksinId) throws IOException {
        Vaksin vaksinResponse = vaksinRepository.findVaksinById(vaksinId);
        if(vaksinResponse.isValid()){
            vaksinRepository.deleteById(vaksinId);
        }else{
            throw new ResourceNotFoundException("Vaksin", "id", vaksinId);
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
