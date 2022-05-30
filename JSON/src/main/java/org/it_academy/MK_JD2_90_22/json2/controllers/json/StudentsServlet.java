package org.it_academy.MK_JD2_90_22.json2.controllers.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.it_academy.MK_JD2_90_22.json2.controllers.utils.ControllerUtils;
import org.it_academy.MK_JD2_90_22.json2.dao.entity.Student;
import org.it_academy.MK_JD2_90_22.json2.dto.NewStudent;
import org.it_academy.MK_JD2_90_22.json2.dto.StudentId;
import org.it_academy.MK_JD2_90_22.json2.dto.UpdatedStudent;
import org.it_academy.MK_JD2_90_22.json2.exceptions.StudentsServiceException;
import org.it_academy.MK_JD2_90_22.json2.exceptions.api.CoursesIllegalArgumentException;
import org.it_academy.MK_JD2_90_22.json2.service.StudentsService;
import org.it_academy.MK_JD2_90_22.json2.service.api.ICRUDStudentsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

@WebServlet(name = "StudentsServlet", urlPatterns = "/students1")

public class StudentsServlet extends HttpServlet {

    private static final ICRUDStudentsService service = StudentsService.getInstance();
    private final ObjectMapper mapper;

    public StudentsServlet() {
        mapper = new ObjectMapper()
                .setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ControllerUtils.setEncodingType(req, resp);

        PrintWriter writer = resp.getWriter();
        String parameterId = req.getParameter("id");

        if (parameterId != null) {
            try {
                Student student = service.get(Long.parseLong(parameterId));

                resp.setContentType("application/json");
                writer.write(mapper.writeValueAsString(student));
                return;

            }catch (JsonProcessingException e) {
                    log(e.getMessage(), e);
                    resp.setStatus(415);
                    return;

            }catch (NumberFormatException e) {
                resp.setStatus(400);
                writer.write("Incorrect parameter id");
                return;

            } catch (CoursesIllegalArgumentException e) {
                sendError(resp, e);
                return;
            }
        }

        List<Student> students;

        try {
            students = service.getAll();

        }catch (CoursesIllegalArgumentException e) {
            sendError(resp, e);
            return;
        }

        try {
            resp.setContentType("application/json");
            writer.write(mapper.writeValueAsString(students));

        }catch (JsonProcessingException e) {
            log(e.getMessage(), e);
            resp.setStatus(415);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ControllerUtils.setEncodingType(req, resp);

        try {
            NewStudent newStudent = mapper.readValue(req.getInputStream(), NewStudent.class);

            long save = service.save(newStudent);
            resp.setStatus(201);

            PrintWriter writer = resp.getWriter();

            resp.setContentType("application/json");
            writer.write(mapper.writeValueAsString(save));

        }catch (JsonProcessingException e) {
            log(e.getMessage(), e);
            resp.setStatus(415);

        }catch (CoursesIllegalArgumentException e) {
            sendError(resp, e);

        }catch (IOException e) {
            log("Return failed", e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ControllerUtils.setEncodingType(req, resp);

        try {
            UpdatedStudent updatedStudent = mapper.readValue(req.getInputStream(), UpdatedStudent.class);

            service.update(updatedStudent);
            resp.setStatus(204);

        }catch (JsonProcessingException e) {
            log(e.getMessage(), e);
            resp.setStatus(415);

        }catch (CoursesIllegalArgumentException e) {
            sendError(resp, e);

        }catch (IOException e) {
            log("Return failed", e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ControllerUtils.setEncodingType(req, resp);

        try {
            StudentId studentId = mapper.readValue(req.getInputStream(), StudentId.class);

            service.delete(studentId);
            resp.setStatus(204);

        }catch (JsonProcessingException e) {
            log(e.getMessage(), e);
            resp.setStatus(415);

        }catch (StudentsServiceException e) {
            sendError(resp, e);

        }catch (IOException e) {
            log("Return failed", e);
        }
    }

    private void sendError(HttpServletResponse resp, CoursesIllegalArgumentException exception) {
        int status = exception.getStatus();
        resp.setStatus(exception.getStatus());

        try {
            resp.getWriter().write(exception.getMessage());

        }catch (IOException e) {
            log("Return failed", e);
        }

        if (status == 500) {
            log(exception.getMessage(), exception);
        }
    }
}
