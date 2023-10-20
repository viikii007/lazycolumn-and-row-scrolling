package com.task.task.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.task.task.R
import com.task.task.api.DataOrException
import com.task.task.api.model.Area
import com.task.task.getdataviewmodeObi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

data class DataItem(val title: String, val description: String)

@Composable
fun ScrollingTitleDescriptionList(tempdata: List<DataItem>) {
    val profileData = produceState(initialValue = DataOrException(loading = true))
    {
        value = getdataviewmodeObi!!.GetDataInfo()!!
    }.value

    if (profileData.loading == true) {
        CustomeProgressbar()
    } else {
        if (profileData.data != null) {
            val data = profileData.data!!.data.Vendors_detail.areas

            //  val data=tempdata

            var selectedTitle by remember { mutableStateOf(data.first()) }
            var selectedDescription by remember { mutableStateOf(data.first()) }


            val lazyListState = rememberLazyListState()
            val lazyrowstate = rememberLazyListState()
            val scope = rememberCoroutineScope()


            Column {
                val indeq = remember { derivedStateOf { lazyListState.firstVisibleItemIndex } }

                LaunchedEffect(key1 = lazyListState.isScrollInProgress && !lazyrowstate.isScrollInProgress)
                {
                    scope.launch {
                        lazyrowstate.animateScrollAndCentralizeItem(indeq.value, this)
                    }
                }

                LazyRow(
                    state = lazyrowstate,
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(30.dp)
                ) {

                    items(data) { item ->

                        Column(
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = Alignment.CenterHorizontally
                        )
                        {

                            Tab(
                                selected = selectedTitle == item,
                                onClick = {
                                    selectedTitle = item
                                    scope.launch {
                                        lazyListState.animateScrollToItem(data.indexOf(item))
                                        lazyrowstate.animateScrollAndCentralizeItem(
                                            data.indexOf(
                                                item
                                            ), this
                                        )

                                    }
                                },
                                text = {
                                    Text(
                                        text = item.name_en,
                                        maxLines = 1,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold
                                    )

                                },
                                modifier = Modifier
                                    .padding(horizontal = 16.dp)
                                    .height(48.dp),
                                selectedContentColor = if (data.indexOf(
                                        item
                                    ) == indeq.value
                                ) Color.Blue else Color.Black
                            )

                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // LazyColumn with vertical scrolling descriptions
                LazyColumn(
                    state = lazyListState,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(50.dp)
                ) {

                    items(data) { item ->

                        ItemListView(
                            item, modifier = Modifier
                                .clickable(onClick = {
                                    selectedDescription = item
                                    // Scroll to the selected title
                                    scope.launch {
                                        lazyrowstate.animateScrollAndCentralizeItem(
                                            data.indexOf(item),
                                            this
                                        )
                                        lazyListState.animateScrollToItem(data.indexOf(item))

                                    }
                                })
                        )
                    }
                }
            }
        }

    }
}


@Composable
fun ItemListView(title: Area, modifier: Modifier)
{
    Column {
        Row(modifier = modifier.padding(16.dp))
        {
            Column(modifier = Modifier.weight(2f), verticalArrangement = Arrangement.Center) {
                Text(
                    text = title.name_en,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Left
                )
                Text(
                    text = title.area_id,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Left
                )
                Text(
                    text = title.vendor_area_id,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Left
                )
            }

            Row(modifier = Modifier.weight(1f)) {
                Image(
                    painter = painterResource(id = R.drawable.icecream),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
            }
        }
        Divider(modifier=Modifier.fillMaxWidth(), thickness = 2.dp, color = Color.LightGray)

    }

}


@Composable
fun CustomeProgressbar()
{
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        CircularProgressIndicator()
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp(tempdata: List<DataItem>) {

    Scaffold() {
        ScrollingTitleDescriptionList(tempdata)
    }
}

@Composable
fun MainContent() {
    val data = List(100) { index ->
        DataItem("Title $index", "Description for Title $index")
    }
    MyApp(data)
}


fun LazyListState.animateScrollAndCentralizeItem(index: Int, scope: CoroutineScope) {
    val itemInfo = this.layoutInfo.visibleItemsInfo.firstOrNull { it.index == index }
    scope.launch {
        if (itemInfo != null) {
            val center = this@animateScrollAndCentralizeItem.layoutInfo.viewportEndOffset / 2
            val childCenter = itemInfo.offset + itemInfo.size / 2
            this@animateScrollAndCentralizeItem.animateScrollBy((childCenter - center).toFloat())
        } else {
            this@animateScrollAndCentralizeItem.animateScrollToItem(index)
        }
    }
}