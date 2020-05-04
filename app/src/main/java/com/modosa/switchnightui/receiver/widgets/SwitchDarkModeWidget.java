package com.modosa.switchnightui.receiver.widgets;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import com.modosa.switchnightui.R;
import com.modosa.switchnightui.activity.SwitchDarkModeActivity;
import com.modosa.switchnightui.util.OpUtil;

/**
 * Implementation of SwitchDarkModeWidget functionality.
 *
 * @author dadaewq
 */
public class SwitchDarkModeWidget extends AppWidgetProvider {

    private static final String WIDGET_ACTION = "com.modosa.switchnightui.appwidget.switch_dark_mode";

    private static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                        int appWidgetId) {

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget_switch_dark_mode);

        views.setOnClickPendingIntent(R.id.img_switch_dark_mode, getPendingIntent(context));

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    private static PendingIntent getPendingIntent(Context context) {

        Intent intent = new Intent()
                .setClass(context, SwitchDarkModeWidget.class)
                .setAction(WIDGET_ACTION)
                .setData(Uri.parse("id:" + R.id.img_switch_dark_mode));

        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them

        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    private void startActivity(Context context) {
        OpUtil.startMyClass(context, SwitchDarkModeActivity.class);
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        if (WIDGET_ACTION.equals(intent.getAction())) {
            startActivity(context);
        }
    }

}

