package edu.vavali.cupcakes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import edu.vavali.cupcakes.databinding.FragmentDateBinding
import java.text.NumberFormat




/**
 * A simple [Fragment] subclass.
 * Use the [DateFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DateFragment : Fragment() {


    private var _binding: FragmentDateBinding? = null
    private val binding get() = _binding!!
    private val args : DateFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDateBinding.inflate(inflater, container, false)
        var isHigherCost = false
        val cost = args.orderSummary?.price
        val formattedCost = cost?.let { NumberFormat.getCurrencyInstance().format(it) }
        val oldText = binding.priceText.text
        val newText = formattedCost + oldText
        binding.priceText.text = newText

        val higherCost = cost?.plus(7.00)
        val formattedHigherCost = higherCost?.let { NumberFormat.getCurrencyInstance().format(it) }
        val newHigherText = formattedHigherCost + oldText


        binding.dateButtons.setOnCheckedChangeListener { _, _ -> // checkedId is the RadioButton selected
            if(binding.march7Button.isChecked) {
                if (!isHigherCost){
                    isHigherCost = true
                    binding.priceText.text = newHigherText
                }
            } else {
                if(isHigherCost) {
                    binding.priceText.text = newText
                    isHigherCost = false;
                }
            }
        }



        val date = when(binding.dateButtons.checkedRadioButtonId) {
            R.id.march7Button -> "March 7"
            R.id.march8Button -> "March 8"
            R.id.march9Button -> "March 9"
            R.id.march10Button -> "March 10"
            else -> "March 11"
        }


        binding.nextButton.setOnClickListener{
            if(binding.dateButtons.checkedRadioButtonId != -1) {
                val numCupcakes = args.orderSummary?.numCupcakes ?: 0
                val flavor = args.orderSummary?.flavor ?: ""
                var orderCost = args.orderSummary?.price ?: 0.0
                if(isHigherCost) {
                    orderCost += 7.0
                }
                val order = CupcakeOrder(numCupcakes, flavor, orderCost, date)
                val action = DateFragmentDirections.actionDateFragmentToOrderSummaryFragment(order)
                view?.findNavController()?.navigate(action)
            }


        }



        binding.cancelButton.setOnClickListener{

            view?.findNavController()?.navigate(DateFragmentDirections.actionDateFragmentToHomeFragment())


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