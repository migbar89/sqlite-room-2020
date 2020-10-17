package com.example.recycleview

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_nuevoprod.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

//https://www.journaldev.com/309/android-alert-dialog-using-kotlin
class activity_editar : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevoprod)
        tv_titulo.setText("Editando Producto")
        var pos=datospublicos.pos
        var pro=datospublicos.listaproducto[pos]

        et_nuevo_nombre.setText(pro.nombre)
        et_nuevo_cantidad.setText(pro.cantidad.toString())
        et_nuevo_precio.setText(pro.precio.toString())

        img_guardar.setOnClickListener {

            var builder =  AlertDialog.Builder(this);
            builder.setTitle("Editando Producto")
                .setMessage("Esta seguro que desea Editar este producto")
                .setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->
                    Toast.makeText(this,"Cancelando Accion", Toast.LENGTH_SHORT).show()

                })
                .setNeutralButton("neutral", null)
                .setPositiveButton("Si", DialogInterface.OnClickListener { dialog, which ->
                    var pro=producto(pro.id,et_nuevo_nombre.text.toString(),et_nuevo_precio.text.toString().toFloat(),et_nuevo_cantidad.text.toString().toInt())

                    GlobalScope.launch {
                        datospublicos.room?.dao()?.updateproducto(pro)
                        Log.e("Actualizando producto","Actualizando Producto");
                        finish()
                    }


                });

            builder.create().show()



        }
    }
}
