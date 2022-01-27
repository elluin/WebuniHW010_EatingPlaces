package com.example.webunihw_eatingplaces.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User (
    @PrimaryKey(autoGenerate = true) var id: Long?,
    @ColumnInfo(name = "userid") var userId: String,
    @ColumnInfo(name = "username") var name : String,
    @ColumnInfo(name = "useremail") var email : String,
    @ColumnInfo(name = "usertoken") var token : String
)