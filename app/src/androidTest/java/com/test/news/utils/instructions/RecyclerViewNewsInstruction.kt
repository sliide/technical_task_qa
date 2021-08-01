package com.test.news.utils.instructions

import android.view.View
import com.azimolabs.conditionwatcher.Instruction
import com.test.news.R
import com.test.news.utils.Utils.getActivityInstance


class RecyclerViewNewsInstruction : Instruction() {
    override fun getDescription(): String {
        return "SwipeRefreshLayout should finish refreshing and disappear"
    }

    override fun checkCondition(): Boolean {
        val srItemList = getActivityInstance().findViewById<View>(R.id.recyclerViewNews)
        return srItemList?.visibility == View.VISIBLE
    }
}