package com.sosyal.tr.playersCrud.entity;

import java.util.Date;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Player.class)
public class Player_ {
	public static volatile SingularAttribute<Player, Long> playerId;
	public static volatile SingularAttribute<Player, Date> birthdate;
	public static volatile SingularAttribute<Player, Date> careerStartDate;
	public static volatile SingularAttribute<Player, String> name;
	public static volatile ListAttribute<Player, Contract> contracts;
}
