package ca.gbc.comp3074.kenny_john.lab03

import android.hardware.camera2.params.BlackLevelPattern
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Android
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.ui.graphics.shadow.Shadow


@Composable
fun MessageItem(message: Message) {
    Row(
        modifier = Modifier.padding(16.dp).fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(
            imageVector = Icons.Default.Android,
            contentDescription = "Icon Android",
            modifier = Modifier
                .size(75.dp)
                .border(2.dp, Color.Red, CircleShape)
                .padding(20.dp)
                .dropShadow(
                    shape = RectangleShape,
                    shadow = Shadow(
                        radius = 4.dp,
                        color = Color.Gray.copy(alpha = 0.5f),
                        offset = DpOffset(4.dp, 4.dp)
                )


//                    radius = 4.dp,
//                    color = Color.Black.copy(alpha = 0.5f),
//                    offset = DpOffset(4.dp, 4.dp)
                ),
            tint = Color.White,

        )
        Column(
            modifier = Modifier
                .padding(16.dp)
                .padding(16.dp)
        ) {
            Text(
                text = message.user,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = message.content,
                fontSize = 16.sp,
                modifier = Modifier
//                    .clip(RoundedCornerShape(12.dp))
                    .border(2.dp,  Color.Gray)
                    .padding(5.dp)
            )
        }
    }
}



@Composable
fun LazyMessageList(modifier: Modifier, messages: List<Message>) {
    LazyColumn (
        modifier = modifier.padding(16.dp)
            .fillMaxSize()
    ){
        items(messages){
            message ->
            MessageItem(message)
        }
    }
}

@Composable
fun MessageScreen (modifier: Modifier) {
    val context = LocalContext.current
    val messages = remember {
        mutableStateListOf<Message>().apply {
            addAll(loadMessages(context))
        }
    }



    Column(modifier = modifier.fillMaxWidth()){


        LazyMessageList(modifier, messages)
    }
}

@Preview(showBackground = true)
@Composable
fun MessageScreenPreview() {
    MessageScreen(modifier = Modifier)
}