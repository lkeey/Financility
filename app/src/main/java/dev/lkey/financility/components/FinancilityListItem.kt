package dev.lkey.financility.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun FinancilityListItem (
    title: String,
    trailingText: String? = null,
    trailingSubText: String? = null,
    trailingIcon: Int? = null,
    description: String? = null,
    emoji: String? = null,
    height: Dp = 56.dp,
    isShowDivider: Boolean = true,
    backgroundColor: Color = MaterialTheme.colorScheme.onSurface,
    backgroundEmojiColor: Color = MaterialTheme.colorScheme.surfaceContainerLow,
    isClickable: Boolean = true,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { }
) {
    Column (
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor),
        verticalArrangement = Arrangement.Center
    ) {

        Row (
            modifier = Modifier
                .height(height)
                .fillMaxSize()
                .let {
                    if (isClickable) {
                        it.clickable { onClick() }
                    } else {
                        it
                    }
                }
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            emoji?.let {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(backgroundEmojiColor, CircleShape)
                ) {
                    Text(
                        text = it,
                        fontSize =
                            if (!isUnicodeEscape(it)) 18.sp
                            else 10.sp,
                        fontWeight = FontWeight(500),
                        lineHeight = 22.sp,
                        letterSpacing = 0.sp,
                        color = MaterialTheme.colorScheme.inverseOnSurface,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))
            }

            Column (
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.W400,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    letterSpacing = 0.5.sp,
                    color = MaterialTheme.colorScheme.inverseOnSurface
                )

                description?.let {
                    Text(
                        text = it,
                        color = MaterialTheme.colorScheme.surfaceContainer,
                        fontWeight = FontWeight.W400,
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        letterSpacing = 0.25.sp,
                    )
                }
            }

            Column (
                horizontalAlignment = Alignment.End
            ) {
                trailingText?.let {
                    Text(
                        text = it,
                        color = MaterialTheme.colorScheme.inverseOnSurface,
                        fontWeight = FontWeight.W400,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        letterSpacing = 0.5.sp,
                    )
                }

                trailingSubText?.let {
                    Text(
                        text = it,
                        color = MaterialTheme.colorScheme.inverseOnSurface,
                        fontWeight = FontWeight.W400,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        letterSpacing = 0.5.sp,
                    )
                }
            }



            Spacer(modifier = Modifier.width(12.dp))

            trailingIcon?.let {
                Icon(
                    painter = painterResource(it),
                    contentDescription = "awdawd",
                    modifier = Modifier
                        .size(24.dp)
                )
            }
        }
        if (isShowDivider) {
            HorizontalDivider(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth(),
                color = MaterialTheme.colorScheme.surfaceDim,
            )
        }
    }
}

fun isUnicodeEscape(str: String): Boolean {
    val regex = Regex("""[А-Я]*""")
    return regex.matches(str)
}