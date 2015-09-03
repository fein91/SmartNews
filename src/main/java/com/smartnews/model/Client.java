package com.smartnews.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = Client.TABLE_NAME)
@NamedQueries({@NamedQuery(name = Client.FIND_ALL, query = "select c from Client c")})
public class Client implements ModelEntity {
    public static final String FIND_ALL = "Client.findAll";
    public static final String TABLE_NAME = "client";
    private static final String SEQUENCE_GENERATOR = "client_seq_gen";
    private static final String SEQUENCE_NAME = "client_seq";
    private static final String CLIENT_FK = "client_fk";
    private static final String FOLDER_REFERENCED_COLUMN = "id";

    public Client() {
    }

    public Client(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_GENERATOR)
    @SequenceGenerator(name = SEQUENCE_GENERATOR, sequenceName = SEQUENCE_NAME, allocationSize=1)
    private long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = CLIENT_FK, referencedColumnName = FOLDER_REFERENCED_COLUMN)
    private List<Folder> folders;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Folder> getFolders() {
        return folders;
    }

    public void setFolders(List<Folder> folders) {
        this.folders = folders;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (id != client.id) return false;
        if (name != null ? !name.equals(client.name) : client.name != null) return false;

        return true;
    }
}
