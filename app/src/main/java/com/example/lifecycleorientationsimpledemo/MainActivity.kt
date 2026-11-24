package com.example.lifecycleorientationsimpledemo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.lifecycleorientationsimpledemo.ui.theme.LifeCycleOrientationSimpleDemoTheme
import com.example.lifecycleorientationsimpledemo.ui.theme.MainScreen

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate called")
        viewModel.logLifecycleEvent("onCreate called")

        enableEdgeToEdge()
        setContent {
            LifeCycleOrientationSimpleDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        viewModel = viewModel,
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart called")
        viewModel.logLifecycleEvent("onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume called")
        viewModel.logLifecycleEvent("onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause called")
        viewModel.logLifecycleEvent("onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop called")
        viewModel.logLifecycleEvent("onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy called")
        viewModel.logLifecycleEvent("onDestroy called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart called")
        viewModel.logLifecycleEvent("onRestart called")
    }

    companion object {
        private const val TAG = "LifecycleDemo"
    }
}