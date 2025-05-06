/**
 * Classe que representa um ator.
 * Armazena informações sobre o ator e o personagem interpretado.
 */
public class Actor {
    private String name;           // Nome do ator
    private String characterName;  // Nome do personagem interpretado pelo ator

    /**
     * Construtor da classe Actor.
     * Cria um ator com seu nome e o nome do personagem interpretado.
     * 
     * @param name Nome do ator
     * @param characterName Nome do personagem interpretado
     */
    public Actor(String name, String characterName) {
        this.name = name;
        this.characterName = characterName;
    }

    // Métodos getters e setters para acessar e modificar os atributos

    /**
     * Retorna o nome do ator
     * 
     * @return O nome do ator
     */
    public String getName() {
        return name;
    }

    /**
     * Define um novo nome para o ator
     * 
     * @param name O novo nome do ator
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retorna o nome do personagem interpretado pelo ator.
     * 
     * @return O nome do personagem
     */
    public String getCharacterName() {
        return characterName;
    }

    /**
     * Define um novo nome de personagem para o ator
     * 
     * @param characterName O novo nome do personagem
     */
    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    /**
     * Retorna uma representação em string do ator
     * 
     * @return Uma string formatada com as informações do ator
     */
    @Override
    public String toString() {
        return String.format("%s como %s", name, characterName);
    }
}
