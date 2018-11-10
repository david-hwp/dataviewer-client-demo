package com.hansight.dataviewer.demo;

import com.hansight.dataviewer.client.TimerClient;
import com.hansight.dataviewer.common.model.client.TimerEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DV JAVA客户端数据源的操作示例
 * Created by WeiPing He on 2018/11/10 10:52.
 * Email: weiping_he@hansight.com
 */
public class TimerDemo {
    private static TimerClient client = new TimerClient();
    private static String timerId = "timer_for_java_client_demo";
    public static TimerEntity entity = new TimerEntity();

    static {
        Map<String, Object> data = new HashMap<>();
        data.put("name", "range");
        data.put("stopType", "finishReadStop");
        data.put("startTime", "2018-11-10 17:30:00");
        data.put("forceStop", "false");
        data.put("waitBeforeStop", "10");
        entity.setId(timerId);
        entity.setName(timerId);
        entity.setTimerType("range");
        entity.setData(data);
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
        TimerEntity entity = client.get(timerId);
        System.out.println(entity);
    }

    /**
     * 获得所有数据存储配置
     */
    private static void getAll() {
        List<TimerEntity> configs = client.all();
        configs.forEach(System.out::println);
    }

    /**
     * 根据指定的多个id获得相关的数据存储配置
     */
    private static void queryByIds() {
        List<String> ids = new ArrayList<>();
        ids.add(timerId);
        List<TimerEntity> configs = client.getList(ids);
        configs.forEach(System.out::println);
    }

    /**
     * 分页查询数据存储配置
     */
    private static void queryByPage() {
        Map<String, String> search = new HashMap<>();
        search.put("name", "");
        List<TimerEntity> configs = client.queryByPage(0, 10, "name", "asc", search);
        configs.forEach(System.out::println);
    }

}
