package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.RecipeDatabase
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.model.RecipeWithSteps
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.repository.RecipeRepository
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.databinding.FragmentDetailRecipeBinding
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.ui.list.ListRecipeAdapter
import kotlinx.android.synthetic.main.fragment_list_recipe.*


class DetailRecipeFragment : Fragment() {

    private lateinit var factory: DetailRecipeViewModelFactory
    private lateinit var viewModel: DetailRecipeViewModel
    private lateinit var binding: FragmentDetailRecipeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val dao = RecipeDatabase.getInstance(requireActivity()).recipeDao
        val repository = RecipeRepository(dao)

        val recipeWithStep: RecipeWithSteps? = activity?.intent?.getSerializableExtra("recipeWithSteps") as? RecipeWithSteps

        factory = DetailRecipeViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(DetailRecipeViewModel::class.java)

        binding.lifecycleOwner = this
        binding.detailRecipeViewModel = viewModel

        if (recipeWithStep != null) {
            viewModel.setRecipeWithSteps(recipeWithStep)
        }

    }

}