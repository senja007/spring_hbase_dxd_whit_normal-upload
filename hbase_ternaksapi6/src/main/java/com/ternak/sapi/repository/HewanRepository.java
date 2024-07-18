
package com.ternak.sapi.repository;

import com.ternak.sapi.controller.HewanController;
import com.ternak.sapi.helper.HBaseCustomClient;
import com.ternak.sapi.model.Hewan;
import com.ternak.sapi.model.Hewan;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;

import java.io.IOException;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HewanRepository {
    private static final Logger logger = LoggerFactory.getLogger(HewanRepository.class);
    Configuration conf = HBaseConfiguration.create();
    String tableName = "hewans";

    public List<Hewan> findAll(int size) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableHewan = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("kodeEartagNasional", "kodeEartagNasional");
        columnMapping.put("noKartuTernak", "noKartuTernak");
        columnMapping.put("peternak", "peternak");
        columnMapping.put("petugas", "petugas");
        columnMapping.put("kandang", "kandang");
        columnMapping.put("spesies", "spesies");
        columnMapping.put("sex", "sex");
        columnMapping.put("umur", "umur");
        columnMapping.put("identifikasiHewan", "identifikasiHewan");
        columnMapping.put("tanggalTerdaftar", "tanggalTerdaftar");
        columnMapping.put("alamat", "alamat");
        columnMapping.put("desa", "desa");
        columnMapping.put("kecamatan", "kecamatan");
        columnMapping.put("kabupaten", "kabupaten");
        columnMapping.put("provinsi", "provinsi");
        columnMapping.put("latitude", "latitude");
        columnMapping.put("longitude", "longitude");
        columnMapping.put("file_path", "file_path");
        columnMapping.put("fotoType", "fotoType");
        columnMapping.put("data", "data");
        List<Hewan> hewans = client.showListTable(tableHewan.toString(), columnMapping, Hewan.class, size);

        for (Hewan hewan : hewans) {
            String dataString = client.getValue(tableHewan.toString(), hewan.getKodeEartagNasional(), "main", "data");
            if (dataString != null) {
                logger.info("Raw data string retrieved for ID " + hewan.getKodeEartagNasional() + ": " + dataString);
                hewan.setData(Base64.getDecoder().decode(dataString));
                logger.info("Successfully retrieved data for Hewan ID: " + hewan.getKodeEartagNasional());
            } else {
                logger.info("Data is null when retrieving for Hewan ID: " + hewan.getKodeEartagNasional());
            }
        }

        return hewans;
    }

    public Hewan save(Hewan hewan) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        String rowKey = hewan.getKodeEartagNasional();
        TableName tableHewan = TableName.valueOf(tableName);
        client.insertRecord(tableHewan, rowKey, "main", "kodeEartagNasional", hewan.getKodeEartagNasional());
        client.insertRecord(tableHewan, rowKey, "main", "noKartuTernak", hewan.getNoKartuTernak());
        client.insertRecord(tableHewan, rowKey, "main", "spesies", hewan.getSpesies());
        client.insertRecord(tableHewan, rowKey, "main", "sex", hewan.getSex());
        client.insertRecord(tableHewan, rowKey, "main", "umur", hewan.getUmur());
        client.insertRecord(tableHewan, rowKey, "main", "identifikasiHewan", hewan.getIdentifikasiHewan());
        client.insertRecord(tableHewan, rowKey, "main", "tanggalTerdaftar", hewan.getTanggalTerdaftar());
        client.insertRecord(tableHewan, rowKey, "main", "alamat", hewan.getAlamat());
        client.insertRecord(tableHewan, rowKey, "main", "desa", hewan.getDesa());
        client.insertRecord(tableHewan, rowKey, "main", "kecamatan", hewan.getKecamatan());
        client.insertRecord(tableHewan, rowKey, "main", "kabupaten", hewan.getKabupaten());
        client.insertRecord(tableHewan, rowKey, "main", "provinsi", hewan.getProvinsi());
        client.insertRecord(tableHewan, rowKey, "main", "latitude", hewan.getLatitude());
        client.insertRecord(tableHewan, rowKey, "main", "longitude", hewan.getLongitude());
        client.insertRecord(tableHewan, rowKey, "peternak", "idPeternak", hewan.getPeternak().getIdPeternak());
        client.insertRecord(tableHewan, rowKey, "peternak", "nikPeternak", hewan.getPeternak().getNikPeternak());
        client.insertRecord(tableHewan, rowKey, "peternak", "namaPeternak", hewan.getPeternak().getNamaPeternak());
        client.insertRecord(tableHewan, rowKey, "peternak", "idISIKHNAS", hewan.getPeternak().getIdISIKHNAS());
        client.insertRecord(tableHewan, rowKey, "petugas", "nikPetugas", hewan.getPetugas().getNikPetugas());
        client.insertRecord(tableHewan, rowKey, "petugas", "namaPetugas", hewan.getPetugas().getNamaPetugas());
        client.insertRecord(tableHewan, rowKey, "kandang", "idKandang", hewan.getKandang().getIdKandang());
        client.insertRecord(tableHewan, rowKey, "kandang", "alamat", hewan.getKandang().getAlamat());
        client.insertRecord(tableHewan, rowKey, "main", "file_path", hewan.getFile_path());
        client.insertRecord(tableHewan, rowKey, "main", "fotoType", hewan.getFotoType());
         byte[] data = hewan.getData();
        if (data != null) {
            String dataString = Base64.getEncoder().encodeToString(data);
            client.insertRecord(tableHewan, rowKey, "main", "data", dataString);
        }
        client.insertRecord(tableHewan, rowKey, "detail", "created_by", "Polinema");
        return hewan;
    }

    public Hewan findById(String hewanId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableHewan = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("kodeEartagNasional", "kodeEartagNasional");
        columnMapping.put("noKartuTernak", "noKartuTernak");
        columnMapping.put("peternak", "peternak");
        columnMapping.put("petugas", "petugas");
        columnMapping.put("kandang", "kandang");
        columnMapping.put("spesies", "spesies");
        columnMapping.put("sex", "sex");
        columnMapping.put("umur", "umur");
        columnMapping.put("identifikasiHewan", "identifikasiHewan");
        columnMapping.put("tanggalTerdaftar", "tanggalTerdaftar");
        columnMapping.put("alamat", "alamat");
        columnMapping.put("desa", "desa");
        columnMapping.put("kecamatan", "kecamatan");
        columnMapping.put("kabupaten", "kabupaten");
        columnMapping.put("provinsi", "provinsi");
        columnMapping.put("latitude", "latitude");
        columnMapping.put("longitude", "longitude");
        columnMapping.put("file_path", "file_path");
        columnMapping.put("fotoType", "fotoType");
        columnMapping.put("data", "data");
        Hewan hewan = client.showDataTable(tableHewan.toString(), columnMapping, hewanId, Hewan.class);
        String dataString = client.getValue(tableHewan.toString(), hewanId, "main", "data");
        if (dataString != null) {
            hewan.setData(Base64.getDecoder().decode(dataString));
        } else {
            logger.info("Data is null when retrieving");
        }
        return hewan;
    }

    public List<Hewan> findHewanByPeternak(String peternakId, int size) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableHewan = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("kodeEartagNasional", "kodeEartagNasional");
        columnMapping.put("noKartuTernak", "noKartuTernak");
        columnMapping.put("peternak", "peternak");
        columnMapping.put("petugas", "petugas");
        columnMapping.put("kandang", "kandang");
        columnMapping.put("spesies", "spesies");
        columnMapping.put("sex", "sex");
        columnMapping.put("umur", "umur");
        columnMapping.put("identifikasiHewan", "identifikasiHewan");
        columnMapping.put("tanggalTerdaftar", "tanggalTerdaftar");
        columnMapping.put("alamat", "alamat");
        columnMapping.put("desa", "desa");
        columnMapping.put("kecamatan", "kecamatan");
        columnMapping.put("kabupaten", "kabupaten");
        columnMapping.put("provinsi", "provinsi");
        columnMapping.put("latitude", "latitude");
        columnMapping.put("longitude", "longitude");
        columnMapping.put("file_path", "file_path");
        columnMapping.put("fotoType", "fotoType");
        columnMapping.put("data", "data");
        List<Hewan> hewan = client.getDataListByColumn(tableHewan.toString(), columnMapping, "peternak", "id", peternakId, Hewan.class, size);

        return hewan;
    }
    
    public List<Hewan> findHewanByKandang(String kandangId, int size) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableHewan = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("kodeEartagNasional", "kodeEartagNasional");
        columnMapping.put("noKartuTernak", "noKartuTernak");
        columnMapping.put("peternak", "peternak");
        columnMapping.put("petugas", "petugas");
        columnMapping.put("kandang", "kandang");
        columnMapping.put("spesies", "spesies");
        columnMapping.put("sex", "sex");
        columnMapping.put("umur", "umur");
        columnMapping.put("identifikasiHewan", "identifikasiHewan");
        columnMapping.put("tanggalTerdaftar", "tanggalTerdaftar");
        columnMapping.put("alamat", "alamat");
        columnMapping.put("desa", "desa");
        columnMapping.put("kecamatan", "kecamatan");
        columnMapping.put("kabupaten", "kabupaten");
        columnMapping.put("provinsi", "provinsi");
        columnMapping.put("latitude", "latitude");
        columnMapping.put("longitude", "longitude");
        columnMapping.put("file_path", "file_path");
        columnMapping.put("fotoType", "fotoType");
        columnMapping.put("data", "data");
        List<Hewan> hewan = client.getDataListByColumn(tableHewan.toString(), columnMapping, "kandang", "id", kandangId, Hewan.class, size);

        return hewan;
    }
    
    public List<Hewan> findHewanByPetugas(String petugasId, int size) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableHewan = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("kodeEartagNasional", "kodeEartagNasional");
        columnMapping.put("noKartuTernak", "noKartuTernak");
        columnMapping.put("peternak", "peternak");
        columnMapping.put("petugas", "petugas");
        columnMapping.put("kandang", "kandang");
        columnMapping.put("spesies", "spesies");
        columnMapping.put("sex", "sex");
        columnMapping.put("umur", "umur");
        columnMapping.put("identifikasiHewan", "identifikasiHewan");
        columnMapping.put("tanggalTerdaftar", "tanggalTerdaftar");
        columnMapping.put("alamat", "alamat");
        columnMapping.put("desa", "desa");
        columnMapping.put("kecamatan", "kecamatan");
        columnMapping.put("kabupaten", "kabupaten");
        columnMapping.put("provinsi", "provinsi");
        columnMapping.put("latitude", "latitude");
        columnMapping.put("longitude", "longitude");
        columnMapping.put("file_path", "file_path");
        columnMapping.put("fotoType", "fotoType");
        columnMapping.put("data", "data");
        List<Hewan> hewan = client.getDataListByColumn(tableHewan.toString(), columnMapping, "petugas", "id", petugasId, Hewan.class, size);

        return hewan;
    }

    public List<Hewan> findAllById(List<String> hewanIds) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName table = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();
        // Add the mappings to the HashMap
        columnMapping.put("kodeEartagNasional", "kodeEartagNasional");
        columnMapping.put("noKartuTernak", "noKartuTernak");
        columnMapping.put("peternak", "peternak");
        columnMapping.put("petugas", "petugas");
        columnMapping.put("kandang", "kandang");
        columnMapping.put("spesies", "spesies");
        columnMapping.put("sex", "sex");
        columnMapping.put("umur", "umur");
        columnMapping.put("identifikasiHewan", "identifikasiHewan");
        columnMapping.put("tanggalTerdaftar", "tanggalTerdaftar");
        columnMapping.put("alamat", "alamat");
        columnMapping.put("desa", "desa");
        columnMapping.put("kecamatan", "kecamatan");
        columnMapping.put("kabupaten", "kabupaten");
        columnMapping.put("provinsi", "provinsi");
        columnMapping.put("latitude", "latitude");
        columnMapping.put("longitude", "longitude");
        columnMapping.put("file_path", "file_path");

        List<Hewan> hewans = new ArrayList<>();
        for (String hewanId : hewanIds) {
            Hewan hewan = client.showDataTable(table.toString(), columnMapping, hewanId, Hewan.class);
            if (hewan != null) {
                hewans.add(hewan);
            }
        }

        return hewans;
    }

    public Hewan update(String hewanId, Hewan hewan) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableHewan = TableName.valueOf(tableName);
        client.insertRecord(tableHewan, hewanId, "main", "noKartuTernak", hewan.getNoKartuTernak());
        client.insertRecord(tableHewan, hewanId, "main", "spesies", hewan.getSpesies());
        client.insertRecord(tableHewan, hewanId, "main", "sex", hewan.getSex());
        client.insertRecord(tableHewan, hewanId, "main", "umur", hewan.getUmur());
        client.insertRecord(tableHewan, hewanId, "main", "identifikasiHewan", hewan.getIdentifikasiHewan());
        client.insertRecord(tableHewan, hewanId, "main", "tanggalTerdaftar", hewan.getTanggalTerdaftar());
        client.insertRecord(tableHewan, hewanId, "main", "alamat", hewan.getAlamat());
        client.insertRecord(tableHewan, hewanId, "main", "desa", hewan.getDesa());
        client.insertRecord(tableHewan, hewanId, "main", "kecamatan", hewan.getKecamatan());
        client.insertRecord(tableHewan, hewanId, "main", "kabupaten", hewan.getKabupaten());
        client.insertRecord(tableHewan, hewanId, "main", "provinsi", hewan.getProvinsi());
        client.insertRecord(tableHewan, hewanId, "main", "latitude", hewan.getLatitude());
        client.insertRecord(tableHewan, hewanId, "main", "longitude", hewan.getLongitude());
        client.insertRecord(tableHewan, hewanId, "peternak", "idPeternak", hewan.getPeternak().getIdPeternak());
        client.insertRecord(tableHewan, hewanId, "peternak", "nikPeternak", hewan.getPeternak().getNikPeternak());
        client.insertRecord(tableHewan, hewanId, "peternak", "namaPeternak", hewan.getPeternak().getNamaPeternak());
        client.insertRecord(tableHewan, hewanId, "peternak", "idISIKHNAS", hewan.getPeternak().getIdISIKHNAS());
        client.insertRecord(tableHewan, hewanId, "petugas", "nikPetugas", hewan.getPetugas().getNikPetugas());
        client.insertRecord(tableHewan, hewanId, "petugas", "namaPetugas", hewan.getPetugas().getNamaPetugas());
        client.insertRecord(tableHewan, hewanId, "kandang", "idKandang", hewan.getKandang().getIdKandang());
        client.insertRecord(tableHewan, hewanId, "kandang", "alamat", hewan.getKandang().getAlamat());
        client.insertRecord(tableHewan, hewanId, "main", "file_path", hewan.getFile_path());
        client.insertRecord(tableHewan, hewanId, "main", "fotoType", hewan.getFotoType());
        byte[] data = hewan.getData();
        if (data != null) {
            String dataString = Base64.getEncoder().encodeToString(data);
            client.insertRecord(tableHewan, hewanId, "main", "data", dataString);
        }
        return hewan;
    }

    public boolean deleteById(String hewanId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        client.deleteRecord(tableName, hewanId);
        return true;
    }
}
