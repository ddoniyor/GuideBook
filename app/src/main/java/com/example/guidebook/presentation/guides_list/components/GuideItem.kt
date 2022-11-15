package com.example.guidebook.presentation.guides_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guidebook.domain.model.Guide
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun GuideItem(
    guide: Guide,
    onCardClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable { onCardClick() },
        elevation = 10.dp,

        shape = RoundedCornerShape(10.dp)
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp, top = 10.dp, bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            GlideImage(
                imageModel = guide.icon,
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .weight(1f)
                ,
                contentScale = ContentScale.Crop,
                alignment = Alignment.TopStart
            )
            Spacer(modifier = Modifier.weight(0.3f))
        //Should display the name, city, state, and end date
            Column(modifier = Modifier.weight(4f)) {
                Text(
                    text = "${guide.name}.",
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(start = 0.dp, end = 5.dp, top = 0.dp, bottom = 0.dp)
                )
                if (!guide.loginRequired){
                    Text(
                        text = "Do not need log in.",
                        fontSize = 14.sp,
                        color = Color.Green,
                        modifier = Modifier
                            .padding(start = 0.dp, end = 5.dp, top = 5.dp, bottom = 0.dp)
                    )
                }

                Text(
                    text = "Start date: ${guide.startDate}.\nEnd date: ${guide.endDate}.",
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(start = 0.dp, end = 5.dp, top = 5.dp, bottom = 0.dp)
                )
            }



        }
    }


}