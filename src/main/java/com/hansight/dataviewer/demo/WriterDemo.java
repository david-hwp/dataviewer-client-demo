package com.hansight.dataviewer.demo;

import com.hansight.dataviewer.client.WriterClient;
import com.hansight.dataviewer.common.model.client.WriterEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DV JAVA客户端数据源的操作示例
 * Created by WeiPing He on 2018/11/10 10:52.
 * Email: weiping_he@hansight.com
 */
public class WriterDemo {
    private static WriterClient client = new WriterClient();
    private static String writerId = "writer_for_java_client_demo";
    public static WriterEntity entity = new WriterEntity();

    static {
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> contentType = new HashMap<>();
        Map<String, Object> persisRef = new HashMap<>();
        data.put("name", "file");
        data.put("path", "/Users/david/test/dv_result.txt");
        data.put("cache", 1000);
        contentType.put("name", "json");
        contentType.put("encoding", "UTF-8");
        data.put("contentType", contentType);
        persisRef.put("name", "none");
        data.put("persisRef", persisRef);
        entity.setId(writerId);
        entity.setName(writerId);
        entity.setWriterType("local");
        entity.setData(data);
    }

    public static void main(String[] args) {
        set();
//        fetchOne();
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
        System.out.println(client.get(writerId));
    }

    /**
     * 获得所有数据存储配置
     */
    private static void getAll() {
        List<WriterEntity> configs = client.all();
        configs.forEach(System.out::println);
    }

    /**
     * 根据指定的多个id获得相关的数据存储配置
     */
    private static void queryByIds() {
        List<String> ids = new ArrayList<>();
        ids.add(writerId);
        List<WriterEntity> configs = client.getList(ids);
        configs.forEach(System.out::println);
    }

    /**
     * 分页查询数据存储配置
     */
    private static void queryByPage() {
        Map<String, String> search = new HashMap<>();
        search.put("name", "");
        List<WriterEntity> configs = client.queryByPage(0, 10, "name", "asc", search);
        configs.forEach(System.out::println);
    }
}
