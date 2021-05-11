package com.controller.exceptions;

public class CartNotFoundException extends Exception{
    private static  final String MESSAGE="Cart not found!";

    public CartNotFoundException(){super(MESSAGE);}
}


