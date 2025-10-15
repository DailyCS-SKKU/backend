package cse.skku.edu.dailycs.service.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cse.skku.edu.dailycs.dto.auth.GoogleOAuthResponseDto;
import cse.skku.edu.dailycs.dto.auth.LoginUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class GoogleLoginService {

    @Value("${GOOGLE_CLIENT_ID}")
    private String clientId;
    @Value("${GOOGLE_CLIENT_SECRET}")
    private String clientSecret;
    @Value("${GOOGLE_REDIRECT_URI}")
    private String redirectUri;
    @Value("${GOOGLE_AUTH_TOKEN_URI}")
    private String tokenUri;
    @Value("${GOOGLE_AUTH_USERINFO_URI}")
    private String userInfoUri;


    public LoginUserDto getUserInfoByAccessToken(String accessToken) throws JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();

        userInfoUri = "https://openidconnect.googleapis.com/v1/userinfo";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                userInfoUri,
                HttpMethod.GET,
                entity,
                String.class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(response.getBody());

            // 필요한 필드만 추출
            String givenName = node.path("given_name").asText();
            String picture = node.path("picture").asText();
            String email = node.path("email").asText();

            return LoginUserDto.builder()
                    .name(givenName)
                    .imageUrl(picture)
                    .email(email)
                    .provider("google")
                    .build();
        } else {
            throw new RuntimeException("Failed to fetch Google userinfo: " + response.getStatusCode());
        }
    }

    public String requestAccessToken(String code) {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> params = new HashMap<>();
        params.put("code", code);
        params.put("client_id", clientId);
        params.put("client_secret", clientSecret);
        params.put("redirect_uri", redirectUri);
        params.put("grant_type", "authorization_code");

        ResponseEntity<GoogleOAuthResponseDto> responseEntity = restTemplate.postForEntity(tokenUri, params, GoogleOAuthResponseDto.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            GoogleOAuthResponseDto responseDto = responseEntity.getBody();

            if(responseDto == null) {
                throw new RuntimeException("Response body is null");
            }

            return responseDto.getAccess_token();
        } else {
            throw new RuntimeException("Failed to get access token from Google");
        }
    }
}
