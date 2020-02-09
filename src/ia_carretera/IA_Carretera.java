package ia_carretera;
class IA_Carretera {
    /*
    static class carrito{
        int velocidad=10;
        int calidad=10;
        int estado;
        //llantas
            //tipo
            //estado
            //peso
        
        int llantas=10;
        public void aleatorio(){
            this.calidad=(int)(Math.random()*100+1);
        }
        public void definir(){
            
        }
    }*/
    //String x=JOptionPane.showInputDialog("Seleccione el tipo de coche\n1-Default\n2-Aleatorio\n3-Definir");
    //int opcion=Integer.parseInt(x);
    /*
    Carrito
        0 velocidad
        1 calidad
        2 estado
        llantas
            3 tipo
            4 estado
            5 hora salida
    */
    static int caminos = 3;
    static boolean fin=false;
    static float[] suma_total= new float[caminos];
    public static void main(String[] args) {
        int contador = 0;
        int cont=1;
        int x1=(int)(Math.random()*100+51);
        int x2=(int)(Math.random()*100+1);
        int x3=(int)(Math.random()*100+1);
        int x4=(int)(Math.random()*3+1);
        int x5=(int)(Math.random()*100+1);
        int x6=(int)((Math.random()*24+1)*(60));
        //carrito carrito = new carrito();
        //int caminos = (int)(Math.random()*100+1);
        int secciones = (int)(Math.random()*100+1);
        float[][] carrito = new float[3][6];
        for (float[] fs : carrito) {
            fs[0]=x1;
            fs[1]=x2;
            fs[2]=x3;
            fs[3]=x4;
            fs[4]=x5;
            fs[5]=x6;
            //carrito[5]=(int)(Math.random()*100+1);
            System.out.println("Coche "+cont);
            System.out.println("Hora de salida: "+((int)(fs[5]/60))+":"+((fs[5]%60)));
            System.out.println("    Velocidad: "+fs[0]);
            System.out.println("    Calidad: "+fs[1]);
            System.out.println("    Estado: "+fs[2]);
            System.out.println("    Llantas: ");
            System.out.println("        Tipo: "+fs[3]);
            System.out.println("        Estado: "+fs[4]);
            cont++;
        }
        int i=0;
        float[][][]  matriz = new float[caminos][secciones][6];
        for (float[][] fs : matriz) {
            for (float[] f : fs) {
                generar(f);
                eventos();
                analizar(f, contador);
                generarTiempo(f,carrito[i],i);
            }
            contador++;
            fin=false;
            i++;
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
        6 :data set next(array)
        */
        try{
            seccion[1]=(int) (Math.random()*100+1);
            seccion[3]=(int) (Math.random()*90000+1);
            seccion[4]=(int) ((Math.random()*4+1))*25;
            seccion[5]=(int) (Math.random()*100+1);
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
            System.out.println("Camino "+(cont_camino+1)+":");
            for (float[] f : fs) {
                if(f[2]!=0){
                    System.out.println("    Sección "+cont_seccion+":");
                    System.out.println("        Peso total: "+f[0]);
                    System.out.println("        Complejidad: "+f[1]);
                    System.out.println("        Tiempo: "+f[2]);
                    System.out.println("        Longitud: "+f[3]);
                    System.out.println("        Tipo: "+f[4]);
                    System.out.println("        Estado: "+f[5]);
                    cont_seccion++;
                }
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
    public static void generarTiempo(float[] seccion, float[] carrito,int i) {
        /*
        0   peso total
        *1   complejidad
        2   tiempo
        *3   longitud
        *4   tipo
        *5   estado
        Carrito
        *0 velocidad
        1 calidad
        2 estado
        llantas
            3 tipo
            4 estado
            5 peso
        */
        
        //COndicion coche
        if(fin==false){
            if(carrito[2]>0&&carrito[4]>0){
                try {
                    double km=(double)(seccion[3]/1000);
                    float daño=0;
                    float estadoAuto=carrito[2];
                    int velocidad=(int)(carrito[0]);
                    //Tipo
                    switch((int)seccion[4]){
                        case 25:
                            switch((int)(carrito[3])){
                                case 1:
                                    daño=(float)(km*-.1);
                                    carrito[4]-=daño;
                                    break;
                                case 2:
                                    daño=(float)(km*-.1);
                                    carrito[4]-=daño;
                                    break;
                                case 3:
                                    daño=(float)(km*-.1);
                                    carrito[4]-=daño;
                                    velocidad=(int)(velocidad*.9);
                                    break;
                            }
                            carrito[2]-=(km*.1);
                            break;
                        case 50:
                            velocidad=(int)(velocidad*.95);
                            switch((int)(carrito[3])){
                                case 1:
                                    daño=(float)(km*-.3);
                                    carrito[4]-=daño;
                                    velocidad=(int)(velocidad*.95);
                                    break;
                                case 2:
                                    daño=(float)(km*-.2);
                                    carrito[4]-=daño;
                                    velocidad=(int)(velocidad*.95);
                                    break;
                                case 3:
                                    daño=(float)(km*-.2);
                                    carrito[4]-=daño;
                                    velocidad=(int)(velocidad*.92);
                                    break;
                            }
                            carrito[2]-=(km*.2);
                            break;
                        case 75:
                            velocidad=(int)(velocidad*.90);
                            switch((int)(carrito[3])){
                                case 1:
                                    daño=(float)(km*-.4);
                                    carrito[4]-=daño;
                                    velocidad=(int)(velocidad*.9);
                                    break;
                                case 2:
                                    daño=(float)(km*-.3);
                                    carrito[4]-=daño;
                                    velocidad=(int)(velocidad*.93);
                                    break;
                                case 3:
                                    daño=(float)(km*-.3);
                                    carrito[4]-=daño;
                                    velocidad=(int)(velocidad*.95);
                                    break;
                            }
                            carrito[2]-=(km*.35);
                            break;
                        case 100:
                            velocidad=(int)(velocidad*.85);
                            switch((int)(carrito[3])){
                                case 1:
                                    daño=(float)(km*-.5);
                                    carrito[4]-=daño;
                                    velocidad=(int)(velocidad*.85);
                                    break;
                                case 2:
                                    daño=(float)(km*-.3);
                                    carrito[4]-=daño;
                                    velocidad=(int)(velocidad*.9);
                                    break;
                                case 3:
                                    daño=(float)(km*-.1);
                                    carrito[4]-=daño;
                                    velocidad=(int)(velocidad*.95);
                                    break;
                            }
                            carrito[2]-=(km*.5);
                            break;
                    }
                    //Daño coche estado
                    carrito[4]-=(km*((101-seccion[5])/200));
                    //Estado
                    velocidad=(int)(velocidad*(100-(seccion[5]/5))/100);
                    //complejidad
                    velocidad=(int)(velocidad*(100-(seccion[1]/6))/100);
                    //tiempo
                    seccion[2]=(seccion[3]/(velocidad*1000));

                } catch (Exception e) {
                    System.out.println("Algo paso cuate");
                }
            }else{
                System.out.println("Oh no, parece que el auto exploto en la seccion"+i);
                fin=true;
            }
        }
    }
    static void eventos(){
        try {
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}