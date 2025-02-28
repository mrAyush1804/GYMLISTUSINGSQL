package com.example.gymlist.firestoreprectice

import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
class FirestoreManager private constructor() {
    private val db: FirebaseFirestore by lazy { Firebase.firestore }

    companion object {
        @Volatile
        private var instance: FirestoreManager? = null

        fun getInstance(): FirestoreManager {
            return instance ?: synchronized(this) {
                instance ?: FirestoreManager().also { instance = it }
            }
        }
    }

    fun <T : Any> savedata(
        collection: String,
        documentId: String,
        data: T,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit

    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                db.collection(collection)
                    .document(documentId)
                    .set(data)
                    .await()

                // Switch to Main thread for callback
                CoroutineScope(Dispatchers.Main).launch {
                    onSuccess()
                }
            } catch (e: Exception) {
                CoroutineScope(Dispatchers.Main).launch {
                    onFailure(e)
                }
            }
        }
    }

    suspend fun <T : Any> saveDataSuspend(
        collection: String,
        documentId: String,
        data: T
    ): Result<Unit> {
        return suspendCoroutine { continuation ->
            db.collection(collection)
                .document(documentId)
                .set(data)
                .addOnSuccessListener { continuation.resume(Result.success(Unit)) }
                .addOnFailureListener { e -> continuation.resumeWithException(e) }
        }
    }
}
