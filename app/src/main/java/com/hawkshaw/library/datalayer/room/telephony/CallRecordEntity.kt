package com.hawkshaw.library.datalayer.room.telephony

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity representing a phone number to be recorded
 */
@Entity(tableName = "call_record_entity")
data class CallRecordEntity(
    @ColumnInfo(name = "name")
    var name: String = "",
    
    @ColumnInfo(name = "number")
    var number: String = "",

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
) 