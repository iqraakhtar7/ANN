/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ann;

import java.util.ArrayList;

/**
 *
 * @author IqraAkhtar
 */
public class Node {
   

    double output;
    double error;
    double bias;
    ArrayList<Double> inCommingWeights;

    public Node() {
        this.inCommingWeights = new ArrayList<>();
    }

    public double getOutput() {
        return output;
    }

    public void setOutput(double output) {
        this.output = output;
    }

    public double getError() {
        return error;
    }

    public void setError(double error) {
        this.error = error;
    }

    public double getBias() {
        return bias;
    }

    public void setBias(double bias) {
        this.bias = bias;
    }

    public ArrayList<Double> getInCommingWeights() {
        return inCommingWeights;
    }

    public void setInCommingWeights(ArrayList<Double> inCommingWeights) {
        this.inCommingWeights = inCommingWeights;
    }

    public void addAttribute(double weight) {
        this.inCommingWeights.add(weight);
    }

    public void addHiddenLayerNodeWeight() {

    }

    public void calculateHiddenNodeInput() {

    }

}
