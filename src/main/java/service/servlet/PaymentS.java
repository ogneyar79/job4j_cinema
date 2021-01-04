package service.servlet;

import model.TimeStampResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet("/pay")
public class PaymentS extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        System.out.println(" DoPost  PaymentS  31 String");
        String sessionDate = req.getParameter("dateSession");
        System.out.println(" sessionDate  34 String :" + sessionDate);
        Timestamp dateSession = TimeStampResult.stringDateConvertTs(sessionDate);

        String placeNumber = req.getParameter("place");
        HttpSession sc = req.getSession();
        sc.setAttribute("data", sessionDate);
        //     req.setAttribute("data", sessionDate);
        req.setAttribute("pN", placeNumber);

        System.out.println(" DoGet  PaymentS + placeNumber:" + placeNumber + " " + "Timestamp dateSession:" + dateSession);
        req.getRequestDispatcher("payment.jsp").forward(req, resp);
        resp.sendRedirect(req.getContextPath() + "/pay");
    }
}
