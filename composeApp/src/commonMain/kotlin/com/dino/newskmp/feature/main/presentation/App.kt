import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.dino.newskmp.common.utils.isLargeScreen
import com.dino.newskmp.designSystem.presentation.component.RawrBottomNavigationBar
import com.dino.newskmp.designSystem.presentation.component.RawrNavigationRail
import com.dino.newskmp.designSystem.presentation.component.TabContainer
import com.dino.newskmp.designSystem.presentation.theme.NewsKMPTheme
import com.dino.newskmp.feature.main.presentation.tab.FavoriteTab
import com.dino.newskmp.feature.main.presentation.tab.HomeTab
import com.dino.newskmp.feature.main.presentation.tab.SearchTab
import com.dino.newskmp.platform.SystemBarColor
import news_kmp.composeapp.generated.resources.Res
import news_kmp.composeapp.generated.resources.ic_bookmark_filled
import news_kmp.composeapp.generated.resources.ic_bookmark_outlined
import news_kmp.composeapp.generated.resources.ic_home_filled
import news_kmp.composeapp.generated.resources.ic_home_outlined
import news_kmp.composeapp.generated.resources.ic_search_filled
import news_kmp.composeapp.generated.resources.ic_search_outlined
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
            Row (
                modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)
            ) {
                AnimatedVisibility(visible = isLargeScreen()) {
                    RawrNavigationRail(
                        tabs,
                        modifier = Modifier
                    )
                }
                Column(
                    modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box (
                        modifier = Modifier.weight(1f)
                    ) {
                        CurrentTab()
                    }
                    AnimatedVisibility(visible = !isLargeScreen()) {
                        RawrBottomNavigationBar(
                            tabs,
                            modifier = Modifier.padding(bottom = 24.dp)
                        )
                    }
                }
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