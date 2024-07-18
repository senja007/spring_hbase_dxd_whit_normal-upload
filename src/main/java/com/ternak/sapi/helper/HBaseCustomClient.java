package com.ternak.sapi.helper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.lang.reflect.Field;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HBaseCustomClient {

    private HBaseAdmin admin;
    private Connection connection = null;

    public HBaseCustomClient(Configuration conf) throws IOException {
        connection = ConnectionFactory.createConnection(conf);
        admin = (HBaseAdmin) connection.getAdmin();
    }

    public void createTable(TableName tableName, String[] CFs) {

        try {
            if (admin.tableExists(tableName)) {

                System.out.println(tableName + "Already Exists");

            } else {

                HTableDescriptor tableDescriptor = new HTableDescriptor(TableName.valueOf(tableName.toString()));

                for (String CFName : CFs) {
                    tableDescriptor.addFamily(new HColumnDescriptor(CFName));
                }

                admin.createTable(tableDescriptor);

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
    public String getValue(String tableName, String rowKey, String family, String qualifier) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Get get = new Get(Bytes.toBytes(rowKey));
        get.addColumn(Bytes.toBytes(family), Bytes.toBytes(qualifier));
        Result result = table.get(get);
        byte[] value = result.getValue(Bytes.toBytes(family), Bytes.toBytes(qualifier));
        table.close();
        return value != null ? Bytes.toString(value) : null;
    }

    public void deleteTable(TableName tableName) {

        try {
            if (admin.tableExists(tableName)) {

                admin.disableTable(tableName);
                admin.deleteTable(tableName);
            } else {
                System.out.println(tableName + " Doesn't exist");
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void insertRecord(TableName tableName, String rowKey, String family, String qualifier, String value) {

        try {
            Table table = connection.getTable(tableName);
            Put p = new Put(Bytes.toBytes(rowKey));
            p.addColumn(Bytes.toBytes(family), Bytes.toBytes(qualifier), Bytes.toBytes(value));
            table.put(p);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void insertListRecord(TableName tableName, String rowKey, String family, String qualifier, List<String> values) {
        try {
            Table table = connection.getTable(tableName);
            Put p = new Put(Bytes.toBytes(rowKey));
            for (String value : values) {
                p.addColumn(Bytes.toBytes(family), Bytes.toBytes(qualifier), Bytes.toBytes(value));
            }
            table.put(p);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void deleteRecord(String tableName, String rowKey) {

        try {
            Table table = connection.getTable(TableName.valueOf(tableName));
            Delete d = new Delete(Bytes.toBytes(rowKey));
            table.delete(d);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public Table getTable(String tableName) throws IOException {
        return connection.getTable(TableName.valueOf(tableName));
    }

    public <T> List<T> showListTable(String tablename, Map<String, String> columnMapping, Class<T> modelClass, int sizeLimit) {
        ResultScanner rsObj = null;

        try {
            Table table = connection.getTable(TableName.valueOf(tablename));

            Scan s = new Scan();
            s.setCaching(100);
            if(sizeLimit > 0){
                s.setLimit(sizeLimit);
            }

            TableDescriptor tableDescriptor = connection.getAdmin().getDescriptor(TableName.valueOf(tablename));
            ColumnFamilyDescriptor[] columnFamilies = tableDescriptor.getColumnFamilies();
            for (ColumnFamilyDescriptor columnFamily : columnFamilies) {
                byte[] family = columnFamily.getName();
                s.addFamily(family);
            }

            rsObj = table.getScanner(s);

            // Create a list to store the objects
            List<T> objects = new ArrayList<T>();

            for (Result result : rsObj) {
                // Do something with the result, e.g. print it to the console
                T object = modelClass.newInstance();
                for (Cell cell : result.listCells()) {
                    // Get the column name
                    String familyName = Bytes.toString(CellUtil.cloneFamily(cell));
                    String columnName = Bytes.toString(CellUtil.cloneQualifier(cell));

                    // Get the variable name from the columnMapping
                    String variableName = columnMapping.get(columnName);

                    String value = Bytes.toString(CellUtil.cloneValue(cell));
                        // Get the value of the cell as a string
                        // Check if the variableName contains "department"
                        if (columnMapping.containsKey(familyName)) {
                            // Get the subfield name
                            String subFieldName = columnName.substring(columnName.indexOf(".") + 1);
                            // Get the department object from the main object
                            Field familyField = object.getClass().getDeclaredField(familyName);
                            familyField.setAccessible(true);
                            Object familyObject = familyField.get(object);
                            if (familyObject == null) {
                                if (familyField.getType() == List.class) {
                                    familyObject = new ArrayList<>();
                                    familyField.set(object, familyObject);
                                } else {
                                    familyObject = familyField.getType().newInstance();
                                    familyField.set(object, familyObject);
                                }
                            }
                            // Set the value to the subfield
                            if (familyObject instanceof List) {
                                Object currentObject = familyObject;
                                ObjectMapper mapper = new ObjectMapper();

                                JsonNode jsonNode = null;
                                try {
                                    jsonNode = mapper.readTree((String) value);
                                } catch (Exception e) {
                                    // Tidak berformat JSON, lakukan konversi biasa
                                }

                                if (jsonNode != null && jsonNode.getNodeType() == JsonNodeFactory.instance.objectNode().getNodeType()) {
                                    // Value berformat JSON, lakukan konversi ke Map
                                    Map<String, Object> dataList = mapper.readValue((String) value, new TypeReference<Map<String, Object>>(){});
                                    ((List) currentObject).add(dataList);
                                } else {
                                    // Value tidak berformat JSON, lakukan konversi biasa
                                    ((List) currentObject).add(value);
                                }
                            } else {
                                Field subField = familyObject.getClass().getDeclaredField(subFieldName);
                                subField.setAccessible(true);
                                setField(subField, familyObject, value);
                            }
                        } else {
                            if (variableName != null) {
                                // Set the value to the variable
                                Field field = object.getClass().getDeclaredField(variableName);
                                field.setAccessible(true);
                                setField(field, object, value);
                            }
                        }
                }
                objects.add(object);
            }

            // Close the scanner and table objects
            rsObj.close();
            return objects;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            if (rsObj != null) {
       
    }
    e.printStackTrace();
        } catch (InstantiationException | IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public <T> T showDataTable(String tablename, Map<String, String> columnMapping, String uuid, Class<T> modelClass) {
        Result result = null;

        try {
            Table table = connection.getTable(TableName.valueOf(tablename));
            Get get = new Get(Bytes.toBytes(uuid));
            TableDescriptor tableDescriptor = connection.getAdmin().getDescriptor(TableName.valueOf(tablename));
            ColumnFamilyDescriptor[] columnFamilies = tableDescriptor.getColumnFamilies();
            for (ColumnFamilyDescriptor columnFamily : columnFamilies) {
                byte[] family = columnFamily.getName();
                get.addFamily(family);
            }

            result = table.get(get);

            // Create a list to store the objects
            T object = modelClass.newInstance();

            // Do something with the result, e.g. print it to the console
            for (Cell cell : result.rawCells()) {
                // Get the column name
                String familyName = Bytes.toString(CellUtil.cloneFamily(cell));
                String columnName = Bytes.toString(CellUtil.cloneQualifier(cell));

                // Get the variable name from the columnMapping
                String variableName = columnMapping.get(columnName);

                String value = Bytes.toString(CellUtil.cloneValue(cell));
                // Get the value of the cell as a string
                // Check if the variableName contains "department"
                if (columnMapping.containsKey(familyName)) {
                    // Get the subfield name
                    String subFieldName = columnName.substring(columnName.indexOf(".") + 1);
                    // Get the department object from the main object
                    Field familyField = object.getClass().getDeclaredField(familyName);
                    familyField.setAccessible(true);
                    Object familyObject = familyField.get(object);
                    if (familyObject == null) {
                        if (familyField.getType() == List.class) {
                            familyObject = new ArrayList<>();
                            familyField.set(object, familyObject);
                        } else {
                            familyObject = familyField.getType().newInstance();
                            familyField.set(object, familyObject);
                        }
                    }
                    // Set the value to the subfield
                    if (familyObject instanceof List) {
                        Object currentObject = familyObject;
                        ObjectMapper mapper = new ObjectMapper();

                        JsonNode jsonNode = null;
                        try {
                            jsonNode = mapper.readTree((String) value);
                        } catch (Exception e) {
                            // Tidak berformat JSON, lakukan konversi biasa
                        }

                        if (jsonNode != null && jsonNode.getNodeType() == JsonNodeFactory.instance.objectNode().getNodeType()) {
                            // Value berformat JSON, lakukan konversi ke Map
                            Map<String, Object> dataList = mapper.readValue((String) value, new TypeReference<Map<String, Object>>(){});
                            ((List) currentObject).add(dataList);
                        } else {
                            // Value tidak berformat JSON, lakukan konversi biasa
                            ((List) currentObject).add(value);
                        }
                    } else {
                        Field subField = familyObject.getClass().getDeclaredField(subFieldName);
                        subField.setAccessible(true);
                        setField(subField, familyObject, value);
                    }
                } else {
                    if (variableName != null) {
                        // Set the value to the variable
                        Field field = object.getClass().getDeclaredField(variableName);
                        field.setAccessible(true);
                        setField(field, object, value);
                    }
                }
            }

            // Close the scanner and table objects
            table.close();
            return object;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException | IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public <T> T getDataByColumn(String tableName, Map<String, String> columnMapping, String familyName, String columnName, String columnValue, Class<T> modelClass) {
        try {
            // Create HBase table object
            Table table = connection.getTable(TableName.valueOf(tableName));

            // Create Scan object to scan table
            Scan scan = new Scan();
            scan.setCaching(100);
            scan.setLimit(1000);

            // Add filter to scan by column value
            Filter filter = new SingleColumnValueFilter(Bytes.toBytes(familyName), Bytes.toBytes(columnName), CompareOperator.EQUAL, Bytes.toBytes(columnValue));
            scan.setFilter(filter);

            // Create a list to store the objects
            T object = modelClass.newInstance();

            // Scan the table and get the data
            ResultScanner scanner = table.getScanner(scan);
            for (Result result : scanner) {
                // Do something with the result, e.g. print it to the console
                for (Cell cell : result.rawCells()) {
                    String familyName2 = Bytes.toString(CellUtil.cloneFamily(cell));
                    String columnName2 = Bytes.toString(CellUtil.cloneQualifier(cell));
                    // Get the variable name from the columnMapping
                    String variableName = columnMapping.get(columnName2);

                    if (variableName != null) {
                        // Get the value of the cell as a string
                        String value = Bytes.toString(CellUtil.cloneValue(cell));
                        // Check if the variableName contains "department"
                        if (columnMapping.containsKey(familyName2)) {
                            // Get the subfield name
                            String subFieldName = variableName.substring(variableName.indexOf(".") + 1);
                            // Get the department object from the main object
                            Field familyField = object.getClass().getDeclaredField(familyName2);
                            familyField.setAccessible(true);
                            Object familyObject = familyField.get(object);
                            if (familyObject == null) {
                                familyObject = familyField.getType().newInstance();
                                familyField.set(object, familyObject);
                            }
                            // Set the value to the subfield
                            Field subField = familyObject.getClass().getDeclaredField(subFieldName);
                            subField.setAccessible(true);
                            setField(subField, familyObject, value);
                        } else {
                            // Set the value to the variable
                            Field field = object.getClass().getDeclaredField(variableName);
                            field.setAccessible(true);
                            setField(field, object, value);
                        }
                    }
                }
            }

            // Close the scanner and table objects
            table.close();
            return object;
        } catch (IOException | InstantiationException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }

        return null;
    }

    public <T> List<T> getDataListByColumn(String tableName, Map<String, String> columnMapping, String familyName, String columnName, String columnValue, Class<T> modelClass, int sizeLimit) {
        ResultScanner rsObj = null;

        try {
            Table table = connection.getTable(TableName.valueOf(tableName));

            Scan s = new Scan();
            s.setCaching(100);
            s.setLimit(sizeLimit);
            TableDescriptor tableDescriptor = connection.getAdmin().getDescriptor(TableName.valueOf(tableName));
            ColumnFamilyDescriptor[] columnFamilies = tableDescriptor.getColumnFamilies();
            for (ColumnFamilyDescriptor columnFamily : columnFamilies) {
                byte[] family = columnFamily.getName();
                s.addFamily(family);
            }

            Filter filter = new SingleColumnValueFilter(Bytes.toBytes(familyName), Bytes.toBytes(columnName), CompareOperator.EQUAL, Bytes.toBytes(columnValue));
            s.setFilter(filter);

            rsObj = table.getScanner(s);

            // Create a list to store the objects
            List<T> objects = new ArrayList<T>();

            for (Result result : rsObj) {
                // Do something with the result, e.g. print it to the console
                T object = modelClass.newInstance();
                for (Cell cell : result.listCells()) {
                    // Get the column name
                    String familyName2 = Bytes.toString(CellUtil.cloneFamily(cell));
                    String columnName2 = Bytes.toString(CellUtil.cloneQualifier(cell));

                    // Get the variable name from the columnMapping
                    String variableName = columnMapping.get(columnName2);

                    String value = Bytes.toString(CellUtil.cloneValue(cell));
                    // Get the value of the cell as a string
                    // Check if the variableName contains "department"
                    if (columnMapping.containsKey(familyName2)) {
                        // Get the subfield name
                        String subFieldName = columnName2.substring(columnName2.indexOf(".") + 1);
                        // Get the department object from the main object
                        Field familyField = object.getClass().getDeclaredField(familyName2);
                        familyField.setAccessible(true);
                        Object familyObject = familyField.get(object);
                        if (familyObject == null) {
                            if (familyField.getType() == List.class) {
                                familyObject = new ArrayList<>();
                                familyField.set(object, familyObject);
                            } else {
                                familyObject = familyField.getType().newInstance();
                                familyField.set(object, familyObject);
                            }
                        }
                        // Set the value to the subfield
                        if (familyObject instanceof List) {
                            Object currentObject = familyObject;
                            ObjectMapper mapper = new ObjectMapper();

                            JsonNode jsonNode = null;
                            try {
                                jsonNode = mapper.readTree((String) value);
                            } catch (Exception e) {
                                // Tidak berformat JSON, lakukan konversi biasa
                            }

                            if (jsonNode != null && jsonNode.getNodeType() == JsonNodeFactory.instance.objectNode().getNodeType()) {
                                // Value berformat JSON, lakukan konversi ke Map
                                Map<String, Object> dataList = mapper.readValue((String) value, new TypeReference<Map<String, Object>>(){});
                                ((List) currentObject).add(dataList);
                            } else {
                                // Value tidak berformat JSON, lakukan konversi biasa
                                ((List) currentObject).add(value);
                            }
                        } else {
                            Field subField = familyObject.getClass().getDeclaredField(subFieldName);
                            subField.setAccessible(true);
                            setField(subField, familyObject, value);
                        }
                    } else {
                        if (variableName != null) {
                            // Set the value to the variable
                            Field field = object.getClass().getDeclaredField(variableName);
                            field.setAccessible(true);
                            setField(field, object, value);
                        }
                    }
                }
                objects.add(object);
            }

            // Close the scanner and table objects
            rsObj.close();
            return objects;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            rsObj.close();
            e.printStackTrace();
        } catch (InstantiationException | IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    private void setField(Field field, Object object, String value) throws IllegalAccessException {
        // Ubah hak akses field agar dapat diakses
        field.setAccessible(true);

        // Dapatkan tipe data dari field
        Class<?> fieldType = field.getType();

        // Parsing nilai string menjadi nilai sesuai tipe data
        if (fieldType == Integer.class) {
            Integer intValue = Integer.parseInt(value);
            field.set(object, intValue);
        } else if (fieldType == long.class) {
            long longValue = Long.parseLong(value);
            field.setLong(object, longValue);
        } else if (fieldType == float.class) {
            float floatValue = Float.parseFloat(value);
            field.setFloat(object, floatValue);
        } else if (fieldType == Float.class) {
            Float floatValue = Float.parseFloat(value);
            field.set(object, floatValue);
        } else if (fieldType == double.class) {
            double doubleValue = Double.parseDouble(value);
            field.setDouble(object, doubleValue);
        } else if (fieldType == Double.class) {
            Double doubleValue = Double.valueOf(value);
            field.setDouble(object, doubleValue);
        } else if (fieldType == boolean.class) {
            boolean booleanValue = Boolean.parseBoolean(value);
            field.setBoolean(object, booleanValue);
        } else if (fieldType == Boolean.class) {
            Boolean booleanValue;
            if(value.equalsIgnoreCase("true")){
                booleanValue = Boolean.TRUE;
            }else{
                booleanValue = Boolean.FALSE;
            }
            field.set(object, booleanValue);
        } else if (fieldType == String.class) {
            field.set(object, value);
        } else if (fieldType == Instant.class) {
            Instant instantValue = Instant.parse(value);
            field.set(object, instantValue);
        
        } else {
            // Tipe data yang tidak dikenal, lewati saja
            System.out.println("Tipe data " + fieldType + " tidak dikenali.");
        }
    }
}