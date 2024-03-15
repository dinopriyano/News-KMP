import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.dino.newskmp.designSystem.presentation.component.RawrBottomNavigationBar
import com.dino.newskmp.designSystem.presentation.component.TabContainer
import com.dino.newskmp.designSystem.presentation.theme.NewsKMPTheme
import com.dino.newskmp.feature.main.presentation.tab.FavoriteTab
import com.dino.newskmp.feature.main.presentation.tab.HomeTab
import com.dino.newskmp.feature.main.presentation.tab.SearchTab
import com.dino.newskmp.platform.SystemBarColor
import news_kmp.composeapp.generated.resources.*
import news_kmp.composeapp.generated.resources.Res
import news_kmp.composeapp.generated.resources.compose_multiplatform
import news_kmp.composeapp.generated.resources.ic_home_filled
import news_kmp.composeapp.generated.resources.ic_search_filled
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App() {
    NewsKMPTheme {
        SystemBarColor(
            statusBarColor = MaterialTheme.colorScheme.background,
            navigationBarColor = MaterialTheme.colorScheme.background
        )
        Application().Content()
    }
}

class Application: Screen {
    
    @Composable
    override fun Content() {
       val tabs = rememberTabs()

        TabNavigator(HomeTab) {
            Box(
                modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
                contentAlignment = Alignment.BottomCenter
            ) {
                CurrentTab()
                RawrBottomNavigationBar(
                    tabs,
                    modifier = Modifier.padding(bottom = 24.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun rememberTabs(): List<TabContainer> {
    return remember {
        listOf(
            TabContainer(
                HomeTab,
                Res.drawable.ic_home_filled,
                Res.drawable.ic_home_outlined
            ),
            TabContainer(
                SearchTab,
                Res.drawable.ic_search_filled,
                Res.drawable.ic_search_outlined
            ),
            TabContainer(
                FavoriteTab,
                Res.drawable.ic_bookmark_filled,
                Res.drawable.ic_bookmark_outlined
            )
        )
    }
}