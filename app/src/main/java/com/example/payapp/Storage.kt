package com.example.payapp

import android.accounts.Account

// 이미지 더미 데이터를 위한 파일
// 나중에는 데이터를 저장하는 클래스로 확장할 수도 있음
fun getDummyData(): List<Person>{
    return listOf(
        Person(R.drawable.img_1,"아이비","국민은행","000-111-2222"),
        Person(R.drawable.img_2,"아이비","국민은행","000-111-2222"),
        Person(R.drawable.img_3,"아이비","국민은행","000-111-2222"),
        Person(R.drawable.img_4,"아이비","국민은행","000-111-2222"),
        Person(R.drawable.img_5,"아이비","국민은행","000-111-2222"),
        Person(R.drawable.img_6,"아이비","국민은행","000-111-2222"),
        Person(R.drawable.img_7,"아이비","국민은행","000-111-2222"),
        Person(R.drawable.img_8,"아이비","국민은행","000-111-2222"),
        Person(R.drawable.img_9,"아이비","국민은행","000-111-2222"),
        Person(R.drawable.ic_transfer,"아이비","국민은행","000-111-2222"),
        Person(R.drawable.ic_home,"아이비","국민은행","000-111-2222"),
    )
}

