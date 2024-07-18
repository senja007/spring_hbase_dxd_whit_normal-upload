package com.ternak.sapi.payload;

import org.springframework.web.multipart.MultipartFile;

public class BeritaRequest {
    private String idBerita;
    private String judul;
    private String tglPembuatan;
    private String isiBerita;
    private String pembuat;
    private MultipartFile file;

    public BeritaRequest() {
    }

    public BeritaRequest(String idBerita, String judul, String tglPembuatan, String isiBerita, String pembuat, MultipartFile file) {
        this.idBerita = idBerita;
        this.judul = judul;
        this.tglPembuatan = tglPembuatan;
        this.isiBerita = isiBerita;
        this.pembuat = pembuat;
        this.file = file;
    }

    public String getIdBerita() {
        return idBerita;
    }

    public void setIdBerita(String idBerita) {
        this.idBerita = idBerita;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
    
    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getTglPembuatan() {
        return tglPembuatan;
    }

    public void setTglPembuatan(String tglPembuatan) {
        this.tglPembuatan = tglPembuatan;
    }

    public String getIsiBerita() {
        return isiBerita;
    }

    public void setIsiBerita(String isiBerita) {
        this.isiBerita = isiBerita;
    }

    public String getPembuat() {
        return pembuat;
    }

    public void setPembuat(String pembuat) {
        this.pembuat = pembuat;
    }

    public void set(String fieldName, String value) {
        switch (fieldName) {
            case "idBerita":
                this.idBerita = value;
                break;
            case "judul":
                this.judul = value;
                break;
            case "tglPembuatan":
                this.tglPembuatan = value;
                break;
            case "isiBerita":
                this.isiBerita = value;
                break;
            case "pembuat":
                this.pembuat = value;
                break;
            default:
                throw new IllegalArgumentException("Invalid field name: " + fieldName);
        }
    }
}
