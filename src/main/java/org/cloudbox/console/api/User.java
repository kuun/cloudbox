package org.cloudbox.console.api;

/**
 * Created by kevin on 7/27/16.
 */
public class User {
    private int id;
    private String name;
    private String passwd;

    public User(int id, String name, String passwd) {
        this.id = id;
        this.name = name;
        this.passwd =passwd;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", passwd='" + passwd + '\'' +
                '}';
    }
}
