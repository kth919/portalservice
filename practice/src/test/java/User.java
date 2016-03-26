/**
 * Created by admin on 2016-03-25.
 */
public class User {

    Long id;
    String name;
    String password;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {

        return password;
    }

    public void setId(long id) {
        this.id = id;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
