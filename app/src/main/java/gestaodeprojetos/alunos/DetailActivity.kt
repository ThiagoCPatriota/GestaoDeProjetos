package gestaodeprojetos.alunos

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NOME = "extra_nome"
        const val EXTRA_DISCIPLINA = "extra_disciplina"
        const val EXTRA_INICIO = "extra_inicio"
        const val EXTRA_ENTREGA = "extra_entrega"
        const val EXTRA_DIA_ESTUDO = "extra_dia_estudo"
        const val EXTRA_HORARIO_INICIO = "extra_horario_inicio"
        const val EXTRA_HORARIO_TERMINO = "extra_horario_termino"
    }

    private lateinit var nome: String
    private lateinit var disciplina: String
    private lateinit var diaEstudo: String
    private lateinit var horarioInicio: String
    private lateinit var horarioTermino: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        nome = intent.getStringExtra(EXTRA_NOME) ?: "-"
        disciplina = intent.getStringExtra(EXTRA_DISCIPLINA) ?: "-"
        val inicio = intent.getStringExtra(EXTRA_INICIO) ?: "-"
        val entrega = intent.getStringExtra(EXTRA_ENTREGA) ?: "-"
        diaEstudo = intent.getStringExtra(EXTRA_DIA_ESTUDO) ?: "-"
        horarioInicio = intent.getStringExtra(EXTRA_HORARIO_INICIO) ?: "-"
        horarioTermino = intent.getStringExtra(EXTRA_HORARIO_TERMINO) ?: "-"

        findViewById<TextView>(R.id.tvDetalheNomeProjeto).text = nome
        findViewById<TextView>(R.id.tvDetalheDisciplina).text = getString(R.string.label_disciplina, disciplina)

        findViewById<TextView>(R.id.tvLinhaProjeto).text = nome
        findViewById<TextView>(R.id.tvLinhaDisciplina).text = disciplina
        findViewById<TextView>(R.id.tvLinhaInicio).text = inicio
        findViewById<TextView>(R.id.tvLinhaEntrega).text = entrega
        findViewById<TextView>(R.id.tvLinhaDiaEstudo).text = diaEstudo
        findViewById<TextView>(R.id.tvLinhaHorario).text = "$horarioInicio - $horarioTermino"

        findViewById<Button>(R.id.btnAdicionarCalendario).setOnClickListener {
            abrirCalendario()
        }

        findViewById<Button>(R.id.btnVoltar).setOnClickListener {
            finish()
        }
    }

    private fun abrirCalendario() {
        val inicioMillis = calcularMillis(diaEstudo, horarioInicio)
        val fimMillis = calcularMillis(diaEstudo, horarioTermino)

        if (inicioMillis == null || fimMillis == null) {
            Toast.makeText(
                this,
                "Dia de estudo e horários precisam estar preenchidos corretamente",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val intent = Intent(Intent.ACTION_INSERT)
            .setData(CalendarContract.Events.CONTENT_URI)
            .putExtra(CalendarContract.Events.TITLE, nome)
            .putExtra(CalendarContract.Events.DESCRIPTION, "Disciplina: $disciplina")
            .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, inicioMillis)
            .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, fimMillis)

        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "Nenhum aplicativo de calendário encontrado", Toast.LENGTH_SHORT).show()
        }
    }

    private fun calcularMillis(data: String, hora: String): Long? {
        return try {
            val partesData = data.split("/")
            val partesHora = hora.split(":")
            val calendario = Calendar.getInstance()
            calendario.set(
                partesData[2].toInt(),
                partesData[1].toInt() - 1,
                partesData[0].toInt(),
                partesHora[0].toInt(),
                partesHora[1].toInt(),
                0
            )
            calendario.timeInMillis
        } catch (e: Exception) {
            null
        }
    }
}
