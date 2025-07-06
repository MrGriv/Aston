package home.task.module2.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import home.task.module2.controller.UserController;
import home.task.module2.dto.UserNew;
import home.task.module2.dto.UserUpdate;
import home.task.module2.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(controllers = {UserController.class, ErrorHandler.class})
class ErrorHandlerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private UserService userService;

    @Test
    @DisplayName("Должен получиться статус 404, при использовании фейк id")
    void whenUpdateUserWithFakeIdShouldReturnNotFound() throws Exception {
        String invalidIdMessage = "User с ID = 99999999 не найден для обновления";
        UserUpdate userUpdate = new UserUpdate("Иван", "k@mail.ru", 30);
        Long fakeId = 99999999L;

        when(userService.update(fakeId, userUpdate))
                .thenThrow(new NotFoundException(String.format("User с ID = %s не найден для обновления", fakeId)));
        mvc.perform(patch("/users/{fakeId}", fakeId)
                        .content(mapper.writeValueAsString(userUpdate))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.statusCode", is(404)))
                .andExpect(jsonPath("$.errors.error", is(invalidIdMessage)));
    }

    @Test
    @DisplayName("Должен получиться статус 400, при использовании не валидных данных")
    void whenUseInvalidUserShouldThrowValidationException() throws Exception {
        String invalidNameMessage = "Имя не может быть пустым";
        String invalidEmailMessage = "Почта не может быть пустой";
        String invalidAgeMessage = "Возраст не может быть отрицательным числом";
        UserNew userNew = new UserNew(null, null, -1);

        mvc.perform(post("/users")
                        .content(mapper.writeValueAsString(userNew))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.statusCode", is(400)))
                .andExpect(jsonPath("$.errors.name", is(invalidNameMessage)))
                .andExpect(jsonPath("$.errors.email", is(invalidEmailMessage)))
                .andExpect(jsonPath("$.errors.age", is(invalidAgeMessage)));
    }
}