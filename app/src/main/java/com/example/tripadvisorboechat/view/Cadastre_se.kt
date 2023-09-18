package com.example.tripadvisorboechat.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.tripadvisorboechat.R
import com.example.tripadvisorboechat.model.User
import com.example.tripadvisorboechat.repository.TravelRepository
import com.example.tripadvisorboechat.repository.UserRepository
import kotlinx.android.synthetic.main.activity_cadastre_se.*
import kotlinx.android.synthetic.main.toolbar.*

class Cadastre_se : AppCompatActivity(), View.OnClickListener {

    private val SALVAR: String = "Salvar";
    private val CANCELAR: String = "Cancelar";
    private val ERROR: String = "Error"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastre_se)

        buttonEnviar.setOnClickListener(this)
        insertToolbar()
    }

    private fun insertToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Cadastre-se"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun saveUser(){
        val user = User(
            idUser = 0,
            name = editName.text.toString(),
            email = editEmail.text.toString(),
            cpf = editCPF.text.toString(),
            password = editPasswor.text.toString()
        )
        val repository = UserRepository(this)
        val id = repository.saveUser(user)
        if(id > 0 ){
            alert(SALVAR)
            //Toast.makeText(this, typeTravel, Toast.LENGTH_SHORT).show()
        }
        else{
            alert(ERROR)
        }
    }

    private fun alert(call: String){
        val builderDialog = AlertDialog.Builder(this)
        when (call) {
            SALVAR -> {
                builderDialog.setTitle("Login Criado")
                builderDialog.setMessage("Seu Usuário foi Criado com Sucesso\n\n")
                builderDialog.setIcon(R.drawable.ic_baseline_done_24)
                builderDialog.setPositiveButton("OK"){ _, _ ->
                    onBackPressed()
                }
                builderDialog.show()
            }
            CANCELAR -> {
                builderDialog.setTitle("Cancelar")
                builderDialog.setMessage("")
                builderDialog.setIcon(R.drawable.ic_baseline_clear_30)
                builderDialog.setPositiveButton("SIM") { _, _ ->
                    onBackPressed()
                }
                builderDialog.setNegativeButton("NÃO") { _, _ ->
                    //cadastroNome.requestFocus()
                }
                builderDialog.show()
            }
            ERROR -> {
                builderDialog.setTitle("Erro ao salvar os dados")
                builderDialog.setMessage("Sinto muito não foi possivel criar seu Usuário\n\n Tente novamente mais tarde")
                builderDialog.setIcon(R.drawable.ic_baseline_clear_30)
                builderDialog.setPositiveButton("OK") { _, _ ->
                    onBackPressed()
                }
                builderDialog.show()
            }
        }
    }

    private fun verifyForm(): Boolean{
        if(editPasswor.text.toString().equals(textConfirmPassword.text.toString())){
            if(editName.text.toString().length > 3 && editEmail.text.toString().length > 3
                && editCPF.text.toString().length > 3 && editPasswor.text.toString().length > 3){
                return true
            }
        }
        return false
    }

    override fun onClick(view: View) {
        if(view.id == R.id.buttonEnviar){
           if (verifyForm()){
               saveUser()
           }
            else{
               Toast.makeText(this, "Verifique seus Dados", Toast.LENGTH_SHORT).show()
           }
        }
    }
}