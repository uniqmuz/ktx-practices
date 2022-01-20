package com.uniqmuz.test.flowtextview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private val _etState = MutableStateFlow("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        et_flowtest?.doAfterTextChanged {
            _etState.value = (it ?: "").toString()
        }

        // 백그라운드 작업
        CoroutineScope(Dispatchers.IO).launch {
            _etState.collect() { tv_flowtest.text = it }
        }
    }
}