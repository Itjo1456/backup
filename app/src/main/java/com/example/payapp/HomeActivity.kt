package com.example.payapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.payapp.R.layout
import com.example.payapp.databinding.ActivityHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeBinding.inflate(layoutInflater)

        setContentView(binding.root)

        //밑에서 설명한 nav controller
        setBottomNavigation()

    }

    private fun setBottomNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.container_home) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationHome.setupWithNavController(navController)

        //네비게이션을 숨겨야하는 경우에 추가하는 코드(특정 페이지에서 네비게이션을 숨길 때)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when(destination.id){
                R.id.navigation_homeFragment, R.id.navigation_transferFragment ->{
                    binding.bottomNavigationHome.visibility = View.VISIBLE
                }
                else ->{
                    binding.bottomNavigationHome.visibility = View.GONE
                }
            }
        }
    }

}



//    val navController = findNavController(R.id.nav_host_fragment)
//    val appBarConfiguration = AppBarConfiguration(navController.graph)
//    findViewById<Toolbar>(R.id.toolbar)
//    .setupWithNavController(navController, appBarConfiguration)
//} supportFragmentManger는 activity에서 fragment manager에 접근하고 있는 것.
//fragmentcontainerview의 아이디를 읽어서, navhostfragment로 타입을 캐스팅함.
// s네브 컨트롤러임!
// 컨트롤러가 있어야, navhost가 관리하는 destination graph에 접근이 가능해짐.

// 메뉴의 아이템의 아이디와 데스티네이션의 아이디가 일치하면, 내부 컨트롤러가 해당 데스티네이션으로 이동시켜줌.
// 간단히 말하면, menu 아이콘의 아이디는 destiniation graph의 destination 아이디와 같아야함
// 네브 컨트롤러와 바텀 네비게이션은 연결되있어야함 ---> 데스트네이션 그래프 + 네브 호스트 프레그먼트(fragment container)

//    <!-- viewBing이란? 바인딩 클래스의 객체를 활용해, xml에 선언한 view에 접근하게 해줌(아이디로) -->
//    <!-- activity랑 fragment에서 바인딩을 사용하는 방식이 살짝 다름-->
// 왜 달라지는가?
// fragment는 fragmentview보다 더 오래 살아남기 때문이다.
// 라이프 사이클의 차이
// fragment가 생성되고 onCreate()가 실행되고, onCreateView()가 실행되며, 뷰의 라이프 사이클이 초기화됨
// onDestroyView가 실행되며, fragment는 소멸되지만, fragment는  ondestroy가 호출되기 전까지 계속 살아 있음
// 그래서 바인딩 클래스의 참조를 정리하는 코디가 onDestroyView에 존재하는 것이다.

