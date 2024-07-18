package com.ternak.sapi.model;


public class Berita {
    private String idBerita;
    private String judul;
    private String tglPembuatan;
    private String isiBerita;
    private String pembuat;
    private String file_path;
    private String fotoType;
    private byte[] data;

    public Berita() {
    }

    public Berita(String idBerita, String judul, String tglPembuatan, String isiBerita, String pembuat, String file_path, String fotoType, byte[] data) {
        this.idBerita = idBerita;
        this.judul = judul;
        this.tglPembuatan = tglPembuatan;
        this.isiBerita = isiBerita;
        this.pembuat = pembuat;
        this.file_path =file_path;
        this.fotoType = fotoType;
        this.data = data;
    }
    
    public String getIdBerita() {
        return idBerita;
    }

    public void setIdBerita(String idBerita) {
        this.idBerita = idBerita;
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
        return this.idBerita != null && 
               this.judul != null && 
               this.tglPembuatan != null &&
               this.isiBerita != null && 
               this.pembuat != null && 
               this.file_path != null && 
               this.fotoType != null && 
               this.data != null ;
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