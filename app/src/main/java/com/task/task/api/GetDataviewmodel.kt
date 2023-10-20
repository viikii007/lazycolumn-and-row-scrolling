package com.task.task.api

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.task.task.api.model.VendarDetails

class GetDataviewmodel(application: Application): AndroidViewModel(application)
{

        private var repository: GetDataRepo? = null

        init {
            repository = GetDataRepo()
        }


  suspend  fun GetDataInfo(): DataOrException<VendarDetails, Boolean, Exception>?
    {
       return repository?.GetProfileInfo()
    }





}