package com.example.oneul.data

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "diary_table")
data class Diary(
    @PrimaryKey @ColumnInfo(name = "date")
    var date: String,
    var images: String? = null,
    var imagesUrl: String? = null,
    var mood: Int? = null,
    var diary: String? = null
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(date)
        parcel.writeString(images)
        parcel.writeString(imagesUrl)
        parcel.writeValue(mood)
        parcel.writeString(diary)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Diary> {
        override fun createFromParcel(parcel: Parcel): Diary {
            return Diary(parcel)
        }

        override fun newArray(size: Int): Array<Diary?> {
            return arrayOfNulls(size)
        }
    }
}