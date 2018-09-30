package de.thinkad.lumoo.application

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.ImageButton
import de.thinkad.lumoo.R
import de.thinkad.lumoo.application.tabbar_fragments.*
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.sdk25.coroutines.onClick
import net.hockeyapp.android.UpdateManager
import net.hockeyapp.android.CrashManager



class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        set_buttons_disabled()
        when (item.itemId) {
            R.id.navigation_home -> {

                val fragment = Fragment.instantiate(this@MainActivity,
                        PersonalFragment::class.java!!.getName())

                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
                supportActionBar!!.title = "mein Profil"
                tabbar_personal.backgroundResource = R.drawable.round_green
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_chats -> {
                val fragment = Fragment.instantiate(this@MainActivity,
                        ChatListFragment::class.java!!.getName())

                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
                supportActionBar!!.title = "Nachrichten"
                tabbar_chats.setBackgroundResource(R.drawable.round_green)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_discover -> {
                val fragment = Fragment.instantiate(this@MainActivity,
                        DiscoverFragment::class.java!!.getName())

                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()

                supportActionBar!!.title = "Entdecken"
                tabbar_discover.setBackgroundResource(R.drawable.round_green)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_events -> {
                val fragment = Fragment.instantiate(this@MainActivity,
                        EventFragment::class.java!!.getName())

                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
                supportActionBar!!.title = "Veranstaltungen"
                tabbar_events.setBackgroundResource(R.drawable.round_green)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_settings -> {

                val fragment = Fragment.instantiate(this@MainActivity,
                        SettingsFragment::class.java.getName())

                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
                supportActionBar!!.title = "Einstellungen"
                tabbar_settings.setBackgroundResource(R.drawable.round_green)
                return@OnNavigationItemSelectedListener true
            }
        }

        false
    }

    private fun set_buttons_disabled() {
        val buttons = mutableListOf<ImageButton>(tabbar_chats, tabbar_events, tabbar_personal, tabbar_settings)
        for (b in buttons) {
            b.setBackgroundResource(R.drawable.round_red)
        }
        tabbar_discover.backgroundResource= R.drawable.round_light
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.main_toolbar))

        navigation.enableAnimation(false)
        navigation.setTextVisibility(false)
        navigation.setIconSize(32f, 32f)
        navigation.enableShiftingMode(false)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.selectedItemId = navigation.selectedItemId
        navigation.currentItem = 2

        val fragment = Fragment.instantiate(this@MainActivity,
                DiscoverFragment::class.java!!.getName())
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
        supportActionBar!!.title = "Entdecken"
        tabbar_personal.onClick {
            navigation.currentItem = 0
        }
        tabbar_chats.onClick {
            navigation.currentItem = 1
        }
        tabbar_discover.onClick {
            navigation.currentItem = 2

        }
        tabbar_events.onClick {
            navigation.currentItem = 3

        }
        tabbar_settings.onClick {
            navigation.currentItem = 4

        }
        checkForUpdates()
    }

    public override fun onResume() {
        super.onResume()
        checkForCrashes()
    }

    public override fun onPause() {
        super.onPause()
        unregisterManagers()
    }

    public override fun onDestroy() {
        super.onDestroy()
        unregisterManagers()
    }

    private fun checkForCrashes() {
        CrashManager.register(this)
    }

    private fun checkForUpdates() {
        // Remove this for store builds!
        UpdateManager.register(this)
    }

    private fun unregisterManagers() {
        UpdateManager.unregister()
    }

}
