package com.hansight.dataviewer.demo;

import com.hansight.dataviewer.client.ResolverClient;
import com.hansight.dataviewer.common.model.client.ResolverEntity;
import com.hansight.dataviewer.common.model.client.ResolverPropertiesEntity;
import com.hansight.dataviewer.core.filter.AddFields;
import com.hansight.dataviewer.core.filter.Rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DV JAVA客户端数据源的操作示例
 * Created by WeiPing He on 2018/11/10 10:52.
 * Email: weiping_he@hansight.com
 */
public class ResolverDemo {
    private static ResolverClient client = new ResolverClient();
    private static String resolverId = "resolver_for_java_client_demo";
    public static ResolverEntity entity = new ResolverEntity();

    static {
        //设置解析规则id
        entity.setId(resolverId);
        //设置解析规则名称
        entity.setName(resolverId);
        //样例日志
        entity.setSample("{\"host\":\"127.0.0.1\",\"host_name\":\"localhost\",\"id\":100000,\"port\":64925.0,\"raw\":\"hkdhfgkdhgjkdfhgkdhgjkdfjkghkd\"}");

        Map<String, Object> parser = new HashMap<>();

        /*构造数据过滤部分开始*/
        Map<String, Object> filter = new HashMap<>();
        List<Rule> rules = new ArrayList<>();

        List<List<String>> addFields = new ArrayList<>();
        addFields.add(new ArrayList<String>() {{
            add("addFiledName1");
            add("value");
            add("addFiledValue1");
        }});
        addFields.add(new ArrayList<String>() {{
            add("addFiledName2");
            add("value");
            add("addFiledValue2");
        }});


        rules.add(new AddFields(addFields));
        filter.put("rules", rules);
        /*构造数据过滤部分结束*/

        //添加解析规则类型到解析规则配置
        parser.put("name", "json");
//        parser.put("metadata", "null");
        //添加数据过滤规则到解析规则配置
        parser.put("filter", filter);
        //设置解析规则配置
        entity.setParser(parser);

        List<ResolverPropertiesEntity> properties = new ArrayList<>();
        properties.add(new ResolverPropertiesEntity("", "", "string", "host", "", "127.0.0.1"));
        properties.add(new ResolverPropertiesEntity("", "", "string", "host_name", "", "localhost"));
        properties.add(new ResolverPropertiesEntity("", "", "string", "id", "", "1000000"));
        properties.add(new ResolverPropertiesEntity("", "", "double", "port", "", "64925.0"));
        properties.add(new ResolverPropertiesEntity("", "", "string", "raw", "", "hkdhfgkdhgjkdfhgkdhgjkdfjkghkd"));
        properties.add(new ResolverPropertiesEntity("", "", "string", "addFiledName1", "", "addFiledValue1"));
        properties.add(new ResolverPropertiesEntity("", "", "string", "addFiledName2", "", "addFiledValue2"));

        entity.setProperties(properties);
        entity.setAssetType(AssetTypeDemo.entity);
//        entity.setResolverType("range");
//        entity.setData(data);
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
        ResolverEntity entity = client.get(resolverId);
        System.out.println(entity);
    }

    /**
     * 获得所有数据存储配置
     */
    private static void getAll() {
        List<ResolverEntity> configs = client.all();
        configs.forEach(System.out::println);
    }

    /**
     * 根据指定的多个id获得相关的数据存储配置
     */
    private static void queryByIds() {
        List<String> ids = new ArrayList<>();
        ids.add(resolverId);
        List<ResolverEntity> configs = client.getList(ids);
        configs.forEach(System.out::println);
    }

    /**
     * 分页查询数据存储配置
     */
    private static void queryByPage() {
        Map<String, String> search = new HashMap<>();
        search.put("name", "");
        List<ResolverEntity> configs = client.queryByPage(0, 10, "name", "asc", search);
        configs.forEach(System.out::println);
    }

}
