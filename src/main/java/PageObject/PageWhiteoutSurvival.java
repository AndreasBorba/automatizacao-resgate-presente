package PageObject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageWhiteoutSurvival {

	private WebDriver driver;
	private WebDriverWait wait;

	private final By inputIDJogador = By.xpath("//input[@placeholder='ID do Jogador']");
	private final By btnLogin = By.xpath("//div[@class='btn login_btn ']");
	private final By inputCodigoResgate = By.xpath("//input[@placeholder='Inserir Código de Presente']"
			+ "");
	private final By btnConfirmar = By.xpath("//div[@class='btn exchange_btn ']");
	private final By modal = By.xpath("//div[@class='modal_content']");
	private final By btnLogout = By.xpath("//div[@class='exit_icon']");
	private final By btnModalConfirmar = By.xpath("//div[@class='modal_content']//div[@class='confirm_btn']");

	private final String textoPositivo = "Resgatado, reivindique as recompensas no seu e-mail!";
	private final String textoNegativo = "Já coletado, não é possível coletar novamente";
	private final String textoServidor = "Servidor ocupado. Tente novamente mais tarde.";

	private boolean primeiraIteracao = true;

	public String getTextoPositivo() {
		return textoPositivo;
	}

	public String getTextoNegativo() {
		return textoNegativo;
	}

	public PageWhiteoutSurvival(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}

	public void preencherFormularioWOS(String user, String giftCode) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(inputIDJogador)).clear();
		if (primeiraIteracao) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(inputCodigoResgate)).sendKeys(giftCode);
			this.primeiraIteracao = false;
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(inputIDJogador)).sendKeys(user);

	}

	public void realizarLogin() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnLogin)).click();
		boolean condicional = validarTextoPresenteModal(textoServidor);
		while (condicional) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(btnModalConfirmar)).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(btnLogin)).click();
			condicional = validarTextoPresenteModal(textoServidor);
		}
	}

	public String getTextoServidor() {
		return textoServidor;
	}

	public void realizarLogout() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnLogout)).click();
	}

	public void confirmarResgate() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnConfirmar)).click();
	}

	public void confirmarRegasteModal() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnModalConfirmar)).click();
	}

	public boolean validarTextoPresenteModal(String mensagem) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(modal));
			String dadosPagina = driver.getPageSource();
			if (dadosPagina.contains(mensagem)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

}
