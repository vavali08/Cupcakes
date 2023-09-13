package edu.vavali.cupcakes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import edu.vavali.cupcakes.databinding.FragmentFlavorsBinding
import java.text.NumberFormat


/**
 * A simple [Fragment] subclass.
 * Use the [FlavorsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FlavorsFragment : Fragment() {

    private var _binding: FragmentFlavorsBinding? = null
    private val binding get() = _binding!!
    private val args : FlavorsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFlavorsBinding.inflate(inflater, container, false)
        val cost = args.Order?.numCupcakes?.times(3.60)
        val formattedCost = NumberFormat.getCurrencyInstance().format(cost)
        val oldText = binding.priceText.text
        val newText = formattedCost + oldText
        binding.priceText.text = newText

        binding.cancelButton.setOnClickListener{
            view?.findNavController()?.navigate(FlavorsFragmentDirections.actionFlavorsFragmentToHomeFragment())
        }

        val flavor = when (binding.flavorButtons.checkedRadioButtonId) {
            R.id.chocolateButton -> "Chocolate"
            R.id.vanillaButton -> "Vanilla"
            R.id.birthdayButton -> "Birthday Cake"
            R.id.carrotButton -> "Carrot Cake"
            else -> "Strawberry"
        }

        binding.nextButton.setOnClickListener{
            if(binding.flavorButtons.checkedRadioButtonId != -1) {
                val order = CupcakeOrder(args.Order!!.numCupcakes, flavor, cost!!)
                val action = FlavorsFragmentDirections.actionFlavorsFragmentToDateFragment(order)
                view?.findNavController()?.navigate(action)
            }
        }

        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //ensures that you either navigate through navigation controller of call superconstructor
        // of that function
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController()) ||
                super.onContextItemSelected(item)
    }



}