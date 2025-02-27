package com.tracelink.prodsec.plugin.veracode.sca.repository;

import com.tracelink.prodsec.plugin.veracode.sca.model.VeracodeScaProject;
import com.tracelink.prodsec.synapse.products.model.ProjectModel;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for DB integration with the {@link VeracodeScaProject}.
 *
 * @author mcool
 */
@Repository
public interface VeracodeScaProjectRepository extends JpaRepository<VeracodeScaProject, UUID> {

	/**
	 * Gets all {@link VeracodeScaProject} entities where the Synapse {@link
	 * ProjectModel} is not null
	 *
	 * @return list of mapped Veracode SCA apps
	 */
	List<VeracodeScaProject> findAllBySynapseProjectNotNull();

	/**
	 * Gets all {@link VeracodeScaProject} entities where the Synapse {@link
	 * ProjectModel} is null
	 *
	 * @return list of unmapped Veracode SCA apps
	 */
	List<VeracodeScaProject> findAllBySynapseProjectIsNull();

	/**
	 * Gets the list of {@link VeracodeScaProject} for the given Synapse {@link ProjectModel}.
	 * This does a search by the join column automatically.
	 *
	 * @param synapseProject the Synapse project to search by
	 * @return the Veracode SCA project mapped to the Synapse project, or null
	 */
	List<VeracodeScaProject> findBySynapseProject(ProjectModel synapseProject);

	/**
	 * Gets the {@link VeracodeScaProject} with the given name.
	 *
	 * @param name the name to search by
	 * @return the Veracode SCA project with the given name, or null
	 */
	VeracodeScaProject findByName(String name);
}
