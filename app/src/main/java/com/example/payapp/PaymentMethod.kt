package com.example.payapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// 데이터를 담는 클래스
// 서버와의 통신으로 비밀번호까지 전달해야할 때, 어떻게 암호화를 해야할지
// 소통이 필요함
// pareclable이란? 객체 직렬화할 수 있는 방법을 제공하는 타입
// 다른 플랫폼으로 데이터를 전달할 때는 바이트화가 필요함
// 데이터를 받을 화면에서 arguement를 추가해야함
@Parcelize
data class PaymentMethod(
    val cardNumber : String,
    val validMonth : String,
    val validYear : String,
    val cardName : String

) : Parcelable
