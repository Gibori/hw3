package local.zva.hw3

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import local.zva.hw3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var bindingMA: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMA = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMA.root)
        bindingMA.topAppBar.findViewById<View>(R.id.settings).setOnClickListener {
            Toast.makeText(this, "Настройки", Toast.LENGTH_SHORT).show()
        }

    }

    fun onClickBtn(view: View) {
        val b = view as Button
        Toast.makeText(this, b.text, Toast.LENGTH_SHORT).show()
    }

}