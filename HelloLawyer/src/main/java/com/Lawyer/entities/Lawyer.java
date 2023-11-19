package com.Lawyer.entities;

import jakarta.persistence.*;
import org.apache.catalina.User;
import org.hibernate.engine.internal.Cascade;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "LAWYER")
public class Lawyer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int lid;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String chamber;
    private String noOfChamber;
    private String imageUrl;
    @Column(length = 5000)
    private String about;
    private boolean available;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy =  "lawyer")
    private List<Client> clients = new ArrayList<>();

    public Lawyer() {
    }

    public Lawyer(int lid, String firstName, String lastName, int age, String email, String chamber, String noOfChamber, String imageUrl, String about, boolean available) {
        this.lid = lid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.chamber = chamber;
        this.noOfChamber = noOfChamber;
        this.imageUrl = imageUrl;
        this.about = about;
        this.available = available;
    }

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getChamber() {
        return chamber;
    }

    public void setChamber(String chamber) {
        this.chamber = chamber;
    }

    public String getNoOfChamber() {
        return noOfChamber;
    }

    public void setNoOfChamber(String noOfChamber) {
        this.noOfChamber = noOfChamber;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public String toString() {
        return "Lawyer{" +
                "lid=" + lid +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", chamber='" + chamber + '\'' +
                ", noOfChamber='" + noOfChamber + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", about='" + about + '\'' +
                ", available=" + available +
                '}';
    }
}
