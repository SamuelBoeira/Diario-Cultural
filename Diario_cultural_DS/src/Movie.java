import java.util.List;

/**
 * Classe que representa um filme, herança de DigitalMedia.
 */
public class Movie extends DigitalMedia {
    private int duration;
    private String director;
    private String screenplay;

    /**
     * Construtor da classe Movie.
     * 
     * @param title Título do filme(possivelmente traduzido)
     * @param genre Gênero do filme
     * @param releaseYear Ano de lançamento do filme
     * @param originalTitle Título original do filme
     * @param whereToWatch Plataforma onde o filme pode ser assistido
     * @param duration Duração do filme em minutos
     * @param director Nome do diretor do filme
     * @param screenplay Nome do roteirista do filme
     */
    public Movie(String title, String genre, int releaseYear, String originalTitle,
                 String whereToWatch, int duration, String director, String screenplay) {
        super(title, genre, releaseYear, originalTitle, whereToWatch);
        this.duration = duration;
        this.director = director;
        this.screenplay = screenplay;
    }

    public int getDuration() {
        return duration;
    }

    /**
     * Define uma nova duração para o filme.
     * 
     * @param duration A nova duração do filme em minutos
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDirector() {
        return director;
    }

    /**
     * Define um novo diretor para o filme.
     * 
     * @param director O novo nome do diretor
     */
    public void setDirector(String director) {
        this.director = director;
    }

    public String getScreenplay() {
        return screenplay;
    }

    /**
     * Define um novo roteirista para o filme.
     * 
     * @param screenplay O novo nome do roteirista
     */
    public void setScreenplay(String screenplay) {
        this.screenplay = screenplay;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("\nDuração: %d min\nDiretor: %s\nEscritor: %s",
                duration, director, screenplay);
    }
}
