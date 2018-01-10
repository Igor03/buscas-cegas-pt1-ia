package Problemas;

import java.util.LinkedList;
import Estruturas.Estado;

public interface Problema {
	
	public void inicializarAcoes();
	public LinkedList<Estado> funcaoSucessora(Estado estado);
	public boolean testeDeObjetivo(Estado estadoAtual);
	public Estado verificaEstadoInicial (String nomeEstadoInicial);
	public Estado verificaObjetivo (String nomeOjetivo);
	public Estado getEstadoInicial();
	public Estado getObejetivo();
	public String getNomeEstadoInicial();
	public String getNomeObjetivo();

}
