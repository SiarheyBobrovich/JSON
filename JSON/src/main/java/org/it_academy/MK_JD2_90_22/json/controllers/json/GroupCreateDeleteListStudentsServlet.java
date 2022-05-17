package org.it_academy.MK_JD2_90_22.json.controllers.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.it_academy.MK_JD2_90_22.json.dto.GroupStudentId;
import org.it_academy.MK_JD2_90_22.json.services.GroupCreateDeleteListStudentsService;
import org.it_academy.MK_JD2_90_22.json.services.api.ICrossService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GroupCreateDeleteListStudentsServlet extends HttpServlet {

    private ObjectMapper mapper;
    private static final ICrossService<GroupStudentId> service = GroupCreateDeleteListStudentsService.getInstance();

    public GroupCreateDeleteListStudentsServlet() {
        this.mapper = new ObjectMapper()
                .setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE)
                .registerModule(new JavaTimeModule());
    }
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
