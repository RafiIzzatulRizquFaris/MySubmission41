package com.example.mysubmission41.notification;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.mysubmission41.ApiConfig;
import com.example.mysubmission41.ApiInterface;
import com.example.mysubmission41.R;
import com.example.mysubmission41.activity.MainActivity;
import com.example.mysubmission41.pojo.Movie;
import com.example.mysubmission41.pojo.MovieDetailItem;
import com.example.mysubmission41.response.MovieListResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mysubmission41.ApiConfig.API_KEY;

public class ReleaseReminder extends BroadcastReceiver {

    public static final int pageNo = 1;
    private static final int ID_RELEASE = 100000;
    private final String TAG = "Release Reminder: ";
    public static final int NOTIFICATION_ID = 11;
    public static String CHANNEL_ID = "channel_02";
    public static CharSequence CHANNEL_NAME = "movie channel";


    @Override
    public void onReceive(Context context, Intent intent) {
        getMoviesRelease(context);
    }

    private void getMoviesRelease(Context context) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String date = dateFormat.format(Calendar.getInstance().getTime());
        String url = Constants.getUrl(
                Constants.URL_TYPE_NEW_RELEASE,
                Constants.URL_MOVIES,
                date,
                context
        );
        getMovies(context, url);
//        final ApiInterface apiInterface = ApiConfig.getClient().create(ApiInterface.class);
//        Call<MovieListResponse> call = apiInterface.getMovieDiscover(API_KEY, pageNo);
//        call.enqueue(new Callback<MovieListResponse>() {
//            @Override
//            public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
//                List<MovieDetailItem> movieItems = response.body().getResults();
//
//                String title = movieItems.get(0).getTitle();
//                String getMessage = context.getResources().getString(R.string.message_release_today);
//                String message = title + " " + getMessage;
//                String releaseDate = movieItems.get(0).getReleaseDate();
//                int id  = movieItems.get(0).getId();
//                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
//                Date date = new Date();
//                final String getTime = dateFormat.format(date);
//                Log.d(TAG, "TIME: " + releaseDate);
//
//                if (releaseDate.equals(getTime)) {
//                    notificationRelease(context, title, message, id);
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<MovieListResponse> call, Throwable t) {
//                Log.e(TAG, "Error : ", t);
//            }
//        });
    }

    private void getMovies(Context context, String url) {
        AndroidNetworking.get(url)
                .setTag("movies")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray results =  response.getJSONArray("results");
                            int totalItem = results.length();
                            if(totalItem == 0)
                                notificationRelease(
                                        context,
                                        context.getResources().getString(R.string.message_release_today),
                                        context.getResources().getString(R.string.message_release_today),
                                        ID_RELEASE
                                );
                            else
                                for(int i=0; i< totalItem;i++) {
                                    JSONObject movieObject = results.getJSONObject(i);
                                    final Movie moviesItem = new Movie(movieObject);
                                    String message = String.format(
                                            context.getResources().getString(R.string.notification_format_message),
                                            moviesItem.getTitle()
                                    );
                                    notificationRelease(
                                            context,
                                            moviesItem.getTitle(),
                                            message,
                                            moviesItem.getId()
                                    );
                                }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        String title = context.getResources().getString(R.string.message_release_today);
                        String message = context.getResources().getString(R.string.message_release_today);
                        notificationRelease(
                                context,
                                title,
                                message,
                                ID_RELEASE
                        );
                    }
                });
    }

    private void notificationRelease(Context context, String title, String message, int id) {

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = TaskStackBuilder.create(context)
                .addNextIntent(intent)
                .getPendingIntent(NOTIFICATION_ID, PendingIntent.FLAG_UPDATE_CURRENT);
        Uri defaultNotification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setContentTitle(title)
                .setContentText(message)
                .setVibrate(new long[] {1000, 1000, 1000, 1000})
                .setSound(defaultNotification);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            mBuilder.setChannelId(CHANNEL_ID);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }

        Notification notification = mBuilder.build();
        mBuilder.setAutoCancel(true);

        if (notificationManager != null) {
            notificationManager.notify(id, notification);
        }
    }

    public void cancelNotificationRelease(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, ReleaseReminder.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, NOTIFICATION_ID, intent, 0);
        (alarmManager).cancel(pendingIntent);
    }

    public void setAlarmRelease(Context context, String type, String time, String message) {
        cancelNotificationRelease(context);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, ReleaseReminder.class);
        intent.putExtra("message", message);
        intent.putExtra("type", type);
        String timeArray[] = time.split(":");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]));
        calendar.set(Calendar.SECOND, 0);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, NOTIFICATION_ID, intent, 0);
        (alarmManager).setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }
}
