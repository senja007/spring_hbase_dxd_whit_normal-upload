package com.ternak.sapi.repository;

import com.ternak.sapi.helper.HBaseCustomClient;
import com.ternak.sapi.model.Pkb;
import com.ternak.sapi.model.User;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;

import java.io.IOException;
import java.util.*;

public class PkbRepository {
    Configuration conf = HBaseConfiguration.create();
    String tableName = "pkbs";

    public List<Pkb> findAll(int size) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tablePkb = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("idKejadian", "idKejadian");
        columnMapping.put("tanggalPkb", "tanggalPkb");
        columnMapping.put("lokasi", "lokasi");
        columnMapping.put("peternak", "peternak");
        columnMapping.put("hewan", "hewan");
        columnMapping.put("petugas", "petugas");
        columnMapping.put("spesies", "spesies");
        columnMapping.put("kategori", "kategori");
        columnMapping.put("jumlah", "jumlah");
        columnMapping.put("umurKebuntingan", "umurKebuntingan");

        return client.showListTable(tablePkb.toString(), columnMapping, Pkb.class, size);
    }

    public Pkb save(Pkb pkb) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        String rowKey = pkb.getIdKejadian();

        TableName tablePkb = TableName.valueOf(tableName);
        client.insertRecord(tablePkb, rowKey, "main", "idKejadian", rowKey);
        client.insertRecord(tablePkb, rowKey, "main", "tanggalPkb", pkb.getTanggalPkb());
        client.insertRecord(tablePkb, rowKey, "main", "lokasi", pkb.getLokasi());
        client.insertRecord(tablePkb, rowKey, "main", "spesies", pkb.getSpesies());
        client.insertRecord(tablePkb, rowKey, "main", "kategori", pkb.getKategori());
        client.insertRecord(tablePkb, rowKey, "main", "jumlah", pkb.getJumlah());
        client.insertRecord(tablePkb, rowKey, "main", "umurKebuntingan", pkb.getUmurKebuntingan());
        client.insertRecord(tablePkb, rowKey, "peternak", "idPeternak", pkb.getPeternak().getIdPeternak());
        client.insertRecord(tablePkb, rowKey, "peternak", "nikPeternak", pkb.getPeternak().getNikPeternak());
        client.insertRecord(tablePkb, rowKey, "peternak", "namaPeternak", pkb.getPeternak().getNamaPeternak());
        client.insertRecord(tablePkb, rowKey, "petugas", "nikPetugas", pkb.getPetugas().getNikPetugas());
        client.insertRecord(tablePkb, rowKey, "petugas", "namaPetugas", pkb.getPetugas().getNamaPetugas());
        client.insertRecord(tablePkb, rowKey, "hewan", "kodeEartagNasional", pkb.getHewan().getKodeEartagNasional());
        client.insertRecord(tablePkb, rowKey, "hewan", "noKartuTernak", pkb.getHewan().getNoKartuTernak());
        client.insertRecord(tablePkb, rowKey, "hewan", "alamat", pkb.getHewan().getAlamat());
        client.insertRecord(tablePkb, rowKey, "detail", "created_by", "Polinema");
        return pkb;
    }

    public Pkb findPkbById(String pkbId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tablePkb = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("idKejadian", "idKejadian");
        columnMapping.put("tanggalPkb", "tanggalPkb");
        columnMapping.put("lokasi", "lokasi");
        columnMapping.put("peternak", "peternak");
        columnMapping.put("hewan", "hewan");
        columnMapping.put("petugas", "petugas");
        columnMapping.put("spesies", "spesies");
        columnMapping.put("kategori", "kategori");
        columnMapping.put("jumlah", "jumlah");
        columnMapping.put("umurKebuntingan", "umurKebuntingan");

        return client.showDataTable(tablePkb.toString(), columnMapping, pkbId, Pkb.class);
    }

    public List<Pkb> findPkbByPeternak(String peternakId, int size) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName table = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();
        // Add the mappings to the HashMap
        columnMapping.put("idKejadian", "idKejadian");
        columnMapping.put("tanggalPkb", "tanggalPkb");
        columnMapping.put("lokasi", "lokasi");
        columnMapping.put("peternak", "peternak");
        columnMapping.put("hewan", "hewan");
        columnMapping.put("petugas", "petugas");
        columnMapping.put("spesies", "spesies");
        columnMapping.put("kategori", "kategori");
        columnMapping.put("jumlah", "jumlah");
        columnMapping.put("umurKebuntingan", "umurKebuntingan");

        return client.getDataListByColumn(table.toString(), columnMapping, "main", "peternak", peternakId, Pkb.class, size);
    }
    
    public List<Pkb> findPkbByPetugas(String petugasId, int size) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName table = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();
        // Add the mappings to the HashMap
        columnMapping.put("idKejadian", "idKejadian");
        columnMapping.put("tanggalPkb", "tanggalPkb");
        columnMapping.put("lokasi", "lokasi");
        columnMapping.put("peternak", "peternak");
        columnMapping.put("hewan", "hewan");
        columnMapping.put("petugas", "petugas");
        columnMapping.put("spesies", "spesies");
        columnMapping.put("kategori", "kategori");
        columnMapping.put("jumlah", "jumlah");
        columnMapping.put("umurKebuntingan", "umurKebuntingan");
        
        return client.getDataListByColumn(table.toString(), columnMapping, "main", "petugas", petugasId, Pkb.class, size);
    }
    
    public List<Pkb> findPkbByHewan(String hewanId, int size) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName table = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();
        // Add the mappings to the HashMap
        columnMapping.put("idKejadian", "idKejadian");
        columnMapping.put("tanggalPkb", "tanggalPkb");
        columnMapping.put("lokasi", "lokasi");
        columnMapping.put("peternak", "peternak");
        columnMapping.put("hewan", "hewan");
        columnMapping.put("petugas", "petugas");
        columnMapping.put("spesies", "spesies");
        columnMapping.put("kategori", "kategori");
        columnMapping.put("jumlah", "jumlah");
        columnMapping.put("umurKebuntingan", "umurKebuntingan");

        return client.getDataListByColumn(table.toString(), columnMapping, "main", "hewan", hewanId, Pkb.class, size);
    }

    public List<Pkb> findAllById(List<String> pkbIds) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName table = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();
        // Add the mappings to the HashMap
        columnMapping.put("idKejadian", "idKejadian");
        columnMapping.put("tanggalPkb", "tanggalPkb");
        columnMapping.put("lokasi", "lokasi");
        columnMapping.put("peternak", "peternak");
        columnMapping.put("hewan", "hewan");
        columnMapping.put("petugas", "petugas");
        columnMapping.put("spesies", "spesies");
        columnMapping.put("kategori", "kategori");
        columnMapping.put("jumlah", "jumlah");
        columnMapping.put("umurKebuntingan", "umurKebuntingan");

        List<Pkb> pkbs = new ArrayList<>();
        for (String pkbId : pkbIds) {
            Pkb pkb = client.showDataTable(table.toString(), columnMapping, pkbId, Pkb.class);
            if (pkb != null) {
                pkbs.add(pkb);
            }
        }

        return pkbs;
    }

    public Pkb update(String pkbId, Pkb pkb) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tablePkb = TableName.valueOf(tableName);
        client.insertRecord(tablePkb, pkbId, "main", "tanggalPkb", pkb.getTanggalPkb());
        client.insertRecord(tablePkb, pkbId, "main", "lokasi", pkb.getLokasi());
        client.insertRecord(tablePkb, pkbId, "main", "spesies", pkb.getSpesies());
        client.insertRecord(tablePkb, pkbId, "main", "kategori", pkb.getKategori());
        client.insertRecord(tablePkb, pkbId, "main", "jumlah", pkb.getJumlah());
        client.insertRecord(tablePkb, pkbId, "main", "umurKebuntingan", pkb.getUmurKebuntingan());
        client.insertRecord(tablePkb, pkbId, "peternak", "idPeternak", pkb.getPeternak().getIdPeternak());
        client.insertRecord(tablePkb, pkbId, "peternak", "nikPeternak", pkb.getPeternak().getNikPeternak());
        client.insertRecord(tablePkb, pkbId, "peternak", "namaPeternak", pkb.getPeternak().getNamaPeternak());
        client.insertRecord(tablePkb, pkbId, "petugas", "nikPetugas", pkb.getPetugas().getNikPetugas());
        client.insertRecord(tablePkb, pkbId, "petugas", "namaPetugas", pkb.getPetugas().getNamaPetugas());
        client.insertRecord(tablePkb, pkbId, "hewan", "kodeEartagNasional", pkb.getHewan().getKodeEartagNasional());
        client.insertRecord(tablePkb, pkbId, "hewan", "noKartuTernak", pkb.getHewan().getNoKartuTernak());
        client.insertRecord(tablePkb, pkbId, "hewan", "alamat", pkb.getHewan().getAlamat());
        client.insertRecord(tablePkb, pkbId, "detail", "created_by", "Polinema");
        return pkb;
    }

    public boolean deleteById(String pkbId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        client.deleteRecord(tableName, pkbId);
        return true;
    }
}
