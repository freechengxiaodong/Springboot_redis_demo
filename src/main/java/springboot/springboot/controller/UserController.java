package springboot.springboot.controller;



import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;
import springboot.springboot.bean.User;
import springboot.springboot.service.UserService;
import springboot.springboot.constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.Console;

import java.util.List;
/**
 * @author 我命倾尘
 */
@RestController
@EnableCaching
public class UserController<GoodsSolrEntity> {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "getUser/{id}", method = RequestMethod.GET)
    public String GetUser(@PathVariable int id) {
        return userService.getUserInfo(id).toString();
    }

    @RequestMapping("/user/login")
    public String getAgeOfUser(){
        //从数据库中查询用户信息
        return "asdads";
    }

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 通过 key 获取字符串
     */
    @GetMapping("/redis/get")
    public String visitStringByKey(@RequestParam String key) {
        return (String) redisTemplate.opsForValue().get(Constant.NAMESPACE + ":" + key);
    }

    /**
     * 在 redis 中设置 key/value
     */
    @GetMapping("/redis/set")
    public String visitStringByKey(@RequestParam String key, @RequestParam String value) {
        try {
            redisTemplate.opsForValue().set(Constant.NAMESPACE + ":" + key, value);
        } catch (Exception e) {
            return "error";
        }
        return "success";
    }
}
