package wolf.north.fbwapp.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class FBWPlan(
    val name: String,
    val imageResId: Int,
    val duration: String,
    val level: String,
    val description: String
)


// Pole do przechowywania aktualnie wybranego planu
var selectedPlan by mutableStateOf<FBWPlan?>(null)