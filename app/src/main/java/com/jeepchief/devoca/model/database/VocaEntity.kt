package com.jeepchief.devoca.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class VocaEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "vid")
    var vid: Int = 0,

    @ColumnInfo(name = "voca_name")
    var vocaName: String,

    @ColumnInfo(name = "voca_desc")
    var vocaDesc: String,

    @ColumnInfo(name = "voca_from")
    var vocaFrom: String?
)