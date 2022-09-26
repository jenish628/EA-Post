package edu.miu.post.clients;

import edu.miu.post.dto.Verification;
import edu.miu.post.dto.VerifyRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-verification", url = "${user.services.url}")
public interface UserVerificationRequest {
    @PostMapping
    Verification verifyUserDetail(@RequestBody VerifyRequest verificationRequest);
}
