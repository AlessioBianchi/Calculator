package model;

import java.util.ArrayList;

import observer.Observer;
import observer.Subject;

public class CalculatorModel implements Subject {
    private ArrayList<Observer> observers;

    private String number;
    private double previousNumber;
    private String currentTypeOfOperation;
    private int counter;

    public CalculatorModel () {
        observers = new ArrayList<>();
        number = "0";
        previousNumber = 0;
        currentTypeOfOperation = "";
        counter = 0;
    }

    public void addDigit (String digit) {
    	if (number.equals("0")) {
    		setNumber(digit);
    	} else {
    		setNumber(getNumber() + digit);
    	}
    }

    public Double makeOperation () {
        Double result = 0.0;
        Double n = Double.valueOf(this.getNumber());
        switch (this.currentTypeOfOperation) {
            case "+":
                result = this.getPreviousNumber() + n;
                break;
            case "-":
                result = this.getPreviousNumber() - n;
                break;
            case "x":
                result = this.getPreviousNumber() * n;
                break;
            case "/":
                result = this.getPreviousNumber() / n;
                break;
        }
        return result;
    }

    public String getNumber () {
        return number;
    }

    public void setNumber (String number) {
        this.number = number;
        notifyObservers();
    }

    public String getCurrentTypeOfOperation() {
        return currentTypeOfOperation;
    }

    public void setCurrentTypeOfOperation(String currentTypeOfOperation) {
        this.currentTypeOfOperation = currentTypeOfOperation;
    }

    public double getPreviousNumber() {
        return previousNumber;
    }

    public void setPreviousNumber(String previousNumber) {
        this.previousNumber = Double.valueOf(previousNumber);
    }
    
    public int getCounter() {
    	return counter;
    }
    
    public void incCounter() {
    	this.counter++;
    }

    @Override
    public void registerObserver (Observer o) {
        observers.add(o);
    }

    @Override
    public void unregisterObserver (Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        this.observers.forEach(Observer::update);
    }
}
