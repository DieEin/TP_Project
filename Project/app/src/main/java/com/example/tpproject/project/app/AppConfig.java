package com.example.tpproject.project.app;

/**
 * Created by Tomi on 7.5.2016 г..
 */
public class AppConfig {
    // Server user login url
    public static String URL_LOGIN = "http://192.168.56.1/android_login_api/login.php";
    // Server user register url
    public static String URL_REGISTER = "http://192.168.56.1/android_login_api/register.php";

    // Server user stats update url
    public static String URL_UPDATE = "http://192.168.56.1/android_login_api/storeData.php";

    public static String URL_GETUSERS = "http://192.168.56.1/android_login_api/get_logged_users.php";

    public static String URL_LOGINBOOL = "http://192.168.56.1/android_login_api/login_user.php";

    public static String URL_LOGOUTBOOL = "http://192.168.56.1/android_login_api/logout_user.php";

    public static String URL_STOREISLOGGED = "http://192.168.56.1/android_login_api/store_isLoggedInf.php";
}
