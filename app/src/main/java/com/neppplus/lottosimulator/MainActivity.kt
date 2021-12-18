package com.neppplus.lottosimulator

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.neppplus.lottosimulator.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    val mWinLottoNumArr = ArrayList<Int>()

    lateinit var mLottoNumTxtList: ArrayList<TextView>

    var mBonusNum = 0

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
            makeBonusNum()

        }

    }

    fun makeBonusNum() {

//        써도 되는 숫자가 나올때까지 무한 반복

        while (true) {

            val randomNum = (1..45).random()

            val isDuplOk = !mWinLottoNumArr.contains(randomNum)

            if (isDuplOk) {
                mBonusNum = randomNum
                break
            }

        }
//        보너스번호 TextView 반영
        binding.txtBonusNum.text = mBonusNum.toString()
    }

    fun makeLottoNumbers() {

//        기존에 번호가 있다면 전부 삭제
        mWinLottoNumArr.clear()

//        6개의 당첨 번호 -> 반복 횟수 명확 => for
        for (i in 0 until 6) {

//            랜덤 숫자 추출 -> (제대로 된 숫자라면) 목록에 추가
            while (true) {

                val randomNum = (Math.random() * 45 + 1).toInt()

//                중복검사 : 당첨 숫자 목록에 내 숫자가 있는지?
                val isDuplOk = !mWinLottoNumArr.contains(randomNum)

                if (isDuplOk) {
//                    숫자를 당첨 목록에 추가
                    mWinLottoNumArr.add(randomNum)
                    break
                }

            }

        }

//        Bubble sort 기능 활용
        for (i in 0 until mWinLottoNumArr.size) {

            for (j in 0 until mWinLottoNumArr.size - 1) {

                if (mWinLottoNumArr[j] > mWinLottoNumArr[j + 1]) {

                    val backUp = mWinLottoNumArr[j]
                    mWinLottoNumArr[j] = mWinLottoNumArr[j + 1]
                    mWinLottoNumArr[j + 1] = backUp

                }

            }

        }

//        당첨 번호 6개 확인
        for (i in 0 until 6) {

//            TextView[i]  =  당첨번호[i]
            mLottoNumTxtList[i].text = mWinLottoNumArr[i].toString()
        }

    }

    override fun setValues() {

        mLottoNumTxtList = arrayListOf(
            binding.txtLottoNum1,
            binding.txtLottoNum2,
            binding.txtLottoNum3,
            binding.txtLottoNum4,
            binding.txtLottoNum5,
            binding.txtLottoNum6
        )

    }
}