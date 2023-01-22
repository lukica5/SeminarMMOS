package com.example.projektmmoslukab
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    private lateinit var sensorManager: SensorManager
    private lateinit var accelerometer: Sensor
    private lateinit var xValue: TextView
    private lateinit var yValue: TextView
    private lateinit var zValue: TextView
    private  lateinit var linear: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        xValue = findViewById(R.id.x_value)
        yValue = findViewById(R.id.y_value)
        zValue = findViewById(R.id.z_value)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        sensorManager.registerListener(
            accelerometerListener,
            accelerometer,
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    private val accelerometerListener = object : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        }

        override fun onSensorChanged(event: SensorEvent?) {
            if (event != null) {
                xValue.text = "X: " + event.values[0].toString()
                yValue.text = "Y: " + event.values[1].toString()
                zValue.text = "Z: " + event.values[2].toString()
                linear = findViewById(R.id.lay)
                if((event.values[0] < 0.2) && (event.values[1] < 0.2) && (event.values[0] > -0.2) && (event.values[1] > -0.2))
                    linear.setBackgroundColor(resources.getColor(R.color.zelena))
                else
                    linear.setBackgroundColor(resources.getColor(R.color.crvena))
            }
        }
    }
}
