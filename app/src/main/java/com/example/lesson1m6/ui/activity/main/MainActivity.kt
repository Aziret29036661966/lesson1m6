package com.example.lesson1m6.ui.activity.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.lesson1m6.*
import com.example.lesson1m6.databinding.ActivityMainBinding
import com.example.lesson1m6.ui.activity.second.SecondActivity
import es.dmoral.toasty.Toasty

class MainActivity : AppCompatActivity() {
    private var num: Int = Zero
    private val duration = Toast.LENGTH_SHORT
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
            if (it.resultCode == ResultCode) run {
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
                Toasty.custom(this, ToastText,
                    ContextCompat.getDrawable(this, R.drawable.ic_baseline_warning_24),
                    ContextCompat.getColor(this, android.R.color.black),
                    ContextCompat.getColor(this, android.R.color.holo_red_light),
                    duration, true, true).show()
                Toasty.Config.reset()
            }else{
                Toasty.custom(this, editTextToString(),
                    ContextCompat.getDrawable(this, R.drawable.ic_baseline_near_me_24),
                    ContextCompat.getColor(this, android.R.color.black),
                    ContextCompat.getColor(this, android.R.color.holo_green_light),
                    duration, true, true).show()
                Toasty.Config.reset()
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