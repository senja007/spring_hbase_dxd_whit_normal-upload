package com.ternak.sapi.repository;

import com.ternak.sapi.helper.HBaseCustomClient;
import com.ternak.sapi.model.Vaksin;
import com.ternak.sapi.model.User;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;

import java.io.IOException;
import java.util.*;

public class VaksinRepository {
    Configuration conf = HBaseConfiguration.create();
    String tableName = "vaksins";

    public List<Vaksin> findAll(int size) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableVaksin = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("idVaksin", "idVaksin");
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

        return client.showListTable(tableVaksin.toString(), columnMapping, Vaksin.class, size);
    }

    public Vaksin save(Vaksin vaksin) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        String rowKey = vaksin.getIdVaksin();

        TableName tableVaksin = TableName.valueOf(tableName);
        client.insertRecord(tableVaksin, rowKey, "main", "idVaksin", vaksin.getIdVaksin());
        client.insertRecord(tableVaksin, rowKey, "main", "tanggalIB", vaksin.getTanggalIB());
        client.insertRecord(tableVaksin, rowKey, "main", "lokasi", vaksin.getLokasi());
        client.insertRecord(tableVaksin, rowKey, "main", "ib1", vaksin.getIb1());
        client.insertRecord(tableVaksin, rowKey, "main", "ib2", vaksin.getIb2());
        client.insertRecord(tableVaksin, rowKey, "main", "ib3", vaksin.getIb3());
        client.insertRecord(tableVaksin, rowKey, "main", "ibLain", vaksin.getIbLain());
        client.insertRecord(tableVaksin, rowKey, "main", "idPejantan", vaksin.getIdPejantan());
        client.insertRecord(tableVaksin, rowKey, "main", "idPembuatan", vaksin.getIdPembuatan());
        client.insertRecord(tableVaksin, rowKey, "main", "bangsaPejantan", vaksin.getBangsaPejantan());
        client.insertRecord(tableVaksin, rowKey, "main", "produsen", vaksin.getProdusen());
        client.insertRecord(tableVaksin, rowKey, "peternak", "idPeternak", vaksin.getPeternak().getIdPeternak());
        client.insertRecord(tableVaksin, rowKey, "peternak", "nikPeternak", vaksin.getPeternak().getNikPeternak());
        client.insertRecord(tableVaksin, rowKey, "peternak", "namaPeternak", vaksin.getPeternak().getNamaPeternak());
        client.insertRecord(tableVaksin, rowKey, "petugas", "nikPetugas", vaksin.getPetugas().getNikPetugas());
        client.insertRecord(tableVaksin, rowKey, "petugas", "namaPetugas", vaksin.getPetugas().getNamaPetugas());
        client.insertRecord(tableVaksin, rowKey, "hewan", "kodeEartagNasional", vaksin.getHewan().getKodeEartagNasional());
        client.insertRecord(tableVaksin, rowKey, "hewan", "noKartuTernak", vaksin.getHewan().getNoKartuTernak());
        client.insertRecord(tableVaksin, rowKey, "hewan", "alamat", vaksin.getHewan().getAlamat());
        client.insertRecord(tableVaksin, rowKey, "detail", "created_by", "Polinema");
        return vaksin;
    }

    public Vaksin findVaksinById(String vaksinId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableVaksin = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("idVaksin", "idVaksin");
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

        return client.showDataTable(tableVaksin.toString(), columnMapping, vaksinId, Vaksin.class);
    }

    public List<Vaksin> findVaksinByPeternak(String peternakId, int size) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName table = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();
        // Add the mappings to the HashMap
        columnMapping.put("idVaksin", "idVaksin");
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

        return client.getDataListByColumn(table.toString(), columnMapping, "main", "peternak", peternakId, Vaksin.class, size);
    }
    
    public List<Vaksin> findVaksinByPetugas(String petugasId, int size) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName table = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();
        // Add the mappings to the HashMap
        columnMapping.put("idVaksin", "idVaksin");
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

        return client.getDataListByColumn(table.toString(), columnMapping, "main", "petugas", petugasId, Vaksin.class, size);
    }
    
    public List<Vaksin> findVaksinByHewan(String hewanId, int size) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName table = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();
        // Add the mappings to the HashMap
        columnMapping.put("idVaksin", "idVaksin");
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

        return client.getDataListByColumn(table.toString(), columnMapping, "main", "hewan", hewanId, Vaksin.class, size);
    }

    public List<Vaksin> findAllById(List<String> vaksinIds) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName table = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();
        // Add the mappings to the HashMap
        columnMapping.put("idVaksin", "idVaksin");
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

        List<Vaksin> vaksins = new ArrayList<>();
        for (String vaksinId : vaksinIds) {
            Vaksin vaksin = client.showDataTable(table.toString(), columnMapping, vaksinId, Vaksin.class);
            if (vaksin != null) {
                vaksins.add(vaksin);
            }
        }

        return vaksins;
    }

    public Vaksin update(String vaksinId, Vaksin vaksin) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableVaksin = TableName.valueOf(tableName);
        client.insertRecord(tableVaksin, vaksinId, "main", "tanggalIB", vaksin.getTanggalIB());
        client.insertRecord(tableVaksin, vaksinId, "main", "lokasi", vaksin.getLokasi());
        client.insertRecord(tableVaksin, vaksinId, "main", "ib1", vaksin.getIb1());
        client.insertRecord(tableVaksin, vaksinId, "main", "ib2", vaksin.getIb2());
        client.insertRecord(tableVaksin, vaksinId, "main", "ib3", vaksin.getIb3());
        client.insertRecord(tableVaksin, vaksinId, "main", "ibLain", vaksin.getIbLain());
        client.insertRecord(tableVaksin, vaksinId, "main", "idPejantan", vaksin.getIdPejantan());
        client.insertRecord(tableVaksin, vaksinId, "main", "idPembuatan", vaksin.getIdPembuatan());
        client.insertRecord(tableVaksin, vaksinId, "main", "bangsaPejantan", vaksin.getBangsaPejantan());
        client.insertRecord(tableVaksin, vaksinId, "main", "produsen", vaksin.getProdusen());
        client.insertRecord(tableVaksin, vaksinId, "peternak", "idPeternak", vaksin.getPeternak().getIdPeternak());
        client.insertRecord(tableVaksin, vaksinId, "peternak", "nikPeternak", vaksin.getPeternak().getNikPeternak());
        client.insertRecord(tableVaksin, vaksinId, "peternak", "namaPeternak", vaksin.getPeternak().getNamaPeternak());
        client.insertRecord(tableVaksin, vaksinId, "petugas", "nikPetugas", vaksin.getPetugas().getNikPetugas());
        client.insertRecord(tableVaksin, vaksinId, "petugas", "namaPetugas", vaksin.getPetugas().getNamaPetugas());
        client.insertRecord(tableVaksin, vaksinId, "hewan", "kodeEartagNasional", vaksin.getHewan().getKodeEartagNasional());
        client.insertRecord(tableVaksin, vaksinId, "hewan", "noKartuTernak", vaksin.getHewan().getNoKartuTernak());
        client.insertRecord(tableVaksin, vaksinId, "hewan", "alamat", vaksin.getHewan().getAlamat());
        return vaksin;
    }

    public boolean deleteById(String vaksinId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        client.deleteRecord(tableName, vaksinId);
        return true;
    }
}
