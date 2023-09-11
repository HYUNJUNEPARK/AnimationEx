package com.example.animationex.anim

import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.LinearLayout
import android.widget.TextView

class RotateExpandAnimation {
    companion object {
        /**
         * @param isExpanded 이벤트 발생 시점. 뷰의 접힌 상태
         * @param eventTriggerView 이벤트를 발생시키는 뷰
         * @param targetExpandView 이벤트로 인해 변화되는 뷰
         *
         * @return 파라미터로 받은 isExpanded 속성을 반대로 바꿔 반환한다.
         */
        fun rotateExpandView(
            isExpanded: Boolean,
            eventTriggerView: View,
            targetExpandView: LinearLayout,
        ): Boolean {
            if (isExpanded) { //펼쳐진 상태
                collapse(targetExpandView) //뷰를 접는다.
            } else { //접힌 상태
                expand(targetExpandView) //뷰를 펼친다.
            }

            return rotateArrow(eventTriggerView, isExpanded) //화살표를 회전 시키고, 파라미터로 전달받은 isExpanded 의 반대값을 리턴
        }

        fun rotateArrow(view: View, isExpanded: Boolean): Boolean {
            return if (isExpanded) { //서브 뷰가 펼쳐져 있고 화살표는 위로 향한 상태(기본 설정: 화살표 아래로)
                view.animate().setDuration(200).rotation(0f) //화살표를 아래로 돌린다.
                false //isExpanded.not()
            } else { //서브 뷰가 접혀 있고 화살표는 아래로 향한 상태(기본 설정: 화살표 아래로)
                view.animate().setDuration(200).rotation(180f) //화살표를 위로 돌린다.
                true //isExpanded.not()
            }
        }

        fun expand(view: View) {
            val animation = expandAction(view)
            view.startAnimation(animation)
        }

        private fun expandAction(view:View): Animation {
            view.measure(
                /*widthMeasureSpec*/ViewGroup.LayoutParams.MATCH_PARENT,
                /*heightMeasureSpec*/ViewGroup.LayoutParams.WRAP_CONTENT
            )

            val actualHeight = view.measuredHeight

            view.layoutParams.height = 0
            view.visibility = View.VISIBLE

            val animation = object : Animation() {
                override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
                    view.layoutParams.height = if (interpolatedTime == 1f) { //애니메이션 동작이 끝나면 interpolatedTime == 1f
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    } else {
                        (actualHeight*interpolatedTime).toInt()
                    }
                    view.requestLayout()
                }
            }

            animation.duration = (actualHeight / view.context.resources.displayMetrics.density).toLong()

            view.startAnimation(animation)

            return animation
        }

        fun collapse(view: View) {
            val actualHeight = view.measuredHeight

            val animation = object : Animation() {
                override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
                    if (interpolatedTime == 1f) { //애니메이션 동작이 끝나면 interpolatedTime == 1f
                        view.visibility = View.GONE
                    } else {
                        view.layoutParams.height = (actualHeight - (actualHeight * interpolatedTime)).toInt() //뷰의 높이가 점진적으로 줄어든다.
                        view.requestLayout()
                    }
                }
            }

            animation.duration = (actualHeight / view.context.resources.displayMetrics.density).toLong()

            view.startAnimation(animation)
        }
    }
}