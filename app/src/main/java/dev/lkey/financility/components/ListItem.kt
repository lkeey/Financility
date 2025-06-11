package dev.lkey.financility.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
fun ListItem (
    title: String,
    trailingText: String? = null,
    trailingIcon: Int? = null,
    description: String? = null,
    emoji: String? = null,
    height: Dp = 56.dp,
    backgroundColor: Color = MaterialTheme.colorScheme.onSurface,
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor),
        verticalArrangement = Arrangement.Center
    ) {

//        HorizontalDivider(
//            modifier = Modifier
//                .height(1.dp)
//                .fillMaxWidth(),
//            color = MaterialTheme.colorScheme.surfaceDim,
//        )

//        Spacer(Modifier.height(8.dp))

        Row (
            modifier = Modifier
                .height(height)
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            emoji?.let {
                Text(
                    text = it,
                    fontSize = 20.sp
                )

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

            trailingText?.let {
                Text(
                    text = trailingText,
                    color = MaterialTheme.colorScheme.inverseOnSurface,
                    fontWeight = FontWeight.W400,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    letterSpacing = 0.5.sp,
                )
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

//        Spacer(Modifier.height(8.dp))

        HorizontalDivider(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.surfaceDim,
        )
    }
}