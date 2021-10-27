package me.agape.example;

import me.agape.example.waw.OtherBonus;
import me.agape.example.waw.RandomGenerator;
import me.agape.example.waw.SomeWatts;
import me.agape.example.waw.DiceGame;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.Listener;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * @author Agape
 */
public final class PluginMain extends JavaPlugin {
    SomeWatts someWatts = new SomeWatts();
    OtherBonus otherBonus = new OtherBonus();
    RandomGenerator randomGenerator = new RandomGenerator();
    DiceGame dicegame = new DiceGame();
    public static final PluginMain INSTANCE = new PluginMain();
    private PluginMain() {
        super(new JvmPluginDescriptionBuilder("me.agape.test", "0.0.1")
                .info("EG")
                .build());
    }

    @Override
    public void onEnable() {
        //不返回一行log谁知道你启动了没
        getLogger().info("plugin Started");
        Listener listener = GlobalEventChannel.INSTANCE.subscribeAlways(MessageEvent.class,g->{
            try {
                RunPrefix(g.getMessage().contentToString(),g,g.getSender().getId(),g.getSender().getNick(),g.getSubject().getId());
            } catch (ClassNotFoundException | SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }
    private void RunPrefix(String input, MessageEvent messageEvent,long qqid,String name,long groupid) throws SQLException, ClassNotFoundException {
        //大小写转换
        input = input.toLowerCase();
        //中英文标点符号转换
        input = input.replaceAll("，",",");
        input = input.replaceAll("。",".");
        //测试用
//        if(input.equals("helloworld.agape")){
//            messageEvent.getSubject().sendMessage(new PlainText("Hello World!! ").plus(new At(qqid)));
//        }
        //已实装
         if(input.equals("修仙")){
            int value = someWatts.bonusDaily(String.valueOf(qqid));
            if(value>0){
                if(value == 2147483644){
                    value = someWatts.bonusDaily(String.valueOf(qqid));
                }
                someWatts.findone(String.valueOf(qqid));
                messageEvent.getSubject().sendMessage(new At(qqid).plus("恭喜您!今日功德圆满! 共获得"+value+"点修为！\n"+someWatts.findone(String.valueOf(qqid))));
            }else {
                messageEvent.getSubject().sendMessage(new At(qqid).plus(",害修呢？不怕暴毙？\n啥？你没修？去翻翻群公告"));
            }
        }
        //已实装
        else if(input.equals("奇遇")){
            messageEvent.getSubject().sendMessage(new At(qqid).plus(someWatts.bonusExtra(String.valueOf(qqid))));
        }
        //功能，但未实装
        else if(input.startsWith("挑衅")){
             long qid = Long.parseLong(input.replaceAll(" ", "").replaceAll("挑衅@", ""));
                     messageEvent.getSubject().sendMessage(someWatts.fight(String.valueOf(qqid),String.valueOf(qid)));
        }
        //功能，但未实装
        else if(input.equals("吸收灵石")){
            messageEvent.getSubject().sendMessage(someWatts.stoneBonus(String.valueOf(qqid)));
        }
        //已实装
        else if (input.equals("查看")){
            messageEvent.getSubject().sendMessage(new At(qqid).plus(someWatts.findone(String.valueOf(qqid)))).recallIn(100000);
        }
        //已实装
        else if (input.equals("排行")){

            ResultSet resultSet = someWatts.findAll();
            String[] qid =new String[10];
            String[] xiu = new String[10];
            while(resultSet.next()) {
                qid[resultSet.getRow()-1] = resultSet.getString(1);
                xiu[resultSet.getRow()-1] = resultSet.getString(2);
            }
            MessageChain messageChain =new MessageChainBuilder()
                    .append(new At(Long.parseLong(qid[0])).plus("目前修为："+xiu[0]+"\n"))
                    .append(new At(Long.parseLong(qid[1])).plus("目前修为："+xiu[1]+"\n"))
                    .append(new At(Long.parseLong(qid[2])).plus("目前修为："+xiu[2]+"\n"))
                    .append(new At(Long.parseLong(qid[3])).plus("目前修为："+xiu[3]+"\n"))
                    .append(new At(Long.parseLong(qid[4])).plus("目前修为："+xiu[4]+"\n"))
                    .append(new At(Long.parseLong(qid[5])).plus("目前修为："+xiu[5]+"\n"))
                    .append(new At(Long.parseLong(qid[6])).plus("目前修为："+xiu[6]+"\n"))
                    .append(new At(Long.parseLong(qid[7])).plus("目前修为："+xiu[7]+"\n"))
                    .append(new At(Long.parseLong(qid[8])).plus("目前修为："+xiu[8]+"\n"))
                    .append(new At(Long.parseLong(qid[9])).plus("目前修为："+xiu[9]+"\n"))
                    .build();
            messageEvent.getSubject().sendMessage(messageChain).recallIn(100000);

            resultSet.close();

        }
        //bonus-determine
        else if (input.startsWith("/change")){
             System.out.println(qqid);
            if (qqid == 741398387 || qqid == 745502806) {
                String[] changes = input.replaceAll("/change", "").split("\\s+");
                messageEvent.getSubject().sendMessage(someWatts.changeBonus(changes[1], Integer.parseInt(changes[2])));
            }
            else messageEvent.getSubject().sendMessage("Permission Denied");
         }
        //clear
        else if (input.startsWith("/clear")){
             if (qqid == 741398387 || qqid == 745502806) {
            messageEvent.getSubject().sendMessage(
         someWatts.clearBonus(
                 Integer.parseInt(input.replaceAll("/clear",""))
         ));
         }
             else messageEvent.getSubject().sendMessage("Permission Denied");
        }

        //gen个随机数拿来roll
        else if(input.startsWith(".r")){
            input = input.replaceAll(".r ","");

            messageEvent.getSubject().sendMessage(new PlainText("r到的数字是："+randomGenerator.ranGen(input)));
        }
        else if(input.startsWith("骰子")){
            try {
                String[] type = input.replaceAll("骰子 ","").split("\\s+");
                String send = "";
                switch (type[0]){
                    case "大小":
                        if (type[1] == "大"){
                            type[1] = "2";
                        }else if (type[1] == "小"){
                            type[1] = "1";
                        }
                        send = dicegame.compareSize(String.valueOf(qqid),Integer.parseInt(type[1]),Integer.parseInt(type[2]));
                        break;
                    case "面点":
                        send = dicegame.dicePoints(String.valueOf(qqid),Integer.parseInt(type[1]),Integer.parseInt(type[2]));
                        break;
                    case "豹子":
                        if (type[1] == "任意") send = dicegame.anyLeopard(String.valueOf(qqid),Integer.parseInt(type[2]));
                        else send = dicegame.specLeopard(String.valueOf(qqid),Integer.parseInt(type[1]),Integer.parseInt(type[2]));
                        break;
                    case "和点":
                        send = dicegame.dicePointSum(String.valueOf(qqid),Integer.parseInt(type[1]),Integer.parseInt(type[2]));
                        break;
                    default:
                        send = "你骰你妈呢？";
                        break;
                }
                messageEvent.getSubject().sendMessage(send);
            }catch (Exception e){
                e.printStackTrace();
                messageEvent.getSubject().sendMessage((Message)e);
            }

         }

        else if (groupid == 341958124){
            Random random = new Random();
            int bonus1 = random.nextInt(101);
             System.out.println(bonus1);
            if (bonus1==82) messageEvent.getSubject().sendMessage(otherBonus.talkingBonus(String.valueOf(qqid))+"，Bonus数值为"+bonus1);
             int bonus2 = random.nextInt(1001);
             System.out.println(bonus2);
             if (bonus2==777) messageEvent.getSubject().sendMessage(otherBonus.talkingBonusCoin(String.valueOf(qqid))+"，Bonus数值为"+bonus2);
         }


    }
}
