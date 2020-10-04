package com.example.stock_backend.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Accessors(chain = true)
public class Product {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long id;

	@Column(length = 150, nullable = false, unique = false)
	private String name;
	private String image;
	private int price;
	private int stock;

	@Setter(AccessLevel.NONE)
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date", nullable = false)
	private Date createDate;
	@Setter(AccessLevel.NONE)
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_update", nullable = false)
	private Date updateDate;
}
