//package com.ethan.compose.ui.composite.view
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxHeight
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.statusBarsPadding
//import androidx.compose.foundation.layout.wrapContentSize
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.LazyListState
//import androidx.compose.foundation.lazy.itemsIndexed
//import androidx.compose.foundation.pager.HorizontalPager
//import androidx.compose.foundation.pager.rememberPagerState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Tab
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.MutableState
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableIntStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.media3.common.MediaItem
//import androidx.media3.exoplayer.ExoPlayer
//import com.ethan.compose.R
//import com.ethan.compose.custom.view.ScrollableTabRow
//import com.ethan.compose.theme.Black20
//import com.ethan.compose.theme.NO_PADDING_TEXT_STYLE
//import com.ethan.compose.theme.Purple9258FA
//import com.ethan.compose.theme.Transparent
//import com.ethan.compose.theme.White
//import com.ethan.compose.theme.White50
//import com.ethan.compose.theme.White60
//import com.ethan.compose.theme.White8
//import com.ethan.compose.utils.antiShakeClick
//import kotlinx.coroutines.launch
//
//@Composable
//@Preview
//fun TabWithHorizontalPager() {
//    val scope = rememberCoroutineScope()
//    var selectedTabIndex by remember { mutableIntStateOf(0) }
//    val pagerState = rememberPagerState(initialPage = 0) { 6 }
//    val pageList = listOf("所有","我的声音","男性","女性","青年","老年")
//
//    Column(modifier = Modifier.fillMaxWidth().height(500.dp).statusBarsPadding()) {
//
//        ScrollableTabRow(
//            selectedTabIndex = selectedTabIndex,
//            edgePadding = 12.dp,
//            containerColor = Transparent,
//            divider = {},
//            indicator = { tabPositions ->
//                if (tabPositions.isNotEmpty()) {
//                    PagerTabIndicator(tabPositions = tabPositions, pagerState = pagerState)
//                }
//            },
//        ) {
//            pageList.forEachIndexed { index, pageName ->
//                Tab(selected = selectedTabIndex == index,
//                    onClick = {
//                        selectedTabIndex = index
//                        scope.launch {
//                            pagerState.animateScrollToPage(index)
//                        }
//                    },
//                    modifier = Modifier
//                        .wrapContentSize()
//                        .padding(horizontal = 8.dp)
//                ) {
//                    Box(modifier = Modifier.padding(start = 4.dp, end = 4.dp, bottom = 6.dp)) {
//                        Text(
//                            text = pageName,
//                            color = if (selectedTabIndex == index) White else White60,
//                            fontSize = 14.sp,
//                            style = NO_PADDING_TEXT_STYLE.copy(fontWeight = if (selectedTabIndex == index) FontWeight.W600 else FontWeight.W400)
//                        )
//                    }
//                }
//            }
//        }
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        HorizontalPager(pagerState, beyondViewportPageCount = 2, userScrollEnabled = false) {
//            when (it) {
//                // All
//                0 -> {
//                    allVoicePagingState?.let { state -> VoiceList(exoPlayer, isPlayer, allListState, selectVoice, state, aiHumanViewModel, "all") }
//                }
//                // My Voice
//                1 -> {
//                    MyVoiceView(exoPlayer, isPlayer, selectVoice)
//                }
//                // Male
//                2 -> {
//                    maleVoicePagingState?.let { state -> VoiceList(exoPlayer, isPlayer, maleListState, selectVoice, state, aiHumanViewModel, "male") }
//                }
//                // Female
//                3 -> {
//                    femaleVoicePagingState?.let { state -> VoiceList(exoPlayer, isPlayer, femaleListState, selectVoice, state, aiHumanViewModel, "female") }
//                }
//                // young
//                4 -> {
//                    youngVoicePagingState?.let { state -> VoiceList(exoPlayer, isPlayer, youngListState, selectVoice, state, aiHumanViewModel, "young") }
//                }
//                // old
//                5 -> {
//                    oldVoicePagingState?.let { state -> VoiceList(exoPlayer, isPlayer, oldListState, selectVoice, state, aiHumanViewModel, "old") }
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun VoiceList(exoPlayer: ExoPlayer, isPlaying: MutableState<Boolean>, listState: LazyListState) {
//    LazyColumn(
//        state = listState,
//        contentPadding = PaddingValues(start = 5.dp, end = 5.dp),
//        verticalArrangement = Arrangement.spacedBy(8.dp), // 添加项间距
//        modifier = Modifier.fillMaxSize()
//    ) {
//        itemsIndexed() { index, voice ->
//            VoiceItemView(
//                exoPlayer = exoPlayer,
//                voice = voice,
//                isPlaying = isPlaying,
//                isSelected = selectVoice.value == voice,
//                index = index,
//                source = source,
//                onclick = {selectVoice.value = it}
//            )
//        }
//    }
//}
//
///** 音频项 */
//@Composable
//fun VoiceItemView(
//    modifier: Modifier = Modifier,
//    exoPlayer: ExoPlayer,
//    voice: SharedVoices,
//    isPlaying: MutableState<Boolean>,
//    isSelected: Boolean,
//    index: Int,
//    source: String = "all",
//    onclick: (voice: SharedVoices) -> Unit = {},
//) {
//    val recordPlayBtn = if (isSelected && isPlaying.value) R.drawable.svg_icon_voice_play else R.drawable.svg_icon_voice_pause
//    val voiceItemImg = when(source) {
//        "my" -> R.mipmap.voice_item_my_img
//        "male" -> R.mipmap.voice_item_male_img
//        "female" -> R.mipmap.voice_item_female_img
//        "young" -> R.mipmap.voice_item_young_img
//        "old" -> R.mipmap.voice_item_old_img
//        "all" -> when (voice.age) {
//            "young" -> R.mipmap.voice_item_young_img
//            "old" -> R.mipmap.voice_item_old_img
//            else -> {
//                when (voice.gender) {
//                    "male" -> R.mipmap.voice_item_male_img
//                    "female" -> R.mipmap.voice_item_female_img
//                    else -> R.mipmap.voice_item_young_img
//                }
//            }
//        }
//        else -> R.mipmap.voice_item_young_img
//    }
//
//    Row(modifier = modifier
//        .fillMaxWidth()
//        .height(80.dp)
//        .border(
//            width = 2.dp,
//            color = if (isSelected) Purple9258FA else Transparent,
//            shape = RoundedCornerShape(12.dp)
//        )
//        .background(color = if (isSelected) Black20 else Transparent, shape = RoundedCornerShape(12.dp))
//        .padding(horizontal = 7.dp)
//        .antiShakeClick {
//            exoPlayer.apply {
//                clearMediaItems()
//                val mediaItem = MediaItem.fromUri(voice.pre_view_url)
//                addMediaItem(index, mediaItem)
//            }
//            exoPlayer.prepare()
//
//            if (isSelected) {
//                exoPlayer.playWhenReady = !exoPlayer.playWhenReady
//                isPlaying.value = exoPlayer.playWhenReady
//            } else {
//                exoPlayer.playWhenReady = true
//                isPlaying.value = true
//            }
//
//            onclick.invoke(voice)
//        },
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Box {
//            Image(painter = painterResource(voiceItemImg), contentScale = ContentScale.Crop, contentDescription = null, modifier = Modifier
//                .size(64.dp)
//                .clip(RoundedCornerShape(8.dp))
//            )
//
//            Image(painter = painterResource(recordPlayBtn), contentDescription = null, modifier = Modifier.align(Alignment.Center))
//        }
//
//        Column(modifier = Modifier
//            .weight(1f)
//            .fillMaxHeight()
//            .padding(start = 12.dp, end = 8.dp),
//            verticalArrangement = Arrangement.Center
//        ) {
//            Text(
//                text = voice.name,
//                color = White,
//                fontSize = 14.sp,
//                style = NO_PADDING_TEXT_STYLE.copy(fontWeight = FontWeight.W500)
//            )
//            Spacer(modifier = Modifier.height(10.dp))
//            Row(modifier = Modifier.wrapContentSize()) {
//                Text(
//                    text = voice.age,
//                    color = White50,
//                    fontSize = 10.sp,
//                    style = NO_PADDING_TEXT_STYLE.copy(fontWeight = FontWeight.W400),
//                    modifier = Modifier
//                        .padding(end = 8.dp)
//                        .background(color = White8, shape = RoundedCornerShape(24.dp))
//                        .padding(horizontal = 8.dp, vertical = 4.dp)
//                )
//
//                Text(
//                    text = voice.gender,
//                    color = White50,
//                    fontSize = 10.sp,
//                    style = NO_PADDING_TEXT_STYLE.copy(fontWeight = FontWeight.W400),
//                    modifier = Modifier
//                        .padding(end = 8.dp)
//                        .background(color = White8, shape = RoundedCornerShape(24.dp))
//                        .padding(horizontal = 8.dp, vertical = 4.dp)
//                )
//            }
//        }
//    }
//}