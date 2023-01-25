package africa.semicolon.notification.whatsapp;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class WhatsappRequest {

    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String message;
}
