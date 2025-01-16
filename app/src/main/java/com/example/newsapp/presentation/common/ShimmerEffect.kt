package com.example.newsapp.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp.R
import com.example.newsapp.presentation.Dimens.ArticleCardSize
import com.example.newsapp.presentation.Dimens.ExtraSmallPadding
import com.example.newsapp.presentation.Dimens.MediumPadding1
import com.example.newsapp.ui.theme.NewsAppTheme

// Modifier의 Shimmer Effect(빛나는 효과)를 추가하는 확장 함수
// 무한 반복 애니메이션을 사용하여 시각적으로 로딩 상태를 표현
fun Modifier.shimmerEffect() = composed {
    // 무한 반복 애니메이션을 기억
    val transition = rememberInfiniteTransition()
    // 애니메이션으로 투명도 변화
    val alpha = transition.animateFloat(
        initialValue = 0.2f, // 투명도의 초기값
        targetValue = 0.9f, // 투명도의 목표값
        animationSpec = infiniteRepeatable( // 무한 반복 애니메이션 설정
            animation = tween(durationMillis = 1000), // 애니메이션 지속 시간
            repeatMode = RepeatMode.Reverse // 애니메이션 반복 모드 (역방향)
        )
    ).value
    // 배경색에 alpha 값을 적용하여 Shimmer Effect 생성
    background(color = colorResource(id = R.color.shimmer).copy(alpha = alpha))
}

// 로딩 상태를 보여주는 Shimmer Effect가 적용된 ArticleCard Composable
@Composable
fun ArticleCardShimmerEffect(
    modifier: Modifier = Modifier
) {
    // Row로 Shimmer Effect 레이아웃 구성
    Row(
        modifier = modifier
    ) {
        // 이미지 영역에 Shimmer Effect 적용
        Box(
            modifier = Modifier
                .size(ArticleCardSize) // 이미지 크기 설정
                .clip(MaterialTheme.shapes.medium) // 모서리를 둥글게 설정
                .shimmerEffect(), // Shimmer Effect 적용
        )

        // 텍스트 영역에 Shimmer Effect 적용
        Column(
            verticalArrangement = Arrangement.SpaceAround, // 요소 간 간격 설정
            modifier = Modifier
                .padding(horizontal = ExtraSmallPadding) // 좌우 여백 추가
                .height(
                    ArticleCardSize // Column의 높이를 이미지와 동일하게 설정
                )
        ) {
            // 제목 영역에 Shimmer Effect 적용
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .padding(horizontal = MediumPadding1)
                    .shimmerEffect(),
            )

            // 하단 텍스트 영역에 Shimmer Effect 적용
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.5f) // 가로를 50%만 채움
                        .height(15.dp)
                        .padding(horizontal = MediumPadding1)
                        .shimmerEffect(),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ArticleCardShimmerEffectPreview() {
    NewsAppTheme {
        ArticleCardShimmerEffect()
    }
}