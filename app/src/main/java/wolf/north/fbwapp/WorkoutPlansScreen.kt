package wolf.north.fbwapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class WorkoutPlansScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FBWPlanScreen(viewModel: WorkoutViewModel, navController : NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                        Spacer(modifier = Modifier.width(25.dp))
                        Text(text = "Select FBW plan")
                    }
                },
                actions = {
                    IconButton(onClick = { /* Obsługa kliknięcia ikony */ }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "More")
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(viewModel.fbwPlans) { plan ->
                        FBWPlanCard(plan = plan, viewModel = viewModel)
                    }
                }
            }
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

        )

    // Wyświetlanie AlertDialog, gdy plan jest wybrany (wyświetla szczegóły)
    viewModel.selectedPlan?.let { plan ->
        AlertDialog(
            onDismissRequest = { viewModel.selectedPlan = null  },
            title = { Text(text = plan.name) },
            text = {
                Column {
                    Text(text = "Duration: ${plan.duration}")
                    Text(text = "Level: ${plan.level}")
                    Text(text = "Description: ${plan.description}")
                    // Dodaj inne szczegóły planu
                }
            },
            confirmButton = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { viewModel.selectCurrentPlan(plan) }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(1f).clickable { viewModel.selectCurrentPlan(plan) }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = Color.Yellow,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Text(
                            text = "Add to current plan",
                            Modifier.clickable { viewModel.selectCurrentPlan(plan) },
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    TextButton(onClick = { viewModel.selectedPlan = null }) {
                        Text("OK")
                    }
                }
            }
        )
    }
}

@Composable
fun FBWPlanCard(plan: FBWPlan, viewModel: WorkoutViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(150.dp)
            .clickable { viewModel.selectedPlan = plan },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = plan.imageResId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
            )
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                ) {
                    Text(
                        text = plan.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Duration: ${plan.duration}",
                        fontSize = 14.sp,
                        color = Color.White
                    )
                }
                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Text(
                        text = "Level: \n${plan.level}",
                        fontSize = 14.sp,
                        textAlign = TextAlign.End,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun WorkoutPlansPreview() {
    FBWPlanScreen(viewModel = WorkoutViewModel(), navController = rememberNavController())
}