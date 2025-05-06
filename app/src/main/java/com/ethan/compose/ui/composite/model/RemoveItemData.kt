package com.ethan.compose.ui.composite.model

data class RemoveItemData(
    val category: List<RemoveCategoryData>
)

data class RemoveCategoryData(
    val title: String,
    val detail: List<RemoveDetail>
)

data class RemoveDetail(
    val title: String,
    val isFree: Int,
    val previewUrl: String
)

val removeDataSource = RemoveItemData(
    category = listOf(
        RemoveCategoryData(
            title = "颜色",
            detail = listOf(
                RemoveDetail(
                    title = "Color1",
                    isFree = 1,
                    previewUrl = "https://material.hitpaw.com/static/454d0d40944c6a4838939dcf328b0f61/upload/b30a718c98b4d6fdc4fbf6769cfa581c6.png"
                ),
                RemoveDetail(
                    title = "Color2",
                    isFree = 1,
                    previewUrl = "https://material.hitpaw.com/static/454d0d40944c6a4838939dcf328b0f61/upload/b30a718c98b4d6fdc4fbf6769cfa581c6.png"
                ),
                RemoveDetail(
                    title = "Color3",
                    isFree = 1,
                    previewUrl = "https://material.hitpaw.com/static/454d0d40944c6a4838939dcf328b0f61/upload/b30a718c98b4d6fdc4fbf6769cfa581c6.png"
                ),
                RemoveDetail(
                    title = "Color4",
                    isFree = 1,
                    previewUrl = "https://material.hitpaw.com/static/454d0d40944c6a4838939dcf328b0f61/upload/b30a718c98b4d6fdc4fbf6769cfa581c6.png"
                ),
                RemoveDetail(
                    title = "Color5",
                    isFree = 1,
                    previewUrl = "https://material.hitpaw.com/static/454d0d40944c6a4838939dcf328b0f61/upload/b30a718c98b4d6fdc4fbf6769cfa581c6.png"
                ),
                RemoveDetail(
                    title = "Color6",
                    isFree = 1,
                    previewUrl = "https://material.hitpaw.com/static/454d0d40944c6a4838939dcf328b0f61/upload/b30a718c98b4d6fdc4fbf6769cfa581c6.png"
                ),
                RemoveDetail(
                    title = "Color7",
                    isFree = 1,
                    previewUrl = "https://material.hitpaw.com/static/454d0d40944c6a4838939dcf328b0f61/upload/b30a718c98b4d6fdc4fbf6769cfa581c6.png"
                ),
                RemoveDetail(
                    title = "Color8",
                    isFree = 1,
                    previewUrl = "https://material.hitpaw.com/static/454d0d40944c6a4838939dcf328b0f61/upload/b30a718c98b4d6fdc4fbf6769cfa581c6.png"
                ),
                RemoveDetail(
                    title = "Color9",
                    isFree = 1,
                    previewUrl = "https://material.hitpaw.com/static/454d0d40944c6a4838939dcf328b0f61/upload/b30a718c98b4d6fdc4fbf6769cfa581c6.png"
                ),
                RemoveDetail(
                    title = "Color10",
                    isFree = 1,
                    previewUrl = "https://material.hitpaw.com/static/454d0d40944c6a4838939dcf328b0f61/upload/b30a718c98b4d6fdc4fbf6769cfa581c6.png"
                ),
            )
        ),

        RemoveCategoryData(
            title = "旅行",
            detail = listOf(
                RemoveDetail(
                    title = "Travel1",
                    isFree = 2,
                    previewUrl = "https://material.hitpaw.com/static/5deb7ac911e0946fe07442ce08467b02/upload/5e54eed933461d92b479e3a38d33d1f08.png"
                ),
                RemoveDetail(
                    title = "Travel2",
                    isFree = 2,
                    previewUrl = "https://material.hitpaw.com/static/5deb7ac911e0946fe07442ce08467b02/upload/5e54eed933461d92b479e3a38d33d1f08.png"
                ),
                RemoveDetail(
                    title = "Travel3",
                    isFree = 2,
                    previewUrl = "https://material.hitpaw.com/static/5deb7ac911e0946fe07442ce08467b02/upload/5e54eed933461d92b479e3a38d33d1f08.png"
                ),
                RemoveDetail(
                    title = "Travel4",
                    isFree = 2,
                    previewUrl = "https://material.hitpaw.com/static/5deb7ac911e0946fe07442ce08467b02/upload/5e54eed933461d92b479e3a38d33d1f08.png"
                ),
                RemoveDetail(
                    title = "Travel5",
                    isFree = 2,
                    previewUrl = "https://material.hitpaw.com/static/5deb7ac911e0946fe07442ce08467b02/upload/5e54eed933461d92b479e3a38d33d1f08.png"
                ),
                RemoveDetail(
                    title = "Travel6",
                    isFree = 2,
                    previewUrl = "https://material.hitpaw.com/static/5deb7ac911e0946fe07442ce08467b02/upload/5e54eed933461d92b479e3a38d33d1f08.png"
                ),
                RemoveDetail(
                    title = "Travel7",
                    isFree = 2,
                    previewUrl = "https://material.hitpaw.com/static/5deb7ac911e0946fe07442ce08467b02/upload/5e54eed933461d92b479e3a38d33d1f08.png"
                ),
                RemoveDetail(
                    title = "Travel8",
                    isFree = 2,
                    previewUrl = "https://material.hitpaw.com/static/5deb7ac911e0946fe07442ce08467b02/upload/5e54eed933461d92b479e3a38d33d1f08.png"
                ),
            )
        ),

        RemoveCategoryData(
            title = "生活",
            detail = listOf(
                RemoveDetail(
                    title = "Life Style 1",
                    isFree = 2,
                    previewUrl = "https://material.hitpaw.com/static/7c606ea54bce9e7204941b15787a4998/upload/f7a62b2b2918bc289ee8954c6ca3549c12.png"
                ),
                RemoveDetail(
                    title = "Life Style 2",
                    isFree = 2,
                    previewUrl = "https://material.hitpaw.com/static/7c606ea54bce9e7204941b15787a4998/upload/f7a62b2b2918bc289ee8954c6ca3549c12.png"
                ),
                RemoveDetail(
                    title = "Life Style 3",
                    isFree = 2,
                    previewUrl = "https://material.hitpaw.com/static/7c606ea54bce9e7204941b15787a4998/upload/f7a62b2b2918bc289ee8954c6ca3549c12.png"
                ),
                RemoveDetail(
                    title = "Life Style 4",
                    isFree = 2,
                    previewUrl = "https://material.hitpaw.com/static/7c606ea54bce9e7204941b15787a4998/upload/f7a62b2b2918bc289ee8954c6ca3549c12.png"
                ),
                RemoveDetail(
                    title = "Life Style 5",
                    isFree = 2,
                    previewUrl = "https://material.hitpaw.com/static/7c606ea54bce9e7204941b15787a4998/upload/f7a62b2b2918bc289ee8954c6ca3549c12.png"
                ),
                RemoveDetail(
                    title = "Life Style 6",
                    isFree = 2,
                    previewUrl = "https://material.hitpaw.com/static/7c606ea54bce9e7204941b15787a4998/upload/f7a62b2b2918bc289ee8954c6ca3549c12.png"
                ),
                RemoveDetail(
                    title = "Life Style 7",
                    isFree = 2,
                    previewUrl = "https://material.hitpaw.com/static/7c606ea54bce9e7204941b15787a4998/upload/f7a62b2b2918bc289ee8954c6ca3549c12.png"
                ),
                RemoveDetail(
                    title = "Life Style 8",
                    isFree = 2,
                    previewUrl = "https://material.hitpaw.com/static/7c606ea54bce9e7204941b15787a4998/upload/f7a62b2b2918bc289ee8954c6ca3549c12.png"
                ),
            )
        ),

        RemoveCategoryData(
            title = "节日",
            detail = listOf(
                RemoveDetail(
                    title = "Festival1",
                    isFree = 2,
                    previewUrl = "https://material.hitpaw.com/static/90687c340367c36e2d81871be53f67dc/upload/f97d3e341a1ed168502c4fa0c3ad83ec11.png"
                ),
                RemoveDetail(
                    title = "Festival2",
                    isFree = 2,
                    previewUrl = "https://material.hitpaw.com/static/90687c340367c36e2d81871be53f67dc/upload/f97d3e341a1ed168502c4fa0c3ad83ec11.png"
                ),
                RemoveDetail(
                    title = "Festival3",
                    isFree = 2,
                    previewUrl = "https://material.hitpaw.com/static/90687c340367c36e2d81871be53f67dc/upload/f97d3e341a1ed168502c4fa0c3ad83ec11.png"
                ),
                RemoveDetail(
                    title = "Festival4",
                    isFree = 2,
                    previewUrl = "https://material.hitpaw.com/static/90687c340367c36e2d81871be53f67dc/upload/f97d3e341a1ed168502c4fa0c3ad83ec11.png"
                ),
                RemoveDetail(
                    title = "Festival5",
                    isFree = 2,
                    previewUrl = "https://material.hitpaw.com/static/90687c340367c36e2d81871be53f67dc/upload/f97d3e341a1ed168502c4fa0c3ad83ec11.png"
                ),
                RemoveDetail(
                    title = "Festival6",
                    isFree = 2,
                    previewUrl = "https://material.hitpaw.com/static/90687c340367c36e2d81871be53f67dc/upload/f97d3e341a1ed168502c4fa0c3ad83ec11.png"
                ),
                RemoveDetail(
                    title = "Festival7",
                    isFree = 2,
                    previewUrl = "https://material.hitpaw.com/static/90687c340367c36e2d81871be53f67dc/upload/f97d3e341a1ed168502c4fa0c3ad83ec11.png"
                ),
                RemoveDetail(
                    title = "Festival8",
                    isFree = 2,
                    previewUrl = "https://material.hitpaw.com/static/90687c340367c36e2d81871be53f67dc/upload/f97d3e341a1ed168502c4fa0c3ad83ec11.png"
                ),
                RemoveDetail(
                    title = "Festival9",
                    isFree = 2,
                    previewUrl = "https://material.hitpaw.com/static/90687c340367c36e2d81871be53f67dc/upload/f97d3e341a1ed168502c4fa0c3ad83ec11.png"
                ),
            )
        ),

        RemoveCategoryData(
            title = "季节",
            detail = listOf(
                RemoveDetail(
                    title = "Season1",
                    isFree = 2,
                    previewUrl = "https://material.hitpaw.com/static/db20770a8b02dd110bcd9460a4a316d3/upload/abbee7ce901b1a0987f9a5b69483d9d711.png"
                ),
                RemoveDetail(
                    title = "Season2",
                    isFree = 2,
                    previewUrl = "https://material.hitpaw.com/static/db20770a8b02dd110bcd9460a4a316d3/upload/abbee7ce901b1a0987f9a5b69483d9d711.png"
                ),
                RemoveDetail(
                    title = "Season3",
                    isFree = 2,
                    previewUrl = "https://material.hitpaw.com/static/db20770a8b02dd110bcd9460a4a316d3/upload/abbee7ce901b1a0987f9a5b69483d9d711.png"
                ),
                RemoveDetail(
                    title = "Season4",
                    isFree = 2,
                    previewUrl = "https://material.hitpaw.com/static/db20770a8b02dd110bcd9460a4a316d3/upload/abbee7ce901b1a0987f9a5b69483d9d711.png"
                ),
                RemoveDetail(
                    title = "Season5",
                    isFree = 2,
                    previewUrl = "https://material.hitpaw.com/static/db20770a8b02dd110bcd9460a4a316d3/upload/abbee7ce901b1a0987f9a5b69483d9d711.png"
                ),
                RemoveDetail(
                    title = "Season6",
                    isFree = 2,
                    previewUrl = "https://material.hitpaw.com/static/db20770a8b02dd110bcd9460a4a316d3/upload/abbee7ce901b1a0987f9a5b69483d9d711.png"
                ),
                RemoveDetail(
                    title = "Season7",
                    isFree = 2,
                    previewUrl = "https://material.hitpaw.com/static/db20770a8b02dd110bcd9460a4a316d3/upload/abbee7ce901b1a0987f9a5b69483d9d711.png"
                ),
                RemoveDetail(
                    title = "Season8",
                    isFree = 2,
                    previewUrl = "https://material.hitpaw.com/static/db20770a8b02dd110bcd9460a4a316d3/upload/abbee7ce901b1a0987f9a5b69483d9d711.png"
                ),
            )
        ),
    )
)
