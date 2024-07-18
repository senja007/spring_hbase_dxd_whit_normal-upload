package com.ternak.sapi.model;

public class Inseminasi {
    private String idInseminasi;
    private String tanggalIB;
    private String lokasi;
    private Peternak peternak;
    private Hewan hewan;
    private Petugas petugas;
    private String ib1;
    private String ib2;
    private String ib3;
    private String ibLain;
    private String idPejantan;
    private String idPembuatan;
    private String bangsaPejantan;
    private String produsen;
    
    

    public Inseminasi() {
    }

    public Inseminasi(String idInseminasi, String tanggalIB, String lokasi, 
            Peternak peternak, Hewan hewan, Petugas petugas, String ib1, 
            String ib2, String ib3, String ibLain, String idPejantan, 
            String idPembuatan, String bangsaPejantan, String produsen) {
        this.idInseminasi = idInseminasi;
        this.tanggalIB = tanggalIB;
        this.lokasi = lokasi;
        this.peternak = peternak;
        this.hewan = hewan;
        this.petugas = petugas;
        this.ib1 = ib1;
        this.ib2 = ib2;
        this.ib3 = ib3;
        this.ibLain = ibLain;
        this.idPejantan = idPejantan;
        this.idPembuatan = idPembuatan;
        this.bangsaPejantan = bangsaPejantan;
        this.produsen = produsen;
    }

    public String getIdInseminasi() {
        return idInseminasi;
    }

    public void setIdInseminasi(String idInseminasi) {
        this.idInseminasi = idInseminasi;
    }

    public String getTanggalIB() {
        return tanggalIB;
    }

    public void setTanggalIB(String tanggalIB) {
        this.tanggalIB = tanggalIB;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public Peternak getPeternak() {
        return peternak;
    }

    public void setPeternak(Peternak peternak) {
        this.peternak = peternak;
    }

    public Hewan getHewan() {
        return hewan;
    }

    public void setHewan(Hewan hewan) {
        this.hewan = hewan;
    }

    public Petugas getPetugas() {
        return petugas;
    }

    public void setPetugas(Petugas petugas) {
        this.petugas = petugas;
    }

    public String getIb1() {
        return ib1;
    }

    public void setIb1(String ib1) {
        this.ib1 = ib1;
    }

    public String getIb2() {
        return ib2;
    }

    public void setIb2(String ib2) {
        this.ib2 = ib2;
    }

    public String getIb3() {
        return ib3;
    }

    public void setIb3(String ib3) {
        this.ib3 = ib3;
    }

    public String getIbLain() {
        return ibLain;
    }

    public void setIbLain(String ibLain) {
        this.ibLain = ibLain;
    }

    public String getIdPejantan() {
        return idPejantan;
    }

    public void setIdPejantan(String idPejantan) {
        this.idPejantan = idPejantan;
    }

    public String getIdPembuatan() {
        return idPembuatan;
    }

    public void setIdPembuatan(String idPembuatan) {
        this.idPembuatan = idPembuatan;
    }

    public String getBangsaPejantan() {
        return bangsaPejantan;
    }

    public void setBangsaPejantan(String bangsaPejantan) {
        this.bangsaPejantan = bangsaPejantan;
    }

    public String getProdusen() {
        return produsen;
    }

    public void setProdusen(String produsen) {
        this.produsen = produsen;
    }

   
    public boolean isValid() {
        return this.idInseminasi != null && 
               this.tanggalIB != null && 
               this.lokasi != null && 
               this.peternak != null && 
               this.hewan != null && 
               this.petugas != null && 
               this.ib1 != null && 
               this.ib2 != null && 
               this.ib3 != null && 
               this.ibLain != null && 
               this.idPejantan != null && 
               this.idPembuatan != null && 
               this.bangsaPejantan != null &&
               this.produsen != null;
    }

    public void set(String fieldName, String value) {
        switch (fieldName) {
            case "idInseminasi":
                this.idInseminasi = value;
                break;
            case "tanggalIB":
                this.tanggalIB = value;
                break;
            case "lokasi":
                this.lokasi = value;
                break;
            case "ib1":
                this.ib1 = value;
                break;
            case "ib2":
                this.ib2 = value;
                break;
            case "ib3":
                this.ib3 = value;
                break;
            case "ibLain":
                this.ibLain = value;
                break;
            case "idPejantan":
                this.idPejantan = value;
                break;
            case "idPembuatan":
                this.idPembuatan = value;
                break;
            case "bangsaPejantan":
                this.bangsaPejantan = value;
                break;
            case "produsen":
                this.produsen = value;
                break;
            default:
                throw new IllegalArgumentException("Invalid field name: " + fieldName);
        }
    }
}
