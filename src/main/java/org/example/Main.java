package org.example;

import java.util.Hashtable;
import java.util.Scanner;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        Vector<Paint> paints = new Vector<>();

        Scanner reader = new Scanner(System.in);
        System.out.println("Number of different paint colours: ");
        String input = reader.next();
        int paintsN = Integer.parseInt(input);

        for (int a = 0; a < paintsN; a++){
            System.out.println("What is the colour of paint number " + a + "?");
            String colour = reader.next();
            float area = calcPaintArea(colour);
            float litres = area / 13000000;
            float[] costAndTins = calcPaintCost(litres);
            paints.add(new Paint(colour, area, litres,costAndTins[0],(int)costAndTins[1], (int)costAndTins[2]));
        }

        float totalCost = 0;
        for (Paint p : paints) {
            totalCost += p.cost;
        }

        for (Paint p : paints){
            System.out.println("For the " + p.colour + " walls and ceilings, you will need to cover " + (p.area / 1000000) + "m^2. For this you will need: ");
            System.out.println(p.largeN + " large tins and " + p.mediumN + " medium tins, costing £" + p.cost + ".");
        }

        if (paintsN > 1){
            System.out.println("This comes to a total cost of £" + totalCost + ".");
        }
        // paint coverage 13m^2 per 1 litre of paint
        // paint costs 2.5l = £45, 5l = £70
    }

    private static class Paint{
        public String colour;
        public float area;
        public float litres;
        public float cost;
        public int mediumN;
        public int largeN;
        public Paint(String colour, float area, float litres, float cost, int mediumN, int largeN){
            this.colour = colour;
            this.area = area;
            this.litres = litres;
            this.cost = cost;
            this.mediumN = mediumN;
            this.largeN = largeN;
        }
    }

    private static float[] calcPaintCost(float litres){
        // paint costs med = 2.5l = £45, large = 5l = £70
        float volumeLarge = 5f;
        float volumeMedium = 2.5f;
        float costLarge = 70f;
        float costMedium = 45f;

        int mediumN = 0;
        int largeN = (int)Math.floor(litres / volumeLarge);
        litres -= largeN * volumeLarge;

        if(litres > volumeMedium){
            largeN++;
        }
        else{
            mediumN++;
        }
        float cost = (largeN * costLarge + mediumN * costMedium);
        return new float[]{cost, mediumN, largeN};

    }
    private static float calcPaintArea(String colour){
        Scanner reader = new Scanner(System.in);
        System.out.println("Number of " + colour + " walls/ceilings: ");
        String input = reader.next();
        int wallsN = Integer.parseInt(input);
        float wallsArea = 0;

        for (int i = 0; i < wallsN; i++){
            //get height of the wall
            System.out.println("For " + colour + " wall/ceiling " + i + ", what is the height?");
            input = reader.next();
            float h = Integer.parseInt(input);
            //get width of the wall
            System.out.println("For " + colour + " wall/ceiling " + i + ", what is the width?");
            input = reader.next();
            float w = Integer.parseInt(input);
            //sections to not paint
            System.out.println("For " + colour + " wall/ceiling " + i + ", How many door and window sections that do not need to be painted?");
            input = reader.next();
            int doorsN = Integer.parseInt(input);
            float doorsArea = 0;

            for (int j = 0; j < doorsN; j++){
                //get the height of the door
                System.out.println("For " + colour + " wall/ceiling " + i + ", door/window " + j + ", what is the height?");
                input = reader.next();
                float doorH = Integer.parseInt(input);
                //get width of the door
                System.out.println("For " + colour + " wall/ceiling " + i + ", door/window " + j + ", what is the width?");
                input = reader.next();
                float doorW = Integer.parseInt(input);

                doorsArea += doorH * doorW;
            }

            //get the number of coats
            System.out.println("For " + colour + " wall/ceiling " + i + ", How many coats?");
            input = reader.next();
            int c = Integer.parseInt(input);

            wallsArea += ((h * w  - doorsArea) * c);
        }
        return wallsArea;
    }
}