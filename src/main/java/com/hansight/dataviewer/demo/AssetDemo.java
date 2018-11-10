package com.hansight.dataviewer.demo;

import com.hansight.dataviewer.client.AssetClient;
import com.hansight.dataviewer.common.model.client.AssetEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DV JAVA客户端数据源的操作示例
 * Created by WeiPing He on 2018/11/10 10:52.
 * Email: weiping_he@hansight.com
 */
public class AssetDemo {
    private static AssetClient client = new AssetClient();
    private static String assetId = "asset_for_java_client_demo";
    public static AssetEntity entity = new AssetEntity();

    static {
        entity.setId(assetId);
        entity.setName(assetId);
        entity.setAssetType(AssetTypeDemo.entity);
        entity.setResponsible("贺伟平");
        entity.setDesci("测试资产");
        entity.setIpAddress("172.20.10.11");
        entity.setMacAddress("ac:de:48:00:11:22");
        entity.setMobile("15570966048");
    }

    public static void main(String[] args) {
        set();
        fetchOne();
//        queryByIds();
//        queryByPage();
//        getAll();
    }

    /**
     * 根据id获取数据存储配置
     */
    public static void set() {
        System.out.println(client.set(entity));
    }

    /**
     * 根据id获取数据存储配置
     */
    private static void fetchOne() {
        AssetEntity entity = client.get(assetId);
        System.out.println(entity);
    }

    /**
     * 获得所有数据存储配置
     */
    private static void getAll() {
        List<AssetEntity> assets = client.all();
        assets.forEach(System.out::println);
    }

    /**
     * 根据指定的多个id获得相关的数据存储配置
     */
    private static void queryByIds() {
        List<String> ids = new ArrayList<>();
        ids.add(assetId);
        List<AssetEntity> assets = client.getList(ids);
        assets.forEach(System.out::println);
    }

    /**
     * 分页查询数据存储配置
     */
    private static void queryByPage() {
        Map<String, String> search = new HashMap<>();
        search.put("name", "");
        List<AssetEntity> configs = client.queryByPage(0, 10, "name", "asc", search);
        configs.forEach(System.out::println);
    }

}
