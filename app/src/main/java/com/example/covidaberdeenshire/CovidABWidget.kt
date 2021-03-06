package com.example.covidaberdeenshire

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.util.Log
import android.widget.RemoteViews
import com.example.covidaberdeenshire.data.CovidAPI
import com.example.covidaberdeenshire.data.CovidApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import kotlin.math.roundToInt

/**
 * Implementation of App Widget functionality.
 */
class CovidABWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        CovidAPI.retrofitService.getAberdeenshireCases().enqueue(
            object: Callback<CovidApiResponse> {
                override fun onFailure(call: Call<CovidApiResponse>, t: Throwable) {
                    Log.d("Web-Test", t.message.toString());
                }

                override fun onResponse(call: Call<CovidApiResponse>, response: Response<CovidApiResponse>) {
                    val lastRecord = response.body()?.result?.records?.last();

                    Log.d("Web-Success", lastRecord?.getWeekly().toString());
                    val out = lastRecord?.getWeekly()?.roundToInt().toString()

                    // There may be multiple widgets active, so update all of them
                    for (appWidgetId in appWidgetIds) {
                        updateAppWidget(context, appWidgetManager, appWidgetId, out)
                    }

                }

            }
        )

    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int,
    widgetText: String
) {
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.covid_a_b_widget)
    views.setTextViewText(R.id.appwidget_text, widgetText)

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}