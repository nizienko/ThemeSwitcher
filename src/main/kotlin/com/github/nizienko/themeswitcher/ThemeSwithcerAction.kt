package com.github.nizienko.themeswitcher

import com.intellij.ide.actions.QuickChangeLookAndFeel
import com.intellij.ide.ui.LafManager
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class ThemeSwitcherAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val themes = LafManager.getInstance().installedLookAndFeels
        val currentToNextMap = themes.mapIndexed { i, theme ->
            theme to if (i < themes.size - 1) themes[i + 1] else themes[0]
        }.toMap()
        val nextTheme = currentToNextMap[LafManager.getInstance().currentLookAndFeel] ?: throw IllegalStateException()
        QuickChangeLookAndFeel.switchLafAndUpdateUI(LafManager.getInstance(), nextTheme, true)
    }
}