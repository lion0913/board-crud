package com.ll.exam;

public class App {
    public static final String BASE_PACKAGE_PATH = "com.ll.exam";
    public static String DB_HOST = "localhost";
    public static int DB_PORT = 3306;
    public static String DB_ID = "root";
    public static String DB_PASSWORD = "lion1065";
    public static String DB_NAME = "blog";
    public static boolean isProd = false;

    static {
        String profilesActive = System.getenv("SPRING_PROFILES_ACTIVE");

        if (profilesActive != null && profilesActive.equals("production")) {
            isProd = true;
        }

        if (isProd) {
            DB_HOST = "172.17.0.1";
            DB_PORT = 3306;
            DB_ID = "dajunglocal";
            DB_PASSWORD = "1234";
            DB_NAME = "site1";
        }
    }
}
