package model;

import java.util.Calendar;
import java.util.List;

public interface GerenciadorT {
	boolean pagarAluguel(int idAluguel, int idCliente);
	AluguelModel gerarAluguel(int idCliente, Calendar dataFim);
	List<AluguelModel> listarAluguel(int idCliente);
	
}
