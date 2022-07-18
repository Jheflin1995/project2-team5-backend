package com.revature.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "throw_usage")
@Data @AllArgsConstructor @NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = ThrowUsage.class)
public class ThrowUsage {
	
	@Id
	@Column(name="throw_usage_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int uses;
	private int wins;
	
	@Column(nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private ThrowEnum throwEnum;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
	
	public ThrowUsage(int uses, int wins) {
		super();
		this.uses = uses;
		this.wins = wins;
	}
	
	
	
}
