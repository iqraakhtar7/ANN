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
public class NeuralNetwork {
    
    double TargetOutput;
    ArrayList<Node> input_Layer;
    ArrayList<Node> hidden_Layer;
    ArrayList<Node> output_Layer;
    private double learningRate;
    private double error;


    public void setTargetOutput(double TargetOutput) {
        this.TargetOutput = TargetOutput;
    }

    public NeuralNetwork() {

    }

    public double getError() {
        return error;
    }

    public void setError(double error) {
        this.error = error;
    }

    public double getLearningRate() {
        return learningRate;
    }

    public void setLearningRate(double learningRate) {
        this.learningRate = learningRate;
    }

    //only for hidden and output layers
    public void initializeNodesWeights(ArrayList<Node> previousLayer, ArrayList<Node> currentLayer) {

        for (int i = 0; i < currentLayer.size(); i++) {

            for (int j = 0; j < previousLayer.size(); j++) {

                //setting hidden layer weight
                currentLayer.get(i).getInCommingWeights().add(Mathematics.GenerateRandomDouble(-0.5, 0.5));
                // assigning random weight to every hidden node
            }
        }
    }

    public void calculateNodesOutput(ArrayList<Node> previousLayer, ArrayList<Node> currentLayer) {

        //for each current layer node
        for (int i = 0; i < currentLayer.size(); i++) {

            double x = 0;
            for (int j = 0; j < previousLayer.size(); j++) {
                //bias
                x = x + previousLayer.get(j).getOutput() * currentLayer.get(i).getInCommingWeights().get(j) + currentLayer.get(i).getBias();

//               double output=1/(1+(Math.exp(-x)));
            }
            // output= 1/ 1 + e^ -x
       // currentLayer.get(i).setOutput(1 / (1 + (Math.exp(-(x + currentLayer.get(i).getBias())))));
           currentLayer.get(i).setOutput(1 / (1 + (Math.exp(-(x)))));
        }
    }

    public void calculateOutputLayerError(double targetOutput, double networkOutput) {

    
        double error = networkOutput * (1 - networkOutput) * (targetOutput - networkOutput);
//        System.out.println(error);
        this.output_Layer.get(0).setError(error);
        this.setError(error);
    }

    //for input and hidden layers
    public void calculateHiddenLayerErrorsAndUpdateWeights() {

        Node outputNode = output_Layer.get(0);

        //for each hidden layer node
        for (int i = 0; i < hidden_Layer.size(); i++) {

            Node hiddenNode = hidden_Layer.get(i);

            hiddenNode.setError(hiddenNode.getOutput() * (1 - hiddenNode.getOutput())
                    * outputNode.getError() * outputNode.getInCommingWeights().get(i));

            double w = (this.learningRate) * output_Layer.get(0).getError() * hiddenNode.getOutput();
            //updating weights
            outputNode.getInCommingWeights().set(i, outputNode.getInCommingWeights().get(i) + w);

            //updating bias
            double deltaTheta = this.learningRate * output_Layer.get(0).getError();
            outputNode.setBias(outputNode.getBias() + deltaTheta);

        }
    }


    
    public ArrayList<Node> getInputLayer() {
        return input_Layer;
    }

    public void setInputLayer(ArrayList<Node> input_Layer) {
        this.input_Layer = input_Layer;
    }

    public ArrayList<Node> getHiddenLayer() {
        return hidden_Layer;
    }

    public void setHiddenLayer(ArrayList<Node> hidden_Layer) {
        this.hidden_Layer = hidden_Layer;
    }

    public ArrayList<Node> getOutputLayer() {
        return output_Layer;
    }

    public void setOutputLayer(ArrayList<Node> output_Layer) {
        this.output_Layer = output_Layer;
    }

    public String toString() {

        return ""
//                + "inputx1 " + input_Layer.get(0).getInCommingWeights().get(0) + " \n"
//                +"inputx2 " + input_Layer.get(1).getInCommingWeights().get(0) + " \n"
//                +"inputx3 " + input_Layer.get(2).getInCommingWeights().get(0) + " \n"
//                 +"inputy " + input_Layer.get(3).getInCommingWeights().get(0) + " \n"
                +"w14 " + hidden_Layer.get(0).getInCommingWeights().get(0) + " \n"
                + "w24 " + hidden_Layer.get(0).getInCommingWeights().get(1) + " \n"
                + "w34 " + hidden_Layer.get(0).getInCommingWeights().get(2) + " \n"
                + "w15 " + hidden_Layer.get(1).getInCommingWeights().get(0) + " \n"
                + "w25 " + hidden_Layer.get(1).getInCommingWeights().get(1) + " \n"
                + "w35 " + hidden_Layer.get(1).getInCommingWeights().get(2) + " \n"
                + "w46 " + output_Layer.get(0).getInCommingWeights().get(0) + " \n"
                + "w56 " + output_Layer.get(0).getInCommingWeights().get(1) + " \n"
                + "bias 4 " + hidden_Layer.get(0).getBias() + "\n"
                + "bias 5 " + hidden_Layer.get(1).getBias() + "\n"
                + "bias 6 " + output_Layer.get(0).getBias() + "\n"
                + "Learning Rate " + this.getLearningRate() + "\n"
                + "Output " + output_Layer.get(0).getOutput() + "\n"
             //   + "Target Output :" +TargetOutput+"\n"
                + "Error " + this.getError();
    }
     public String tooString() {

        return ""
//             
                +  + output_Layer.get(0).getOutput();
             //   + "Target Output :" +TargetOutput+"\n"
            //    + "Error " + this.getError();
    }
     
}

//initial values will also be stored in inComming wights array



