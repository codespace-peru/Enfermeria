package pe.com.codespace.nurse;

/**
 * Created by Carlos on 22/04/2014.
 */
public class Formulas {

    public static double ASCbyHaycock(double dPeso, double dTalla) {
        double resultado;
        double peso = Math.pow(dPeso,0.5378);
        double talla = Math.pow(dTalla,0.3964);
        resultado = (0.024265)*peso*talla;
        resultado = (double) Math.round(resultado*100)/100;
        return resultado;
    }

    public static double ASCbyMollester(double peso, double talla) {
        double resultado;
        resultado = (Math.sqrt(peso*talla))/60;
        resultado = (double) Math.round(resultado*100)/100;
        return resultado;
    }

    public static double PerdidaInsensibleAdultoNormotermo(double peso, double horas) {
        double resultado;
        resultado = (0.5)*peso*horas;
        resultado = (double) Math.round(resultado*100)/100;
        return resultado;
    }

    public static double VelocidadGoteo(double volumen, double horas) {
        double resultado;
        resultado = (volumen/(horas*3));
        resultado = (double) Math.round(resultado);
        return resultado;
    }

    public static double VolumenInfusion(double goteo, double horas) {
        double resultado;
        resultado = goteo*horas*3;
        resultado = (double) Math.round(resultado);
        return resultado;
    }

    public static double TiempoInfusion(double volumen, double goteo) {
        double resultado;
        resultado = volumen/(goteo*3);
        resultado = (double) Math.round(resultado);
        return resultado;
    }

    public static double IMC(double peso, double talla) {
        double resultado;
        double altura = talla/100;
        resultado = peso/(Math.pow(altura,2));
        resultado = (double) Math.round(resultado*100)/100;
        return resultado;
    }

    public static double TestFormula(double x1, double x2, double x3) {
        double resultado;
        resultado = (x1*x2)+x1;
        resultado = (double) Math.round(resultado*100)/100;
        return resultado;
    }

    public static double Regla3Simple(double x1, double x2, double x3) {
        double resultado;
        resultado = (x2*x3)/(x1);
        resultado = (double) Math.round(resultado*100)/100;
        return resultado;
    }

    public static int ConversionSoluciones(double base, double mix, double target, double targetVol) {
        int resultado;
        resultado = (int) ((targetVol*(target-base))/(mix-base));
        resultado = Math.round(resultado);
        return resultado;
    }

    public static double ConversionFarmacoDopamina(double dosis, double peso, double medTotal, double volTotal) {
        double temp;
        double resultado;
        temp = ((dosis*peso)/1000)*60;
        resultado = (temp*volTotal)/medTotal;
        resultado = (double) Math.round(resultado*100)/100;
        return resultado;
    }


}
