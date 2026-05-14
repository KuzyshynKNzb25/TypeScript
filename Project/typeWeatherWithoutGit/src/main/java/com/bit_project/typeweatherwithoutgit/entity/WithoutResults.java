package com.bit_project.typeweatherwithoutgit.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "without_results")
public class WithoutResults {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_type")
    private String userType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
