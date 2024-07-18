package com.ternak.sapi.payload;

public class VaksinRequest {
    private String idVaksin;
    private String tanggalIB;
    private String lokasi;
    private String peternak_id;
    private String hewan_id;
    private String petugas_id;
    private String ib1;
    private String ib2;
    private String ib3;
    private String ibLain;
    private String idPejantan;
    private String idPembuatan;
    private String bangsaPejantan;
    private String produsen;

    public VaksinRequest() {
    }

    public VaksinRequest(String idVaksin, String tanggalIB, String lokasi, String peternak_id, 
            String hewan_id, String petugas_id, String ib1, String ib2, String ib3, String ibLain, 
            String idPejantan, String idPembuatan, String bangsaPejantan, String produsen) {
        this.idVaksin = idVaksin;
        this.tanggalIB = tanggalIB;
        this.lokasi = lokasi;
        this.peternak_id = peternak_id;
        this.hewan_id = hewan_id;
        this.petugas_id = petugas_id;
        this.ib1 = ib1;
        this.ib2 = ib2;
        this.ib3 = ib3;
        this.ibLain = ibLain;
        this.idPejantan = idPejantan;
        this.idPembuatan = idPembuatan;
        this.bangsaPejantan = bangsaPejantan;
        this.produsen = produsen;
    }

    public String getIdVaksin() {
        return idVaksin;
    }

    public void setIdVaksin(String idVaksin) {
        this.idVaksin = idVaksin;
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

    

    public void set(String fieldName, String value) {
        switch (fieldName) {
            case "idVaksin":
                this.idVaksin = value;
                break;
            case "tanggalIB":
                this.tanggalIB = value;
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
