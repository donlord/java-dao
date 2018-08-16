package com.cooksys.DAO;

import java.util.List;




public class App 
{
    public static void main( String[] args )
    {
		PersonDAO pDAO = new PersonDAO("jdbc:postgresql://localhost:5432","postgres","bondstone");
		Person p = pDAO.get(1);
		LocationDAO lDAO = new LocationDAO("jdbc:postgresql://localhost:5432","postgres","bondstone");
		Location l = lDAO.get(1);
		InterestDAO iDAO = new InterestDAO("jdbc:postgresql://localhost:5432","postgres","bondstone");
		Interest i = iDAO.get(1);
		
		
		//working save functions for testing
		Person insertedPerson = pDAO.save(new Person(6,"Liz","Brydon",23)); 
		System.out.println(insertedPerson.getFirstName()+" "+insertedPerson.getId());
		
		Location insertedLocation = lDAO.save(new Location("Jackson","Tennessee","USA"));
		System.out.println(insertedLocation.getCity());
		
		Interest insertedInterest = iDAO.save(new Interest("boxing"));
		System.out.println(insertedInterest.getTitle());
		
		//working getMore function to output more data
		List<Person> people = pDAO.getMore("Donny");
		for (Person per: people) {
			System.out.println(per.getFirstName()+" likes "+per.getInterest()+ " at "+per.getLocation());
		}
		
//		Person updatedPerson = pDAO.save2(new Person(6,"Liz","Brydon",23,"baston",1,"tv"));
//		System.out.println(updatedPerson.getLocation());
//		
    }
}
