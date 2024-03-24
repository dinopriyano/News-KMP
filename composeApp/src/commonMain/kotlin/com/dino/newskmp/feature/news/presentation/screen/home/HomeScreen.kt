package com.dino.newskmp.feature.news.presentation.screen.home

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import com.dino.newskmp.common.presentation.base.BaseScreen
import com.dino.newskmp.common.utils.*
import com.dino.newskmp.designSystem.presentation.component.*
import com.dino.newskmp.designSystem.presentation.theme.DarkTransparent
import com.dino.newskmp.designSystem.presentation.theme.IconPastel
import com.dino.newskmp.feature.news.data.DummyData
import news_kmp.composeapp.generated.resources.*
import news_kmp.composeapp.generated.resources.Res
import news_kmp.composeapp.generated.resources.ic_bookmark_outlined
import news_kmp.composeapp.generated.resources.ic_like_outlined
import news_kmp.composeapp.generated.resources.ic_share_outlined
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import kotlin.math.abs

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
    val lazyListState = rememberLazyListState()
    val snapBehavior = rememberSnapFlingBehavior(lazyListState)

    LazyRow (
        modifier = modifier,
        state = lazyListState,
        flingBehavior = snapBehavior,
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp)
    ) {
        itemsIndexed(DummyData.newsList) {index, news ->
            Card(
                modifier = Modifier
                    .fillParentMaxWidth()
                    .noRippleClickable {

                    }.carouselTransition(
                        index,
                        lazyListState
                    ),
                shape = RoundedCornerShape(28.dp),
                colors = CardDefaults.cardColors(
                    containerColor = getCardColor(index)
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
        modifier = modifier.clip(CircleShape).background(DarkTransparent),
        onClick = onClick
    ) {
        Icon(
            painterResource(imageResource),
            contentDescription = null,
            modifier = Modifier.padding(5.dp),
            tint = IconPastel
        )
    }
}