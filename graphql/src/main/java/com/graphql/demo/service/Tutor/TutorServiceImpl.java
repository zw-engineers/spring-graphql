package com.graphql.demo.service.Tutor;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.function.Predicate;

import static org.springframework.http.HttpEntity.EMPTY;
import static org.springframework.http.HttpMethod.GET;

@Service
public class TutorServiceImpl implements TutorService {

    private static final String URL = "http://localhost:8081/api/university/tutor";
    private final RestTemplate restTemplate;

    public TutorServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public TutorDto getTutorByDegree(String degree) {
        return getAllTutors()
                .stream()
                .filter(isTutorPresent(degree))
                .map(this::toTutorDto)
                .findFirst()
                .orElse(new TutorDto());
    }

    private Predicate<Tutor> isTutorPresent(String degree) {
        return tutor -> tutor.getDegree().getDegree().contains(degree);
    }

    private TutorDto toTutorDto(Tutor tutor) {
        return new TutorDto(tutor.getName(), tutor.getSurname(), tutor.getDegree());
    }

    private List<Tutor> getAllTutors() {
        ParameterizedTypeReference<List<Tutor>> tutorTypeRef = new ParameterizedTypeReference<>() {};

        ResponseEntity<List<Tutor>> responseEntity = this.restTemplate.exchange(URL, GET, EMPTY, tutorTypeRef);

        if (responseEntity.getStatusCode().value() == 200) {
            return responseEntity.getBody();
        } else {
            throw new RuntimeException("Got an unexpected response from University Service retrieving Tutor");
        }
    }
}
