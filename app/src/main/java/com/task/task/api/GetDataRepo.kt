package com.task.task.api


import android.util.Log
import com.task.task.api.model.VendarDetails

class GetDataRepo {

    private var apiInterface_preggy: Apiinterface? = null

    init {
        apiInterface_preggy = MainApiService.Apiservicesfun()
    }

  suspend  fun GetProfileInfo():DataOrException<VendarDetails,Boolean,Exception>
    {
        val response=try {
            apiInterface_preggy?.getData(id = 127, lang = "en", userId = "")
        }
        catch (e:Exception) {
            Log.d("getdata", "datafromapi: $e")
            return DataOrException(e=e)
        }
        Log.d("failed", "messagefromapi: $response")
        return DataOrException(data = response)
    }

}


