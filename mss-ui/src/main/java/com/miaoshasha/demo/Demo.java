package com.miaoshasha.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author fengchaojun <br/>
 * -----------------------------
 * Created with IDEA.
 * Date：2018-10-09
 * Time：18:51
 * -----------------------------
 */
public class Demo {
    public volatile static int index = 0;

    public static void testVol() {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            executorService.execute(() -> {
                for (int j = 0; j < 100; j++) {
                    index++;
                }
            });
        }
    }

    public static int getIndex() {
        return index;
    }

    public static void main(String[] args) {
//        Demo.testVol();
////        for (int i = 0; i <100 ; i++) {
////            new Thread(()->{
////                for (int j = 0; j <100 ; j++) {
////                    index++;
////                }
////            }).start();
////        }
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        System.out.println("------------FRIDAY=" +DemoEnum.FRIDAY.getDesc() + "");
    }
}
