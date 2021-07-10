package com.app.appshortcut

import android.annotation.TargetApi
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= 25) {
            createShortcut()
        } else {
            removeShortcuts()
        }
    }

    @TargetApi(25)
    private fun createShortcut() {
        // ShortcutManager uses system service so this has to be in an activity and remember this only works on Android 7.1
        val sM = getSystemService(ShortcutManager::class.java)
        val intent1 = Intent(applicationContext, ProfileActivity::class.java)
        intent1.action = Intent.ACTION_VIEW
        val shortcut1 = ShortcutInfo.Builder(this, "profileshortcut")
            .setIntent(intent1)
            .setShortLabel(getString(R.string.short_label_profile))
            .setLongLabel(getString(R.string.long_label_profile))
            .setDisabledMessage(getString(R.string.disable_label_profile))
            .setIcon(Icon.createWithResource(this, R.drawable.ic_baseline_person_24))
            .build()
        sM.dynamicShortcuts = listOf(shortcut1)
    }

    @TargetApi(25)
    private fun removeShortcuts() {
        val shortcutManager = getSystemService(ShortcutManager::class.java)
        shortcutManager.disableShortcuts(listOf("profileshortcut"))
        shortcutManager.removeAllDynamicShortcuts()
    }
}
