package create_structure;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.http.client.fluent.Request;
import org.json.JSONArray;
import org.json.JSONObject;

import com.github.javafaker.Faker;
import com.ternak.sapi.property.FileStorageProperties;
import java.net.URL;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;

public class SeederData implements CommandLineRunner{
    
        
      public static void main(String[] args)  throws Exception {
        
        

        Configuration conf = HBaseConfiguration.create();
        HBaseCustomClient client = new HBaseCustomClient(conf);

        // time now
        ZoneId zoneId = ZoneId.of("Asia/Jakarta");
        ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);
        Instant instant = zonedDateTime.toInstant();

        // define name all table;
        TableName tableBerita = TableName.valueOf("beritas");
        TableName tableHewan = TableName.valueOf("hewans");
        TableName tableInseminasi = TableName.valueOf("inseminasis");
        TableName tableKandang = TableName.valueOf("kandangs");
        TableName tableKelahiran = TableName.valueOf("kelahirans");
        TableName tablePengobatan = TableName.valueOf("pengobatans");
        TableName tablePeternak = TableName.valueOf("peternaks");
        TableName tablePetugas = TableName.valueOf("petugass");
        TableName tablePkb = TableName.valueOf("pkbs");
        TableName tableVaksin = TableName.valueOf("vaksins");
        TableName tableUser = TableName.valueOf("users");

        // ==============================================================================================
        // INSERT DATA
        // ==============================================================================================
        
        // Faker
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Faker faker = new Faker(new Locale("id-ID"));
        Random random = new Random();

        //DATA PETUGAS
        for (int i = 0; i < 50; i++) {

            String rowKey = "PTG" + faker.idNumber().valid();
            String nikPetugas = rowKey;
            String namaPetugas = faker.name().fullName();
            String noTelp = faker.phoneNumber().cellPhone();
            String email = faker.internet().emailAddress();

            client.insertRecord(tablePetugas, rowKey, "main", "nikPetugas", nikPetugas);
            client.insertRecord(tablePetugas, rowKey, "main", "namaPetugas", namaPetugas);
            client.insertRecord(tablePetugas, rowKey, "main", "noTelp", noTelp);
            client.insertRecord(tablePetugas, rowKey, "main", "email", email);
            client.insertRecord(tablePetugas, rowKey, "detail", "created_by", "Polinema");
        }

        
        List<String> nikPetugasList = client.getAllRecords("petugass", "main", "nikPetugas");
        List<String> namaPetugasList = client.getAllRecords("petugass", "main", "namaPetugas");
        List<String> noTelpPetugasList = client.getAllRecords("petugass", "main", "noTelp");
        List<String> emailPetugasList = client.getAllRecords("petugass", "main", "email");

        List<String> lumajangLocations = Arrays.asList(
                "Kecamatan Lumajang", "Kecamatan Tempeh", "Kecamatan Pasirian", "Kecamatan Senduro",
                "Kecamatan Kunir", "Kecamatan Yosowilangun", "Kecamatan Kunir", "Kecamatan Rowokangkung",
                "Kecamatan Ranuyoso", "Kecamatan Gucialit", "Kecamatan Tekung", "Kecamatan Klakah",
                "Kecamatan Kedungjajang", "Kecamatan Sukodono", "Kecamatan Padang", "Kecamatan Pasrujambe"
        );

//========================================================================================================================
        
        //DATA PETERNAK
        for (int i = 0; i < 50; i++) {

            String rowKey = "PTR" + faker.idNumber().valid();
            String idPeternak = rowKey;
            String nikPeternak = faker.number().digits(16);
            String namaPeternak = faker.name().fullName();
            String idISIKHNAS = "ISKN" + faker.number().digits(4);
            String lokasi = getRandomLumajangLocation(lumajangLocations, random);
            String tanggalPendaftaran = sdf.format(faker.date().birthday());

            int randomIndex = random.nextInt(nikPetugasList.size());
            String nikPetugas = nikPetugasList.get(randomIndex);
            String namaPetugas = namaPetugasList.get(randomIndex);
            String noTelp = noTelpPetugasList.get(randomIndex);
            String email = emailPetugasList.get(randomIndex);

            client.insertRecord(tablePeternak, rowKey, "main", "idPeternak", idPeternak);
            client.insertRecord(tablePeternak, rowKey, "main", "nikPeternak", nikPeternak);
            client.insertRecord(tablePeternak, rowKey, "main", "namaPeternak", namaPeternak);
            client.insertRecord(tablePeternak, rowKey, "main", "idISIKHNAS", idISIKHNAS);
            client.insertRecord(tablePeternak, rowKey, "main", "lokasi", lokasi);
            client.insertRecord(tablePeternak, rowKey, "main", "tanggalPendaftaran", tanggalPendaftaran);
            client.insertRecord(tablePeternak, rowKey, "petugas", "nikPetugas", nikPetugas);
            client.insertRecord(tablePeternak, rowKey, "petugas", "namaPetugas", namaPetugas);
            client.insertRecord(tablePeternak, rowKey, "petugas", "noTelp", noTelp);
            client.insertRecord(tablePeternak, rowKey, "petugas", "email", email);
            client.insertRecord(tablePeternak, rowKey, "detail", "created_by", "Polinema");
        }

        List<String> luasKandang = Arrays.asList(
                "5 Meter", "10 Meter", "14 Meter", "15 Meter", "16 Meter", "17 Meter", "18 Meter", "19 Meter",
                "20 Meter", "21 Meter", "22 Meter", "23 Meter", "24 Meter", "25 Meter", "26 Meter", "27 Meter", "28 Meter",
                "29 Meter", "30 Meter", "31 Meter", "32 Meter", "33 Meter", "34 Meter", "35 Meter", "36 Meter", "37 Meter",
                "38 Meter", "39 Meter", "40 Meter", "41 Meter"
        );

        List<String> idPeternakList = client.getAllRecords("peternaks", "main", "idPeternak");
        List<String> nikPeternakList = client.getAllRecords("peternaks", "main", "nikPeternak");
        List<String> namaPeternakList = client.getAllRecords("peternaks", "main", "namaPeternak");
        List<String> idISIKHNASList = client.getAllRecords("peternaks", "main", "idISIKHNAS");

//========================================================================================================================
        
        //DATA KANDANG
        for (int i = 0; i < 0; i++) {

            String rowKey = "KND" + faker.idNumber().valid();
            String idKandang = rowKey;
            String luas = getRandomLuasKandang(luasKandang, random);
            String kapasitas = faker.number().digits(2);
            String nilaiBangunan = faker.currency().name();
            double latitude = getRandomLatitude();
            double longitude = getRandomLongitude();
            String latitudeString = String.format("%.6f", latitude);
            String longitudeString = String.format("%.6f", longitude);

            String url = "https://nominatim.openstreetmap.org/reverse?lat=" + latitudeString + "&lon=" + longitudeString + "&format=json";
            String response = Request.Get(url).execute().returnContent().asString();
            JSONObject json = new JSONObject(response);
            String fullAddress = json.getString("display_name");

            String[] addressComponents = fullAddress.split(",");
            String desa = addressComponents.length > 0 ? addressComponents[0].trim() : "";
            String kecamatan = addressComponents.length > 1 ? addressComponents[1].trim() : "";
            String kabupaten = addressComponents.length > 2 ? addressComponents[2].trim() : "";
            String provinsi = addressComponents.length > 3 ? addressComponents[3].trim() : "";

            int randomIndex = random.nextInt(idPeternakList.size());
            String idPeternak = idPeternakList.get(randomIndex);
            String nikPeternak = nikPeternakList.get(randomIndex);
            String namaPeternak = namaPeternakList.get(randomIndex);
            String idISIKHNAS = idISIKHNASList.get(randomIndex);


            String photoUrl = "https://picsum.photos/200";
            String photoFilePath = Paths.get("/uploads", idKandang + ".jpg").toString();
            downloadFakePhoto(photoUrl, photoFilePath);
            String foto = photoFilePath;

            client.insertRecord(tableKandang, rowKey, "main", "idKandang", idKandang);
            client.insertRecord(tableKandang, rowKey, "main", "luas", luas);
            client.insertRecord(tableKandang, rowKey, "main", "kapasitas", kapasitas);
            client.insertRecord(tableKandang, rowKey, "main", "nilaiBangunan", nilaiBangunan);
            client.insertRecord(tableKandang, rowKey, "main", "alamat", fullAddress);
            client.insertRecord(tableKandang, rowKey, "main", "desa", desa);
            client.insertRecord(tableKandang, rowKey, "main", "kecamatan", kecamatan);
            client.insertRecord(tableKandang, rowKey, "main", "kabupaten", kabupaten);
            client.insertRecord(tableKandang, rowKey, "main", "provinsi", provinsi);
            client.insertRecord(tableKandang, rowKey, "main", "latitude", latitudeString);
            client.insertRecord(tableKandang, rowKey, "main", "longitude", longitudeString);
            client.insertRecord(tableKandang, rowKey, "peternak", "idPeternak", idPeternak);
            client.insertRecord(tableKandang, rowKey, "peternak", "nikPeternak", nikPeternak);
            client.insertRecord(tableKandang, rowKey, "peternak", "namaPeternak", namaPeternak);
            client.insertRecord(tableKandang, rowKey, "peternak", "idISIKHNAS", idISIKHNAS);
            client.insertRecord(tableKandang, rowKey, "main", "file_path", foto);
            client.insertRecord(tableKandang, rowKey, "main", "fotoType", "images");
            client.insertRecord(tableKandang, rowKey, "main", "data", "Polinema");
            client.insertRecord(tableKandang, rowKey, "detail", "created_by", "Polinema");

        }

        List<String> idKandangList = client.getAllRecords("kandangs", "main", "idKandang");
        List<String> alamatKandangList = client.getAllRecords("kandangs", "main", "alamat");

//========================================================================================================================

        //DATA HEWAN
        for (int i = 0; i < 0; i++) {
            String rowKey = "HWN" + faker.idNumber().valid();

            String kodeEartagNasional = rowKey;
            String noKartuTernak = faker.idNumber().valid();
            String provinsi = faker.address().state();
            String kabupaten = faker.address().city();
            String kecamatan = faker.address().cityName();
            String desa = faker.address().streetName();
            String alamat = faker.address().fullAddress();

            int randomIndex = random.nextInt(nikPetugasList.size());
            String nikPetugas = nikPetugasList.get(randomIndex);
            String namaPetugas = namaPetugasList.get(randomIndex);

            int randomIndex1 = random.nextInt(idPeternakList.size());
            String idPeternak = idPeternakList.get(randomIndex1);
            String nikPeternak = nikPeternakList.get(randomIndex1);
            String namaPeternak = namaPeternakList.get(randomIndex1);
            String idISIKHNAS = idISIKHNASList.get(randomIndex1);

            int randomIndex2 = random.nextInt(idKandangList.size());
            String idKandang = idKandangList.get(randomIndex2);
            String alamatKandang = alamatKandangList.get(randomIndex2);

            String spesies = faker.animal().name();
            String sex = faker.demographic().sex();
            String umur = String.valueOf(faker.number().numberBetween(1, 10));
            String identifikasiHewan = faker.lorem().word();
            String tanggalTerdaftar = zonedDateTime.toString();
            String latitude = String.valueOf(faker.address().latitude());
            String longitude = String.valueOf(faker.address().longitude());
          
            String photoUrl = "https://picsum.photos/200";
            String photoFilePath = Paths.get("/uploads", kodeEartagNasional + ".jpg").toString();
            downloadFakePhoto(photoUrl, photoFilePath);
            String foto = photoFilePath;

            client.insertRecord(tableHewan, rowKey, "main", "kodeEartagNasional", kodeEartagNasional);
            client.insertRecord(tableHewan, rowKey, "main", "noKartuTernak", noKartuTernak);
            client.insertRecord(tableHewan, rowKey, "main", "spesies", spesies);
            client.insertRecord(tableHewan, rowKey, "main", "sex", sex);
            client.insertRecord(tableHewan, rowKey, "main", "umur", umur);
            client.insertRecord(tableHewan, rowKey, "main", "identifikasiHewan", identifikasiHewan);
            client.insertRecord(tableHewan, rowKey, "main", "tanggalTerdaftar", tanggalTerdaftar);
            client.insertRecord(tableHewan, rowKey, "main", "alamat", alamat);
            client.insertRecord(tableHewan, rowKey, "main", "desa", desa);
            client.insertRecord(tableHewan, rowKey, "main", "kecamatan", kecamatan);
            client.insertRecord(tableHewan, rowKey, "main", "kabupaten", kabupaten);
            client.insertRecord(tableHewan, rowKey, "main", "provinsi", provinsi);
            client.insertRecord(tableHewan, rowKey, "main", "latitude", latitude);
            client.insertRecord(tableHewan, rowKey, "main", "longitude", longitude);
            client.insertRecord(tableHewan, rowKey, "peternak", "idPeternak", idPeternak);
            client.insertRecord(tableHewan, rowKey, "peternak", "nikPeternak", nikPeternak);
            client.insertRecord(tableHewan, rowKey, "peternak", "namaPeternak", namaPeternak);
            client.insertRecord(tableHewan, rowKey, "peternak", "idISIKHNAS", idISIKHNAS);
            client.insertRecord(tableHewan, rowKey, "petugas", "nikPetugas", nikPetugas);
            client.insertRecord(tableHewan, rowKey, "petugas", "namaPetugas",namaPetugas);
            client.insertRecord(tableHewan, rowKey, "kandang", "idKandang",idKandang);
            client.insertRecord(tableHewan, rowKey, "kandang", "alamat",alamatKandang);
            client.insertRecord(tableHewan, rowKey, "main", "file_path",foto);
            client.insertRecord(tableHewan, rowKey, "main", "fotoType","images");
            client.insertRecord(tableHewan, rowKey, "main", "data", "Polinema");
            client.insertRecord(tableHewan, rowKey, "detail", "created_by", "Polinema");
        }
    }
    
//========================================================================================================================

    private static String getRandomLumajangLocation(List<String> locations, Random random) {
        int index = random.nextInt(locations.size());
        return locations.get(index);
    }

    private static String getRandomLuasKandang(List<String> luas, Random random) {
        int index = random.nextInt(luas.size());
        return luas.get(index);
    }

    // Method untuk mendownload foto dari Lorem Picsum
    private static void downloadFakePhoto(String photoUrl, String destinationFilePath) throws IOException {
        Path destinationPath = Paths.get(destinationFilePath).getParent();
        if (!Files.exists(destinationPath)) {
            Files.createDirectories(destinationPath);
        }
        URL url = new URL(photoUrl);
        Files.copy(url.openStream(), Paths.get(destinationFilePath), StandardCopyOption.REPLACE_EXISTING);
    }

   private static double getRandomLatitude() {
    double minLatitude = -7.978298;
    double maxLatitude = -8.288940;
    return minLatitude + (Math.random() * (maxLatitude - minLatitude));
}


    private static double getRandomLongitude() {
        double minLongitude = 112.921044;
        double maxLongitude = 113.367704;
        return minLongitude + (Math.random() * (maxLongitude - minLongitude));
    }

    @Override
    public void run(String... args) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
   
       
    

}
