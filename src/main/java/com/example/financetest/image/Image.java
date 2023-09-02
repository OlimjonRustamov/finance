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

	private long id;

	@Column
	@JsonIgnore
	private long createdAt = System.currentTimeMillis();

	@JsonProperty("content_type")
	private String contentType;

	@JsonIgnore
	private byte[] image;

	private String link;

	public String getLink() {
		return "localhost:8080/api/image/view/" + id;
	}
	public Image(String contentType, byte[] image) {
		this.contentType = contentType;
		this.setImage(image);
	}
}