package com.ternak.sapi.repository;

import com.ternak.sapi.helper.HBaseCustomClient;
import com.ternak.sapi.model.Kelahiran;
import com.ternak.sapi.model.User;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;

import java.io.IOException;
import java.util.*;

public class KelahiranRepository {
    Configuration conf = HBaseConfiguration.create();
    String tableName = "kelahirans";

    public List<Kelahiran> findAll(int size) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableKelahiran = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("idKejadian", "idKejadian");
        columnMapping.put("tanggalLaporan", "tanggalLaporan");
        columnMapping.put("tanggalLahir", "tanggalLahir");
        columnMapping.put("lokasi", "lokasi");
        columnMapping.put("petugas", "petugas");
        columnMapping.put("peternak", "peternak");
        columnMapping.put("hewan", "hewan");
        columnMapping.put("kartuTernakInduk", "kartuTernakInduk");
        columnMapping.put("eartagInduk", "eartagInduk");
        columnMapping.put("spesiesInduk", "spesiesInduk");
        columnMapping.put("idPejantanStraw", "idPejantanStraw");
        columnMapping.put("idBatchStraw", "idBatchStraw");
        columnMapping.put("produsenStraw", "produsenStraw");
        columnMapping.put("spesiesPejantan", "spesiesPejantan");
        columnMapping.put("jumlah", "jumlah");
        columnMapping.put("kartuTernakAnak", "kartuTernakAnak");
        columnMapping.put("eartagAnak", "eartagAnak");
        columnMapping.put("idHewanAnak", "idHewanAnak");
        columnMapping.put("jenisKelaminAnak", "jenisKelaminAnak");
        columnMapping.put("kategori", "kategori");
        columnMapping.put("urutanIb", "urutanIb");

        return client.showListTable(tableKelahiran.toString(), columnMapping, Kelahiran.class, size);
    }

    public Kelahiran save(Kelahiran kelahiran) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        String rowKey = kelahiran.getIdKejadian();

        TableName tableKelahiran = TableName.valueOf(tableName);
        client.insertRecord(tableKelahiran, rowKey, "main", "idKejadian", rowKey);
        client.insertRecord(tableKelahiran, rowKey, "main", "tanggalLaporan", kelahiran.getTanggalLaporan());
        client.insertRecord(tableKelahiran, rowKey, "main", "tanggalLahir", kelahiran.getTanggalLahir());
        client.insertRecord(tableKelahiran, rowKey, "main", "lokasi", kelahiran.getLokasi());
        client.insertRecord(tableKelahiran, rowKey, "main", "kartuTernakInduk", kelahiran.getKartuTernakInduk());
        client.insertRecord(tableKelahiran, rowKey, "main", "eartagInduk", kelahiran.getEartagInduk());
        client.insertRecord(tableKelahiran, rowKey, "main", "spesiesInduk", kelahiran.getSpesiesInduk());
        client.insertRecord(tableKelahiran, rowKey, "main", "idPejantanStraw", kelahiran.getIdPejantanStraw());
        client.insertRecord(tableKelahiran, rowKey, "main", "idBatchStraw", kelahiran.getIdBatchStraw());
        client.insertRecord(tableKelahiran, rowKey, "main", "produsenStraw", kelahiran.getProdusenStraw());
        client.insertRecord(tableKelahiran, rowKey, "main", "spesiesPejantan", kelahiran.getSpesiesPejantan());
        client.insertRecord(tableKelahiran, rowKey, "main", "jumlah", kelahiran.getJumlah());
        client.insertRecord(tableKelahiran, rowKey, "main", "kartuTernakAnak", kelahiran.getKartuTernakAnak());
        client.insertRecord(tableKelahiran, rowKey, "main", "eartagAnak", kelahiran.getEartagAnak());
        client.insertRecord(tableKelahiran, rowKey, "main", "idHewanAnak", kelahiran.getIdHewanAnak());
        client.insertRecord(tableKelahiran, rowKey, "main", "jenisKelaminAnak", kelahiran.getJenisKelaminAnak());
        client.insertRecord(tableKelahiran, rowKey, "main", "kategori", kelahiran.getKategori());
        client.insertRecord(tableKelahiran, rowKey, "main", "urutanIb", kelahiran.getUrutanIb());
        client.insertRecord(tableKelahiran, rowKey, "peternak", "idPeternak", kelahiran.getPeternak().getIdPeternak());
        client.insertRecord(tableKelahiran, rowKey, "peternak", "nikPeternak", kelahiran.getPeternak().getNikPeternak());
        client.insertRecord(tableKelahiran, rowKey, "peternak", "namaPeternak", kelahiran.getPeternak().getNamaPeternak());
        client.insertRecord(tableKelahiran, rowKey, "petugas", "nikPetugas", kelahiran.getPetugas().getNikPetugas());
        client.insertRecord(tableKelahiran, rowKey, "petugas", "namaPetugas", kelahiran.getPetugas().getNamaPetugas());
        client.insertRecord(tableKelahiran, rowKey, "hewan", "kodeEartagNasional", kelahiran.getHewan().getKodeEartagNasional());
        client.insertRecord(tableKelahiran, rowKey, "hewan", "noKartuTernak", kelahiran.getHewan().getNoKartuTernak());
        client.insertRecord(tableKelahiran, rowKey, "hewan", "alamat", kelahiran.getHewan().getAlamat());
        client.insertRecord(tableKelahiran, rowKey, "detail", "created_by", "Polinema");
        return kelahiran;
    }

    public Kelahiran findKelahiranById(String kelahiranId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableKelahiran = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("idKejadian", "idKejadian");
        columnMapping.put("tanggalLaporan", "tanggalLaporan");
        columnMapping.put("tanggalLahir", "tanggalLahir");
        columnMapping.put("lokasi", "lokasi");
        columnMapping.put("petugas", "petugas");
        columnMapping.put("peternak", "peternak");
        columnMapping.put("hewan", "hewan");
        columnMapping.put("kartuTernakInduk", "kartuTernakInduk");
        columnMapping.put("eartagInduk", "eartagInduk");
        columnMapping.put("spesiesInduk", "spesiesInduk");
        columnMapping.put("idPejantanStraw", "idPejantanStraw");
        columnMapping.put("idBatchStraw", "idBatchStraw");
        columnMapping.put("produsenStraw", "produsenStraw");
        columnMapping.put("spesiesPejantan", "spesiesPejantan");
        columnMapping.put("jumlah", "jumlah");
        columnMapping.put("kartuTernakAnak", "kartuTernakAnak");
        columnMapping.put("eartagAnak", "eartagAnak");
        columnMapping.put("idHewanAnak", "idHewanAnak");
        columnMapping.put("jenisKelaminAnak", "jenisKelaminAnak");
        columnMapping.put("kategori", "kategori");
        columnMapping.put("urutanIb", "urutanIb");

        return client.showDataTable(tableKelahiran.toString(), columnMapping, kelahiranId, Kelahiran.class);
    }

    public List<Kelahiran> findKelahiranByPeternak(String peternakId, int size) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName table = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();
        // Add the mappings to the HashMap
        columnMapping.put("idKejadian", "idKejadian");
        columnMapping.put("tanggalLaporan", "tanggalLaporan");
        columnMapping.put("tanggalLahir", "tanggalLahir");
        columnMapping.put("lokasi", "lokasi");
        columnMapping.put("petugas", "petugas");
        columnMapping.put("peternak", "peternak");
        columnMapping.put("hewan", "hewan");
        columnMapping.put("kartuTernakInduk", "kartuTernakInduk");
        columnMapping.put("eartagInduk", "eartagInduk");
        columnMapping.put("spesiesInduk", "spesiesInduk");
        columnMapping.put("idPejantanStraw", "idPejantanStraw");
        columnMapping.put("idBatchStraw", "idBatchStraw");
        columnMapping.put("produsenStraw", "produsenStraw");
        columnMapping.put("spesiesPejantan", "spesiesPejantan");
        columnMapping.put("jumlah", "jumlah");
        columnMapping.put("kartuTernakAnak", "kartuTernakAnak");
        columnMapping.put("eartagAnak", "eartagAnak");
        columnMapping.put("idHewanAnak", "idHewanAnak");
        columnMapping.put("jenisKelaminAnak", "jenisKelaminAnak");
        columnMapping.put("kategori", "kategori");
        columnMapping.put("urutanIb", "urutanIb");

        return client.getDataListByColumn(table.toString(), columnMapping, "main", "peternak", peternakId, Kelahiran.class, size);
    }
    
    public List<Kelahiran> findKelahiranByPetugas(String petugasId, int size) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName table = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();
        // Add the mappings to the HashMap
        columnMapping.put("idKejadian", "idKejadian");
        columnMapping.put("tanggalLaporan", "tanggalLaporan");
        columnMapping.put("tanggalLahir", "tanggalLahir");
        columnMapping.put("lokasi", "lokasi");
        columnMapping.put("petugas", "petugas");
        columnMapping.put("peternak", "peternak");
        columnMapping.put("hewan", "hewan");
        columnMapping.put("kartuTernakInduk", "kartuTernakInduk");
        columnMapping.put("eartagInduk", "eartagInduk");
        columnMapping.put("spesiesInduk", "spesiesInduk");
        columnMapping.put("idPejantanStraw", "idPejantanStraw");
        columnMapping.put("idBatchStraw", "idBatchStraw");
        columnMapping.put("produsenStraw", "produsenStraw");
        columnMapping.put("spesiesPejantan", "spesiesPejantan");
        columnMapping.put("jumlah", "jumlah");
        columnMapping.put("kartuTernakAnak", "kartuTernakAnak");
        columnMapping.put("eartagAnak", "eartagAnak");
        columnMapping.put("idHewanAnak", "idHewanAnak");
        columnMapping.put("jenisKelaminAnak", "jenisKelaminAnak");
        columnMapping.put("kategori", "kategori");
        columnMapping.put("urutanIb", "urutanIb");

        return client.getDataListByColumn(table.toString(), columnMapping, "main", "petugas", petugasId, Kelahiran.class, size);
    }
    
    public List<Kelahiran> findKelahiranByHewan(String hewanId, int size) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName table = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();
        // Add the mappings to the HashMap
        columnMapping.put("idKejadian", "idKejadian");
        columnMapping.put("tanggalLaporan", "tanggalLaporan");
        columnMapping.put("tanggalLahir", "tanggalLahir");
        columnMapping.put("lokasi", "lokasi");
        columnMapping.put("petugas", "petugas");
        columnMapping.put("peternak", "peternak");
        columnMapping.put("hewan", "hewan");
        columnMapping.put("kartuTernakInduk", "kartuTernakInduk");
        columnMapping.put("eartagInduk", "eartagInduk");
        columnMapping.put("spesiesInduk", "spesiesInduk");
        columnMapping.put("idPejantanStraw", "idPejantanStraw");
        columnMapping.put("idBatchStraw", "idBatchStraw");
        columnMapping.put("produsenStraw", "produsenStraw");
        columnMapping.put("spesiesPejantan", "spesiesPejantan");
        columnMapping.put("jumlah", "jumlah");
        columnMapping.put("kartuTernakAnak", "kartuTernakAnak");
        columnMapping.put("eartagAnak", "eartagAnak");
        columnMapping.put("idHewanAnak", "idHewanAnak");
        columnMapping.put("jenisKelaminAnak", "jenisKelaminAnak");
        columnMapping.put("kategori", "kategori");
        columnMapping.put("urutanIb", "urutanIb");

        return client.getDataListByColumn(table.toString(), columnMapping, "main", "hewan", hewanId, Kelahiran.class, size);
    }

    public List<Kelahiran> findAllById(List<String> kelahiranIds) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName table = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();
        // Add the mappings to the HashMap
        columnMapping.put("idKelahiran", "idKelahiran");
        columnMapping.put("tanggalIB", "tanggalIB");
        columnMapping.put("lokasi", "lokasi");
        columnMapping.put("peternak", "peternak");
        columnMapping.put("hewan", "hewan");
        columnMapping.put("petugas", "petugas");
        columnMapping.put("ib1", "ib1");
        columnMapping.put("ib2", "ib2");
        columnMapping.put("ib3", "ib3");
        columnMapping.put("ibLain", "ibLain");
        columnMapping.put("idPejantan", "idPejantan");
        columnMapping.put("idPembuatan", "idPembuatan");
        columnMapping.put("bangsaPejantan", "bangsaPejantan");
        columnMapping.put("produsen", "produsen");

        List<Kelahiran> kelahirans = new ArrayList<>();
        for (String kelahiranId : kelahiranIds) {
            Kelahiran kelahiran = client.showDataTable(table.toString(), columnMapping, kelahiranId, Kelahiran.class);
            if (kelahiran != null) {
                kelahirans.add(kelahiran);
            }
        }

        return kelahirans;
    }

    public Kelahiran update(String kelahiranId, Kelahiran kelahiran) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableKelahiran = TableName.valueOf(tableName);
        client.insertRecord(tableKelahiran, kelahiranId, "main", "tanggalLaporan", kelahiran.getTanggalLaporan());
        client.insertRecord(tableKelahiran, kelahiranId, "main", "tanggalLahir", kelahiran.getTanggalLahir());
        client.insertRecord(tableKelahiran, kelahiranId, "main", "lokasi", kelahiran.getLokasi());
        client.insertRecord(tableKelahiran, kelahiranId, "main", "kartuTernakInduk", kelahiran.getKartuTernakInduk());
        client.insertRecord(tableKelahiran, kelahiranId, "main", "eartagInduk", kelahiran.getEartagInduk());
        client.insertRecord(tableKelahiran, kelahiranId, "main", "spesiesInduk", kelahiran.getSpesiesInduk());
        client.insertRecord(tableKelahiran, kelahiranId, "main", "idPejantanStraw", kelahiran.getIdPejantanStraw());
        client.insertRecord(tableKelahiran, kelahiranId, "main", "idBatchStraw", kelahiran.getIdBatchStraw());
        client.insertRecord(tableKelahiran, kelahiranId, "main", "produsenStraw", kelahiran.getProdusenStraw());
        client.insertRecord(tableKelahiran, kelahiranId, "main", "spesiesPejantan", kelahiran.getSpesiesPejantan());
        client.insertRecord(tableKelahiran, kelahiranId, "main", "jumlah", kelahiran.getJumlah());
        client.insertRecord(tableKelahiran, kelahiranId, "main", "kartuTernakAnak", kelahiran.getKartuTernakAnak());
        client.insertRecord(tableKelahiran, kelahiranId, "main", "eartagAnak", kelahiran.getEartagAnak());
        client.insertRecord(tableKelahiran, kelahiranId, "main", "idHewanAnak", kelahiran.getIdHewanAnak());
        client.insertRecord(tableKelahiran, kelahiranId, "main", "jenisKelaminAnak", kelahiran.getJenisKelaminAnak());
        client.insertRecord(tableKelahiran, kelahiranId, "main", "kategori", kelahiran.getKategori());
        client.insertRecord(tableKelahiran, kelahiranId, "main", "urutanIb", kelahiran.getUrutanIb());
        client.insertRecord(tableKelahiran, kelahiranId, "peternak", "idPeternak", kelahiran.getPeternak().getIdPeternak());
        client.insertRecord(tableKelahiran, kelahiranId, "peternak", "nikPeternak", kelahiran.getPeternak().getNikPeternak());
        client.insertRecord(tableKelahiran, kelahiranId, "peternak", "namaPeternak", kelahiran.getPeternak().getNamaPeternak());
        client.insertRecord(tableKelahiran, kelahiranId, "petugas", "nikPetugas", kelahiran.getPetugas().getNikPetugas());
        client.insertRecord(tableKelahiran, kelahiranId, "petugas", "namaPetugas", kelahiran.getPetugas().getNamaPetugas());
        client.insertRecord(tableKelahiran, kelahiranId, "hewan", "kodeEartagNasional", kelahiran.getHewan().getKodeEartagNasional());
        client.insertRecord(tableKelahiran, kelahiranId, "hewan", "noKartuTernak", kelahiran.getHewan().getNoKartuTernak());
        client.insertRecord(tableKelahiran, kelahiranId, "hewan", "alamat", kelahiran.getHewan().getAlamat());
        client.insertRecord(tableKelahiran, kelahiranId, "detail", "created_by", "Polinema");
        return kelahiran;
    }

    public boolean deleteById(String kelahiranId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        client.deleteRecord(tableName, kelahiranId);
        return true;
    }
}
