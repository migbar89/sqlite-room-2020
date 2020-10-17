package com.example.recycleview

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_nuevoprod.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class activity_nuevoprod : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevoprod)
      val db = database.getDatabase(this)
        img_guardar.setOnClickListener {
           // datospublicos.listaproducto.add(pro)

          GlobalScope.launch {
           // var pro=producto(0)
            var pro=producto(0,et_nuevo_nombre.text.toString(),et_nuevo_precio.text.toString().toFloat(),et_nuevo_cantidad.text.toString().toInt())

          /*  pro.precio=tvprecio.text.toString().toFloat()
            pro.nombre=tvnombre.text.toString()
            pro.cantidad=tvcantidad.text.toString().toInt()
            pro.categoria=tvcategoria.text.toString()*/

            db.dao().insert(pro)
            val snack = Snackbar.make(
              findViewById(R.id.img_guardar),
              "Producto Guardado",Snackbar.LENGTH_LONG).setAction("Salir",
              {
                //Toast.makeText(this,"Cancelando Accion", Toast.LENGTH_SHORT).show()
                finish()

              })
            snack.show()
          }


           // finish()
        }
    }

}
