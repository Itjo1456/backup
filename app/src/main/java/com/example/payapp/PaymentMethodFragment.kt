package com.example.payapp

import android.os.Bundle
import android.text.InputFilter
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import com.example.payapp.databinding.FragmentHomeBinding
import com.example.payapp.databinding.FragmentPaymentMethodBinding
private const val  PASSWORD_FORMAT_LENGTH = 2
private const val  DURATION_FORMAT_LENGTH = 4
class PaymentMethodFragment : Fragment() {

    private var _binding: FragmentPaymentMethodBinding? = null
    private val binding get() = _binding!! // null이면 안돼!

    private var cardNumber = ""
    private var validMonth = ""
    private var validYear = ""
    private var cardName = ""

    private var isValidCardNumber = false
    private var isValidDate = false
    private var isValidPassword = false
    private var isValidCardName = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaymentMethodBinding.inflate(inflater, container, false)
        binding.root
        return binding.root
    }


    // view와 관련된 설정은 이곳에서 하기
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTextInput()

        // 버튼 눌렀을 때 화면 전환
        binding.btnSubmitCardInfo.setOnClickListener {

            // 파라미터로 데이터 전달
            val paymentMethod = PaymentMethod(
                cardNumber = cardNumber,
                validMonth = validMonth,
                validYear =validYear,
                cardName = cardName

            )

            val action = PaymentMethodFragmentDirections.actionNavigationPaymentMethodFragmentToNavigationPaymentRegistration(paymentMethod)//여기에 데이터도 넣어서 전달 가능, 액션 아이디를 굳이 적을필요x
            findNavController().navigate(action)//action을 지원해주는 함수, 실제로 화면의 이동을 제어해줌
        }


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

    private fun setTextInput() {
        // edittext의 입력값의 길이를 제한함
        // 과제에 써먹을 수 있을 듯
        // arrayOf를 사용하는 이유, filters 내부에서 array 리스트를 사용한다고 한다.
        binding.etExpiredDate.filters = arrayOf(InputFilter.LengthFilter(DURATION_FORMAT_LENGTH+1))
        binding.etInputPassword.filters = arrayOf(InputFilter.LengthFilter(PASSWORD_FORMAT_LENGTH))

        // 카드 번호가 입력된 후 실행되는 함수
        binding.etInputCardNumber.doAfterTextChanged {
            cardNumber = it?.toString() ?: ""
            // isVliadeInput() 함수를 통해, 에디터 텍스트가 빈칸인지 아닌지를 true fase로 판단
            isValidCardNumber = isValidInput(cardNumber)
            // updateButtonEnableState(), 4가지 에디터 모두가 true일 때, enable을 true로 바꿔줌. 굉장히 합리적 합리적
            updateButtonEnableState()
        }
        binding.etCardName.doAfterTextChanged {
            cardName =it?.toString() ?: ""
            isValidCardName = isValidInput(cardName)
            updateButtonEnableState()
        }
        binding.etExpiredDate.doAfterTextChanged {
            val validDate = it?.toString() ?: ""
            // 입력값 제어
            if (validDate.isNullOrBlank() || validDate.length<DURATION_FORMAT_LENGTH){
                isValidDate = false
                updateButtonEnableState()
                return@doAfterTextChanged
            }
            if(isValidDuration(validDate)){
                applyDurationFormat(validDate)
                isValidDate = true
                updateButtonEnableState()
            }else if(!isValidDurationFormat(validDate)){
                restDurationFromat(validDate)
                isValidDate = false
            }
            updateButtonEnableState()


        }
        binding.etInputPassword.doAfterTextChanged {
            val password = it?.toString() ?:""
            isValidPassword = isValidPassword(password)
            updateButtonEnableState()
        }
    }

    private fun updateButtonEnableState() {
        binding.btnSubmitCardInfo.isEnabled = isValidCardName && isValidCardNumber && isValidPassword && isValidDate
    }

    private fun isValidInput(text:String):Boolean{
        return text.isNotBlank()
    }

    private fun isValidPassword(text: String):Boolean{
        return text.length == PASSWORD_FORMAT_LENGTH
    }

    private fun isValidDuration(text:String):Boolean{
        return text.length == DURATION_FORMAT_LENGTH &&!text.contains('/')
    }

    private fun isValidDurationFormat(text:String):Boolean{
        return text.length == DURATION_FORMAT_LENGTH+1 &&text.contains('/')
    }

    private fun restDurationFromat(text:String){
        binding.etExpiredDate.setText(text.replace("/",""))
        binding.etExpiredDate.setSelection(text.lastIndex)
    }

    private fun applyDurationFormat(text:String){
        val monthCharacterSize = 2
        validMonth = text.substring(0,monthCharacterSize)
        validYear = text.substring(monthCharacterSize, DURATION_FORMAT_LENGTH)
        binding.etExpiredDate.setText("$validMonth/$validYear")
        // 인덱스를 전달하여 커서의 위치를 변경하는 함수
        binding.etExpiredDate.setSelection(5)
    }


    override fun onDestroyView(){
        super.onDestroyView()
        _binding=null
    }

}