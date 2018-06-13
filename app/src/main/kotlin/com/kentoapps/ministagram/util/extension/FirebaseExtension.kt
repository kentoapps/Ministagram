package com.kentoapps.ministagram.util.extension

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

fun FirebaseAuth.createUser(email: String,
                            password: String,
                            complete: (Task<AuthResult>) -> Unit) {
    createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(complete)
}

fun FirebaseAuth.signIn(email: String,
                        password: String,
                        complete: (Task<AuthResult>) -> Unit) {
    signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(complete)
}