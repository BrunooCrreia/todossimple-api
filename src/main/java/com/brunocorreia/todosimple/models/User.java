package com.brunocorreia.todosimple.models;

import org.antlr.v4.runtime.misc.NotNull;
import org.apache.commons.logging.Log;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;



@Entity
@Table(name = User.TABLE_NAME)
public class User {

    public interface CreateUser {
    }

    public interface UpdateUser {
    }// public interface CreateUser { } e public interface UpdateUser { }: São
     // interfaces de marcação usadas como grupos de validação.
     // Isso permite que você aplique diferentes conjuntos de validações com base no
     // contexto.
     // Por exemplo, ao criar um usuário, você pode aplicar validações específicas
     // desse contexto usando o grupo CreateUser.

    public static final String TABLE_NAME = "user";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "username", length = 100, nullable = false, unique = true)
    // @NotNull(groups = CreateUser.class)
    // @NotNull(groups = CreateUser.class) //Pode ser NotEmpty, porem minha versao
    // nao permitiu.
    @Size(min = 2, max = 100)
    private String userName;
    
    //@JsonProperty(access = Access.WRITE_ONLY)
    @Column(name = "password", length = 60, nullable = false)
    // @NotNull(groups = {CreateUser.class, UpdateUser.class})
    @Size(groups = CreateUser.class, min = 8, max = 60)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Task> tasks = new ArrayList<Task>();

    public User() {
    }

    public User(Long id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public User id(Long id) {
        setId(id);
        return this;
    }

    public User userName(String userName) {
        setUserName(userName);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", userName='" + getUserName() + "'" +
                "}";
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        return result;
    }

    public User(Long id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User password(String password) {
        setPassword(password);
        return this;
    }

    public User(Long id, String userName, String password, List<Task> tasks) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public User tasks(List<Task> tasks) {
        setTasks(tasks);
        return this;
    }


@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User other = (User) o;
    if (id == null) {
        return other.id == null;
    } else if (!id.equals(other.id)) {
        return false;
    }
    return Objects.equals(userName, other.userName) &&
            Objects.equals(password, other.password);
}

}
