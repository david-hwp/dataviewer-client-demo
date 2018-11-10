package com.hansight.dataviewer.demo;

import com.hansight.dataviewer.client.CollectorClient;
import com.hansight.dataviewer.common.model.client.CollectorEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DV JAVA客户端数据源的操作示例
 * Created by WeiPing He on 2018/11/10 10:52.
 * Email: weiping_he@hansight.com
 */
public class CollectorDemo {
    public static CollectorClient client = new CollectorClient();
    private static String collectorId = "worker-dev";

    public static void main(String[] args) {
//        set();
        fetchOne();
//        queryByIds();
//        queryByPage();
//        getAll();
    }

    /**
     * 根据id获取数据存储配置
     */
    private static void fetchOne() {
        System.out.println(client.get(collectorId));
    }

    /**
     * 获得所有数据存储配置
     */
    private static void getAll() {
        List<CollectorEntity> configs = client.all();
        configs.forEach(System.out::println);
    }

    /**
     * 根据指定的多个id获得相关的数据存储配置
     */
    private static void queryByIds() {
        List<String> ids = new ArrayList<>();
        ids.add(collectorId);
        List<CollectorEntity> configs = client.getList(ids);
        configs.forEach(System.out::println);
    }

    /**
     * 分页查询数据存储配置
     */
    private static void queryByPage() {
        Map<String, String> search = new HashMap<>();
        search.put("name", "");
        List<CollectorEntity> configs = client.queryByPage(0, 10, "name", "asc", search);
        configs.forEach(System.out::println);
    }
}
