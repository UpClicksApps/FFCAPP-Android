package com.upclicks.ffc.fcm;

import com.google.firebase.messaging.FirebaseMessagingService;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
//    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();
//    private NotificationChannel mChannel;
//    private NotificationManager notifManager;
//
//    @Override
//    public void onMessageReceived(RemoteMessage remoteMessage) {
//        // Check if message contains a data payload.
//        if (remoteMessage.getData().size() > 0) {
//            Log.e(TAG, "Data Payload: " + remoteMessage.getData().toString());
//            String title = "0";
//            String body = "0";
//            String message = "0";
//            String img = "www.google.com/";
//            title = remoteMessage.getData().get("title");
//            body =  remoteMessage.getData().get("body");
//            img = remoteMessage.getData().get("imageUrl");
//            message = remoteMessage.getData().get("message");
//            Log.e(TAG, "title: " + title);
//            Log.e(TAG, "message: " + body);
//            Log.e(TAG, "id: " + message);
//            notify(title,"",body,img+"","");
//        }
//    }
//
//    public void notify(String title, String description, String message,String img, String click_action) {
//
//        if (notifManager == null) {
//            notifManager = (NotificationManager) getSystemService
//                    (Context.NOTIFICATION_SERVICE);
//        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationCompat.Builder builder;
//            Intent intent = new Intent(click_action);
//            intent.putExtra("orderId", message);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            PendingIntent pendingIntent;
//            int importance = NotificationManager.IMPORTANCE_HIGH;
//            if (mChannel == null) {
//                mChannel = new NotificationChannel
//                        ("0", title, importance);
//                mChannel.setDescription(description);
//                mChannel.enableVibration(true);
//                notifManager.createNotificationChannel(mChannel);
//            }
//            builder = new NotificationCompat.Builder(this, "0");
//
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
//                    Intent.FLAG_ACTIVITY_SINGLE_TOP);
//            pendingIntent = PendingIntent.getActivity(this, 1251, intent, PendingIntent.FLAG_ONE_SHOT);
//            builder.setContentTitle(title)
////                    .setSmallIcon(getNotificationIcon()) // required
//                    .setContentText(message)  // required
//                    .setDefaults(Notification.DEFAULT_ALL)
//                    .setAutoCancel(true)
//                    .setLargeIcon(getBitmapFromURL(img))
//                    .setBadgeIconType(NotificationCompat.BADGE_ICON_LARGE)
//                    .setContentIntent(pendingIntent)
//                    .setSound(RingtoneManager.getDefaultUri
//                            (RingtoneManager.TYPE_NOTIFICATION));
//            Notification notification = builder.build();
//            notifManager.notify(0, notification);
//        } else {
//
//            Intent intent = new Intent(click_action);
//            intent.putExtra("orderId", message);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            PendingIntent pendingIntent = null;
//            pendingIntent = PendingIntent.getActivity(this, 1251, intent, PendingIntent.FLAG_ONE_SHOT);
//            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
//                    .setContentTitle(title)
//                    .setContentText(message)
//                    .setAutoCancel(true)
//                    .setColor(ContextCompat.getColor(getBaseContext(), R.color.colorPrimary))
//                    .setSound(defaultSoundUri)
////                    .setSmallIcon(getNotificationIcon())
//                    .setLargeIcon(getBitmapFromURL(img))
//                    .setContentIntent(pendingIntent)
//                    .setStyle(new NotificationCompat.BigTextStyle().setBigContentTitle(title).bigText(description));
//
//            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//            notificationManager.notify(1251, notificationBuilder.build());
//        }
//    }
//
////    private int getNotificationIcon() {
////        boolean useWhiteIcon = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP);
////        return useWhiteIcon ? R.drawable.ic_stat_ads4u_logo_png : R.drawable.ic_stat_ads4u_logo_png;
////    }
//
//
//    public Bitmap getBitmapFromURL(String strURL) {
//        try {
//            URL url = new URL(strURL);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setDoInput(true);
//            connection.connect();
//            InputStream input = connection.getInputStream();
//            Bitmap myBitmap = BitmapFactory.decodeStream(input);
//            return myBitmap;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

}
