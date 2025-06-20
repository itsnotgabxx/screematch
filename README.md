O `screenmatch` é um projeto Spring Boot que interage com a API OMDB para obter, processar e armazenar informações de séries em um banco de dados local. Ele também expõe endpoints RESTful para acessar e filtrar esses dados, sendo projetado para se conectar com uma interface frontend.

## Funcionalidades

  * **Busca de Séries**: Permite buscar séries por título usando a API OMDB.
  * **Busca de Episódios**: Recupera todos os episódios de uma série específica.
  * **Listar Séries**: Exibe todas as séries salvas no banco de dados.
  * **Top 5 Séries**: Retorna as 5 séries mais bem avaliadas.
  * **Séries por Categoria**: Filtra séries por gênero/categoria.
  * **Filtrar Séries por Temporada e Avaliação**: Filtra séries com base no número total de temporadas e uma avaliação mínima.
  * **Busca de Episódios por Trecho do Título**: Encontra episódios fornecendo um trecho do título.
  * **Top 5 Episódios por Série**: Lista os 5 melhores episódios para uma série específica.
  * **Episódios Lançados Após uma Data Específica**: Encontra episódios lançados após um determinado ano para uma série selecionada.
  * **Configuração CORS**: Permite requisições de *cross-origin* a partir de `http://127.0.0.1:5500`, o que é essencial para a conexão com o frontend.

## Tecnologias Utilizadas

  * **Java 17**: Linguagem de programação.
  * **Spring Boot**: Framework para construção da aplicação.
  * **Spring Data JPA**: Para interação com o banco de dados.
  * **PostgreSQL**: (Assumido, pois é uma escolha comum com Spring Data JPA, embora não explicitamente definido nos arquivos fornecidos).
  * **Jackson**: Para processamento e mapeamento JSON.
  * **OMDB API**: API externa para dados de séries e filmes.
  * **MyMemory Translation API**: Utilizada para traduzir as sinopses das séries.

## Estrutura do Projeto

O projeto segue uma estrutura padrão de aplicação Spring Boot:

  * `br.com.alura.screenmatch`: Pacote principal da aplicação.
      * `ScreenmatchApplication.java`: Ponto de entrada da aplicação Spring Boot.
      * `config`: Contém classes de configuração, como `CorsConfiguration`.
      * `controller`: Lida com as requisições web.
          * `SerieController.java`: Controlador REST para operações relacionadas a séries.
      * `dto`: Objetos de Transferência de Dados (DTOs) para expor os dados.
      * `model`: Define os modelos de dados e entidades da aplicação.
      * `principal`: Contém a classe `Principal`, que inclui a lógica principal da aplicação para interação via console.
      * `repository`: Interfaces para acesso e persistência de dados.
      * `service`: Camada de lógica de negócios e serviços.
          * `ConsumoApi.java`: Lida com o consumo de APIs externas.
          * `ConverteDados.java`: Utilitário para converter dados JSON em objetos Java.
          * `IConverteDados.java`: Interface para conversão de dados.
          * `SerieService.java`: Camada de serviço para operações relacionadas a séries.
          * `traducao`: Pacote para serviços de tradução.
              * `ConsultaMyMemory.java`: Lida com a tradução usando a API MyMemory.
              * `DadosResposta.java`: Record para a resposta de texto traduzido.
              * `DadosTraducao.java`: Record para os dados de tradução.

## Primeiros Passos

### Pré-requisitos

  * Java 17 ou superior
  * Maven ou Gradle (para gerenciamento de dependências)
  * Uma IDE como IntelliJ IDEA ou VS Code
  * Uma Chave de API OMDB (você pode obter uma em [http://www.omdbapi.com/](http://www.omdbapi.com/))
  * Um banco de dados (ex: PostgreSQL)

### Instalação

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/itsnotgabxx/screematch.git
    cd screematch/screematch-main
    ```
2.  **Configure a Chave da API:**
      * Abra `src/main/resources/application.properties` (ou `application.yml`) e adicione sua chave da API OMDB:
        ```properties
        # Para API OMDB
        api.key=SUA_CHAVE_API_OMDB
        ```
      * Em `Principal.java`, certifique-se de que a constante `API_KEY` esteja configurada corretamente, caso não esteja usando `application.properties`:
        ```java
        private final String API_KEY = "&apikey=SUA_CHAVE_API_OMDB"; // Substitua pela sua chave real
        ```
3.  **Configure o Banco de Dados:**
      * Atualize `src/main/resources/application.properties` com os detalhes da sua conexão com o banco de dados (ex: para PostgreSQL):
        ```properties
        spring.datasource.url=jdbc:postgresql://localhost:5432/screenmatch
        spring.datasource.username=seu_usuario
        spring.datasource.password=sua_senha
        spring.jpa.hibernate.ddl-auto=update
        spring.jpa.show-sql=true
        spring.jpa.properties.hibernate.format_sql=true
        ```
4.  **Construa o projeto:**
    ```bash
    ./mvnw clean install
    ```
    ou
    ```bash
    ./gradlew clean build
    ```

### Executando a Aplicação

Você pode executar a aplicação de duas maneiras:

1.  **Pela sua IDE:** Execute a classe `ScreenmatchApplication.java`.
2.  **Pela linha de comando:**
    ```bash
    java -jar target/screenmatch-0.0.1-SNAPSHOT.jar
    ```

### Acessando a API

Uma vez que a aplicação esteja em execução, os endpoints da API REST estarão disponíveis, geralmente em `http://localhost:8080`.

Aqui estão alguns exemplos de endpoints do `SerieController.java`:

  * `GET /series`: Retorna todas as séries.
  * `GET /series/top5`: Retorna as 5 melhores séries.
  * `GET /series/lancamentos`: Retorna os lançamentos mais recentes.
  * `GET /series/{id}`: Retorna uma série pelo ID.
  * `GET /series/{id}/temporadas/todas`: Retorna todas as temporadas de uma série.
  * `GET /series/{id}/temporadas/{numero}`: Retorna uma temporada específica de uma série por número.
  * `GET /series/categoria/{nomeGenero}`: Retorna séries por categoria.

## Uso (Aplicação de Console - `ScreenmatchApplicationSemWeb.java`)

O projeto também contém uma aplicação baseada em console (`ScreenmatchApplicationSemWeb.java`) para interação direta, que está atualmente comentada. Se você descomentá-la e habilitá-la (descomentando a anotação `@SpringBootApplication` e a implementação `CommandLineRunner`), você poderá executá-la para interagir através de um menu no console:

Para usar o menu do console:

1.  Descomente o código em `ScreenmatchApplicationSemWeb.java`.
2.  Execute `ScreenmatchApplicationSemWeb.java` como uma aplicação Spring Boot.
3.  Siga as instruções no seu console para interagir com a aplicação.

## Contribuição

Sinta-se à vontade para fazer um *fork* do repositório, criar uma nova *branch* e enviar *pull requests*.
