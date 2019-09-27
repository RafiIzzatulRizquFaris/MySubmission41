package com.example.mysubmission41.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.mysubmission41.R;

public class FavoriteWidget extends AppWidgetProvider {
    public static final String TOAST_ACTION = "com.example.mysubmission41.TOAST_ACTION";
    public static final String EXTRA_ITEM = "com.example.mysubmission41.EXTRA_DATA";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        Intent i = new Intent(context, StackWidgetService.class);
        i.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        i.setData(Uri.parse(i.toUri(Intent.URI_INTENT_SCHEME)));
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.favorite_widget);
        rv.setRemoteAdapter(R.id.stack_view, i);
        rv.setEmptyView(R.id.stack_view, R.id.empty_view);
        Intent ti = new Intent(context, FavoriteWidget.class);
        ti.setAction(FavoriteWidget.TOAST_ACTION);
        ti.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        i.setData(Uri.parse(i.toUri(Intent.URI_INTENT_SCHEME)));
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, ti, PendingIntent.FLAG_UPDATE_CURRENT);
        rv.setPendingIntentTemplate(R.id.stack_view, pi);
        appWidgetManager.updateAppWidget(appWidgetId, rv);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onReceive(Context context, Intent i) {
        if (i.getAction().equals(TOAST_ACTION)) {
            int viewIndex = i.getIntExtra(EXTRA_ITEM, 0);
            Toast.makeText(context, "Choosing layer " + viewIndex, Toast.LENGTH_SHORT).show();
        }
        super.onReceive(context, i);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}
