package gestaodeprojetos.alunos

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import gestaodeprojetos.alunos.model.Project

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Dados de exemplo para o Dia 4 (objetivo: tela inicial abrindo no
        // emulador). A partir do Dia 7/8, esta lista deixa de ser fixa e
        // passa a refletir os projetos cadastrados via FormActivity.
        val projetos = listOf(
            Project("Projeto de Mobile", "Desenvolvimento Mobile", "18/09"),
            Project("Trabalho de Banco de Dados", "Banco de Dados", "22/09")
        )

        val container = findViewById<LinearLayout>(R.id.containerProjetos)
        val inflater = LayoutInflater.from(this)

        for (projeto in projetos) {
            val itemView = inflater.inflate(R.layout.item_project, container, false)

            itemView.findViewById<TextView>(R.id.tvNomeProjeto).text = projeto.nome
            itemView.findViewById<TextView>(R.id.tvDisciplina).text =
                "Disciplina: ${projeto.disciplina}"
            itemView.findViewById<TextView>(R.id.tvEntrega).text =
                "Entrega: ${projeto.dataEntrega}"

            itemView.findViewById<Button>(R.id.btnVerDetalhes).setOnClickListener {
                // TODO (Dia 7+, tarefa 3.5 do EAP): trocar por Intent explícita
                // para DetailActivity, enviando os dados via putExtra.
                Toast.makeText(this, "Detalhes de ${projeto.nome} (em breve)", Toast.LENGTH_SHORT).show()
            }

            container.addView(itemView)
        }

        findViewById<Button>(R.id.btnNovoProjeto).setOnClickListener {
            // TODO (Dia 7+, tarefa 3.5 do EAP): trocar por Intent explícita
            // para FormActivity.
            Toast.makeText(this, "Novo projeto (em breve)", Toast.LENGTH_SHORT).show()
        }
    }
}