package gestaodeprojetos.alunos

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import gestaodeprojetos.alunos.data.ProjectStorage
import gestaodeprojetos.alunos.model.Project

class MainActivity : AppCompatActivity() {

    private lateinit var container: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        container = findViewById(R.id.containerProjetos)

        findViewById<Button>(R.id.btnNovoProjeto).setOnClickListener {
            startActivity(Intent(this, FormActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        carregarProjetos()
    }

    private fun carregarProjetos() {
        container.removeAllViews()

        val projetos = mutableListOf(
            Project("Projeto de Mobile", "Desenvolvimento Mobile", "05/09", "18/09", "10/09", "19:00", "21:00"),
            Project("Trabalho de Banco de Dados", "Banco de Dados", "08/09", "22/09", "15/09", "20:00", "22:00")
        )

        ProjectStorage.carregarUltimoProjeto(this)?.let { salvo ->
            projetos.add(0, salvo)
        }

        val inflater = LayoutInflater.from(this)
        for (projeto in projetos) {
            val itemView = inflater.inflate(R.layout.item_project, container, false)

            itemView.findViewById<TextView>(R.id.tvNomeProjeto).text = projeto.nome
            itemView.findViewById<TextView>(R.id.tvDisciplina).text = getString(R.string.label_disciplina, projeto.disciplina)
            itemView.findViewById<TextView>(R.id.tvEntrega).text = getString(R.string.label_entrega, projeto.dataEntrega)

            itemView.findViewById<Button>(R.id.btnVerDetalhes).setOnClickListener {
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
            }

            container.addView(itemView)
        }
    }
}
