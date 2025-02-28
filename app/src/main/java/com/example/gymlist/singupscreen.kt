package com.example.gymlist

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.gymlist.firestoreprectice.FirestoreManager
import com.example.gymlist.firestoreprectice.User

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [singupscreen.newInstance] factory method to
 * create an instance of this fragment.
 */
@Suppress("NAME_SHADOWING")
class singupscreen : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val firestoreManager = FirestoreManager.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_singupscreen, container, false)
        val name = view.findViewById<EditText>(R.id.Exitextname)
        val email = view.findViewById<EditText>(R.id.editemail)
        val phone = view.findViewById<EditText>(R.id.phone)
        val password = view.findViewById<EditText>(R.id.password0ne)

        view.findViewById<Button>(R.id.btnlogin).setOnClickListener {
            signupUser(name, email, phone, password)
        }

        return view
    }

    private fun signupUser(name: EditText, email: EditText, phone: EditText, paswword: EditText) {
        val name = name.text.toString().trim()
        val email = email.text.toString().trim()
        val phone = phone.text.toString().trim()
        val password = paswword.text.toString().trim()

        // Validation
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // Create User object
        val user = User(name, email, phone, password)

        // Save to Firestore using FirestoreManager
        firestoreManager.savedata(
            collection = "users",
            documentId = email, // Email as unique ID
            data = user,
            onSuccess = {
                Toast.makeText(requireContext(), "Signup Successful!", Toast.LENGTH_SHORT).show()
                findNavController().navigate("otp") // Navigate to OTP
            },
            onFailure = { e ->
                Toast.makeText(requireContext(), "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        )
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment singupscreen.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            singupscreen().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
