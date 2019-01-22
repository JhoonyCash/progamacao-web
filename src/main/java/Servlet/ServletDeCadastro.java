package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import br.com.caelum.stella.validation.TituloEleitoralValidator;

@WebServlet("/servletcadastro")
public class ServletDeCadastro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		Banco banco = new Banco();
		
		String nome = req.getParameter("nome");
		String sobrenome = req.getParameter("snome");
		String cep = req.getParameter("cep");
		String rg = req.getParameter("rg");
		String end = req.getParameter("end");
		String cpf = req.getParameter("cpf");
		String titulo = req.getParameter("titulo");
		
		PrintWriter out = res.getWriter();
		
		CPFValidator cpfvalidator = new CPFValidator();
		TituloEleitoralValidator tituloValidator = new TituloEleitoralValidator();
		
		
		if(nome.isEmpty()||sobrenome.isEmpty()||cep.isEmpty()||rg.isEmpty()||end.isEmpty()||cpf.isEmpty()||titulo.isEmpty()) {
			
			out.println("<html><head><link rel=stylesheet href=https://www.w3schools.com/w3css/4/w3.css>");
			out.println("<title>ERRO</title>"
						+ "<meta charset=\"UTF-8\" name=\"viewport\" content=\"width=device-width, initial-scale=1\"></head>");
			
			out.println("<body><div class=\"w3-conteiner w3-red\">\n" + 
					"  <h3>Algum(ns) dos Campos Requisitados Esta(ao) Em Branco, Efetue Novamente o Preenchimento Clicando <a href=CadastroHtml.html>Aqui</a></h3>\n"+ 
					"</div> ");
			
			out.println("<a href=http://localhost:8080/servletCadastro/CadastroHtml.html class=\"w3-btn w3-grey w3-round w3-margin\">Voltar</a> ");
			out.println("<a href=http://localhost:8080/servletCadastro/Front.html class=\"w3-btn w3-grey w3-display-bottomright w3-round style=\"float:left\" w3-margin\">Pagina Principal</a> ");
			
			out.println("</body>");
			return;
			
			
		}else{
			
			Cadastro c = new Cadastro ();
			
			c.setNome(nome);
			c.setSobrenome(sobrenome);
			c.setCep(cep);
			c.setEnd(end);
			c.setTitulo(titulo);
			c.setRg(rg);
			c.setCpf(cpf);
			
			out.println("<html><head><link rel=stylesheet href=https://www.w3schools.com/w3css/4/w3.css>");
			out.println("<title>ERRO</title>"
						+ "<meta charset=\"UTF-8\" name=\"viewport\" content=\"width=device-width, initial-scale=1\"></head>");
			
			try {
				cpfvalidator.assertValid(cpf);
				tituloValidator.assertValid(titulo);
			} catch (InvalidStateException e) {
				List<ValidationMessage> cpfValidationMessages = cpfvalidator.invalidMessagesFor(cpf);
				List<ValidationMessage> tituloValidationMessages = tituloValidator.invalidMessagesFor(titulo);
				
				
				if(!cpfValidationMessages.isEmpty()) {
					out.println("<body><div class=\"w3-container w3-red\">\n" + 
							"  <h3>CPF Invalido</h3>\n" + 
							"  <p>"+ cpfValidationMessages.toString()+"</p>\n" + 
							"</div> ");
				}
				if(!tituloValidationMessages.isEmpty()) {
					out.println("<body><div class=\"w3-conteiner w3-red\">\n" + 
							"  <h3>Titulo de Eleitor Invalido</h3>\n" + 
							"  <p>"+ tituloValidationMessages.toString()+"</p>\n" + 
							"</div> ");
				}
				
			
			out.println("<a href=http://localhost:8080/servletCadastro/CadastroHtml.html class=\"w3-btn w3-grey w3-round w3-margin\">Voltar</a> ");
			out.println("<a href=http://localhost:8080/servletCadastro/Front.html class=\"w3-btn w3-grey w3-round w3-display-bottomright style=\"float:right\" w3-margin\">Pagina Principal</a> ");
	
			out.println("</body></html>");
			return;
			
		}
			if(banco.contem(c)) {
				out.println("<body><div class=\"w3-container w3-grey\">\n" + 
						"  <h3>Cadastro Não Efetuada</h3>\n" + 
						"  <p>Dados ja existentes em outros cadastros</p>\n" + 
						"</div> ");
				
				out.println("<a href=http://localhost:8080/servletCadastro/CadastroHtml.html class=\"w3-btn w3-grey w3-round w3-margin\">Cadastrar Novamente</a> ");
				out.println("<a href=http://localhost:8080/servletCadastro/Front.html class=\"w3-btn w3-grey w3-display-bottomright w3-round style=\"float:right\" w3-margin\">Pagina Principal</a> ");
		
				
			}else{
				out.println("<body><div class=\"w3-container w3-grey\">\n" + 
						"  <h3>Cadastro Efetuada</h3>\n" + 
						"  <p>Seus dados foram cadastrados com sucesso</p>\n" + 
						"</div> ");
				out.println("<a href=http://localhost:8080/servletCadastro/CadastroHtml.html class=\"w3-btn w3-grey w3-round w3-margin\">Novo Cadstro</a> ");
				out.println("<a href=http://localhost:8080/servletCadastro/Front.html class=\"w3-btn w3-grey  w3-display-bottomright w3-round style=\"float:right\" w3-margin\">Pagina Principal</a> ");
		
				banco.adicionar(c);
				
				
			}
	 }
	}
//   }

}
