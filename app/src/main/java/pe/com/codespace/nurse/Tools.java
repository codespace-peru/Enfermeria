package pe.com.codespace.nurse;


/**
 * Creado por Carlos on 01/03/14.
 */
class Tools {

    public static boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
            return true;
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
    }


   /* public static double FarenheitToCentigrados (double farenheit)
    {
        double centigrados;
        centigrados = (farenheit-32)/1.8;
        centigrados = round(centigrados,2);
        return  centigrados;
    }

    public static double CentigradosToFarenheit (double centigrados)
    {
        double farenheit;
        farenheit = centigrados*1.8 + 32;
        farenheit = round(farenheit,2);
        return  farenheit;
    }*/

    public static double InchesToCentimetros(double inches)
    {
        double centimetros;
        centimetros = inches*2.54;
        centimetros = round(centimetros,2);
        return centimetros;
    }

    public static double LibrasToKilos(double libras)
    {
        double kilos;
        kilos = libras/2.20462262;
        kilos = round(kilos,2);
        return kilos;
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}

