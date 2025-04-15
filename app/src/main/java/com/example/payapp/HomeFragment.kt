package com.example.payapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.payapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding:FragmentHomeBinding?= null
    private val binding get() = _binding!! // null이면 안돼!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        binding.root
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewCardArea.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToPaymentMethodFragment()//여기에 데이터도 넣어서 전달 가능, 액션 아이디를 굳이 적을필요x
            findNavController().navigate(action)//action을 지원해주는 함수, 실제로 화면의 이동을 제어해줌
        }


    }

    override fun onDestroyView(){
        super.onDestroyView()
        _binding=null
    }

}

//
//class HomeFragment : Fragment() {
//    private var _binding:FragmentHomeBinding?= null
//    private val binding get() = _binding!! // null이면 안돼!
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentHomeBinding.inflate(inflater,container,false)
//        binding.root
//        return binding.root
//    }
//
//    override fun onDestroyView(){
//        super.onDestroyView()
//        _binding=null
//    }
//
//} 해당 코드로 인해, fragment가 생성됨.


// safe args : 사용하려면 빌드 그레들, 앱 모듈 그레들 다 라이브러리 설정 필요
// generated code : 해당 코드에는 네비게이션 그래프에서 정의하는 액션을 클래스가 제공해주는 기능으로서 작동
// afragment+Direction, 스태틱 메서드를 제공함
// 액션을 클래스가 제공하는 함수로 이용할 수 있음