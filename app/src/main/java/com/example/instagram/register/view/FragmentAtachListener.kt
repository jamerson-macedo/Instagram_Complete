package com.example.instagram.register.view
// comunicação entre fragment e activty
interface FragmentAtachListener {
    fun goToNameAndPassword(email:String)
    fun goToWelcomeScreen(nome:String)
    fun goToUploadPhotoScreen()
    fun goToMainScreen()
}