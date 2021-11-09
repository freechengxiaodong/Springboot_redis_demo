package springboot.springboot.bean;


import lombok.Data;

import java.io.Serializable;

@Data
public class Days implements Serializable {
    private String openId;
    private String daysId;
    //每天的标题
    private String title;
    //代办事项的数量
    private int itemNumber;
    //日程
    private String date;
}


