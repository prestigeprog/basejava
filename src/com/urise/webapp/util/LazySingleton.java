package com.urise.webapp.util;


public class LazySingleton {
    volatile private static LazySingleton INSTANCE;
    int i;
    double sin = Math.sin(13);

    private LazySingleton() {
    }

    private static class LazySingletonHolder{
        private static LazySingleton INSTANCE= new LazySingleton();
    }

  public static LazySingleton getInstance() {
        return LazySingleton.INSTANCE;
//        if (INSTANCE == null) {
//            synchronized (LazySingleton.class){
//                if (INSTANCE == null){
//                    int i = 13;
//                    INSTANCE = new LazySingleton();
//                }
//            }
//        }
//        return INSTANCE;
  }


}
