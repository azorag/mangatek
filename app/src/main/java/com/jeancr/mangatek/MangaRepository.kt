package com.jeancr.mangatek

import android.net.Uri
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import com.jeancr.mangatek.MangaRepository.Singleton.databaseRef
import com.jeancr.mangatek.MangaRepository.Singleton.mangaList
import com.jeancr.mangatek.MangaRepository.Singleton.storageReference
import java.util.UUID
import javax.security.auth.callback.Callback

class MangaRepository {


    object Singleton{
        private val BUCKET_URL: String ="gs://mangatek-f27da.appspot.com"
        //connection stockage
        val storageReference= FirebaseStorage.getInstance().getReferenceFromUrl(BUCKET_URL)
        //connection db
        val databaseRef = FirebaseDatabase.getInstance("https://mangatek-f27da-default-rtdb.europe-west1.firebasedatabase.app/").getReference("mangas")
        //création de la liste
        val mangaList = arrayListOf<MangaModel>()
    }

    fun uploadImage(file:Uri){
        if (file!=null){
            val fileName =UUID.randomUUID().toString()+ ".jpg"
            val ref= storageReference.child(fileName)
            val uploadTask = ref.putFile(file)

            uploadTask.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
                if(!task.isSuccessful){
                    task.exception?.let { throw it}
                }
                return@Continuation ref.downloadUrl
            }).addOnCompleteListener { task ->
                if (task.isSuccessful){
                    val downloadUri =task.result
                }
            }
        }
    }
    
    fun updateData(callback: ()->Unit){
            databaseRef.addValueEventListener(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    mangaList.clear()
                    //récupérer la liste
                    for (ds in snapshot.children){
                        val manga = ds.getValue(MangaModel::class.java)
                        if (manga != null){
                            mangaList.add(manga)
                        }
                    }
                    callback()
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
    }


}