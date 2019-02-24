package au.com.techfields.spendingplanner.model

data class Category(var mId:String,
                    var mName: String,
                    var mAmount: Double,
                    var mType: String,
                    var mIconId: Int,
                    var mColorId: String)