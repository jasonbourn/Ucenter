package cn.ihealthbay.controller;

/**
 * Created by qiang on 2016/07/27.
 */

import cn.ihealthbay.entity.Address;
import cn.ihealthbay.entity.User;
import cn.ihealthbay.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by wisely on 2015/5/25.
 */

@Controller
public class DemoController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    DemoService demoService;

    @RequestMapping("/test")
    @ResponseBody
    public String putCache(){
        demoService.findUser(1l,"wang","yunfei");
        demoService.findAddress(1l,"anhui","hefei");
        System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
        return "ok";
    }
    @RequestMapping("/test2")
    @ResponseBody
    public String testCache(){
        User user = demoService.findUser(1l,"wang","yunfei");
        Address address =demoService.findAddress(1l,"anhui","hefei");
        System.out.println("我这里没执行查询");
        System.out.println("user:"+"/"+user.getFirstName()+"/"+user.getLastName());
        System.out.println("address:"+"/"+address.getProvince()+"/"+address.getCity());
        return "ok";
    }
    @RequestMapping("/test3")
    @ResponseBody
    public List<Map<String, Object>> getDbType(){
        String sql = "select * from message";
        List<Map<String, Object>> list =  jdbcTemplate.queryForList(sql);
        for (Map<String, Object> map : list) {
            Set<Map.Entry<String, Object>> entries = map.entrySet( );
            if(entries != null) {
                Iterator<Map.Entry<String, Object>> iterator = entries.iterator( );
                while(iterator.hasNext( )) {
                    Map.Entry<String, Object> entry =(Map.Entry<String, Object>) iterator.next( );
                    Object key = entry.getKey( );
                    Object value = entry.getValue();
                    System.out.println(key+":"+value);
                }
            }
        }
        return list;
    }
}