package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.R
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.databinding.RecyclerviewListRecipeBinding
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.model.RecipeWithSteps

class ListRecipeAdapter (
    private val recipesWithSteps: List<RecipeWithSteps>,
    private val listener: OnRecipeCickListener
): RecyclerView.Adapter<ListRecipeAdapter.RecipeListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RecipeListViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.recyclerview_list_recipe,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: RecipeListViewHolder, position: Int) {
        holder.recyclerviewListRecipeBinding.recipesWithSteps = recipesWithSteps[position]
    }

    override fun getItemCount(): Int = recipesWithSteps.size


    inner class RecipeListViewHolder(
        val recyclerviewListRecipeBinding: RecyclerviewListRecipeBinding,
    ): RecyclerView.ViewHolder(recyclerviewListRecipeBinding.root), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position: Int = adapterPosition
            if(position != RecyclerView.NO_POSITION) {
                listener.onRecipeClick(position)
            }
        }
    }

    interface OnRecipeCickListener {
        fun onRecipeClick(position: Int)
    }

}
