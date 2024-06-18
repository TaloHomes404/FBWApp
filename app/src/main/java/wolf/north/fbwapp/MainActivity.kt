package wolf.north.fbwapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import wolf.north.fbwapp.ui.theme.FBWAppTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FBWAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "welcome") {
                    composable("welcome") { WelcomeScreen(navController) }
                    composable("home") {  HomeScreenContent(
                        navController = navController,
                        viewModel = UserInfoViewModel(),
                        secViewModel = WorkoutViewModel()
                    ) }
                    composable("exercises_list") { ExercisesListScreen(WorkoutViewModel(), navController) }
                    composable("user_equipment") { UserEquipmentDefault(navController) }
                    composable("workout_plans") { FBWPlanScreen(WorkoutViewModel(), navController) }
                    composable("profile_screen") { UserProfileScreen(navController) }
                }
            }


        }
    }
}
