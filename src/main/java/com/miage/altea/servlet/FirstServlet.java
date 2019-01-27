package com.miage.altea.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.RetentionPolicy;


//loadonstartup = 1 -> on demarre la servlet sans attendre de première requête
@WebServlet(urlPatterns = "/FirstServlet", loadOnStartup = 1)
public class FirstServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        var writer = resp.getWriter();
        writer.println("Hello !");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        System.out.println("Initialisation de la servlet");
    }

}


