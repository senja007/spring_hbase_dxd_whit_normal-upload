package com.ternak.sapi.model;

public class Petugas {
    private String nikPetugas;
    private String namaPetugas;
    private String noTelp;
    private String email;

    public Petugas() {
    }

    public Petugas(String nikPetugas, String namaPetugas, String noTelp, String email) {
        this.nikPetugas = nikPetugas;
        this.namaPetugas = namaPetugas;
        this.noTelp = noTelp;
        this.email = email;
    }

    public String getNikPetugas() {
        return nikPetugas;
    }

    public void setNikPetugas(String nikPetugas) {
        this.nikPetugas = nikPetugas;
    }

    public String getNamaPetugas() {
        return namaPetugas;
    }

    public void setNamaPetugas(String namaPetugas) {
        this.namaPetugas = namaPetugas;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isValid() {
        return this.nikPetugas != null  && this.namaPetugas != null && this.noTelp != null && this.email != null;
    }

    public void set(String fieldName, String value) {
        switch (fieldName) {
            case "nikPetugas":
                this.nikPetugas = value;
                break;
            case "namaPetugas":
                this.namaPetugas = value;
                break;
            case "noTelp":
                this.noTelp = value;
                break;
            case "email":
                this.email = value;
                break;
            default:
                throw new IllegalArgumentException("Invalid field name: " + fieldName);
        }
    }
}
