package com.perisatto.fiapprj.request_manager.infra.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.LinkedHashSet;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perisatto.fiapprj.request_manager.application.usecases.CreateRequestUseCase;
import com.perisatto.fiapprj.request_manager.application.usecases.GetRequestUseCase;
import com.perisatto.fiapprj.request_manager.application.usecases.UpdateRequestUseCase;
import com.perisatto.fiapprj.request_manager.domain.entities.Request;
import com.perisatto.fiapprj.request_manager.domain.entities.RequestStatus;
import com.perisatto.fiapprj.request_manager.infra.controllers.dtos.CreateRequestRequestDTO;
import com.perisatto.fiapprj.request_manager.infra.controllers.dtos.UpdateRequestRequestDTO;

@ActiveProfiles(value = "test")
public class RequestManagerRestControllerTest {
	
	private MockMvc mockMvc;
	
	private String token = "eyJraWQiOiI1ZzhpN01DZ0dUZVZYRGkrT3pWTHlzU1Z3R3JFbzlJMTF6MXBxSXVuY2xVPSIsImFsZyI6IlJTMjU2In0.eyJzdWIiOiI1NDA4NjRlOC02MDAxLTcwMDUtZWIyOS1kOGQyMjU3ZGI1ZWUiLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiaXNzIjoiaHR0cHM6XC9cL2NvZ25pdG8taWRwLnVzLWVhc3QtMS5hbWF6b25hd3MuY29tXC91cy1lYXN0LTFfYkR6SG5QNWhNIiwiY3VzdG9tOmlkIjoiMSIsImNvZ25pdG86dXNlcm5hbWUiOiI1NDA4NjRlOC02MDAxLTcwMDUtZWIyOS1kOGQyMjU3ZGI1ZWUiLCJnaXZlbl9uYW1lIjoiTm9tZSBTb2JyZW5vbWUiLCJvcmlnaW5fanRpIjoiZDM4YmRlZDktMzhmYS00OTM3LTgxY2EtN2FiNzFiZDBjNGJlIiwiYXVkIjoiMjk5NWs1Z3IybmNzM21ibzhiMjlyOWc0ZDAiLCJldmVudF9pZCI6ImM0Mzc0NjM4LWFhNWItNDQ4NC05Nzc2LTVlOTU2MzBmMjQ2NyIsInRva2VuX3VzZSI6ImlkIiwiYXV0aF90aW1lIjoxNzM4NzE2MTUyLCJleHAiOjE3Mzg3MTk3NTIsImlhdCI6MTczODcxNjE1MiwianRpIjoiYjczZGE4MDMtMjg2NC00ZDkzLTg5ODEtMWVmZDA0YjFlY2FjIiwiZW1haWwiOiJyb2RyaWdvQHBlcmlzYXR0by5jb20ifQ.ThTF3NrN6cPi04Tdmmopbg-aKEW8HbFlmOUfAp3B3fFo-YIo6DGi1Mkd3-3tho-eXiRBh4XSjbN0ul7jqClJaYnjSkykhsAbgPSsSNyDFiWD7Y5IXyVuBHtHRjKFd-uLfemPXyTXo4ye2kT06gQeAgr1QyVf1BCW0MPhhWuw8U8X9FPxHnEXgPZp2d6R1TWxUubzn2OWGrb4CJ1raZjP-blxXdHq2aCVE_Nh-ylzS_elEbd9R_G57uzHdZf8n5fCSJhMtsAV6KM0L2FWAJ-C22XHl7jManXHOgxvN4C2URZDsxOILmR68fb1KNlCh8KSzBfOXE-39zvE6HL-y2uYrQ";
	
	@Mock
	private CreateRequestUseCase createRequestUseCase;
	
	@Mock
	private UpdateRequestUseCase updateRequestUseCase;
	
	@Mock
	private GetRequestUseCase getRequestUseCase;
	
	@Mock
	private Properties requestProperties;
	
	AutoCloseable openMocks;
	
	@BeforeEach
	void setUp() {
		openMocks = MockitoAnnotations.openMocks(this);
		RequestManagerRestController requestManagerRestController = new RequestManagerRestController(createRequestUseCase, requestProperties, updateRequestUseCase, getRequestUseCase);
		
		mockMvc = MockMvcBuilders.standaloneSetup(requestManagerRestController)
				.addFilter((request, response, chain) -> {
					response.setCharacterEncoding("UTF-8");
					chain.doFilter(request, response);
				}, "/*")
				.build();
	}
	
	@AfterEach
	void teadDown() throws Exception {
		openMocks.close();
	}
	
	@Nested
	class CreateRequest {
		
		@Test
		void givenValidData_CreateRequest() throws Exception {
			String owner = "me";
			Integer interval = 10;
			String videoFileName = "JohnCenaChairFight.mpeg";
			
			Request request = new Request(owner, interval, videoFileName);
			request.setStatus(RequestStatus.PENDING_UPLOAD);
			
			when(createRequestUseCase.createRequest(any(String.class), any(Integer.class), any(String.class)))
			.thenReturn(request);
			
			CreateRequestRequestDTO createRequestRequestDTO = new CreateRequestRequestDTO();
			
			createRequestRequestDTO.setInterval(10);
			createRequestRequestDTO.setVideoFileName("JohnCenaChairFight.mpeg");
			
			mockMvc.perform(post("/users/{userId}/requests", owner)
					.header("Authorization", token)
					.contentType(MediaType.APPLICATION_JSON)
					.content(asJsonString(createRequestRequestDTO)))
			.andExpect(status().isCreated());		
			
			verify(createRequestUseCase, times(1)).createRequest(any(String.class), any(Integer.class), any(String.class));
		}
	}
	
	@Nested
	class UpdateRequest {
		
		@Test
		void givenValidStatus_thenUpdateRequest() throws Exception {
			String owner = "me";
			Integer interval = 10;
			String videoFileName = "JohnCenaChairFight.mpeg";
			
			Request request = new Request(owner, interval, videoFileName);
			request.setStatus(RequestStatus.PENDING_PROCESS);
			
			when(updateRequestUseCase.updateRequest(any(String.class), any(String.class), any(RequestStatus.class)))
			.thenReturn(request);
			
			UpdateRequestRequestDTO updateRequestRequestDTO = new UpdateRequestRequestDTO();
			updateRequestRequestDTO.setStatus("PENDING_PROCESS");
			updateRequestRequestDTO.setRemarks("No more remarks");
			
			mockMvc.perform(patch("/users/{userId}/requests/{requestId}", owner, UUID.randomUUID().toString())
					.contentType(MediaType.APPLICATION_JSON)
					.content(asJsonString(updateRequestRequestDTO)))
			.andExpect(status().isOk());
			
			verify(updateRequestUseCase, times(1)).updateRequest(any(String.class), any(String.class), any(RequestStatus.class));
		}
	}
	
	@Nested
	class GetRequest {
		
		@Test
		void givenValidId_thenRetrieveRequest() throws Exception {
			String owner = "me";
			Integer interval = 10;
			String videoFileName = "JohnCenaChairFight.mpeg";
			
			Request request = new Request(owner, interval, videoFileName);
			request.setStatus(RequestStatus.PENDING_PROCESS);
			
			when(getRequestUseCase.getRequestById(any(String.class)))
			.thenReturn(request);
			
			mockMvc.perform(get("/users/{userId}/requests/{requestId}", owner, UUID.randomUUID().toString())
					.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
			
			verify(getRequestUseCase, times(1)).getRequestById(any(String.class));
		}
		
		@Test
		void listRequestData() throws Exception {
		
			when(getRequestUseCase.findAllRequests(any(Integer.class), any(Integer.class), any(String.class)))
			.thenAnswer(i -> {
				Set<Request> result = new LinkedHashSet<Request>();
				Request requestData1 = new Request("me", 10, "JohnCenaChairFight.mpeg");
				requestData1.setStatus(RequestStatus.PENDING_UPLOAD);
				Request requestData2 = new Request("me", 10, "JohnCenaChairFight.mpeg");
				requestData2.setStatus(RequestStatus.COMPLETED);
				result.add(requestData1);
				result.add(requestData2);
				return result;
			});
			
			mockMvc.perform(get("/users/{userId}/requests", "me")
					.contentType(MediaType.APPLICATION_JSON)
					.param("_page", "1")
					.param("_size", "50"))
			.andExpect(status().isOk());
			
			verify(getRequestUseCase, times(1)).findAllRequests(any(Integer.class), any(Integer.class), any(String.class));
		}
	}
	
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
