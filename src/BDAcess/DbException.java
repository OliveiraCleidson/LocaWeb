package BDAcess;

public class DbException extends RuntimeException {
	/* SQLException herda de Exception
	** Com o lancamento de DbException herdada de RuntimeException
	** Nao sera necessario tratar exceptions toda hora */
	private static final long serialVersionUID = 1L;
	public DbException(String msg) {
		super(msg);
	}
}
