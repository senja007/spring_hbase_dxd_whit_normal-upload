
package com.ternak.sapi.repository;

import com.ternak.sapi.controller.HewanController;
import com.ternak.sapi.helper.HBaseCustomClient;
import com.ternak.sapi.model.Hewan;
import com.ternak.sapi.model.Kandang;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;

import java.io.IOException;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KandangRepository {
    private static final Logger logger = LoggerFactory.getLogger(KandangRepository.class);
    Configuration conf = HBaseConfiguration.create();
    String tableName = "kandangs";
   // HewanController departmentController = new HewanController();

    public List<Kandang> findAll(int size) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableKandang = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("idKandang", "idKandang");
        columnMapping.put("peternak", "peternak");
        columnMapping.put("luas", "luas");
        columnMapping.put("kapasitas", "kapasitas");
        columnMapping.put("nilaiBangunan", "nilaiBangunan");
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
        List<Kandang> kandangs = client.showListTable(tableKandang.toString(), columnMapping, Kandang.class, size);

        for (Kandang kandang : kandangs) {
            String dataString = client.getValue(tableKandang.toString(), kandang.getIdKandang(), "main", "data");
            if (dataString != null) {
                logger.info("Raw data string retrieved for ID " + kandang.getIdKandang() + ": " + dataString);
                kandang.setData(Base64.getDecoder().decode(dataString));
                logger.info("Successfully retrieved data for Kandang ID: " + kandang.getIdKandang());
            } else {
                logger.info("Data is null when retrieving for Kandang ID: " + kandang.getIdKandang());
            }
        }

        return kandangs;
    }

    public Kandang save(Kandang kandang) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        String rowKey = kandang.getIdKandang();
        TableName tableKandang = TableName.valueOf(tableName);
        client.insertRecord(tableKandang, rowKey, "main", "idKandang", kandang.getIdKandang());
        client.insertRecord(tableKandang, rowKey, "main", "luas", kandang.getLuas());
        client.insertRecord(tableKandang, rowKey, "main", "kapasitas", kandang.getKapasitas());
        client.insertRecord(tableKandang, rowKey, "main", "nilaiBangunan", kandang.getNilaiBangunan());
        client.insertRecord(tableKandang, rowKey, "main", "alamat", kandang.getAlamat());
        client.insertRecord(tableKandang, rowKey, "main", "desa", kandang.getDesa());
        client.insertRecord(tableKandang, rowKey, "main", "kecamatan", kandang.getKecamatan());
        client.insertRecord(tableKandang, rowKey, "main", "kabupaten", kandang.getKabupaten());
        client.insertRecord(tableKandang, rowKey, "main", "provinsi", kandang.getProvinsi());
        client.insertRecord(tableKandang, rowKey, "main", "latitude", kandang.getLatitude());
        client.insertRecord(tableKandang, rowKey, "main", "longitude", kandang.getLongitude());
        client.insertRecord(tableKandang, rowKey, "peternak", "idPeternak", kandang.getPeternak().getIdPeternak());
        client.insertRecord(tableKandang, rowKey, "peternak", "nikPeternak", kandang.getPeternak().getNikPeternak());
        client.insertRecord(tableKandang, rowKey, "peternak", "namaPeternak", kandang.getPeternak().getNamaPeternak());
        client.insertRecord(tableKandang, rowKey, "peternak", "idISIKHNAS", kandang.getPeternak().getIdISIKHNAS());
        client.insertRecord(tableKandang, rowKey, "main", "file_path", kandang.getFile_path());
        client.insertRecord(tableKandang, rowKey, "main", "fotoType", kandang.getFotoType());
        byte[] data = kandang.getData();
        if (data != null) {
            String dataString = Base64.getEncoder().encodeToString(data);
            client.insertRecord(tableKandang, rowKey, "main", "data", dataString);
        }
        client.insertRecord(tableKandang, rowKey, "detail", "created_by", "Polinema");
        return kandang;
    }

    public Kandang findById(String kandangId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableKandang = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("idKandang", "idKandang");
        columnMapping.put("peternak", "peternak");
        columnMapping.put("luas", "luas");
        columnMapping.put("kapasitas", "kapasitas");
        columnMapping.put("nilaiBangunan", "nilaiBangunan");
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

        Kandang kandang = client.showDataTable(tableKandang.toString(), columnMapping, kandangId, Kandang.class);
        String dataString = client.getValue(tableKandang.toString(), kandangId, "main", "data");
        if (dataString != null) {
            kandang.setData(Base64.getDecoder().decode(dataString));
        } else {
            logger.info("Data is null when retrieving");
        }
        return kandang;
    }

    public List<Kandang> findKandangByPeternak(String peternakId, int size) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableKandang = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("idKandang", "idKandang");
        columnMapping.put("peternak", "peternak");
        columnMapping.put("luas", "luas");
        columnMapping.put("kapasitas", "kapasitas");
        columnMapping.put("nilaiBangunan", "nilaiBangunan");
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
        List<Kandang> kandang = client.getDataListByColumn(tableKandang.toString(), columnMapping, "peternak", "id", peternakId, Kandang.class, size);

        return kandang;
    }

    public List<Kandang> findAllById(List<String> kandangIds) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName table = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();
        // Add the mappings to the HashMap
        columnMapping.put("idKandang", "idKandang");
        columnMapping.put("peternak", "peternak");
        columnMapping.put("luas", "luas");
        columnMapping.put("kapasitas", "kapasitas");
        columnMapping.put("nilaiBangunan", "nilaiBangunan");
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
        List<Kandang> kandangs = new ArrayList<>();
        for (String kandangId : kandangIds) {
            Kandang kandang = client.showDataTable(table.toString(), columnMapping, kandangId, Kandang.class);
            if (kandang != null) {
                kandangs.add(kandang);
            }
        }

        return kandangs;
    }

    public Kandang update(String kandangId, Kandang kandang) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableKandang = TableName.valueOf(tableName);
        client.insertRecord(tableKandang, kandangId, "main", "luas", kandang.getLuas());
        client.insertRecord(tableKandang, kandangId, "main", "kapasitas", kandang.getKapasitas());
        client.insertRecord(tableKandang, kandangId, "main", "nilaiBangunan", kandang.getNilaiBangunan());
        client.insertRecord(tableKandang, kandangId, "main", "alamat", kandang.getAlamat());
        client.insertRecord(tableKandang, kandangId, "main", "desa", kandang.getDesa());
        client.insertRecord(tableKandang, kandangId, "main", "kecamatan", kandang.getKecamatan());
        client.insertRecord(tableKandang, kandangId, "main", "kabupaten", kandang.getKabupaten());
        client.insertRecord(tableKandang, kandangId, "main", "provinsi", kandang.getProvinsi());
        client.insertRecord(tableKandang, kandangId, "main", "latitude", kandang.getLatitude());
        client.insertRecord(tableKandang, kandangId, "main", "longitude", kandang.getLongitude());
        client.insertRecord(tableKandang, kandangId, "peternak", "idPeternak", kandang.getPeternak().getIdPeternak());
        client.insertRecord(tableKandang, kandangId, "peternak", "nikPeternak", kandang.getPeternak().getNikPeternak());
        client.insertRecord(tableKandang, kandangId, "peternak", "namaPeternak", kandang.getPeternak().getNamaPeternak());
        client.insertRecord(tableKandang, kandangId, "peternak", "idISIKHNAS", kandang.getPeternak().getIdISIKHNAS());
        client.insertRecord(tableKandang, kandangId, "main", "file_path", kandang.getFile_path());
        client.insertRecord(tableKandang, kandangId, "main", "fotoType", kandang.getFotoType());
        byte[] data = kandang.getData();
        if (data != null) {
            String dataString = Base64.getEncoder().encodeToString(data);
            client.insertRecord(tableKandang, kandangId, "main", "data", dataString);
        }
        return kandang;
    }

    public boolean deleteById(String kandangId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        client.deleteRecord(tableName, kandangId);
        return true;
    }
}
