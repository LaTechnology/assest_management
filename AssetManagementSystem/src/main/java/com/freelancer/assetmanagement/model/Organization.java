package com.freelancer.assetmanagement.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
//@ToString
@Entity
public class Organization extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1289637811507127416L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long organizationId;
	private String organizationCode;
	private String name;
	private String emailId;
	private String location;
	private String contactNumber;
	private Boolean active;
	
	@OneToMany(mappedBy = "organization")
	@JsonIgnore
//	@JsonBackReference
	private List<Asset> asset;
	
	@OneToMany(mappedBy = "organization")
	@JsonIgnore
//	@JsonBackReference
	private List<Employee> employees;

	@Override
	public String toString() {
		return "Organization [organizationId=" + organizationId + ", organizationCode=" + organizationCode + ", name="
				+ name + ", emailId=" + emailId + ", location=" + location + ", contactNumber=" + contactNumber
				+ ", active=" + active + ", employees=" + employees + "]";
	}
}
