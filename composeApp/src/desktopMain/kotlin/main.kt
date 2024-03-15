import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.dino.newskmp.feature.main.di.initKoin

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "News-KMP") {
        initKoin("")
        App()
    }
}