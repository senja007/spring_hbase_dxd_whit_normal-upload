package com.ternak.sapi.repository;

import com.ternak.sapi.helper.HBaseCustomClient;
import com.ternak.sapi.model.Petugas;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PetugasRepository {
    Configuration conf = HBaseConfiguration.create();
    String tableName = "petugass";

    public List<Petugas> findAll(int size) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tablePetugas = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("nikPetugas", "nikPetugas");
        columnMapping.put("namaPetugas", "namaPetugas");
        columnMapping.put("noTelp", "noTelp");
        columnMapping.put("email", "email");

        return client.showListTable(tablePetugas.toString(), columnMapping, Petugas.class, size);
    }

    public List<Petugas> findAllByUserID(String userID, int size) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tablePetugas = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("nikPetugas", "nikPetugas");
        columnMapping.put("namaPetugas", "namaPetugas");
        columnMapping.put("noTelp", "noTelp");
        columnMapping.put("email", "email");

        return client.getDataListByColumn(tablePetugas.toString(), columnMapping, "user", "id", userID, Petugas.class, size);
    }

    public Petugas save(Petugas petugas) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        String rowKey = petugas.getNikPetugas();

        TableName tablePetugas = TableName.valueOf(tableName);
        client.insertRecord(tablePetugas, rowKey, "main", "nikPetugas", petugas.getNikPetugas());
        client.insertRecord(tablePetugas, rowKey, "main", "namaPetugas", petugas.getNamaPetugas());
        client.insertRecord(tablePetugas, rowKey, "main", "noTelp", petugas.getNoTelp());
        client.insertRecord(tablePetugas, rowKey, "main", "email", petugas.getEmail());
        client.insertRecord(tablePetugas, rowKey, "detail", "created_by", "Polinema");
        return petugas;
    }

    public Petugas findById(String petugasId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tablePetugas = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("nikPetugas", "nikPetugas");
        columnMapping.put("namaPetugas", "namaPetugas");
        columnMapping.put("noTelp", "noTelp");
        columnMapping.put("email", "email");

        return client.showDataTable(tablePetugas.toString(), columnMapping, petugasId, Petugas.class);
    }

    public Petugas update(String petugasId, Petugas petugas) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        TableName tablePetugas = TableName.valueOf(tableName);
        client.insertRecord(tablePetugas, petugasId, "main", "namaPetugas", petugas.getNamaPetugas());
        client.insertRecord(tablePetugas, petugasId, "main", "noTelp", petugas.getNoTelp());
        client.insertRecord(tablePetugas, petugasId, "main", "email", petugas.getEmail());
        client.insertRecord(tablePetugas, petugasId, "detail", "created_by", "Polinema");
        return petugas;
    }

    public boolean existsByUserID(String UID) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tablePetugas = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("id", "id");

        Petugas petugas = client.getDataByColumn(tablePetugas.toString(), columnMapping, "user", "id", UID, Petugas.class);
        if(petugas.getNikPetugas()!= null){
            return true;
        }else{
            return false;
        }
    }
    
//    public boolean existsByUserID(String UUID) throws IOException {
//        HBaseCustomClient client = new HBaseCustomClient(conf);
//
//        TableName tablePetugas = TableName.valueOf(tableName);
//        Map<String, String> columnMapping = new HashMap<>();
//
//        // Add the mappings to the HashMap
//        columnMapping.put("id", "id");
//
//        // Logging for debugging
//        System.out.println("Checking existence for UID: " + UUID);
//
//        Petugas petugas = client.getDataByColumn(tablePetugas.toString(), columnMapping, "user", "id", UUID, Petugas.class);
//
//        if (petugas == null) {
//            System.out.println("No Petugas found for UID: " + UUID);
//            return false;
//        }
//
//        return petugas.getNikPetugas() != null;
//    }

    public boolean deleteById(String petugasId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        client.deleteRecord(tableName, petugasId);
        return true;
    }
}
