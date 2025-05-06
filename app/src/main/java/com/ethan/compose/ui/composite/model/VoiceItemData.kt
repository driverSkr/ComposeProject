package com.ethan.compose.ui.composite.model

data class VoiceItemDetail(
    val name: String,
    val type1: String,
    val type2: String
)

data class VoiceCategoryData(
    val title: String,
    val detail: List<VoiceItemDetail>
)

data class VoiceItemList(
    val category: List<VoiceCategoryData>
)

val voiceDataSource = VoiceItemList(
    category = listOf(
        VoiceCategoryData(
            title = "所有",
            detail = listOf(
                VoiceItemDetail(name = "Hope - upbeat and clear", type1 = "young", type2 = "female"),
                VoiceItemDetail(name = "Hope - upbeat and clear", type1 = "young", type2 = "female"),
                VoiceItemDetail(name = "Hope - upbeat and clear", type1 = "young", type2 = "female"),
                VoiceItemDetail(name = "Hope - upbeat and clear", type1 = "young", type2 = "female"),
                VoiceItemDetail(name = "Hope - upbeat and clear", type1 = "young", type2 = "female"),
                VoiceItemDetail(name = "Hope - upbeat and clear", type1 = "young", type2 = "female"),
                VoiceItemDetail(name = "Hope - upbeat and clear", type1 = "young", type2 = "female"),
                VoiceItemDetail(name = "Hope - upbeat and clear", type1 = "young", type2 = "female"),
                VoiceItemDetail(name = "Hope - upbeat and clear", type1 = "young", type2 = "female"),
                VoiceItemDetail(name = "Hope - upbeat and clear", type1 = "young", type2 = "female")
            )
        ),
        VoiceCategoryData(
            title = "我的声音",
            detail = listOf(
                VoiceItemDetail(name = "黑色柳丁", type1 = "young", type2 = "male"),
                VoiceItemDetail(name = "黑色柳丁", type1 = "young", type2 = "male"),
                VoiceItemDetail(name = "黑色柳丁", type1 = "young", type2 = "male"),
                VoiceItemDetail(name = "黑色柳丁", type1 = "young", type2 = "male"),
                VoiceItemDetail(name = "黑色柳丁", type1 = "young", type2 = "male"),
                VoiceItemDetail(name = "黑色柳丁", type1 = "young", type2 = "male"),
                VoiceItemDetail(name = "黑色柳丁", type1 = "young", type2 = "male"),
                VoiceItemDetail(name = "黑色柳丁", type1 = "young", type2 = "male"),
                VoiceItemDetail(name = "黑色柳丁", type1 = "young", type2 = "male"),
                VoiceItemDetail(name = "黑色柳丁", type1 = "young", type2 = "male")
            )
        ),
        VoiceCategoryData(
            title = "男性",
            detail = listOf(
                VoiceItemDetail(name = "Russell - Dramatic British TV", type1 = "middle_age", type2 = "male"),
                VoiceItemDetail(name = "Russell - Dramatic British TV", type1 = "middle_age", type2 = "male"),
                VoiceItemDetail(name = "Russell - Dramatic British TV", type1 = "middle_age", type2 = "male"),
                VoiceItemDetail(name = "Russell - Dramatic British TV", type1 = "middle_age", type2 = "male"),
                VoiceItemDetail(name = "Russell - Dramatic British TV", type1 = "middle_age", type2 = "male"),
                VoiceItemDetail(name = "Russell - Dramatic British TV", type1 = "middle_age", type2 = "male"),
                VoiceItemDetail(name = "Russell - Dramatic British TV", type1 = "middle_age", type2 = "male"),
                VoiceItemDetail(name = "Russell - Dramatic British TV", type1 = "middle_age", type2 = "male"),
                VoiceItemDetail(name = "Russell - Dramatic British TV", type1 = "middle_age", type2 = "male"),
                VoiceItemDetail(name = "Russell - Dramatic British TV", type1 = "middle_age", type2 = "male"),
                VoiceItemDetail(name = "Russell - Dramatic British TV", type1 = "middle_age", type2 = "male"),
            )
        ),
        VoiceCategoryData(
            title = "女性",
            detail = listOf(
                VoiceItemDetail(name = "Russell - Dramatic British TV", type1 = "middle_age", type2 = "male"),
                VoiceItemDetail(name = "Russell - Dramatic British TV", type1 = "middle_age", type2 = "male"),
                VoiceItemDetail(name = "Russell - Dramatic British TV", type1 = "middle_age", type2 = "male"),
                VoiceItemDetail(name = "Russell - Dramatic British TV", type1 = "middle_age", type2 = "male"),
                VoiceItemDetail(name = "Russell - Dramatic British TV", type1 = "middle_age", type2 = "male"),
                VoiceItemDetail(name = "Russell - Dramatic British TV", type1 = "middle_age", type2 = "male"),
                VoiceItemDetail(name = "Russell - Dramatic British TV", type1 = "middle_age", type2 = "male"),
                VoiceItemDetail(name = "Russell - Dramatic British TV", type1 = "middle_age", type2 = "male"),
                VoiceItemDetail(name = "Russell - Dramatic British TV", type1 = "middle_age", type2 = "male"),
                VoiceItemDetail(name = "Russell - Dramatic British TV", type1 = "middle_age", type2 = "male"),
                VoiceItemDetail(name = "Russell - Dramatic British TV", type1 = "middle_age", type2 = "male"),
            )
        ),
        VoiceCategoryData(
            title = "青年",
            detail = listOf(
                VoiceItemDetail(name = "Russell - Dramatic British TV", type1 = "middle_age", type2 = "male"),
                VoiceItemDetail(name = "Russell - Dramatic British TV", type1 = "middle_age", type2 = "male"),
                VoiceItemDetail(name = "Russell - Dramatic British TV", type1 = "middle_age", type2 = "male"),
                VoiceItemDetail(name = "Russell - Dramatic British TV", type1 = "middle_age", type2 = "male"),
                VoiceItemDetail(name = "Russell - Dramatic British TV", type1 = "middle_age", type2 = "male"),
                VoiceItemDetail(name = "Russell - Dramatic British TV", type1 = "middle_age", type2 = "male"),
                VoiceItemDetail(name = "Russell - Dramatic British TV", type1 = "middle_age", type2 = "male"),
                VoiceItemDetail(name = "Russell - Dramatic British TV", type1 = "middle_age", type2 = "male"),
                VoiceItemDetail(name = "Russell - Dramatic British TV", type1 = "middle_age", type2 = "male"),
                VoiceItemDetail(name = "Russell - Dramatic British TV", type1 = "middle_age", type2 = "male"),
                VoiceItemDetail(name = "Russell - Dramatic British TV", type1 = "middle_age", type2 = "male"),
            )
        ),
        VoiceCategoryData(
            title = "老年",
            detail = listOf(
                VoiceItemDetail(name = "Russell - Dramatic British TV", type1 = "middle_age", type2 = "male"),
                VoiceItemDetail(name = "Russell - Dramatic British TV", type1 = "middle_age", type2 = "male"),
                VoiceItemDetail(name = "Russell - Dramatic British TV", type1 = "middle_age", type2 = "male"),
                VoiceItemDetail(name = "Russell - Dramatic British TV", type1 = "middle_age", type2 = "male"),
                VoiceItemDetail(name = "Russell - Dramatic British TV", type1 = "middle_age", type2 = "male"),
                VoiceItemDetail(name = "Russell - Dramatic British TV", type1 = "middle_age", type2 = "male"),
                VoiceItemDetail(name = "Russell - Dramatic British TV", type1 = "middle_age", type2 = "male"),
                VoiceItemDetail(name = "Russell - Dramatic British TV", type1 = "middle_age", type2 = "male"),
                VoiceItemDetail(name = "Russell - Dramatic British TV", type1 = "middle_age", type2 = "male"),
                VoiceItemDetail(name = "Russell - Dramatic British TV", type1 = "middle_age", type2 = "male"),
                VoiceItemDetail(name = "Russell - Dramatic British TV", type1 = "middle_age", type2 = "male"),
            )
        )
    )
)
