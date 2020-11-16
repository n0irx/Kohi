package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.R
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db.model.Recipe
import kotlinx.android.synthetic.main.recycleview_list_recipe.view.*

class ListRecipeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var recipes = mutableListOf<Recipe>()

    fun submitRecipes(recipeList: MutableList<Recipe>) {
        recipes = recipeList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       return ListRecipeViewHolder(
           LayoutInflater.from(parent.context).inflate(R.layout.recycleview_list_recipe, parent, false)
       )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is ListRecipeViewHolder -> {
                holder.bindItem(recipes.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    class ListRecipeViewHolder constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView) {

        val recipeImage = itemView.recipe_image
        val recipeName = itemView.recipe_name
        val recipeCoffeeBlend = itemView.recipe_coffee_blend
        val recipeGrindSize = itemView.recipe_grind_size
        val recipeBrewMethod = itemView.recipe_brew_method
        val recipePreparationTime = itemView.recipe_preparation_time
        val recipeNote = itemView.recipe_note

        fun bindItem(recipe: Recipe) {
            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .load(recipe.imageSource)
                .into(recipeImage)

            recipeName.text = recipe.name
            recipeCoffeeBlend.text = recipe.coffeeBlend
            recipeGrindSize.text = recipe.grindSize
            recipeBrewMethod.text = recipe.brewMethod

            // TODO: calculate total preparation times from steps
            recipePreparationTime.text = "0"

            recipeNote.text = recipe.note

        }
    }




}
