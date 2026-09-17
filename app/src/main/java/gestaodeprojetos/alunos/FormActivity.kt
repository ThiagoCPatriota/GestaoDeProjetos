package gestaodeprojetos.alunos

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import gestaodeprojetos.alunos.data.ProjectStorage
import gestaodeprojetos.alunos.model.Project
import java.util.Calendar
import java.util.Locale


class FormActivity : AppCompatActivity() {

    private lateinit var etNomeProjeto: EditText
    private lateinit var etDisciplina: EditText
    private lateinit var etDataInicio: EditText
    private lateinit var etDataEntrega: EditText
    private lateinit var etDiaEstudo: EditText
    private lateinit var etHorarioInicio: EditText
    private lateinit var etHorarioTermino: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        etNomeProjeto = findViewById(R.id.etNomeProjeto)
        etDisciplina = findViewById(R.id.etDisciplina)
        etDataInicio = findViewById(R.id.etDataInicio)
        etDataEntrega = findViewById(R.id.etDataEntrega)
        etDiaEstudo = findViewById(R.id.etDiaEstudo)
        etHorarioInicio = findViewById(R.id.etHorarioInicio)
        etHorarioTermino = findViewById(R.id.etHorarioTermino)

        // RF-03: seletores de data e horário usando componentes Android nativos se o ícone for clicado.
        findViewById<ImageView>(R.id.ivIconDataInicio).setOnClickListener { abrirDatePicker(etDataInicio) }
        findViewById<ImageView>(R.id.ivIconDataEntrega).setOnClickListener { abrirDatePicker(etDataEntrega) }
        findViewById<ImageView>(R.id.ivIconDiaEstudo).setOnClickListener { abrirDatePicker(etDiaEstudo) }
        findViewById<ImageView>(R.id.ivIconHorarioInicio).setOnClickListener { abrirTimePicker(etHorarioInicio) }
        findViewById<ImageView>(R.id.ivIconHorarioTermino).setOnClickListener { abrirTimePicker(etHorarioTermino) }

        findViewById<Button>(R.id.btnSalvar).setOnClickListener { salvarProjeto() }
        findViewById<Button>(R.id.btnCancelar).setOnClickListener { finish() }
    }

    private fun salvarProjeto() {
        val nome = etNomeProjeto.text.toString().trim()
        val disciplina = etDisciplina.text.toString().trim()
        val dataInicio = etDataInicio.text.toString()
        val dataEntrega = etDataEntrega.text.toString()
        val diaEstudo = etDiaEstudo.text.toString()
        val horarioInicio = etHorarioInicio.text.toString()
        val horarioTermino = etHorarioTermino.text.toString()

        // RF-08 (Dia 11): impedir salvamento quando campos obrigatórios
        // estiverem vazios.
        val erro = validarCampos(
            nome, disciplina, dataInicio, dataEntrega,
            diaEstudo, horarioInicio, horarioTermino
        )
        if (erro != null) {
            Toast.makeText(this, erro, Toast.LENGTH_SHORT).show()
            return
        }

        val projeto = Project(
            nome = nome,
            disciplina = disciplina,
            dataInicio = dataInicio,
            dataEntrega = dataEntrega,
            diaEstudo = diaEstudo,
            horarioInicio = horarioInicio,
            horarioTermino = horarioTermino
        )

        // RF-09 (Dia 12): persistência simples, sem prejudicar o fluxo
        // principal — se falhar, o app continua funcionando normalmente,
        // só não restaura o projeto na próxima abertura.
        try {
            ProjectStorage.salvarUltimoProjeto(this, projeto)
        } catch (e: Exception) {
            // Persistência é "desejável" (RF-09), não obrigatória (risco R1).
        }

        // RF-04/RF-05 (Dia 8): Intent explícita com os dados reais via putExtra.
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_NOME, projeto.nome)
            putExtra(DetailActivity.EXTRA_DISCIPLINA, projeto.disciplina)
            putExtra(DetailActivity.EXTRA_INICIO, projeto.dataInicio)
            putExtra(DetailActivity.EXTRA_ENTREGA, projeto.dataEntrega)
            putExtra(DetailActivity.EXTRA_DIA_ESTUDO, projeto.diaEstudo)
            putExtra(DetailActivity.EXTRA_HORARIO_INICIO, projeto.horarioInicio)
            putExtra(DetailActivity.EXTRA_HORARIO_TERMINO, projeto.horarioTermino)
        }
        startActivity(intent)
        finish()
    }

    private fun validarCampos(
        nome: String,
        disciplina: String,
        dataInicio: String,
        dataEntrega: String,
        diaEstudo: String,
        horarioInicio: String,
        horarioTermino: String
    ): String? = when {
        nome.isBlank() -> "Informe o nome do projeto"
        disciplina.isBlank() -> "Informe a disciplina"
        dataInicio.isBlank() -> "Informe a data de início"
        dataEntrega.isBlank() -> "Informe a data de entrega"
        diaEstudo.isBlank() -> "Informe o dia de estudo"
        horarioInicio.isBlank() -> "Informe o horário de início"
        horarioTermino.isBlank() -> "Informe o horário de término"
        else -> null
    }

    private fun abrirDatePicker(campo: EditText) {
        val calendario = Calendar.getInstance()
        DatePickerDialog(
            this,
            { _, ano, mes, dia ->
                campo.setText(String.format(Locale.getDefault(), "%02d/%02d/%04d", dia, mes + 1, ano))
            },
            calendario.get(Calendar.YEAR),
            calendario.get(Calendar.MONTH),
            calendario.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun abrirTimePicker(campo: EditText) {
        val calendario = Calendar.getInstance()
        TimePickerDialog(
            this,
            { _, hora, minuto ->
                campo.setText(String.format(Locale.getDefault(), "%02d:%02d", hora, minuto))
            },
            calendario.get(Calendar.HOUR_OF_DAY),
            calendario.get(Calendar.MINUTE),
            true
        ).show()
    }
}