package com.zhouyp.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class HoldCPUMain{
        public static class HoldCPUTask implements Runnable{
                @Override
                public void run(){
                        while(true){
                                try (FileOutputStream tmp = new FileOutputStream(new File("tmp"))) {
                                        for (int i = 0; i < 10000; i++) {
                                                tmp.write(i);
                                        }
                                        tmp.close();
                                        final FileInputStream tmp1 = new FileInputStream(new File("tmp"));
                                        while (tmp1.read()!=-1);
                                } catch (FileNotFoundException e) {
                                        e.printStackTrace();
                                } catch (IOException e) {
                                        e.printStackTrace();
                                }
                        }
                }
        }
        public static class LazyTask implements Runnable{
                public void run(){
                        try{
                                while(true){
                                        Thread.sleep(1000);
                                }
                        }catch(Exception e){
                        }
                }
        }
        public static void main(String[] args){
                new Thread(new HoldCPUTask()).start();
                new Thread(new LazyTask()).start();
                new Thread(new LazyTask()).start();
                new Thread(new LazyTask()).start();
        }
}
