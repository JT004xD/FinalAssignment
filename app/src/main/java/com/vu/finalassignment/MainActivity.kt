package com.vu.finalassignment

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.material.button.MaterialButton
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var progressCircular: CircularProgressIndicator
    private lateinit var loginButton: MaterialButton
    private lateinit var usernameEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        progressCircular = findViewById(R.id.progress_circular)
        loginButton = findViewById(R.id.login_button)
        usernameEditText = findViewById(R.id.username)
        passwordEditText = findViewById(R.id.password)

        usernameEditText = findViewById<TextInputLayout>(R.id.usernameLayout).editText as TextInputEditText
        passwordEditText = findViewById<TextInputLayout>(R.id.passwordLayout).editText as TextInputEditText

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("https://vu-nit3213-api.onrender.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        loginButton.setOnClickListener {
            progressCircular.visibility = View.VISIBLE

            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            lifecycleScope.launch {
                try {
                    val response = apiService.login(LoginRequest(username, password))
                    // Handle successful login, e.g., navigate to dashboard
                } catch (e: Exception) {
                    // Handle login error, e.g., show error message
                } finally {
                    progressCircular.visibility = View.GONE
                }
            }
        }
    }
}