package com.example.a20230710_chinguyen_nycschools.mvvm.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a20230710_chinguyen_nycschools.R
import com.example.a20230710_chinguyen_nycschools.mvvm.viewmodel.SchoolInfoViewModel
import com.example.a20230710_chinguyen_nycschools.mvvm.viewmodel.SchoolListViewModel
import com.example.a20230710_chinguyen_nycschools.ui.theme.DividerGrey
import com.example.a20230710_chinguyen_nycschools.ui.theme.ScreenBackground
import com.example.a20230710_chinguyen_nycschools.ui.theme.ToolbarColor
import com.example.a20230710_chinguyen_nycschools.ui.theme.White
import io.reactivex.Single

@Composable
fun SchoolListScreen(viewModel: SchoolListViewModel) {
    val schoolList = viewModel.schoolList.observeAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "High schools in NYC") },
                contentColor = Color.White,
                backgroundColor = ToolbarColor
            )
        },
        content = { it ->
            LazyColumn(
                modifier = Modifier
                    .background(ScreenBackground)
                    .padding(
                        start = dimensionResource(R.dimen.side_paddings),
                        end = dimensionResource(R.dimen.side_paddings)
                    )
            ) {
                schoolList.value?.let { list ->
                    itemsIndexed(list) { index, item ->
                        SchoolListItem(
                            index = index,
                            dbn = item.dbn,
                            name = item.name,
                            neighborhood = item.neighborhood,
                            address = item.address,
                            phone = item.phone,
                            website = item.website,
                            totalStudent = item.totalStudent
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun SchoolListItem(
    index: Int,
    dbn: String,
    name: String,
    neighborhood: String,
    address: String,
    phone: String,
    website: String,
    totalStudent: String
) {
    var displayingInfo by remember { mutableStateOf(false) }
    val schoolInfoViewModel: SchoolInfoViewModel = viewModel()
    val schoolInfo = schoolInfoViewModel.schoolInfo.observeAsState()

    Card(
        modifier = Modifier.padding(bottom = dimensionResource(R.dimen.dimen_8dp))
    ) {
        Column() {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(R.dimen.dimen_8dp))
                        .background(White)
                        .weight(1f)
                ) {
                    Text(name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Spacer(
                        modifier = Modifier
                            .height(dimensionResource(R.dimen.dimen_1dp))
                            .fillMaxWidth()
                            .background(Color.Black)
                    )
                    Text("Neighborhood: $neighborhood")
                    Text("Add: ${address.substringBefore("(")}")
                    Text("Tel: $phone")
                    Text("Website: $website")
                    Text("Student: $totalStudent")
                }

                Icon(
                    modifier = Modifier
                        .padding(end = dimensionResource(id = R.dimen.dimen_8dp))
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = {
                                displayingInfo = !displayingInfo
                                schoolInfoViewModel.getSchoolInfo(dbn)
                            }),
                    painter = painterResource(id = if (displayingInfo) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down),
                    tint = Color.Black,
                    contentDescription = ""
                )
            }

            if (displayingInfo) {
                Spacer(
                    modifier = Modifier
                        .height(dimensionResource(id = R.dimen.dimen_1dp))
                        .padding(
                            start = dimensionResource(id = R.dimen.dimen_10dp),
                            end = dimensionResource(id = R.dimen.dimen_10dp)
                        )
                        .fillMaxWidth()
                        .background(DividerGrey)
                )
                schoolInfo.value?.let { schoolInfoData ->
                    SchoolInfoLayout(
                        numberOfTestTakers = schoolInfoData[index].testTakerCount,
                        mathAvg = schoolInfoData[index].satMathAverage,
                        readingAvg = schoolInfoData[index].satReadingAverage,
                        writingAvg = schoolInfoData[index].satReadingAverage
                    )
                }
            }
        }
    }
}

@Composable
fun SchoolInfoLayout(
    numberOfTestTakers: String,
    mathAvg: String,
    readingAvg: String,
    writingAvg: String
) {
    Column(
        Modifier
            .padding(dimensionResource(R.dimen.dimen_8dp))
            .fillMaxWidth()
            .wrapContentHeight()
            .background(White)
    ) {
        Spacer(
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.dimen_1dp))
                .background(DividerGrey)
        )

        Text("Number of test takers: $numberOfTestTakers")
        Text("SAT Math average: $mathAvg")
        Text("SAT Reading average: $readingAvg")
        Text("SAT Writing average: $writingAvg")
    }
}