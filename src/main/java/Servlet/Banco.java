package Servlet;

import java.util.ArrayList;
import java.util.List;

public class Banco {
	
	private static List<Cadastro> banco =  new ArrayList<Cadastro>();
	
	public void adicionar (Cadastro c) {
		banco.add(c);
	}
	
	public List<Cadastro> getAll(){
		return Banco.banco;
	}
	
	public boolean contem(Cadastro c) {
		for (Cadastro cad : banco) {
			if(c.getRg().equals(cad.getRg())||
			   c.getCpf().equals(cad.getCpf()) ||
			   c.getTitulo().equals(cad.getTitulo())) {
			return true;
	    	}
		}
			return false;
		}
		
	
}
