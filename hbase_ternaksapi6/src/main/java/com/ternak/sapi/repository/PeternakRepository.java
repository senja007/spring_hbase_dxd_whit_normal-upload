package com.ternak.sapi.repository;

import com.ternak.sapi.helper.HBaseCustomClient;
import com.ternak.sapi.model.Peternak;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PeternakRepository {
    Configuration conf = HBaseConfiguration.create();
    String tableName = "peternaks";

    public List<Peternak> findAll(int size) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableUsers = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("idPeternak", "idPeternak");
        columnMapping.put("nikPeternak", "nikPeternak");
        columnMapping.put("namaPeternak", "namaPeternak");
        columnMapping.put("idISIKHNAS", "idISIKHNAS");
        columnMapping.put("lokasi", "lokasi");
        columnMapping.put("petugas", "petugas");
        columnMapping.put("tanggalPendaftaran", "tanggalPendaftaran");

        return client.showListTable(tableUsers.toString(), columnMapping, Peternak.class, size);
    }

    public List<Peternak> findAllByUserID(String userID, int size) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableUsers = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("idPeternak", "idPeternak");
        columnMapping.put("nikPeternak", "nikPeternak");
        columnMapping.put("namaPeternak", "namaPeternak");
        columnMapping.put("idISIKHNAS", "idISIKHNAS");
        columnMapping.put("lokasi", "lokasi");
        columnMapping.put("petugas", "petugas");
        columnMapping.put("tanggalPendaftaran", "tanggalPendaftaran");

        return client.getDataListByColumn(tableUsers.toString(), columnMapping, "user", "id", userID, Peternak.class, size);
    }

    public Peternak save(Peternak peternak) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        String rowKey = peternak.getIdPeternak();

        TableName tablePeternak = TableName.valueOf(tableName);
        client.insertRecord(tablePeternak, rowKey, "main", "idPeternak", peternak.getIdPeternak());
        client.insertRecord(tablePeternak, rowKey, "main", "nikPeternak", peternak.getNikPeternak());
        client.insertRecord(tablePeternak, rowKey, "main", "namaPeternak", peternak.getNamaPeternak());
        client.insertRecord(tablePeternak, rowKey, "main", "idISIKHNAS", peternak.getIdISIKHNAS());
        client.insertRecord(tablePeternak, rowKey, "main", "lokasi", peternak.getLokasi());
        client.insertRecord(tablePeternak, rowKey, "main", "tanggalPendaftaran", peternak.getTanggalPendaftaran());
        client.insertRecord(tablePeternak, rowKey, "petugas", "nikPetugas", peternak.getPetugas().getNikPetugas());
        client.insertRecord(tablePeternak, rowKey, "petugas", "namaPetugas", peternak.getPetugas().getNamaPetugas());
        client.insertRecord(tablePeternak, rowKey, "petugas", "noTelp", peternak.getPetugas().getNoTelp());
        client.insertRecord(tablePeternak, rowKey, "petugas", "email", peternak.getPetugas().getEmail());
        client.insertRecord(tablePeternak, rowKey, "detail", "createdBy", "Polinema");
        return peternak;
    }

    public Peternak findById(String peternakId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableUsers = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("idPeternak", "idPeternak");
        columnMapping.put("nikPeternak", "nikPeternak");
        columnMapping.put("namaPeternak", "namaPeternak");
        columnMapping.put("idISIKHNAS", "idISIKHNAS");
        columnMapping.put("lokasi", "lokasi");
        columnMapping.put("petugas", "petugas");
        columnMapping.put("tanggalPendaftaran", "tanggalPendaftaran");

        return client.showDataTable(tableUsers.toString(), columnMapping, peternakId, Peternak.class);
    }

    public Peternak update(String peternakId, Peternak peternak) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        TableName tablePeternak = TableName.valueOf(tableName);
        client.insertRecord(tablePeternak, peternakId, "main", "nikPeternak", peternak.getNikPeternak());
        client.insertRecord(tablePeternak, peternakId, "main", "namaPeternak", peternak.getNamaPeternak());
        client.insertRecord(tablePeternak, peternakId, "main", "idISIKHNAS", peternak.getIdISIKHNAS());
        client.insertRecord(tablePeternak, peternakId, "main", "lokasi", peternak.getLokasi());
        client.insertRecord(tablePeternak, peternakId, "main", "tanggalPendaftaran", peternak.getTanggalPendaftaran());
        client.insertRecord(tablePeternak, peternakId, "petugas", "nikPetugas", peternak.getPetugas().getNikPetugas());
        client.insertRecord(tablePeternak, peternakId, "petugas", "namaPetugas", peternak.getPetugas().getNamaPetugas());
        client.insertRecord(tablePeternak, peternakId, "petugas", "noTelp", peternak.getPetugas().getNoTelp());
        client.insertRecord(tablePeternak, peternakId, "petugas", "email", peternak.getPetugas().getEmail());
        client.insertRecord(tablePeternak, peternakId, "detail", "created_by", "Polinema");
        return peternak;
    }

//    public boolean existsByUserID(String UID) throws IOException {
//        HBaseCustomClient client = new HBaseCustomClient(conf);
//
//        TableName tableUsers = TableName.valueOf(tableName);
//        Map<String, String> columnMapping = new HashMap<>();
//
//        // Add the mappings to the HashMap
//        columnMapping.put("id", "id");
//
//        Peternak peternak = client.getDataByColumn(tableUsers.toString(), columnMapping, "user", "id", UID, Peternak.class);
//        if(peternak.getIdPeternak()!= null){
//            return true;
//        }else{
//            return false;
//        }
//    }

    public boolean deleteById(String peternakId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        client.deleteRecord(tableName, peternakId);
        return true;
    }
}
