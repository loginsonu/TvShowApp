package com.example.tvshowapp.presentation.tv_show_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.tvshowapp.R
import com.example.tvshowapp.common.Constants
import com.example.tvshowapp.domain.model.TvShow
import com.example.tvshowapp.presentation.ui.theme.TVShowAppTheme

@Composable
fun TvShowListItem(
    tvShow: TvShow,
    onItemClick: (TvShow)->Unit
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(5.dp),
        shape = RoundedCornerShape(3.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        ConstraintLayout {
            val (
                image,
                name,
                fav
            )=createRefs()

            Image(
                modifier = Modifier
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth()
                    .height(200.dp)
                ,
                painter = rememberAsyncImagePainter(Constants.BASE_IMAGE_URL+tvShow.posterPath),
                contentDescription ="image",
                contentScale = ContentScale.FillBounds
            )

            Text(
                modifier = Modifier.constrainAs(name){
                    top.linkTo(image.bottom,5.dp)
                    start.linkTo(image.start,2.dp)
                    bottom.linkTo(parent.bottom)
                }
                    .wrapContentSize().padding(1.dp)
                ,
                text = tvShow.name,
                style = TextStyle(fontSize = 12.sp, textAlign = TextAlign.Start)
            )

            Image(
                modifier = Modifier.constrainAs(fav){
                    top.linkTo(name.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(name.bottom)
                },
                painter = painterResource(id = R.drawable.baseline_stars_24),
                contentDescription = "Fav")

        }
    }
}


@Preview(showBackground = true)
@Composable
fun TvShowListItemPreview() {
    TVShowAppTheme {
      // TvShowListItem()
    }
}