package com.airwatch.front_end;

import com.airwatch.hibernate_layer.TripCRUDLogic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class BookTrip
        extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter responseOutWriter = response.getWriter();
        responseOutWriter.println(Writer.output);

        String bookerName = request.getParameter("name");
        String source = request.getParameter("source");
        String destination = request.getParameter("destination");
        String startDate = request.getParameter("start_date");
        String returnDate = request.getParameter("return_date");
        int passengers = Integer.parseInt(request.getParameter("passengers"));

        TripCRUDLogic logic = new TripCRUDLogic();

        logic.addTrip(bookerName, source, destination, startDate, returnDate, passengers);
        logic.listTrips();
    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        super.doPut(req, resp);
    }
}

class Writer {
    static String output = "<html>\n\t<head>\n\t\t<title>Travel Club</title>\n\t\t<style>\n\t\t\t#travel_form{\n\t\t\t\tfont-family:Calibri;\n\t\t\t\tdisplay:block;\n\t\t\t\tborder:8px groove #ddd;\n\t\t\t\tborder-radius:25px;\n\t\t\t\tpadding:20px;\n\t\t\t\tmargin:10px;\n\t\t\t}\n\n\t\t\t.form_elements{\n\t\t\t\tlist-style-type:none;    \n\t\t\t}\n\n\t\t\t.float_r{\n\t\t\t\tfloat:right;\n\t\t\t\tborder:2px solid blue;\n\t\t\t\tborder-radius:1000px;\n\t\t\t\twidth:300px;  \n\t\t\t\tpadding:0px 10px 0px 10px;\n\t\t\t}\n\n\t\t\t.float_r:focus{\n\t\t\t\tborder:2px solid red;\n\t\t\t}\n\n\t\t\tspan{\n\t\t\t\tmargin:0px 10px 0px 350px;\n\t\t\t}\n\n\t\t\tli{\n\t\t\t\tpadding:7px 0px 7px 0px;\n\t\t\t\tmargin:0px 350px 0px 10px;\n\t\t\t}\n\n\t\t\t#heading{\n\t\t\t\tborder:8px groove #ddd;\n\t\t\t\tborder-radius:25px;\n\t\t\t\tmargin:10px;\n\t\t\t}\n\n\t\t\th1{\n\t\t\t\ttext-align:center;\n\t\t\t\tcolor:red;\n\t\t\t\tfont-style:Cambria;\n\t\t\t}\n\n\t\t\t#book_journey{\n\t\t\t\tmargin-left:50%;\n\t\t\t\tmargin-right:50%;\n\t\t\t\tborder:2px groove #ddd;\n\t\t\t\tborder-radius:1000px;\n\t\t\t\tbackground:red;\n\t\t\t\tcolor:white;\n\t\t\t\theight:30px;\n\t\t\t\twidth:100px;\n\t\t\t}\n\t\t</style>\n\t</head>\n\t<body>\n\t\t<div id='container'>\n\t\t\t<div id='heading'>\n\t\t\t\t<h1>TRAVEL CLUB</h1>\n\t\t\t</div>\n\t\t\t<form id='travel_form'>\n\t\t\t\t<ul>\n\t\t\t\t\t<li class='form_elements'>\n\t\t\t\t\t<span>Booker's Name : </span>\n\t\t\t\t\t<input type='text' id='booker' name='booker' class='float_r' autofocus='true' required/>\n\t\t\t\t\t</li>\n\t\t\t\t\t<li class='form_elements'>\n\t\t\t\t\t<span>From : </span>\n\t\t\t\t\t<input type='text' id='source' name='source' class='float_r' maxlength='10' required/>\n\t\t\t\t\t</li>\n\t\t\t\t\t<li class='form_elements'>\n\t\t\t\t\t<span>To : </span>\n\t\t\t\t\t<input type='text' id='destination' name='destination' class='float_r' maxlength='10' required/>\n\t\t\t\t\t</li>\n\t\t\t\t\t<li class='form_elements'>\n\t\t\t\t\t<span>Date of Journey : </span>\n\t\t\t\t\t<input type='date' id='start_date' name='start_date' class='float_r' required/>\n\t\t\t\t\t</li>\n\t\t\t\t\t<li class='form_elements'>\n\t\t\t\t\t<span>Date of Return : </span>\n\t\t\t\t\t<input type='date' id='return_date' name='end_date' class='float_r' required/>\n\t\t\t\t\t</li>\n\t\t\t\t\t<li class='form_elements'>\n\t\t\t\t\t<span>Number of Passengers : </span>\n\t\t\t\t\t<input type='number' id='passengers' name='passengers' class='float_r' max='10' min='1' required/>\n\t\t\t\t\t</li>\n\t\t\t\t</ul>\n\t\t\t\t<input type='submit' id='book_journey' value='Book Journey'/>\n\t\t\t</form>\n\t\t</div>\n\t</body>\n</html>";
}
