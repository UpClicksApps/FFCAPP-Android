package com.upclicks.ffc.ui.general

import android.view.View
import androidx.activity.viewModels
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.upclicks.ffc.R
import com.upclicks.ffc.architecture.BaseActivity
import com.upclicks.ffc.databinding.ActivityHelpBinding
import com.upclicks.ffc.ui.authentication.viewmodel.AccountViewModel
import com.upclicks.ffc.ui.general.adapter.FaqAdapter
import com.upclicks.ffc.ui.general.model.Faq

class HelpActivity : BaseActivity() {

    lateinit var binding: ActivityHelpBinding

    private var faqList = ArrayList<Faq>()
    private lateinit var faqAdapter: FaqAdapter
    private val accountViewModel: AccountViewModel by viewModels()


    //load on scroll properties
    lateinit var linearLayoutManager: LinearLayoutManager
    var onScrollChangeListener: NestedScrollView.OnScrollChangeListener? = null
    var skip = 0
    var take: Int = 10
    var stopScroll: Boolean = false


    override fun getLayoutResourceId(): View {
        binding = ActivityHelpBinding.inflate(layoutInflater)
        initPage()
        return binding.root
    }

    private fun initPage() {
        setUpToolbar()
        setUpPageAction()
        setUpList()
        setUpObservers()
        binding.viewModel = accountViewModel
        binding.lifecycleOwner = this
    }

    // set up toolbar like page title,back button...etc
    private fun setUpToolbar() {
        binding.toolbar.titleTv.text = getString(R.string.help)
        binding.toolbar.backIv.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setUpPageAction() {
        setUpLoadOnScrollListener()
        binding.swipeRefresh.setOnRefreshListener {
            skip = 0
            faqList.clear()
            faqAdapter.notifyDataSetChanged()
            accountViewModel.getAllFaq(skip,take)
        }
    }

    private fun setUpList() {
        faqAdapter = FaqAdapter(
            this, faqList
        )
        binding.faqRecycler.adapter = faqAdapter
        binding.faqRecycler.layoutManager = LinearLayoutManager(this)
    }

    private fun setUpObservers() {
        accountViewModel.getAllFaq(skip,take)
        accountViewModel.observeFaqResponse.observe(this, Observer {faqList->
            binding.swipeRefresh.isRefreshing = false
            if (faqList != null && faqList.isNotEmpty()) {
                binding.emptyFlag.visibility = View.GONE
                this.faqList.addAll(faqList)
                stopScrollIfDataEnded(binding.nestedScrollView, faqList.size)
                faqAdapter.notifyDataSetChanged()
            } else {
                binding.emptyFlag.visibility = View.VISIBLE
            }
        })
    }

    // Set Up Load On Scroll Listener
    private fun setUpLoadOnScrollListener() {
        onScrollChangeListener =
            NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                if (v.getChildAt(v.childCount - 1) != null) {
                    if (scrollY >= v.getChildAt(v.childCount - 1).measuredHeight - v.measuredHeight &&
                        scrollY > oldScrollY
                    ) {
                        if (!stopScroll) {
                            accountViewModel.getAllFaq(skip,take)
                        }
                    }
                }
            }
        binding.nestedScrollView.setOnScrollChangeListener(onScrollChangeListener)
    }

    // Stop Scroll If Data Ended
    private fun stopScrollIfDataEnded(scrollView: NestedScrollView, size: Int) {
        stopScroll = size < take
        skip += take
        scrollView.setOnScrollChangeListener(onScrollChangeListener)
    }

}