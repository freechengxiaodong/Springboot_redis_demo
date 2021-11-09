package springboot.springboot.util.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @program: com.zhangxian.pay.util.redis
 * @author: zwx
 * @create: 2020-12-08 17:33
 **/
// todo @Component  将一个普通的javaBean 实例化到spring容器中
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 设置过期时间
     * @param key redis key值
     * @param time 过期时间
     * @return
     */
    public boolean expire(String key, long time)
    {
        try {
            if ( time > 0 ) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *获取过期时间
     * @param key redis key
     * @return
     */
    public long getExpire(String key)
    {
        try {
            return redisTemplate.getExpire(key, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // ====================================================== get =====================================================//

    /**
     * 根据key获取值
     * @param key
     * @return
     */
    public Object StringGet(String key)
    {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 设置值
     * @param key
     * @param value
     * @return
     */
    public boolean StringSet(String key, Object value)
    {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设置值并设置过期时间  如果过期时间大于0 则设置过期时间
     * @param key
     * @param value
     * @param time
     * @return
     */
    public boolean StringExpireSet(String key, Object value, long time)
    {
        try {
            if ( time > 0 ) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                StringSet(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 通过key删除值
     * @param key
     */
    public void StringDelete(String... key)
    {
        if ( key != null && key.length > 0 ) {
            if ( key.length == 1 ) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete((Collection<String>) CollectionUtils.arrayToList(key));
            }
        }
    }

    // ====================================================== hash ===================================================//

    /**
     * 获取hash值
     * @param key hash大key
     * @param item hash 小key
     * @return
     */
    public Object HashGet(String key, String item)
    {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 设置hash值
     * @param key hash 值的大key
     * @param item hash 值的小key
     * @param value hash 存储的值
     * @return
     */
    public boolean HashSet(String key, String item, Object value)
    {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设置hash值并设置过期时间   如果过期时间大于0则设置过期时间
     * @param key
     * @param item
     * @param value
     * @param time
     * @return
     */
    public boolean HashExpireSet(String key, String item , Object value, long time)
    {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if ( time > 0 ) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除hash 值
     * @param key
     * @param item
     */
    public void HashDelete(String key, Object... item)
    {
        redisTemplate.opsForHash().delete(key, item);
    }

    // ====================================================== set ====================================================//

    /**
     * 设置 set 值
     * @param key
     * @param values
     * @return
     */
    public long SetSet(String key, Object... values)
    {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 设置set值  并且设置过期时间
     * @param key
     * @param time
     * @param values
     * @return
     */
    public long SetExpireSet(String key, long time, Object... values)
    {
        try {
            Long count = redisTemplate.opsForSet().add(key, values);
            if ( time > 0 ) {
                expire(key, time);
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 删除set值
     * @param key
     * @param values
     * @return
     */
    public long SetDelete(String key, Object... values)
    {
        try {
            Long count = redisTemplate.opsForSet().remove(key, values);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 通过key获取set值
     * @param key
     * @return
     */
    public Set<Object> SetGet(String key)
    {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // ====================================================== list ===================================================//

    /**
     * list 通过key获取值  如果  start = 0 end = -1 查询的所有的数据
     * @param key 需要查询的key值
     * @param start 开始数据
     * @param end   结束数据
     * @return
     */
    public List<Object> ListGet(String key, long start, long end)
    {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 设置list值
     * @param key 键值
     * @param value 值
     * @return
     */
    public boolean ListSet(String key, Object value)
    {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * list 设置值 并且设置过期时间
     * @param key 键值
     * @param value 值
     * @param time 过期时间
     * @return
     */
    public boolean ListExpireSet(String key, Object value, long time)
    {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if ( time > 0 ) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除list值
     * @param key 键值
     * @param count 删除的数量
     * @param values 删除的值
     * @return
     */
    public long ListDelete(String key, long count, Object values)
    {
        try {
            Long removeCount = redisTemplate.opsForList().remove(key, count, values);
            return removeCount;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
