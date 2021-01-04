package service.servlet;

import com.google.gson.Gson;
import model.Seat;
import persistencebase.HallEntityWork;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/hall")
public class HallServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        System.out.println("DoGet  HallServlet");
        ArrayList<Seat> seats = (ArrayList<Seat>) new HallEntityWork().findAll();
        //  List<String> seatsSt = seats.stream().map(seat -> seat.toString()).collect(Collectors.toList());
        //       List<String> seatsJs = seats.stream().map(seat -> new Gson().toJson(seat)).collect(Collectors.toList());
        //      List<String> seatsJs =  new Gson().toJson(seats);
        String js = new Gson().toJson(seats);
        //     System.out.println(js);
        PrintWriter writer = new PrintWriter(resp.getOutputStream(), true, StandardCharsets.UTF_8);
        writer.println(js);
        writer.flush();
    }
}
