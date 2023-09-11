package com.example.animationex

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.animationex.anim.RotateExpandAnimation
import com.example.animationex.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var isRotate = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //화살표 회전 밑 / 디테일 뷰 ON/OFF
        binding.ivArrow.setOnClickListener {
            val isExpanded = binding.layoutSampleTxt.visibility == View.VISIBLE

            RotateExpandAnimation.rotateExpandView(
                isExpanded = isExpanded,
                eventTriggerView = it,
                targetExpandView = binding.layoutSampleTxt
            )
        }

        //val anim
        val anim = AnimationUtils.loadAnimation(this, R.anim.blink_animation)
        binding.ivAndroid.startAnimation(anim)
        
//        binding.ivAndroid.setOnClickListener {
//            val animation = if (isRotate) {
//                ObjectAnimator.ofFloat(it, "rotationY", 0.0f, 180f)
//            } else {
//                ObjectAnimator.ofFloat(it, "rotationY", 180f, 0.0f)
//            }
//            isRotate = !isRotate
//
//
//            animation.duration = 200
//            animation.repeatCount = 1
//            animation.interpolator = AccelerateDecelerateInterpolator()
//            animation.start()
//        }
//
//        val animation =  ObjectAnimator.ofFloat(binding.ivAndroid, "rotationY", 90f, 0.0f)
//        animation.duration = 200
//        animation.repeatCount = 1
//        animation.interpolator = AccelerateDecelerateInterpolator()
//        animation.start()

//        binding.ivAndroid.setOnClickListener {
//            it.animate().rotationX(180f)
//        }
    }
}