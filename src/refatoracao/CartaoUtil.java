package refatoracao;

import java.text.*;
import java.util.*;

public class CartaoUtil {

    private CartaoDeCredito cartaoCredito = new CartaoDeCredito();
    private Date dataValidade = null;
    private boolean validadeOK;
    private boolean formatoOK;

    private boolean formatData(String validade) {
// ----- VALIDADE -----
        try {
            dataValidade = new SimpleDateFormat("MM/yyyy").parse(validade);
            return cartaoCredito.isOK();
        } catch (Exception e) {
            return cartaoCredito.isERRO();
        }
    }

    private boolean validacaoMesEAno() {
        Calendar calValidade = new GregorianCalendar();
        calValidade.setTime(dataValidade);
// apenas mês e ano são utilizados na validação
        Calendar calTemp = new GregorianCalendar();
        Calendar calHoje = (GregorianCalendar) calValidade.clone();
        calHoje.set(Calendar.MONTH, calTemp.get(Calendar.MONTH));
        calHoje.set(Calendar.YEAR, calTemp.get(Calendar.YEAR));
        return validadeOK = calHoje.before(calValidade);
    }

    private String validarDigitosNãoNumericos(String numero) {
        String formatado = "";
        // remove caracteres não-numéricos
        for (int i = 0; i < numero.length(); i++) {
            char c = numero.charAt(i);
            if (Character.isDigit(c)) {
                formatado += c;
            }
        }
        return formatado;
    }

    public boolean validar(int bandeira, String numero, String validade) {
        formatData(validade);
        if (!validacaoMesEAno()) {
            return cartaoCredito.isERRO();
        } else {
            TipoBandeiraCartaoCredito(bandeira, validarDigitosNãoNumericos(numero));
            if (!formatoOK) {
                return cartaoCredito.isERRO();
            } else {
                return calculoCartão(validarDigitosNãoNumericos(numero));
            }
        }
    }

    private boolean calculoCartão(String formatado) {
        int soma = 0;
        int digito = 0;
        int somafim = 0;
        boolean multiplica = false;

        for (int i = formatado.length() - 1; i >= 0; i--) {
            digito = Integer.parseInt(formatado.substring(i, i + 1));
            if (multiplica) {
                somafim = digito * 2;
                if (somafim > 9) {
                    somafim -= 9;
                }
            } else {
                somafim = digito;
            }
            soma += somafim;
            multiplica = !multiplica;
        }
        int resto = soma % 10;
        if (resto == 0) {
            return cartaoCredito.isOK();
        } else {
            return cartaoCredito.isERRO();
        }
    }

    private void TipoBandeiraCartaoCredito(int bandeira, String formatado) {
        int tamanho = formatado.length();
        boolean comecoDigito = formatado.startsWith("");

        if ((bandeira == cartaoCredito.getVISA() && comecoDigito) && (tamanho == 13 || tamanho == 16)) {
            formatoOK = true;
        } else if ((bandeira == cartaoCredito.getMASTERCARD() && comecoDigito) && tamanho == 16) {
            formatoOK = true;
        } else if ((bandeira == cartaoCredito.getAMEX() && comecoDigito) && tamanho == 15) {
            formatoOK = true;
        } else if ((bandeira == cartaoCredito.getDINERS() && comecoDigito) && tamanho == 14) {
            formatoOK = true;
        }
    }
}
