package presentation.text

import platform.Foundation.NSBundle
import platform.Foundation.NSString
import platform.Foundation.NSURL
import platform.Foundation.stringWithFormat

actual object Strings {

    actual fun get(id: String, quantity: Int): String = id.localized(quantity)

    actual fun get(id: String): String = id.localized()

    actual fun format(id: String, vararg args: Any): String = id.localized(*args)
}

fun String.localized(): String {
    val localizedString = NSBundle.mainBundle.localizedStringForKey(this, this, null)
    if (localizedString != this) return localizedString

    val baseResourcePath = NSBundle.mainBundle.pathForResource("Base", "lproj")
        ?.let { NSURL(fileURLWithPath = it) }
    val baseBundle = baseResourcePath?.let { NSBundle(it) }

    if (baseBundle != null) {
        val baseLocalizedString = baseBundle.localizedStringForKey(this, this, null)
        if (baseLocalizedString != this) return baseLocalizedString
    }

    return this
}

fun String.localized(vararg args: Any?): String {
    val format = localized()
    val a = args
    return when (args.size) {
        0 -> NSString.stringWithFormat(format)
        1 -> NSString.stringWithFormat(format, a[0])
        2 -> NSString.stringWithFormat(format, a[0], a[1])
        3 -> NSString.stringWithFormat(format, a[0], a[1], a[2])
        4 -> NSString.stringWithFormat(format, a[0], a[1], a[2], a[3])
        5 -> NSString.stringWithFormat(format, a[0], a[1], a[2], a[3], a[4])
        6 -> NSString.stringWithFormat(format, a[0], a[1], a[2], a[3], a[4], a[5])
        7 -> NSString.stringWithFormat(format, a[0], a[1], a[2], a[3], a[4], a[5], a[6])
        8 -> NSString.stringWithFormat(format, a[0], a[1], a[2], a[3], a[4], a[5], a[6], a[7])
        9 -> NSString.stringWithFormat(format, a[0], a[1], a[2], a[3], a[4], a[5], a[6], a[7], a[8])
        10 -> NSString.stringWithFormat(format, a[0], a[1], a[2], a[3], a[4], a[5], a[6], a[7], a[8], a[9])
        else -> throw IllegalArgumentException("Too many arguments for localized string")
    }
}