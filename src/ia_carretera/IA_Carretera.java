package ia_carretera;

import java.lang.Math;
import java.util.Random;

public class IA_Carretera {
    public static void main(String[] args) {
        //int caminos = (int)(Math.random()*100+1);
        int caminos = 3;
        int secciones = (int)(Math.random()*100+1);
        float[] SUITS = new float[3];
        float[][][]  matriz = new float[caminos][secciones][3];
        for (float[][] fs : matriz) {
            for (float[] f : fs) {
                generar(fs);
            }
        }
    }
    public static void generar(float[][] seccion){
        try{
            
            System.out.println(seccion);
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
