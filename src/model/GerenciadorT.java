package model;

import java.util.Calendar;

public interface GerenciadorT {
	AluguelModel pagarAluguel(int idAluguel, int idCliente);
	AluguelModel gerarAluguel(Calendar dataFim);
	
}
