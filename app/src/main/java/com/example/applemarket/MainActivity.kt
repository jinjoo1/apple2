package com.example.applemarket

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.applemarket.databinding.ActivityListBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //데이터 리스트 변수추가
        val dataList: MutableList<SaleItem> = mutableListOf()

        val adapter = ItemAdapter(dataList)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        val activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK) {
                    val itemIndex = it.data?.getIntExtra("itemIndex", 0) as Int
                    val isLike = it.data?.getBooleanExtra("isLike", false) as Boolean

                    if (isLike != dataList[itemIndex].isLike) {
                        dataList[itemIndex].isLike = isLike
                        if (isLike) {
                            dataList[itemIndex].InterestCnt += 1
                        } else {
                            dataList[itemIndex].InterestCnt -= 1
                        }
                    }

                    adapter.notifyItemChanged(itemIndex)
                }
            }

        //더미데이터 있는곳
        dataList.add(
            SaleItem(
                R.drawable.im_sample1,
                ItemTitle = "산지 한달된 선풍기 팝니다",
                ItemDetail = "이사가서 필요가 없어졌어요 급하게 내놓습니다",
                SellerName = "대현동",
                Price = 1000,
                Address = "서울 서대문구 창천동",
                InterestCnt = 13,
                ChatCnt = 25,
                isLike = false
            )
        )
        dataList.add(
            SaleItem(
                R.drawable.im_sample2,
                ItemTitle = "김치냉장고",
                ItemDetail = "이사로인해 내놔요",
                SellerName = "안마담",
                Price = 20000,
                Address = "인천 계양구 귤현동",
                InterestCnt = 8,
                ChatCnt = 28,
                isLike = false
            )
        )
        dataList.add(
            SaleItem(
                R.drawable.im_sample3,
                ItemTitle = "샤넬 카드지갑",
                ItemDetail = "고퀄지갑이구요\\n사용감이 있어서 싸게 내어둡니다",
                SellerName = "코코유",
                Price = 10000,
                Address = "수성구 범어동",
                InterestCnt = 23,
                ChatCnt = 5,
                isLike = false
            )
        )
        dataList.add(
            SaleItem(
                R.drawable.im_sample4,
                ItemTitle = "금고",
                ItemDetail = "금고\\n떼서 가져가야함\\n대우월드마크센텀\\n미국이주관계로 싸게 팝니다",
                SellerName = "Nicole",
                Price = 10000,
                Address = "해운대구 우제2동",
                InterestCnt = 14,
                ChatCnt = 17,
                isLike = false
            )
        )
        dataList.add(
            SaleItem(
                R.drawable.im_sample5,
                ItemTitle = "갤럭시Z플립3 팝니다",
                ItemDetail = "갤럭시 Z플립3 그린 팝니다\\n항시 케이스 씌워서 썻고 필름 한장챙겨드립니다\\n화면에 살짝 스크래치난거 말고 크게 이상은없습니다!",
                SellerName = "절명",
                Price = 150000,
                Address = "연제구 연산제8동",
                InterestCnt = 22,
                ChatCnt = 9,
                isLike = false
            )
        )
        dataList.add(
            SaleItem(
                R.drawable.im_sample6,
                ItemTitle = "프라다 복조리백",
                ItemDetail = "까임 오염없고 상태 깨끗합니다\\n정품여부모름",
                SellerName = "미니멀하게",
                Price = 50000,
                Address = "수원시 영통구 원천동",
                InterestCnt = 25,
                ChatCnt = 16,
                isLike = false
            )
        )
        dataList.add(
            SaleItem(
                R.drawable.im_sample7,
                ItemTitle = "울산 동해오션뷰 60평 복층 펜트하우스 1일 숙박권 펜션 힐링 숙소 별장",
                ItemDetail = "울산 동해바다뷰 60평 복층 펜트하우스 1일 숙박권\\n(에어컨이 없기에 낮은 가격으로 변경했으며 8월 초 가장 더운날 다녀가신 분 경우 시원했다고 잘 지내다 가셨습니다)\\n1. 인원: 6명 기준입니다. 1인 10,000원 추가요금\\n2. 장소: 북구 블루마시티, 32-33층\\n3. 취사도구, 침구류, 세면도구, 드라이기 2개, 선풍기 4대 구비\\n4. 예약방법: 예약금 50,000원 하시면 저희는 명함을 드리며 입실 오전 잔금 입금하시면 저희는 동.호수를 알려드리며 고객님은 예약자분 신분증 앞면 주민번호 뒷자리 가리시거나 지우시고 문자로 보내주시면 저희는 카드키를 우편함에 놓아 둡니다.\\n5. 33층 옥상 야외 테라스 있음, 가스버너 있음\\n6. 고기 굽기 가능\\n7. 입실 오후 3시, 오전 11시 퇴실, 정리, 정돈 , 밸브 잠금 부탁드립니다.\\n8. 층간소음 주의 부탁드립니다.\\n9. 방3개, 화장실3개, 비데 3개\\n10. 저희 집안이 쓰는 별장입니다.",
                SellerName = "굿리치",
                Price = 150000,
                Address = "남구 옥동",
                InterestCnt = 142,
                ChatCnt = 54,
                isLike = false
            )
        )
        dataList.add(
            SaleItem(
                R.drawable.im_sample8,
                ItemTitle = "샤넬 탑핸들 가방",
                ItemDetail = "샤넬 트랜디 CC 탑핸들 스몰 램스킨 블랙 금장 플랩백 !\\n + \"\\n\" + \"색상 : 블랙\\n\" + \"사이즈 : 25.5cm * 17.5cm * 8cm\\n\" + \"구성 : 본품더스트\\n\" + \"\\n\" + \"급하게 돈이 필요해서 팝니다 ㅠ ㅠ",
                SellerName = "난쉽",
                Price = 180000,
                Address = "동래구 온천제2동",
                InterestCnt = 31,
                ChatCnt = 7,
                isLike = false
            )
        )
        dataList.add(
            SaleItem(
                R.drawable.im_sample9,
                ItemTitle = "4행정 엔진분무기 판매합니다.",
                ItemDetail = "3년전에 사서 한번 사용하고 그대로 둔 상태입니다. 요즘 사용은 안해봤습니다. 그래서 저렴하게 내 놓습니다. 중고라 반품은 어렵습니다.\\n",
                SellerName = "알뜰한",
                Price = 30000,
                Address = "원주시 명륜2동",
                InterestCnt = 7,
                ChatCnt = 28,
                isLike = false
            )
        )
        dataList.add(
            SaleItem(
                R.drawable.im_sample10,
                ItemTitle = "셀린느 버킷 가방",
                ItemDetail = "22년 신세계 대전 구매입니당\\n + \"셀린느 버킷백\\n\" + \"구매해서 몇번사용했어요\\n\" + \"까짐 스크래치 없습니다.\\n\" + \"타지역에서 보내는거라 택배로 진행합니당!\"",
                SellerName = "똑태현",
                Price = 190000,
                Address = "중구 동화동",
                InterestCnt = 40,
                ChatCnt = 6,
                isLike = false
            )
        )



        adapter.itemClick = object : ItemAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra(Constants.ITEM_INDEX, position);
                intent.putExtra(Constants.ITEM_OBJECT, dataList[position]);
                activityResultLauncher.launch(intent)

            }
        }

        adapter.itemLongClick = object : ItemAdapter.ItemLongClick {
            override fun onLongClick(view: View, position: Int) {
                val ad = AlertDialog.Builder(this@MainActivity)
                ad.setIcon(R.drawable.img_chat)
                ad.setTitle("상품삭제")
                ad.setMessage("상품을 정말로 삭제하시겠습니까?")
                ad.setPositiveButton("확인") { dialog, _ ->
                    dataList.removeAt(position)
                    adapter.notifyItemRemoved(position)
                }
                ad.setNegativeButton("취소") { dialog, _ ->
                    dialog.dismiss()
                }

                ad.show()
            }
        }

//종눌렀을때 실행
        binding.ivNoti.setOnClickListener {
            notification()
        }

        //플로팅 액션버튼에 fadin.out
        val fadeIn = AlphaAnimation(0f, 1f).apply { duration = 500 }
        val fadeOut = AlphaAnimation(1f, 0f).apply { duration = 500 }
        var isTop = true

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)


                if (!binding.recyclerView.canScrollVertically(-1) // 스크롤 업 + 탑
                    && newState == RecyclerView.SCROLL_STATE_IDLE
                ) {
                    binding.fbScrollup.startAnimation(fadeOut)
                    binding.fbScrollup.visibility = View.GONE
                    isTop = true
                } else {
                    if (isTop) {
                        binding.fbScrollup.visibility = View.VISIBLE
                        binding.fbScrollup.startAnimation(fadeIn)
                        isTop = false
                    }
                }
            }
        })

        binding.fbScrollup.setOnClickListener {
            binding.recyclerView.smoothScrollToPosition(0)
        }


    }

    override fun onBackPressed() {
        val ad = AlertDialog.Builder(this)
        ad.setIcon(R.drawable.img_chat)
        ad.setTitle("종료")
        ad.setMessage("정말 종료하시겠습니까?")

        ad.setPositiveButton("확인") { dialog, _ ->
            finish()
        }
        ad.setNegativeButton("취소") { dialog, _ ->
            dialog.dismiss()
        }
        ad.show()
    }

    fun notification() {
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val builder: NotificationCompat.Builder
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "one-channel"
            val channelName = "My Channel One"
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "My Channel One Description"
                setShowBadge(true)
                val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                val audioAttributes = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build()
                setSound(uri, audioAttributes)
                enableVibration(true)
            }
            manager.createNotificationChannel(channel)

            builder = NotificationCompat.Builder(this, channelId)

        } else {
            builder = NotificationCompat.Builder(this)
        }

        builder.run {
            setSmallIcon(R.mipmap.ic_launcher)
            setWhen(System.currentTimeMillis())
            setContentTitle("키워드 알림")
            setContentText("설정한 키워드에 대한 알림이 도착했습니다!!")
        }
        manager.notify(11, builder.build())
    }

}