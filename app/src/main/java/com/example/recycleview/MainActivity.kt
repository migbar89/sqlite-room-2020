package com.example.recycleview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_nuevoprod.*
import kotlinx.android.synthetic.main.item.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    var adapter = adaptador()


    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
      /* datospublicos.listaproducto.add(producto("teclado","10".toFloat(),5))
        datospublicos.listaproducto.add(producto("Pantalla","20".toFloat(),15))
        datospublicos.listaproducto.add(producto("cargador","50".toFloat(),25))
*/
        recycle_producto.layoutManager= LinearLayoutManager(this, LinearLayout.VERTICAL,false)

        recycle_producto.adapter = adapter


        datospublicos.room = database.getDatabase(this)
        cargardatos()

        btbuscar.setOnClickListener {
            if(busquedaprecio.text.toString().length==0) {
                Toast.makeText(this, "Ninguna coincidencia", Toast.LENGTH_SHORT).show()
                cargardatos()
            }
            else {

                GlobalScope.launch(Dispatchers.IO) {

                    datospublicos.listaproducto = datospublicos.room!!.dao()
                        .get_product_by_precio(busquedaprecio.text.toString().toInt())

                    withContext(Dispatchers.Main) {
                        adapter.notifyDataSetChanged()

                    }
                }
            }
        }


    }

    override fun onRestart()
    {
       cargardatos()
        super.onRestart()
    }


     fun cargardatos()
    {
   /*     GlobalScope.launch {


            datospublicos.listaproducto= room!!.dao().getallproductos()
            adapter.notifyDataSetChanged()

        }*/

        GlobalScope.launch (Dispatchers.IO) {

            datospublicos.listaproducto= datospublicos.room!!.dao().getallproductos()

            withContext(Dispatchers.Main) {
                adapter.notifyDataSetChanged()

            }
        }


    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.menu_add_producto)
        {
          /* var intent=Intent(this,activity_nuevoprod::class.java)
            startActivity(intent)*/

            val builder = AlertDialog.Builder(this)
            val inflater = layoutInflater
            //builder.setTitle("Nuevo Producot")
            val dialogLayout = inflater.inflate(R.layout.activity_nuevoprod, null)

            val etnombre  = dialogLayout.findViewById<EditText>(R.id.et_nuevo_nombre)
            val etprecio  = dialogLayout.findViewById<EditText>(R.id.et_nuevo_precio)
            val etcantidad  = dialogLayout.findViewById<EditText>(R.id.et_nuevo_cantidad)

            builder.setView(dialogLayout)
            builder.setPositiveButton("Guardar") { dialogInterface, i ->


                GlobalScope.launch {
                    // var pro=producto(0)
                    var pro=producto(0,etnombre.text.toString(),etprecio.text.toString().toFloat(),etcantidad.text.toString().toInt())


                    datospublicos.room?.dao()?.insert(pro)
                    cargardatos()

                    val snack = Snackbar.make(
                        findViewById(R.id.recycle_producto),
                        "Producto Guardado",
                        Snackbar.LENGTH_LONG)
                    snack.show()
                }







                }
            builder.show()





        }



        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_producto, menu)
        return super.onCreateOptionsMenu(menu)
    }

}
