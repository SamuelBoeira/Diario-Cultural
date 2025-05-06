/**
 * Autor: Samuel Boeira Dantas
 * Componenete curricular: 2025.1 EXA863 - MI - PROGRAMAÇÃO (TP05)
 * Concluído em: 22/04/2025
 * Declaro que este código foi elaborado por mim de forma individual e não contém nenhum techo de código
 * de outro colega ou de outro autor, tais como provindos de livros e apostilas, e páginas de documentos
 * eletrônicos da internet. Qualquer trecho de código de outra autoria que não a minha está destacado com
 * uma citação para o autor e a fonte do código, e estou ciente que esses techaos não serão considerados
 * para fins de avaliação.
 */


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Classe principal que controla o sistema de Diário Cultural.
 * Gerencia os principais métodos do programa.
 */
public class Controller {
    private List<Book> books;       // Lista de livros cadastrados
    private List<Movie> movies;     // Lista de filmes cadastrados
    private List<Series> series;    // Lista de séries cadastradas
    private Scanner scanner;        // Scanner para leitura de entrada do user

    /**
     * Inicializa as listas de mídias e o scanner para entrada do usuário.
     */
    public Controller() {
        books = new ArrayList<>();
        movies = new ArrayList<>();
        series = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.run();
    }

    /**
     * Método principal de execução do programa, roda o Menu de interação com o User.
     */
    private void run() {
        boolean running = true;
        while (running) {
            // Exibe o menu principal
            System.out.println("\n=== DIÁRIO CULTURAL ===");
            System.out.println("1. Cadastrar Livro");
            System.out.println("2. Avaliar Livro");
            System.out.println("3. Cadastrar Filme");
            System.out.println("4. Avaliar Filme");
            System.out.println("5. Cadastrar Série");
            System.out.println("6. Avaliar Temporada de Série");
            System.out.println("7. Buscar Conteúdo");
            System.out.println("8. Listar Conteúdo");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            // Lê a escolha do User
            int choice = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer de entrada

            // Processa a escolha do User
            switch (choice) {
                case 1:
                    registerBook();
                    break;
                case 2:
                    reviewBook();
                    break;
                case 3:
                    registerMovie();
                    break;
                case 4:
                    reviewMovie();
                    break;
                case 5:
                    registerSeries();
                    break;
                case 6:
                    reviewSeason();
                    break;
                case 7:
                    searchContent();
                    break;
                case 8:
                    listContent();
                    break;
                case 0:
                    running = false;
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
        scanner.close(); // Fecha o scanner ao sair do programa
    }

    /**
     * Cadastra um novo livro no Diário Cultural.
     * User fornece todas as informações para criar um objeto Book.
     */
    private void registerBook() {
        System.out.println("\n=== CADASTRAR LIVRO ===");
        
        // Solicita informações do livro ao usuário
        System.out.print("Título: ");
        String title = scanner.nextLine();

        System.out.print("Autor: ");
        String author = scanner.nextLine();

        System.out.print("Editora: ");
        String publisher = scanner.nextLine();

        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();

        System.out.print("Gênero: ");
        String genre = scanner.nextLine();

        System.out.print("Ano de publicação: ");
        int year = scanner.nextInt();

        System.out.print("Possui exemplar? (1-Sim / 2-Não): ");
        boolean owned = scanner.nextInt() == 1;

        // Cria um novo objeto Book com as informações fornecidas
        Book book = new Book(title, author, publisher, isbn, genre, year, owned);
        books.add(book); // Adiciona o livro à lista de livros
        System.out.println("Livro cadastrado com sucesso!");
    }

    /**
     * User avalia um livro existente no sistema.
     * Busca o livro pelo título, marca como lido e adiciona uma avaliação.
     */

    private void reviewBook() {
        System.out.println("\n=== AVALIAR LIVRO ===");
        
        // Verifica se existem livros cadastrados
        if (books.isEmpty()) {
            System.out.println("Nenhum livro cadastrado!");
            return;
        }

        System.out.print("Digite o título do livro: ");
        String title = scanner.nextLine();

        // Busca o livro pelo título usando Stream API
        Book book = books.stream()
                .filter(b -> b.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);

        // Verifica se o livro foi encontrado
        if (book == null) {
            System.out.println("Livro não encontrado!");
            return;
        }

        // Pergunta se o User leu o livro, pois só pode avaliar caso já tenha sido
        System.out.print("Você leu este livro? (1-Sim / 2-Não): ");
        boolean read = scanner.nextInt() == 1;
        scanner.nextLine();

        if (read) {
            // Se o livro foi lido, solicita a data de leitura
            System.out.print("Data de leitura (AAAA-MM-DD): ");
            String dateString = scanner.nextLine();
            LocalDate date = LocalDate.parse(dateString);

            // Marca o livro como lido na data fornecida pelo User
            book.markAsConsumed(date);

            // User faz a avaliação do livro
            System.out.print("Avaliação (1-5 estrelas): ");
            int rating = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Comentário (opcional): ");
            String comment = scanner.nextLine();

            // Aqui a avaliação ao livro é registrada
            book.addReview(rating, comment, date);
            System.out.println("Avaliação registrada com sucesso!");
        } else {
            // Se o livro não foi lido, marca como não lido
            book.setConsumed(false);
            book.setConsumptionDate(null);
            System.out.println("Livro marcado como não lido.");
        }
    }

    /**
     * Cadastra um novo filme no Diário Cultural.
     * User fornece todas as informações necessárias para criar um objeto Movie.
     * Também permite adicionar atores ao elenco do filme.
     */
    private void registerMovie() {
        System.out.println("\n=== CADASTRAR FILME ===");
        System.out.print("Título: ");
        String title = scanner.nextLine();

        System.out.print("Título Original: ");
        String originalTitle = scanner.nextLine();

        System.out.print("Gênero: ");
        String genre = scanner.nextLine();

        System.out.print("Ano de lançamento: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Duração (minutos): ");
        int duration = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Diretor: ");
        String director = scanner.nextLine();

        System.out.print("Roteirista: ");
        String screenplay = scanner.nextLine();

        System.out.print("Onde assistir: ");
        String whereToWatch = scanner.nextLine();

        // Cria um novo objeto Movie com as informações fornecidas
        Movie movie = new Movie(title, genre, year, originalTitle, whereToWatch, duration, director, screenplay);

        // Adiciona os atores ao elenco do filme
        boolean addingCast = true;
        while (addingCast) {
            System.out.print("Adicionar ator? (1-Sim / 2-Não): ");
            int option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1) {
                System.out.print("Nome do ator: ");
                String actorName = scanner.nextLine();

                System.out.print("Personagem: ");
                String character = scanner.nextLine();

                movie.addActor(new Actor(actorName, character));
            } else {
                addingCast = false;
            }
        }

        movies.add(movie); // Adiciona o filme à lista de filmes
        System.out.println("Filme cadastrado com sucesso!");
    }

    /**
     * Avalia um filme existente no sistema.
     * Busca o filme pelo título, marca como assistido e adiciona uma avaliação.
     */
    private void reviewMovie() {
        System.out.println("\n=== AVALIAR FILME ===");
        
        // Verifica se existem filmes cadastrados
        if (movies.isEmpty()) {
            System.out.println("Nenhum filme cadastrado!");
            return;
        }

        System.out.print("Digite o título do filme: ");
        String title = scanner.nextLine();

        // Stream API novamente para buscar o filme
        Movie movie = movies.stream()
                .filter(m -> m.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);

        // Verifica se o filme foi encontrado
        if (movie == null) {
            System.out.println("Filme não encontrado!");
            return;
        }

        // Pergunta se o User assistiu ao filme
        System.out.print("Você assistiu este filme? (1-Sim / 2-Não): ");
        boolean watched = scanner.nextInt() == 1;
        scanner.nextLine();

        if (watched) {
            // Se o filme foi assistido solicita a data de leitura
            System.out.print("Data de leitura (AAAA-MM-DD): ");
            String dateString = scanner.nextLine();
            LocalDate date = LocalDate.parse(dateString);
            movie.markAsConsumed(date);

            // User faz a avaliação do filme
            System.out.print("Avaliação (1-5 estrelas): ");
            int rating = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Comentário (opcional): ");
            String comment = scanner.nextLine();

            // Aqui a avaliação ao filme é registrada
            movie.addReview(rating, comment, LocalDate.now());
            System.out.println("Avaliação registrada com sucesso!");
        } else {
            // Se o filme não foi assistido, marca como não assistido
            movie.setConsumed(false);
            System.out.println("Filme marcado como não assistido.");
        }
    }

    /**
     * Cadastra uma nova série no Diário Cultural.
     * User fornece todas as informações necessárias para criar um objeto Series.
     * Também permite adicionar atores ao elenco e temporadas à série.
     */
    private void registerSeries() {
        System.out.println("\n=== CADASTRAR SÉRIE ===");

        System.out.print("Título: ");
        String title = scanner.nextLine();

        System.out.print("Título Original: ");
        String originalTitle = scanner.nextLine();

        System.out.print("Gênero: ");
        String genre = scanner.nextLine();

        System.out.print("Ano de lançamento: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Ano de término (0 se ainda em produção): ");
        int endYear = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Onde assistir: ");
        String whereToWatch = scanner.nextLine();

        // Cria um novo objeto Series com as informações fornecidas
        Series series = new Series(title, genre, year, originalTitle, whereToWatch, endYear);

        // Adiciona atores ao elenco da série
        boolean addingCast = true;
        while (addingCast) {
            System.out.print("Adicionar ator? (1-Sim / 2-Não): ");
            int option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1) {
                System.out.print("Nome do ator: ");
                String actorName = scanner.nextLine();

                System.out.print("Personagem: ");
                String character = scanner.nextLine();

                series.addActor(new Actor(actorName, character));
            } else {
                addingCast = false;
            }
        }

        // Adiciona temporadas à série
        boolean addingSeasons = true;
        while (addingSeasons) {
            System.out.print("Adicionar temporada? (1-Sim / 2-Não): ");
            int option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1) {
                System.out.print("Número da temporada: ");
                int seasonNumber = scanner.nextInt();

                System.out.print("Ano: ");
                int seasonYear = scanner.nextInt();

                System.out.print("Quantidade de episódios: ");
                int episodes = scanner.nextInt();
                scanner.nextLine();

                series.addSeason(seasonNumber, seasonYear, episodes);
            } else {
                addingSeasons = false;
            }
        }

        this.series.add(series); // Adiciona a série à lista de séries
        System.out.println("Série cadastrada com sucesso!");
    }

    /**
     * Avalia uma temporada específica de uma série existente no Diário Cultural.
     * Busca a série pelo título, seleciona a temporada e adiciona uma avaliação.
     */
    private void reviewSeason() {
        System.out.println("\n=== AVALIAR TEMPORADA ===");
        
        // Verifica se existem séries cadastradas
        if (series.isEmpty()) {
            System.out.println("Nenhuma série cadastrada!");
            return;
        }

        System.out.print("Digite o título da série: ");
        String title = scanner.nextLine();

        // Stream API novamente
        Series selectedSeries = series.stream()
                .filter(s -> s.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);

        // Verifica se a série foi encontrada
        if (selectedSeries == null) {
            System.out.println("Série não encontrada!");
            return;
        }

        // Verifica se a série tem temporadas cadastradas
        if (selectedSeries.getSeasons().isEmpty()) {
            System.out.println("Esta série não tem temporadas cadastradas!");
            return;
        }

        System.out.println("Temporadas disponíveis:");
        selectedSeries.getSeasons().forEach(s ->
                System.out.println(s.getSeasonNumber() + " - " + s.getYear()));

        // User fornece número da temporada a ser avaliada
        System.out.print("Digite o número da temporada: ");
        int seasonNumber = scanner.nextInt();
        scanner.nextLine();

        // Stream API para buscar a temporada
        Season season = selectedSeries.getSeasons().stream()
                .filter(s -> s.getSeasonNumber() == seasonNumber)
                .findFirst()
                .orElse(null);

        // Verifica se a temporada foi encontrada
        if (season == null) {
            System.out.println("Temporada não encontrada!");
            return;
        }

        // User faz a avaliação da temporada
        System.out.print("Avaliação (1-5 estrelas): ");
        int rating = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Comentário (opcional): ");
        String comment = scanner.nextLine();

        // Registra a avaliação à temporada
        season.addReview(rating, comment, LocalDate.now());
        System.out.println("Avaliação registrada com sucesso!");
    }

    /**
     * Método para o menu de busca de conteúdo, direciona para o método específico
     * de acordo com o tipo de mídia que o User quer buscar.
     */
    private void searchContent() {
        System.out.println("\n=== BUSCAR CONTEÚDO ===");
        System.out.println("1. Buscar Livros");
        System.out.println("2. Buscar Filmes");
        System.out.println("3. Buscar Séries");
        System.out.print("Escolha uma opção: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                searchBooks();
                break;
            case 2:
                searchMovies();
                break;
            case 3:
                searchSeries();
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    /**
     * Busca livros no Diário Cultural de acordo com o filtro.
     */
    private void searchBooks() {
        System.out.println("\n=== BUSCAR LIVROS ===");
        System.out.println("1. Por Título");
        System.out.println("2. Por Autor");
        System.out.println("3. Por Gênero");
        System.out.println("4. Por Ano");
        System.out.println("5. Por ISBN");
        System.out.print("Escolha uma opção: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        List<Book> results = new ArrayList<>();
        String searchTerm;

        // Realiza a busca de acordo com o filtro escolhido
        switch (choice) {
            case 1:
                System.out.print("Digite o título: ");
                searchTerm = scanner.nextLine();
                // Stream API para buscar livros por título
                results = books.stream()
                        .filter(b -> b.getTitle().toLowerCase().contains(searchTerm.toLowerCase()))
                        .collect(Collectors.toList());
                break;
            case 2:
                System.out.print("Digite o autor: ");
                searchTerm = scanner.nextLine();
                // Stream API para buscar livros por autor
                results = books.stream()
                        .filter(b -> b.getAuthor().toLowerCase().contains(searchTerm.toLowerCase()))
                        .collect(Collectors.toList());
                break;
            case 3:
                System.out.print("Digite o gênero: ");
                searchTerm = scanner.nextLine();
                // Stream API para buscar livros por gênero
                results = books.stream()
                        .filter(b -> b.getGenre().toLowerCase().contains(searchTerm.toLowerCase()))
                        .collect(Collectors.toList());
                break;
            case 4:
                System.out.print("Digite o ano: ");
                int year = scanner.nextInt();
                scanner.nextLine();
                // Stream API para buscar livros por ano
                results = books.stream()
                        .filter(b -> b.getReleaseYear() == year)
                        .collect(Collectors.toList());
                break;
            case 5:
                System.out.print("Digite o ISBN: ");
                searchTerm = scanner.nextLine();
                // Stream API para buscar livros com ISBN
                results = books.stream()
                        .filter(b -> b.getIsbn().equalsIgnoreCase(searchTerm))
                        .collect(Collectors.toList());
                break;
            default:
                System.out.println("Opção inválida!");
                return;
        }

        // Exibe os resultados da busca
        if (results.isEmpty()) {
            System.out.println("Nenhum livro encontrado!");
        } else {
            System.out.println("\n=== RESULTADOS ===");
            results.forEach(System.out::println);
        }
    }

    /**
     * Busca filmes no Diário Cultural de acordo com o filtro.
     */
    private void searchMovies() {
        System.out.println("\n=== BUSCAR FILMES ===");
        System.out.println("1. Por Título");
        System.out.println("2. Por Diretor");
        System.out.println("3. Por Ator");
        System.out.println("4. Por Gênero");
        System.out.println("5. Por Ano");
        System.out.print("Escolha uma opção: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        List<Movie> results = new ArrayList<>();
        String searchTerm;

        switch (choice) {
            case 1:
                System.out.print("Digite o título: ");
                searchTerm = scanner.nextLine();
                // Stream API para buscar filmes por título
                results = movies.stream()
                        .filter(m -> m.getTitle().toLowerCase().contains(searchTerm.toLowerCase()))
                        .collect(Collectors.toList());
                break;
            case 2:
                System.out.print("Digite o diretor: ");
                searchTerm = scanner.nextLine();
                // Stream API para buscar filmes por diretor
                results = movies.stream()
                        .filter(m -> m.getDirector().toLowerCase().contains(searchTerm.toLowerCase()))
                        .collect(Collectors.toList());
                break;
            case 3:
                System.out.print("Digite o nome do ator: ");
                searchTerm = scanner.nextLine();
                // Stream API para buscar filmes por autor
                results = movies.stream()
                        .filter(m -> m.getCast().stream()
                                .anyMatch(a -> a.getName().toLowerCase().contains(searchTerm.toLowerCase())))
                        .collect(Collectors.toList());
                break;
            case 4:
                System.out.print("Digite o gênero: ");
                searchTerm = scanner.nextLine();
                // Stream API para buscar filmes por gênero
                results = movies.stream()
                        .filter(m -> m.getGenre().toLowerCase().contains(searchTerm.toLowerCase()))
                        .collect(Collectors.toList());
                break;
            case 5:
                System.out.print("Digite o ano: ");
                int year = scanner.nextInt();
                scanner.nextLine();
                // Stream API para buscar filmes por ano
                results = movies.stream()
                        .filter(m -> m.getReleaseYear() == year)
                        .collect(Collectors.toList());
                break;
            default:
                System.out.println("Opção inválida!");
                return;
        }

        // Exibe os resultados da busca
        if (results.isEmpty()) {
            System.out.println("Nenhum filme encontrado!");
        } else {
            System.out.println("\n=== RESULTADOS ===");
            results.forEach(System.out::println);
        }
    }

    /**
     * Busca séries no Diário Cultural de acordo com o filtro
     */
    private void searchSeries() {
        System.out.println("\n=== BUSCAR SÉRIES ===");
        System.out.println("1. Por Título");
        System.out.println("2. Por Ator");
        System.out.println("3. Por Gênero");
        System.out.println("4. Por Ano");
        System.out.print("Escolha uma opção: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        List<Series> results = new ArrayList<>();
        String searchTerm;

        switch (choice) {
            case 1:
                System.out.print("Digite o título: ");
                searchTerm = scanner.nextLine();
                results = series.stream()
                        .filter(s -> s.getTitle().toLowerCase().contains(searchTerm.toLowerCase()))
                        .collect(Collectors.toList());
                break;
            case 2:
                System.out.print("Digite o nome do ator: ");
                searchTerm = scanner.nextLine();
                results = series.stream()
                        .filter(s -> s.getCast().stream()
                                .anyMatch(a -> a.getName().toLowerCase().contains(searchTerm.toLowerCase())))
                        .collect(Collectors.toList());
                break;
            case 3:
                System.out.print("Digite o gênero: ");
                searchTerm = scanner.nextLine();
                results = series.stream()
                        .filter(s -> s.getGenre().toLowerCase().contains(searchTerm.toLowerCase()))
                        .collect(Collectors.toList());
                break;
            case 4:
                System.out.print("Digite o ano: ");
                int year = scanner.nextInt();
                scanner.nextLine();
                results = series.stream()
                        .filter(s -> s.getReleaseYear() == year)
                        .collect(Collectors.toList());
                break;
            default:
                System.out.println("Opção inválida!");
                return;
        }

        // Exibe os resultados da busca
        if (results.isEmpty()) {
            System.out.println("Nenhuma série encontrada!");
        } else {
            System.out.println("\n=== RESULTADOS ===");
            results.forEach(System.out::println);
        }
    }

    /**
     * Exibe o menu de listagem de conteúdo e lista de acordo com a escolha
     * de filtro do User
     */
    private void listContent() {
        System.out.println("\n=== LISTAR CONTEÚDO ===");
        System.out.println("1. Listar Livros");
        System.out.println("2. Listar Filmes");
        System.out.println("3. Listar Séries");
        System.out.print("Escolha uma opção: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                listBooks();
                break;
            case 2:
                listMovies();
                break;
            case 3:
                listSeries();
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    /**
     * Lista livros com diferentes opções de ordenação e filtragem.
     */
    private void listBooks() {
        System.out.println("\n=== LISTAR LIVROS ===");
        System.out.println("1. Ordenar por Avaliação (Melhores)");
        System.out.println("2. Ordenar por Avaliação (Piores)");
        System.out.println("3. Filtrar por Gênero");
        System.out.println("4. Filtrar por Ano");
        System.out.print("Escolha uma opção: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        List<Book> sortedBooks = new ArrayList<>(books);
        switch (choice) {
            case 1:
                // Ordena os livros por avaliação (do maior para o menor)
                sortedBooks.sort((b1, b2) -> Double.compare(b2.getAverageRating(), b1.getAverageRating()));
                break;
            case 2:
                // Ordena os livros por avaliação (do menor para o maior)
                sortedBooks.sort((b1, b2) -> Double.compare(b1.getAverageRating(), b2.getAverageRating()));
                break;
            case 3:
                System.out.print("Digite o gênero: ");
                String genre = scanner.nextLine();
                // Filtra os livros cria a lista pelo gênero
                sortedBooks = books.stream()
                        .filter(b -> b.getGenre().equalsIgnoreCase(genre))
                        .collect(Collectors.toList());
                break;
            case 4:
                System.out.print("Digite o ano: ");
                int year = scanner.nextInt();
                scanner.nextLine();
                // Filtra os livros pelo ano e cria a lista
                sortedBooks = books.stream()
                        .filter(b -> b.getReleaseYear() == year)
                        .collect(Collectors.toList());
                break;
            default:
                System.out.println("Opção inválida!");
                return;
        }

        // Exibe os resultados da listagem
        if (sortedBooks.isEmpty()) {
            System.out.println("Nenhum livro encontrado!");
        } else {
            System.out.println("\n=== RESULTADOS ===");
            sortedBooks.forEach(System.out::println);
        }
    }

    /**
     * Lista filmes com diferentes opções de ordenação e filtragem.
     */
    private void listMovies() {
        System.out.println("\n=== LISTAR FILMES ===");
        System.out.println("1. Ordenar por Avaliação (Melhores)");
        System.out.println("2. Ordenar por Avaliação (Piores)");
        System.out.println("3. Filtrar por Gênero");
        System.out.println("4. Filtrar por Ano");
        System.out.print("Escolha uma opção: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        List<Movie> sortedMovies = new ArrayList<>(movies);

        switch (choice) {
            case 1:
                sortedMovies.sort((m1, m2) -> Double.compare(m2.getAverageRating(), m1.getAverageRating()));
                break;
            case 2:
                sortedMovies.sort((m1, m2) -> Double.compare(m1.getAverageRating(), m2.getAverageRating()));
                break;
            case 3:
                System.out.print("Digite o gênero: ");
                String genre = scanner.nextLine();
                sortedMovies = movies.stream()
                        .filter(m -> m.getGenre().equalsIgnoreCase(genre))
                        .collect(Collectors.toList());
                break;
            case 4:
                System.out.print("Digite o ano: ");
                int year = scanner.nextInt();
                scanner.nextLine();
                sortedMovies = movies.stream()
                        .filter(m -> m.getReleaseYear() == year)
                        .collect(Collectors.toList());
                break;
            default:
                System.out.println("Opção inválida!");
                return;
        }

        if (sortedMovies.isEmpty()) {
            System.out.println("Nenhum filme encontrado!");
        } else {
            System.out.println("\n=== RESULTADOS ===");
            sortedMovies.forEach(System.out::println);
        }
    }

    /**
     * Lista séries com diferentes opções de ordenação e filtragem.
     */
    private void listSeries() {
        System.out.println("\n=== LISTAR SÉRIES ===");
        System.out.println("1. Ordenar por Avaliação (Melhores)");
        System.out.println("2. Ordenar por Avaliação (Piores)");
        System.out.println("3. Filtrar por Gênero");
        System.out.println("4. Filtrar por Ano");
        System.out.print("Escolha uma opção: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        List<Series> sortedSeries = new ArrayList<>(series);

        switch (choice) {
            case 1:
                sortedSeries.sort((s1, s2) -> Double.compare(s2.getAverageRating(), s1.getAverageRating()));
                break;
            case 2:
                sortedSeries.sort((s1, s2) -> Double.compare(s1.getAverageRating(), s2.getAverageRating()));
                break;
            case 3:
                System.out.print("Digite o gênero: ");
                String genre = scanner.nextLine();
                sortedSeries = series.stream()
                        .filter(s -> s.getGenre().equalsIgnoreCase(genre))
                        .collect(Collectors.toList());
                break;
            case 4:
                System.out.print("Digite o ano: ");
                int year = scanner.nextInt();
                scanner.nextLine();
                sortedSeries = series.stream()
                        .filter(s -> s.getReleaseYear() == year)
                        .collect(Collectors.toList());
                break;
            default:
                System.out.println("Opção inválida!");
                return;
        }

        if (sortedSeries.isEmpty()) {
            System.out.println("Nenhuma série encontrada!");
        } else {
            System.out.println("\n=== RESULTADOS ===");
            sortedSeries.forEach(System.out::println);
        }
    }
}
