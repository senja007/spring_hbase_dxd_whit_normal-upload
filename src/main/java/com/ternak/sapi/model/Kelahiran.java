package com.ternak.sapi.model;

import java.time.Instant;
import java.util.List;

public class Kelahiran {
    private String idKejadian;
    private String tanggalLaporan;
    private String tanggalLahir;
    private String lokasi;
    private Peternak peternak;
    private Hewan hewan;
    private Petugas petugas;
    private String kartuTernakInduk;
    private String eartagInduk;
    private String spesiesInduk;
    private String idPejantanStraw;
    private String idBatchStraw;
    private String produsenStraw;
    private String spesiesPejantan;
    private String jumlah;
    private String kartuTernakAnak;
    private String eartagAnak;
    private String idHewanAnak;
    private String jenisKelaminAnak;
    private String kategori;
    private String urutanIb;

    public Kelahiran() {
    }

    public Kelahiran(String idKejadian, String tanggalLaporan, String tanggalLahir, String lokasi, Peternak peternak, Hewan hewan, 
            Petugas petugas, String kartuTernakInduk, String eartagInduk, String spesiesInduk, String idPejantanStraw, 
            String idBatchStraw, String produsenStraw, String spesiesPejantan, String jumlah, String kartuTernakAnak, 
            String eartagAnak, String idHewanAnak, String jenisKelaminAnak,String kategori, String urutanIb) {
        this.idKejadian = idKejadian;
        this.tanggalLaporan = tanggalLaporan;
        this.tanggalLahir = tanggalLahir;
        this.lokasi = lokasi;
        this.peternak = peternak;
        this.hewan = hewan;
        this.petugas = petugas;
        this.kartuTernakInduk = kartuTernakInduk;
        this.eartagInduk = eartagInduk;
        this.spesiesInduk = spesiesInduk;
        this.idPejantanStraw = idPejantanStraw;
        this.idBatchStraw = idBatchStraw;
        this.produsenStraw = produsenStraw;
        this.spesiesPejantan = spesiesPejantan;
        this.jumlah = jumlah;
        this.kartuTernakAnak = kartuTernakAnak;
        this.eartagAnak = eartagAnak;
        this.idHewanAnak = idHewanAnak;
        this.jenisKelaminAnak = jenisKelaminAnak;
        this.kategori = kategori;
        this.urutanIb = urutanIb;
    }

    public String getIdKejadian() {
        return idKejadian;
    }

    public void setIdKejadian(String idKejadian) {
        this.idKejadian = idKejadian;
    }

    public String getTanggalLaporan() {
        return tanggalLaporan;
    }

    public void setTanggalLaporan(String tanggalLaporan) {
        this.tanggalLaporan = tanggalLaporan;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
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

    public Petugas getPetugas() {
        return petugas;
    }

    public void setPetugas(Petugas petugas) {
        this.petugas = petugas;
    }

    public Hewan getHewan() {
        return hewan;
    }

    public void setHewan(Hewan hewan) {
        this.hewan = hewan;
    }

    public String getKartuTernakInduk() {
        return kartuTernakInduk;
    }

    public void setKartuTernakInduk(String kartuTernakInduk) {
        this.kartuTernakInduk = kartuTernakInduk;
    }

    public String getEartagInduk() {
        return eartagInduk;
    }

    public void setEartagInduk(String eartagInduk) {
        this.eartagInduk = eartagInduk;
    }

    public String getSpesiesInduk() {
        return spesiesInduk;
    }

    public void setSpesiesInduk(String spesiesInduk) {
        this.spesiesInduk = spesiesInduk;
    }

    public String getIdPejantanStraw() {
        return idPejantanStraw;
    }

    public void setIdPejantanStraw(String idPejantanStraw) {
        this.idPejantanStraw = idPejantanStraw;
    }

    public String getIdBatchStraw() {
        return idBatchStraw;
    }

    public void setIdBatchStraw(String idBatchStraw) {
        this.idBatchStraw = idBatchStraw;
    }

    public String getProdusenStraw() {
        return produsenStraw;
    }

    public void setProdusenStraw(String produsenStraw) {
        this.produsenStraw = produsenStraw;
    }

    public String getSpesiesPejantan() {
        return spesiesPejantan;
    }

    public void setSpesiesPejantan(String spesiesPejantan) {
        this.spesiesPejantan = spesiesPejantan;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getKartuTernakAnak() {
        return kartuTernakAnak;
    }

    public void setKartuTernakAnak(String kartuTernakAnak) {
        this.kartuTernakAnak = kartuTernakAnak;
    }

    public String getEartagAnak() {
        return eartagAnak;
    }

    public void setEartagAnak(String eartagAnak) {
        this.eartagAnak = eartagAnak;
    }

    public String getIdHewanAnak() {
        return idHewanAnak;
    }

    public void setIdHewanAnak(String idHewanAnak) {
        this.idHewanAnak = idHewanAnak;
    }

    public String getJenisKelaminAnak() {
        return jenisKelaminAnak;
    }

    public void setJenisKelaminAnak(String jenisKelaminAnak) {
        this.jenisKelaminAnak = jenisKelaminAnak;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getUrutanIb() {
        return urutanIb;
    }

    public void setUrutanIb(String urutanIb) {
        this.urutanIb = urutanIb;
    }

    public boolean isValid() {
        return this.idKejadian != null &&
                this.tanggalLaporan != null &&
                this.tanggalLahir != null &&
                this.lokasi != null &&
                this.peternak != null &&
                this.hewan != null &&
                this.petugas != null &&
                this.kartuTernakInduk != null &&
                this.eartagInduk != null &&
                this.spesiesInduk != null &&
                this.idPejantanStraw != null &&
                this.idBatchStraw != null &&
                this.produsenStraw != null &&
                this.spesiesPejantan != null &&
                this.jumlah != null &&
                this.kartuTernakAnak != null &&
                this.eartagAnak != null &&
                this.idHewanAnak != null &&
                this.jenisKelaminAnak != null &&
                this.kategori != null &&
                this.urutanIb != null;
    }

    public void set(String fieldName, String value) {
        switch (fieldName) {
            case "idKejadian":
                this.idKejadian = value;
                break;
            case "tanggalLaporan":
                this.tanggalLaporan = value;
                break;
            case "tanggalLahir":
                this.tanggalLahir = value;
                break;
            case "lokasi":
                this.lokasi = value;
                break;
            case "kartuTernakInduk":
                this.kartuTernakInduk = value;
                break;
            case "eartagInduk":
                this.eartagInduk = value;
                break;
            case "spesiesInduk":
                this.spesiesInduk = value;
                break;
            case "idPejantanStraw":
                this.idPejantanStraw = value;
                break;
            case "idBatchStraw":
                this.idBatchStraw = value;
                break;
            case "produsenStraw":
                this.produsenStraw = value;
                break;
            case "spesiesPejantan":
                this.spesiesPejantan = value;
                break;
            case "jumlah":
                this.jumlah = value;
                break;
            case "kartuTernakAnak":
                this.kartuTernakAnak = value;
                break;
            case "eartagAnak":
                this.eartagAnak = value;
                break;
            case "idHewanAnak":
                this.idHewanAnak = value;
                break;
            case "jenisKelaminAnak":
                this.jenisKelaminAnak = value;
                break;
            case "kategori":
                this.kategori = value;
                break;
            case "urutanIb":
                this.urutanIb = value;
                break;
            default:
                throw new IllegalArgumentException("Invalid field name: " + fieldName);
        }
    }
}
