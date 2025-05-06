import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa uma temporada de uma série.
 * Armazena informações sobre a temporada e suas avaliações.
 */
public class Season {
    private int seasonNumber;
    private int year;
    private int episodeCount;
    private List<Review> reviews;

    /**
     * Construtor da classe Season.
     * 
     * @param seasonNumber Número da temporada
     * @param year Ano de lançamento da temporada
     * @param episodeCount Quantidade de episódios na temporada
     */
    public Season(int seasonNumber, int year, int episodeCount) {
        this.seasonNumber = seasonNumber;
        this.year = year;
        this.episodeCount = episodeCount;
        this.reviews = new ArrayList<>(); // Inicializa a lista de avaliações vazia
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public int getYear() {
        return year;
    }

    /**
     * Define um novo ano de lançamento para a temporada.
     * 
     * @param year O novo ano de lançamento
     */
    public void setYear(int year) {
        this.year = year;
    }

    public int getEpisodeCount() {
        return episodeCount;
    }

    /**
     * Define uma nova quantidade de episódios para a temporada.
     * 
     * @param episodeCount A nova quantidade de episódios
     */
    public void setEpisodeCount(int episodeCount) {
        this.episodeCount = episodeCount;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    /**
     * Adiciona uma nova avaliação à temporada
     * 
     * @param rating Avaliação de 1 a 5 estrelas
     * @param comment Comentário sobre a temporada
     * @param date Data em que a temporada foi assistida
     * @throws IllegalArgumentException Se a avaliação estiver fora do intervalo 1-5
     */
    public void addReview(int rating, String comment, LocalDate date) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Avaliação precisa ser entre 1 e 5.");
        }
        reviews.add(new Review(rating, comment, date));
    }

    /**
     * Calcula a média das avaliações da temporada.
     * Se não houver avaliações, retorna 0.
     */
    public double getAverageRating() {
        if (reviews.isEmpty()) {
            return 0;
        }
        // Stream API para calcular a média das avaliações
        return reviews.stream().mapToInt(Review::getRating).average().orElse(0);
    }

    /**
     * Retorna uma representação em string da temporada.
     */
    @Override
    public String toString() {
        return String.format("Temporada %d (%d) - %d Episódios - Nota: %.1f",
                seasonNumber, year, episodeCount, getAverageRating());
    }
}
