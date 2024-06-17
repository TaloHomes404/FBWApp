package wolf.north.fbwapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import wolf.north.fbwapp.ui.theme.FBWAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

        }
    }
}



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultPreview(viewModel: UserInfoViewModel) {
    //List of strings that will represent our categories on main screen
    val workoutCategories = listOf("Full Body", "Stretching", "Chest", "Triceps", "Legs")
    val userName = viewModel.userName
    FBWAppTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "FBW App")
                    },
                    modifier = Modifier
                        .padding(18.dp)
                        .background(MaterialTheme.colorScheme.background),
                    {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(65.dp)
                                    .clip(CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.randomperson),
                                    contentDescription = null
                                )
                            }

                            Text(text = buildAnnotatedString {
                                append("Hello, ")
                                withStyle(
                                    SpanStyle(
                                        color = Color.Black, fontWeight = FontWeight.Bold,
                                        fontSize = 28.sp
                                    )
                                ) {
                                    if(userName.isNotEmpty()) append(userName) else append("Guest!")
                                }
                            }, modifier = Modifier.padding(start = 10.dp))
                            Spacer(modifier = Modifier.weight(1f))
                            BadgedBox(badge = {
                                Badge(
                                    Modifier
                                        .clip(CircleShape)
                                        .background(Color.Red)
                                        .align(Alignment.BottomEnd)
                                )
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.Notifications,
                                    contentDescription = null,
                                    tint = Color.Black
                                )
                            }
                        }
                    })

            },
            bottomBar = {
                BottomAppBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
                    containerColor = Color.Black,
                    contentColor = Color.Gray,
                    tonalElevation = 5.dp,
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        //Bottom Bar Icon #1
                        BottomNavigationItem(
                            selected = true,
                            onClick = { /*TODO*/ },
                            icon = {
                                Icon(imageVector = Icons.Filled.Person, contentDescription = null)
                            },
                            selectedContentColor = Color.White
                        )
                        //Bottom Bar Icon #2
                        BottomNavigationItem(
                            selected = false,
                            onClick = { /*TODO*/ },
                            icon = {
                                Icon(imageVector = Icons.Outlined.Person, contentDescription = null)
                            },
                            unselectedContentColor = Color.Gray
                        )
                        //Bottom Bar Icon #3
                        BottomNavigationItem(
                            selected = false,
                            onClick = { /*TODO*/ },
                            icon = {
                                Icon(imageVector = Icons.Outlined.Person, contentDescription = null)
                            },
                            unselectedContentColor = Color.Gray
                        )
                        //Bottom Bar Icon #4
                        BottomNavigationItem(
                            selected = false,
                            onClick = { /*TODO*/ },
                            icon = {
                                Icon(imageVector = Icons.Outlined.Person, contentDescription = null)
                            },
                            unselectedContentColor = Color.Gray
                        )
                    }
                }
            },
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it),
                ) {
                    //Spacer between topbar and our content (lazy row with workout cattegories)
                    Spacer(modifier = Modifier.size(15.dp))

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(workoutCategories) { workout ->
                            WorkoutTypeComposable(workoutType = workout)
                        }
                    }

                    Spacer(modifier = Modifier.size(30.dp))

                    Box(
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .clip(RoundedCornerShape(10))
                            .background(Color.LightGray)
                    ) {
                        Column(
                            modifier = Modifier.padding(horizontal = 20.dp, vertical = 22.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Complete\nTriceps Workout",
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 32.sp
                                )
                                
                            }

                        Spacer(modifier = Modifier.size(15.dp))

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(15.dp))
                                .background(Color.White)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Spacer(modifier = Modifier.weight(0.5f))

                                Image(
                                    painter = painterResource(id = R.drawable.triceps_parts),
                                    contentDescription = null, modifier = Modifier.size(200.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.size(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,

                            ) {
                            Icon(
                                imageVector = Icons.Outlined.Menu,
                                contentDescription = null,
                                tint = Color.Black
                            )

                            Text(
                                text = "30 minutes",
                                fontSize = 18.sp,
                                color = Color.Black,
                                modifier = Modifier.padding(start = 5.dp)
                            )

                            Spacer(modifier = Modifier.weight(1f))

                            TextButton(
                                onClick = { /*TODO*/ },
                                colors = ButtonDefaults.textButtonColors(
                                    containerColor = Color.Transparent
                                )
                            ) {
                                Row(horizontalArrangement = Arrangement.spacedBy(5.dp),
                                    verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = "Begin",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black
                                    )
                                    Icon(
                                        imageVector = Icons.Filled.PlayArrow,
                                        contentDescription = null,
                                        tint = Color.Black
                                    )
                                }

                            }
                        }

                    }
                }
                }
            }
        )
    }
}

@Composable
fun WorkoutTypeComposable(workoutType: String) {
    //observable state for isSelected category
    var isSelected by remember { mutableStateOf(false) }
    //Background color depending if item is selected or no
    val backgroundColor = if (isSelected) Color.Black else Color.White
    //Text color depending if item is selected or no
    val textColor = if (isSelected) Color.White else Color.Black

    OutlinedButton(
        shape = RoundedCornerShape(20.dp),
        contentPadding = PaddingValues(6.dp),
        border = BorderStroke(width = 1.dp, color = Color.Gray),
        onClick = { isSelected = !isSelected },
        colors = ButtonDefaults.textButtonColors(containerColor = backgroundColor)
    ) {
        Text(text = workoutType, color = textColor, fontSize = 18.sp)

    }
}

@Composable
@Preview(showSystemUi = true)
fun AppPreview() {
    DefaultPreview(viewModel = UserInfoViewModel())
}