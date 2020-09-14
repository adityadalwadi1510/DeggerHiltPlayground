package com.example.deggerhiltplayground.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.deggerhiltplayground.Model.Blog
import com.example.deggerhiltplayground.R
import com.example.deggerhiltplayground.Util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.lang.StringBuilder

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private val TAG = "App Debug"

    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        susbscribeObesrver()
        viewModel.setStateEvent(MainStateEvent.GetBlogEvent)
    }

    private fun susbscribeObesrver(){
        viewModel.dataState.observe(this, Observer { dataState ->
            when(dataState){
                is DataState.Success<List<Blog>>->{
                    displayProgressbar(false)
                    appendBlogTitle(dataState.data)
                }
                is DataState.Error->{
                    displayProgressbar(false)
                    displayError(dataState.exception.message)
                }
                is DataState.Loading->{
                    displayProgressbar(true)
                }
            }
        })
    }

    private fun displayError(message:String?){
        if(message != null){
            text.text=message
        }else{
            text.text="UNKNOWN ERROR"
        }
    }

    private fun displayProgressbar(isDisplay:Boolean){
        progress_bar.visibility=if(isDisplay) View.VISIBLE else View.GONE
    }

    private fun appendBlogTitle(blogs:List<Blog>){
        val sb=StringBuilder()
        for (blog in  blogs){
            sb.append(blog.title+"\n")
        }
        text.text =sb.toString()

    }
}