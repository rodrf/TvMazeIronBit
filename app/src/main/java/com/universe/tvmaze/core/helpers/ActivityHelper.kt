package com.universe.tvmaze.core.helpers

import androidx.appcompat.app.AppCompatActivity

open class ActivityHelper: AppCompatActivity(), IActivityHelper {
    override fun showLoader(message: String) {
        DialogStatusHelper.showDialog(this, message)
    }

    override fun hideLoader() {
        DialogStatusHelper.closeDialog()
    }
}