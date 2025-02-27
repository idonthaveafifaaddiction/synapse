package com.tracelink.prodsec.plugin.veracode.sca.service;

import static org.mockito.Mockito.times;

import com.tracelink.prodsec.plugin.veracode.sca.exception.VeracodeScaProductException;
import com.tracelink.prodsec.plugin.veracode.sca.mock.VeracodeScaMocks;
import com.tracelink.prodsec.plugin.veracode.sca.model.VeracodeScaIssue;
import com.tracelink.prodsec.plugin.veracode.sca.model.VeracodeScaProject;
import com.tracelink.prodsec.plugin.veracode.sca.model.VeracodeScaWorkspace;
import com.tracelink.prodsec.plugin.veracode.sca.model.issue.IssueStatus;
import com.tracelink.prodsec.plugin.veracode.sca.repository.VeracodeScaProjectRepository;
import com.tracelink.prodsec.plugin.veracode.sca.util.model.Project;
import com.tracelink.prodsec.synapse.products.model.ProductLineModel;
import com.tracelink.prodsec.synapse.products.model.ProjectModel;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class VeracodeScaProjectServiceTest {

	@MockBean
	private VeracodeScaProjectRepository projectRepository;

	private VeracodeScaProjectService projectService;
	private VeracodeScaWorkspace workspace;
	private VeracodeScaProject project;
	private VeracodeScaIssue issue;
	private Project baseApiProject;

	@Before
	public void setup() {
		projectService = new VeracodeScaProjectService(projectRepository);
		project = VeracodeScaMocks.mockProject();

		issue = VeracodeScaMocks.mockVulnerabilityIssue();

		baseApiProject = new Project();
		baseApiProject.setId(UUID.randomUUID());
		baseApiProject.setName("API Project");
		baseApiProject.setLastScanDate("2020-06-11T21:38:06.443+0000");
		baseApiProject.setSiteId("ZYXabc");

		workspace = new VeracodeScaWorkspace();
		workspace.setName("Mock Workspace");
		workspace.setSiteId("ABCzyx");
		project.setWorkspace(workspace);
	}

	@Test
	public void testGetProjects() {
		BDDMockito.when(projectRepository.findAll()).thenReturn(Collections.emptyList());
		List<VeracodeScaProject> returnedProjects = projectService.getProjects();
		Assert.assertTrue(returnedProjects.isEmpty());

		BDDMockito.when(projectRepository.findAll())
				.thenReturn(Collections.singletonList(project));

		returnedProjects = projectService.getProjects();
		Assert.assertEquals(1, returnedProjects.size());
		Assert.assertTrue(returnedProjects.contains(project));

		workspace.setIncluded(false);
		returnedProjects = projectService.getProjects();
		Assert.assertEquals(0, returnedProjects.size());
		Assert.assertTrue(returnedProjects.isEmpty());
	}

	@Test
	public void testGetMappedProjects() {
		BDDMockito.when(projectRepository.findAllBySynapseProjectNotNull())
				.thenReturn(Collections.emptyList());

		List<VeracodeScaProject> returnedProjects = projectService.getMappedProjects();
		Assert.assertTrue(returnedProjects.isEmpty());

		BDDMockito.when(projectRepository.findAllBySynapseProjectNotNull())
				.thenReturn(Collections.singletonList(project));

		returnedProjects = projectService.getMappedProjects();
		Assert.assertEquals(1, returnedProjects.size());
		Assert.assertTrue(returnedProjects.contains(project));

		workspace.setIncluded(false);
		returnedProjects = projectService.getMappedProjects();
		Assert.assertTrue(returnedProjects.isEmpty());
	}

	@Test
	public void testGetUnmappedProjects() {
		BDDMockito.when(projectRepository.findAllBySynapseProjectIsNull())
				.thenReturn(Collections.emptyList());

		List<VeracodeScaProject> returnedProjects = projectService.getUnmappedProjects();
		Assert.assertTrue(returnedProjects.isEmpty());

		BDDMockito.when(projectRepository.findAllBySynapseProjectIsNull())
				.thenReturn(Collections.singletonList(project));

		returnedProjects = projectService.getUnmappedProjects();
		Assert.assertEquals(1, returnedProjects.size());
		Assert.assertTrue(returnedProjects.contains(project));

		workspace.setIncluded(false);
		returnedProjects = projectService.getUnmappedProjects();
		Assert.assertEquals(0, returnedProjects.size());
		Assert.assertTrue(returnedProjects.isEmpty());
	}

	@Test
	public void testGetProject() {
		BDDMockito.when(projectRepository.findById(BDDMockito.any(UUID.class)))
				.thenReturn(Optional.empty());
		VeracodeScaProject returnedProject = projectService.getProject(UUID.randomUUID());
		Assert.assertNull(returnedProject);

		BDDMockito.when(projectRepository.findById(BDDMockito.any(UUID.class)))
				.thenReturn(Optional.of(project));
		returnedProject = projectService.getProject(UUID.randomUUID());
		Assert.assertEquals(project, returnedProject);
	}

	@Test
	public void testUpdateProjectsNewProjectDefaultDevelop() {
		baseApiProject.setBranches(Collections.singletonList("develop"));

		BDDMockito.when(projectRepository.findById(BDDMockito.any(UUID.class))).thenReturn(
				Optional.empty());

		projectService.updateProjects(Collections.singletonList(baseApiProject), workspace);

		@SuppressWarnings("unchecked")
		ArgumentCaptor<Collection<VeracodeScaProject>> projectCaptor = ArgumentCaptor
				.forClass(Collection.class);
		BDDMockito.verify(projectRepository, times(1)).saveAll(projectCaptor.capture());

		Assert.assertFalse(projectCaptor.getValue().isEmpty());
		VeracodeScaProject storedProject = projectCaptor.getValue().iterator().next();
		Assert.assertEquals(baseApiProject.getId(), storedProject.getId());
		Assert.assertEquals(baseApiProject.getName(), storedProject.getName());
		Assert.assertEquals(baseApiProject.getSiteId(), storedProject.getSiteId());
		Assert.assertEquals("2020-06-11", storedProject.getLastScanDate().format(
				DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		Assert
				.assertEquals(new HashSet<>(baseApiProject.getBranches()),
						storedProject.getBranches());
		Assert.assertEquals("develop", storedProject.getDefaultBranch());
		Assert.assertEquals(workspace, storedProject.getWorkspace());
	}

	@Test
	public void testUpdateProjectsNewProjectDefaultMaster() {
		baseApiProject.setBranches(Collections.singletonList("master"));

		BDDMockito.when(projectRepository.findById(BDDMockito.any(UUID.class))).thenReturn(
				Optional.empty());

		projectService.updateProjects(Collections.singletonList(baseApiProject), workspace);

		@SuppressWarnings("unchecked")
		ArgumentCaptor<Collection<VeracodeScaProject>> projectCaptor = ArgumentCaptor
				.forClass(Collection.class);
		BDDMockito.verify(projectRepository, times(1)).saveAll(projectCaptor.capture());

		Assert.assertFalse(projectCaptor.getValue().isEmpty());
		VeracodeScaProject storedProject = projectCaptor.getValue().iterator().next();
		Assert
				.assertEquals(new HashSet<>(baseApiProject.getBranches()),
						storedProject.getBranches());
		Assert.assertEquals("master", storedProject.getDefaultBranch());
	}

	@Test
	public void testUpdateProjectsNewProjectDefaultOther() {
		baseApiProject.setBranches(Collections.singletonList("beta"));

		BDDMockito.when(projectRepository.findById(BDDMockito.any(UUID.class))).thenReturn(
				Optional.empty());

		projectService.updateProjects(Collections.singletonList(baseApiProject), workspace);

		@SuppressWarnings("unchecked")
		ArgumentCaptor<Collection<VeracodeScaProject>> projectCaptor = ArgumentCaptor
				.forClass(Collection.class);
		BDDMockito.verify(projectRepository, times(1)).saveAll(projectCaptor.capture());

		Assert.assertFalse(projectCaptor.getValue().isEmpty());
		VeracodeScaProject storedProject = projectCaptor.getValue().iterator().next();
		Assert
				.assertEquals(new HashSet<>(baseApiProject.getBranches()),
						storedProject.getBranches());
		Assert.assertEquals("beta", storedProject.getDefaultBranch());
	}

	@Test
	public void testUpdateProjectsNewProjectNoBranches() {
		baseApiProject.setBranches(Collections.emptyList());

		BDDMockito.when(projectRepository.findById(BDDMockito.any(UUID.class))).thenReturn(
				Optional.empty());

		projectService.updateProjects(Collections.singletonList(baseApiProject), workspace);

		@SuppressWarnings("unchecked")
		ArgumentCaptor<Collection<VeracodeScaProject>> projectCaptor = ArgumentCaptor
				.forClass(Collection.class);
		BDDMockito.verify(projectRepository, times(1)).saveAll(projectCaptor.capture());

		Assert.assertTrue(projectCaptor.getValue().isEmpty());
	}

	@Test
	public void testUpdateProjectsExistingProject() {
		baseApiProject.setBranches(Collections.singletonList("master"));

		BDDMockito.when(projectRepository.findById(BDDMockito.any(UUID.class))).thenReturn(
				Optional.of(project));

		projectService.updateProjects(Collections.singletonList(baseApiProject), workspace);

		@SuppressWarnings("unchecked")
		ArgumentCaptor<Collection<VeracodeScaProject>> projectCaptor = ArgumentCaptor
				.forClass(Collection.class);
		BDDMockito.verify(projectRepository, times(1)).saveAll(projectCaptor.capture());

		Assert.assertFalse(projectCaptor.getValue().isEmpty());
		VeracodeScaProject storedProject = projectCaptor.getValue().iterator().next();
		Assert.assertEquals(project, storedProject);
		Assert.assertTrue(project.getBranches().contains("develop"));
		Assert.assertTrue(project.getBranches().contains("master"));
		Assert.assertEquals("develop", project.getDefaultBranch());
	}

	@Test
	public void testGetUnresolvedIssuesForProductLineNull() {
		// Case where there are no projects in a product line
		ProductLineModel productLine = new ProductLineModel();
		productLine.setProjects(Collections.emptyList());

		Assert.assertNull(projectService.getUnresolvedIssuesForProductLine(productLine));

		// Case where all projects in a product line are unmapped
		ProjectModel project = new ProjectModel();
		project.setName("project");
		productLine.setProjects(Collections.singletonList(project));

		BDDMockito.when(projectRepository.findBySynapseProject(project))
				.thenReturn(Collections.emptyList());
		Assert.assertNull(projectService.getUnresolvedIssuesForProductLine(productLine));
	}

	@Test
	public void testGetUnresolvedIssuesForProductLineEmpty() {
		ProjectModel synapseProject = new ProjectModel();
		synapseProject.setName("project");

		ProductLineModel productLine = new ProductLineModel();
		productLine.setProjects(Collections.singletonList(synapseProject));

		BDDMockito.when(projectRepository.findBySynapseProject(synapseProject))
				.thenReturn(Collections.singletonList(project));

		// Case where project has no issues
		project.setIssues(Collections.emptyList());
		Assert.assertTrue(projectService.getUnresolvedIssuesForProductLine(productLine).isEmpty());

		// Case where issues are fixed
		issue.setIssueStatus(IssueStatus.FIXED);
		project.setIssues(Collections.singletonList(issue));

		Assert.assertTrue(projectService.getUnresolvedIssuesForProductLine(productLine).isEmpty());
	}

	@Test
	public void testGetUnresolvedIssuesForProductLineNotEmpty() {
		ProjectModel synapseProject = new ProjectModel();
		synapseProject.setName("project");

		ProductLineModel productLine = new ProductLineModel();
		productLine.setProjects(Collections.singletonList(synapseProject));

		BDDMockito.when(projectRepository.findBySynapseProject(synapseProject))
				.thenReturn(Collections.singletonList(project));

		// Case where there is a single mapped project
		project.setIssues(Collections.singletonList(issue));
		List<VeracodeScaIssue> returnedIssues = projectService
				.getUnresolvedIssuesForProductLine(productLine);
		Assert.assertEquals(1, returnedIssues.size());
		Assert.assertTrue(returnedIssues.contains(issue));

		// Case where there are two mapped projects
		ProjectModel synapseProject2 = new ProjectModel();
		synapseProject2.setName("synapseProject2");

		productLine.setProjects(Arrays.asList(synapseProject, synapseProject2));

		BDDMockito.when(projectRepository.findBySynapseProject(synapseProject2))
				.thenReturn(Collections.singletonList(project));

		returnedIssues = projectService.getUnresolvedIssuesForProductLine(productLine);
		Assert.assertEquals(2, returnedIssues.size());
		Assert.assertTrue(returnedIssues.contains(issue));
	}

	@Test
	public void testGetUnresolvedIssuesForProjectNull() {
		ProjectModel project = new ProjectModel();
		project.setName("project");

		BDDMockito.when(projectRepository.findBySynapseProject(project))
				.thenReturn(Collections.emptyList());
		Assert.assertNull(projectService.getUnresolvedIssuesForProject(project));
	}

	@Test
	public void testGetUnresolvedIssuesForProjectEmpty() {
		ProjectModel synapseProject = new ProjectModel();
		synapseProject.setName("synapseProject");

		BDDMockito.when(projectRepository.findBySynapseProject(synapseProject))
				.thenReturn(Collections.singletonList(project));

		// Case where project has no issues
		project.setIssues(Collections.emptyList());
		Assert.assertTrue(projectService.getUnresolvedIssuesForProject(synapseProject).isEmpty());

		// Case where issues are fixed
		issue.setIssueStatus(IssueStatus.FIXED);
		project.setIssues(Collections.singletonList(issue));

		Assert.assertTrue(projectService.getUnresolvedIssuesForProject(synapseProject).isEmpty());
	}

	@Test
	public void testGetUnresolvedIssuesForProjectNotEmpty() {
		ProjectModel synapseProject = new ProjectModel();
		synapseProject.setName("synapseProject");

		BDDMockito.when(projectRepository.findBySynapseProject(synapseProject))
				.thenReturn(Collections.singletonList(project));

		// Case where there is a single issue
		project.setIssues(Collections.singletonList(issue));
		List<VeracodeScaIssue> returnedIssue = projectService
				.getUnresolvedIssuesForProject(synapseProject);
		Assert.assertEquals(1, returnedIssue.size());
		Assert.assertTrue(returnedIssue.contains(issue));

		// Case where there are two issues
		VeracodeScaIssue issue2 = VeracodeScaMocks.mockVulnerabilityIssue();
		issue2.setIgnored(true);

		project.setIssues(Arrays.asList(issue2, issue));

		returnedIssue = projectService.getUnresolvedIssuesForProject(synapseProject);
		Assert.assertEquals(1, returnedIssue.size());
		Assert.assertFalse(returnedIssue.contains(issue2));
		Assert.assertTrue(returnedIssue.contains(issue));
	}

	@Test
	public void testCreateMappingNull() {
		projectService.createMapping(null, project.getName());
		BDDMockito.verify(projectRepository, times(0)).saveAndFlush(BDDMockito.any());

		projectService.createMapping(new ProjectModel(), null);
		BDDMockito.verify(projectRepository, times(0)).saveAndFlush(BDDMockito.any());

		projectService.createMapping(null, null);
		BDDMockito.verify(projectRepository, times(0)).saveAndFlush(BDDMockito.any());
	}

	@Test
	public void testCreateMapping() {
		ProjectModel synapseProject = new ProjectModel();
		synapseProject.setName("synapseProject");

		BDDMockito.when(projectRepository.findByName(BDDMockito.anyString())).thenReturn(project);
		projectService.createMapping(synapseProject, project.getName());
		BDDMockito.verify(projectRepository, times(1)).saveAndFlush(BDDMockito.any());
		Assert.assertEquals(synapseProject, project.getSynapseProject());
	}

	@Test
	public void testDeleteMappingNull() {
		projectService.deleteMapping(null);
		BDDMockito.verify(projectRepository, times(0)).saveAndFlush(BDDMockito.any());
	}

	@Test
	public void testDeleteMapping() {
		BDDMockito.when(projectRepository.findByName(BDDMockito.anyString())).thenReturn(project);
		projectService.deleteMapping(project.getName());
		BDDMockito.verify(projectRepository, times(1)).saveAndFlush(BDDMockito.any());
		Assert.assertNull(project.getSynapseProject());
	}

	@Test
	public void testSetDefaultProjectNull() {
		BDDMockito.when(projectRepository.findByName(BDDMockito.anyString())).thenReturn(null);
		try {
			projectService.setDefaultBranch(project.getName(), project.getDefaultBranch());
			Assert.fail("Exception should have been thrown");
		} catch (VeracodeScaProductException e) {
			Assert.assertEquals("No project found with the name: Mock Project", e.getMessage());
		}
	}

	@Test
	public void testSetDefaultProjectInvalidBranch() {
		BDDMockito.when(projectRepository.findByName(BDDMockito.anyString())).thenReturn(project);
		try {
			projectService.setDefaultBranch(project.getName(), "foo");
			Assert.fail("Exception should have been thrown");
		} catch (VeracodeScaProductException e) {
			Assert.assertEquals("No branch found with the name: foo", e.getMessage());
		}
	}

	@Test
	public void testSetDefaultProject() {
		project.addBranches(new HashSet<>(Arrays.asList("develop", "master")));
		BDDMockito.when(projectRepository.findByName(BDDMockito.anyString())).thenReturn(project);
		Assert.assertEquals("develop", project.getDefaultBranch());
		projectService.setDefaultBranch(project.getName(), "master");
		Assert.assertEquals("master", project.getDefaultBranch());
		BDDMockito.verify(projectRepository, times(1)).saveAndFlush(project);
	}
}
