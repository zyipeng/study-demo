package com.zhouyp.demo.类加载;

public class StaticA {
	static{
		try {
			Thread.sleep(1000);			
		} catch (InterruptedException e) {
		}
		try {
			Class.forName("geym.zbase.ch10.staticdead.StaticB");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("StaticA init OK");
	}
}
