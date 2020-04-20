package cz.ear.rrs.model;

import javax.persistence.*;


@MappedSuperclass
public abstract class Reservable extends AbstractEntity {
//    private int id;
    private String location;
    private String name;

    @Enumerated(EnumType.STRING)
    private Rights rights;

//    @Id
//    @Column(name = "id")
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    @Basic
    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String area) {
        this.location = area;
    }

    @Basic
    @Column(name = "rights")
    public Rights getRights() {
        return rights;
    }

    public void setRights(Rights rights) {
        this.rights = rights;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
