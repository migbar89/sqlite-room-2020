package com.example.recycleview

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object datospublicos
{
    var listaproducto= mutableListOf<producto>()
    var pos=0
    var room:database?=null



    fun cargardatos() {
        GlobalScope.launch {


            listaproducto = room!!.dao().getallproductos()


        }
    }
}