package com.example.androidkotlintemplate.weblink

import android.content.Context
import android.content.Intent
import android.net.Uri
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface WebLinkLauncher {
    fun launchApplication(uRL: String)
}

class WeblinkLauncherImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : WebLinkLauncher {
    override fun launchApplication(uRL: String) {
        try {
            context.startActivity(
                Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(uRL)
                    applyFlags()
                }
            )
        } catch (exception: Exception) {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(uRL)
                setPackage("com.android.vending")
                applyFlags()
            }
            context.startActivity(intent)
        }
    }

    private fun Intent.applyFlags() {
        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
}
