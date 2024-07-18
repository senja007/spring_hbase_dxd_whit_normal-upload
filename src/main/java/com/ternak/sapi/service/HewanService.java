package com.ternak.sapi.service;

import com.ternak.sapi.exception.BadRequestException;
import com.ternak.sapi.exception.FileStorageException;
import com.ternak.sapi.exception.ResourceNotFoundException;
import com.ternak.sapi.model.Peternak;
import com.ternak.sapi.model.Hewan;
import com.ternak.sapi.model.Petugas;
import com.ternak.sapi.model.Kandang;
import com.ternak.sapi.payload.DefaultResponse;
import com.ternak.sapi.payload.HewanRequest;
import com.ternak.sapi.payload.PagedResponse;
import com.ternak.sapi.property.FileStorageProperties;
import com.ternak.sapi.repository.PeternakRepository;
import com.ternak.sapi.repository.HewanRepository;
import com.ternak.sapi.repository.KandangRepository;
import com.ternak.sapi.repository.PetugasRepository;
import com.ternak.sapi.util.AppConstants;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class HewanService {
    private HewanRepository hewanRepository = new HewanRepository();
    private PeternakRepository peternakRepository = new PeternakRepository();
    private PetugasRepository petugasRepository = new PetugasRepository();
    private KandangRepository kandangRepository = new KandangRepository();
    private final Path fileStorageLocation;

    private static final Logger logger = LoggerFactory.getLogger(HewanService.class);

    @Autowired
    public HewanService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (IOException ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }
    public PagedResponse<Hewan> getAllHewan(int page, int size, String peternakId, String petugasId, String kandangId) throws IOException {
        validatePageNumberAndSize(page, size);

        // Retrieve Polls
        List<Hewan> hewanResponse = new ArrayList<>();

        if(peternakId.equalsIgnoreCase("*")){
            hewanResponse = hewanRepository.findAll(size);
//        }else if(kandangId != null && !kandangId.isEmpty()) {
//            hewanResponse = hewanRepository.findHewanByKandang(kandangId, size);
//        }else if(petugasId != null && !petugasId.isEmpty()){
//            hewanResponse = hewanRepository.findHewanByPetugas(petugasId, size);
        }else{
            hewanResponse = hewanRepository.findHewanByPeternak(peternakId, size);
        }

        return new PagedResponse<>(hewanResponse, hewanResponse.size(), "Successfully get data", 200);
    }

    public Hewan createHewan(HewanRequest hewanRequest, MultipartFile file) throws IOException {
        Hewan hewan = new Hewan();
//        Peternak peternakResponse = peternakRepository.findById(hewanRequest.getKandang_id()!= "" ? hewanRequest.getKandang_id(): "0");
//        Petugas petugasResponse = petugasRepository.findById(hewanRequest.getPetugas_id()!= "" ? hewanRequest.getPetugas_id() : "0");
//        Kandang kandangResponse = kandangRepository.findById(hewanRequest.getKandang_id()!= "" ? hewanRequest.getKandang_id(): "0");
Peternak peternakResponse = peternakRepository.findById(hewanRequest.getPeternak_id().toString());
Petugas petugasResponse = petugasRepository.findById(hewanRequest.getPetugas_id().toString());
Kandang kandangResponse = kandangRepository.findById(hewanRequest.getKandang_id().toString());
        if (peternakResponse.getNamaPeternak()!= null && petugasResponse.getNamaPetugas()!= null && kandangResponse.getAlamat()!= null) {
            hewan.setKodeEartagNasional(hewanRequest.getKodeEartagNasional());
            hewan.setNoKartuTernak(hewanRequest.getNoKartuTernak());
            hewan.setIdentifikasiHewan(hewanRequest.getIdentifikasiHewan());
            hewan.setSpesies(hewanRequest.getSpesies());
            hewan.setSex(hewanRequest.getSex());
            hewan.setUmur(hewanRequest.getUmur());
            hewan.setTanggalTerdaftar(hewanRequest.getTanggalTerdaftar());
            hewan.setAlamat(hewanRequest.getAlamat());
            hewan.setDesa(hewanRequest.getDesa());
            hewan.setKecamatan(hewanRequest.getKecamatan());
            hewan.setKabupaten(hewanRequest.getKabupaten());
            hewan.setProvinsi(hewanRequest.getProvinsi());
            hewan.setLatitude(hewanRequest.getLatitude());
            hewan.setLongitude(hewanRequest.getLongitude());
            if (file != null && !file.isEmpty()) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
               try {
                   // Check if the file's name contains invalid characters
                   if (fileName.contains("..")) {
                       throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
                   }

                   Path targetLocation = this.fileStorageLocation.resolve(fileName);
                   Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

                   hewan.setFile_path(fileName);
                   hewan.setFotoType(file.getContentType());
                   hewan.setData(file.getBytes());
               } catch (IOException ex) {
                   throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
               }
           }
            hewan.setPeternak(peternakResponse);
            hewan.setPetugas(petugasResponse);
            hewan.setKandang(kandangResponse);
            
            return hewanRepository.save(hewan);
        } else {
            return null;
        }
    }


    public DefaultResponse<Hewan> getHewanById(String hewanId) throws IOException {
        // Retrieve Hewan
        Hewan hewanResponse = hewanRepository.findById(hewanId);
        return new DefaultResponse<>(hewanResponse.isValid() ? hewanResponse : null, hewanResponse.isValid() ? 1 : 0, "Successfully get data");
    }

    public Hewan updateHewan(String hewanId, HewanRequest hewanRequest, MultipartFile file) throws IOException {
        Hewan hewan = new Hewan();
//        Peternak peternakResponse = peternakRepository.findById(hewanRequest.getKandang_id()!= "" ? hewanRequest.getKandang_id(): "0");
//        Petugas petugasResponse = petugasRepository.findById(hewanRequest.getPetugas_id()!= "" ? hewanRequest.getPetugas_id() : "0");
//        Kandang kandangResponse = kandangRepository.findById(hewanRequest.getKandang_id()!= "" ? hewanRequest.getKandang_id(): "0");
Peternak peternakResponse = peternakRepository.findById(hewanRequest.getPeternak_id().toString());
Petugas petugasResponse = petugasRepository.findById(hewanRequest.getPetugas_id().toString());
Kandang kandangResponse = kandangRepository.findById(hewanRequest.getKandang_id().toString());
        if (peternakResponse.getNamaPeternak()!= null) {
            hewan.setNoKartuTernak(hewanRequest.getNoKartuTernak());
            hewan.setIdentifikasiHewan(hewanRequest.getIdentifikasiHewan());
            hewan.setSpesies(hewanRequest.getSpesies());
            hewan.setSex(hewanRequest.getSex());
            hewan.setUmur(hewanRequest.getUmur());
            hewan.setTanggalTerdaftar(hewanRequest.getTanggalTerdaftar());
            hewan.setAlamat(hewanRequest.getAlamat());
            hewan.setDesa(hewanRequest.getDesa());
            hewan.setKecamatan(hewanRequest.getKecamatan());
            hewan.setKabupaten(hewanRequest.getKabupaten());
            hewan.setProvinsi(hewanRequest.getProvinsi());
            hewan.setLatitude(hewanRequest.getLatitude());
            hewan.setLongitude(hewanRequest.getLongitude());
             if (file != null && !file.isEmpty()) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
               try {
                   // Check if the file's name contains invalid characters
                   if (fileName.contains("..")) {
                       throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
                   }

                   Path targetLocation = this.fileStorageLocation.resolve(fileName);
                   Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

                   hewan.setFile_path(fileName);
                   hewan.setFotoType(file.getContentType());
                   hewan.setData(file.getBytes());
               } catch (IOException ex) {
                   throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
               }
           }
            hewan.setPeternak(peternakResponse);
            hewan.setPetugas(petugasResponse);
            hewan.setKandang(kandangResponse);
           
            return hewanRepository.update(hewanId, hewan);
        } else {
            return null;
        }
    }

    public void deleteHewanById(String hewanId) throws IOException {
        Hewan hewanResponse = hewanRepository.findById(hewanId);
        if(hewanResponse.isValid()){
            hewanRepository.deleteById(hewanId);
        }else{
            throw new ResourceNotFoundException("Hewan", "id", hewanId);
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