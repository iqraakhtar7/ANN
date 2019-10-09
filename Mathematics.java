/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ann;

import java.text.DecimalFormat;
import java.util.Random;

/**
 *
 * @author IqraAkhtar
 */
public class Mathematics {

    public static double GenerateRandomDouble(double min, double max) {
  Random r = new Random();
double randomValue = min + (max - min) * r.nextDouble();
return randomValue;
 
    }


}
