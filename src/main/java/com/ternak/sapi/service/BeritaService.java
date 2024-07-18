package com.ternak.sapi.service;

import com.ternak.sapi.exception.BadRequestException;
import com.ternak.sapi.exception.FileStorageException;
import com.ternak.sapi.exception.ResourceNotFoundException;
import com.ternak.sapi.model.Berita;
import com.ternak.sapi.payload.DefaultResponse;
import com.ternak.sapi.payload.BeritaRequest;
import com.ternak.sapi.payload.PagedResponse;
import com.ternak.sapi.property.FileStorageProperties;
import com.ternak.sapi.repository.BeritaRepository;
import com.ternak.sapi.util.AppConstants;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class BeritaService {
    private BeritaRepository beritaRepository = new BeritaRepository();
    private final Path fileStorageLocation;
    private static final Logger logger = LoggerFactory.getLogger(BeritaService.class);
    
    @Autowired
    public BeritaService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (IOException ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }
 
//    private String constructFileUrl(String fileName) {
//        return ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/downloadFile/")
//                .path(fileName)
//                .toUriString();
//    }


    public PagedResponse<Berita> getAllBerita(int page, int size) throws IOException {
        validatePageNumberAndSize(page, size);

        // Retrieve Polls
        List<Berita> beritaResponse = beritaRepository.findAll(size);


        return new PagedResponse<>(beritaResponse, beritaResponse.size(), "Successfully get data", 200);
    }

    public Berita createBerita(BeritaRequest beritaRequest, MultipartFile file) throws IOException {
        Berita berita = new Berita();
        
        berita.setIdBerita(beritaRequest.getIdBerita());
        berita.setJudul(beritaRequest.getJudul());
        berita.setTglPembuatan(beritaRequest.getTglPembuatan());
        berita.setIsiBerita(beritaRequest.getIsiBerita());
        berita.setPembuat(beritaRequest.getPembuat());
        if (file != null && !file.isEmpty()) {
         String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            try {
                // Check if the file's name contains invalid characters
                if (fileName.contains("..")) {
                    throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
                }

                Path targetLocation = this.fileStorageLocation.resolve(fileName);
                Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

             //   String fileUrl = constructFileUrl(fileName);

                berita.setFile_path(fileName);
                berita.setFotoType(file.getContentType());
                berita.setData(file.getBytes());
            } catch (IOException ex) {
                throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
            }
        }

        return beritaRepository.save(berita);
    }

    public DefaultResponse<Berita> getBeritaById(String beritaId) throws IOException {
        // Retrieve Berita
        Berita beritaResponse = beritaRepository.findById(beritaId);
        return new DefaultResponse<>(beritaResponse.isValid() ? beritaResponse : null, beritaResponse.isValid() ? 1 : 0, "Successfully get data");
    }

    public Berita updateBerita(String beritaId, BeritaRequest beritaRequest, MultipartFile file) throws IOException {
        Berita berita = new Berita();
        berita.setJudul(beritaRequest.getJudul());
        berita.setTglPembuatan(beritaRequest.getTglPembuatan());
        berita.setIsiBerita(beritaRequest.getIsiBerita());
        berita.setPembuat(beritaRequest.getPembuat());
        if (file != null && !file.isEmpty()) {
         String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            try {
                // Check if the file's name contains invalid characters
                if (fileName.contains("..")) {
                    throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
                }

                Path targetLocation = this.fileStorageLocation.resolve(fileName);
                Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

                berita.setFile_path(fileName);
                berita.setFotoType(file.getContentType());
                berita.setData(file.getBytes());
            } catch (IOException ex) {
                throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
            }
        }

        return beritaRepository.update(beritaId, berita);
    }

    public void deleteBeritaById(String beritaId) throws IOException {
        Berita beritaResponse = beritaRepository.findById(beritaId);
        if(beritaResponse.isValid()){
            beritaRepository.deleteById(beritaId);
        }else{
            throw new ResourceNotFoundException("Berita", "id", beritaId);
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
