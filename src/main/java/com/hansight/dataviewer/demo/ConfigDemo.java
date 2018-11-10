package com.hansight.dataviewer.demo;

import com.hansight.dataviewer.client.ConfigClient;
import com.hansight.dataviewer.client.MetricClient;
import com.hansight.dataviewer.common.model.client.*;

import java.util.*;

/**
 * DV JAVA客户端数据源的操作示例
 * Created by WeiPing He on 2018/11/10 10:52.
 * Email: weiping_he@hansight.com
 */
public class ConfigDemo {
    private static ConfigClient client = new ConfigClient();
    private static String configId = "config_for_java_client_demo";
    public static ConfigEntity entity = new ConfigEntity();

    static {
        entity.setId(configId);
        entity.setName(configId);
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> properties = new HashMap<>();
        Map<String, Object> codec = new HashMap<>();
        data.put("name", "local");
        data.put("path", "/Users/david/test/mutidir/20170*/demo.txt");
        data.put("encoding", "UTF-8");
        data.put("contentType", "txt");
        data.put("category", "other");
        data.put("skipLine", 0);
        properties.put("interval", 10);
        properties.put("timeout", 1000);
        properties.put("resume", true);
        data.put("properties", properties);
        codec.put("name", "line");
        data.put("codec", codec);
        entity.setAssetType(AssetTypeDemo.entity);
        entity.setResolver(ResolverDemo.entity);
        entity.setTimer(TimerDemo.entity);
        entity.setWriters(new ArrayList<WriterEntity>() {{
            add(WriterDemo.entity);
        }});
        entity.setCollectors(new ArrayList<CollectorEntity>() {{
            add(CollectorDemo.client.get("worker-dev"));
        }});
        entity.setData(data);
    }



    public static void main(String[] args) {
//        set();
//        fetchOne();
//        queryByIds();
//        queryByPage();
//        getAll();
//        start();
//        getMetric();
        stop();
    }

    public static void set(){
        System.out.println(client.set(entity));
    }

    /**
     * 根据id获取数据源信息
     */
    private static void fetchOne() {
        ConfigEntity entity = client.get(configId);
        println(entity);
    }

    /**
     * 获得所有数据源信息
     */
    private static void getAll() {
        List<ConfigEntity> configs = client.all();
        configs.forEach(ConfigDemo::println);
    }

    /**
     * 根据指定的多个id获得相关的数据源信息
     */
    private static void queryByIds() {
        List<String> ids = new ArrayList<>();
        ids.add(configId);
        List<ConfigEntity> configs = client.getList(ids);
        configs.forEach(ConfigDemo::println);
    }

    /**
     * 分页查询数据源信息
     */
    private static void queryByPage() {
        Map<String, String> search = new HashMap<>();
        search.put("name", "udp");
        List<ConfigEntity> configs = client.queryByPage(0, 10, "name", "asc", search);
        configs.forEach(ConfigDemo::println);
    }

    private static void start() {
        System.out.println(client.startConfig(configId));
    }

    private static void getMetric() {
        List<MetricEntity> entity = new MetricClient().queryMetric("worker-dev", "f10b91d8-45b0-470e-92ed-9a0cdabdbde3", "PROGRESS", new Date(System.currentTimeMillis() - 3000 * 60 * 1000), new Date());
        System.out.println(entity.size());
    }

    private static void stop() {
        System.out.println(client.stopConfig(configId));
    }

    private static void println(ConfigEntity entity) {
        Map<String, Object> data = entity.getData();
        System.out.println("Config name = " + entity.getName());
        System.out.println("Config data = " + data);
        List<WriterEntity> writers = entity.getWriters();
        writers.forEach(writer -> System.out.println(writer.getName()));

        List<CollectorEntity> collectors = entity.getCollectors();
        collectors.forEach(collector -> System.out.println(collector.getId()));

        ResolverEntity resolver = entity.getResolver();
        System.out.println(resolver.getName());

        AssetTypeEntity assetType = entity.getAssetType();
        System.out.println(assetType.getName());

        TimerEntity timer = entity.getTimer();
        if (timer != null) {
            System.out.println(timer.getName());
        }

        AssetEntity asset = entity.getAsset();
        if (asset != null) {
            System.out.println(asset.getName());
        }
    }
}
