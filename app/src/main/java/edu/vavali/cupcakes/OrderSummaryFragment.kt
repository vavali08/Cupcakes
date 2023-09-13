package edu.vavali.cupcakes

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import edu.vavali.cupcakes.databinding.FragmentDateBinding
import edu.vavali.cupcakes.databinding.FragmentOrderSummaryBinding
import java.text.NumberFormat

class OrderSummaryFragment : Fragment() {

    private var _binding: FragmentOrderSummaryBinding? = null
    private val binding get() = _binding!!
    private val args : OrderSummaryFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOrderSummaryBinding.inflate(inflater, container, false)

        binding.quantityInfoText.text = args.order?.numCupcakes.toString() + " Cupcake(s)"

        binding.flavorInfoText.text = args.order?.flavor.toString()

        binding.dateInfoText.text = args.order?.date.toString()

        val cost = args.order?.price
        val formattedCost = cost?.let { NumberFormat.getCurrencyInstance().format(it) }
        val oldText = binding.priceText.text
        val newText = formattedCost + oldText
        binding.priceText.text = newText

        binding.cancelButton.setOnClickListener{
            view?.findNavController()?.navigate(OrderSummaryFragmentDirections.actionOrderSummaryFragmentToHomeFragment())
        }

        binding.nextButton.setOnClickListener{
            shareOrder();
        }





        return binding.root
    }


    private fun getShareIntent(): Intent {
        val args : OrderSummaryFragmentArgs by navArgs()
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Quantity: ${args.order?.numCupcakes}\n" +
                "Flavor: ${args.order?.flavor}," +
                "\nPickup Date: ${args.order?.date}" +
                "\nSubtotal: $${args.order?.price} ")
        return shareIntent
    }
    private fun shareOrder() {
        startActivity(getShareIntent())
    }
}