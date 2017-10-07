package com.whf.messagerelayer.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;

import com.whf.messagerelayer.R;

import static android.graphics.BitmapFactory.decodeResource;

/**
 * Created by Clevo on 2016/6/14.
 */
public class NotificationUtils {

    NotificationManager manager;
    NotificationCompat.Builder builder;

    int notificationId = -1;

    Context context;

    public NotificationUtils(Context context, int notificationId) {
        this.context = context;
        this.notificationId = notificationId;

        manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        builder = new NotificationCompat.Builder(context);
    }

    public void sample(String ticker, String title, String content, PendingIntent intent, boolean sound, boolean vibrate, boolean lights) {
        //状态栏文字
        builder.setTicker(ticker);
        //通知栏标题
        builder.setContentTitle(title);
        //通知栏内容
        builder.setContentText(content);
        //触发的intent
        builder.setContentIntent(intent);
        //这边设置颜色，可以给5.0及以上版本smallIcon设置背景色
        builder.setColor(0x00558961);
        //小图标
        builder.setSmallIcon(R.mipmap.ic_notification);
        //大图标(这边同时设置了小图标跟大图标，在5.0及以上版本通知栏里面的样式会有所不同)
        //builder.setLargeIcon(decodeResource(context.getResources(), R.mipmap.icon));
        //设置该条通知时间
        //builder.setWhen(System.currentTimeMillis());
        //设置为true，点击该条通知会自动删除，false时只能通过滑动来删除
        //builder.setAutoCancel(true);
        //设置优先级，级别高的排在前面
        //builder.setPriority(NotificationCompat.PRIORITY_MAX);
        int defaults = 0;
        if (sound) {
            defaults |= Notification.DEFAULT_SOUND;
        }
        if (vibrate) {
            defaults |= Notification.DEFAULT_VIBRATE;
        }
        if (lights) {
            defaults |= Notification.DEFAULT_LIGHTS;
        }
        //设置声音、闪光、震动
        builder.setDefaults(defaults);
        //设置是否为一个正在进行中的通知，这一类型的通知将无法删除
        builder.setOngoing(true);
    }

    /**
     * 单行文本使用
     *
     * @param ticker
     * @param title
     * @param content
     * @param intent
     */
    public void sendSingleLineNotification(String ticker, String title, String content, PendingIntent intent) {
        sample(ticker, title, content, intent, false, false, false);
        Notification notification = builder.build();
        send(notification);
    }


    private void send(Notification notification) {
        manager.notify(notificationId, notification);
    }
}
