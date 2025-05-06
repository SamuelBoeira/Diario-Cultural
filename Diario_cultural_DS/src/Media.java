import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstrata que representa uma mídia genérica (Book, Movie e Series).
 * Serve como base para todos os tipos de mídia no sistema de Diário Cultural que herdam os métodos de Media.
 */
public abstract class Media {
    protected String title;
    protected String genre;
    protected int releaseYear;
    protected boolean consumed;
    protected LocalDate consumptionDate;
    protected List<Review> reviews;

    /**
     * Construtor da classe Media.
     * 
     * @param title Título da mídia
     * @param genre Gênero da mídia
     * @param releaseYear Ano de lançamento da mídia
     */
    public Media(String title, String genre, int releaseYear) {
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.consumed = false;  // Inicializa como não consumida
        this.consumptionDate = null;  // Data começa como null
        this.reviews = new ArrayList<>(); // Inicializa a lista de avaliações vazia
    }


    public String getTitle() {
        return title;
    }

    /**
     * Define um novo título para a mídia.
     * 
     * @param title O novo título da mídia
     */
    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    /**
     * Define um novo gênero para a mídia.
     * 
     * @param genre O novo gênero da mídia
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    /**
     * Define um novo ano de lançamento para a mídia.
     * 
     * @param releaseYear O novo ano de lançamento da mídia
     */
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public boolean isConsumed() {
        return consumed;
    }

    /**
     * Define o estado de consumo da mídia.
     * 
     * @param consumed O novo estado de consumo (true para consumida, false para não consumida)
     */
    public void setConsumed(boolean consumed) {
        this.consumed = consumed;
    }

    public LocalDate getConsumptionDate() {
        return consumptionDate;
    }

    /**
     * Define a data em que a mídia foi consumida.
     * 
     * @param consumptionDate A nova data de consumo da mídia
     */
    public void setConsumptionDate(LocalDate consumptionDate) {
        this.consumptionDate = consumptionDate;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    /**
     * Valida se a avaliação está entre 1 e 5 e se a data não é nula.
     * 
     * @param rating Avaliação de 1 a 5 estrelas
     * @param comment Comentário sobre a mídia
     * @param date Data em que a mídia foi consumida
     * @throws IllegalArgumentException Se a avaliação estiver fora do intervalo 1-5 ou a data for nula
     */
    public void addReview(int rating, String comment, LocalDate date) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Avaliação precisa ser entre 1 e 5");
        }
        if (date == null) {
            throw new IllegalArgumentException("Data não pode ser nula");
        }
        reviews.add(new Review(rating, comment, date));
    }

    /**
     * Marca a mídia como consumida na data especificada.
     * 
     * @param date Data em que a mídia foi consumida
     * @throws IllegalArgumentException Se a data for nula
     */
    public void markAsConsumed(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Data não pode ser nula");
        }
        this.consumed = true;
        this.consumptionDate = date;
    }

    /**
     * Calcula a média das avaliações da mídia.
     */
    public double getAverageRating() {
        if (reviews.isEmpty()) {
            return 0;
        }
        // Stream API para calcular a média das avaliações
        return reviews.stream().mapToInt(Review::getRating).average().orElse(0);
    }

    @Override
    public String toString() {
        return String.format("Título: %s (%d)\nGênero: %s\nConsumido: %s\nAvaliação: %.1f",
                title, releaseYear, genre, consumed ? "Sim" : "Não", getAverageRating());
    }
}
