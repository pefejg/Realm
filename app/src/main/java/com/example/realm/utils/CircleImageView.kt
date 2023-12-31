package com.example.realm.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.ViewOutlineProvider
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.withStyledAttributes
import com.example.realm.R
import kotlin.math.min

class CircleImageView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : AppCompatImageView(context, attrs, defStyleAttr) {
    private var customDrawable: Drawable? = null

    init {
        //the outline (view edges) of the view should be derived from the background
        outlineProvider = ViewOutlineProvider.BACKGROUND
        //cut the view to match the view to the outline of the background
        clipToOutline = true
        //use the following background to calculate the outline
        setBackgroundResource(R.drawable.bg_circle)

        //fill in the whole image view, crop if needed from while keeping the center
        scaleType = ScaleType.CENTER_CROP

        context.withStyledAttributes(attrs, R.styleable.CircleImageView) {
            customDrawable = getDrawable(R.styleable.CircleImageView_civ_src)
        }

        customDrawable?.let { setImageDrawable(it) }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        //width and height values are received as encoded into an Int, to decode them
        //we have to use View.MeasureSpace.getSize() method
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)
        //we want to make sure width and height are same and if they are different
        //then we want to take the min of width and height. Keeping width and height
        //same is necessary as otherwise our view will not be a perfect circle.
        val minDimension = min(width, height)

        //call this method to let parent view know that we want this view to be
        //this much wide and tall
        setMeasuredDimension(minDimension, minDimension)
    }
}