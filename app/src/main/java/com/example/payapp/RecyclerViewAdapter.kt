package com.example.payapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.payapp.databinding.ItemTransferAccountBinding

class RecyclerViewAdapter(private val list: List<Person>):
        RecyclerView.Adapter<RecyclerViewAdapter.CustomViewHolder>(){
    inner class CustomViewHolder(private val binding: ItemTransferAccountBinding):
            RecyclerView.ViewHolder(binding.root){
        fun bind(item : Person){
            binding.ivAccountImage.setImageResource(item.img)
            binding.tvAccountName.text = item.name
            binding.tvAccountingBankName.text = item.accounting_bank_name
            binding.tvAccountNumber.text =item.account_number

        }
            }

    // recyclerivew의 목록을 관리할 adapter이다. recyclerview 각각의 아이템이 될 viewholder를 inner class로 정의한다.
    // view holder는 목록에서 개별 항목의 레이아웃을 바인딩하고, adapter에서 필요시 viewholder 객체에 접근하여, 뷰와 데이터를 설정합니다.
    // onCreatViewHolder : ViewHolder와 그에 연결된 뷰를 생성한다
    // onBindViewHolder : 아이템의 뷰에 데이터를 연결
    // getItem Count : recycler 아이템의 개수

    // 전반적인 정리!, frament.xml에 recyclerview 추가
    // 2. item layout 만들기
    // 3. 데이터 모델 클래스 생성
    // 4. adapter 클래스 생성
    // 5. activity나 fragment에 recyclerview와 adapter 연결

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = ItemTransferAccountBinding.inflate(LayoutInflater.from(parent.context),parent,false

        )

        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount()= list.size
}
