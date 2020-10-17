package com.example.recycleview

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "producto")
class producto(
  @PrimaryKey(autoGenerate = true)  @ColumnInfo(name = "id") val id: Int,
  @ColumnInfo(name = "nombre")  var nombre:String,
  @ColumnInfo(name = "precio") var precio:Float,
  @ColumnInfo(name = "cantidad")  var cantidad:Int)
{

}