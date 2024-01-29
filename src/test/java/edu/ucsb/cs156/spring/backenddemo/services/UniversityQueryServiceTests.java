package edu.ucsb.cs156.spring.backenddemo.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(UniversityQueryService.class)
public class UniversityQueryServiceTest {

    @Autowired
    private MockRestServiceServer mockRestServiceServer;

    @Autowired
    private UniversityQueryService universityQueryService;

    @Test
    public void testGetJSON() {
        // Given
        String university = "UCSB";
        String expectedURL = UniversityQueryService.ENDPOINT.replace("{name}", university);
        String fakeJsonResult = "{ \"fake\" : \"result\" }";

        // When
        this.mockRestServiceServer.expect(requestTo(expectedURL))
                .andExpect(header("Accept", MediaType.APPLICATION_JSON.toString()))
                .andExpect(header("Content-Type", MediaType.APPLICATION_JSON.toString()))
                .andRespond(withSuccess(fakeJsonResult, MediaType.APPLICATION_JSON));

        String actualResult = universityQueryService.getJSON(university);

        // Then
        assertEquals(fakeJsonResult, actualResult);
    }
}
