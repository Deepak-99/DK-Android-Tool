package com.hawkshaw.library.datalayer.room.telephony

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity representing a phone number to be blocked
 */
@Entity(tableName = "call_block_entity")
data class CallBlockEntity(
    @ColumnInfo(name = "name")
    var name: String = "",
    
    @ColumnInfo(name = "number")
    var number: String = "",
    
    @ColumnInfo(name = "block_outgoing")
    var blockOutgoing: Boolean = false,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
) 