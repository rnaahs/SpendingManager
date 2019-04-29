package au.com.techfields.spendingplanner.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class User(@PrimaryKey var mId: String = "",
                var mFirstName: String = "",
                var mLastName: String = "",
                var mEmail: String = "",
                var mBalance: Double = 0.0): RealmObject()
