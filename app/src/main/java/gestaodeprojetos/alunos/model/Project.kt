package gestaodeprojetos.alunos.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "projects")
data class Project(
    val nome: String,
    val disciplina: String,
    val dataInicio: String = "-",
    val dataEntrega: String,
    val diaEstudo: String = "-",
    val horarioInicio: String = "-",
    val horarioTermino: String = "-",
    val isCompleted: Boolean = false,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0
) : Serializable
