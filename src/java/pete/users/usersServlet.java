package pete.users;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Enumeration;
import java.util.Properties;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class usersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.process(request, response);
    }

    private void Submitprocessor(HttpServletRequest request, HttpServletResponse response, PrintWriter out) {
        String msUrl = "jdbc:mysql://localhost:3306/users";
        Properties msProps = new Properties();
        msProps.setProperty("user", "root");
        msProps.setProperty("password", "1580");

        try {
            Connection msConnection = DriverManager.getConnection(msUrl, msProps);
            Statement msStatement = msConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Enumeration en = request.getParameterNames();
            String login = null, password = null;
            while (en.hasMoreElements()) {
                // Get the name of the request parameter
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
            } else {
                out.println("Wrong login/password! :(");
            }
            //response.sendRedirect("REGsuccessed.jsp");
        } catch (SQLException ex) {
            out.println(ex);
        }
    }

    private void Registerprocessor(HttpServletRequest request, HttpServletResponse response, PrintWriter out) {
        String msUrl = "jdbc:mysql://localhost:3306/users";
        Properties msProps = new Properties();
        msProps.setProperty("user", "root");
        msProps.setProperty("password", "1580");

        try {
            Connection msConnection = DriverManager.getConnection(msUrl, msProps);
            Statement msStatement = msConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Enumeration en = request.getParameterNames();
            String login = null, password = null;
            while (en.hasMoreElements()) {
                // Get the name of the request parameter
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
     generate the page showing all the request parameters
     */
    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(200);
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        Enumeration en = request.getParameterNames();
        while (en.hasMoreElements()) {
            String name = (String) en.nextElement();
            String value = request.getParameter(name);
            if (name.equals("Submit")) {
                Submitprocessor(request, response, out);
            }
            if (name.equals("Register")) {
                Registerprocessor(request, response, out);
            }
        }

        //
//        String msUrl = "jdbc:mysql://localhost:3306/users";
//        Properties msProps = new Properties();
//        msProps.setProperty("user", "root");
//        msProps.setProperty("password", "1580");
//        try {
//            Connection msConnection = DriverManager.getConnection(msUrl, msProps);
//            Statement msStatement = msConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//            ResultSet rs = msStatement.executeQuery("SELECT * FROM login_password");
//            while (rs.next()) {
//                //int foo = rs.getInt(1);
//                out.println(rs.getString(1));
//                out.println(rs.getString(2));
//            }
//        } catch (SQLException ex) {
//            out.println(ex);
//        }
        //out.println(groupsResultSet.getString(1));
        // Get the values of all request parameters
        //////////////////////////////////////////////
//        Enumeration en = request.getParameterNames();
//        while (en.hasMoreElements()) {
//            // Get the name of the request parameter
//            String name = (String) en.nextElement();
//            out.println(name);
//
//            // Get the value of the request parameter
//            String value = request.getParameter(name);
//
//            // If the request parameter can appear more than once in the query string, get all values
//            String[] values = request.getParameterValues(name);
//
//            for (int i = 0; i < values.length; i++) {
//                out.println(" " + values[i]);
//            }
//        }
//        out.close();
    }
}
