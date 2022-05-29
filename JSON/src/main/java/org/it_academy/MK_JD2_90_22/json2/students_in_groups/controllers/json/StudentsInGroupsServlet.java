package org.it_academy.MK_JD2_90_22.json2.students_in_groups.controllers.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.it_academy.MK_JD2_90_22.json2.group.dto.GroupId;
import org.it_academy.MK_JD2_90_22.json2.student.dto.StudentId;
import org.it_academy.MK_JD2_90_22.json2.student.exceptions.studentsInGroups.StudentsInGroupsServiceException;
import org.it_academy.MK_JD2_90_22.json2.students_in_groups.dao.api.ICrossServiceController;
import org.it_academy.MK_JD2_90_22.json2.students_in_groups.dto.GroupStudentId;
import org.it_academy.MK_JD2_90_22.json2.students_in_groups.services.StudentsInGroupsService;
import org.it_academy.MK_JD2_90_22.json2.utils.ControllerUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class StudentsInGroupsServlet extends HttpServlet {

    private final ICrossServiceController service = StudentsInGroupsService.getInstance();
    private final ObjectMapper mapper;

    public StudentsInGroupsServlet() {
        mapper = new ObjectMapper()
                .setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ControllerUtils.setEncodingType(req, resp);
        PrintWriter writer = resp.getWriter();

        String studentId = req.getParameter("student");
        String groupId = req.getParameter("group");

        String result;

        try {

            if (studentId != null) {

                result = mapper.writeValueAsString(
                        service.getS(
                                Long.parseLong(studentId))
                );

                resp.setContentType("application/json");
                writer.write(result);

            }else if (groupId != null) {

                    result = mapper.writeValueAsString(
                            service.getG(
                                    Long.parseLong(groupId))
                    );

                    resp.setContentType("application/json");
                    writer.write(result);

            }else {
                result = mapper.writeValueAsString(
                        service.getAll()
                );

                resp.setContentType("application/json");
                writer.write(result);
            }

        }catch (NumberFormatException e) {
            resp.setStatus(400);
            writer.write(e.getMessage());

        }catch (JsonProcessingException e) {
            log(e.getMessage(), e);

        }catch (StudentsInGroupsServiceException e) {
            resp.setStatus(e.getStatus());

            if (e.getStatus() == 500) {
                log(e.getMessage(), e);
            }

            writer.write(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ControllerUtils.setEncodingType(req, resp);
        PrintWriter writer = resp.getWriter();

        GroupStudentId groupStudentId;

        try {
            groupStudentId = mapper.readValue(req.getInputStream(), GroupStudentId.class);

        }catch (JsonProcessingException e) {
            resp.setStatus(415);
            writer.write("Unsupported media type");
            return;
        }

        try {
            service.save(new GroupId(
                    groupStudentId.getGroupId()),
                    new StudentId(groupStudentId.getStudentId())
            );

            resp.setStatus(201);
            writer.write("Created");

        }catch (StudentsInGroupsServiceException e) {
            resp.setStatus(e.getStatus());

            if (e.getStatus() == 500) {
                log(e.getMessage(), e);
            }

            writer.write(e.getMessage());
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ControllerUtils.setEncodingType(req, resp);

        PrintWriter writer = resp.getWriter();
        ServletInputStream inputStream = req.getInputStream();

        GroupId groupId;
        StudentId studentId;

        try {
            groupId = mapper.readValue(inputStream, GroupId.class);
            service.deleteG(groupId);

        }catch (JsonProcessingException e) {
            studentId = mapper.readValue(inputStream, StudentId.class);
            service.deleteS(studentId);

        }catch (StudentsInGroupsServiceException e) {
            resp.setStatus(e.getStatus());
            writer.write(e.getMessage());

            if (e.getStatus() == 500) {
                log(e.getMessage(), e);
            }

        }catch (RuntimeException e) {
            resp.setStatus(415);
            writer.write("Unsupported media type");
        }
    }
}
