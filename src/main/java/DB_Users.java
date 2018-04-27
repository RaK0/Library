import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "users")
public class DB_Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, nullable = false)
    private String login;
    @Length(min = 5, message = "za krótkie hasło")
    private String password;
    private String name;
    private String surname;
    @Column(nullable = true)
    private Access access;

    public enum Access {
        ADMIN("Administrator"),
        CUSTO("Klient"),
        EMPLOY("Pracownik");

        private String name;

        Access(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }

    private long pesel;
    private long howMuchRent;
    private Date createDate = new Date();
    private Date modifyDate;

    @OneToMany(mappedBy = "user")
    private List<DB_Books> books;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public long getHowMuchRent() {
        return howMuchRent;
    }

    public void setHowMuchRent(long howMuchRent) {
        this.howMuchRent = howMuchRent;
    }

    public long getPesel() {
        return pesel;
    }

    public void setPesel(long pesel) {
        this.pesel = pesel;
    }

    public Access getAccess() {
        return access;
    }

    public void setAccess(Access access) {
        this.access = access;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public List<DB_Books> getBooks() {
        return books;
    }

    public void setBooks(List<DB_Books> books) {
        this.books = books;
    }
}
