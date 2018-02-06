import java.util.*;

abstract class Animal
{

    private int physiscalSize;
    private int population;
    //private int age;
    private double hunger;


//concreate mehods
    public void setPhysiscalSize(int size)
    {
        physiscalSize = size;
    }
    public int getPhysiscalSize()
    {
        return physiscalSize;
    }

    public void setPopulation(int pop)
    {
        population = pop;
    }
    public int getPopulation()
    {
        return population;
    }

   /* private void setAge(int days)
    {
        age = days;
    }
    public int getAge()
    {
        return age;
    }*/

    private void setHunger()
    {
        hunger = this.getPhysiscalSize() * .25;
    }
    public double getHunger()
    {
        return hunger;
    }
    private int getReproductionRate()
    {
        int reproductionRate = 0;
        switch (physiscalSize) {
            case 1:
                reproductionRate = 12;
                break;

            case 2:
                reproductionRate = 10;
                break;

            case 3:
                reproductionRate = 8;
                break;
            case 4:
                reproductionRate = 5;
                break;
            case 5:
                reproductionRate = 3;
                break;
            case 6:
                reproductionRate = 2;
                break;
            case 7:
            case 8:
            case 9:
            case 10:
                reproductionRate = 1;
                break;
            default:
                System.out.println("Error.getReproductionRate(); PhysicalSize not valid int.");
        }

        return reproductionRate;
    }

    public void reproduce()
    {

        int pop = (this.getPopulation()/2) * this.getReproductionRate();
        this.setPopulation(pop);
    }

    public void die(int rip)
    {
        int pop = this.getPopulation() - rip;
        this.setPopulation(pop);
    }

    //abstract methods
    abstract public void eat();
    abstract public String foodType();


}


class Carnivore extends Animal
{

    public void Carnivore(int size, int pop)
    {
        this.setPhysiscalSize(size);
        this.setPopulation(pop);

    }

    /*public String foodType()
    {
        return "Animal";
    }*/

    public void eat(Animal food)
    {
        food.die(getHunger() * getPopulation());
    }
}


class Herbivore extends Animal
{

    public void Herbivore(int size, int pop)
    {
        super.setPhysiscalSize(size);
        super.setPopulation(pop);
    }


    public void eat()
    {

    }
}



//this class makes me realize that plant life also has similaritys to animal life in this situation
//maybe an abstract class named life would be an efective way to create all fourms of life with
class Plant
{
    private int population;
    private int satisfaction;

    public void setPopulation(int pop)
    {
        population = pop;
    }
    public int getPopulation()
    {
        return population;
    }
    public void setSatisfaction(int satisfy)
    {
        satisfaction = satisfy;
    }
    public int getSatisfaction()
    {
        return satisfaction;
    }

    public void reproduce()
    {
        this.population = this.getPopulation() * 2;
    }
    public void die(int quantity)
    {
        this.setPopulation(this.getPopulation() - quantity);
    }
}

//gona extend to increase plant variaty
class PlantFood extends Plant
{

}

//life needs water to survive
class Watter
{

}

public class EchoSystem
{
    public static void main(String [] args)
    {
        Herbivore cat = new Herbivore();
        Carnivore lion = new Carnivore();

        cat.Herbivore(3,50);
        lion.Carnivore(5, 500);

        System.out.println(" we have " + cat.getPopulation() + " cats. /n"
        + "And " + lion.getPopulation() + " lions");

        lion.eat(cat);
        System.out.println(" we have " + cat.getPopulation() + " cats. /n"
                + "And " + lion.getPopulation() + " lions");

    }

    public void plantFood()
    {

    }
    public void animalFood()
    {
        
    }
}