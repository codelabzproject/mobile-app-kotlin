package com.example.mobile_app_kotlin.view.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_app_kotlin.databinding.FragmentCardTimelineBinding
import com.example.mobile_app_kotlin.service.listener.CodeListener
import com.example.mobile_app_kotlin.service.model.response.PostModel

class CodeViewHolder(private val itemBinding: FragmentCardTimelineBinding, val listener: CodeListener) :
    RecyclerView.ViewHolder(itemBinding.root) {

    /**
     * Atribui valores aos elementos de interface
     */
    fun bindData(postModel: PostModel) {

        itemBinding.titlePost.text = postModel.title
        itemBinding.contentPost.text = postModel.content
        itemBinding.commentsPosts.text = postModel.comments.toString()
        itemBinding.liskes.text = postModel.points.toString()

//        itemBinding.liskes.text = postModel.points.toString()
//        itemBinding.commentsPosts.text = postModel.comments.toString()

//        val date = SimpleDateFormat("yyyy-MM-dd").parse(postModel.createdIn)
//        itemBinding.textDueDate.text = SimpleDateFormat("dd/MM/yyyy").format(date)

        // Faz o tratamento das imagens
//        if (postModel.complete) {
//            itemBinding.imageTask.setImageResource(R.drawable.ic_done)
//        } else {
//            itemBinding.imageTask.setImageResource(R.drawable.ic_todo)
//        }

//        // Eventos
//        itemBinding.textDescription.setOnClickListener { listener.onListClick(postModel.id) }
//        itemBinding.imageTask.setOnClickListener {
//            if (postModel.complete) {
//                listener.onUndoClick(postModel.id)
//            } else {
//                listener.onCompleteClick(postModel.id)
//            }
//        }

//        itemBinding.textDescription.setOnLongClickListener {
//            AlertDialog.Builder(itemView.context)
//                .setTitle(R.string.remocao_de_tarefa)
//                .setMessage(R.string.remover_tarefa)
//                .setPositiveButton(R.string.sim) { dialog, which ->
//                    listener.onDeleteClick(postModel.id)
//                }
//                .setNeutralButton(R.string.cancelar, null)
//                .show()
//            true
//        }

    }
}