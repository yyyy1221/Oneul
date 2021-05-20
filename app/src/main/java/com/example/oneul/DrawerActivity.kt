package com.example.oneul

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import androidx.core.view.GravityCompat
import com.example.oneul.databinding.ActivityDrawerBinding
import kotlinx.android.synthetic.main.activity_drawer.*


class DrawerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDrawerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        //val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer)
        /*
        //1. 페이지 데이터 로드
        val list=listOf(myCalandar())
        //2. 아답터를 생성
        val pageAdapter = FragmentPagerAdapter(list, this)
        //3. 아답터와 뷰페이지 연결
        binding.viewPager.adapter = pageAdapter
        */

        btnOpen.setOnClickListener {
            drawer.openDrawer(GravityCompat.START)
        }
        //총 내가 가지고 있는 메뉴 리스트들의 버튼들을 모두 나열해야함
        menu_myCalandar.setOnClickListener{

            //drawer.closeDrawer(GravityCompat.START) //드로어 닫기
        }

        add_menu.setOnClickListener{
            // 캘린더 추가하기
            // 프래그먼트 띄워서 거기서 처리해주기
        }
        circles.setOnCheckedChangeListener{ buttonView, checkedId->
            when(checkedId){
                R.id.circle1->Toast.makeText(this.getApplicationContext(),"circle1",Toast.LENGTH_SHORT).show()
                R.id.circle2->Toast.makeText(this.getApplicationContext(),"circle2",Toast.LENGTH_SHORT).show()
                R.id.circle3->Toast.makeText(this.getApplicationContext(),"circle3",Toast.LENGTH_SHORT).show()
                R.id.circle4->Toast.makeText(this.getApplicationContext(),"circle4",Toast.LENGTH_SHORT).show()
                R.id.circle5->Toast.makeText(this.getApplicationContext(),"circle5",Toast.LENGTH_SHORT).show()
                R.id.circle6->Toast.makeText(this.getApplicationContext(),"circle6",Toast.LENGTH_SHORT).show()
                R.id.circle7->Toast.makeText(this.getApplicationContext(),"circle7",Toast.LENGTH_SHORT).show()
                R.id.circle8->Toast.makeText(this.getApplicationContext(),"circle8",Toast.LENGTH_SHORT).show()


            }
        }
    }

    /*
    private fun movePage(index:Int){
        viewPager.currentItem = index
        drawer.closeDrawer(GravityCompat.START)
    }
    private fun setViewPage(){
        val fragmentList = listOf(myCalandar())
        val adapter = FragmentAdapter(this)
        adapter.fragmentList = fragmentList
        viewPager.adapter = adapter
    }

     */
}
