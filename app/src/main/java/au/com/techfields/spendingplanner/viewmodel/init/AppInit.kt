package au.com.techfields.spendingplanner.viewmodel.init

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration


class AppInit : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val config = RealmConfiguration.Builder().name("spendingplanner.realm").build()
        Realm.setDefaultConfiguration(config)
    }
}