package com.ternak.sapi.payload;

import java.time.LocalDate;

public class PeternakRequest {
    private String idPeternak;
    private String nikPeternak;
    private String namaPeternak;
    private String idISIKHNAS;
    private String lokasi;
    private String petugas_id;
    private String tanggalPendaftaran;

    public PeternakRequest() {
    }

    public PeternakRequest(String idPeternak,  String nikPeternak, String namaPeternak, String idISIKHNAS, String lokasi, String petugas_id, String tanggalPendaftaran) {
        this.idPeternak = idPeternak;
        this.nikPeternak = nikPeternak;
        this.namaPeternak = namaPeternak;
        this.idISIKHNAS = idISIKHNAS;
        this.lokasi = lokasi;
        this.petugas_id = petugas_id;
        this.tanggalPendaftaran = tanggalPendaftaran;
    }

    public String getIdPeternak() {
        return idPeternak;
    }

    public void setIdPeternak(String idPeternak) {
        this.idPeternak = idPeternak;
    }

    public String getNikPeternak() {
        return nikPeternak;
    }

    public void setNikPeternak(String nikPeternak) {
        this.nikPeternak = nikPeternak;
    }

    public String getNamaPeternak() {
        return namaPeternak;
    }

    public void setNamaPeternak(String namaPeternak) {
        this.namaPeternak = namaPeternak;
    }

    public String getIdISIKHNAS() {
        return idISIKHNAS;
    }

    public void setIdISIKHNAS(String idISIKHNAS) {
        this.idISIKHNAS = idISIKHNAS;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getPetugas_id() {
        return petugas_id;
    }

    public void setPetugas_id(String petugas_id) {
        this.petugas_id = petugas_id;
    }

    public String getTanggalPendaftaran() {
        return tanggalPendaftaran;
    }

    public void setTanggalPendaftaran(String tanggalPendaftaran) {
        this.tanggalPendaftaran = tanggalPendaftaran;
    }
    
    public void set(String fieldName, String value) {
        switch (fieldName) {
            case "idPeternak":
                this.idPeternak = value;
                break;
            case "nikPeternak":
                this.nikPeternak = value;
                break;
            case "namaPeternak":
                this.namaPeternak = value;
                break;
            case "idISIKHNAS":
                this.idISIKHNAS = value;
                break;
            case "lokasi":
                this.lokasi = value;
                break;
            case "petugas_id":
                this.petugas_id = value;
                break;
            case "tanggalPendaftaran":
                this.tanggalPendaftaran = value;
                break;
            default:
                throw new IllegalArgumentException("Invalid field name: " + fieldName);
        }
    }
}
