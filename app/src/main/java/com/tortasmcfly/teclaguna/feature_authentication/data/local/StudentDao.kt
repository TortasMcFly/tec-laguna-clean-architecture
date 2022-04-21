package com.tortasmcfly.teclaguna.feature_authentication.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tortasmcfly.teclaguna.feature_authentication.data.local.entity.StudentEntity

@Dao
interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(studentEntity: StudentEntity)

    @Query("SELECT * FROM student_table LIMIT 1")
    suspend fun getStudent(): StudentEntity?

    @Query("SELECT * FROM student_table WHERE controlNumber LIKE :controlNumber LIMIT 1")
    suspend fun getStudent(controlNumber: String): StudentEntity?

    @Query("DELETE FROM student_table WHERE controlNumber LIKE :controlNumber")
    suspend fun deleteStudent(controlNumber: String)

}