package com.example.applemarket

import android.content.ClipData.Item
import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.res.ResourcesCompat
import com.example.applemarket.databinding.ActivityDetailBinding
import com.google.android.material.snackbar.Snackbar

class DetailActivity : AppCompatActivity() {

    // 바인딩 객체 선언
    private lateinit var binding: ActivityDetailBinding

    private var isLike = false

    private  val item: SaleItem? by lazy {
        intent.getParcelableExtra<SaleItem>(Constants.ITEM_OBJECT)
    }

    private val itemPosition: Int by lazy {
        intent.getIntExtra(Constants.ITEM_OBJECT, 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 바인딩 객체 초기화
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("jblee", "itemPosition = $itemPosition")

        binding.ivItemImage.setImageDrawable(item?.let {
            ResourcesCompat.getDrawable(
                resources,
                it.Image,
                null
            )
        })

        binding.tvSellerName.text = item?.SellerName
        binding.tvSellerAddress.text = item?.Address
        binding.tvITemTitle.text = item?.ItemTitle
        binding.tvITemDetail.text = item?.ItemDetail

        val formatter = DecimalFormat("#,###")

        binding.tvItemDetailPrice.text =  formatter.format(item?.Price) + "원"

        isLike = item?.isLike == true

        binding.ivDetailLike.setImageResource(if (isLike){R.drawable.img_like2}else{R.drawable.img_like})

        binding.ivBack.setOnClickListener{
            exit()
        }

        binding.llDetailLike.setOnClickListener{
            if (!isLike){
                binding.ivDetailLike.setImageResource(R.drawable.img_like2)
                Snackbar.make(binding.constLayout, "관심목록에 추가 되었습니다.", Snackbar.LENGTH_SHORT).show()
                isLike = true
            }else{
                binding.ivDetailLike.setImageResource(R.drawable.img_like)
                isLike = false
            }
        }
    }

    fun exit() {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("itemIndex", itemPosition)
            putExtra("isLike", isLike)
        }
        setResult(RESULT_OK, intent)
        if(!isFinishing) finish()
    }

    override fun onBackPressed() {
        exit()
    }
}