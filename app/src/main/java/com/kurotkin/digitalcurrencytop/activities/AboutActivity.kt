package com.kurotkin.digitalcurrencytop.activities

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.ads.AdRequest
import com.kurotkin.digitalcurrencytop.R
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        // Стрелка возврата в тулбаре
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Открывать страницу приложения в маркете GooglePlay
        buttonRateApp.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW,
                Uri.parse("market://details?id=${applicationContext.packageName}")))
        }

        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
    }


}
