package me.agape.example.waw;

import me.agape.example.bean.XiuxianDatal;
import me.agape.example.propties.JDBCProperties;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SomeWatts {
    Statement statement = null;
    ResultSet resultSet = null;
    List<XiuxianDatal> list = null;

    public ResultSet findAll() {
        XiuxianDatal xiuxianDatal = null;
        try {
            String sql = "select qq_id,xiuwei from xiuwei_list order by xiuwei desc limit 0,10";
            statement = JDBCProperties.statement();
            resultSet = statement.executeQuery(sql);

            statement.close();
            return resultSet;
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public String findone(String QQId) {

        XiuxianDatal xiuxianDatal = new XiuxianDatal();
        String sql = "select * from xiuwei_list where qq_id ='" + QQId + "'";
        try {

            statement = JDBCProperties.statement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xiuxianDatal.setQQ_id(QQId);
                xiuxianDatal.setXiuwei(resultSet.getInt("xiuwei"));
                xiuxianDatal.setLast_date(resultSet.getDate("last_date"));
                xiuxianDatal.setQiyu_count(resultSet.getInt("qiyu_count"));
                xiuxianDatal.setTiaozhan_count(resultSet.getInt("tiaozhan_count"));
                xiuxianDatal.setSlingshi(resultSet.getInt("Slingshi"));
                xiuxianDatal.setMlingshi(resultSet.getInt("Mlingshi"));
                xiuxianDatal.setBlingshi(resultSet.getInt("BLingshi"));
                xiuxianDatal.setHlingshi(resultSet.getInt("Hlingshi"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            xiuxianDatal.setQQ_id("抱歉，查无此人。");
        }
        if (!xiuxianDatal.getQQ_id().equals(QQId)) {
            return xiuxianDatal.getQQ_id() + "请联系@CQCODE_741398387进行添加。";
        } else {
            return "修为：" + xiuxianDatal.getXiuwei() +
                    "\n上次修仙时间：" + new SimpleDateFormat("yyyy年MM月dd日").format(xiuxianDatal.getLast_date()) +
                    "\n寻访次数：" + xiuxianDatal.getQiyu_count() +
                    "\n小灵石：" + xiuxianDatal.getSlingshi() +
                    "\n中灵石：" + xiuxianDatal.getMlingshi() +
                    "\n大灵石：" + xiuxianDatal.getHlingshi() +
                    "\n极品灵石：" + xiuxianDatal.getHlingshi();
        }
    }

    public int bonusDaily(String QQId) throws ClassNotFoundException, SQLException {
        Random random = new Random();
        int xiuwei = 0;
        int qiyu_count = 0;
        int hlingshi = 0;
        int blingshi = 0;
        int mlingshi = 0;
        int slingshi = 0;
        int tiaozhan_count = 0;
        Date last_date = null;
        String sql = "";
        int xiuxianRanValue = random.nextInt(51);
        int dailyBonus = random.nextInt(100);
        try {
            sql = "select * from xiuwei_list where qq_id ='" + QQId + "'";
            statement = JDBCProperties.statement();
            resultSet = statement.executeQuery(sql);
            list = new ArrayList<XiuxianDatal>();
            while (resultSet.next()) {
                xiuwei = resultSet.getInt("xiuwei");
                qiyu_count = resultSet.getInt("qiyu_count");
                hlingshi = resultSet.getInt("hlingshi");
                blingshi = resultSet.getInt("blingshi");
                mlingshi = resultSet.getInt("mlingshi");
                slingshi = resultSet.getInt("slingshi");
                tiaozhan_count = resultSet.getInt("tiaozhan_count");
                last_date = resultSet.getDate("last_date");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();

        }
        try {
            if (!(new Date(System.currentTimeMillis())).toString().equals(last_date.toString())) {

                xiuwei = xiuwei + xiuxianRanValue;
                qiyu_count = qiyu_count + 3;
                //灵石获取概率
                if (dailyBonus <=10) slingshi++;
                if (dailyBonus <=6 ) mlingshi++;
                if (dailyBonus <=3 ) blingshi++;
                if (dailyBonus <=1 ) hlingshi++;

                tiaozhan_count++;
                last_date = new Date(System.currentTimeMillis());
                String sqlUpdate = "UPDATE xiuwei_list SET  xiuwei = " + xiuwei + ", qiyu_count = " + qiyu_count + ", slingshi = " + slingshi + ", mlingshi = " + mlingshi + ", blingshi = " + blingshi + ", hlingshi = " + hlingshi + ",  tiaozhan_count = " + tiaozhan_count + ", last_date = \"" + new Date(System.currentTimeMillis()) + "\"  WHERE `qq_id` = " + QQId;
                try {
                    resultSet = statement.executeQuery(sqlUpdate);
                    return xiuxianRanValue;

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        } catch (NullPointerException e) {
            sql = "INSERT INTO `xiuxian`.`xiuwei_list` (`QQ_id`, `xiuwei`, `qiyu_count`, `hlingshi`, `blingshi`, `mlingshi`, `slingshi`, `tiaozhan_count`, `last_date`) VALUES (" + QQId + ", 0, 0, 0, 0, 0, 0, 0, '2020-10-19')";
            statement = JDBCProperties.statement();
            resultSet = statement.executeQuery(sql);
            return 2147483644;
        }
        return 0;
    }

    public String bonusExtra(String QQId) {
        //定义一群b
        Random random = new Random();
        int qiyu_count = 0;
        int qiyup = 20; //奇遇概率
        int minusp = 33; //负值概率
        int qiyumaxp = 67; //奇遇上下限
        int qiyuPercent = random.nextInt(100); //随机几率
        int qiyuMinusPercent = random.nextInt(100); //随机倒扣
        String sql = "";
        XiuxianDatal xiuxianDatal = new XiuxianDatal();

         sql = "select xiuwei, qiyu_count from xiuwei_list where QQ_id = '" + QQId + "'";
        try {
            statement = JDBCProperties.statement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xiuxianDatal.setQQ_id(QQId);
                xiuxianDatal.setXiuwei(resultSet.getInt("xiuwei"));
                xiuxianDatal.setQiyu_count(resultSet.getInt("qiyu_count"));
                qiyu_count = xiuxianDatal.getQiyu_count();
            }

            if (qiyu_count > 0){

                if (qiyuPercent - qiyup >0){
                    sql = "update xiuwei_list set qiyu_count = qiyu_count-1 where QQ_id = '"+QQId+"'";
                    statement = JDBCProperties.statement();
                    resultSet = statement.executeQuery(sql);

                    return "无事发生。";
                }else{
                    int qiyuaddc = random.nextInt(qiyumaxp);
                    if (qiyuMinusPercent - minusp <0) qiyuaddc = -qiyuaddc;

                    sql = "update xiuwei_list set qiyu_count = qiyu_count - 1 , xiuwei = xiuwei + "+qiyuaddc+" where QQ_id = '"+QQId+"'";
                    statement = JDBCProperties.statement();
                    resultSet = statement.executeQuery(sql);
                    return "奇遇成功！获得"+qiyuaddc+"点修为！";
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "次数不足！";
    }

    public String fight(String QQId, String anoQQId) throws ClassNotFoundException, SQLException {
        XiuxianDatal mainUser = new XiuxianDatal();
        XiuxianDatal anoUser  = new XiuxianDatal();
        String sql = "select * from xiuwei_list where qq_id ='" + QQId + "'";
        Random random = new Random();
        statement = JDBCProperties.statement();
        resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            mainUser.setQQ_id(QQId);
            mainUser.setXiuwei(resultSet.getInt("xiuwei"));
            mainUser.setTiaozhan_count(resultSet.getInt("tiaozhan_count"));
        }
        sql = "select * from xiuwei_list where qq_id ='" +anoQQId + "'";
        statement = JDBCProperties.statement();
        resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            anoUser.setQQ_id(QQId);
            anoUser.setXiuwei(resultSet.getInt("xiuwei"));
        }
        return "挑衅失败，没有人受到伤害";
    }

    public String stoneBonus(String QQId) throws SQLException, ClassNotFoundException {
        XiuxianDatal xiuxianDatal = new XiuxianDatal();
        String sql = "";
        Random random = new Random();
        int sStoneDiff = 2;  //小石头diff
        int mStoneDiff = 3; //中石头diff
        int bStoneDiff = 6; //大石头diff
        int hStoneDIff = 11; //特石头diff
        int sStoneMin = 1;   //小石头最小值
        int mStoneMin = 3;  //中石头最小值
        int bStoneMin = 5;  //大石头最小值
        int hStoneMin = 10;  //特石头最小值
        int sStoneBonus, mStoneBonus, bStoneBonus, hStoneBonus, xiuweiBonus = 0;

        sql = "select xiuwei, hlingshi,blingshi,mlingshi,slingshi from xiuwei_list where QQ_id = '" + QQId + "'";

        statement = JDBCProperties.statement();
        resultSet = statement.executeQuery(sql);
        sStoneBonus = random.nextInt(sStoneDiff) + sStoneMin;
        mStoneBonus = random.nextInt(mStoneDiff) + mStoneMin;
        bStoneBonus = random.nextInt(bStoneDiff) + bStoneMin;
        hStoneBonus = random.nextInt(hStoneDIff) + hStoneMin;
        while (resultSet.next()) {
            xiuxianDatal.setQQ_id(QQId);
            xiuxianDatal.setXiuwei(resultSet.getInt("xiuwei"));
            xiuxianDatal.setHlingshi(resultSet.getInt("hlingshi"));
            xiuxianDatal.setBlingshi(resultSet.getInt("blingshi"));
            xiuxianDatal.setMlingshi(resultSet.getInt("mlingshi"));
            xiuxianDatal.setSlingshi(resultSet.getInt("slingshi"));
        }
        xiuweiBonus = sStoneBonus * xiuxianDatal.getSlingshi() +
                mStoneBonus * xiuxianDatal.getMlingshi() +
                bStoneBonus * xiuxianDatal.getBlingshi() +
                hStoneBonus * xiuxianDatal.getHlingshi();
        if (xiuweiBonus == 0) {
            return "你有吗？就隔着嗯吸";
        }
        sql = "update xiuwei_list set mlingshi=0,slingshi=0,blingshi=0,hlingshi=0 , xiuwei = xiuwei + " + xiuweiBonus + " where QQ_id = '" + QQId + "'";
        statement = JDBCProperties.statement();
        resultSet = statement.executeQuery(sql);
        return "吸收成功！获得" + xiuweiBonus + "点修为！";


    }
}
