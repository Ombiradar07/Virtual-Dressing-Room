package com.virtualdressingroom.customer.entity;

import com.virtualdressingroom.customer.util.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class Customer {

  @Id
  private String id;
  private String firstname;
  private String lastname;
  private String email;
  private String mobile;
  private Address address;

}
