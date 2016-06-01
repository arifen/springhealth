package com.springapp.mvc.model;
import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name="User")
public class User {
	@Id
	@GeneratedValue
	@Column(name="userId")
	private long userId;

	@Column(name="name")
	private String name;
	@Column(name="emailAddress")
	private String emailAddress;

	@Column(name="loginId")
	private String loginId;

	@Column(name="password")
	private String password;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "User_ROLE",
			joinColumns = { @JoinColumn(name = "userId") },
			inverseJoinColumns = { @JoinColumn(name = "Id") })
	private Set<Role> role;

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public long getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String userName) {
		this.name = userName;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRole() {
		return role;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User{" +
				"userId=" + userId +
				", name='" + name + '\'' +
				", emailAddress='" + emailAddress + '\'' +
				", loginId='" + loginId + '\'' +
				", password='" + password + '\'' +
				", role=" + role +
				'}';
	}
}