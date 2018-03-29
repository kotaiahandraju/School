package com.aurospaces.neighbourhood.db.basemodel;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



import java.util.Date;
import java.math.BigDecimal;


/**
 *
 * @author autogenerated
 */



public class BaseUserRole1 
{

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
		 /** Field mapping. **/
@Column(name= "id")
protected int id   = 0;

/** Field mapping. **/
@Column(name= "userId")
protected int userId ;

/** Field mapping. **/
@Column(name= "roleId")
protected int roleId ;

public int getId()
{
  return id;
}
public void setId(final int id)
{
  this.id = id;
}
public int getUserId()
{
  return userId;
}
public void setUserId(final int userId)
{
  this.userId = userId;
}
public int getRoleId()
{
  return roleId;
}
public void setRoleId(final int roleId)
{
  this.roleId = roleId;
}

}