package com.tortasmcfly.teclaguna.feature_authentication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tortasmcfly.teclaguna.feature_authentication.data.local.entity.StudentEntity

@Database(
    entities = [StudentEntity::class],
    version = 1
)
abstract class StudentDatabase: RoomDatabase() {
    abstract val dao: StudentDao
}