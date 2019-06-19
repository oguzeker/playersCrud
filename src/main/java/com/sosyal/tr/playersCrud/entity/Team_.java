package com.sosyal.tr.playersCrud.entity;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Team.class)
public class Team_ {
	public static volatile SingularAttribute<Team, Long> teamId;
	public static volatile SingularAttribute<Team, String> currency;
	public static volatile SingularAttribute<Team, String> name;
	public static volatile ListAttribute<Team, Contract> contracts;
}
