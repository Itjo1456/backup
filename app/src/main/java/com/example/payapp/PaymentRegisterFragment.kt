package com.example.payapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.payapp.databinding.FragmentPaymentMethodBinding
import com.example.payapp.databinding.FragmentPaymentRegisterBinding

class PaymentRegisterFragment : Fragment() {

    private var _binding: FragmentPaymentRegisterBinding? = null
    private val binding get() = _binding!! // null이면 안돼!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaymentRegisterBinding.inflate(inflater, container, false)
        binding.root
        return binding.root
    }

    // view와 관련된 설정은 이곳에서 하기
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // 결제 수단 등록에서 입력창 4개가 모두 등록되었을 때, xml의 enable이 true가 되도록 하는 기능
        // true여야 버튼이 활성화 됨.

        val toolbar: Toolbar = binding.toolbarPaymentmethod // toolbar 바인딩
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        // fragment는 자체 액션바 설정 불가
        // fragment를 호스팅하고 있는 activiy를 갖고 와서, AppcompatActivity로 형변환하고
        // 그 activiy의 액션바를 우리가 가져온 toolbar로 교체하는 작업
        // 이러면 툴바가 액션바처럼 동작한다.

        // 뒤로가기 버튼 눌렀을 때
        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onDestroyView(){
        super.onDestroyView()
        _binding=null
    }


}