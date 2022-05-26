package org.it_academy.MK_JD2_90_22.json2.controllers.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.it_academy.MK_JD2_90_22.json2.dao.entity.StudentsInGroup;
import org.it_academy.MK_JD2_90_22.json2.dto.group_student.GroupStudentsList;
import org.it_academy.MK_JD2_90_22.json2.exceptions.service.GroupSaveDeleteStudentNullException;
import org.it_academy.MK_JD2_90_22.json2.exceptions.service.GroupSaveDeleteStudentsIllegalIDException;
import org.it_academy.MK_JD2_90_22.json2.services.api.ICDService;
import org.it_academy.MK_JD2_90_22.json2.exceptions.dao.GroupSaveDeleteStudentsDaoException;
import org.it_academy.MK_JD2_90_22.json2.exceptions.service.GroupSaveDeleteStudentsIllegalArgumentException;
import org.it_academy.MK_JD2_90_22.json2.exceptions.service.StudentsIllegalIdException;
import org.it_academy.MK_JD2_90_22.json2.services.GroupSaveDeleteStudentsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "GroupSaveDeleteStudentsServlet", urlPatterns = "/group/student")
public class GroupSaveDeleteStudentsServlet extends HttpServlet {

    private final ObjectMapper mapper;
    private static final ICDService service = GroupSaveDeleteStudentsService.getInstance();
    private static final String ERROR = "Не верно переданы данные";

    public GroupSaveDeleteStudentsServlet() {
        this.mapper = new ObjectMapper()
                .setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");

        PrintWriter writer = resp.getWriter();
        long id;

        try {
            id = mapper.readValue(req.getInputStream(), long.class);
            StudentsInGroup studentsInGroup = service.get(id);

            resp.setContentType("applecation/json");
            writer.write(mapper.writeValueAsString(studentsInGroup));

        }catch (JsonProcessingException e) {
            List<StudentsInGroup> all = service.getAll();

            resp.setContentType("applecation/json");
            writer.write(mapper.writeValueAsString(all));

        }catch (GroupSaveDeleteStudentNullException |
                GroupSaveDeleteStudentsIllegalArgumentException |
                GroupSaveDeleteStudentsIllegalIDException e) {
            writer.write(e.getMessage());

        }catch (GroupSaveDeleteStudentsDaoException e) {
            log(e.getMessage(), e.getCause());
            writer.write("В данный момент база данных не доступна");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPutDelete(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPutDelete(req, resp);
    }

    private void doPutDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");

        GroupStudentsList gList;

        PrintWriter writer = resp.getWriter();

        try {
            gList = mapper.readValue(req.getInputStream(), GroupStudentsList.class);

        }catch (JsonProcessingException e) {
            writer.write(ERROR);
            return;
        }

        try {
            switch (req.getMethod()) {
                case "PUT" :
                    service.save(gList);

                    writer.write("Данные сохранены успешно!");
                    return;

                case "DELETE" :
                    service.delete(gList);

                    writer.write("Данные удалены успешно!");
                }

        }catch (GroupSaveDeleteStudentNullException |
                GroupSaveDeleteStudentsIllegalArgumentException |
                GroupSaveDeleteStudentsIllegalIDException |
                StudentsIllegalIdException e) {
            writer.write(e.getMessage());

        }catch (GroupSaveDeleteStudentsDaoException e) {
            this.log(e.getMessage(), e.getCause());
            writer.write("База данных не доступна, попробуйте позже");
        }
    }
}
