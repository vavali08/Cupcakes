package edu.vavali.cupcakes

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import edu.vavali.cupcakes.databinding.FragmentHomeBinding


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.oneCupcakeButton.setOnClickListener {
            val order = CupcakeOrder(1)
            val action = HomeFragmentDirections.actionHomeFragmentToFlavorsFragment(order)
            view?.findNavController()?.navigate(action)
        }

        binding.sixCupcakeButton.setOnClickListener {
            val order = CupcakeOrder(6)
            val action = HomeFragmentDirections.actionHomeFragmentToFlavorsFragment(order)
            view?.findNavController()?.navigate(action)
        }

        binding.twelveCupcakeButton.setOnClickListener {
            val order = CupcakeOrder(12)
            val action = HomeFragmentDirections.actionHomeFragmentToFlavorsFragment(order)
            view?.findNavController()?.navigate(action)
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
