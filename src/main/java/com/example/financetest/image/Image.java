package com.example.financetest.image;

import com.example.financetest.Views;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(Views.Public.class)
	private long id;

	@Column
	@JsonIgnore
	@JsonView(Views.Public.class)
	private long createdAt = System.currentTimeMillis();

	@JsonProperty("content_type")
	@JsonView(Views.Public.class)
	private String contentType;

	@JsonIgnore
	private byte[] image;

	@JsonView(Views.Public.class)
	private String link;

	public String getLink() {
		return "https://finance-uz-a172736792a3.herokuapp.com" + "/api/image/view/" + id;
	}
	public Image(String contentType, byte[] image) {
		this.contentType = contentType;
		this.setImage(image);
	}
}