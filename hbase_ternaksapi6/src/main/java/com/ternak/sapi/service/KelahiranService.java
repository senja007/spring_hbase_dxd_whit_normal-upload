package com.ternak.sapi.service;

import com.ternak.sapi.exception.BadRequestException;
import com.ternak.sapi.exception.ResourceNotFoundException;
import com.ternak.sapi.model.Kelahiran;
import com.ternak.sapi.model.Hewan;
import com.ternak.sapi.model.Peternak;
import com.ternak.sapi.model.Petugas;
import com.ternak.sapi.payload.DefaultResponse;
import com.ternak.sapi.payload.KelahiranRequest;
import com.ternak.sapi.payload.PagedResponse;
import com.ternak.sapi.repository.KelahiranRepository;
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
public class KelahiranService {
    private KelahiranRepository kelahiranRepository = new KelahiranRepository();
    private PeternakRepository peternakRepository = new PeternakRepository();
    private PetugasRepository petugasRepository = new PetugasRepository();
    private HewanRepository hewanRepository = new HewanRepository();

    private static final Logger logger = LoggerFactory.getLogger(KelahiranService.class);


    public PagedResponse<Kelahiran> getAllKelahiran(int page, int size, String peternakId, String petugasId, String hewanId) throws IOException {
        validatePageNumberAndSize(page, size);
        List<Kelahiran> kelahiranResponse;
        if(peternakId.equalsIgnoreCase("*")){
            kelahiranResponse = kelahiranRepository.findAll(size);
        }else if(hewanId != null && !hewanId.isEmpty()) {
            kelahiranResponse = kelahiranRepository.findKelahiranByHewan(hewanId, size);
        }else if(petugasId != null && !petugasId.isEmpty()){
            kelahiranResponse = kelahiranRepository.findKelahiranByPetugas(petugasId, size);
        }else{
            kelahiranResponse = kelahiranRepository.findKelahiranByPeternak(peternakId, size);
        }

        // Retrieve Polls


        return new PagedResponse<>(kelahiranResponse, kelahiranResponse.size(), "Successfully get data", 200);
    }

    public Kelahiran createKelahiran(KelahiranRequest kelahiranRequest) throws IOException {
        Kelahiran kelahiran = new Kelahiran();
        Peternak peternakResponse = peternakRepository.findById(kelahiranRequest.getPeternak_id().toString());
Petugas petugasResponse = petugasRepository.findById(kelahiranRequest.getPetugas_id().toString());
Hewan hewanResponse = hewanRepository.findById(kelahiranRequest.getHewan_id().toString());
        if (peternakResponse.getNamaPeternak()!= null && petugasResponse.getNamaPetugas()!= null && hewanResponse.getAlamat()!= null) {
            kelahiran.setIdKejadian(kelahiranRequest.getIdKejadian());
            kelahiran.setTanggalLaporan(kelahiranRequest.getTanggalLaporan());
            kelahiran.setTanggalLahir(kelahiranRequest.getTanggalLahir());
            kelahiran.setLokasi(kelahiranRequest.getLokasi());
            kelahiran.setKartuTernakInduk(kelahiranRequest.getKartuTernakInduk());
            kelahiran.setEartagInduk(kelahiranRequest.getEartagInduk());
            kelahiran.setSpesiesInduk(kelahiranRequest.getSpesiesInduk());
            kelahiran.setIdPejantanStraw(kelahiranRequest.getIdPejantanStraw());
            kelahiran.setIdBatchStraw(kelahiranRequest.getIdBatchStraw());
            kelahiran.setProdusenStraw(kelahiranRequest.getProdusenStraw());
            kelahiran.setSpesiesPejantan(kelahiranRequest.getSpesiesPejantan());
            kelahiran.setJumlah(kelahiranRequest.getJumlah());
            kelahiran.setKartuTernakAnak(kelahiranRequest.getKartuTernakAnak());
            kelahiran.setEartagAnak(kelahiranRequest.getEartagAnak());
            kelahiran.setIdHewanAnak(kelahiranRequest.getIdHewanAnak());
            kelahiran.setJenisKelaminAnak(kelahiranRequest.getJenisKelaminAnak());
            kelahiran.setKategori(kelahiranRequest.getKategori());
            kelahiran.setUrutanIb(kelahiranRequest.getUrutanIb());
            kelahiran.setPeternak(peternakResponse);
            kelahiran.setPetugas(petugasResponse);
            kelahiran.setHewan(hewanResponse);

            return kelahiranRepository.save(kelahiran);
        } else {
            return null;
        }
    }

    public DefaultResponse<Kelahiran> getKelahiranById(String kelahiranId) throws IOException {
        // Retrieve Kelahiran
        Kelahiran kelahiranResponse = kelahiranRepository.findKelahiranById(kelahiranId);
        return new DefaultResponse<>(kelahiranResponse.isValid() ? kelahiranResponse : null, kelahiranResponse.isValid() ? 1 : 0, "Successfully get data");
    }

    public Kelahiran updateKelahiran(String kelahiranId, KelahiranRequest kelahiranRequest) throws IOException {
        Kelahiran kelahiran = new Kelahiran();
      Peternak peternakResponse = peternakRepository.findById(kelahiranRequest.getPeternak_id().toString());
Petugas petugasResponse = petugasRepository.findById(kelahiranRequest.getPetugas_id().toString());
Hewan hewanResponse = hewanRepository.findById(kelahiranRequest.getHewan_id().toString());
        if (peternakResponse.getNamaPeternak()!= null && petugasResponse.getNamaPetugas()!= null && hewanResponse.getAlamat()!= null) {
            kelahiran.setTanggalLaporan(kelahiranRequest.getTanggalLaporan());
            kelahiran.setTanggalLahir(kelahiranRequest.getTanggalLahir());
            kelahiran.setLokasi(kelahiranRequest.getLokasi());
            kelahiran.setKartuTernakInduk(kelahiranRequest.getKartuTernakInduk());
            kelahiran.setEartagInduk(kelahiranRequest.getEartagInduk());
            kelahiran.setSpesiesInduk(kelahiranRequest.getSpesiesInduk());
            kelahiran.setIdPejantanStraw(kelahiranRequest.getIdPejantanStraw());
            kelahiran.setIdBatchStraw(kelahiranRequest.getIdBatchStraw());
            kelahiran.setProdusenStraw(kelahiranRequest.getProdusenStraw());
            kelahiran.setSpesiesPejantan(kelahiranRequest.getSpesiesPejantan());
            kelahiran.setJumlah(kelahiranRequest.getJumlah());
            kelahiran.setKartuTernakAnak(kelahiranRequest.getKartuTernakAnak());
            kelahiran.setEartagAnak(kelahiranRequest.getEartagAnak());
            kelahiran.setIdHewanAnak(kelahiranRequest.getIdHewanAnak());
            kelahiran.setJenisKelaminAnak(kelahiranRequest.getJenisKelaminAnak());
            kelahiran.setKategori(kelahiranRequest.getKategori());
            kelahiran.setUrutanIb(kelahiranRequest.getUrutanIb());
            kelahiran.setPeternak(peternakResponse);
            kelahiran.setPetugas(petugasResponse);
            kelahiran.setHewan(hewanResponse);

            return kelahiranRepository.update(kelahiranId, kelahiran);
        } else {
            return null;
        }
    }

    public void deleteKelahiranById(String kelahiranId) throws IOException {
        Kelahiran kelahiranResponse = kelahiranRepository.findKelahiranById(kelahiranId);
        if(kelahiranResponse.isValid()){
            kelahiranRepository.deleteById(kelahiranId);
        }else{
            throw new ResourceNotFoundException("Kelahiran", "id", kelahiranId);
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
