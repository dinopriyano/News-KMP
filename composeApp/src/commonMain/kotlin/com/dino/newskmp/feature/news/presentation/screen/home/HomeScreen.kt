package com.dino.newskmp.feature.news.presentation.screen.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import com.dino.newskmp.common.presentation.base.BaseScreen
import com.dino.newskmp.common.utils.animateScaled
import com.dino.newskmp.common.utils.carouselTransition
import com.dino.newskmp.common.utils.getCardColor
import com.dino.newskmp.common.utils.noRippleClickable
import com.dino.newskmp.designSystem.presentation.component.NewsCard
import com.dino.newskmp.designSystem.presentation.component.RawrMultiSelectionChip
import com.dino.newskmp.designSystem.presentation.component.RawrMultiSelectionColor
import com.dino.newskmp.designSystem.presentation.theme.DarkTransparent
import com.dino.newskmp.designSystem.presentation.theme.IconPastel
import com.dino.newskmp.feature.news.data.DummyData
import news_kmp.composeapp.generated.resources.Res
import news_kmp.composeapp.generated.resources.ic_bookmark_outlined
import news_kmp.composeapp.generated.resources.ic_kmp_news_light
import news_kmp.composeapp.generated.resources.ic_like_outlined
import news_kmp.composeapp.generated.resources.ic_share_outlined
import news_kmp.composeapp.generated.resources.title
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

class HomeScreen : BaseScreen<HomeScreenModel, HomeScreenUiState, HomeScreenUiEffect, HomeScreenInteractionListener>() {

    @Composable
    override fun Content() {
        initScreen(getScreenModel())
    }

    @Composable
    override fun onRender(state: HomeScreenUiState, listener: HomeScreenInteractionListener) {
        var selectedCategoryIndex by rememberSaveable { mutableStateOf(0) }

        Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
            // Home header
            NewsHeader(Modifier.fillMaxWidth())

            // Category
            NewsCategories(
                selectedCategoryIndex,
                DummyData.categories,
                Modifier.padding(top = 16.dp),
            ) { title, index ->
                selectedCategoryIndex = index
                // TODO: API call here
            }

            // Slider content
            NewsSlider(modifier = Modifier.fillMaxSize().padding(bottom = (56.dp + 5.dp + 24.dp)))
        }
    }

    override fun onEffect(effect: HomeScreenUiEffect, navigator: Navigator) {
        // TODO: Not yet implemented
    }

}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalResourceApi::class)
@Composable
fun NewsHeader(
    modifier: Modifier
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        title = {
            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_kmp_news_light),
                    modifier = Modifier.size(42.dp),
                    tint = Color.Unspecified,
                    contentDescription = null
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = stringResource(Res.string.title),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    )
}

@Composable
fun NewsCategories(
    selectedIndex: Int,
    categories: List<String>,
    modifier: Modifier,
    onSelected: (String, Int) -> Unit
) {
    RawrMultiSelectionChip(
        options = categories,
        selectedIndex = selectedIndex,
        colors = RawrMultiSelectionColor(
            containerColor = MaterialTheme.colorScheme.background,
            selectedOptionColor = MaterialTheme.colorScheme.background,
            unselectedOptionColor = MaterialTheme.colorScheme.onBackground,
            indicatorColor = MaterialTheme.colorScheme.onBackground
        ),
        onOptionSelected = { title, index ->
            onSelected(title, index)
        },
        horizontalContentPadding = 24.dp,
        modifier = modifier,
        rememberScrollState()
    )
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalResourceApi::class)
@Composable
fun NewsSlider(
    modifier: Modifier
) {
    val pagerState = rememberPagerState(
        pageCount = {
            DummyData.newsList.size
        }
    )
    HorizontalPager(
        state = pagerState,
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp)
    ) { page ->
        val newsList = DummyData.newsList.getOrNull(page)
        newsList?.let { news ->
            Card(
                modifier = Modifier
                    .noRippleClickable {

                    }
                    .carouselTransition(
                        page,
                        pagerState
                    ),
                shape = RoundedCornerShape(28.dp),
                elevation = CardDefaults.cardElevation(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = getCardColor(page)
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.End
                ) {
                    NewsCard(
                        news = news,
                        modifier = Modifier.fillMaxWidth().weight(1f).padding(24.dp)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier.padding(16.dp)
                    ) {
                        ActionImage(
                            imageResource = Res.drawable.ic_like_outlined,
                            modifier = Modifier
                        ) {

                        }
                        ActionImage(
                            imageResource = Res.drawable.ic_bookmark_outlined,
                            modifier = Modifier
                        ) {

                        }
                        ActionImage(
                            imageResource = Res.drawable.ic_share_outlined,
                            modifier = Modifier
                        ) {

                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ActionImage(
    imageResource: DrawableResource,
    modifier: Modifier,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier.clip(CircleShape),
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = DarkTransparent
        ),
        onClick = onClick
    ) {
        Icon(
            painterResource(imageResource),
            contentDescription = null,
            modifier = Modifier.padding(4.dp).animateScaled(),
            tint = IconPastel
        )
    }
}