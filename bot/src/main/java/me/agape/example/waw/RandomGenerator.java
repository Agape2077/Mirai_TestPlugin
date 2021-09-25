package me.agape.example.waw;

import java.util.Random;

/**
 * @author Agape
 */
public class RandomGenerator {

    /**
     * 不会吧不会吧 不会真有人看不懂吧
     *@param in_num
     * 进来一个不超过2147483647的数字
     *@return
     * 一个随机数或者一个bug
     *
      *
     * */

    public  String ranGen(String in_num){
        try {
            int _in_num = Integer.parseInt(in_num);
            Random random = new Random();
            //转换成String后再输出
            return String.valueOf(random.nextInt(_in_num)+1);
        }catch (Exception e){
            return "你空格加到你🐴身上了啊？";
        }
    }
}
