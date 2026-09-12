package com.travelport.core.servlets;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.stream.Collectors;

@Component(service = Servlet.class)
@SlingServletPaths("/bin/api/students")
@ServiceDescription("This servlet is written for test, how the AF form can submit by using REST API")
public class StudentServlet extends SlingAllMethodsServlet {

    private static final Logger log = LoggerFactory.getLogger(StudentServlet.class);


    @Reference(target = "(datasource.name=form_training)")
    private DataSource dataSource;

    private static final String INSERT_QUERY="INSERT INTO student VALUES (?,?,?,?)";

    @Override
    protected void doPost(SlingHttpServletRequest request,  SlingHttpServletResponse response) throws ServletException, IOException, IllegalStateException {
        log.info("Inside doPost method");

        response.setContentType("application/json");
        String jcrData = request.getParameter("jcr:data");
        log.info("jcrData={}", jcrData);

        ObjectMapper  mapper = new ObjectMapper();
        JsonNode formData = mapper.readTree(jcrData);
        log.info("formData={}", formData);

        String id = formData.get("id").asText();
        String name = formData.get("name").asText();
        String email = formData.get("email").asText();
        long phone = formData.get("phone").asLong();

        log.info("id, studentName, email, phoneNumber: {},{},{},{}",id,name,email,phone);

        if (id == null || name == null || email == null || phone == 0) {
            return;
        }

        try {
            Connection connection = dataSource.getConnection();
            log.info("Connection established {}", connection);

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, email);
            preparedStatement.setLong(4, phone);
            preparedStatement.executeUpdate();

            log.info("Inserted student");

            PrintWriter writer = response.getWriter();
            writer.println("{\n+id:"+id+","+"\n+studentName:"+name+",\n"+"email:"+email+",\n"+phone);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}