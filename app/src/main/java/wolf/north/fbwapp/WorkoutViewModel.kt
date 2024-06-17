package wolf.north.fbwapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class WorkoutViewModel : ViewModel() {
    // Stan dla aktualnie wybranej kategorii
    // Mutable state for selected categories
    var selectedCategories by mutableStateOf(emptyList<String>())
        private set

    // Function to toggle category selection
    fun toggleCategorySelection(category: String) {
        selectedCategories = if (selectedCategories.contains(category)) {
            selectedCategories - category
        } else {
            selectedCategories + category
        }
    }

    // Function to get exercises for selected categories
    fun getExercisesForSelectedCategories(): List<Exercise> {
        return if (selectedCategories.isEmpty()) {
            exercises
        } else {
            exercises.filter { it.category in selectedCategories }
        }
    }

    var workoutCategories = listOf("Full Body", "Stretching", "Chest", "Triceps", "Legs", "Biceps")

    // Lista ćwiczeń (przykładowa implementacja)
    val exercises = listOf(
        Exercise("Push-ups", "Chest", R.drawable.pushup),
        Exercise("Bench Press", "Chest", R.drawable.bench_press),
        Exercise("Barbell Rows", "Back", R.drawable.barbell_rows),
        Exercise("Dumbbell Rows", "Back", R.drawable.db_rows),
        Exercise("Pull-ups", "Back", R.drawable.pull_ups),
        Exercise("Arm Circles", "Stretching", R.drawable.arm_circles),
        Exercise("RKC Plank", "Full Body", R.drawable.plank),
        Exercise("Burpees", "Full Body", R.drawable.burpee),
        Exercise("Deadlift", "Back", R.drawable.deadlift),
        Exercise("Squats", "Legs", R.drawable.squat),
        Exercise("Lunges", "Legs", R.drawable.lunges),
        Exercise("Bicep Curls", "Biceps", R.drawable.biceps_curls),
        Exercise("Tricep Dips", "Triceps", R.drawable.chair_dips),
        Exercise("Chair Dips", "Triceps", R.drawable.chair_dips),

    )

    // Funkcja do filtrowania ćwiczeń na podstawie wybranej kategorii
    fun getExercisesForCategory(category: String): List<Exercise> {
        return exercises.filter { it.category == category }
    }
}

data class Exercise(val name: String, val category: String, val imageRes: Int)
