package service.servlet;

import model.*;
import persistencebase.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@WebServlet("/complete")
public class CompletingPaymentS extends HttpServlet {

    public boolean saveEntityOrNot(CheckerEntity<Model> checker, String field, Model model) {
        if (!checker.isEntityHereTwo(field)) {
            checker.getEntities().save(model);
            return true;
        }
        return false;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        System.out.println(" DoGet CompletingPs 31");
        String phoneString = req.getParameter("phone");
        System.out.println(" DoGet CPS  String phoneString 33:" + phoneString);

        String[] numbersArray = phoneString.split(" "); // splitting string by spaces
        StringBuilder b = new StringBuilder();
        for (String a : numbersArray) {
            System.out.print(a);
            b.append(a);
        }
        String phone = b.toString();
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");


        System.out.println(" DoGet  DoGet CompletingPs  39 firstName :" + firstName);
        System.out.println(" DoGet  DoGet CompletingPs  40 lastName :" + lastName);
        int place = Integer.parseInt(req.getParameter("place"));
        String data = req.getParameter("data");

        Timestamp timePayment = Timestamp.valueOf(LocalDateTime.now());
        System.out.println("TimeStamp Time payment :" + timePayment);
        System.out.println("Time 0f Session :" + data);
        System.out.println("PhoneNumber :" + phone + "Place :" + place);

        User inputNewUser = new User(0, firstName, lastName, phone);

        new UserEntity().saveIfNot(inputNewUser);
        new AccountTicketEntity().create(new AccountTicket(0, new TicketEntity().create(new Ticket(0, timePayment, TimeStampResult
                .stringDateConvertTs(data), place)).getNumber(), phone));
        new HallEntityWork().changeOccupation(place);

        PrintWriter outNew = resp.getWriter();
        outNew.println("Hello baby Payment was successful, Time of Payment:" + timePayment.toString());
        //   outNew.close();
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }
}
