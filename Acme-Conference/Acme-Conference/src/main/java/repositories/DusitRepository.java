
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Dusit;

@Repository
public interface DusitRepository extends JpaRepository<Dusit, Integer> {

	@Query("select q from Dusit q where q.administrator.id=?1")
	Collection<Dusit> findDusitsByPrincipal(int id);

	@Query("select q from Dusit q where q.mode='FINAL'")
	Collection<Dusit> findDusitsFinal();

	@Query("select q from Dusit q where q.mode='FINAL' and q.conference.id=?1")
	Collection<Dusit> findDusitsByConference(int conferenceId);

	@Query("select avg(1.0*(select count(q) from Dusit q where q.conference.id = c.id and q.mode='FINAL')),stddev(1.0*(select count(q) from Dusit q where q.conference.id = c.id and q.mode='FINAL')) from Conference c")
	Collection<Double> statsNumberDusits();

	@Query("select sum(case when q.mode='FINAL' then 1.0 else 0.0 end)/count(q) from Dusit q")
	Double publishedRatio();
	
	@Query("select sum(case when q.mode='DRAFT' then 1.0 else 0.0 end)/count(q) from Dusit q")
	Double unpublishedRatio();

}
