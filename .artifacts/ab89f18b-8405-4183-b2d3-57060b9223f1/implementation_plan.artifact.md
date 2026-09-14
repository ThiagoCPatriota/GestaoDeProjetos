# Implementação das Telas do AcadPlan

Reconstrução das telas "Meus Projetos", "Novo Projeto" e "Detalhes do Projeto" seguindo os wireframes fornecidos, utilizando XML Views e Activities.

## User Review Required

> [!IMPORTANT]
> A navegação entre as telas será feita via Intents explícitas.
> O fluxo será: `MainActivity` (Meus Projetos) -> `FormActivity` (Novo Projeto) e `MainActivity` -> `DetailActivity` (Detalhes).

## Proposed Changes

### [Model]
#### [MODIFY] [Project.kt](file:///C:/Users/Emerson SIlva/Documents/5Periodo/GestaoProjetos/app/src/main/java/gestaodeprojetos/alunos/model/Project.kt)
- Adicionar campos: `dataInicio`, `diaEstudo`, `horarioInicio`, `horarioTermino`, `observacoes`.

### [Resources]
#### [MODIFY] [strings.xml](file:///C:/Users/Emerson SIlva/Documents/5Periodo/GestaoProjetos/app/src/main/res/values/strings.xml)
- Adicionar textos das telas (títulos, labels, hints).

#### [MODIFY] [colors.xml](file:///C:/Users/Emerson SIlva/Documents/5Periodo/GestaoProjetos/app/src/main/res/values/colors.xml)
- Definir cores cinza e branco conforme wireframe.

#### [NEW] [Icons](file:///C:/Users/Emerson SIlva/Documents/5Periodo/GestaoProjetos/app/src/main/res/drawable/)
- Criar ícones para calendário, relógio, home, lista e perfil.

### [Layouts]
#### [MODIFY] [activity_main.xml](file:///C:/Users/Emerson SIlva/Documents/5Periodo/GestaoProjetos/app/src/main/res/layout/activity_main.xml)
- Atualizar para incluir `BottomNavigationView` e layout fiel ao wireframe.

#### [NEW] [activity_form.xml](file:///C:/Users/Emerson SIlva/Documents/5Periodo/GestaoProjetos/app/src/main/res/layout/activity_form.xml)
- Criar formulário de "Novo Projeto".

#### [NEW] [activity_detail.xml](file:///C:/Users/Emerson SIlva/Documents/5Periodo/GestaoProjetos/app/src/main/res/layout/activity_detail.xml)
- Criar tela de "Detalhes do Projeto".

#### [MODIFY] [item_project.xml](file:///C:/Users/Emerson SIlva/Documents/5Periodo/GestaoProjetos/app/src/main/res/layout/item_project.xml)
- Ajustar estilo do card de projeto.

### [Logic]
#### [MODIFY] [MainActivity.kt](file:///C:/Users/Emerson SIlva/Documents/5Periodo/GestaoProjetos/app/src/main/java/gestaodeprojetos/alunos/MainActivity.kt)
- Gerenciar a lista e navegação.

#### [NEW] [FormActivity.kt](file:///C:/Users/Emerson SIlva/Documents/5Periodo/GestaoProjetos/app/src/main/java/gestaodeprojetos/alunos/FormActivity.kt)
- Lógica de captura de dados e salvamento.

#### [NEW] [DetailActivity.kt](file:///C:/Users/Emerson SIlva/Documents/5Periodo/GestaoProjetos/app/src/main/java/gestaodeprojetos/alunos/DetailActivity.kt)
- Exibição dos detalhes do projeto.

## Verification Plan

### Manual Verification
- Executar o app e validar a aparência de cada tela.
- Testar a navegação entre as telas.
- Preencher o formulário e verificar se os dados são passados corretamente.
