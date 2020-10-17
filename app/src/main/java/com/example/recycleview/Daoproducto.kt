package com.example.recycleview

import androidx.room.*

@Dao
interface Daoproducto
{
  @Query("SELECT * from producto")
  fun getallproductos(): MutableList<producto>

  @Query("SELECT * from producto where precio=:precio")
  fun get_product_by_precio(precio:Int): MutableList<producto>

  @Insert
  suspend fun insert(producto: producto)
  @Update
  fun updateproducto(pro: producto)

  @Query("DELETE FROM producto")
  suspend fun deleteAll()
  @Delete
  suspend fun delete(pro: producto)
}