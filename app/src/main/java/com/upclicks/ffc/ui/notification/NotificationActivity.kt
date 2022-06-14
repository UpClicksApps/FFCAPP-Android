package com.upclicks.ffc.ui.notification

import android.content.Intent
import android.view.View
import android.widget.PopupMenu
import androidx.activity.viewModels
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.upclicks.ffc.R
import com.upclicks.ffc.architecture.BaseActivity
import com.upclicks.ffc.databinding.ActivityNotificationBinding
import com.upclicks.ffc.ui.notification.adapter.NotificationAdapter
import com.upclicks.ffc.ui.notification.data.model.Notification
import com.upclicks.ffc.ui.notification.data.viewModel.NotificationViewModel
import com.upclicks.ffc.ui.general.dialog.ConfirmDialog
import com.upclicks.ffc.commons.FcmNotificationType
import com.upclicks.ffc.commons.Keys
import com.upclicks.ffc.ui.orders.OrderDetailsActivity
import www.sanju.motiontoast.MotionToast

class NotificationActivity : BaseActivity() {


    lateinit var binding: ActivityNotificationBinding
    private val notificationViewModel: NotificationViewModel by viewModels()
    private lateinit var notificationAdapter: NotificationAdapter
    private var notificationList = ArrayList<Notification>()

    //load on scroll properties
    lateinit var linearLayoutManager: LinearLayoutManager
    var onScrollChangeListener: NestedScrollView.OnScrollChangeListener? = null
    var skip = 0
    var take: Int = 10
    var stopScroll: Boolean = false

    override fun getLayoutResourceId(): View {
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        initPage()
        return binding.root
    }

    // Main function for this page
    private fun initPage() {
        setUpToolbar()
        setUpPageUi()
        setUpPageActions()
        setUpObservers()
        binding.viewModel = notificationViewModel
        binding.lifecycleOwner = this
    }

    // set up toolbar like page title,back button...etc
    private fun setUpToolbar() {
        binding.toolbar.titleTv.text = getString(R.string.notifications)
        binding.toolbar.backIv.setOnClickListener {
            onBackPressed()
        }
    }

    // set up page ui
    private fun setUpPageUi() {
        if (sessionHelper.userProfile.unSeenNotificationsCount != null && sessionHelper.userProfile.unSeenNotificationsCount!! > 0) {
            binding.markAllAsReadBtn.visibility = View.VISIBLE
        } else binding.markAllAsReadBtn.visibility = View.GONE

        setUpListUi()
    }

    // set up notification list
    private fun setUpListUi() {
        notificationAdapter = NotificationAdapter(
            this, notificationList,
            onNotificationItemClicked = { notification ->
                setUpNotificationItemClick(notification)
            },
            onOptionsBtnClicked = { notification, view ->
                showNotificationOptionsPopup(notification.id!!, view)
            }
        )
        binding.notificationRecycler.adapter = notificationAdapter
        binding.notificationRecycler.layoutManager = LinearLayoutManager(this)
    }
    // Set up page listeners and callback
    private fun setUpPageActions() {
        setUpLoadOnScrollListener()
        binding.swipeRefresh.setOnRefreshListener {
            skip = 0
            notificationList.clear()
            notificationAdapter.notifyDataSetChanged()
            notificationViewModel.callNotificationsList(skip, take)
        }
        binding.markAllAsReadBtn.setOnClickListener {
            ConfirmDialog(
                this,
                getString(R.string.notifications),
                getString(R.string.mark_all_notification_as_read),
                onYesBtnClick = {
                    notificationViewModel.callReadAllNotifications()
                },
                onNoBtnClick = {}).show()
        }
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
                            notificationViewModel.callNotificationsList(skip, take)
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
    // Set Up Notification Item Click
    private fun setUpNotificationItemClick(notification: Notification) {
        when (notification.notificationType) {
            FcmNotificationType.OrderCancelled.value -> {
                navigateToAppointment(notification)
            }
            FcmNotificationType.OrderConfirmed.value -> {
                navigateToAppointment(notification)
            }
            FcmNotificationType.OrderFinished.value -> {
                navigateToAppointment(notification)
            }
            FcmNotificationType.OrderRequested.value -> {
                navigateToAppointment(notification)
            }
            FcmNotificationType.OrderRefused.value -> {
                navigateToAppointment(notification)
            }
            else -> {
            }
        }
    }

    // Navigate To Group Appointment
    private fun navigateToAppointment(notification: Notification) {
        startActivity(
            Intent(this, OrderDetailsActivity::class.java)
                .putExtra(Keys.Intent_Constants.ORDER_ID, notification.entityId)
                .putExtra(Keys.Intent_Constants.NOTIFY_ID, notification.id)
        )
    }

    // Show Notification Options Popup
    private fun showNotificationOptionsPopup(id: String, view: View) {
        val popupMenu = PopupMenu(this, view)
        val inflater = popupMenu.menuInflater
        inflater.inflate(R.menu.notification_menu, popupMenu.menu)
        popupMenu.show()
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.read_action -> {
                    notificationViewModel.callReadNotification(id)
                }
                R.id.delete_action -> {
                    ConfirmDialog(
                        this,
                        getString(R.string.notifications),
                        getString(R.string.delete_this_notification),
                        onYesBtnClick = {
                            notificationViewModel.callDeleteNotification(id)
                        },
                        onNoBtnClick = {}).show()
                }
            }
            false
        }
    }


    /////////////////////////////////////////
    // Set up Observers
    private fun setUpObservers() {
        observeNotificationList()
        observeReadNotification()
        observeDeleteNotification()
        observeReadAllNotification()
    }

    // Observe notification list
    private fun observeNotificationList() {
        notificationViewModel.callNotificationsList(skip, take)
        notificationViewModel.observeNotificationsListResult().observe(this, Observer {
            binding.swipeRefresh.isRefreshing = false
            if (it != null && it.isNotEmpty()) {
                binding.emptyFlag.visibility = View.GONE
                notificationList.addAll(it)
                stopScrollIfDataEnded(binding.nestedScrollView, it.size)
                notificationAdapter.notifyDataSetChanged()
            } else {
                binding.emptyFlag.visibility = View.VISIBLE
            }
        })
    }

    // Observe read notification
    private fun observeReadNotification() {
        notificationViewModel.observeReadNotificationResult().observe(this, Observer {
            shoMsg(it, MotionToast.TOAST_SUCCESS)
            skip = 0
            notificationList.clear()
            notificationAdapter.notifyDataSetChanged()
            notificationViewModel.callNotificationsList(skip, take)
        })
    }

    // Observe read all notification
    private fun observeReadAllNotification() {
        notificationViewModel.observeReadAllNotificationResult().observe(this, Observer {
            sessionHelper.userProfile.unSeenNotificationsCount = 0
            binding.markAllAsReadBtn.visibility = View.GONE
            shoMsg(it, MotionToast.TOAST_SUCCESS)
            skip = 0
            notificationList.clear()
            notificationAdapter.notifyDataSetChanged()
            notificationViewModel.callNotificationsList(skip, take)
        })
    }

    // Observe delete notification
    private fun observeDeleteNotification() {
        notificationViewModel.observeDeleteNotificationResult().observe(this, Observer {
            shoMsg(it, MotionToast.TOAST_SUCCESS)
            skip = 0
            notificationList.clear()
            notificationAdapter.notifyDataSetChanged()
            notificationViewModel.callNotificationsList(skip, take)
        })
    }
}