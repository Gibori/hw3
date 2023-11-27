package local.zva.hw3.view

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import local.zva.hw3.R
import local.zva.hw3.databinding.ActivityMainBinding
import local.zva.hw3.domain.Film
import local.zva.hw3.view.fragments.DetailsFragment
import local.zva.hw3.view.fragments.FavoritesFragment
import local.zva.hw3.view.fragments.HomeFragment
import local.zva.hw3.view.fragments.SelectionsFragment
import local.zva.hw3.view.fragments.WatchLaterFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    //переменная для отслеживания текущего фрагмента
    private lateinit var currentFragment: String

    override fun onCreate(savedInstanceState: Bundle?) {
        //SplashScreen api работает с sdk 31, проверка на SDK
        //теоретически должна работать с sdk 31, но на 31-32 не отображает вектор поэтому 33
        if (Build.VERSION.SDK_INT >= 33) {
            //это для того чтобы видно было, пускай висит 2 секунды
            var keepSplash = true
            installSplashScreen().setKeepOnScreenCondition { keepSplash }
            Handler(Looper.getMainLooper()).postDelayed({ keepSplash = false }, 1000L)
            //заменяем тему для вызова splash screen
            splashScreen.setSplashScreenTheme(R.style.Theme_AppSplash)
            splashScreen.setOnExitAnimationListener { splashView ->
                splashView.iconView!!
                    .animate()
                    .setDuration(600L)
                    .alpha(0f)
                    .withEndAction {
                        splashView.remove()
                    }
                    .start()
            }
        }
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            currentFragment = "home"
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
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_placeholder, fragment, tag)
            .addToBackStack(tag)
            .commit()

        currentFragment = tag
    }

    override fun onBackPressed() {
        val fragmentTags = arrayOf("home", "favorites", "watch_later", "selections")
        if (currentFragment in fragmentTags) {
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

            currentFragment = supportFragmentManager.getBackStackEntryAt(
                supportFragmentManager.backStackEntryCount - 1
            ).name ?: ""
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
            .addToBackStack("details")
            .commit()

        currentFragment = "details"
    }
}