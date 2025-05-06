import java.util.List;
import java.util.ArrayList;

/**
 * Classe abstrata que representa mídias digitais (pai de Movie e Series).
 * Estende a classe Media adicionando atributos e métodos específicos para séries e filmes.
 */
public abstract class DigitalMedia extends Media {
    protected List<Actor> cast;
    protected String originalTitle;
    protected String whereToWatch;

    /**
     * Construtor da classe DigitalMedia.
     * 
     * @param title Título
     * @param genre Gênero
     * @param releaseYear Ano de lançamento
     * @param originalTitle Título original
     * @param whereToWatch Plataforma onde a mídia pode ser assistida
     */
    public DigitalMedia(String title, String genre, int releaseYear, String originalTitle, String whereToWatch) {
        super(title, genre, releaseYear);  // Chama o construtor da classe pai (Media)
        this.originalTitle = originalTitle;
        this.whereToWatch = whereToWatch;
        this.cast = new ArrayList<>();     // Inicializa a lista de atores vazia
    }

    /**
     * Retorna a lista de atores que participam da mídia.
     */
    public List<Actor> getCast() {
        return cast;
    }

    /**
     * Define uma nova lista de atores para a mídia.
     * 
     * @param cast A nova lista de atores
     */
    public void setCast(List<Actor> cast) {
        this.cast = cast;
    }

    /**
     * Retorna o título original da mídia.
     */
    public String getOriginalTitle() {
        return originalTitle;
    }

    /**
     * Define um novo título original para a mídia.
     * 
     * @param originalTitle O novo título original da mídia
     */
    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    /**
     * Retorna a plataforma onde a mídia pode ser assistida.
     */
    public String getWhereToWatch() {
        return whereToWatch;
    }

    /**
     * Define uma nova plataforma onde a mídia pode ser assistida.
     * 
     * @param whereToWatch A nova plataforma de streaming
     */
    public void setWhereToWatch(String whereToWatch) {
        this.whereToWatch = whereToWatch;
    }

    /**
     * Adiciona um ator ao elenco da mídia.
     * 
     * @param actor O ator a ser adicionado ao elenco
     */
    public void addActor(Actor actor) {
        cast.add(actor);
    }

    /**
     * Retorna uma representação em string da mídia digital.
     * Estende o método toString da classe pai (Media) adicionando informações específicas de mídias digitais.
     */
    @Override
    public String toString() {
        return super.toString() + String.format("\nTítulo original: %s\nOnde assistir: %s",
                originalTitle, whereToWatch);
    }
}
