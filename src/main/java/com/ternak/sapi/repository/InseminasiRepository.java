package com.ternak.sapi.repository;

import com.ternak.sapi.helper.HBaseCustomClient;
import com.ternak.sapi.model.Inseminasi;
import com.ternak.sapi.model.User;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;

import java.io.IOException;
import java.util.*;

public class InseminasiRepository {
    Configuration conf = HBaseConfiguration.create();
    String tableName = "inseminasis";

    public List<Inseminasi> findAll(int size) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableInseminasi = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("idInseminasi", "idInseminasi");
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

        return client.showListTable(tableInseminasi.toString(), columnMapping, Inseminasi.class, size);
    }

    public Inseminasi save(Inseminasi inseminasi) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        String rowKey = inseminasi.getIdInseminasi();

        TableName tableInseminasi = TableName.valueOf(tableName);
        client.insertRecord(tableInseminasi, rowKey, "main", "idInseminasi", rowKey);
        client.insertRecord(tableInseminasi, rowKey, "main", "tanggalIB", inseminasi.getTanggalIB());
        client.insertRecord(tableInseminasi, rowKey, "main", "lokasi", inseminasi.getLokasi());
        client.insertRecord(tableInseminasi, rowKey, "main", "ib1", inseminasi.getIb1());
        client.insertRecord(tableInseminasi, rowKey, "main", "ib2", inseminasi.getIb2());
        client.insertRecord(tableInseminasi, rowKey, "main", "ib3", inseminasi.getIb3());
        client.insertRecord(tableInseminasi, rowKey, "main", "ibLain", inseminasi.getIbLain());
        client.insertRecord(tableInseminasi, rowKey, "main", "idPejantan", inseminasi.getIdPejantan());
        client.insertRecord(tableInseminasi, rowKey, "main", "idPembuatan", inseminasi.getIdPembuatan());
        client.insertRecord(tableInseminasi, rowKey, "main", "bangsaPejantan", inseminasi.getBangsaPejantan());
        client.insertRecord(tableInseminasi, rowKey, "main", "produsen", inseminasi.getProdusen());
        client.insertRecord(tableInseminasi, rowKey, "peternak", "idPeternak", inseminasi.getPeternak().getIdPeternak());
        client.insertRecord(tableInseminasi, rowKey, "peternak", "nikPeternak", inseminasi.getPeternak().getNikPeternak());
        client.insertRecord(tableInseminasi, rowKey, "peternak", "namaPeternak", inseminasi.getPeternak().getNamaPeternak());
        client.insertRecord(tableInseminasi, rowKey, "petugas", "nikPetugas", inseminasi.getPetugas().getNikPetugas());
        client.insertRecord(tableInseminasi, rowKey, "petugas", "namaPetugas", inseminasi.getPetugas().getNamaPetugas());
        client.insertRecord(tableInseminasi, rowKey, "hewan", "kodeEartagNasional", inseminasi.getHewan().getKodeEartagNasional());
        client.insertRecord(tableInseminasi, rowKey, "hewan", "noKartuTernak", inseminasi.getHewan().getNoKartuTernak());
        client.insertRecord(tableInseminasi, rowKey, "hewan", "alamat", inseminasi.getHewan().getAlamat());
        client.insertRecord(tableInseminasi, rowKey, "detail", "created_by", "Polinema");
        return inseminasi;
    }

    public Inseminasi findInseminasiById(String inseminasiId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableInseminasi = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("idInseminasi", "idInseminasi");
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

        return client.showDataTable(tableInseminasi.toString(), columnMapping, inseminasiId, Inseminasi.class);
    }

    public List<Inseminasi> findInseminasiByPeternak(String peternakId, int size) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName table = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();
        // Add the mappings to the HashMap
        columnMapping.put("idInseminasi", "idInseminasi");
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

        return client.getDataListByColumn(table.toString(), columnMapping, "main", "peternak", peternakId, Inseminasi.class, size);
    }
    
    public List<Inseminasi> findInseminasiByPetugas(String petugasId, int size) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName table = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();
        // Add the mappings to the HashMap
        columnMapping.put("idInseminasi", "idInseminasi");
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

        return client.getDataListByColumn(table.toString(), columnMapping, "main", "petugas", petugasId, Inseminasi.class, size);
    }
    
    public List<Inseminasi> findInseminasiByHewan(String hewanId, int size) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName table = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();
        // Add the mappings to the HashMap
        columnMapping.put("idInseminasi", "idInseminasi");
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

        return client.getDataListByColumn(table.toString(), columnMapping, "main", "hewan", hewanId, Inseminasi.class, size);
    }

    public List<Inseminasi> findAllById(List<String> inseminasiIds) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName table = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();
        // Add the mappings to the HashMap
        columnMapping.put("idInseminasi", "idInseminasi");
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

        List<Inseminasi> inseminasis = new ArrayList<>();
        for (String inseminasiId : inseminasiIds) {
            Inseminasi inseminasi = client.showDataTable(table.toString(), columnMapping, inseminasiId, Inseminasi.class);
            if (inseminasi != null) {
                inseminasis.add(inseminasi);
            }
        }

        return inseminasis;
    }

    public Inseminasi update(String inseminasiId, Inseminasi inseminasi) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableInseminasi = TableName.valueOf(tableName);
        client.insertRecord(tableInseminasi, inseminasiId, "main", "tanggalIB", inseminasi.getTanggalIB());
        client.insertRecord(tableInseminasi, inseminasiId, "main", "lokasi", inseminasi.getLokasi());
        client.insertRecord(tableInseminasi, inseminasiId, "main", "ib1", inseminasi.getIb1());
        client.insertRecord(tableInseminasi, inseminasiId, "main", "ib2", inseminasi.getIb2());
        client.insertRecord(tableInseminasi, inseminasiId, "main", "ib3", inseminasi.getIb3());
        client.insertRecord(tableInseminasi, inseminasiId, "main", "ibLain", inseminasi.getIbLain());
        client.insertRecord(tableInseminasi, inseminasiId, "main", "idPejantan", inseminasi.getIdPejantan());
        client.insertRecord(tableInseminasi, inseminasiId, "main", "idPembuatan", inseminasi.getIdPembuatan());
        client.insertRecord(tableInseminasi, inseminasiId, "main", "bangsaPejantan", inseminasi.getBangsaPejantan());
        client.insertRecord(tableInseminasi, inseminasiId, "main", "produsen", inseminasi.getProdusen());
        client.insertRecord(tableInseminasi, inseminasiId, "peternak", "idPeternak", inseminasi.getPeternak().getIdPeternak());
        client.insertRecord(tableInseminasi, inseminasiId, "peternak", "nikPeternak", inseminasi.getPeternak().getNikPeternak());
        client.insertRecord(tableInseminasi, inseminasiId, "peternak", "namaPeternak", inseminasi.getPeternak().getNamaPeternak());
        client.insertRecord(tableInseminasi, inseminasiId, "petugas", "nikPetugas", inseminasi.getPetugas().getNikPetugas());
        client.insertRecord(tableInseminasi, inseminasiId, "petugas", "namaPetugas", inseminasi.getPetugas().getNamaPetugas());
        client.insertRecord(tableInseminasi, inseminasiId, "hewan", "kodeEartagNasional", inseminasi.getHewan().getKodeEartagNasional());
        client.insertRecord(tableInseminasi, inseminasiId, "hewan", "noKartuTernak", inseminasi.getHewan().getNoKartuTernak());
        client.insertRecord(tableInseminasi, inseminasiId, "hewan", "alamat", inseminasi.getHewan().getAlamat());
        return inseminasi;
    }

    public boolean deleteById(String inseminasiId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        client.deleteRecord(tableName, inseminasiId);
        return true;
    }
}
