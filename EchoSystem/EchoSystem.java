//Alec McDaugale
//This is an ecosystem that bases hunger and appetite off of size and reduces the population of any given species when
//consumed.
//Still in beta
//Version x.2

import java.math.*;
import java.util.*;


interface Nameable
{

    void setName(String name);
    String getName();

}

abstract class Life implements Nameable
{
    private String name;
    private int physicalSize;
    private int population;
    private int thirst;
    private int thirstCounter;

    public void Life(String name,int size, int population)
    {
        this.setName(name);
        this.setPopulation(population);
        this.setPhysicalSize(size);
        this.resetThirstCounter();
    }

    public void setName(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return name;
    }
    public void setPhysicalSize(int size)
    {
        physicalSize = size;
    }
    public int getPhysicalSize()
    {
        return physicalSize;
    }
    public void setPopulation(int pop)
    {
        population = pop;
    }
    public int getPopulation()
    {
        return population;
    }
    public void setThirst()
    {
        this.thirst = this.getPhysicalSize() * this.getPopulation();
    }
    public int getThirst()
    {
        return thirst;
    }
    public int getSatisfaction()
    {
        return this.getPhysicalSize();
    }
    public void resetThirstCounter()
    {
        thirstCounter = 0;
    }
    public void incrementTirstCounter()
    {
        thirstCounter++;
    }
    public int getThirstCounter()
    {
        return thirstCounter;
    }


    public void printString()
    {
        System.out.println(this.getName() + " Type = " + getClass().getName() + " pop = " + this.getPopulation());
    }


    public void drink(Watter watter)
    {
        int temp = this.getThirst();
        watter.removeWatter(this.getThirst());
        System.out.println("The " + this.getName() + "'s consumed " + temp + " gallons of watter.");
    }
    public void die(int quantity)
    {
        this.setPopulation(this.getPopulation() - quantity);
    }

    abstract public void reproduce();
    public void printReproduction(int temp, Life life)
    {
        System.out.println("The " + this.getName() + " increased in popilation with " +
                (temp - life.getPopulation()) + " new " + this.getClass().getName());
    }

}



class Plant extends Life
{

    public void Plant( String name, int size, int population)
    {
        super.Life(name, size, population);
    }



    @Override
    public void reproduce()
    {
        int temp = this.getPopulation();
        this.setPopulation(this.getPopulation() * 2);
        printReproduction(temp, this);
    }



}


//abstract animal class
abstract class Animal extends Life
{
    private int hunger;
    private int hungerCounter;

    public void Animal(String name, int size, int pop)
    {
        hunger = 0;
        this.resetHungerCounter();
        super.Life(name, size, pop);
    }

    public void setHunger()
    {
        hunger = this.getPhysicalSize() + this.getHunger();
    }
    public int getHunger()
    {
        return hunger;
    }
    public void resetHungerCounter()
    {
        hungerCounter = 0;
    }
    public void incrementHungerCounter()
    {
        hungerCounter++;
    }
    public int getHungerCounter()
    {
        return hungerCounter;
    }

    private int getReproductionRate()
    {
        int reproductionRate = 0;
        switch (this.getPhysicalSize()) {
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

    @Override
    public void reproduce()
    {
        int temp = this.getPopulation();
        int pop = ((this.getPopulation()/2) * this.getReproductionRate()) + this.getPopulation();
        this.setPopulation(pop);
        this.printReproduction(temp, this);
    }

    abstract public void eat(Object O);
    public void printEat(int temp, Life food)
    {
        System.out.println("The " + this.getName() + " consumed " + (temp - food.getPopulation()) + " " + food.getName());
    }

}






//carnivor class
class Carnivore extends Animal
{

    public void Carnivore(String name, int size, int pop)
    {
        super.Animal(name, size, pop);
    }


//    impliment sucess rate
    @Override
    public void eat(Object o)
    {
        this.eat(o);
    }
    public void eat(Animal food)
    {
        int hunger = (this.getHunger() / food.getSatisfaction()) * this.getPopulation();

        if (food.getPopulation() < hunger)
        {
            System.out.println("not enough food");
        }else
        {

            int temp = food.getPopulation();
            food.die((this.getHunger() / food.getSatisfaction()) * this.getPopulation());
            this.printEat(temp, food);
        }
    }
}



class Herbivore extends Animal
{

    public void Herbivore(String name, int size, int pop)
    {
        super.Animal(name, size, pop);
    }


    @Override
    public void eat(Object o)
    {
        this.eat(o);
    }
    public void eat(Plant food)
    {

        int foodPopulation = food.getPopulation();
        int hunger = (this.getHunger()/food.getSatisfaction()) * this.getPopulation();

        if (hunger < foodPopulation)
        {
            food.die(hunger);
            this.printEat(foodPopulation, food);
        }else if (hunger > foodPopulation)
        {
            this.incrementHungerCounter();
            System.out.println(hunger - foodPopulation);
            System.out.println(foodPopulation - hunger);
        }

    }

}


class Watter
{
    private int gallons;

    public void Watter(int gallons)
    {
        this.setWatter(gallons);
    }
    public void setWatter(int gallons)
    {
        this.gallons = gallons;
    }
    public int getWatter()
    {
        return gallons;
    }
    public void rain()
    {
        int temp = this.getWatter();
        setWatter(this.getWatter() + (int)((Math.random() * 20000) + 0));
        System.out.println("It rained " + (this.getWatter() - temp) + " gallons.");
    }
    public void removeWatter(int thirst)
    {
        this.setWatter(this.getWatter() - thirst);

    }
    public void printString()
    {
        System.out.println("Watter = " + gallons + " gallons.");
    }

}

class Time
{
    private int time;
    public void Time()
    {
        time = 0;
        this.getString();
    }
    public void incrementTime()
    {
        time++;
        this.getString();
    }
    public int getTime()
    {
        return time;
    }
    public void getString()
    {
        System.out.println("Day " + (this.getTime() + 1) + "\n");
    }
}


public class EchoSystem
{
    private Time time = new Time();
    private Watter watter = new Watter();

    private Vector<Herbivore> herbivores = new Vector<Herbivore>();
    private Vector<Carnivore> carnivores = new Vector<Carnivore>();
    private Vector<Plant> plants = new Vector<Plant>();

    public void EchoSystem(int gallons)
    {
        time.Time();
        watter.Watter(gallons);

    }
    public void newPlant(String name, int size, int pop)
    {
        Plant plant = new Plant();
        plant.Plant(name, size, pop);
        plants.addElement(plant);
    }
    public void newCarnivore(String name, int size, int pop)
    {
        Carnivore carnivore = new Carnivore();
        carnivore.Carnivore(name, size, pop);
        carnivores.addElement(carnivore);
    }
    public void newHerbivore(String name, int size, int pop)
    {
        Herbivore herbivore = new Herbivore();
        herbivore.Herbivore(name, size, pop);
        herbivores.addElement(herbivore);
    }
    public void animalsEat()
    {
        for (int index=0; index < herbivores.size(); index++)
        {
            herbivores.elementAt(index).setHunger();
            herbivores.elementAt(index).eat(plants.elementAt(0));
        }

        for (int index=0; index < carnivores.size(); index++)
        {
            carnivores.elementAt(index).setHunger();
            carnivores.elementAt(index).eat(herbivores.elementAt(0));
        }
    }
    public void lifeDrink()
    {
        for (int index=0; index < plants.size(); index++)
        {
            plants.elementAt(index).setThirst();
            plants.elementAt(index).drink(watter);
        }
        for (int index=0; index < herbivores.size(); index++)
        {
            herbivores.elementAt(index).setThirst();
            herbivores.elementAt(index).drink(watter);
        }

        for (int index=0; index < carnivores.size(); index++)
        {
            carnivores.elementAt(index).setThirst();
            carnivores.elementAt(index).drink(watter);
        }
    }

    public void printEcho()
    {
        System.out.println(" \n");
        watter.printString();
        for (int index=0; index < plants.size(); index++)
        {
            plants.elementAt(index).printString();
        }
        for (int index=0; index < herbivores.size(); index++)
        {
            herbivores.elementAt(index).printString();
        }

        for (int index=0; index < carnivores.size(); index++)
        {
            carnivores.elementAt(index).printString();
            System.out.println("\n");
        }
    }
    public void reproduceEcho()
    {
        watter.rain();
        for (int index=0; index < plants.size(); index++)
        {
            plants.elementAt(index).reproduce();
        }
        for (int index=0; index < herbivores.size(); index++)
        {
            herbivores.elementAt(index).reproduce();
        }

        for (int index=0; index < carnivores.size(); index++)
        {
            carnivores.elementAt(index).reproduce();
        }
    }
    public void newDay()
    {
        time.incrementTime();
        this.lifeDrink();
        this.animalsEat();
        this.reproduceEcho();
        this.printEcho();

    }
}




class EchoSystemUI
{

    public static void main(String [] args)
    {
        EchoSystem eco = new EchoSystem();

        eco.EchoSystem(20000);
        eco.newPlant("Grass", 1,10000);
        eco.newHerbivore("Goat",3,500);
        eco.newCarnivore("Velociraptor", 5, 500);

        for(int index = 0; index < 10; index++)
        {
            eco.newDay();
        }
        System.exit(0);

    }
}