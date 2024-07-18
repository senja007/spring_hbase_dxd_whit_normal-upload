package com.ternak.sapi.model;


public class Kandang {
    private String idKandang;
    private Peternak peternak;
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
    private String file_path;
    private String fotoType;
    private byte[] data;
    
    public Kandang() {
    }

    public Kandang(String idKandang, Peternak peternak, String luas, String kapasitas, String nilaiBangunan, 
            String alamat, String desa, String kecamatan, String kabupaten, String provinsi, String latitude, 
            String longitude, String file_path, String fotoType, byte[] data) {
        this.idKandang = idKandang;
        this.peternak = peternak;
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
        this.file_path = file_path;
        this.fotoType = fotoType;
        this.data = data;
    }

    public String getIdKandang() {
        return idKandang;
    }

    public void setIdKandang(String idKandang) {
        this.idKandang = idKandang;
    }

    public Peternak getPeternak() {
        return peternak;
    }

    public void setPeternak(Peternak peternak) {
        this.peternak = peternak;
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

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public String getFotoType() {
        return fotoType;
    }

    public void setFotoType(String fotoType) {
        this.fotoType = fotoType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public boolean isValid() {
        return this.idKandang != null && 
               this.peternak != null && 
               this.luas != null && 
               this.kapasitas != null && 
               this.nilaiBangunan != null && 
               this.alamat != null &&
               this.desa != null &&
               this.kecamatan != null &&
               this.kabupaten != null &&
               this.provinsi != null &&
               this.latitude != null &&
               this.longitude != null &&
               this.file_path != null && 
               this.fotoType != null && 
               this.data != null ;
    }

    public void set(String fieldName, String value) {
        switch (fieldName) {
            case "idKandang":
                this.idKandang = value;
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
            case "file_path":
                this.file_path = value;
                break;
            case "fotoType":
                this.fotoType = value;
                break;
            case "data":
                this.data = value.getBytes();
                break;
            default:
                throw new IllegalArgumentException("Invalid field name: " + fieldName);
        }
    }
}