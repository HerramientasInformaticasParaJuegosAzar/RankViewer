/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rankMatrix;

import card.Play;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vjacynycz
 */
public class RankMatrix {

    private static final String RANKFILE = "ranks.txt";

    private class matrixCell {

        private double cost;

        private Play p;

        public Play getP() {
            return p;
        }

        public double getCost() {
            return cost;
        }

        public void setP(Play p) {
            this.p = p;
        }

        public void setCost(double cost) {
            this.cost = cost;
        }

        public matrixCell() {
            this.p=Play._98s;
            this.cost=0.0;
        }

        @Override
        public String toString(){
            return this.p+"-"+this.cost;
        }
        
    }

    private matrixCell[] cells;

    public RankMatrix() {
        this.cells = new matrixCell[169];
    }

    public void parse() {
        Scanner sc = null;
        try {
            File f = new File(RANKFILE);
            sc = new Scanner(f);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RankMatrix.class.getName()).log(Level.SEVERE, null, ex);
        }
        String line = sc.next();
        String[] values = line.split(";");
        if (values.length != 169) {
            System.out.println("Error en el formato de texto");
        } else {
            int i = 0;
            for (Play play : Play.values()) {
                Double d = Double.parseDouble(values[i]);
                this.cells[i]=new matrixCell();
                this.cells[i].setP(play);
                this.cells[i].setCost(d);
                i++;
            }
            quickSort(cells, 0, 168);
            System.out.println(Arrays.toString(cells));
        }
    }

    
    
    /*http://www.programcreek.com/2012/11/quicksort-array-in-java/*/
    
    public static void quickSort(matrixCell[] arr, int low, int high) {

        if (arr == null || arr.length == 0) {
            return;
        }

        if (low >= high) {
            return;
        }

        //pick the pivot
        int middle = low + (high - low) / 2;
        Double pivot = arr[middle].getCost();

        //make left < pivot and right > pivot
        int i = low, j = high;
        while (i <= j) {
            while (arr[i].getCost() < pivot) {
                i++;
            }

            while (arr[j].getCost() > pivot) {
                j--;
            }

            if (i <= j) {
                double temp = arr[i].getCost();
                arr[i] = arr[j];
                arr[j].setCost(temp);
                i++;
                j--;
            }
        }

        //recursively sort two sub parts
        if (low < j) {
            quickSort(arr, low, j);
        }

        if (high > i) {
            quickSort(arr, i, high);
        }
    }

    public static void main(String[] args) {
        RankMatrix mat = new RankMatrix();
        mat.parse();
    }
}
