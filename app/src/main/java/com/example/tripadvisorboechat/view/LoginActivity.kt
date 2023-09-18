package com.example.tripadvisorboechat.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.tripadvisorboechat.R
import com.example.tripadvisorboechat.model.User
import com.example.tripadvisorboechat.repository.UserRepository
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.toolbar.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        buttonLogin.setOnClickListener(this)
        buttonNewUser.setOnClickListener(this)

        insertToolbar()
    }

    private fun insertToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Login"
        //supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.buttonLogin){

            val repository = UserRepository(this)
            val email = textUserName.text.toString()
            val senha = textPasssword.text.toString()

            val user = repository.login(email, senha)

            if (user != null) {
                textUserName.setText("")
                textPasssword.setText("")
                textUserName.requestFocus()
                val openActivity = Intent(this, MainActivity::class.java);
                startActivity(openActivity)
            } else {
                // O login falhou, você pode exibir uma mensagem de erro aqui
                Log.d("LoginActivity", "Login falhou")
                Toast.makeText(this, "Usuário não Encontrado", Toast.LENGTH_SHORT).show()
            }

        }
        else if(view.id == R.id.buttonNewUser){
            val openActivity = Intent(this, Cadastre_se::class.java)
            startActivity(openActivity)
        }
    }
}