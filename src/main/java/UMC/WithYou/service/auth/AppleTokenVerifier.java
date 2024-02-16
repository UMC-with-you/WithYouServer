package UMC.WithYou.service.auth;

import UMC.WithYou.dto.auth.ApplePublicKeyResponse;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.security.interfaces.RSAPublicKey;
import java.security.KeyFactory;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.Map;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Service
@RequiredArgsConstructor
public class AppleTokenVerifier {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public DecodedJWT verifyToken(String token) throws Exception {
        ApplePublicKeyResponse response = fetchApplePublicKeys();
        ApplePublicKeyResponse.AppleKey matchingKey = findMatchingKey(token, response);
        RSAPublicKey publicKey = generatePublicKey(matchingKey);
        return verifyJwtToken(token, publicKey);
    }

    private ApplePublicKeyResponse fetchApplePublicKeys() throws Exception {
        URL url = new URL("https://appleid.apple.com/auth/keys");
        return restTemplate.getForObject(url.toURI(), ApplePublicKeyResponse.class);
    }

    private ApplePublicKeyResponse.AppleKey findMatchingKey(String token, ApplePublicKeyResponse response) throws Exception {
        String header = new String(Base64.getUrlDecoder().decode(token.split("\\.")[0]));
        Map<String, String> headerMap = objectMapper.readValue(header, Map.class);
        String kid = headerMap.get("kid");

        return response.getKeys().stream()
                .filter(key -> kid.equals(key.getKid()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Matching key not found in Apple's public keys"));
    }

    private RSAPublicKey generatePublicKey(ApplePublicKeyResponse.AppleKey key) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] nBytes = Base64.getUrlDecoder().decode(key.getN());
        byte[] eBytes = Base64.getUrlDecoder().decode(key.getE());
        BigInteger modulus = new BigInteger(1, nBytes);
        BigInteger exponent = new BigInteger(1, eBytes);

        RSAPublicKeySpec spec = new RSAPublicKeySpec(modulus, exponent);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        return (RSAPublicKey) factory.generatePublic(spec);
    }
    private DecodedJWT verifyJwtToken(String token, RSAPublicKey publicKey) {
        Algorithm algorithm = Algorithm.RSA256(publicKey, null);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("https://appleid.apple.com")
                .build();
        return verifier.verify(token);
    }

}