package com.yarenyarsilikal.pharmacy

import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.orhanobut.hawk.Hawk
import com.yarenyarsilikal.pharmacy.util.PrefUtil
import com.yarenyarsilikal.pharmacy.util.extStartActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Hawk.init(this).build()

        object : CountDownTimer(3000, 1000) {
            override fun onFinish() {
                if (PrefUtil.isFirstTime())
                    startActivity(LoginActivity::class.java)
                else
                    startActivity(MainActivity::class.java)
            }

            override fun onTick(millisUntilFinished: Long) {}
        }.start()
    }

    private fun <T : AppCompatActivity> startActivity(className: Class<T>) {
        this@SplashActivity extStartActivity className
        finish()
    }
}
