package ru.itis.androidpractice.features.topic.common.validators

object TextTrimmer {
    fun trimExcessiveLineBreaks(input: String): String {
        return input.replace(Regex("\n{3,}"), "\n")
    }
}