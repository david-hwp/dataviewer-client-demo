package com.hansight.dataviewer.demo;

import com.hansight.dataviewer.client.AssetTypeClient;
import com.hansight.dataviewer.common.model.client.AssetTypeEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DV JAVA客户端数据源的操作示例
 * Created by WeiPing He on 2018/11/10 10:52.
 * Email: weiping_he@hansight.com
 */
public class AssetTypeDemo {
    private static AssetTypeClient client = new AssetTypeClient();
    private static String assetTypeId = "assetType_for_java_client_demo";
    public static AssetTypeEntity entity = new AssetTypeEntity();

    static {
        entity.setId(assetTypeId);
        entity.setName(assetTypeId);
        entity.setParent(new AssetTypeEntity("root"));
//        entity.setAssetTypeType("range");
//        entity.setData(data);
    }

    public static void main(String[] args) {
        set();
        fetchOne();
        queryByIds();
        queryByPage();
        getAll();
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
        AssetTypeEntity entity = client.get(assetTypeId);
        System.out.println(entity);
    }

    /**
     * 获得所有数据存储配置
     */
    private static void getAll() {
        List<AssetTypeEntity> configs = client.all();
        configs.forEach(System.out::println);
    }

    /**
     * 根据指定的多个id获得相关的数据存储配置
     */
    private static void queryByIds() {
        List<String> ids = new ArrayList<>();
        ids.add(assetTypeId);
        List<AssetTypeEntity> configs = client.getList(ids);
        configs.forEach(System.out::println);
    }

    /**
     * 分页查询数据存储配置
     */
    private static void queryByPage() {
        Map<String, String> search = new HashMap<>();
        search.put("name", "");
        List<AssetTypeEntity> configs = client.queryByPage(0, 10, "name", "asc", search);
        configs.forEach(System.out::println);
    }

}
