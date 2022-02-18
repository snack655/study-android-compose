package kr.co.study.randomuserlist.utils

data class RandomUser(
    val name: String = "개발하는 최민재 👺",
    val description: String = "오늘도 카공 하고 계신가요?",
    val profileImage: String = "https://randomuser.me/api/portraits/women/72.jpg"
)

object DummyDataProvider {

    val userList = List<RandomUser>(200) { RandomUser() }
}