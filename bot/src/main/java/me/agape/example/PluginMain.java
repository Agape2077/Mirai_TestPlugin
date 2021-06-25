package me.agape.example;

import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.Listener;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.MessageSource;
import net.mamoe.mirai.message.data.PlainText;

import java.util.Random;

public final class PluginMain extends JavaPlugin {
    public static final PluginMain INSTANCE = new PluginMain();
    private PluginMain() {
        super(new JvmPluginDescriptionBuilder("me.agape.test", "0.0.1")
                .info("EG")
                .build());
    }

    @Override
    public void onEnable() {
        getLogger().info("plugin Started");
        Listener listener = GlobalEventChannel.INSTANCE.subscribeAlways(MessageEvent.class,g->{
            RunPrefix(g.getMessage().contentToString(),g,g.getSender().getId(),g.getSender().getNick());
        });
    }
    private void RunPrefix(String input, MessageEvent messageEvent,long qqid,String name){
        input = input.toLowerCase();
        input = input.replaceAll("，",",");
        input = input.replaceAll("。",".");
        if(input.equals("helloworld.agape")){
            messageEvent.getSubject().sendMessage(new PlainText("Hello World!! ").plus(new At(qqid)));
        }
        else if(input.equals("修仙")){
            messageEvent.getSubject().sendMessage(new PlainText("很抱歉，"+name+ "，功能未实装"));
        }
        else if(input.equals("奇遇")){
            messageEvent.getSubject().sendMessage(new PlainText("很抱歉，"+name+ "，功能未实装"));
        }
        else if(input.startsWith("挑衅")){
            messageEvent.getSubject().sendMessage(new PlainText("很抱歉，"+name+ "，功能未实装"));
        }
        else if(input.equals("吸收灵石")){
            messageEvent.getSubject().sendMessage(new PlainText("很抱歉，"+name+ "，功能未实装"));
        }
        else if(input.startsWith(".r")){
            input = input.replaceAll(".r ","");
            getLogger().info(input);
            Integer num = Integer.parseInt(input);
            Random random = new Random();
            messageEvent.getSubject().sendMessage(new PlainText("r到的数字是："+ random.nextInt(num)));


        }

    }
}
