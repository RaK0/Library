import javax.persistence.*;

@Entity(name = "books")
public class DB_Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private String author;
    private int releaseDate;
    private int numberOfPages;
    private String description;
    @Column(nullable = false)
    private Boolean rent;
    private int avaiable;
    @Enumerated(EnumType.STRING)
    private State state;
    @ManyToOne
    private DB_Users user;

    public enum State {
        BARDZO_DOBRY("Bardzo Dobry"),
        DOBRY("Dobry"),
        SREDNI("Średni"),
        SLABY("Słaby");

        private String name;

        State(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }

    public long getId() {
        return id;
    }
    private void setId(long id){this.id=id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name.trim();
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        author = author.trim();
        this.author = author;
    }

    public int getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        description = description.trim();
        this.description = description;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean isRent() {
        return rent;
    }

    public void setRent(Boolean rent) {
        this.rent = rent;
    }

    public int getAvaiable() {
        return avaiable;
    }

    public void setAvaiable(int avaiable) {
        this.avaiable = avaiable;
    }

    public DB_Users getUser() {
        return user;
    }

    public void setUser(DB_Users user) {
        this.user = user;
    }
}
