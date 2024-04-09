package com.dino.newskmp.feature.interest.presentation.screen.manage_interest

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import com.dino.newskmp.common.presentation.base.BaseScreen
import com.dino.newskmp.common.utils.animateScaled
import com.dino.newskmp.designSystem.presentation.component.RawrCheckBox
import com.dino.newskmp.designSystem.presentation.theme.PinkPastel
import com.dino.newskmp.platform.SystemBarColor
import news_kmp.composeapp.generated.resources.Res
import news_kmp.composeapp.generated.resources.ic_back_arrow
import news_kmp.composeapp.generated.resources.select_interest_txt
import news_kmp.composeapp.generated.resources.submit_txt
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

/**
 * Created by dinopriyano on 04/04/24.
 */

class ManageInterestScreen :
    BaseScreen<ManageInterestScreenModel, ManageInterestScreenUiState, ManageInterestScreenUiEffect, ManageInterestScreenInteractionListener>() {

    override fun onEffect(effect: ManageInterestScreenUiEffect, navigator: Navigator) {
        when (effect) {
            is ManageInterestScreenUiEffect.NavigateBack -> {
                navigator.pop()
            }
        }
    }

    @Composable
    override fun setSystemBarColor() {
        SystemBarColor(
            statusBarColor = PinkPastel,
            navigationBarColor = PinkPastel
        )
    }

    @Composable
    override fun Content() {
        initScreen(getScreenModel())
    }

    @Composable
    override fun onRender(
        state: ManageInterestScreenUiState,
        listener: ManageInterestScreenInteractionListener
    ) {
        val lazyListState = rememberLazyListState()
        var previousIndex by remember { mutableStateOf(0) }
        var previousScrollOffset by remember { mutableStateOf(0) }
        val isScrollToUp by remember {
            derivedStateOf {
                if (previousIndex != lazyListState.firstVisibleItemIndex) {
                    previousIndex > lazyListState.firstVisibleItemIndex
                } else {
                    previousScrollOffset >= lazyListState.firstVisibleItemScrollOffset
                }.also {
                    previousIndex = lazyListState.firstVisibleItemIndex
                    previousScrollOffset =  lazyListState.firstVisibleItemScrollOffset
                }
            }
        }
        val isButtonShouldVisible by remember {
            derivedStateOf { !lazyListState.isScrollInProgress || isScrollToUp }
        }

        Box (
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column(
                modifier = Modifier.fillMaxSize().background(PinkPastel)
            ) {
                ManageInterestHeader(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    listener.onBackButtonClick()
                }
                ManageInterestCategory(
                    modifier = Modifier.fillMaxSize(),
                    categories = listOf(
                        "Trending", "Business", "Entertainment", "Health", "Science", "Sports", "Technology", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test"
                    ),
                    state = lazyListState
                )
            }

            AnimatedVisibility(
                visible = isButtonShouldVisible,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it })
            ) {
                ManageInterestFooter(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .padding(bottom = 24.dp)
                        .animateScaled(),
                    onClick = {

                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ManageInterestHeader(
    modifier: Modifier,
    onBackButtonClick: () -> Unit
) {
    TopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),
        navigationIcon = {
            Row {
                Spacer(Modifier.width(8.dp))
                IconButton(
                    onClick = onBackButtonClick
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_back_arrow),
                        tint = MaterialTheme.colorScheme.background,
                        contentDescription = null
                    )
                }
            }
        },
        title = {
            Row {
                Spacer(Modifier.width(8.dp))
                Text(
                    text = stringResource(Res.string.select_interest_txt),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.background
                )
            }
        }
    )
}

@Composable
fun ManageInterestCategory(
    modifier: Modifier,
    categories: List<String>,
    state: LazyListState
) {
    val selectedCategories = remember {
        mutableStateMapOf<String, String>()
    }
    LazyColumn (
        modifier = modifier,
        contentPadding = PaddingValues(top = 16.dp, bottom = 100.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        state = state
    ) {
        items(categories) { category ->
            RawrCheckBox(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
                label = category,
                isChecked = selectedCategories.contains(category)
            ) {
                if (it) {
                    selectedCategories[category] = ""
                } else {
                    selectedCategories.remove(category)
                }
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ManageInterestFooter(
    modifier: Modifier,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        contentPadding = PaddingValues(vertical = 16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground
        ),
        onClick = onClick
    ) {
        Text(
            text = stringResource(Res.string.submit_txt),
            style = MaterialTheme.typography.labelMedium
        )
    }

}