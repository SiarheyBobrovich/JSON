package org.it_academy.MK_JD2_90_22.json2.group.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.it_academy.MK_JD2_90_22.json2.group.dto.NewGroup;
import org.it_academy.MK_JD2_90_22.json2.group.dto.UpdatedGroup;
import org.it_academy.MK_JD2_90_22.json2.group.dto.GroupId;
import org.it_academy.MK_JD2_90_22.json2.group.entity.Group;
import org.it_academy.MK_JD2_90_22.json2.group.exceptions.GroupServiceException;
import org.it_academy.MK_JD2_90_22.json2.group.service.GroupService;
import org.it_academy.MK_JD2_90_22.json2.group.service.api.ICRUDGroupService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;

@WebServlet(name = "Group", urlPatterns = "/group1")
public class GroupServlet extends HttpServlet {

    private static final ICRUDGroupService service = GroupService.getInstance();
    private ObjectMapper mapper;

    public GroupServlet() {
        this.mapper = new ObjectMapper()
                .setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setEncodingType(req, resp);

        long id = 0;
        boolean isId = true;

        String parameter = req.getParameter("id");
        PrintWriter writer = resp.getWriter();

        try {
            id = Long.parseLong(parameter);

        }catch (NumberFormatException e) {
            if (parameter != null) {
                resp.setStatus(400);
                return;
            }

            isId = false;
        }

        if (isId) {
            try {
                Group group = service.get(id);

                resp.setContentType("application/json");
                writer.write(mapper.writeValueAsString(group));

            }catch (GroupServiceException e) {
                resp.setStatus(e.getStatus());
                writer.write(e.getMessage());
            }catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

        }else {
            try {

                for (Group group : service.getAll()) {
                    resp.setContentType("application/json");
                    writer.write(mapper.writeValueAsString(group));
                }

            } catch (GroupServiceException  e) {
                log(e.getMessage(), e.getCause());
                resp.setStatus(e.getStatus());
                writer.write(e.getMessage());
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setEncodingType(req, resp);

        NewGroup newGroup;

        PrintWriter writer = resp.getWriter();

        try {
            newGroup = mapper.readValue(req.getInputStream(), NewGroup.class);
        }catch (JsonProcessingException e) {
            resp.setStatus(415);
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
            throw new RemoteException(e.getMessage(), e.getCause());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setEncodingType(req, resp);

        PrintWriter writer = resp.getWriter();

        UpdatedGroup group;

        try {
            group = mapper.readValue(req.getInputStream(), UpdatedGroup.class);
        }catch (JsonProcessingException e) {
            resp.setStatus(415);
            return;
        }

        try {
            service.update(group);
            resp.setStatus(204);

        }catch (GroupServiceException e) {
            resp.setStatus(e.getStatus());
            writer.write(e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setEncodingType(req, resp);

        GroupId dto;
        PrintWriter writer = resp.getWriter();

        try {
             dto = mapper.readValue(req.getInputStream(), GroupId.class);
        }catch (JsonProcessingException e) {
            resp.setStatus(415);
            return;
        }

        try {
            service.delete(dto);
            resp.setStatus(204);

        }catch (GroupServiceException e) {
            resp.setStatus(e.getStatus());
            writer.write(e.getMessage());
        }
    }

    private void setEncodingType(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");
    }
}
