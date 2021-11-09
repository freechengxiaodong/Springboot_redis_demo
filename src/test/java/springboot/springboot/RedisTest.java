package springboot.springboot;


import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import springboot.springboot.bean.Days;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate<String,Days> redisTemplate;
    private Days d;
    @Before
    public void before(){
        d=new Days();
        d.setDate("123");
        d.setDaysId("456");
        d.setItemNumber(123);
        d.setOpenId("dawda");
        d.setTitle("title");
    }
    @Test
    public void testSet(){
        this.redisTemplate.opsForValue().set("days",d);
        System.out.println((redisTemplate.opsForValue().get("days")));
    }
}

