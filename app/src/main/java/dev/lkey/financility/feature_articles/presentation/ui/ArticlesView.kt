package dev.lkey.financility.feature_articles.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.lkey.financility.R
import dev.lkey.financility.components.field.FinancilityEditText
import dev.lkey.financility.components.item.FinancilityListItem
import dev.lkey.financility.feature_articles.presentation.viewmodel.ArticlesEvent
import dev.lkey.financility.feature_articles.presentation.viewmodel.ArticlesState

@Composable
fun ArticlesView (
    modifier: Modifier = Modifier,
    state: ArticlesState,
    onEvent: (ArticlesEvent) -> Unit,
) {

    val scrollState = rememberScrollState()

    Column (
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(
                state = scrollState,
            )
    ){
        FinancilityEditText(
            previousData = "",
            label = stringResource(R.string.find_article),
            onTrailingIconClick = {
                onEvent(ArticlesEvent.OnSearchValueChanged(
                    searchValue = it
                ))
            },
        ) {
            /* TODO */
        }

        HorizontalDivider(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.surfaceDim,
        )

        state.articles
            .filter { it.name.contains(state.searchValue) }
            .forEach {
                FinancilityListItem(
                    emoji = it.emoji,
                    title = it.name,
                    height = 70.dp,
                    isClickable = false,
                    onClick = { }
                )
        }
    }
}