package com.codingbox.jpatest;

import java.time.LocalDateTime;
import java.util.List;

import com.codingbox.jpatest.domain.Member;
import com.codingbox.jpatest.domain.Orders;
import com.codingbox.jpatest.embeded.Address;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaMain {

	public static void main(String[] args) {
		EntityManagerFactory emf 
			= Persistence.createEntityManagerFactory("hello2");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		em.flush();
		em.clear();
		
		for(int i =1; i<= 10; i++) {
		Address addr = new Address("서울","역삼역","123");
		Member member = new Member();
		Orders order = new Orders();
		member.setName("Member" + i);
		member.setAddress(addr);
		order.setStatus("접수" +i);
		order.setOrderDate(LocalDateTime.now());
		em.persist(member);
		em.persist(order);
		System.out.println("Id : " +i);
		}
	
		String jpql = "select o.id, o.status, o.orderDate from Orders o order by o.id desc";
		
		List<Object[]> resultList = em.createQuery(jpql).setFirstResult(0).setMaxResults(10).getResultList();
		//여기부터
//		for (Object[] result : resultList) {
//		    Long id = (Long) result[0];
//		    String status = (String) result[1];
//		    LocalDateTime orderDate = (LocalDateTime) result[2];
//
//		    System.out.println("ID: " + id + ", Status: " + status + ", DateTime: " + orderDate);
//		}//여기까지는 다른 코드 참조해 풀었습니다.
		
		
		
		tx.commit();
		em.close();
		emf.close();
	}
	
}
