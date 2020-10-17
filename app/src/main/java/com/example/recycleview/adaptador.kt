package com.example.recycleview
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class adaptador() : RecyclerView.Adapter<adaptador.Viewholder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adaptador.Viewholder {

        val v= LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        //Toast.makeText(p0.context,firestore.listacliente.size.toString(),Toast.LENGTH_SHORT).show()
        return Viewholder(v)
    }


    override fun getItemCount(): Int {
        return   datospublicos.listaproducto.size
    }

    override fun onBindViewHolder(holder: adaptador.Viewholder, position: Int) {
        var pro=   datospublicos.listaproducto.get(position)
        holder.tvnombre.setText(pro.nombre.toString());
        holder.tvprecio.setText(pro.precio.toString());
        holder.tvcantidad.setText(pro.cantidad.toString());
        holder.img_eliminar.setOnClickListener {

           var builder =  AlertDialog.Builder(holder.itemView.context);
            builder.setTitle("Eliminar Producto")
                .setMessage("Esta seguro que quiere eliminar el producto")
                .setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->
                    Toast.makeText(holder.itemView.context,"Cancelando Accion", Toast.LENGTH_SHORT).show()

                })
                .setNeutralButton("neutral", null)
                .setPositiveButton("Si",DialogInterface.OnClickListener { dialog, which ->

                   // datospublicos.listaproducto.removeAt(position)
                    GlobalScope.launch (Dispatchers.IO) {

                      datospublicos.room?.dao()?.delete(datospublicos.listaproducto[position])
                        datospublicos.cargardatos()

                        withContext(Dispatchers.Main) {
                            notifyDataSetChanged()
                            Toast.makeText(holder.itemView.context,"Eliminando producto", Toast.LENGTH_SHORT).show()


                        }
                    }

                });

            builder.create().show()


            notifyDataSetChanged()
        }

        holder.img_editar.setOnClickListener {
            var intent=Intent(holder.itemView.context,activity_editar::class.java)
            datospublicos.pos=position
            holder.itemView.context.startActivity(intent)
        }

    }


    class Viewholder(itemview: View):RecyclerView.ViewHolder(itemview) {
        val tvnombre: TextView =itemview.tv_nombre
        val tvprecio: TextView =itemview.tv_precio
        val tvcantidad: TextView =itemview.tv_cantidad
        val img_eliminar: ImageView =itemview.img_eliminar
        val img_editar: ImageView =itemview.img_editar
    }

}