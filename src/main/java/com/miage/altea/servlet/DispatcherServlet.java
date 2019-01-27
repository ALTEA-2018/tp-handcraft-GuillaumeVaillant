package com.miage.altea.servlet;

import com.miage.altea.controller.HelloController;
import com.miage.altea.controller.RequestMapping;
import com.miage.altea.controller.Controller;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* Objectif de cette classe -> faire la concordance entre les uri et les méthodes grace aux annotations
   Par exemple on declare la servlet HelloController avec ses méthode saysHello (/hello), saysbye(/bye)
 */
@WebServlet(urlPatterns = "/*", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {

    //String = uri, Method = la requestmethode java qui écoute
    private Map<String, Method> uriMappings = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("Getting request for " + req.getRequestURI());

        String uri = req.getRequestURI();
        Method method = getMappingForUri(uri);

        if(method == null) { resp.sendError(404,"no mapping found for request uri /test"); return ; }

        //Instancier le controller dynamiquement
        Object controller = null;

        try {
            controller = method.getDeclaringClass().getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Récupérer les paramètres de l'url et appeler la méthode
        Object invoke = null;

        try
        {
            invoke = req.getParameterMap().isEmpty() ? method.invoke(controller) : method.invoke(controller,req.getParameterMap());

            resp.getWriter().print((String) invoke);

        } catch (Exception e)
        {
            resp.sendError(500,"exception when calling method someThrowingMethod : some exception message");
        }

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        // on enregistre notre controller au démarrage de la servlet
        this.registerController(HelloController.class);
    }

    protected void registerController(Class controllerClass) {
        System.out.println("Analysing class " + controllerClass.getName());

        if (controllerClass.getAnnotation(Controller.class) == null) {
            throw new IllegalArgumentException("Class must have Controller annotation");
        }

        for (Method declaredMethod : controllerClass.getDeclaredMethods())
        {
            this.registerMethod(declaredMethod);
        }
    }


    protected void registerMethod(Method method) {
        System.out.println("Registering method " + method.getName());

        var a = method.getAnnotation(RequestMapping.class);

        if (a != null && !method.getReturnType().equals(void.class)) {
            System.out.println("uri associate " + a.uri());
            this.uriMappings.put(a.uri(), method);
        }
    }

    protected Map<String, Method> getMappings(){
        return this.uriMappings;
    }

    protected Method getMappingForUri(String uri){
        return this.uriMappings.get(uri);
    }
}