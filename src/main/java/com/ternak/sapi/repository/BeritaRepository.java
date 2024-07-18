package com.ternak.sapi.repository;

import com.ternak.sapi.helper.HBaseCustomClient;
import com.ternak.sapi.model.Berita;
import com.ternak.sapi.model.User;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeritaRepository {
    private static final Logger logger = LoggerFactory.getLogger(BeritaRepository.class);
    Configuration conf = HBaseConfiguration.create();
    String tableName = "beritas";

    public List<Berita> findAll(int size) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableBerita = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("idBerita", "idBerita");
        columnMapping.put("judul", "judul");
        columnMapping.put("tglPembuatan", "tglPembuatan");
        columnMapping.put("isiBerita", "isiBerita");
        columnMapping.put("pembuat", "pembuat");
        columnMapping.put("file_path", "file_path");
        columnMapping.put("fotoType", "fotoType");
        columnMapping.put("data", "data");

        List<Berita> beritas = client.showListTable(tableBerita.toString(), columnMapping, Berita.class, size);

        for (Berita berita : beritas) {
            String dataString = client.getValue(tableBerita.toString(), berita.getIdBerita(), "main", "data");
            if (dataString != null) {
                logger.info("Raw data string retrieved for ID " + berita.getIdBerita() + ": " + dataString);
                berita.setData(Base64.getDecoder().decode(dataString));
                logger.info("Successfully retrieved data for Berita ID: " + berita.getIdBerita());
            } else {
                logger.info("Data is null when retrieving for Berita ID: " + berita.getIdBerita());
            }
        }

        return beritas;
    }

    public Berita save(Berita berita) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        String rowKey = berita.getIdBerita();

        TableName tableBerita = TableName.valueOf(tableName);
        client.insertRecord(tableBerita, rowKey, "main", "idBerita", berita.getIdBerita());
        client.insertRecord(tableBerita, rowKey, "main", "judul", berita.getJudul());
        client.insertRecord(tableBerita, rowKey, "main", "tglPembuatan", berita.getTglPembuatan());
        client.insertRecord(tableBerita, rowKey, "main", "isiBerita", berita.getIsiBerita());
        client.insertRecord(tableBerita, rowKey, "main", "pembuat", berita.getPembuat());
        client.insertRecord(tableBerita, rowKey, "main", "file_path", berita.getFile_path());
        client.insertRecord(tableBerita, rowKey, "main", "fotoType", berita.getFotoType());
        byte[] data = berita.getData();
        if (data != null) {
            String dataString = Base64.getEncoder().encodeToString(data);
            client.insertRecord(tableBerita, rowKey, "main", "data", dataString);
        }
        client.insertRecord(tableBerita, rowKey, "detail", "created_by", "Polinema");
        return berita;
    }

    public Berita findById(String beritaId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableBerita = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("idBerita", "idBerita");
        columnMapping.put("judul", "judul");
        columnMapping.put("tglPembuatan", "tglPembuatan");
        columnMapping.put("isiBerita", "isiBerita");
        columnMapping.put("pembuat", "pembuat");
        columnMapping.put("file_path", "file_path");
        columnMapping.put("fotoType", "fotoType");
        columnMapping.put("data", "data");

        Berita berita = client.showDataTable(tableBerita.toString(), columnMapping, beritaId, Berita.class);
        String dataString = client.getValue(tableBerita.toString(), beritaId, "main", "data");
        if (dataString != null) {
            berita.setData(Base64.getDecoder().decode(dataString));
        } else {
            logger.info("Data is null when retrieving");
        }
        return berita;
    }

    public Berita update(String beritaId, Berita berita) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableBerita = TableName.valueOf(tableName);
        client.insertRecord(tableBerita, beritaId, "main", "judul", berita.getJudul());
        client.insertRecord(tableBerita, beritaId, "main", "tglPembuatan", berita.getTglPembuatan());
        client.insertRecord(tableBerita, beritaId, "main", "isiBerita", berita.getIsiBerita());
        client.insertRecord(tableBerita, beritaId, "main", "pembuat", berita.getPembuat());
        client.insertRecord(tableBerita, beritaId, "main", "file_path", berita.getFile_path());
        client.insertRecord(tableBerita, beritaId, "main", "fotoType", berita.getFotoType());
        byte[] data = berita.getData();
        if (data != null) {
            String dataString = Base64.getEncoder().encodeToString(data);
            client.insertRecord(tableBerita, beritaId, "main", "data", dataString);
        }
        return berita;
    }

    public boolean deleteById(String beritaId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        client.deleteRecord(tableName, beritaId);
        return true;
    }
}
