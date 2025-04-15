package com.example.payapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.payapp.databinding.FragmentTransferBinding

class TransferFragment : Fragment() {

    private var _binding : FragmentTransferBinding?=null
    private val binding get() = _binding!!
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransferBinding.inflate(inflater,container,false)
        binding.root
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 준비한 더미 데이터로 리스트 설정
        val list = getDummyData()

        recyclerViewAdapter = RecyclerViewAdapter(list)

        binding.accountRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext()) //이거 대신 리사이클러뷰의 xml에서 레이아웃 메니저 선택하는 것도 가능
            adapter = recyclerViewAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

}

// #
// 1. 해당 xml에 RecyclerView 추가
// 2. 아이템 레이아웃 : 일종의 아이템 항목?
// 3. 데이터 모델 클래스 : 데이터 형식
// 4. Adapter 생성 및 Fragment에서 reyclerview와 adpater 연결