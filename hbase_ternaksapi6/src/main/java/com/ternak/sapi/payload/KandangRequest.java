package com.ternak.sapi.payload;

import org.springframework.web.multipart.MultipartFile;

public class KandangRequest {
    private String idKandang;
    private String peternak_id;
    private String luas;
    private String kapasitas;
    private String nilaiBangunan;
    private String alamat;
    private String desa;
    private String kecamatan;
    private String kabupaten;
    private String provinsi;
    private String latitude;
    private String longitude;
    private MultipartFile file;

    public KandangRequest() {
    }

    public KandangRequest(String idKandang, String peternak_id, String luas, String kapasitas, 
            String nilaiBangunan, String alamat, String desa, String kecamatan, String kabupaten, 
            String provinsi, String latitude, String longitude, MultipartFile file) {
        this.idKandang = idKandang;
        this.peternak_id = peternak_id;
        this.luas = luas;
        this.kapasitas = kapasitas;
        this.nilaiBangunan = nilaiBangunan;
        this.alamat = alamat;
        this.desa = desa;
        this.kecamatan = kecamatan;
        this.kabupaten = kabupaten;
        this.provinsi = provinsi;
        this.latitude = latitude;
        this.longitude = longitude;
        this.file = file;
    }

    public String getIdKandang() {
        return idKandang;
    }

    public void setIdKandang(String idKandang) {
        this.idKandang = idKandang;
    }

    public String getPeternak_id() {
        return peternak_id;
    }

    public void setPeternak_id(String peternak_id) {
        this.peternak_id = peternak_id;
    }

    public String getLuas() {
        return luas;
    }

    public void setLuas(String luas) {
        this.luas = luas;
    }

    public String getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(String kapasitas) {
        this.kapasitas = kapasitas;
    }

    public String getNilaiBangunan() {
        return nilaiBangunan;
    }

    public void setNilaiBangunan(String nilaiBangunan) {
        this.nilaiBangunan = nilaiBangunan;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getDesa() {
        return desa;
    }

    public void setDesa(String desa) {
        this.desa = desa;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getKabupaten() {
        return kabupaten;
    }

    public void setKabupaten(String kabupaten) {
        this.kabupaten = kabupaten;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public void set(String fieldName, String value) {
        switch (fieldName) {
            case "idKandang":
                this.idKandang = value;
                break;
            case "peternak_id":
                this.peternak_id = value;
                break;
            case "luas":
                this.luas = value;
                break;
            case "kapasitas":
                this.kapasitas = value;
                break;
            case "nilaiBangunan":
                this.nilaiBangunan = value;
                break;
            case "alamat":
                this.alamat = value;
                break;
            case "desa":
                this.desa = value;
                break;
            case "kecamatan":
                this.kecamatan = value;
                break;
            case "kabupaten":
                this.kabupaten = value;
                break;
            case "provinsi":
                this.provinsi = value;
                break;
            case "latitude":
                this.latitude = value;
                break;
            case "longitude":
                this.longitude = value;
                break;
            default:
                throw new IllegalArgumentException("Invalid field name: " + fieldName);
        }
    }
}