package me.agape.example.waw;

import me.agape.example.propties.JDBCProperties;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

public class OtherBonus {

    Statement statement = null;
    ResultSet resultSet = null;
    /*
    发言奖励
    兑换系统（不知道要换啥）
     */
    public String talkingBonus(String QQId){
        String sql = "update xiuwei_list set xiuwei = xiuwei+1 where qq_id = "+QQId;
        Random random = new Random();
        int bonus = random.nextInt(100);

            try{
                statement = JDBCProperties.statement();
                resultSet = statement.executeQuery(sql);
                return "这个B刚刚触发了发言奖励，Ins赏了他1修为";
            }catch (Exception e){
                return String.valueOf(e);
            }

    }
}