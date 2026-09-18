# Correção de Erros e Padronização de Activities

Este plano visa corrigir os erros de compilação relacionados a pacotes incorretos e referências não resolvidas às classes `R`, `DetailActivity` e `FormActivity`. Também serão corrigidos os avisos de strings hardcoded para seguir as melhores práticas do Android.

## User Review Required

> [!IMPORTANT]
> A mudança do pacote de `com.ifpe.acadplan` para `gestaodeprojetos.alunos` é necessária para alinhar o código com a estrutura de diretórios e o namespace definido no `build.gradle.kts`.

## Proposed Changes

### Activities (Componente Principal)

---

#### [MODIFY] [DetailActivity.kt](file:///C:/Users/Thiago Leal/StudioProjects/GestaoDeProjetos/app/src/main/java/gestaodeprojetos/alunos/DetailActivity.kt)
- Alterar declaração do pacote para `gestaodeprojetos.alunos`.
- Substituir strings hardcoded por recursos de string.

#### [MODIFY] [FormActivity.kt](file:///C:/Users/Thiago Leal/StudioProjects/GestaoDeProjetos/app/src/main/java/gestaodeprojetos/alunos/FormActivity.kt)
- Alterar declaração do pacote para `gestaodeprojetos.alunos`.

#### [MODIFY] [MainActivity.kt](file:///C:/Users/Thiago Leal/StudioProjects/GestaoDeProjetos/app/src/main/java/gestaodeprojetos/alunos/MainActivity.kt)
- Corrigir referências a `DetailActivity` e `FormActivity` (que serão resolvidas automaticamente após a correção do pacote nelas).
- Substituir strings hardcoded por recursos de string.

### Recursos (UI)

---

#### [MODIFY] [strings.xml](file:///C:/Users/Thiago Leal/StudioProjects/GestaoDeProjetos/app/src/main/res/values/strings.xml)
- Adicionar strings para "Disciplina: %s", "Entrega: %s", etc.

## Verification Plan

### Automated Tests
- Executar `./gradlew assembleDebug` para garantir que todos os erros de compilação foram resolvidos.

### Manual Verification
- Abrir o app e verificar se a navegação entre a `MainActivity` -> `DetailActivity` e `MainActivity` -> `FormActivity` funciona corretamente.
