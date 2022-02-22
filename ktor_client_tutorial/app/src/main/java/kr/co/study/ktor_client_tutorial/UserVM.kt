package kr.co.study.ktor_client_tutorial

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class UserVM: ViewModel() {

    val usersFlow = MutableStateFlow<List<User>>(listOf())

    init {
        Log.d("TestTest", "UserVM(): init called")
        // 뷰모델이 생성되었을 때 api 호출
        viewModelScope.launch {
            // 에러 발생 잡기 위한 runCatching 블럭
            kotlin.runCatching {
                UserRepo.fetchUsers()
            }.onSuccess { fetchedUsers ->
                Log.d("TestTest", "UserVM() onSuccess")
                usersFlow.value = fetchedUsers
            }.onFailure {
                Log.d("TestTest", "UserVM() onFailure")
            }
        }
    }
}