package fr.fms.apitrainings.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

//    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
//    private List<Users> users = new ArrayList<Users>();

//    public Role(Long id, String name) {
//        this.id = id;
//        this.name = name;
//    }

    @Override
    public String toString() {
        return "Role [roleId=" + id + ", name=" + name + "]";
    }

}
