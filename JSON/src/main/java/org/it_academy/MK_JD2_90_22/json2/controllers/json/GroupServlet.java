package org.it_academy.MK_JD2_90_22.json2.controllers.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.it_academy.MK_JD2_90_22.json2.controllers.utils.ControllerUtils;
import org.it_academy.MK_JD2_90_22.json2.dto.NewGroup;
import org.it_academy.MK_JD2_90_22.json2.dto.UpdatedGroup;
import org.it_academy.MK_JD2_90_22.json2.dto.GroupId;
import org.it_academy.MK_JD2_90_22.json2.dao.entity.Group;
import org.it_academy.MK_JD2_90_22.json2.exceptions.GroupServiceException;
import org.it_academy.MK_JD2_90_22.json2.exceptions.api.CoursesIllegalArgumentException;
import org.it_academy.MK_JD2_90_22.json2.service.GroupService;
import org.it_academy.MK_JD2_90_22.json2.service.api.ICRUDGroupService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Group", urlPatterns = "/group1")
public class GroupServlet extends HttpServlet {

    private static final ICRUDGroupService service = GroupService.getInstance();
    private final ObjectMapper mapper;

    public GroupServlet() {
        this.mapper = new ObjectMapper()
                .setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ControllerUtils.setEncodingType(req, resp);

        long id;

        String parameterId = req.getParameter("id");
        PrintWriter writer = resp.getWriter();

        if (parameterId != null) {
            try {
                id = Long.parseLong(parameterId);

            }catch (NumberFormatException e) {
                resp.setStatus(400);
                return;
            }

            try {
                Group group = service.get(id);

                resp.setContentType("application/json");
                writer.write(mapper.writeValueAsString(group));

            }catch (CoursesIllegalArgumentException e) {
                resp.setStatus(e.getStatus());
                writer.write(e.getMessage());

            }catch (JsonProcessingException e) {
                resp.setStatus(500);
                log(e.getMessage(), e);
            }

        }else {
            try {

                for (Group group : service.getAll()) {
                    resp.setContentType("application/json");
                    writer.write(mapper.writeValueAsString(group));
                }

            } catch (CoursesIllegalArgumentException e) {
                log(e.getMessage(), e.getCause());
                resp.setStatus(e.getStatus());
                writer.write(e.getMessage());

            } catch (JsonProcessingException e) {
                resp.setStatus(500);
                log(e.getMessage(), e);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ControllerUtils.setEncodingType(req, resp);

        NewGroup newGroup;

        PrintWriter writer = resp.getWriter();

        try {
            newGroup = mapper.readValue(req.getInputStream(), NewGroup.class);

        }catch (JsonProcessingException e) {
            resp.setStatus(415);
            writer.write("Unsupported media type");
            return;
        }

        try {
            resp.setContentType("application/json");
            writer.write(mapper.writeValueAsString(
                    service.save(newGroup))
            );

            resp.setStatus(201);

        }catch (GroupServiceException e) {
            resp.setStatus(e.getStatus());
            writer.write(e.getMessage());

        }catch (JsonProcessingException e) {
            resp.setStatus(500);
            log(e.getMessage(), e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ControllerUtils.setEncodingType(req, resp);

        PrintWriter writer = resp.getWriter();

        UpdatedGroup group;

        try {
            group = mapper.readValue(req.getInputStream(), UpdatedGroup.class);

        }catch (JsonProcessingException e) {
            resp.setStatus(415);
            writer.write("Unsupported media type");
            return;
        }

        try {
            service.update(group);
            resp.setStatus(204);

        }catch (CoursesIllegalArgumentException e) {
            resp.setStatus(e.getStatus());
            writer.write(e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ControllerUtils.setEncodingType(req, resp);

        GroupId dto;
        PrintWriter writer = resp.getWriter();

        try {
             dto = mapper.readValue(req.getInputStream(), GroupId.class);

        }catch (JsonProcessingException e) {
            resp.setStatus(415);
            writer.write("Unsupported media type");
            return;
        }

        try {
            service.delete(dto);
            resp.setStatus(204);

        }catch (CoursesIllegalArgumentException e) {
            resp.setStatus(e.getStatus());
            writer.write(e.getMessage());
        }
    }
}
