package Services;

import java.util.List;

public class CalcResgate {

	private int qtdUserResgatado;
	private int qtdUserNaoResgatado;
	private List<String> userComErroResgate;
	
	public int getQtdUserResgatado() {
		return qtdUserResgatado;
	}
	public void setQtdUserResgatado(int qtdUserResgatado) {
		this.qtdUserResgatado = qtdUserResgatado;
	}
	public int getQtdUserNaoResgatado() {
		return qtdUserNaoResgatado;
	}
	
	public void setQtdUserNaoResgatado(int d) {
		this.qtdUserNaoResgatado = d;
	}
	
	public void incrementarMaisUmUserNaoResgatado() {
		this.qtdUserNaoResgatado++;
	}
	public void incrementarMaisUmUserResgatado() {
		this.qtdUserResgatado++;
	}
	
	public List<String> getUserComErroResgate() {
		return userComErroResgate;
	}
	public void setUserComErroResgate(List<String> userComErroResgate) {
		this.userComErroResgate = userComErroResgate;
	}
	
	public double getPorcentagemResgatado() {
        int total = getQtdUserResgatado() + getQtdUserNaoResgatado();
        return total > 0 ? (getQtdUserResgatado() / (double) total) * 100 : 0;
    }

    public double getPorcentagemNaoResgatado() {
        int total = getQtdUserResgatado() + getQtdUserNaoResgatado();
        return total > 0 ? (getQtdUserNaoResgatado() / (double) total) * 100 : 0;
    }
	
}
