package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.R
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.databinding.RecycleviewListRecipeBinding
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db.model.Recipe

class ListRecipeAdapter (
    private val recipes: List<Recipe>
): RecyclerView.Adapter<ListRecipeAdapter.RecipeListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RecipeListViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.recycleview_list_recipe,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: RecipeListViewHolder, position: Int) {
        holder.recyclerviewListRecipeBinding.recipe = recipes[position]
    }

    override fun getItemCount(): Int = recipes.size


    inner class RecipeListViewHolder(
        val recyclerviewListRecipeBinding: RecycleviewListRecipeBinding
    ): RecyclerView.ViewHolder(recyclerviewListRecipeBinding.root)

}
