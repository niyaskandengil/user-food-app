package org.nrk.userfoodapp.dto;

import java.time.LocalDate;

import javax.persistence.*;



@Entity
public class Food {
	@Override
	public String toString() {
		return "Food [id=" + id + ", item_name=" + item_name + ", price=" + price + ", del_time=" + del_time
				+ ", ordered_time=" + ordered_time + "]";
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = true)
	private String item_name;
	@Column(nullable = true)
	private double price;
	private String del_time;
	private String ordered_time;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getDel_time() {
		return del_time;
	}
	public void setDel_time(String del_time) {
		this.del_time = del_time;
	}
	public String getOrdered_time() {
		return ordered_time;
	}
	public void setOrdered_time(String ordered_time) {
		this.ordered_time = ordered_time;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

}
