package com.Lawyer.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @NotBlank(message= "First name should not be blank !!")
    @Size(min=2, max=20, message= "Length between 2 to 20 allowed !!")
    private String firstName;
    @Size(max=20, message="Maximum 20 character allowed !!")
    private String lastName;
    private int age;
    
    @Column(unique = true)
    @NotBlank(message = "Email must be required !!")
    @Email
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message="please provide a valid Email !!")
    private String email;
    private String role;
    
    @NotBlank(message = "Password must be required !!")
    @Size(min = 4, max = 32, message = "Size between 4 to 32 character allowed !!")
    private String password;
    private String imageUrl;
    @Column(length = 500)
    private String about;
    private boolean enable;

    @ManyToOne
    private Lawyer lawyer;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy =  "client")
    private List<ClientPost> posts = new ArrayList<>();

    public Client() {
    }

    public List<ClientPost> getPosts() {
        return posts;
    }

    public void setPosts(List<ClientPost> posts) {
        this.posts = posts;
    }

    public Client(int id, String firstName, String lastName, int age, String email, String role, String password, String imageUrl, String about, boolean enable) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.role = role;
        this.password = password;
        this.imageUrl = imageUrl;
        this.about = about;
        this.enable = enable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Lawyer getLawyer() {
        return lawyer;
    }

    public void setLawyer(Lawyer lawyer) {
        this.lawyer = lawyer;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", password='" + password + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", about='" + about + '\'' +
                ", enable=" + enable +
                '}';
    }
}
