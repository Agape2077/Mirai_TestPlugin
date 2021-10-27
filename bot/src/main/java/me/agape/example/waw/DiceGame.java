package me.agape.example.waw;

import me.agape.example.propties.JDBCProperties;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

public class DiceGame {
    Random random = new Random();
    int dice1 = random.nextInt(6)+1;
    int dice2 = random.nextInt(6)+1;
    int dice3 = random.nextInt(6)+1;
    Statement statement = null;
    ResultSet resultSet = null;
    String sql = "";
    //压大小
    public String compareSize(String qqid,int userInput,int barg){
        int xiuwei = 0;
        sql = "select xiuwei from xiuwei_list where qq_id = "+qqid;
        try {
            statement = JDBCProperties.statement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                xiuwei = resultSet.getInt("xiuwei");
            }
        }catch (Exception e){
            return "Query 1 error:"+e;
        }
        if (xiuwei<barg)return "修为不足！";
        // userInput:小为1，大为2
        int diceTotal = dice1+dice2+dice3;
        //4-10为小，11-17为大
        int result = 0;
        if (diceTotal<11) result = 1;
        if (diceTotal>10) result = 2;
        if (dice1 == dice2 && dice2 == dice3) result = 0;
        if (result == userInput){
            sql = "update xiuwei_list set xiuwei = xiuwei+"+barg+" where qq_id = "+qqid;
            System.out.println(sql);
            try {
                statement = JDBCProperties.statement();
                resultSet = statement.executeQuery(sql);
            }catch (Exception e) {
                return "Query 2 error:"+e;
            }
            return "骰盅内骰子点数分别为："+dice1+"、"+dice2+"、"+dice3+"，和为"+diceTotal+"，恭喜获得一倍押注奖励！";
        } else if (result == 0){
            sql = "update xiuwei_list set xiuwei = xiuwei-"+barg+" where qq_id = "+qqid;
            System.out.println("sql");
            try {
                statement = JDBCProperties.statement();
                resultSet = statement.executeQuery(sql);
            }catch (Exception e) {
                return "Query 2 error:"+e;
            }
            return "骰盅内骰子点数分别为："+dice1+"、"+dice2+"、"+dice3+"，庄家通杀，您输了";
        }else{
            sql = "update xiuwei_list set xiuwei = xiuwei-"+barg+" where qq_id = "+qqid;
            System.out.println("sql");
            try {
                statement = JDBCProperties.statement();
                resultSet = statement.executeQuery(sql);
            }catch (Exception e) {
                return "Query 2 error:"+e;
            }
            return "骰盅内骰子点数分别为："+dice1+"、"+dice2+"、"+dice3+"，和为"+diceTotal+"，您输了！";
        }
    }
    //压点数，一个1倍，两个2倍，三个3倍
    public String dicePoints(String qqid,int userInput, int barg){
        return "功能开发中。。。";
    }
    //压豹子，24倍
    public String anyLeopard(String qqid,int barg){
        return "功能开发中。。。";
    }//指定豹子，150倍
    public String specLeopard(String qqid,int userInput,int barg){
        return "功能开发中。。。";
    }//压点数
    /*
    4/17 50倍，5/16 18倍
    6/15 14倍，7/14 12倍
    8/13 8倍，9/10/11/12 6倍
    豹子通杀
     */
    public String dicePointSum(String qqid,int userInput,int barg){
        return "功能开发中。。。";
    }


}
