package com.example.animationex

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.animationex.anim.RotateExpandAnimation
import com.example.animationex.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var isTest = true
    private var isTest2 = true

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

        //Blink
        val anim = AnimationUtils.loadAnimation(this, R.anim.blink_animation)
        binding.ivAndroid.startAnimation(anim)


        //
        binding.ivAir.setOnClickListener { view ->
            var animation =  ObjectAnimator.ofFloat(view, "rotationY", 0.0f, 90f)

            animation.duration = 200
            animation.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)

                    val imageRes = if (isTest) R.drawable.ic_android_red_24dp else R.drawable.ic_android_black_24dp
                    view.setBackgroundResource(imageRes)
                    isTest = !isTest

                    var animation = ObjectAnimator.ofFloat(view, "rotationY", 90f, 180f)

                    animation.duration = 200
                    animation.start()

                }
            })
            animation.start()


//            //https://copycoding.tistory.com/114
//            val rotate = RotateAnimation(
//                0f,
//                180f,
//                Animation.RELATIVE_TO_SELF,
//                0.5f,
//                Animation.RELATIVE_TO_SELF,
//                0.5f
//            )
//            rotate.duration = 200
//            rotate.interpolator = LinearInterpolator()
//
//            binding.ivAir.startAnimation(rotate)
        }


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


//        binding.ivAndroid.setOnClickListener {
//            it.animate().rotationX(180f)
//        }
    }

    companion object {
        const val TAG = "testLog"
    }

}