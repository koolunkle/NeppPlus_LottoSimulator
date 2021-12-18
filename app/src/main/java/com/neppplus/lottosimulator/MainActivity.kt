package com.neppplus.lottosimulator

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.neppplus.lottosimulator.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    val mWinLottoNumArr = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        binding.btnBuyLotto.setOnClickListener {

//            로또 번호 6개 생성
            makeLottoNumbers()

//            보너스번호 생성

        }

    }

    fun makeLottoNumbers() {

//        기존에 번호가 있다면 전부 삭제
        mWinLottoNumArr.clear()

//        6개의 당첨 번호 -> 반복 횟수 명확 => for
        for (i in 0 until 6) {

//            랜덤 숫자 추출 -> (제대로 된 숫자라면) 목록에 추가
            while (true) {

                val randomNum = (Math.random() * 45 + 1).toInt()

                Log.d("랜덤", randomNum.toString())

                if (true) {
                    break
                }

            }

        }

    }

    override fun setValues() {

    }
}