package wolf.north.fbwapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import wolf.north.fbwapp.ui.theme.FBWAppTheme


class WelcomeScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            FBWAppTheme {
                WelcomeScreen(navController = rememberNavController())
            }
        }
    }
}

@Composable
fun EntryScreenImageAndBg() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .clip(
                RoundedCornerShape(bottomStart = 125.dp, bottomEnd = 125.dp)
            )
            .background(color = Color.Black)

    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {

            Image(
                painter = painterResource(id = R.drawable.fitness_woman),
                contentDescription = "welcome screen fitness woman picture",
                modifier = Modifier.padding(top = 32.dp)
            )
        }

    }

}


@Composable
fun EntryScreenTitleAndDesc(navController: NavController) {


    //Default values
    val titleString = stringResource(R.string.entryScreenTitle)
    val descString = stringResource(R.string.entryScreenDescprition)
    val buttonText = stringResource(R.string.entryScreenButtonText)
    val titleSize = MaterialTheme.typography.displayMedium.fontSize
    val descSize = MaterialTheme.typography.bodyLarge.fontSize
    val buttonTextSize = MaterialTheme.typography.headlineSmall.fontSize
    val fontStyle = FontStyle.Italic
    val fontWeight = FontWeight.Bold
    val letterSpacing = 0.25.sp



    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color.White)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = titleString,
            fontSize = titleSize,
            fontStyle = fontStyle,
            fontWeight = fontWeight,
            letterSpacing = letterSpacing,
            textAlign = TextAlign.Center,
            maxLines = 3,
            lineHeight = 50.sp,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        Text(
            text = descString,
            fontSize = descSize,
            style = TextStyle(letterSpacing = 0.15.sp),
            fontStyle = fontStyle,
            fontWeight = fontWeight,
            color = Color.Black,
            textAlign = TextAlign.Center,
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = {navController.navigate("user_equipment")},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, start = 12.dp, end = 12.dp, bottom = 12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                contentPadding = ButtonDefaults.ContentPadding
            ) {
                Text(text = buttonText, color = Color.White, fontSize = buttonTextSize)
            }
        }

    }
}



@Composable
fun WelcomeScreen(navController: NavController) {
    FBWAppTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                EntryScreenImageAndBg()
                EntryScreenTitleAndDesc(navController = navController)
            }
        }
    }
}






