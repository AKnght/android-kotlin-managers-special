package com.randythedford.managerspecials.controls

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet

 class StrikeThruTextView : androidx.appcompat.widget.AppCompatTextView {

    constructor(context: Context) : super(context) {
       addStrike()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
       addStrike()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
       addStrike()
    }

    public fun removeStrike() {
       paintFlags = 0;
    }

    public fun addStrike() {
       paintFlags = Paint.STRIKE_THRU_TEXT_FLAG;
    }


}