
package com.syntech.sustentabilidadesocial.sustentabilidade_social;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.syntech.sustentabilidadesocial.sustentabilidade_social.requests.UpdateEmailRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.syntech.sustentabilidadesocial.sustentabilidade_social.models.User;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.repository.UserRepository;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.requests.CreateUserRequest;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.requests.UpdateNameRequest;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.services.UserServices;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.utils.UserUtils;

public class UserServicesTest {

    @Mock
    private UserRepository userRepository; // Cria um mock do repositório...

    @InjectMocks
    private UserServices userServices; // Injeta os mocks nessa dependência...

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Antes de cada teste inicializa os mocks...
    }

    @Test
    void createUserTest() throws Exception {
        
        CreateUserRequest req = Mockito.mock(CreateUserRequest.class); // Cria um mock do record...
        when(req.email()).thenReturn("teste@email.com"); // quando chamar o atributo email retorna esse email simulado...
        when(req.name()).thenReturn("João da Silva"); // quando chamar o atributo nome retorna esse nome simulado...
        when(req.password()).thenReturn("senha123"); // quando chamar o atributo senha retorna essa senha simulada...
        when(req.pictureProfile()).thenReturn(""); // quando chamar o atributo profilepicture retorna esse valor simulado...

        // Simula a execução das consultas passa any(qualquer valor) valor como parametro e simula o retorno vazio do optional...
        when(userRepository.findByEmail(any())).thenReturn(Optional.empty());
        when(userRepository.findBySlug(any())).thenReturn(Optional.empty());

        // Cria um mock estatico para testar metodos de validação...
        try(MockedStatic<UserUtils> userUtilsMock = Mockito.mockStatic(UserUtils.class)) {
            // Simula validação de domínio
            userUtilsMock.when(() -> UserUtils.isDomainValid(any())).thenReturn(true);
            userUtilsMock.when(() -> UserUtils.generateSlug(any())).thenReturn("joao-da-silva-abc123");

        } catch (Exception ex) {
            throw new Exception("Não foi possível realizar as validações");
        }

        // Simula o save
        User user = User.builder()
                .name("João da Silva")
                .email("teste@email.com")
                .slug("joao-da-silva-abc123")
                .build();
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        Map<String, Object> result = userServices.createUser(req);

        // Assert
        assertNotNull(result);
        assertEquals("João da Silva", result.get("name"));
        // profilePictureUrl deve ser null ou não presente
    }

    @Test
    void createUserErrorTest() {
        CreateUserRequest req = Mockito.mock(CreateUserRequest.class);
        when(req.email()).thenReturn("teste@email.com");
        when(req.name()).thenReturn("João da Silva");
        when(req.password()).thenReturn("senha123");
        when(req.pictureProfile()).thenReturn("");

        // Simula que já existe usuário com esse e-mail
        when(userRepository.findByEmail(any())).thenReturn(Optional.of(new User()));

        Exception ex = assertThrows(Exception.class, () -> userServices.createUser(req));
        assertEquals("Esse email já está cadastrado...", ex.getMessage());
    }

    @Test
    void updateNameTest() throws Exception {

        String nameTest = "Leandro Silva";

        String userIdTest = UUID.randomUUID().toString();

        UpdateNameRequest req = Mockito.mock(UpdateNameRequest.class);
        when(req.name()).thenReturn(nameTest);

        User userTest = User.builder()
        .name("Leandro Souza")
        .email("teste@email.com")
        .slug("leandro-souza-abc123")
        .build();

        when(userRepository.findById(any())).thenReturn(Optional.of(userTest));

        userTest.setName(nameTest);

        when(userRepository.save(any(User.class))).thenReturn(userTest);

        // Act
        Map<String, Object> result = userServices.updateName(userIdTest, req);

        // Assert
        assertNotNull(result);
        assertEquals(nameTest, result.get("name"));
        // profilePictureUrl deve ser null ou não presente...
    }

    @Test
    void updateEmailTest() throws Exception {

        String emailTest = "novoemail@gmail.com";

        String userIdTest = UUID.randomUUID().toString();

        UpdateEmailRequest req = Mockito.mock(UpdateEmailRequest.class);
        when(req.email()).thenReturn(emailTest);

        User userTest = User.builder()
                .name("Leandro Souza")
                .email("teste@email.com")
                .slug("leandro-souza-abc123")
                .build();

        when(userRepository.findById(any())).thenReturn(Optional.of(userTest));

        when(userRepository.findByEmail(emailTest)).thenReturn(Optional.empty());

        try (MockedStatic<UserUtils> userUtilsMock = Mockito.mockStatic(UserUtils.class)) {
            userUtilsMock.when(() -> UserUtils.isDomainValid(any())).thenReturn(true);
        } catch (Exception ex) {
            throw new Exception("Não foi possível realizar a validação");
        }

        userTest.setEmail(emailTest);

        when(userRepository.save(any(User.class))).thenReturn(userTest);

        Map<String, Object> result = userServices.updateEmail(userIdTest, req);

        assertNotNull(result);
        assertEquals(emailTest, result.get("email"));
    }


}
