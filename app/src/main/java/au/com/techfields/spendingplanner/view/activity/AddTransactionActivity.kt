package au.com.techfields.spendingplanner.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import au.com.techfields.spendingplanner.R

import kotlinx.android.synthetic.main.activity_add_transaction.*

class AddTransactionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_transaction)
        setSupportActionBar(toolbar)
    }

}