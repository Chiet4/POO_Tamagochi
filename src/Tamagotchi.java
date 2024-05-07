
import java.util.*;

class Pet{
    private boolean alive;
    private int clean, cleanMax;
    private int energy, energyMax;
    private int hungry, hungryMax;
    private int age, diamonds;


    Pet ( int energy , int hungry, int clean){ // Construtor do codigo
        this.hungryMax = hungry;
        this.cleanMax = clean;
        this.energyMax = energy;

        this.energy = energy;
        this.clean = clean;;
        this.hungry = hungry;

        this.age = 0;
        this.diamonds = 0;
        this.alive = true;
    }

    private boolean testAlive (){ // testar se está vivo;
        if(!this.alive)
            System.out.println("fail: pet esta morto");
        return this.alive;
    }

    public void eat(){ // comer, -1 energia, +4 saciedade, -2 limpeza, +1 idade
        if(!testAlive())
            return;
        setEnergy(getEnergy() -1);
        setHungry(getHungry() + 4);
        setClean(getClean() -2);
        diamonds += 0;
        age++;
    }

    public void play(){ // jogar, -2 energia, -3 limpeza, -1 saciedade, +1 diamante, +1 idade
        if(!testAlive())
            return;

        setEnergy(getEnergy() -2);
        setClean(getClean() -3);
        setHungry(getHungry() -1 );
        diamonds += 1;
        age+=1;
    }

    public void shower(){ // banhar, Max limpeza, -3 energia, -1 saciedade, +2 idade
        if(!testAlive())
            return;

        setClean(getCleanMax());
        setEnergy(getEnergy() -3);
        setHungry(getHungry()-1);
        age += 2;
    }

    public void sleep(){ // dormir, Max energia, -1 saciedade, Ciclos idade (Max energia - energia)
        if(!testAlive()) return;
        if( (getEnergyMax() - 5) < getEnergy() ){
            System.out.println("fail: nao esta com sono");
        }else {
            age+= getEnergyMax() - this.getEnergy();
            setEnergy(getEnergyMax());
            setHungry(getHungry()-1);
        }
    }

    // todos os sets

    public void setEnergy(int valor){
        if ( valor <= 0){
            this.energy = 0;
            System.out.println("fail: pet morreu de fraqueza");
            this.alive = false;
        }else if (valor > getEnergyMax()){
            this.energy = getEnergyMax();
        }else{
            this.energy = valor;
        }
    }

    public void setClean(int valor) {
        if(valor <= 0){
            this.clean = 0;
            System.out.println("fail: pet morreu de sujeira");
            this.alive = false;
        }else if( valor > getCleanMax()){
            this.clean = getCleanMax();
        }else{
            this.clean = valor;
        }
    }

    public void setHungry(int valor) {
        if(valor <= 0){
            this.hungry = 0;
            System.out.println("fail: pet morreu de fome");
            this.alive = false;
        }
        else if(valor > getHungryMax()){
            this.hungry = getHungryMax();
        }else{
            this.hungry = valor;
        }
    }



    // Todos os gets
    int getClean(){
        return clean;
    }
    int getEnergy(){
        return energy;
    }
    int getHungry(){
        return hungry;
    }
    int getCleanMax(){
        return cleanMax;
    }
    int getEnergyMax(){
        return energyMax;
    }
    int getHungryMax(){
        return hungryMax;
    }


    public String toString(){
        String ss= "";
                ss  +=  "E:" + this.energy + "/" + this.energyMax + ", "
                    +   "S:" + this.hungry + "/" + this.hungryMax + ", "
                    +   "L:" + this.clean + "/" + this.cleanMax + ", "
                    +   "D:" + this.diamonds + ", " + "I:" + this.age;
        return ss;

    }

}


public class Tamagotchi{
    public static void main(String [] Args){
        Pet pet = new Pet(0, 0,0); // inicializando pet;

        while (true){
            String linha = input();
            write("$" + linha);
            String[] args = linha.split(" "); //  regex para trata dado de entrada;

            if(args[0].equals("end")) {break;                               }
            else if (args[0].equals("show")) {write(pet.toString());         }
            else if (args[0].equals("init")) {pet = new Pet((int)number(args[1]), (int)number(args[2]), (int)number(args[3]));  }
            else if (args[0].equals("play")) {pet.play(); }
            else if (args[0].equals("eat"))  {pet.eat();}
            else if (args[0].equals("sleep")){pet.sleep();}
            else if (args[0].equals("shower")){pet.shower();}
            else {write("fail: comando invalido");}
        }
    }
    // Formatando entrada de dados, leitura continua;
    static Scanner l = new Scanner(System.in);
    static String input()              { return l.nextLine();              }
    static double number(String valor) { return Double.parseDouble(valor); }
    static void write(String valor)    { System.out.println(valor);        }


}