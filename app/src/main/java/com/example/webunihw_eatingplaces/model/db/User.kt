package com.example.webunihw_eatingplaces.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "user")
data class User (
    @ColumnInfo(name = "userid") var id: String,
    @ColumnInfo(name = "username") var name : String,
    @ColumnInfo(name = "useremail") var email : String
)