package wolf.north.fbwapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import wolf.north.fbwapp.ui.theme.FBWAppTheme

class HomeScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FBWAppTheme {
                HomeScreenContent(
                    navController = rememberNavController(),
                    viewModel = UserInfoViewModel(),
                    secViewModel = WorkoutViewModel()
                )
            }
        }
    }
}

// *TODO* Głowny ekran - wybrany plan ma się wyświetlać, i na dole dodać procent wykonania (np skończony w 73%)
// *TODO* Implementacja ekranu ćwiczeń, Nazwa ćwiczenia, obraz/gif, ilość powtórzeń, wskazówki odnosnie techniki
// i na dole dwa przyciski, mam problem i następne ćwiczenie
// *TODO* Zakładka (ekran) podsumowania, będzie tam wykres odnośnie skali tygodnia ile wykonaliśmy ćwiczeń
// *TODO* można też dodać w ekranie z inputami ile dni w tygodniu chcemy ćwiczyć
// *TODO* Tą skurwiałą nawigacje między ekrananami, dolny pasek i odpowiednie ikony dać
//*TODO* zrobić theme, dwu kolorowy, czcionki itp
// *TODO* zrobić dokumentacje, commit na gita i lecimy po 3.0


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(
    navController: NavHostController,
    viewModel: UserInfoViewModel,
    secViewModel: WorkoutViewModel
) {
    //List of strings that will represent our categories on main screen
    val workoutCategories = listOf("Full Body", "Stretching", "Chest", "Triceps", "Legs")
    val userName = viewModel.userName
    val currentPlan = secViewModel.currentPlan
    FBWAppTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "FBW App")
                    },
                    modifier = Modifier
                        .padding(18.dp),
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
                                    if (viewModel.userName.isNotEmpty()) append(userName) else append(
                                        "Guest!"
                                    )
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
                                Icon(imageVector = Icons.Filled.Home, contentDescription = null)
                            },
                            selectedContentColor = Color.White
                        )
                        //Bottom Bar Icon #2
                        BottomNavigationItem(
                            selected = false,
                            onClick = { navController.navigate("exercises_list") },
                            icon = {
                                Icon(
                                    imageVector = Icons.Outlined.Menu,
                                    contentDescription = null
                                )
                            },
                            unselectedContentColor = Color.Gray
                        )
                        //Bottom Bar Icon #3
                        BottomNavigationItem(
                            selected = false,
                            onClick = { navController.navigate("workout_plans") },
                            icon = {
                                Icon(
                                    imageVector = Icons.Outlined.Favorite,
                                    contentDescription = null
                                )
                            },
                            unselectedContentColor = Color.Gray
                        )
                        //Bottom Bar Icon #4
                        BottomNavigationItem(
                            selected = false,
                            onClick = { /*TODO*/ },
                            icon = {
                                Icon(
                                    imageVector = Icons.Outlined.Person,
                                    contentDescription = null
                                )
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

//                    LazyRow(
//                        horizontalArrangement = Arrangement.spacedBy(12.dp)
//                    ) {
//                        items(workoutCategories) { workout ->
//                            WorkoutTypeComposable(workoutType = workout)
//                        }
//                    }

                    Row(
                        modifier = Modifier.padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        TextButton(
                            onClick = { navController.navigate("workout_plans") },
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.textButtonColors(containerColor = Color.Black),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Wybierz Plan", color = Color.White)
                        }

                        TextButton(
                            onClick = { navController.navigate("exercises_list") },
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.textButtonColors(containerColor = Color.Black),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Lista ćwiczeń", color = Color.White)
                        }
                    }



                    Spacer(modifier = Modifier.size(30.dp))

                    if (currentPlan != null) {
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 20.dp)
                                .clip(RoundedCornerShape(10))
                                .background(Color.LightGray)

                        ) {
                            Column(
                                modifier = Modifier.padding(
                                    horizontal = 20.dp,
                                    vertical = 22.dp
                                )
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    if (currentPlan != null) {
                                        Text(
                                            text = currentPlan.name,
                                            color = Color.Black,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 32.sp
                                        )
                                    }

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
                                            painter = painterResource(id = currentPlan.imageResId),
                                            contentDescription = null,
                                            modifier = Modifier.size(200.dp)
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
                                        text = "Duration: ${currentPlan.duration}",
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
                                        Row(
                                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
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
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "No plan selected, click to display FBW training plan list!",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                        TextButton(
                            onClick = { navController.navigate("workout_plans") },
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.textButtonColors(containerColor = Color.Black),
                        ) {
                            Text("Wybierz Plan", color = Color.White)
                        }
                    }
                }

            }
        )
    }
}

//@Composable
//fun WorkoutTypeComposable(workoutType: String) {
//    //observable state for isSelected category
//    var isSelected by remember { mutableStateOf(false) }
//    //Background color depending if item is selected or no
//    val backgroundColor = if (isSelected) Color.Black else Color.White
//    //Text color depending if item is selected or no
//    val textColor = if (isSelected) Color.White else Color.Black
//
//    OutlinedButton(
//        shape = RoundedCornerShape(20.dp),
//        contentPadding = PaddingValues(6.dp),
//        border = BorderStroke(width = 1.dp, color = Color.Gray),
//        onClick = { isSelected = !isSelected },
//        colors = ButtonDefaults.textButtonColors(containerColor = backgroundColor)
//    ) {
//        Text(text = workoutType, color = textColor, fontSize = 18.sp)
//
//    }
//}

@Composable
@Preview(showSystemUi = true)
fun AppPreview() {
    HomeScreenContent(
        navController = rememberNavController(),
        viewModel = UserInfoViewModel(),
        secViewModel = WorkoutViewModel()
    )
}
