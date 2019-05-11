package hanna.matthew.advandroidfinal.ui

import android.os.Bundle
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity;
import hanna.matthew.advandroidfinal.R

import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : AppCompatActivity() {

    val PREFS_NAME = "prefs"
    val PREF_DARK_THEME = "dark_theme"
    var themeSwitch: Switch? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        val preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        val useDarkTheme = preferences.getBoolean(PREF_DARK_THEME, false)
        if (useDarkTheme) {
            setTheme(R.style.AppTheme_Dark_NoActionBar)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        themeSwitch = findViewById<Switch>(R.id.themeSwitch)
        themeSwitch!!.isChecked = useDarkTheme
        themeSwitch!!.setOnCheckedChangeListener { buttonView, isChecked -> changeTheme(isChecked) }
    }

    fun changeTheme(darkTheme: Boolean?) {
        val editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit()
        editor.putBoolean(PREF_DARK_THEME, darkTheme!!)

        editor.apply()

        val intent = intent
        finish()
        startActivity(intent)
    }

    override fun onRestart() {
        recreate()
        super.onRestart()

    }

}
