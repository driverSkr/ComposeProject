package com.ethan.compose.ui.custom.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ethan.compose.custom.view.StatusBarsView
import com.ethan.compose.custom.widget.rememberLoginDialog
import com.ethan.compose.theme.Black
import com.ethan.compose.theme.White
import com.ethan.compose.ui.main.page.ItemView
import com.ethan.compose.utils.antiShakeClick
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun CustomPage() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dialog = rememberLoginDialog(context)

    Column(modifier = Modifier.fillMaxSize()) {
        StatusBarsView("自定义组件")

        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = White,
                contentColor = Black,
                disabledContainerColor = White,
                disabledContentColor = Black
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.0.dp),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 12.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                ItemView(
                    "底部登陆弹窗",
                    isNotEnd = false,
                    isNeedEndIcon = false,
                    modifier = Modifier.antiShakeClick {
                        scope.launch(Dispatchers.Default) {
                            dialog.show()
                        }
                    }
                )
            }
        }
    }
}