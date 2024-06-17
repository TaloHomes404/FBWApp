package wolf.north.fbwapp

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class UserInfoViewModel : ViewModel() {
    var userName by mutableStateOf("")
}