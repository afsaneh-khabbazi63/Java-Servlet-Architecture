package controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.IndyWinnerModel;
import dao.IndyWinnerDao;


@WebServlet("/")
public class IndyWinnerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IndyWinnerDao indyWinnerDao;
	private int page = 1;
	
	public void init() {
		indyWinnerDao = new IndyWinnerDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter pw = null;
	    response.setContentType("text/html");
	    pw = response.getWriter();
	    String action = request.getServletPath();
	    try {
			switch (action) {
			case "/home":
				pw.println(listIndyWinner(request, response));
				break;
			case "/previous":
				page = page - 1;
				pw.println(listIndyWinner(request, response));
				break;
			case "/next":
				page = page + 1;
				pw.println(listIndyWinner(request, response));
				break;
			default:
				pw.println("<a href='home'><button type='button'>click me to show you table</button></a>");
				break;
			}
		} catch (Exception ex) {
			pw.println(ex.toString());
		}
	    
	   
	    pw.close();
	}

	private String listIndyWinner(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
        int recordsPerPage = 20;
        int noOfRecords = indyWinnerDao.countIndyWinner();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        if(page<1)page = 1 ;
        if(page>noOfPages)page = noOfPages;
        List<IndyWinnerModel> list = indyWinnerDao.getAllIndyWinner((page-1)*recordsPerPage,
                                 recordsPerPage);
        
        String HTML = "";
        HTML = "<div align='center'><div  style='height:550px;overflow:auto;width:400px;'><table border='1' cellpadding='5'><caption><h2>List of IndyWinner</h2></caption>";
        HTML = HTML + "<tr><th>YEAR</th><th>DRIVER</th><th>AVERAGESPEED</th></tr>";
        for (Object indyWinner : list) {
        	Object[] items = (Object[])indyWinner;
        	HTML = HTML + "<tr><th>"+items[0]+"</th><th>"+items[1]+"</th><th>"+items[2]+"</th></tr>";
		}
        HTML = HTML + "</table></div><a style='margin:10px;' href='next'>next</a><a style='margin:10px;' href='previous'>previous</a></div>";
        return HTML;
		
	}
}
