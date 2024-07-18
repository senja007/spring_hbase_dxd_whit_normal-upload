/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package create_structure;
import com.ternak.sapi.config.*;
import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.*;


@org.springframework.context.annotation.Configuration
public class HBaseConfigtesting {

    @Bean
    public Configuration hbaseConfiguration() {
        Configuration config = HBaseConfiguration.create();
        //config.set("hbase.rootdir", "hdfs://h-primary:9000/hbase");
        config.set("hbase.cluster.distributed" , "true");
        //config.set("hbase.zookeeper.property.dataDir" , "hdfs://h-primary:9000/zookeeper");
       // config.set("hbase.zookeeper.quorum", "zookeeper1, zookeeper2, zookeeper3");
         //config.set("hbase.master" , "h-primary:16020" );
        config.set("hbase.rootdir", "hdfs://114.9.13.247:9000/hbase");
        config.set("hbase.zookeeper.property.dataDir" , "hdfs://h-primary:9000/zookeeper");
        config.set("hbase.zookeeper.quorum", "114.9.13.250, 114.9.13.251, 114.9.13.252");
        config.set("hbase.zookeeper.property.clientPort", "2181");
        config.set("hbase.master" , "114.9.13.247:16020" );
        
        return config;
    }

    @Bean
    public Connection hbaseConnection() throws IOException {
        return ConnectionFactory.createConnection(hbaseConfiguration());
    }
}
