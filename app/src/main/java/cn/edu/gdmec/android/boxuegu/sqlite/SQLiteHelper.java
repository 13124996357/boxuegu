package cn.edu.gdmec.android.boxuegu.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by student on 17/12/28.
 */

public class SQLiteHelper extends SQLiteOpenHelper{

    private static final int DB_VERSION = 1;
    public static final String DB_NAME = "bxg.db";
    public static final String U_USERINFO = "userinfo";
    public static final String U_VIDEO_PLAY_LIST = "videoplaylist";

    public SQLiteHelper(Context context) {
        super(context,  DB_NAME, null, DB_VERSION);
    }

    /**
     * 创建数据库
     * @param db
     */

    @Override
    public void onCreate(SQLiteDatabase db) {
        /**
         * 创建个人表
         */
        db.execSQL("CREATE TABLE IF NOT EXISTS  " + U_USERINFO + "( "
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "userName VARCHAR,"//用户名
                + "nickName VARCHAR,"//昵称
                + "sex VARCHAR,"//性别
                + "signature VARCHAR"//签名
                +")" );
        /**
         * 创建视频表
         */

        db.execSQL("CREATE TABLE IF NOT EXISTS  " + U_VIDEO_PLAY_LIST  + "( "
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "userName VARCHAR,"//用户名
                + "chapterId INT,"//章节ID
                + "videoId INT,"//小节ID
                + "videoPath VARCHAR,"//视频地址
                + "title VARCHAR,"//视频章节名称
                + "secondTitle VARCHAR"//视频名称
                +")" );


    }

    /**
     * 数据库升级
     * @param db
     * @param oldVersion
     * @param newVersion
     */

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + U_USERINFO);
        db.execSQL("DROP TABLE IF EXISTS" + U_VIDEO_PLAY_LIST);
        onCreate(db);

    }
}
