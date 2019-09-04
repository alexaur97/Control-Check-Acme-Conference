
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Quolet;

@Repository
public interface QuoletRepository extends JpaRepository<Quolet, Integer> {

	@Query("select q from Quolet q where q.administrator.id=?1")
	Collection<Quolet> findQuoletsByPrincipal(int id);

	@Query("select q from Quolet q where q.mode='FINAL'")
	Collection<Quolet> findQuoletsFinal();

	@Query("select q from Quolet q where q.mode='FINAL' and q.conference.id=?1")
	Collection<Quolet> findQuoletsByConference(int conferenceId);

	@Query("select avg(1.0*(select count(q) from Quolet q where q.conference.id = c.id)),stddev(1.0*(select count(q) from Quolet q where q.conference.id = c.id)) from Conference c")
	Collection<Double> statsNumberQuolets();

	@Query("select sum(case when q.mode='FINAL' then 1.0 else 0.0 end)/count(q) from Quolet q")
	Double publishedRatio();
	
	@Query("select sum(case when q.mode='DRAFT' then 1.0 else 0.0 end)/count(q) from Quolet q")
	Double unpublishedRatio();

}
