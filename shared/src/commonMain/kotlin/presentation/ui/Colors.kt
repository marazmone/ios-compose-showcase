package presentation.ui

import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object Colors {
    object Main {
        val Background = Color(0xFF11124B)
        val BackgroundSecond = Color(0xFF282864)
        val Button = Color(0xFF5BA5F5)
        val ButtonDisable = Color(0x995BA5F5)
    }

    object Rate {
        val Down = Color(0xFFE84A68)
        val Up = Color(0xFF5CC389)
    }

    object Gradient {
        val LightStart = Color(0xFF3A5CF3)
        val LightEnd = Color(0xFF5BA5F5)
        val DarkStart = Color(0xFF23235A)
        val DarkEnd = Color(0xFF303272)
    }

    object Button {

        val DefaultButton: ButtonColors
            @Composable
            get() = ButtonDefaults.buttonColors(
                containerColor = Main.Button,
                contentColor = Color.White,
                disabledContainerColor = Main.ButtonDisable,
                disabledContentColor = Color.White.copy(alpha = 0.5f)
            )
    }
}
