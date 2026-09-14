package gestaodeprojetos.alunos

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import gestaodeprojetos.alunos.R
import gestaodeprojetos.alunos.model.Project

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val projeto = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("PROJECT", Project::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra("PROJECT") as? Project
        }

        projeto?.let {
            findViewById<TextView>(R.id.tvDetailNomeProjeto).text = it.nome
            findViewById<TextView>(R.id.tvDetailDisciplinaTitle).text = getString(R.string.label_disciplina, it.disciplina)
            
            findViewById<TextView>(R.id.tvDetailProjetoValue).text = it.nome
            findViewById<TextView>(R.id.tvDetailDisciplinaValue).text = it.disciplina
            findViewById<TextView>(R.id.tvDetailInicioValue).text = it.dataInicio
            findViewById<TextView>(R.id.tvDetailEntregaValue).text = it.dataEntrega
            findViewById<TextView>(R.id.tvDetailDiaEstudoValue).text = it.diaEstudo
            findViewById<TextView>(R.id.tvDetailHorarioValue).text = "${it.horarioInicio} - ${it.horarioTermino}"
            
            findViewById<TextView>(R.id.tvDetailObservacoes).text = it.observacoes.ifEmpty { getString(R.string.no_observations) }
        }

        findViewById<Button>(R.id.btnAdicionarCalendario).setOnClickListener {
            Toast.makeText(this, getString(R.string.added_to_calendar), Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.btnVoltar).setOnClickListener {
            finish()
        }
    }
}