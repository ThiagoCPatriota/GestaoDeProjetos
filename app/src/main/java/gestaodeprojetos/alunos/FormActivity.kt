package gestaodeprojetos.alunos

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import gestaodeprojetos.alunos.db.AppDatabase
import gestaodeprojetos.alunos.model.Project
import kotlinx.coroutines.launch

class FormActivity : AppCompatActivity() {

    private val database by lazy { AppDatabase.getDatabase(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        setupSpinners()

        findViewById<Button>(R.id.btnSalvar).setOnClickListener {
            val projeto = coletarDados()
            if (projeto != null) {
                lifecycleScope.launch {
                    database.projectDao().insert(projeto)
                    Toast.makeText(this@FormActivity, getString(R.string.project_saved), Toast.LENGTH_SHORT).show()
                    finish()
                }
            } else {
                Toast.makeText(this, getString(R.string.fill_project_name), Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<Button>(R.id.btnCancelar).setOnClickListener {
            finish()
        }
    }

    private fun coletarDados(): Project? {
        val nome = findViewById<EditText>(R.id.etNomeProjeto).text.toString()
        if (nome.isEmpty()) return null

        val disciplina = findViewById<Spinner>(R.id.spDisciplina).selectedItem.toString()
        val dataInicio = findViewById<EditText>(R.id.etDataInicio).text.toString()
        val dataEntrega = findViewById<EditText>(R.id.etDataEntrega).text.toString()
        val diaEstudo = findViewById<Spinner>(R.id.spDiaEstudo).selectedItem.toString()
        val horarioInicio = findViewById<EditText>(R.id.etHorarioInicio).text.toString()
        val horarioTermino = findViewById<EditText>(R.id.etHorarioTermino).text.toString()

        return Project(
            nome = nome,
            disciplina = disciplina,
            dataInicio = dataInicio,
            dataEntrega = dataEntrega,
            diaEstudo = diaEstudo,
            horarioInicio = horarioInicio,
            horarioTermino = horarioTermino,
            observacoes = "",
            isCompleted = false
        )
    }

    private fun setupSpinners() {
        val disciplinas = arrayOf("Desenvolvimento Mobile", "Banco de Dados", "Engenharia de Software")
        val adapterDisc = ArrayAdapter(this, android.R.layout.simple_spinner_item, disciplinas)
        adapterDisc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        findViewById<Spinner>(R.id.spDisciplina).adapter = adapterDisc

        val dias = arrayOf("Segunda-feira", "Terça-feira", "Quarta-feira", "Quinta-feira", "Sexta-feira")
        val adapterDias = ArrayAdapter(this, android.R.layout.simple_spinner_item, dias)
        adapterDias.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        findViewById<Spinner>(R.id.spDiaEstudo).adapter = adapterDias
    }
}