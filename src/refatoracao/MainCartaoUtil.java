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
public class MainCartaoUtil {
    public static void main(String[] args) {
        CartaoUtil cu = new CartaoUtil();
        System.out.println(cu.validar(1, "4551831019701393", "10/2029"));
    }
}
