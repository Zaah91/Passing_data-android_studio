package com.example.passindata

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.passindata.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val logTag = "ActivityLifeCycle"

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: PersonViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    val options = navOptions {
        anim {
            enter = R.anim.slide_in_right
            exit = R.anim.slide_out_left
            popEnter = R.anim.slide_in_left
            popExit = R.anim.slide_out_right
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            val name = binding.nameInput.text.trim().toString()
            if (name.isEmpty()){
                binding.nameInput.error = "Indtast navn"
                return@setOnClickListener
            }

            val ageStr = binding.ageInput.text.trim().toString()
            if (ageStr.isEmpty()){
                binding.ageInput.error = "Indtast alder"
                return@setOnClickListener
            }

            val address = binding.addressInput.text.trim().toString()
            if (address.isEmpty()){
                binding.nameInput.error = "Indtast adresse"
                return@setOnClickListener
            }

            val zipCodeStr = binding.zipCodeInput.text.trim().toString()
            if (zipCodeStr.isEmpty()){
                binding.ageInput.error = "Indtast postnummer"
                return@setOnClickListener
            }

            viewModel.personMutableLiveData.value = PersonCollectedData(name, ageStr.toInt(), address, zipCodeStr.toInt())

            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)

            Log.d(logTag, "Person info sendt")
        }

        binding.buttonThird.setOnClickListener{
            findNavController().navigate(R.id.action_FirstFragment_to_thirdFragment, null, options)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}