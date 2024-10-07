package com.example.weather.presentations.splash

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.airbnb.lottie.LottieCompositionFactory
import com.airbnb.lottie.LottieDrawable
import com.airbnb.lottie.LottieListener
import com.example.weather.R
import com.example.weather.databinding.ActivitySplashBinding
import com.example.weather.presentations.intro.intro
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class splash : AppCompatActivity() {
    @SuppressLint("SuspiciousIndentation")
    lateinit var binding : ActivitySplashBinding
    val time: Long=3000
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val lottieDrawable= LottieDrawable()
        LottieCompositionFactory.fromRawRes(this,R.raw.splash).addListener{
            lottieDrawable.speed=1.25f
          lottieDrawable.repeatCount= LottieDrawable.INFINITE
            lottieDrawable.enableMergePathsForKitKatAndAbove(true)
            lottieDrawable.composition=it
        }
binding.animation.setImageDrawable(lottieDrawable)
        lottieDrawable.playAnimation()
        GlobalScope.launch{
            delay(time)
            intro.open(this@splash)
            finish()
        }
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
    }
}