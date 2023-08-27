package local.zva.hw3

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun onClickBtn(view: View) {
        val b = view as Button
        Toast.makeText(this, b.text, Toast.LENGTH_SHORT).show()
    }

}