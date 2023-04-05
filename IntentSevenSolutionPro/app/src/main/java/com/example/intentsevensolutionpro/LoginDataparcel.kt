package com.example.intentsevensolutionpro

import android.os.Parcel
import android.os.Parcelable

data class LoginDataparcel(val id :String?, val pwd: String?):Parcelable {
    //소포 열기
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    )
    //소포 담기
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(pwd)
    }
    //file descriptor이면 , 그외의 것은 0
    override fun describeContents(): Int {
        return 0
    }
    // 소포 만들기
    companion object CREATOR : Parcelable.Creator<LoginDataparcel> {
        //객체용 소포 만들기
        override fun createFromParcel(parcel: Parcel): LoginDataparcel {
            return LoginDataparcel(parcel)
        }
        //배열용 소포 만들기
        override fun newArray(size: Int): Array<LoginDataparcel?> {
            return arrayOfNulls(size)
        }
    }
}
