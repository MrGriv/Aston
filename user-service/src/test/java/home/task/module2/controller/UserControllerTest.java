package home.task.module2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import home.task.module2.dto.user.UserDto;
import home.task.module2.dto.user.UserNew;
import home.task.module2.dto.user.UserUpdate;
import home.task.module2.service.UserService;
import org.junit.jupiter.api.BeforeEach;
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
import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(controllers = UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private UserService userService;

    private UserDto userDto;

    @BeforeEach
    void setUp() {
        userDto = new UserDto(1L, "Иван", "k@mail.ru", 30, LocalDate.now());
    }

    @Test
    @DisplayName("Должен вернуться DTO пользователь при добавлении")
    void whenAddUserNewShouldReturnUserDto() throws Exception {
        UserNew userNew = new UserNew("Иван", "k@mail.ru", 30);

        when(userService.add(any())).thenReturn(userDto);
        String result = mvc.perform(post("/users")
                        .content(mapper.writeValueAsString(userNew))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        assertEquals(mapper.writeValueAsString(userDto), result);
    }

    @Test
    @DisplayName("Должен вернуться DTO пользователь при обновлении")
    void whenUpdateUserUpdateShouldReturnUserDto() throws Exception {
        UserUpdate userUpdate = new UserUpdate("Иван", "k@mail.ru", 30);

        when(userService.update(anyLong(), any())).thenReturn(userDto);
        String result = mvc.perform(patch("/users/1")
                        .content(mapper.writeValueAsString(userUpdate))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        assertEquals(mapper.writeValueAsString(userDto), result);
    }

    @Test
    @DisplayName("Должен вернуться DTO пользователь при запросе пользователя по id")
    void whenGetByIdShouldReturnUserDto() throws Exception {
        when(userService.get(anyLong())).thenReturn(userDto);
        String result = mvc.perform(get("/users/1")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        assertEquals(mapper.writeValueAsString(userDto), result);
    }

    @Test
    @DisplayName("Должен вернуться список DTO пользователей при запросе всех пользователей")
    void whenGetAllShouldReturnListUserDto() throws Exception {
        when(userService.getAll()).thenReturn(List.of(userDto));
        mvc.perform(get("/users")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(userDto.getId()), Long.class))
                .andExpect(jsonPath("$[0].name", is(userDto.getName())))
                .andExpect(jsonPath("$[0].email", is(userDto.getEmail())))
                .andExpect(jsonPath("$[0].age", is(userDto.getAge())))
                .andExpect(jsonPath("$[0].createdAt", is(userDto.getCreatedAt().toString())));
    }

    @Test
    @DisplayName("Должен удалиться пользователь")
    void whenDeleteByIdShouldReturnStatusOk() throws Exception {
        mvc.perform(delete("/users/1")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }
}