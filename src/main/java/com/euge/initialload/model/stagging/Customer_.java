package com.euge.initialload.model.stagging;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-03-07T13:25:18.406+0100")
@StaticMetamodel(Customer.class)
public class Customer_ {
	public static volatile SingularAttribute<Customer, BigDecimal> id;
	public static volatile SingularAttribute<Customer, String> code;
	public static volatile SingularAttribute<Customer, BigDecimal> integrationid;
	public static volatile SingularAttribute<Customer, String> job;
	public static volatile SingularAttribute<Customer, BigDecimal> numtries;
	public static volatile SingularAttribute<Customer, String> operation;
	public static volatile SingularAttribute<Customer, String> segment;
	public static volatile SingularAttribute<Customer, String> status;
}
