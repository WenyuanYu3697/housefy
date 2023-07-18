package ca.quantum.quants.it.housefy

/*
 * @author Artem Tsurkan, n01414146
 * @author Wenyuan Yu, n01403697
 * @author Kyrylo Lvov, n01414058
 * @course Software Project - CENG-322-0NA
 */

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ca.quantum.quants.it.housefy.components.splash.LogoAndDescription

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LogoAndDescription()
        }

        Handler(Looper.getMainLooper()).postDelayed({
            val activity = Intent(this@SplashScreenActivity, MainActivity::class.java)
            startActivity(activity)
            finish()
        }, 3000)
    }
}
