

val graph = createGraph(startDestination = "splash")
navController.graph = graph
createGraph: Is function mein app ke fragments ko define kiya gaya hai. Har fragment ek screen hai jo user navigate karega.
3. createGraph Function
Is function mein fragments ko define kiya gaya hai:

kotlin
Copy
Edit
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
    // aur fragments...
}
Explanation:

startDestination: Pehli screen kaunsa show karni hai, yahaan "splash" set hai.
fragment: Har fragment ke liye ek route define kiya gaya hai:
fragment<splashcreenfragment>("splash") ka matlab hai ki jab "splash" route access hoga, toh splashcreenfragment open hoga.
label ka use navigation ke titles define karne ke liye hota hai.gg
