package jpql;

import javax.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);

            //반환 타입이 명확할 때 사용
            TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);

            //결과 조회 API
            //1. 리스트 반환 - 결과가 1개 이상일 때
            List<Member> resultList = query.getResultList();

            for (Member member1 : resultList) {
                System.out.println("member1 = " + member1);
            }

            //2. 단일 객체 반환 - 결과가 1개일 때
            Member result = query.getSingleResult();
            System.out.println("result = " + result);
            
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }
}
