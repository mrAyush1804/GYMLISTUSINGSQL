package com.example.gymlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class splashcreenfragment : Fragment(R.layout.fragment_splashcreenfragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewLifecycleOwner.lifecycleScope.launch {
            delay(3000L)
            navigateToLogin()
        }


    }

    private fun navigateToLogin() {
        val navController = requireActivity().supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment)
            ?.childFragmentManager
            ?.fragments
            ?.firstOrNull()
            ?.findNavController()

        navController?.navigate("login")
    }


}