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
                xiuxianDatal.setCoin(resultSet.getInt("coins"));
                xiuxianDatal.setPts(resultSet.getInt("pts"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            xiuxianDatal.setQQ_id("????????????????????????");
        }
        if (!xiuxianDatal.getQQ_id().equals(QQId)) {
            return xiuxianDatal.getQQ_id() + "?????????@CQCODE_741398387???????????????";
        } else {
            return "?????????" + xiuxianDatal.getXiuwei() +
                    "\n?????????"+xiuxianDatal.getCoin()+
                    "\n?????????"+xiuxianDatal.getPts()+
                    "\n?????????????????????" + new SimpleDateFormat("yyyy???MM???dd???").format(xiuxianDatal.getLast_date()) +
                    "\n???????????????" + xiuxianDatal.getQiyu_count() +
                    "\n????????????" + xiuxianDatal.getSlingshi() +
                    "\n????????????" + xiuxianDatal.getMlingshi() +
                    "\n????????????" + xiuxianDatal.getHlingshi() +
                    "\n???????????????" + xiuxianDatal.getHlingshi()
                    ;
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
        int xiuxianRanValue = random.nextInt(35)+1;
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
                //??????????????????
                if (dailyBonus <=10) slingshi++;
                if (dailyBonus <=6 ) mlingshi++;
                if (dailyBonus <=3 ) blingshi++;
                if (dailyBonus <=1 ) hlingshi++;
                tiaozhan_count++;
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
        //????????????b
        Random random = new Random();
        int qiyu_count = 0;
        int qiyup = 21; //????????????
        int minusp = 33; //????????????
        int qiyumaxp = 36; //???????????????
        int qiyuPercent = random.nextInt(100); //????????????
        int qiyuMinusPercent = random.nextInt(100); //????????????
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

                    return "???????????????";
                }else{
                    int qiyuaddc = random.nextInt(qiyumaxp);
                    if (qiyuMinusPercent - minusp <0) qiyuaddc = -qiyuaddc;

                    sql = "update xiuwei_list set qiyu_count = qiyu_count - 1 , xiuwei = xiuwei + "+qiyuaddc+" where QQ_id = '"+QQId+"'";
                    statement = JDBCProperties.statement();
                    resultSet = statement.executeQuery(sql);
                    return "?????????????????????"+qiyuaddc+"????????????";
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "???????????????";
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
        return "????????????????????????????????????";
    }

    public String stoneBonus(String QQId) throws SQLException, ClassNotFoundException {
        XiuxianDatal xiuxianDatal = new XiuxianDatal();
        String sql = "";
        Random random = new Random();
        int sStoneDiff = 2;  //?????????diff
        int mStoneDiff = 3; //?????????diff
        int bStoneDiff = 6; //?????????diff
        int hStoneDIff = 11; //?????????diff
        int sStoneMin = 1;   //??????????????????
        int mStoneMin = 3;  //??????????????????
        int bStoneMin = 5;  //??????????????????
        int hStoneMin = 10;  //??????????????????
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
            return "???????????????????????????";
        }
        sql = "update xiuwei_list set mlingshi=0,slingshi=0,blingshi=0,hlingshi=0 , xiuwei = xiuwei + " + xiuweiBonus + " where QQ_id = '" + QQId + "'";
        statement = JDBCProperties.statement();
        resultSet = statement.executeQuery(sql);
        return "?????????????????????" + xiuweiBonus + "????????????";


    }

    public String changeBonus(String qqId, int bonus){
        int xiuwei_before = 0;
        int xiuwei_after = 0;
        //before
        String sql = "select xiuwei from xiuwei_list where qq_id = "+qqId;
        try {
            statement = JDBCProperties.statement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                xiuwei_before = resultSet.getInt("xiuwei");
            }

        }catch (Exception e){
            return "Query 1 error:"+e;
        }

        sql = "update xiuwei_list set xiuwei = xiuwei+"+bonus+" where qq_id = "+qqId;
        System.out.println("sql");
        try {
            statement = JDBCProperties.statement();
            resultSet = statement.executeQuery(sql);
        }catch (Exception e){
            return "Query 2 error:"+e;
        }

        sql = "select xiuwei from xiuwei_list where qq_id = "+qqId;
        try {
            statement = JDBCProperties.statement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xiuwei_after = resultSet.getInt("xiuwei");
            }
        }catch (Exception e){
            return "Query 3 error:"+e;
        }

        return "success! \n" +
                "before:"+xiuwei_before+"\n" +
                "after:"+xiuwei_after;
    }

    public String clearBonus(int type){
        if (type == 1){                  //?????????
            String sql = "update xiuwei_list set qiyu_count = 0";
            try {
                statement = JDBCProperties.statement();
                resultSet = statement.executeQuery(sql);
            }catch (Exception e){
                return String.valueOf(e);
            }
            return "Success";
        }else if (type == 2){            //?????????+??????
            String sql = "update xiuwei_list setqiyu_count = 0,`hlingshi`=0,`blingshi`=0,`mlingshi`=0,`slingshi`=0";
            try {
                statement = JDBCProperties.statement();
                resultSet = statement.executeQuery(sql);
            }catch (Exception e){
                return String.valueOf(e);
            }
            return "Success.";
        }
        return "Syntax Error.";
    }
}
