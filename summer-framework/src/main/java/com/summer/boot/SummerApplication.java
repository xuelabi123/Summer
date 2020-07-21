package com.summer.boot;

import com.summer.bean.ApplicationContext;
import com.summer.servelet.ServeletHandler;

/**
 * @author panyox
 * @date 2020/7/21 6:05 下午
 */
public class SummerApplication {

    public static void run(Class<?> cla, String[] args) {
        try {
            ApplicationContext app = new ApplicationContext();
            ServeletHandler ser = (ServeletHandler) app.getBean("serveletHandler");
            ser.listen();
            ser.addUser();
            System.out.println("SummerApplication run at port: 8080");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


}
