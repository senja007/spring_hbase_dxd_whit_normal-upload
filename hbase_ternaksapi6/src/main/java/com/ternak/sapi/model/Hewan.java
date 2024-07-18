package com.ternak.sapi.model;


public class Hewan {
    private String kodeEartagNasional;
    private String noKartuTernak;
    private String provinsi;
    private String kabupaten;
    private String kecamatan;
    private String desa;
    private String alamat;
    private Petugas petugas;
    private Peternak peternak;
    private Kandang kandang;
    private String spesies;
    private String sex;
    private String umur;
    private String identifikasiHewan;
    private String tanggalTerdaftar;
    private String latitude;
    private String longitude;
    private String file_path;
    private String fotoType;
    private byte[] data;

    public enum AnswerType {
        IMAGE,
        AUDIO,
        VIDEO,
        NORMAL,
    }

    public Hewan() {
    }

    public Hewan(String kodeEartagNasional, String noKartuTernak, String provinsi, String kabupaten, 
            String kecamatan, String desa, String alamat, Petugas petugas, Peternak peternak, Kandang kandang, 
            String spesies, String sex, String umur, String identifikasiHewan, String tanggalTerdaftar, String latitude, 
            String longitude, String file_path, String fotoType, byte[] data) {
        this.kodeEartagNasional = kodeEartagNasional;
        this.noKartuTernak = noKartuTernak;
        this.provinsi = provinsi;
        this.kabupaten = kabupaten;
        this.kecamatan = kecamatan;
        this.desa = desa;
        this.alamat = alamat;
        this.petugas = petugas;
        this.peternak = peternak;
        this.kandang = kandang;
        this.spesies = spesies;
        this.sex = sex;
        this.umur = umur;
        this.identifikasiHewan = identifikasiHewan;
        this.tanggalTerdaftar = tanggalTerdaftar;
        this.latitude = latitude;
        this.longitude = longitude;
        this.file_path = file_path;
        this.fotoType = fotoType;
        this.data = data;
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

    public Petugas getPetugas() {
        return petugas;
    }

    public void setPetugas(Petugas petugas) {
        this.petugas = petugas;
    }

    public Peternak getPeternak() {
        return peternak;
    }

    public void setPeternak(Peternak peternak) {
        this.peternak = peternak;
    }

    public Kandang getKandang() {
        return kandang;
    }

    public void setKandang(Kandang kandang) {
        this.kandang = kandang;
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
        return this.kodeEartagNasional != null && 
               this.noKartuTernak != null && 
               this.alamat != null &&
               this.desa != null &&
               this.kecamatan != null &&
               this.kabupaten != null &&
               this.provinsi != null &&
               this.petugas != null &&
               this.peternak != null &&
               this.kandang != null &&
               this.spesies != null &&
               this.sex != null &&
               this.umur != null &&
               this.identifikasiHewan != null &&
               this.tanggalTerdaftar != null &&
               this.latitude != null &&
               this.longitude != null &&
               this.file_path != null && 
               this.fotoType != null && 
               this.data != null ;
    }
    
    public void set(String fieldName, String value) {
        switch (fieldName) {
            case "kodeEartagNasional":
                this.kodeEartagNasional = value;
                break;
            case "noKartuTernak":
                this.noKartuTernak = value;
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