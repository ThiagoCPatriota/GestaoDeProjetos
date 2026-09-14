# Pull Request: Refatoração da Tela Inicial e Correções Estruturais

## Descrição
Esta PR resolve inconsistências no projeto que impediam a compilação e execução da `MainActivity`. Além disso, introduz melhorias no layout principal e na organização das dependências.

## Mudanças Principais

### ⚙️ Core & Infraestrutura
- **Correção de Pacote:** Ajustado o package de `MainActivity.kt` para `gestaodeprojetos.alunos`, garantindo conformidade com a estrutura de diretórios e o namespace do Gradle.
- **Gerenciamento de Dependências:** Adicionada a biblioteca `androidx.appcompat` ao Version Catalog (`libs.versions.toml`) e ao `build.gradle.kts`.
- **Correção de Tema:** Migração do tema base em `themes.xml` para `Theme.AppCompat.Light.NoActionBar`, corrigindo o erro de inicialização da `AppCompatActivity`.

### 🎨 Interface (UI)
- **Refatoração do Layout Principal:** O arquivo `activity_main.xml` foi atualizado com um design mais moderno, incluindo:
    - Cabeçalho estilizado (Título: "UniFocus").
    - Lista de projetos em um `ScrollView` para melhor usabilidade.
    - Botão de "+ Novo Projeto" fixo na parte inferior.
- **Inflação Dinâmica:** A `MainActivity` agora popula a lista de projetos dinamicamente usando `LayoutInflater` e o layout `item_project.xml`.

## Arquivos Modificados
- [MainActivity.kt](file:///C:/Users/Thiago Leal/StudioProjects/GestaoDeProjetos/app/src/main/java/gestaodeprojetos/alunos/MainActivity.kt)
- [build.gradle.kts](file:///C:/Users/Thiago Leal/StudioProjects/GestaoDeProjetos/app/build.gradle.kts)
- [libs.versions.toml](file:///C:/Users/Thiago Leal/StudioProjects/GestaoDeProjetos/gradle/libs.versions.toml)
- [themes.xml](file:///C:/Users/Thiago Leal/StudioProjects/GestaoDeProjetos/app/src/main/res/values/themes.xml)
- [activity_main.xml](file:///C:/Users/Thiago Leal/StudioProjects/GestaoDeProjetos/app/src/main/res/layout/activity_main.xml)

## Plano de Verificação
- [x] Sincronização do Gradle realizada sem erros.
- [x] Build do projeto finalizado com sucesso.
- [x] Teste em emulador: O app abre corretamente e exibe os projetos de exemplo na lista.
- [x] Interação: Cliques nos botões de detalhes e novo projeto exibem Toasts (conforme o estágio atual do desenvolvimento).

---
> [!NOTE]
> Esta implementação faz parte do "Dia 4" do EAP, preparando o terreno para a implementação das Intents e persistência de dados.
