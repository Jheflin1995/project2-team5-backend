package com.revature.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "throw_usage")
@Data @AllArgsConstructor @NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ThrowUsage {
	
	@Embeddable
	@Data @AllArgsConstructor @NoArgsConstructor
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    static class Pk implements Serializable{
        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user;

        @Column(nullable = false, updatable = false)
        @Enumerated(EnumType.STRING)
        private ThrowEnum throwEnum;
    }
	
	@EmbeddedId
    private Pk id;
	
	private int uses;
	private int wins;
	
	public ThrowUsage(int uses, int wins) {
		super();
		this.uses = uses;
		this.wins = wins;
	}
	
	
	
}
