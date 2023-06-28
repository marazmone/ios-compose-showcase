package presentation.text

import android.annotation.SuppressLint
import android.content.Context

@SuppressLint("StaticFieldLeak", "DiscouragedApi")
actual object Strings {

    lateinit var context: Context

    actual fun get(id: String): String {
        val resourceId = context.resources.getIdentifier(id, "string", context.packageName)
        if (resourceId == 0) return id
        return context.getString(resourceId)
    }

    actual fun get(id: String, quantity: Int): String {
        val resourceId = context.resources.getIdentifier(id, "plurals", context.packageName)
        if (resourceId == 0) return id
        return context.resources.getQuantityString(resourceId, quantity, quantity)
    }

    actual fun format(id: String, vararg args: Any): String {
        val resourceId = context.resources.getIdentifier(id, "string", context.packageName)
        if (resourceId == 0) return id
        return context.getString(resourceId, *args)
    }
}