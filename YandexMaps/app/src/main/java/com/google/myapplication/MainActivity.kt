package com.google.myapplication

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.yandex.mapkit.Animation

import com.yandex.mapkit.GeoObjectCollection
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraListener
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.CameraUpdateSource
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.map.VisibleRegionUtils
import com.yandex.mapkit.mapview.MapView
import com.yandex.mapkit.search.Response
import com.yandex.mapkit.search.SearchFactory
import com.yandex.mapkit.search.SearchManagerType
import com.yandex.mapkit.search.SearchOptions
import com.yandex.mapkit.search.SearchManager
import com.yandex.mapkit.search.Session
import com.yandex.runtime.Error
import com.yandex.runtime.image.ImageProvider
import com.yandex.runtime.network.NetworkError
import com.yandex.runtime.network.RemoteError
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

/**
 * В этом примере показывается карта и камера выставляется на указанную точку.
 * Не забудьте запросить необходимые разрешения.
 */
class MapActivity : Activity() {
    /**
     * Замените "your_api_key" валидным API-ключом.
     * Ключ можно получить на сайте https://developer.tech.yandex.ru/
     */
    private val MAPKIT_API_KEY = "0071cd7a-8aca-4d08-b24a-e962e2416234"
    private val TARGET_LOCATION = Point(59.945933, 30.320045)
    private val HOSTEL = Point(59.875289, 29.828306)

    private var mapView: MapView? = null
    private var searchEdit: EditText? = null
    private var searchManager: SearchManager? = null
    private var searchSession: Session? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        MapKitFactory.setApiKey(MAPKIT_API_KEY)
        MapKitFactory.initialize(this)
        setContentView(R.layout.activity_main)
        super.onCreate(savedInstanceState)
        mapView = findViewById(R.id.mapview)

        val locationManager = this.getSystemService(Context.LOCATION_SERVICE)

        mapView!!.map.move(
            CameraPosition(TARGET_LOCATION, 12.0f, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 5f),
            null
        )

        mapView!!.map.move(
            CameraPosition(HOSTEL, 12.0f, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 5f),
            null
        )

        mapview.map.mapObjects.addPlacemark(TARGET_LOCATION)
        mapview.map.mapObjects.addPlacemark(HOSTEL)
        var tlat = TARGET_LOCATION.latitude;
        var tlon = TARGET_LOCATION.longitude;
        var tlatstr = tlat.toString()
        var tlonstr = tlon.toString()
        val toast = Toast.makeText(applicationContext, "$tlatstr $tlonstr", Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show()
    }

    override fun onStop() {
        // Вызов onStop нужно передавать инстансам MapView и MapKit.
        mapView!!.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    override fun onStart() {
        // Вызов onStart нужно передавать инстансам MapView и MapKit.
        super.onStart()
        MapKitFactory.getInstance().onStart()
        mapView!!.onStart()
    }
}

private fun Map.addInputListener() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}
