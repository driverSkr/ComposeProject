package com.ethan.compose.work

data class TextItem(
    val id: Int,
    val content: String,
    val tabId: Int // 添加一个字段来标识属于哪个 Tab
)

data class TabData(
    val title: String,
    val textItems: List<TextItem>,
    val id: Int // 添加一个唯一标识符
)

val tabDataSource = listOf(
    TabData(
        title = "Tab1",
        textItems = listOf(
            TextItem(1, "Tab1-1", 1),
            TextItem(2, "Tab1-2", 1),
            TextItem(3, "Tab1-3", 1),
            TextItem(4, "Tab1-4", 1),
            TextItem(5, "Tab1-5", 1),
            TextItem(6, "Tab1-6", 1),
            TextItem(7, "Tab1-7", 1),

        ),
        id = 1
    ),
    TabData(
        title = "Tab2",
        textItems = listOf(
            TextItem(8, "Tab2-1", 2),
            TextItem(9, "Tab2-2", 2),
            TextItem(10, "Tab2-3", 2),
            TextItem(11, "Tab2-4", 2),
            TextItem(12, "Tab2-5", 2),
            TextItem(13, "Tab2-6", 2),
            TextItem(14, "Tab2-7", 2),

        ),
        id = 2
    ),
    TabData(
        title = "Tab3",
        textItems = listOf(
            TextItem(15, "Tab3-1", 3),
            TextItem(16, "Tab3-2", 3),
            TextItem(17, "Tab3-3", 3),
            TextItem(18, "Tab3-4", 3),
            TextItem(19, "Tab3-5", 3),
            TextItem(20, "Tab3-6", 3),
            TextItem(21, "Tab3-7", 3),

            ),
        id = 3
    ),
    TabData(
        title = "Tab4",
        textItems = listOf(
            TextItem(22, "Tab4-1", 4),
            TextItem(23, "Tab4-2", 4),
            TextItem(24, "Tab4-3", 4),
            TextItem(25, "Tab4-4", 4),
            TextItem(26, "Tab4-5", 4),
            TextItem(27, "Tab4-6", 4),
            TextItem(28, "Tab4-7", 4),

            ),
        id = 4
    ),
    TabData(
        title = "Tab5",
        textItems = listOf(
            TextItem(29, "Tab5-1", 5),
            TextItem(30, "Tab5-2", 5),
            TextItem(31, "Tab5-3", 5),
            TextItem(32, "Tab5-4", 5),
            TextItem(33, "Tab5-5", 5),
            TextItem(34, "Tab5-6", 5),
            TextItem(35, "Tab5-7", 5),

            ),
        id = 5
    ),
    TabData(
        title = "Tab6",
        textItems = listOf(
            TextItem(36, "Tab6-1", 6),
            TextItem(37, "Tab6-2", 6),
            TextItem(38, "Tab6-3", 6),
            TextItem(39, "Tab6-4", 6),
            TextItem(40, "Tab6-5", 6),
            TextItem(41, "Tab6-6", 6),
            TextItem(42, "Tab6-7", 6),

            ),
        id = 6
    ),
    TabData(
        title = "Tab7",
        textItems = listOf(
            TextItem(43, "Tab7-1", 7),
            TextItem(44, "Tab7-2", 7),
            TextItem(45, "Tab7-3", 7),
            TextItem(46, "Tab7-4", 7),
            TextItem(47, "Tab7-5", 7),
            TextItem(48, "Tab7-6", 7),
            TextItem(49, "Tab7-7", 7),

            ),
        id = 7
    ),
    TabData(
        title = "Tab8",
        textItems = listOf(
            TextItem(50, "Tab8-1", 8),
            TextItem(51, "Tab8-2", 8),
            TextItem(52, "Tab8-3", 8),
            TextItem(53, "Tab8-4", 8),
            TextItem(54, "Tab8-5", 8),
            TextItem(55, "Tab8-6", 8),
            TextItem(56, "Tab8-7", 8),

            ),
        id = 8
    ),
    TabData(
        title = "Tab9",
        textItems = listOf(
            TextItem(57, "Tab9-1", 9),
            TextItem(58, "Tab9-2", 9),
            TextItem(59, "Tab9-3", 9),
            TextItem(60, "Tab9-4", 9),
            TextItem(61, "Tab9-5", 9),
            TextItem(62, "Tab9-6", 9),
            TextItem(63, "Tab9-7", 9),

            ),
        id = 9
    ),
)