package com.example.lesson1m6.ui.activity.second

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.lesson1m6.*
import com.example.lesson1m6.databinding.ActivitySecondBinding
import com.example.lesson1m6.ui.toastShow
import es.dmoral.toasty.Toasty

class SecondActivity : AppCompatActivity() {

    private val duration = Toast.LENGTH_SHORT

    private val binding: ActivitySecondBinding by lazy {
        ActivitySecondBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        getResult()
        mainActivity()
    }

    private fun editTextToString() : String{
        return binding.edText.text.toString()
    }

    private fun mainActivity() {
        binding.btnNextActivity.setOnClickListener{
            if (editTextToString().isEmpty()){
                toastShow(TOAST_TEXT, R.drawable.ic_baseline_warning_24, android.R.color.holo_red_light)
            }else{
                toastShow(editTextToString(), R.drawable.ic_baseline_near_me_24, android.R.color.holo_green_light)
                nextActivity()
            }
        }
    }

    private fun nextActivity(){
        val intent = Intent()
        intent.putExtra(KEY_SECOND_ACTIVITY, editTextToString())
        setResult(RESULT_CODE, intent)
        super.onBackPressed()
    }

    private fun getResult() {
            val result = intent.extras?.getString(KEY_MAIN_ACTIVITY)
            binding.edText.setText(result)
            }
}