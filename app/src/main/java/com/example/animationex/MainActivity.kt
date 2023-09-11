package com.example.animationex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.RotateAnimation
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.animationex.anim.RotateExpandAnimation
import com.example.animationex.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

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
    }
}