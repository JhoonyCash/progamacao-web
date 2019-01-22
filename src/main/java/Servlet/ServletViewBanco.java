package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/verBanco")
public class ServletViewBanco extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Banco b = new Banco();
		Cadastro c = new Cadastro();
		
		List<Cadastro> cadastrados = b.getAll();
		
		PrintWriter out = response.getWriter();
		
		out.println("<html><head><link rel=stylesheet href=https://www.w3schools.com/w3css/4/w3.css>");
		out.println("<meta charset=ISO-8859-1 name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
		out.println("<title>Lista de Cadastrados</title></head>");
		out.println("<div class=\"w3-container w3-black\" >");
		out.println("<h2>Banco de Cadastros</h2></div>");
		
		out.println("<table class=\"w3-table w3-card-4 w3-centered\">");
		out.println("<thead><tr class=w3-grey>");
		out.println("<th>Nome</th>");
		out.println("<th>Sobrenome</th>");
		out.println("<th>CEP</th>");
		out.println("<th>Endereço</th>");
		out.println("<th>RG</th>");
		out.println("<th>CPF</th>");
		out.println("<th>Título de Eleitor</th></tr></thead>");
		
		for(Cadastro c1 : cadastrados) {
			out.println("<tr class=w3-hover-light-grey>");
				out.println("<th>"+ c1.getNome()+"</th>");
				out.println("<th>"+ c1.getSobrenome()+"</th>");
				out.println("<th>"+ c1.getCep()+"</th>");
				out.println("<th>"+ c1.getEnd()+"</th>");
				out.println("<th>"+ c1.getRg()+"</th>");
				out.println("<th>"+ c1.getCpf()+"</th>");
				out.println("<th>"+ c1.getTitulo()+"</th>");
			out.println("</tr>");
		}
		
		out.println("<a href=CadastroHtml.html>"
				+ "<input class=\"w3-btn w3-grey w3-round w3-display-bottomleft w3-margin\" value=\"Novo cadastro\"></a>");
		out.println("<a href=Front.html>"
				+ "<input class=\"w3-btn w3-grey w3-round w3-display-bottomright w3-margin\" value=\"Pagina Inicial\"></a>");
	
		out.println("</body></html>");
		
		
	}

}
