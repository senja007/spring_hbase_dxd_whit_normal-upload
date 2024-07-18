package com.ternak.sapi.model;

import java.time.LocalDate;

public class Peternak {
    private String idPeternak;
    private String nikPeternak;
    private String namaPeternak;
    private String idISIKHNAS;
    private String lokasi;
    private Petugas petugas;
    private String tanggalPendaftaran;

    public Peternak() {
    }

    public Peternak(String idPeternak, String nikPeternak, String namaPeternak, String idISIKHNAS, String lokasi, 
            Petugas petugas, String tanggalPendaftaran) {
        this.idPeternak = idPeternak;
        this.nikPeternak = nikPeternak;
        this.namaPeternak = namaPeternak;
        this.idISIKHNAS = idISIKHNAS;
        this.lokasi = lokasi;
        this.petugas = petugas;
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

    public Petugas getPetugas() {
        return petugas;
    }

    public void setPetugas(Petugas petugas) {
        this.petugas = petugas;
    }

    public String getTanggalPendaftaran() {
        return tanggalPendaftaran;
    }

    public void setTanggalPendaftaran(String tanggalPendaftaran) {
        this.tanggalPendaftaran = tanggalPendaftaran;
    }
    
    public boolean isValid() {
        return this.idPeternak != null &&
                this.nikPeternak != null &&
                this.namaPeternak != null &&
                this.idISIKHNAS != null &&
                this.lokasi != null &&
                this.petugas != null &&
                this.tanggalPendaftaran != null;
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
            case "tanggalPendaftaran":
                this.tanggalPendaftaran = value;
                break;
            default:
                throw new IllegalArgumentException("Invalid field name: " + fieldName);
        }
    }
}
