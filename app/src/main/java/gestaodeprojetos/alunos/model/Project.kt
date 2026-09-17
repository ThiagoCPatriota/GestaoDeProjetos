package gestaodeprojetos.alunos.model

data class Project(
    val nome: String,
    val disciplina: String,
    val dataInicio: String = "-",
    val dataEntrega: String,
    val diaEstudo: String = "-",
    val horarioInicio: String = "-",
    val horarioTermino: String = "-"
)