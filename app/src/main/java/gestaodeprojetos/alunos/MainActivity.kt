package gestaodeprojetos.alunos

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import gestaodeprojetos.alunos.db.AppDatabase
import gestaodeprojetos.alunos.model.Project
import kotlinx.coroutines.launch
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    private lateinit var containerProjetos: LinearLayout
    private val database by lazy { AppDatabase.getDatabase(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        containerProjetos = findViewById(R.id.containerProjetos)

        // Observa os projetos ativos do banco de dados
        lifecycleScope.launch {
            database.projectDao().getAllActive().collect { projetos ->
                atualizarInterface(projetos)
            }
        }

        findViewById<Button>(R.id.btnNovoProjeto).setOnClickListener {
            val intent = Intent(this, FormActivity::class.java)
            startActivity(intent)
        }
    }

    private fun atualizarInterface(projetos: List<Project>) {
        containerProjetos.removeAllViews()
        for (projeto in projetos) {
            adicionarProjetoAoLayout(projeto)
        }
    }

    private fun adicionarProjetoAoLayout(projeto: Project) {
        val inflater = LayoutInflater.from(this)
        val itemView = inflater.inflate(R.layout.item_project, containerProjetos, false)

        itemView.findViewById<TextView>(R.id.tvNomeProjeto).text = projeto.nome
        itemView.findViewById<TextView>(R.id.tvDisciplina).text = getString(R.string.label_disciplina, projeto.disciplina)
        itemView.findViewById<TextView>(R.id.tvEntrega).text = getString(R.string.label_entrega, projeto.dataEntrega)

        itemView.findViewById<Button>(R.id.btnVerDetalhes).setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("PROJECT", projeto as Serializable)
            startActivity(intent)
        }

        itemView.findViewById<Button>(R.id.btnConcluido).setOnClickListener {
            lifecycleScope.launch {
                database.projectDao().update(projeto.copy(isCompleted = true))
            }
        }

        containerProjetos.addView(itemView)
    }
}