package pete.users;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Enumeration;
import java.util.Properties;
import javax.servlet.http.*;

public class usersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.process(request, response);
    }

    private void loginProcessor(HttpServletRequest request, HttpServletResponse response, PrintWriter out) throws IOException {
        //connect to the database
        String msUrl = "jdbc:mysql://localhost:3306/users";
        Properties msProps = new Properties();
        msProps.setProperty("user", "root");
        msProps.setProperty("password", "1580");

        try {
            Connection msConnection = DriverManager.getConnection(msUrl, msProps);
            Statement msStatement = msConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Enumeration en = request.getParameterNames();
            String login = null, password = null;
            //record  request parameters into variables
            while (en.hasMoreElements()) {
                String name = (String) en.nextElement();
                if (name.equals("Login")) {
                    login = request.getParameter(name);
                }
                if (name.equals("Password")) {
                    password = request.getParameter(name);
                }
            }
            ResultSet rs = msStatement.executeQuery("SELECT password FROM login_password WHERE login = \"" + login + "\"");
            if (rs.next() && password.equals(rs.getString(1))) {
                out.println("Success! :))");
                //get session
                HttpSession hs = request.getSession(true);
                hs.setAttribute("Login", login);
                response.sendRedirect("MainUserPage.jsp");
            } else {
                out.println("Wrong login/password! :(");
            }
        } catch (SQLException ex) {
            out.println(ex);
        }
    }

    private void registerProcessor(HttpServletRequest request, HttpServletResponse response, PrintWriter out) {
        //connect to the database
        String msUrl = "jdbc:mysql://localhost:3306/users";
        Properties msProps = new Properties();
        msProps.setProperty("user", "root");
        msProps.setProperty("password", "1580");

        try {
            Connection msConnection = DriverManager.getConnection(msUrl, msProps);
            Statement msStatement = msConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Enumeration en = request.getParameterNames();
            String login = null, password = null;
            //record  request parameters into variables
            while (en.hasMoreElements()) {
                String name = (String) en.nextElement();
                if (name.equals("Login")) {
                    login = request.getParameter(name);
                }
                if (name.equals("Password")) {
                    password = request.getParameter(name);
                }
            }
            msStatement.executeUpdate("INSERT INTO login_password (login,password) VALUES (" + "\"" + login + "\",\"" + password + "\"" + ")");
            response.sendRedirect("REGsuccessed.jsp");
        } catch (SQLException | IOException ex) {
            out.println(ex);
        }
    }

    /*
     Select processor
     */
    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(200);
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        Enumeration en = request.getParameterNames();
        while (en.hasMoreElements()) {
            String name = (String) en.nextElement();
            if (name.equals("Submit")) {
                loginProcessor(request, response, out);
            }
            if (name.equals("Register")) {
                registerProcessor(request, response, out);
            }
        }
    }
}
