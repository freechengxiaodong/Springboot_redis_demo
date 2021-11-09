package springboot.springboot;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @RequestMapping("/hello")
    public String hello(){
        String SQL = "SELECT count(*) from `t_user`";
        Integer integer = jdbcTemplate.queryForObject(SQL, Integer.class);
        return integer.toString();
    }
    @RequestMapping("/hello2")
    public String hello2(){
        return "hello2方法";
    }
}
