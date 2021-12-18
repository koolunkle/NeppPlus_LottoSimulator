package com.neppplus.lottosimulator

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.neppplus.lottosimulator.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    val mWinLottoNumArr = ArrayList<Int>()

    lateinit var mLottoNumTxtList: ArrayList<TextView>

    var mBonusNum = 0

    val mMyLottoNumArr = arrayListOf(5, 17, 26, 30, 36, 42)

//    내가 쓴 금액? 합산 변수
    var mUsedMoney = 0L  // Long 타입 (긴 숫자 표현) 명시

//    당첨 금액? 합산 변수
    var mEarnedMoney = 0L

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

//            등수 판정
            checkLottoRank()

        }

    }

    fun checkLottoRank() {

//        1천원 사용으로 간주
        mUsedMoney += 1000

//        내 숫자 6개가 -> 당첨번호 6개 중 몇개나 맞췄는가?

        var correctCount = 0

        for (myNum in mMyLottoNumArr) {

//            맞췄는가? -> 당첨번호에 myNum 들어있는가?
            if (mWinLottoNumArr.contains(myNum)) {

//                맞춘 갯수 증가
                correctCount++
            }

        }
        Log.d("맞춘 갯수", "${correctCount}개 맞춤")

//        등수 판단 -> when 활용

        when (correctCount) {
            6 -> {
                Log.d("등수", "1등입니다!")
                mEarnedMoney += 5000000000
            }

            5 -> {
//                보너스번호 검사 -> 보너스 번호가 내 번호 안에 있는가?
                if (mMyLottoNumArr.contains( mBonusNum )) {
                    Log.d("등수", "2등")
                    mEarnedMoney += 50000000
                }
                else {
                    Log.d("등수", "3등")
                    mEarnedMoney += 2000000
                }

            }

            4 -> {
                Log.d("등수", "4등")
                mEarnedMoney += 50000
            }

            3 -> {
                Log.d("등수", "5등")
                mUsedMoney -= 5000
            }

            else -> {
                Log.d("등수", "꽝")
            }
        }

        binding.txtUsedMoney.text = "${NumberFormat.getInstance(Locale.KOREA).format(mUsedMoney)}원"
        binding.txtEarnedMoney.text = "${NumberFormat.getInstance(Locale.KOREA).format(mEarnedMoney)}원"

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