package com.example.tripadvisorboechat.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.tripadvisorboechat.R
import com.example.tripadvisorboechat.model.Travel
import com.example.tripadvisorboechat.repository.TravelRepository
import kotlinx.android.synthetic.main.activity_new_travel.*
import kotlinx.android.synthetic.main.toolbar.*
import java.text.SimpleDateFormat
import java.util.*

class NewTravel : AppCompatActivity(), View.OnClickListener {

    private val SALVAR: String = "Salvar";
    private val CANCELAR: String = "Cancelar";
    private val ERROR: String = "Error"
    private var checked : Boolean = false
    var typeTravel = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_travel)

        insertToolbar()
        buttonEnviar.setOnClickListener(this)

    }

    private fun insertToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Nova Viagem"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    fun onCheckboxClicked(view: View) {
        if (view is CheckBox) {
             checked = view.isChecked

            when (view.id) {
                R.id.CheckedGo -> {
                    if (checked) {
                        CheckedTwo.setChecked(false)
                        CheckeBack.setChecked(false)

                        editTextGo.setEnabled(true)
                        editTextBack.setEnabled(false)
                        editTextBack.setText("")
                        typeTravel = "Ida"
                    }
                }
                R.id.CheckeBack -> {
                    if (checked) {
                        CheckedGo.setChecked(false)
                        CheckedTwo.setChecked(false)

                        editTextBack.setEnabled(true)
                        editTextGo.setEnabled(false)
                        editTextGo.setText("")
                        typeTravel = "Volta"
                    }
                }
                R.id.CheckedTwo -> {
                    if(checked){
                        CheckedGo.setChecked(false)
                        CheckeBack.setChecked(false)

                        editTextGo.setEnabled(true)
                        editTextBack.setEnabled(true)
                        typeTravel = "Ida e Volta"
                    }
                }
            }
        }
    }

    private fun saveTravel(){
        val travel = Travel(
            idTravel = 0,
            dateBack = editTextBack.text.toString(),
            dateGo = editTextGo.text.toString(),
            amounttickets = editTextAmount.text.toString(),
            origin = spinnerOrigin.selectedItem.toString(),
            destiny = spinnerDestiny.selectedItem.toString(),
            classification = spinnerType.selectedItem.toString(),
            typeTrip = typeTravel
        )
        val repository = TravelRepository(this)
        val id = repository.saveTravel(travel)
        if(id > 0 ){
            alert(SALVAR)
        }
        else{
            alert(ERROR)
        }
    }

    private fun validateForm() : Boolean{
        if(checked && editTextAmount.text.toString().length > 0  ){
           if(typeTravel == "Volta" && editTextBack.text.toString().length > 8){
               return true
           }
           else if(typeTravel == "Ida" && editTextGo.text.toString().length > 8){
                return true
           }
           else if(typeTravel == "Ida e Volta"){
                if(editTextBack.text.toString().length > 8 && editTextGo.text.toString().length > 8){
                    return true
                }
            }

        }

        return false
    }

    override fun onClick(view: View) {
        if(view.id == R.id.toolbar){
            onBackPressed()
        }
        if(view.id == R.id.buttonEnviar){
            if(validateForm()){
                saveTravel()
            }
            else{
                Toast.makeText(this, "Preencha as Informações da Viagem", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun alert(call: String){
        val builderDialog = AlertDialog.Builder(this)
        when (call) {
            SALVAR -> {
                builderDialog.setTitle("Viagem Agendada")
                builderDialog.setMessage("Sua Viagem foi Agendada com Sucesso\n\n")
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
                builderDialog.setMessage("Sinto muito não foi possivel salvar os dados da sua viagem\n\n Tente novamente mais tarde")
                builderDialog.setIcon(R.drawable.ic_baseline_clear_30)
                builderDialog.setPositiveButton("OK") { _, _ ->
                    onBackPressed()
                }
                builderDialog.show()
            }
        }

    }

}