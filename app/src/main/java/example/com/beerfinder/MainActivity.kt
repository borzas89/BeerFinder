package example.com.beerfinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import example.com.beerfinder.databinding.ActivityMainBinding
import example.com.beerfinder.extension.Navigator
import example.com.beerfinder.ui.list.MarkerListFragment


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), Navigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            add(MarkerListFragment())
        }
    }

    override fun add(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainContent, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun pop() {
        supportFragmentManager.popBackStack()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            pop()
        } else {
            finish()
        }
    }
}