package com.kentoapps.ministagram.util.extension

import java.util.regex.Pattern

fun String?.isValidEmail(): Boolean =
        this != null && this.isNotEmpty() &&
                Pattern.compile(
                        "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
                ).matcher(this).matches()

fun Int.withSuffix(suffix: Suffix): String =
        if (this <= 1)
            "$this ${suffix.singular}"
        else
            "$this ${suffix.plural}"

enum class Suffix(var singular: String, var plural: String) {
    LIKE("Like", "Likes"), COMMENT("Comment", "Comments")
}