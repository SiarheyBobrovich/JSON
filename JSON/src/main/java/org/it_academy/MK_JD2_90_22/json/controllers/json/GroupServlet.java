package org.it_academy.MK_JD2_90_22.json.controllers.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.it_academy.MK_JD2_90_22.json.dto.group.GroupName;
import org.it_academy.MK_JD2_90_22.json.dto.group.GroupRefresh;
import org.it_academy.MK_JD2_90_22.json.exceptions.dao.GroupDaoException;
import org.it_academy.MK_JD2_90_22.json.exceptions.service.GroupIllegalNameException;
import org.it_academy.MK_JD2_90_22.json.services.GroupService;
import org.it_academy.MK_JD2_90_22.json.services.api.ICRUDGroupService;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GroupServlet", urlPatterns = "/group")
public class GroupServlet extends HttpServlet {

    private static final ICRUDGroupService service = GroupService.getInstance();
    private ObjectMapper mapper;

    public GroupServlet() {
        this.mapper = new ObjectMapper()
                .setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);
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

        }catch (GroupDaoException e) {
            this.log(e.getMessage(), e.getCause());
            resp.getWriter().write("База данных на данный момент не доступна, обратитесь в тех. поддержку");
            return;

        }catch (GroupIllegalNameException e) {
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

        GroupRefresh groupRefresh;

        PrintWriter writer = resp.getWriter();
        ServletInputStream inputStream = req.getInputStream();

        try {
            groupRefresh = mapper.readValue(inputStream, GroupRefresh.class);

        }catch (JsonProcessingException e) {
            writer.write("Не верно переданные данные");
            return;
        }

        try {
            service.update(groupRefresh);

        }catch (GroupIllegalNameException e) {
            writer.write(e.getMessage());
            return;

        }catch (GroupDaoException e) {
            this.log(e.getMessage(), e.getCause());
            writer.write(e.getMessage());
            return;
        }

        writer.write("Данные успешно обновлены");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");

        GroupName groupName;

        ServletInputStream inputStream = req.getInputStream();
        PrintWriter writer = resp.getWriter();

        try {
            groupName = mapper.readValue(inputStream, GroupName.class);

        }catch (JsonProcessingException e) {
            writer.write("Не верно переданы данные");
            return;
        }

        try {
            service.save(groupName);

        }catch (GroupIllegalNameException e) {
            writer.write(e.getMessage());
            return;

        }catch (GroupDaoException e) {
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

        GroupName groupName;

        ServletInputStream inputStream = req.getInputStream();
        PrintWriter writer = resp.getWriter();

        try {
            groupName = mapper.readValue(inputStream, GroupName.class);

        }catch (JsonProcessingException e) {
            writer.write("Не верно переданы данные");
            return;
        }

        try {
            service.delete(groupName);

        }catch (GroupIllegalNameException e) {
            writer.write(e.getMessage());
            return;

        }catch (GroupDaoException e) {
            this.log(e.getMessage(), e.getCause());
            writer.write(e.getMessage());
            return;
        }

        writer.write("Данные удалены успешно");
    }
}
