package org.it_academy.MK_JD2_90_22.json.controllers.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.it_academy.MK_JD2_90_22.json.dao.entity.Group;
import org.it_academy.MK_JD2_90_22.json.dto.group.Group1_1;
import org.it_academy.MK_JD2_90_22.json.dto.group.GroupUpdate1_1;
import org.it_academy.MK_JD2_90_22.json.dto.group.api.IGroup;
import org.it_academy.MK_JD2_90_22.json.exceptions.dao.GroupDaoException;
import org.it_academy.MK_JD2_90_22.json.exceptions.service.group.GroupIllegalStateException;
import org.it_academy.MK_JD2_90_22.json.services.GroupService1_1;
import org.it_academy.MK_JD2_90_22.json.services.api.ICRUDGroupService1_1;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "GroupServlet1_1", urlPatterns = "/group1.1")
public class GroupServlet1_1 extends HttpServlet {

    private static final ICRUDGroupService1_1 service = GroupService1_1.getInstance();
    private final ObjectMapper mapper;

    public GroupServlet1_1() {
        this.mapper = new ObjectMapper()
                .setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setEncodingType(req, resp);

        PrintWriter writer = resp.getWriter();
        String id = req.getParameter("id");

        List<Group> groups;

        try {
            if (Objects.isNull(id) || !id.matches("\\d++")) {
                groups = service.getAll();

                write(resp, mapper.writeValueAsString(groups));

            } else {
                groups = new ArrayList<>(1);
                groups.add(service.get(Long.parseLong(id)));

                write(resp, mapper.writeValueAsString(groups));
            }

        }catch (JsonProcessingException e) {
            log(e.getMessage(), e.getCause());
            writer.write("Ошибка чтения JSON. Обратитесь в тех.поддержку");

        }catch (GroupDaoException e) {
            this.log(e.getMessage(), e.getCause());
            writer.write("База данных на данный момент не доступна, обратитесь в тех. поддержку");

        }catch (GroupIllegalStateException e) {
            writer.write(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        setEncodingType(req, resp);
//
//        IGroup group;
//
//        PrintWriter writer = resp.getWriter();
//        ServletInputStream inputStream = req.getInputStream();
//
//        try {
//            group = mapper.readValue(inputStream, Group1_1.class);
//
//        }catch (JsonProcessingException e) {
//            writer.write("Не верно переданные данные");
//            return;
//        }
//
//        try {
//            service.save(group);
//
//        }catch (GroupIllegalStateException e) {
//            writer.write(e.getMessage());
//            return;
//
//        }catch (GroupDaoException e) {
//            this.log(e.getMessage(), e.getCause());
//            writer.write(e.getMessage());
//            return;
//        }

        if (doCUD(req, resp)) {
            resp.getWriter().write("Данные обновлены успешно");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        setEncodingType(req, resp);
//
//        IGroup group;
//
//        ServletInputStream inputStream = req.getInputStream();
//        PrintWriter writer = resp.getWriter();
//
//        try {
//            group = mapper.readValue(inputStream, GroupUpdate1_1.class);
//
//        }catch (JsonProcessingException e) {
//            writer.write("Не верно переданы данные");
//            return;
//        }
//
//        try {
//            service.update(group);
//
//        }catch (GroupIllegalStateException e) {
//            writer.write(e.getMessage());
//            return;
//
//        }catch (GroupDaoException e) {
//            this.log(e.getMessage(), e.getCause());
//            writer.write(e.getMessage());
//            return;
//        }

        if (doCUD(req, resp)) {
            resp.getWriter().write("Данные добавлены успешно");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        setEncodingType(req, resp);
//
//        IGroup group;
//
//        ServletInputStream inputStream = req.getInputStream();
//        PrintWriter writer = resp.getWriter();
//
//        try {
//            group = mapper.readValue(inputStream, Group1_1.class);
//
//        }catch (JsonProcessingException e) {
//            writer.write("Не верно переданы данные");
//            return;
//        }
//
//        try {
//            service.delete(group);
//
//        }catch (GroupIllegalStateException e) {
//            writer.write(e.getMessage());
//            return;
//
//        }catch (GroupDaoException e) {
//            this.log(e.getMessage(), e.getCause());
//            writer.write(e.getMessage());
//            return;
//        }

        if (doCUD(req, resp)) {
            resp.getWriter().write("Данные удалены успешно");
        }
    }

    private boolean doCUD(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        setEncodingType(req, resp);


        ServletInputStream inputStream = req.getInputStream();
        PrintWriter writer = resp.getWriter();

        IGroup group;

        try {

            switch (req.getMethod()) {
                case "PUT":
                    group = mapper.readValue(inputStream, GroupUpdate1_1.class);
                    service.update(group);
                    break;

                case "POST":
                    group = mapper.readValue(inputStream, Group1_1.class);
                    service.save(group);
                    break;

                case "DELETE":
                    group = mapper.readValue(inputStream, Group1_1.class);
                    service.delete(group);
                    break;
            }

        } catch (JsonProcessingException e) {
            writer.write("Не верно переданы данные");
            return false;

        } catch (GroupIllegalStateException e) {
            writer.write(e.getMessage());
            return false;

        } catch (GroupDaoException e) {
            this.log(e.getMessage(), e.getCause());
            writer.write(e.getMessage());
            return false;
        }

        return true;
    }

    private void setEncodingType(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");
    }

    private void write(HttpServletResponse resp, String json) throws IOException {
        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }
}
