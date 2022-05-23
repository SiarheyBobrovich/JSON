package org.it_academy.MK_JD2_90_22.json.controllers.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.it_academy.MK_JD2_90_22.json.dto.student.StudentDto;
import org.it_academy.MK_JD2_90_22.json.dto.student.StudentId;
import org.it_academy.MK_JD2_90_22.json.exceptions.dao.StudentDaoException;
import org.it_academy.MK_JD2_90_22.json.exceptions.service.StudentNullPointerException;
import org.it_academy.MK_JD2_90_22.json.exceptions.service.StudentsIllegalIdException;
import org.it_academy.MK_JD2_90_22.json.services.StudentService;
import org.it_academy.MK_JD2_90_22.json.services.api.ICRUDStudentsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "StudentServlet", urlPatterns = "/student")
public class StudentServlet extends HttpServlet {

    private final ObjectMapper mapper;
    private static final ICRUDStudentsService service = StudentService.getInstance();


    public StudentServlet() {
        this.mapper = new ObjectMapper()
                .setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE)
                .registerModule(new JavaTimeModule());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");

        String jsonValue;
        try {
            try {
                long id = mapper.readValue(req.getInputStream(), long.class);
                jsonValue = mapper.writeValueAsString(service.get(id));

            } catch (JsonProcessingException e) {
                jsonValue = mapper.writeValueAsString(service.getAll());
            }

        }catch (StudentsIllegalIdException e) {
            resp.getWriter().write(e.getMessage());
            return;

        }catch (StudentDaoException e) {
            this.log(e.getMessage(), e.getCause());
            resp.getWriter().write(e.getMessage());
            return;
        }

        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonValue);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");

        PrintWriter writer = resp.getWriter();

        try {
            StudentDto studentDto = mapper.readValue(req.getInputStream(), StudentDto.class);
            service.update(studentDto);

        }catch (JsonProcessingException | StudentNullPointerException e) {
            writer.write("Не верно переданные данные");
            return;

        }catch (StudentsIllegalIdException e) {
            writer.write(e.getMessage());
            return;

        }catch (StudentDaoException e) {
            this.log(e.getMessage(), e.getCause());
            writer.write(e.getMessage());
            return;
        }

        writer.write("Данные обновлены успешно");

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");

        PrintWriter writer = resp.getWriter();

        try {
            StudentDto studentDto = mapper.readValue(req.getInputStream(), StudentDto.class);
            service.save(studentDto);

        }catch (JsonProcessingException | StudentNullPointerException e) {
            writer.write("Не верно переданные данные");
            return;

        }catch (StudentsIllegalIdException e) {
            writer.write(e.getMessage());
            return;
        }catch (StudentDaoException e) {
            this.log(e.getMessage(), e.getCause());
            writer.write(e.getMessage());
            return;
        }

        writer.write("Данные добавлены успешно");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");

        PrintWriter writer = resp.getWriter();

        try {
            StudentId id = mapper.readValue(req.getInputStream(), StudentId.class);
            service.delete(id);

        }catch (JsonProcessingException e) {
            writer.write("Не верно переданные данные");
            return;

        }catch (StudentsIllegalIdException e) {
            writer.write(e.getMessage());
            return;

        }catch (StudentDaoException e) {
            this.log(e.getMessage(), e.getCause());
            writer.write(e.getMessage());
            return;
        }

        writer.write("Студенты удалёны успешно");
    }
}
