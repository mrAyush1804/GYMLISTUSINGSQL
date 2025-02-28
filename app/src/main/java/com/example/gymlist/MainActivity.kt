package com.example.gymlist
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController as NavController1
import androidx.navigation.NavGraph
import androidx.navigation.createGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.fragment

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController1

    override fun onCreate(savedInstanceState: Bundle?) {
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val graph = createGraph(startDestination = "splash")
        navController.graph = graph
    }

    private fun createGraph(startDestination: String): NavGraph {
        return navController.createGraph(startDestination = startDestination) {
            fragment<splashcreenfragment>("splash") {
                label = "Splash Screen"
            }
            fragment<loginscreen>("login") {
                label = "Login Screen"
            }
            fragment<singupscreen>("signup") {
                label = "Sign Up Screen"
            }
            fragment<otpfragment>("otp") {
                label = " Otp Screen"
            }
            fragment<forgetphonenumberscreen>("forgotphone") {
                label = " fotget Screen"
            }
            fragment<changepasswordscreen>("Changepassswor") {
                label = " chanegepassword Screen"
            }
        }
    }
}
