package com.ternak.sapi.payload;

import org.springframework.web.multipart.MultipartFile;

public class HewanRequest {
    private String kodeEartagNasional;
    private String noKartuTernak;
    private String provinsi;
    private String kabupaten;
    private String kecamatan;
    private String desa;
    private String alamat;
    private String petugas_id;
    private String peternak_id;
    private String kandang_id;
    private String spesies;
    private String sex;
    private String umur;
    private String identifikasiHewan;
    private String tanggalTerdaftar;
    private String latitude;
    private String longitude;
    private MultipartFile file;

    public HewanRequest() {
    }

    public HewanRequest(String kodeEartagNasional, String noKartuTernak, String provinsi, String kabupaten, String kecamatan, 
            String desa, String alamat, String petugas_id, String peternak_id, String kandang_id, String spesies, String sex, 
            String umur, String identifikasiHewan, String tanggalTerdaftar, String latitude, String longitude, MultipartFile file) {
        this.kodeEartagNasional = kodeEartagNasional;
        this.noKartuTernak = noKartuTernak;
        this.provinsi = provinsi;
        this.kabupaten = kabupaten;
        this.kecamatan = kecamatan;
        this.desa = desa;
        this.alamat = alamat;
        this.petugas_id = petugas_id;
        this.peternak_id = peternak_id;
        this.kandang_id = kandang_id;
        this.spesies = spesies;
        this.sex = sex;
        this.umur = umur;
        this.identifikasiHewan = identifikasiHewan;
        this.tanggalTerdaftar = tanggalTerdaftar;
        this.latitude = latitude;
        this.longitude = longitude;
        this.file = file;
    }

    public String getKodeEartagNasional() {
        return kodeEartagNasional;
    }

    public void setKodeEartagNasional(String kodeEartagNasional) {
        this.kodeEartagNasional = kodeEartagNasional;
    }

    public String getNoKartuTernak() {
        return noKartuTernak;
    }

    public void setNoKartuTernak(String noKartuTernak) {
        this.noKartuTernak = noKartuTernak;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getKabupaten() {
        return kabupaten;
    }

    public void setKabupaten(String kabupaten) {
        this.kabupaten = kabupaten;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getDesa() {
        return desa;
    }

    public void setDesa(String desa) {
        this.desa = desa;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getPetugas_id() {
        return petugas_id;
    }

    public void setPetugas_id(String petugas_id) {
        this.petugas_id = petugas_id;
    }

    public String getPeternak_id() {
        return peternak_id;
    }

    public void setPeternak_id(String peternak_id) {
        this.peternak_id = peternak_id;
    }

    public String getKandang_id() {
        return kandang_id;
    }

    public void setKandang_id(String kandang_id) {
        this.kandang_id = kandang_id;
    }

    public String getSpesies() {
        return spesies;
    }

    public void setSpesies(String spesies) {
        this.spesies = spesies;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public String getIdentifikasiHewan() {
        return identifikasiHewan;
    }

    public void setIdentifikasiHewan(String identifikasiHewan) {
        this.identifikasiHewan = identifikasiHewan;
    }

    public String getTanggalTerdaftar() {
        return tanggalTerdaftar;
    }

    public void setTanggalTerdaftar(String tanggalTerdaftar) {
        this.tanggalTerdaftar = tanggalTerdaftar;
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
            case "kodeEartagNasional":
                this.kodeEartagNasional = value;
                break;
            case "noKartuTernak":
                this.noKartuTernak = value;
                break;
            case "petugas_id":
                this.petugas_id = value;
                break;
            case "peternak_id":
                this.peternak_id = value;
                break;
            case "kandang_id":
                this.kandang_id = value;
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
            case "spesies":
                this.spesies = value;
                break;
            case "sex":
                this.sex = value;
                break;
            case "umur":
                this.umur = value;
                break;
            case "identifikasiHewan":
                this.identifikasiHewan = value;
                break;
            case "tanggalTerdaftar":
                this.tanggalTerdaftar = value;
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
