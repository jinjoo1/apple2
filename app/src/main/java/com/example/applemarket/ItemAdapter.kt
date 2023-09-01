package com.example.applemarket

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.applemarket.databinding.ItemBinding
import java.text.DecimalFormat

class ItemAdapter(val mItems: MutableList<SaleItem>) : RecyclerView.Adapter<ItemAdapter.Holder>() {

    interface ItemClick {
        fun onClick(view: View, position: Int)
    }

    interface ItemLongClick {
        fun onLongClick(view: View, position: Int)
    }

    var itemClick: ItemClick? = null
    var itemLongClick: ItemLongClick? = null


    //▼홀더 생성 홀더를 화면에 맞게 몇개 생성할건지 ! 그래서 아이템 틀 만든걸 넣어줘야 한다.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.setOnClickListener {  //클릭이벤트추가부분
            itemClick?.onClick(it, position)
        }



        holder.itemView.setOnLongClickListener {
            itemLongClick?.onLongClick(it, position)
            true
        }

        holder.itemImageView.setImageResource(mItems[position].Image)
        holder.tvItemTitle.text = mItems[position].ItemTitle
        holder.tvAddress.text = mItems[position].Address

        val price = mItems[position].Price
        holder.tvPrice.text = DecimalFormat("#,###").format(price) + "원"

        holder.tvItemChat.text = mItems[position].ChatCnt.toString()
        holder.tvItemLike.text = mItems[position].InterestCnt.toString()

        if (mItems[position].isLike)
            holder.ivAdapterLike.setImageResource(R.drawable.img_like2)
        else
            holder.ivAdapterLike.setImageResource(R.drawable.img_like)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    inner class Holder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val itemImageView = binding.iconItem
        val tvItemTitle = binding.tvItemTitle
        val tvAddress = binding.tvAddress
        val tvPrice = binding.tvPrice
        val tvItemChat = binding.tvChatCnt
        val tvItemLike = binding.tvLikecnt
        val ivAdapterLike = binding.ivLike

    }

}


