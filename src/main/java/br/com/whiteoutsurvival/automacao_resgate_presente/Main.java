package br.com.whiteoutsurvival.automacao_resgate_presente;

import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Enum.List.UsuariosServer;
import PageObject.PageWhiteoutSurvival;
import Services.CalcResgate;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Main {
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();

		// Inicia o navegador Chrome
		WebDriver driver = new ChromeDriver();

		// Acessa uma URL
		driver.get("https://wos-giftcode.centurygame.com/");

		driver.manage().window().maximize();

		String giftCode = "JPX100K";

		PageWhiteoutSurvival pwos = new PageWhiteoutSurvival(driver);
		CalcResgate calc = new CalcResgate();

		UsuariosServer usuariosServer = UsuariosServer.Users341;

		for (String user : usuariosServer.getUsers()) {

			pwos.preencherFormularioWOS(user, giftCode);
			pwos.realizarLogin();
			pwos.confirmarResgate();

			if (pwos.validarTextoPresenteModal(pwos.getTextoPositivo())) {
				System.out.println("Resgate realizado para o usuário: "+user);
				calc.setQtdUserResgatado(calc.getQtdUserNaoResgatado() + 1);
			} else if (pwos.validarTextoPresenteModal(pwos.getTextoNegativo())) {
				System.out.println("Resgate já realizado pelo usuário: " +user);
				calc.setQtdUserNaoResgatado(calc.getQtdUserResgatado() + 1);
			} else {
				System.out.println("Ocorreu algum erro. Tentando novamente...");
				pwos.confirmarRegasteModal();
				pwos.realizarLogout();
				pwos.preencherFormularioWOS(user, giftCode);
				pwos.realizarLogin();
				pwos.confirmarResgate();
				if (pwos.validarTextoPresenteModal(pwos.getTextoPositivo())) {
					System.out.println("Resgate realizado para o usuário: "+user);
					calc.setQtdUserResgatado(calc.getQtdUserNaoResgatado() + 1);
				} else if (pwos.validarTextoPresenteModal(pwos.getTextoNegativo())) {
					System.out.println("Resgate já realizado pelo usuário: " +user);
					calc.setQtdUserNaoResgatado(calc.getQtdUserResgatado() + 1);
				} else {
					System.out.println("Não foi possível realizar o resgaste para o usuário: "+user);
					calc.setUserComErroResgate(Arrays.asList(user));
				}
			}
			pwos.confirmarRegasteModal();
			pwos.realizarLogout();
		}
		System.out.println("Porcentagem de usuários que já realizaram o resgate: "+calc.getPorcentagemResgatado());

		System.out.println("Porcentagem de usuários que não realizaram o resgate antes do robo: "+calc.getPorcentagemNaoResgatado());

		// Fecha o navegador
        driver.quit();
	}
}
