package com.example.lesson1m6.ui.activity.main

import android.content.Intent
import android.os.Bundle

import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

import com.example.lesson1m6.*
import com.example.lesson1m6.databinding.ActivityMainBinding
import com.example.lesson1m6.ui.activity.second.SecondActivity

import com.example.lesson1m6.ui.toastShow


class MainActivity : AppCompatActivity() {
    private var num: Int = ZERO
    private lateinit var activityResultLauncher : ActivityResultLauncher<Intent>
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        launcher()
        decrement()
        increment()
        controlEditText()
    }

    private fun launcher(){
          activityResultLauncher =
              registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_CODE) run {
                val intent: Intent? = it.data

                if (intent != null) run {
                    val data: String? = intent.getStringExtra(KEY_SECOND_ACTIVITY)
                    binding.edText.setText(data).toString().trim()
                }
            }
        }
    }

    private fun editTextToString(): String {
        return binding.edText.text.toString()
    }

    private fun increment() {
        binding.btnIncrement.setOnClickListener {
            num--
            binding.txtNullTv.text = num.toString()
        }
    }

    private fun decrement(){
        binding.btnDecrement.setOnClickListener {
            num++
            binding.txtNullTv.text = num.toString()
        }
    }
    private fun controlEditText(){
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
        val intent = Intent(this@MainActivity, SecondActivity::class.java)
        intent.putExtra(KEY_MAIN_ACTIVITY, editTextToString())
        activityResultLauncher.launch(intent)
    }
}