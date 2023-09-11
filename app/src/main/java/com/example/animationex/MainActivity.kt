package com.example.animationex

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.animationex.anim.RotateExpandAnimation
import com.example.animationex.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var isBookmarked = false

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

        //이미지 Y축 회전 후 변경
        binding.ivStar.setOnClickListener { view ->
            isBookmarked = changeImageAnimationByRotateY(
                view = view as ImageView,
                isBookmarked = isBookmarked,
                bookmarkImgRes = R.drawable.ic_star_fill_24dp,
                cancelBookmarkImgRes = R.drawable.ic_star_blank_24dp
            )
        }

        binding.ivBookmark.setOnClickListener { view ->
            isBookmarked = changeImageAnimationByRotateY(
                view = view as ImageView,
                isBookmarked = isBookmarked,
                bookmarkImgRes = R.drawable.ic_bookmark_64px_yellow,
                cancelBookmarkImgRes = R.drawable.ic_bookmark_64px_gray
            )
        }
    }

    fun changeImageAnimationByRotateY(
        view: ImageView,
        isBookmarked: Boolean,
        bookmarkImgRes: Int,
        cancelBookmarkImgRes: Int
    ): Boolean {
        val animation =  ObjectAnimator.ofFloat(view, "rotationY", 0.0f, 90f)

        animation.duration = 200
        animation.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)

                //현재 북마크 상태와 반대되는 이미지를 세팅한다.
                val imageRes = if (isBookmarked) cancelBookmarkImgRes else bookmarkImgRes
                view.setImageResource(imageRes)

                val animation = ObjectAnimator.ofFloat(view, "rotationY", 90f, 180f)

                animation.duration = 200
                animation.start()
            }
        })
        animation.start()

        return !isBookmarked
    }


    companion object {
        const val TAG = "testLog"
    }
}