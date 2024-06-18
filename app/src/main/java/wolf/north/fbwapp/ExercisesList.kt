package wolf.north.fbwapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class ExercisesList : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExercisesListScreen(viewModel: WorkoutViewModel, navController: NavHostController) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                        Spacer(modifier = Modifier.width(25.dp))
                        Text(text = "Select Exercises")
                    }
                }
            )
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
                        onClick = { navController.navigate("home") },
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
                        onClick = { navController.navigate("profile_screen") },
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
            // Treść ekranu
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
                    items(viewModel.workoutCategories) { category ->
                        WorkoutTypeComposable(workoutType = category, viewModel = viewModel)
                    }
                }

                ExercisesListShow(viewModel = viewModel)
            }
        }

    )
}

@Composable
fun WorkoutTypeComposable(
    workoutType: String,
    viewModel: WorkoutViewModel
) {
    //observable state for isSelected category
    val isSelected = viewModel.selectedCategories.contains(workoutType)
    //Background color depending if item is selected or no
    val backgroundColor = if (isSelected) Color.Black else Color.White
    //Text color depending if item is selected or no
    val textColor = if (isSelected) Color.White else Color.Black

    OutlinedButton(
        shape = RoundedCornerShape(20.dp),
        contentPadding = PaddingValues(6.dp),
        border = BorderStroke(width = 1.dp, color = Color.Gray),
        onClick = {
            viewModel.toggleCategorySelection(workoutType)
        },
        colors = ButtonDefaults.textButtonColors(containerColor = backgroundColor)
    ) {
        Text(text = workoutType, color = textColor, fontSize = 18.sp)

    }
}

@Composable
fun ExercisesListShow(viewModel: WorkoutViewModel) {
    val exercisesToShow = viewModel.getExercisesForSelectedCategories()

    LazyColumn {
        items(exercisesToShow) { exercise ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(Color.White)
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Image
                Image(
                    painter = painterResource(id = exercise.imageRes),
                    contentDescription = null,
                    modifier = Modifier.size(64.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                // Column for text information
                Column {
                    Text(
                        text = exercise.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = exercise.category,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }

        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun ExercisesListPreview() {
    ExercisesListScreen(viewModel = WorkoutViewModel(), navController = rememberNavController())
}