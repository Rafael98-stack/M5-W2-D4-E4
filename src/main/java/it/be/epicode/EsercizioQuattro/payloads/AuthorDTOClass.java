package it.be.epicode.EsercizioQuattro.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthorDTOClass {
    private int id;
    @NotEmpty(message = "Il nome è obbligatorio!")
    @Size(min = 3, max = 20, message = "Il nome deve essere compreso tra 3 e 20 caratteri")
    private String name;
    @NotEmpty(message = "Il cognome è obbligatorio!")
    @Size(min = 3, max = 20, message = "Il cognome deve essere compreso tra 3 e 20 caratteri")
    private String surname;
    @NotEmpty(message = "L'email è obbligatoria!")
    @Email(message = "Non hai inserito un'email valida!")
    private String email;
    @NotEmpty(message = "La pw è obbligatoria!")
    @Size(min = 3, max = 20, message = "La password deve essere compresa tra 3 e 20 caratteri")
    private String password;
}
