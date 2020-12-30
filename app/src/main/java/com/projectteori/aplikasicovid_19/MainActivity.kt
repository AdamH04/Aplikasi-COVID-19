package com.projectteori.aplikasicovid_19

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.projectteori.aplikasicovid_19.api.RetrofitClient
import com.projectteori.aplikasicovid_19.model.IndonesiaResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showIndonesia()
    }

    private fun showIndonesia(){
        RetrofitClient.instance.getIndonesia().enqueue(object :
                Callback<ArrayList<IndonesiaResponse>>{
        override fun onFailure(call: Call<ArrayList<IndonesiaResponse>>, t: Throwable) {
           Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
        }

        override fun onResponse(
                call: Call<ArrayList<IndonesiaResponse>>,
                response: Response<ArrayList<IndonesiaResponse>>
        ){
            val Indonesia = response.body()?.get(0)
            val positive = Indonesia?.positif
            val hospitalized = Indonesia?.dirawat
            val recover = Indonesia?.sembuh
            val death = Indonesia?.meninggal

            val cassPositive : TextView = findViewById(R.id.tvPositive)
            cassPositive.text= positive

            val cassHospitalized :TextView = findViewById(R.id.tvHospitalized)
            cassHospitalized.text= hospitalized

            val cassRecover :TextView = findViewById(R.id.tvRecover)
            cassRecover.text= recover

            val cassDeath :TextView = findViewById(R.id.tvDeath)
            cassDeath.text= death
        }
        })
    }
}