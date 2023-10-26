package com.example.asssignment5


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.driuft.random_pets_starter.PetAdapter
import okhttp3.Headers


class MainActivity : AppCompatActivity() {
    val URLlist = mutableListOf<String>()
    val nameList = mutableListOf<String>()
    val gameIndexList = mutableListOf<String>()
    private lateinit var rvPets: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvPets = findViewById(R.id.pet_list)
        getURL()
//        Log.d("nameList","nameList set")
    }
    private fun getURL(){
        val client = AsyncHttpClient()


        client["https://pokeapi.co/api/v2/pokemon/ditto", object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                Log.d("Pokemon", "response successful$json")
                val gameIndicesArray = json.jsonObject.getJSONArray("game_indices")
                for (i in 0 until gameIndicesArray.length()) {
                    val versionObject = gameIndicesArray.getJSONObject(i).getJSONObject("version")
                    val versionName = versionObject.getString("name")
                    val versionURL = versionObject.getString("url")
                    nameList.add(versionName)
                    URLlist.add(versionURL)
                }

                val gameArray = json.jsonObject.getJSONArray("game_indices")
                for (i in 0 until gameArray.length()) {
                    val gameIndex = gameArray.getJSONObject(i).getInt("game_index")
                    gameIndexList.add(gameIndex.toString())
                }
                val adapter = PetAdapter(nameList, URLlist,gameIndexList)
                rvPets.adapter = adapter
                rvPets.layoutManager = LinearLayoutManager(this@MainActivity)
                rvPets.addItemDecoration(DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL))
            }


            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Pokemon", errorResponse)
            }
        }]
    }
}

