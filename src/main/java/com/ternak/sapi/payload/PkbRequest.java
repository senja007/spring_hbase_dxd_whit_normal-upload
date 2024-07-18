package com.ternak.sapi.payload;

public class PkbRequest {
    private String idKejadian;
    private String tanggalPkb;
    private String lokasi;
    private String peternak_id;
    private String hewan_id;
    private String petugas_id;
    private String spesies;
    private String kategori;
    private String jumlah;
    private String umurKebuntingan;

    public PkbRequest() {
    }

    public PkbRequest(String idKejadian, String tanggalPkb, String lokasi, String peternak_id, 
            String hewan_id, String petugas_id, String spesies, String kategori, String jumlah, String umurKebuntingan) {
        this.idKejadian = idKejadian;
        this.tanggalPkb = tanggalPkb;
        this.lokasi = lokasi;
        this.peternak_id = peternak_id;
        this.hewan_id = hewan_id;
        this.petugas_id = petugas_id;
        this.spesies = spesies;
        this.kategori = kategori;
        this.jumlah = jumlah;
        this.umurKebuntingan = umurKebuntingan;
    }
    
    public String getIdKejadian() {
        return idKejadian;
    }

    public void setIdKejadian(String idKejadian) {
        this.idKejadian = idKejadian;
    }

    public String getTanggalPkb() {
        return tanggalPkb;
    }

    public void setTanggalPkb(String tanggalPkb) {
        this.tanggalPkb = tanggalPkb;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getPeternak_id() {
        return peternak_id;
    }

    public void setPeternak_id(String peternak_id) {
        this.peternak_id = peternak_id;
    }

    public String getHewan_id() {
        return hewan_id;
    }

    public void setHewan_id(String hewan_id) {
        this.hewan_id = hewan_id;
    }

    public String getPetugas_id() {
        return petugas_id;
    }

    public void setPetugas_id(String petugas_id) {
        this.petugas_id = petugas_id;
    }

    public String getSpesies() {
        return spesies;
    }

    public void setSpesies(String spesies) {
        this.spesies = spesies;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getUmurKebuntingan() {
        return umurKebuntingan;
    }

    public void setUmurKebuntingan(String umurKebuntingan) {
        this.umurKebuntingan = umurKebuntingan;
    }

    
 
    public void set(String fieldName, String value) {
        switch (fieldName) {
            case "idKejadian":
                this.idKejadian = value;
                break;
            case "tanggalPkb":
                this.tanggalPkb = value;
                break;
            case "lokasi":
                this.lokasi = value;
                break;
            case "peternak_id":
                this.peternak_id = value;
                break;
            case "hewan_id":
                this.hewan_id = value;
                break;
            case "petugas_id":
                this.petugas_id = value;
                break;
            case "spesies":
                this.spesies = value;
                break;
            case "kategori":
                this.kategori = value;
                break;
            case "jumlah":
                this.jumlah = value;
                break;
            case "umurKebuntingan":
                this.umurKebuntingan = value;
                break;
            default:
                throw new IllegalArgumentException("Invalid field name: " + fieldName);
        }
    }
}
