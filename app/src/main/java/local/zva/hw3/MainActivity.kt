package local.zva.hw3

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import local.zva.hw3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val filmDataBase = listOf(
        Film("Prazske noci", R.drawable.pst_1_pn, "Что-то там про Прагу."),
        Film("Santo el enmascarado de plata y Blue Demon contra los monstruos", R.drawable.pst_2_sbvm,
            "To foil his plan for world domination, wrestling superheroes El Santo " +
                    "and Blue Demon battle the mad Dr. Halder and his army of reanimated monsters."),
        Film("The Maze", R.drawable.pst_3_m, "Шотландия, Замок, Лабиринт из кустов ..etc"),
        Film("Forbidden Planet", R.drawable.pst_4_fp, "Sci-fi 56го года)"),
        Film("Colossus: The Forbin Project", R.drawable.pst_5_c,
            "Thinking this will prevent war, the US government gives an impenetrable " +
                    "supercomputer total control over launching nuclear missiles." +
                    " But what the computer does with the power is unimaginable to its creators."),
        Film("Tanin no kao", R.drawable.pst_6_af, "A businessman with a disfigured face obtains a lifelike mask from his doctor, but the mask starts altering his personality."),
        Film("Invasión", R.drawable.pst_7_i, "Вторжение.."),
        Film("Дорога к звездам", R.drawable.pst_8_rs, "Советская НФ"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.topAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.settings -> {
                    Toast.makeText(this, "Настройки", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nightMode -> {
                    nightModeChange()
                    true
                }
                else -> false
            }
        }

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_placeholder, HomeFragment())
                //.addToBackStack(null)
                .commit()
        }

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.favorites -> {
                    Toast.makeText(this, "Избранное", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.watch_later -> {
                    Toast.makeText(this, "Посмотреть позже", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.selections -> {
                    Toast.makeText(this, "Подборки", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }

    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 0) {
            AlertDialog.Builder(this)
                .setTitle("Вы хотите выйти?")
                .setPositiveButton("Да") {_, _ ->
                    finish()
                }
                .setNegativeButton("Нет") {_, _ ->

                }
                .show()
        } else {
            super.onBackPressed()
        }
    }

    fun launchDetailFragment(film: Film){
        val bundle = Bundle()
        bundle.putParcelable("film", film)

        val fragment = DetailsFragment()
        fragment.arguments = bundle

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_placeholder, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun nightModeChange() {
        if (isNightModeOn()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }

    private fun isNightModeOn(): Boolean {
        val nightModeFlags = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return nightModeFlags == Configuration.UI_MODE_NIGHT_YES
    }


}