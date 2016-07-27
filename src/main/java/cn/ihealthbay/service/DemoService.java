package cn.ihealthbay.service;

/**
 * Created by qiang on 2016/07/27.
 */
import cn.ihealthbay.entity.Address;
import cn.ihealthbay.entity.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by wisely on 2015/5/25.
 */
@Service
public class DemoService {
    @Cacheable(value = "usercache",keyGenerator = "wiselyKeyGenerator")
    public User findUser(Long id, String firstName, String lastName){
        System.out.println("无缓存的时候调用这里");
        return new User(id,firstName,lastName);
    }
    @Cacheable(value = "addresscache",keyGenerator = "wiselyKeyGenerator")
    public Address findAddress(Long id, String province, String city){
        System.out.println("无缓存的时候调用这里");
        return new Address(id,province,city);
    }
}