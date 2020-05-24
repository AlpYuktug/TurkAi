package com.alpyuktug.turkai.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager

import com.alpyuktug.turkai.R
import com.alpyuktug.turkai.adapter.PostListAdapter
import com.alpyuktug.turkai.viewmodel.FlowViewModel
import kotlinx.android.synthetic.main.fragment_flow.*

class FragmentFlow : Fragment() {

    private  lateinit var  viewModel: FlowViewModel
    private  val sharedPostAdapter = PostListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_flow, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(FlowViewModel::class.java)
        viewModel.RefreshFlow()

        RecylerViewFlow.layoutManager = LinearLayoutManager(context)
        RecylerViewFlow.adapter = sharedPostAdapter


        swipeRefreshLayout.setOnRefreshListener {
            RecylerViewFlow.visibility = View.GONE
            TextViewErrorFlow.visibility = View.GONE
            ProgressBarFlow.visibility = View.VISIBLE

            viewModel.RefreshFlow()
            swipeRefreshLayout.isRefreshing = false
        }

        observeLiveData()

        imageViewCreatePost.setOnClickListener{
            val action = FragmentFlowDirections.actionFragmentFlowToFragmentCreatePost()
            Navigation.findNavController(it).navigate(action)
        }

    }

    fun observeLiveData()
    {
        viewModel.postlist.observe(viewLifecycleOwner, Observer { posts ->
            posts?.let {
                RecylerViewFlow.visibility = View.VISIBLE
                sharedPostAdapter.UpdatePostList(posts)
            }
        })

        viewModel.postlisterror.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if(it)
                {
                    TextViewErrorFlow.visibility = View.VISIBLE
                }
                else
                {
                    TextViewErrorFlow.visibility = View.GONE
                }
            }
        })

        viewModel.postlistloading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if(it)
                {
                    ProgressBarFlow.visibility = View.VISIBLE
                    TextViewErrorFlow.visibility = View.GONE
                    RecylerViewFlow.visibility = View.GONE
                }
                else
                {
                    ProgressBarFlow.visibility = View.GONE
                }
            }
        })
    }

}
