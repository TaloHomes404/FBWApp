package wolf.north.fbwapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class UserEquipmentScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun UserEquipmentDefault() {
    val equipmentList = listOf("Dumbbell", "Barbell", "Resistance band", "Ketlebell", "Training Bench", "Bodyweight", "Gym Machines")
    val equipmentIcons = mapOf(
        "Dumbbell" to R.drawable.dumbbells,
        "Ketlebell" to R.drawable.kettlebell,
        "Resistance band" to R.drawable.bands,
        "Training Bench" to R.drawable.bench,
        "Bodyweight" to R.drawable.bodyweight,
        "Barbell" to R.drawable.barbell,
        "Gym Machines" to R.drawable.gymequipment)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Enter your equipment")
                }, modifier = Modifier.padding(8.dp),
                navigationIcon = {
                    IconButton(onClick = { /* TODO */ }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = "Settings"
                        )
                    }
                }
            )
        },
        content = {
            // Główna zawartość ekranu
        Column(){
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ){
                items(equipmentList) {equipment ->
                    EquipmentTypeItem(equipment = equipment, iconResId = equipmentIcons[equipment] ?: R.drawable.dumbbells)
                }
            }
        Spacer(modifier = Modifier.size(15.dp))
            UserInfoSection(viewModel = UserInfoViewModel())
        }
        }
    )
}

@Composable
fun EquipmentTypeItem(equipment: String, iconResId: Int) {

    var isChecked by remember {mutableStateOf(false)}

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = iconResId), // Replace with appropriate icon
                contentDescription = equipment,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = equipment,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        Checkbox(
            checked = isChecked,
            onCheckedChange = { isChecked = it }
        )
    }

}

@Composable
fun UserInfoSection(viewModel: UserInfoViewModel) {
    var userName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Also... we would like to know your name!",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        TextField(
            value = viewModel.userName,
            onValueChange = { viewModel.userName = it },
            placeholder = { Text(text = "Enter your name...") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        Text(
            text = "continue as guest",
            color = Color.Blue,
            modifier = Modifier.padding(top = 2.dp)
        )

        Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.Bottom) {
            Button(onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                contentPadding = ButtonDefaults.ContentPadding) {
                Text(text = "Proceed", color = Color.White, fontSize = 20.sp)
                Icon(imageVector = Icons.Filled.ArrowForward, contentDescription = null)
            }
        }
    }
}

