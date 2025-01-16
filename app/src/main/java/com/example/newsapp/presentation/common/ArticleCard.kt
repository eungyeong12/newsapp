package com.example.newsapp.presentation.common

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.example.newsapp.R
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.model.Source
import com.example.newsapp.presentation.Dimens.ArticleCardSize
import com.example.newsapp.presentation.Dimens.ExtraSmallPadding
import com.example.newsapp.presentation.Dimens.ExtraSmallPadding2
import com.example.newsapp.presentation.Dimens.SmallIconSize
import com.example.newsapp.ui.theme.NewsAppTheme

@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
    article: Article,
    onClick:() -> Unit
) {
    val context = LocalContext.current

    Row(modifier = modifier.clickable { onClick() }) {
        // 비동기로 이미지를 로드 및 표시
        AsyncImage(
            modifier = Modifier
                .size(ArticleCardSize) // 이미지 크기 설정
                .clip(MaterialTheme.shapes.medium), // 이미지의 모서리를 둥글게 설정
            contentScale = ContentScale.Crop, // 이미지를 Crop하여 꽉 채움
            model = ImageRequest.Builder(context).data(article.urlToImage).build(), // 기사 데이터의 이미지 URL
            contentDescription = null
        )

        // 기사 제목과 상세 정보를 담을 Column
        Column(
            verticalArrangement = Arrangement.SpaceAround, // 내부 요소 간 간격 설정
            modifier = Modifier
                .padding(horizontal = ExtraSmallPadding) // 좌우 여백 추가
                .height(
                    ArticleCardSize // Column의 높이를 이미지와 동일하게 설정
                )
        ) {
            // 기사 제목 표시
            Text(
                text = article.title, // 기사 제목
                style = MaterialTheme.typography.bodyMedium, // 텍스트 스타일
                color = colorResource(
                    id = R.color.text_title // 텍스트 색상
                ),
                maxLines = 2, // 최대 2줄로 표시
                overflow = TextOverflow.Ellipsis // 텍스트가 길면 줄임표로 표시
            )

            // 기사 source와 게시 시간 표시
            Row(verticalAlignment = Alignment.CenterVertically) { // 요소들을 세로로 가운데 정렬
                // source 이름
                Text(
                    text = article.source.name,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold), // 텍스트 스타일
                    color = colorResource(id = R.color.body) // 텍스트 색상
                )

                Spacer(modifier = Modifier.width(ExtraSmallPadding2)) // source와 시간 간 간격 추가

                // 시간 아이콘
                Icon(painter = painterResource(id = R.drawable.ic_time), contentDescription = null,
                    modifier = Modifier.size(SmallIconSize), // 아이콘 크기 설정
                    tint = colorResource(id = R.color.body) // 아이콘 색상
                )
                Spacer(modifier = Modifier.width(ExtraSmallPadding2)) // 시간 아이콘과 텍스트 간 간격 추가

                // 게시 시간
                Text(
                    text = article.publishedAt, // 게시 시간 텍스트
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold), // 텍스트 스타일
                    color = colorResource(id = R.color.body) // 텍스트 색상
                )}
        }
    }
}

// ArticleCard의 미리보기를 제공하는 함수
@Preview(showBackground = true) // 기본 라이트 모드에서 배경 포함하여 미리보기
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES) // 다크 모드에서 배경 포함하여 미리보기
@Composable
fun ArticleCardPreview() {
    NewsAppTheme { // 앱 테마 적용
        ArticleCard(article = Article(
            author = "",
            content = "",
            description = "",
            publishedAt = "2 hours",
            source = Source(id = "", name = "BBC"),
            title = "Her traint broke down. Herphone died. And then she met her saver in a",
            url = "",
            urlToImage = ""
        )) {

        }
    }
}