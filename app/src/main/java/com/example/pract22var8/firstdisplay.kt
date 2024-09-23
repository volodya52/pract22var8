package com.example.pract22var8

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import org.json.JSONObject

class firstdisplay : AppCompatActivity() {
    lateinit var receipt: EditText
    lateinit var button: Button
    lateinit var imageview: ImageView
    lateinit var name1: TextView
    lateinit var callories: TextView
    lateinit var uri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firstdisplay)
        receipt = findViewById(R.id.edittext)
        button = findViewById(R.id.button)
        imageview = findViewById(R.id.photo)
        name1 = findViewById(R.id.name)
        callories = findViewById(R.id.callories)
        button.setOnClickListener {
            GetInfo(receipt.text.toString())
        }
    }

    fun GetInfo(nazvanie: String) {
        if (receipt.text.isNotEmpty()) {
            name1.text = receipt.text
            val key = "0032416b9e54404db1f1d75086c1a474"
            val url = "https://api.spoonacular.com/food/menuItems/search?apiKey=${key}&query=${receipt.text.toString()}"
            val url2 = "https://spoonacular.com/menuItemImages/${receipt.text.toString()}.jpg"
            val queue = Volley.newRequestQueue(this)
            val stringRequest = StringRequest(
                Request.Method.GET, url,
                { response ->
                    val obj = JSONObject(response)
                    val menuItems = obj.getJSONArray("menuItems")
                    if (menuItems.length() > 0) {
                        val firstItem = menuItems.getJSONObject(0)
                        val restaurantChain = firstItem.getString("restaurantChain")
                        callories.text = restaurantChain
                        Log.d("MyLog", "Response: ${restaurantChain}")
                        Picasso.get().load(url2).into(imageview)
                    }
                },
                { error ->
                    Log.e("MyLog", "Error: ${error.message}")
                }
            )
            queue.add(stringRequest)
        }
    }
}
