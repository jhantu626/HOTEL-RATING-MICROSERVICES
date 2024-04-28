package io.app.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "users")
public class User {
	@Id
	private String id;
	private String name;
	@Column(nullable = false,unique = true)
	private String email;
	private String about;
	@Transient
	private List<Rating> ratings=new ArrayList<>();
}








