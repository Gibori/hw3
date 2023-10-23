package local.zva.hw3

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_placeholder, HomeFragment(), "home")
                .addToBackStack("home")
                .commit()
        }

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    val tag = "home"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment(fragment?: HomeFragment(), tag)
                    true
                }
                R.id.favorites -> {
                    val tag = "favorites"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment(fragment?: FavoritesFragment(), tag)
                    true
                }
                R.id.watch_later -> {
                    val tag = "watch_later"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment(fragment?: WatchLaterFragment(), tag)
                    true
                }
                R.id.selections -> {
                    val tag = "selections"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment(fragment?: SelectionsFragment(), tag)
                    true
                }
                else -> false
            }
        }

    }

    private fun checkFragmentExistence(tag: String): Fragment? =
        supportFragmentManager.findFragmentByTag(tag)

    private fun changeFragment(fragment: Fragment, tag: String) {
        val skipBackStackTags = arrayOf("home", "favorites", "watch_later", "selections")

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_placeholder, fragment, tag)
            .apply {
                if (tag in skipBackStackTags &&
                    supportFragmentManager.backStackEntryCount >= 2) {
                    commit()
                } else {
                    addToBackStack(tag)
                    commit()
                }
            }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
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
}