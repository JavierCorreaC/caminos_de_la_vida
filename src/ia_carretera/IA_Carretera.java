package ia_carretera;

import java.lang.Math;
import java.util.Arrays;
import java.util.Random;

public class IA_Carretera {
    static int caminos = 3;
    static float[] suma_total= new float[caminos];
    public static void main(String[] args) {
        //int caminos = (int)(Math.random()*100+1);
        int contador = 0;
        int secciones = (int)(Math.random()*100+1);
        float[] SUITS = new float[3];
        float[][][]  matriz = new float[caminos][secciones][6];
        for (float[][] fs : matriz) {
            for (float[] f : fs) {
                generar(f);
                analizar(f, contador);
                contador++;
            }
        }
        imprimir(matriz);
    }
    public static void generar(float[] seccion){
        /*
        0   peso total
        1   complejidad
        2   tiempo
        3   longitud
        4   tipo
        5   estatus
        */
        try{
            seccion[1]=(float) (Math.random()*100+1);
            seccion[2]=(float) (Math.random()*3600+1);
            seccion[3]=(float) (Math.random()*90000+1);
            seccion[4]=(float) ((Math.random()*4+1)*25);
            seccion[5]=(float) (Math.random()*100+1);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public static void analizar(float[] seccion, int contador){
        try{
            seccion[0]=(seccion[1]+(100/3600*seccion[2]+(100/90000*seccion[3])+seccion[4]+seccion[5]));
            suma_total[contador]=(suma_total[contador]+seccion[0]);
        }catch(Exception e){
            
        }
    }
    public static void imprimir(float[][][] matriz){
        int cont_camino=0, cont_seccion=0, camino=0;
        float menor=1000000;
        for (float[][] fs : matriz) {
            System.out.println("Camino "+cont_camino+":");
            for (float[] f : fs) {
                System.out.println("    Sección "+cont_seccion+":");
                for (float g : f) {
                    System.out.println("        "+String.valueOf(g));
                }
                cont_seccion++;
            }
            System.out.println("Suma total: "+suma_total[cont_camino]);
            if(suma_total[cont_camino]<menor){
                menor=suma_total[cont_camino];
                camino=cont_camino;
            }
            cont_camino++;
            cont_seccion=0;
        }
        System.out.println("El mejor camino para escoger es : "+(camino+1));
    }
}
