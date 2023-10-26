package dev.awd.pokedex.data.repository

import android.net.Uri

val String.offsetParam: Int?
    get() {
        val uri = Uri.parse(this)
        val offset = uri.getQueryParameter("offset")

        return offset?.toInt()

    }

