package com.dino.newskmp.feature.news.presentation.screen.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.dino.newskmp.common.domain.model.News
import com.dino.newskmp.common.presentation.base.BaseScreen
import com.dino.newskmp.common.utils.animateScaled
import com.dino.newskmp.common.utils.carouselTransition
import com.dino.newskmp.common.utils.getCardColor
import com.dino.newskmp.common.utils.isLargeScreen
import com.dino.newskmp.common.utils.noRippleClickable
import com.dino.newskmp.designSystem.presentation.component.NewsShortcut
import com.dino.newskmp.designSystem.presentation.component.RawrMultiSelectionChip
import com.dino.newskmp.designSystem.presentation.component.RawrMultiSelectionColor
import com.dino.newskmp.designSystem.presentation.theme.DarkTransparent
import com.dino.newskmp.designSystem.presentation.theme.IconPastel
import news_kmp.composeapp.generated.resources.Res
import news_kmp.composeapp.generated.resources.ic_kmp_news_light
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
        val categories = listOf(
            "Trending", "Business", "Entertainment", "Health", "Science", "Sports", "Technology"
        )

        Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
            // Home header
            NewsHeader(Modifier.fillMaxWidth())

            // Category
            NewsCategories(
                selectedCategoryIndex,
                categories,
                Modifier.padding(top = 16.dp),
            ) { title, index ->
                selectedCategoryIndex = index
                listener.onCategoryClick(title)
            }

            // Slider content
            NewsSlider(
                newsList = state.news,
                modifier = Modifier.fillMaxSize(),
                isShouldScrollToFirstItem = state.isShouldScrollToFirstItem,
                onScrollToFirstItem = listener::onScrollTFirstItem
            )
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

@Composable
fun NewsSlider(
    newsList: List<News>,
    modifier: Modifier,
    isShouldScrollToFirstItem: Boolean,
    onScrollToFirstItem: () -> Unit
) {
    if (isLargeScreen()) {
        LargeScreeSlider(newsList, isShouldScrollToFirstItem, modifier, onScrollToFirstItem)
    } else {
        SmallScreenSlider(newsList, isShouldScrollToFirstItem, modifier, onScrollToFirstItem)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SmallScreenSlider(
    newsList: List<News>,
    isShouldScrollToFirstItem: Boolean,
    modifier: Modifier,
    onScrollToFirstItem: () -> Unit,
) {
    val pagerState = rememberPagerState(
        pageCount = {
            newsList.size
        }
    )

    LaunchedEffect(isShouldScrollToFirstItem) {
        if (isShouldScrollToFirstItem){
            pagerState.animateScrollToPage(0)
            onScrollToFirstItem()
        }
    }

    HorizontalPager(
        state = pagerState,
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp)
    ) { page ->
        val currentNews = newsList.getOrNull(page)
        currentNews?.let { news ->
            NewsShortcut(
                news = news,
                colors = CardDefaults.cardColors(
                    containerColor = getCardColor(page)
                ),
                modifier = Modifier
                    .noRippleClickable {

                    }
                    .carouselTransition(
                        page,
                        pagerState
                    )
            )
        }
    }
}

@Composable
fun LargeScreeSlider(
    newsList: List<News>,
    isShouldScrollToFirstItem: Boolean,
    modifier: Modifier,
    onScrollToFirstItem: () -> Unit,
) {
    val listState = rememberLazyListState()

    LaunchedEffect(isShouldScrollToFirstItem) {
        if (isShouldScrollToFirstItem){
            listState.animateScrollToItem(0)
            onScrollToFirstItem()
        }
    }

    LazyRow (
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        state = listState
    ) {
        itemsIndexed(newsList) { index, news ->
            NewsShortcut(
                news = news,
                colors = CardDefaults.cardColors(
                    containerColor = getCardColor(index)
                ),
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(3f/4f)
                    .noRippleClickable {

                    }
            )
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