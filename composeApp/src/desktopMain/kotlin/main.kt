import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.dino.newskmp.common.di.initKoin
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "News-KMP") {
        Napier.base(DebugAntilog())
        initKoin()
        App()
    }
}