package com.sosyal.tr.playersCrud.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Contract.class)
public class Contract_ {
	public static volatile SingularAttribute<Contract, Long> contractId;
	public static volatile SingularAttribute<Contract, Player> player;
	public static volatile SingularAttribute<Contract, Team> team;
	public static volatile SingularAttribute<Contract, Long> transferPrice;
	public static volatile SingularAttribute<Contract, Long> teamCommission;
	public static volatile SingularAttribute<Contract, Long> totalAmount;
	public static volatile SingularAttribute<Contract, Long> season;
}
