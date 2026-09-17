package gestaodeprojetos.alunos.data

import android.content.Context
import gestaodeprojetos.alunos.model.Project

/**
 * Isola a leitura e gravação em SharedPreferences (RF-09, Dia 12).
 * Decisão do plano: salvar apenas o último projeto cadastrado (não uma
 * lista), para manter o escopo reduzido e não comprometer o fluxo principal
 * (risco R1 do Documento Mestre).
 */
object ProjectStorage {
    private const val PREFS_NAME = "acadplan_prefs"
    private const val KEY_NOME = "ultimo_nome"
    private const val KEY_DISCIPLINA = "ultimo_disciplina"
    private const val KEY_DATA_INICIO = "ultimo_data_inicio"
    private const val KEY_DATA_ENTREGA = "ultimo_data_entrega"
    private const val KEY_DIA_ESTUDO = "ultimo_dia_estudo"
    private const val KEY_HORARIO_INICIO = "ultimo_horario_inicio"
    private const val KEY_HORARIO_TERMINO = "ultimo_horario_termino"

    fun salvarUltimoProjeto(context: Context, projeto: Project) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit()
            .putString(KEY_NOME, projeto.nome)
            .putString(KEY_DISCIPLINA, projeto.disciplina)
            .putString(KEY_DATA_INICIO, projeto.dataInicio)
            .putString(KEY_DATA_ENTREGA, projeto.dataEntrega)
            .putString(KEY_DIA_ESTUDO, projeto.diaEstudo)
            .putString(KEY_HORARIO_INICIO, projeto.horarioInicio)
            .putString(KEY_HORARIO_TERMINO, projeto.horarioTermino)
            .apply()
    }

    fun carregarUltimoProjeto(context: Context): Project? {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val nome = prefs.getString(KEY_NOME, null) ?: return null
        return Project(
            nome = nome,
            disciplina = prefs.getString(KEY_DISCIPLINA, "-") ?: "-",
            dataInicio = prefs.getString(KEY_DATA_INICIO, "-") ?: "-",
            dataEntrega = prefs.getString(KEY_DATA_ENTREGA, "-") ?: "-",
            diaEstudo = prefs.getString(KEY_DIA_ESTUDO, "-") ?: "-",
            horarioInicio = prefs.getString(KEY_HORARIO_INICIO, "-") ?: "-",
            horarioTermino = prefs.getString(KEY_HORARIO_TERMINO, "-") ?: "-"
        )
    }
}