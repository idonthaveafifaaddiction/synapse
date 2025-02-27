package com.tracelink.prodsec.synapse.scheduler.controller;

import com.tracelink.prodsec.synapse.scheduler.model.JobsModel;
import com.tracelink.prodsec.synapse.scheduler.service.JobsService;
import com.tracelink.prodsec.synapse.test.TestSynapseBootApplicationCore;
import java.util.Collections;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestSynapseBootApplicationCore.class)
@AutoConfigureMockMvc
public class JobDisplayControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private JobsService jobsService;

	@Test
	@WithMockUser
	public void testJobDisplay() throws Exception {
		JobsModel mockJob = BDDMockito.mock(JobsModel.class);
		BDDMockito.when(jobsService.getAllJobs()).thenReturn(Collections.singletonList(mockJob));

		mockMvc.perform(MockMvcRequestBuilders.get("/jobs"))
				.andExpect(MockMvcResultMatchers.model().attribute("jobs", Collections.singletonList(mockJob)));
	}
}
