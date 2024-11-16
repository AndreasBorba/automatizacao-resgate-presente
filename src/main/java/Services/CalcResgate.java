package Services;

import java.util.List;

public class CalcResgate {

	private double qtdUserResgatado;
	private double qtdUserNaoResgatado;
	private List<String> userComErroResgate;
	
	public double getQtdUserResgatado() {
		return qtdUserResgatado;
	}
	public void setQtdUserResgatado(double qtdUserResgatado) {
		this.qtdUserResgatado = qtdUserResgatado;
	}
	public double getQtdUserNaoResgatado() {
		return qtdUserNaoResgatado;
	}
	public void setQtdUserNaoResgatado(double d) {
		this.qtdUserNaoResgatado = d;
	}
	public List<String> getUserComErroResgate() {
		return userComErroResgate;
	}
	public void setUserComErroResgate(List<String> userComErroResgate) {
		this.userComErroResgate = userComErroResgate;
	}
	
	public double getPorcentagemResgatado() {
		return (getQtdUserResgatado() / (getQtdUserResgatado() + getQtdUserNaoResgatado())) * 100;
	}
	
	public double getPorcentagemNaoResgatado() {
		return (getQtdUserNaoResgatado() / (getQtdUserResgatado() + getQtdUserNaoResgatado())) * 100;
	}
	
}
