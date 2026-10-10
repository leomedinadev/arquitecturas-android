package com.example.apparquitectura.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.apparquitectura.R
import com.example.apparquitectura.databinding.ActivityMainBinding
import com.example.apparquitectura.model.Coupon
import com.example.apparquitectura.viewmodel.CouponViewModel

class MainActivity : AppCompatActivity() {

    private var couponViewModel: CouponViewModel ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        //View
        setUpBindings(savedInstanceState)
    }

    fun setUpBindings(savedInstanceState: Bundle?) {
        var activityMainBinding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        couponViewModel = ViewModelProviders.of(this).get(CouponViewModel::class.java)

        activityMainBinding.setModel(couponViewModel)
        setUpListUpdate()
    }

    fun setUpListUpdate() {
        couponViewModel?.callCoupons()
        couponViewModel?.getCoupons()?.observe(this, Observer { coupons: List<Coupon> ->
            Log.i("COUPONS", "Cupones recibidos: ${coupons.size}")
            couponViewModel?.setCouponsInRecyclerAdapter(coupons)
        })
    }
}