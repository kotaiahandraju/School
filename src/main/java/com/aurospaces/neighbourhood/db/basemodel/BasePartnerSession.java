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



public class BasePartnerSession 
{

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
		 /** Field mapping. **/
@Column(name= "id")
protected int id   = 0;

/** Field mapping. **/
@Column(name= "partner_id")
protected int partnerId ;

/** Field mapping. **/
@Column(name= "created_time")
protected Date createdTime ;

/** Field mapping. **/
@Column(name= "updated_time")
protected Date updatedTime ;

public int getId()
{
  return id;
}
public void setId(final int id)
{
  this.id = id;
}
public int getPartnerId()
{
  return partnerId;
}
public void setPartnerId(final int partnerId)
{
  this.partnerId = partnerId;
}
public Date getCreatedTime()
{
  return createdTime;
}
public void setCreatedTime(final Date createdTime)
{
  this.createdTime = createdTime;
}
public Date getUpdatedTime()
{
  return updatedTime;
}
public void setUpdatedTime(final Date updatedTime)
{
  this.updatedTime = updatedTime;
}

}