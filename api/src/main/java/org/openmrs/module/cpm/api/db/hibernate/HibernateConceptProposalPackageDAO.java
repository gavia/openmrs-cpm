package org.openmrs.module.cpm.api.db.hibernate;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.openmrs.module.cpm.ConceptProposalPackage;
import org.openmrs.module.cpm.api.db.ConceptProposalPackageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Hibernate specific database methods for {@link ConceptProposalPackageDAO}
 */
@Repository
public class HibernateConceptProposalPackageDAO implements ConceptProposalPackageDAO {

	private static Log log = LogFactory.getLog(HibernateConceptProposalPackageDAO.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * @see org.openmrs.module.metadatasharing.api.ConceptProposalService.getAllConceptProposalPackages()
	 */
	@Override
	public List<ConceptProposalPackage> getAllConceptProposalPackages() {
		@SuppressWarnings("unchecked")
        List<ConceptProposalPackage> result = (List<ConceptProposalPackage>) sessionFactory.getCurrentSession().createQuery("from ConceptProposalPackage").list();
		if (log.isDebugEnabled()) {
			log.debug("getAllConceptProposalPackages returned: " + result.size() + " results");
		}
		return result;
	}
	
	/**
	 * @see org.openmrs.module.metadatasharing.api.ConceptProposalService.getConceptProposalPackageById(Integer id)
	 */
	@Override
	public ConceptProposalPackage getConceptProposalPackageById(Integer id) {
		Query query = sessionFactory.getCurrentSession().createQuery("from ConceptProposalPackage package where package.id = :id");
		query.setInteger("id", id);
		ConceptProposalPackage result = (ConceptProposalPackage) query.uniqueResult();
		if (log.isDebugEnabled()) { log.debug("getConceptProposalPackageById returned: " + result); }
		return result;
	}
	
	/**
	 * @see org.openmrs.module.metadatasharing.api.ConceptProposalService.getConceptProposalPackageByUuid(Integer uuid)
	 */
	@Override
	public ConceptProposalPackage getConceptProposalPackageByUuid(String uuid) {
		Query query = sessionFactory.getCurrentSession().createQuery("from ConceptProposalPackage package where package.uuid = :uuid");
		query.setString("uuid", uuid);
		ConceptProposalPackage result = (ConceptProposalPackage) query.uniqueResult();
		if (log.isDebugEnabled()) { log.debug("getConceptProposalPackageByUuid returned: " + result); }
		return result;
	}
	
	/**
	 * @see org.openmrs.module.metadatasharing.api.ConceptProposalService.saveConceptProposalPackage(ConceptProposalPackage conceptPackage)
	 */
	@Override
	public ConceptProposalPackage saveConceptProposalPackage(ConceptProposalPackage conceptPackage) {
		sessionFactory.getCurrentSession().saveOrUpdate(conceptPackage);
		return conceptPackage; 
	}
	
	/**
	 * @see org.openmrs.module.metadatasharing.api.ConceptProposalService.deleteConceptProposalPackage(ConceptProposalPackage conceptPackage)
	 */
	@Override
	public void deleteConceptProposalPackage(ConceptProposalPackage conceptPackage) {
		sessionFactory.getCurrentSession().delete(conceptPackage);
	}

}
