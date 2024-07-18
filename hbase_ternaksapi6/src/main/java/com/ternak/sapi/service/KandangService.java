package com.ternak.sapi.service;

import com.ternak.sapi.exception.BadRequestException;
import com.ternak.sapi.exception.FileStorageException;
import com.ternak.sapi.exception.ResourceNotFoundException;
import com.ternak.sapi.model.Peternak;
import com.ternak.sapi.model.Kandang;
import com.ternak.sapi.payload.DefaultResponse;
import com.ternak.sapi.payload.KandangRequest;
import com.ternak.sapi.payload.PagedResponse;
import com.ternak.sapi.property.FileStorageProperties;
import com.ternak.sapi.repository.PeternakRepository;
import com.ternak.sapi.repository.KandangRepository;
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
public class KandangService {
    private KandangRepository kandangRepository = new KandangRepository();
    private PeternakRepository peternakRepository = new PeternakRepository();
    private final Path fileStorageLocation;
    private static final Logger logger = LoggerFactory.getLogger(KandangService.class);
    
    @Autowired
    public KandangService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (IOException ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public PagedResponse<Kandang> getAllKandang(int page, int size, String peternakId) throws IOException {
        validatePageNumberAndSize(page, size);

        // Retrieve Polls
        List<Kandang> kandangResponse = new ArrayList<>();

        if(peternakId.equalsIgnoreCase("*")){
            kandangResponse = kandangRepository.findAll(size);
        }else{
            kandangResponse = kandangRepository.findKandangByPeternak(peternakId, size);
        }

        return new PagedResponse<>(kandangResponse, kandangResponse.size(), "Successfully get data", 200);
    }

    public Kandang createKandang(KandangRequest kandangRequest,  MultipartFile file) throws IOException {
        Kandang kandang = new Kandang();
        Peternak peternakResponse = peternakRepository.findById(kandangRequest.getPeternak_id().toString());
        if (peternakResponse.getNamaPeternak()!= null) {
            kandang.setIdKandang(kandangRequest.getIdKandang());
            kandang.setLuas(kandangRequest.getLuas());
            kandang.setKapasitas(kandangRequest.getKapasitas());
            kandang.setNilaiBangunan(kandangRequest.getNilaiBangunan());
            kandang.setAlamat(kandangRequest.getAlamat());
            kandang.setDesa(kandangRequest.getDesa());
            kandang.setKecamatan(kandangRequest.getKecamatan());
            kandang.setKabupaten(kandangRequest.getKabupaten());
            kandang.setProvinsi(kandangRequest.getProvinsi());
            kandang.setLatitude(kandangRequest.getLatitude());
            kandang.setLongitude(kandangRequest.getLongitude());
            if (file != null && !file.isEmpty()) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
               try {
                   // Check if the file's name contains invalid characters
                   if (fileName.contains("..")) {
                       throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
                   }

                   Path targetLocation = this.fileStorageLocation.resolve(fileName);
                   Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

                   kandang.setFile_path(fileName);
                   kandang.setFotoType(file.getContentType());
                   kandang.setData(file.getBytes());
               } catch (IOException ex) {
                   throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
               }
           }
            kandang.setPeternak(peternakResponse);
            return kandangRepository.save(kandang);
        } else {
            return null;
        }
    }


    public DefaultResponse<Kandang> getKandangById(String kandangId) throws IOException {
        // Retrieve Kandang
        Kandang kandangResponse = kandangRepository.findById(kandangId);
        return new DefaultResponse<>(kandangResponse.isValid() ? kandangResponse : null, kandangResponse.isValid() ? 1 : 0, "Successfully get data");
    }

    public Kandang updateKandang(String kandangId, KandangRequest kandangRequest, MultipartFile file) throws IOException {
        Kandang kandang = new Kandang();
        Peternak peternakResponse = peternakRepository.findById(kandangRequest.getPeternak_id().toString());
        if (peternakResponse.getNamaPeternak()!= null) {
            kandang.setLuas(kandangRequest.getLuas());
            kandang.setKapasitas(kandangRequest.getKapasitas());
            kandang.setNilaiBangunan(kandangRequest.getNilaiBangunan());
            kandang.setAlamat(kandangRequest.getAlamat());
            kandang.setDesa(kandangRequest.getDesa());
            kandang.setKecamatan(kandangRequest.getKecamatan());
            kandang.setKabupaten(kandangRequest.getKabupaten());
            kandang.setProvinsi(kandangRequest.getProvinsi());
            kandang.setLatitude(kandangRequest.getLatitude());
            kandang.setLongitude(kandangRequest.getLongitude());
            if (file != null && !file.isEmpty()) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
               try {
                   // Check if the file's name contains invalid characters
                   if (fileName.contains("..")) {
                       throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
                   }

                   Path targetLocation = this.fileStorageLocation.resolve(fileName);
                   Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

                   kandang.setFile_path(fileName);
                   kandang.setFotoType(file.getContentType());
                   kandang.setData(file.getBytes());
               } catch (IOException ex) {
                   throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
               }
           }
            kandang.setPeternak(peternakResponse);
            return kandangRepository.update(kandangId, kandang);
        } else {
            return null;
        }
    }

    public void deleteKandangById(String kandangId) throws IOException {
        Kandang kandangResponse = kandangRepository.findById(kandangId);
        if(kandangResponse.isValid()){
            kandangRepository.deleteById(kandangId);
        }else{
            throw new ResourceNotFoundException("Kandang", "id", kandangId);
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