/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package refatoracao;

/**
 *
 * @author Bruno
 */
public class CartaoDeCredito extends TiposCartoesDeCredito {

    public boolean OK = true;
    public boolean ERRO = false;

    public CartaoDeCredito() {
        super();
    }

    public boolean isOK() {
        return OK;
    }

    public boolean isERRO() {
        return ERRO;
    }

    public int getVISA() {
        return VISA;
    }

    public int getMASTERCARD() {
        return MASTERCARD;
    }

    public int getAMEX() {
        return AMEX;
    }

    public int getDINERS() {
        return DINERS;
    }
    
    
}
