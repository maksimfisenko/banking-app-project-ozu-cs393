# Banking Application Backend Project

## Overview
This is the backend application for a banking app built using Java Spring. The project is created for a CS 393 course (Ozyegin University, Fall 2023).

## Project Description
[PDF File with Project Description](https://github.com/maksimfisenko/banking-app-project-ozu-cs393/blob/main/Project%20Description.pdf)

## Web Pages Description

### Home
Main Page with information about current user (hard codded into the system).

### Transfer Money
This page represents transfer money service. REST service is implemeted. Validation is implemented.

### New Debit Card 
This page represents open a new debit card service. REST service is implemeted. Validation is implemented.

### Change Currency
This page represents change currency service. REST service is implemeted. Validation is implemented.

### Close Account
This page represents close account service. REST service is implemeted.

### My Accounts (New)
This page represents user accounts service. REST service is implemented. 

### My Debit Cards (New)
This page represents account debit cards service. REST service is implemented.

This pages can be used to opening a new debit card over existing account or to change the currency on the existing account. 

## Important Info
Server port of the app is **8081**.
No external JavaScript Libraries have been used.

## Instructions

### Transfer Money
1. Choose one of your accounts from the offerd list.
2. Enter the number of the receiving account (must exist in the system).
3. Enter the ammount (must be greater or equal current balance).
4. Press the button to commit transaction.

### New Debit Card Instraction
1. Choose an existing account from the offerd list.
2. Enter new card name (cannot be empty).
3. Press the button to save the information.

### Change Currency Instraction
1. Choose an existing account from the offerd list.
2. Choose the new currency from the offerd list (cannot choose the old currency).
3. Press the button to confirm changes.

### Close Account
1. Choose any account from the offerd list.
2. Press the button to confirm changes.

### My Accounts
1. When you enter this page the information about your existing accounts is displayed.

### My Debit Cards
1. Choose an existing account from the offerd list.
2. Press the button to send the request.
3. All debit cards corresponding with selected account are displayed below. 


## Collaborators
- [Maksim Fisenko](https://github.com/maksimfisenko)
- [Gleb Shkoda](https://github.com/Gosyaa)
