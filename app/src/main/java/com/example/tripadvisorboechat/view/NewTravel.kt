package com.example.tripadvisorboechat.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import androidx.appcompat.app.AlertDialog
import com.example.tripadvisorboechat.R
import com.example.tripadvisorboechat.model.Travel
import com.example.tripadvisorboechat.repository.TravelRepository
import kotlinx.android.synthetic.main.activity_new_travel.*
import kotlinx.android.synthetic.main.toolbar.*

class NewTravel : AppCompatActivity(), View.OnClickListener {

    private val SALVAR: String = "Salvar";
    private val CANCELAR: String = "Cancelar";
    private val ERROR: String = "Error"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_travel)

        insertToolbar()

    }

    var typeTravel = ""

    private fun insertToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Nova Viagem"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }


    fun onCheckboxClicked(view: View) {
        if (view is CheckBox) {
            val checked: Boolean = view.isChecked

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

    override fun onClick(view: View) {
        if(view.id == R.id.toolbar){
            onBackPressed()
        }
        if(view.id == R.id.buttonLogin){
            saveTravel()
        }
    }

    private fun alert(call: String){
        val builderDialog = AlertDialog.Builder(this)
        when (call) {
            SALVAR -> {
                builderDialog.setTitle("Usuario Cadastrado")
                builderDialog.setMessage("Agora você já consegue fazer login na plataforma\n\n")
                builderDialog.setIcon(R.drawable.ic_baseline_done_24)
                builderDialog.setPositiveButton("OK"){ _, _ ->
                    onBackPressed()
                }
                builderDialog.show()
                //limparFormulario()
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
                builderDialog.setMessage("Sinto muito não foi possivel salvar seu cadastro\n\n Tente novamente mais tarde")
                builderDialog.setIcon(R.drawable.ic_baseline_clear_30)
                builderDialog.setPositiveButton("OK") { _, _ ->
                    onBackPressed()
                }
                builderDialog.show()
            }
        }
    }
}