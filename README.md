# Nutrition Project
## Ideas

This website aims to develop a nutrition platform featuring various product categories such as:
- protein meals
- workout attire
- bars and snacks
- creatine
- vitamins
- vegan options.

Users can select the category from which they wish to purchase products, explore available discounts, and either log in or register and access their shopping cart.
I want also to add a form where you can sign if you want to be invited at a body composition analysis.

The backend of the project uses Spring framework to divide the functionalities in special classes with annotations that represent a function that that class will have.

**Model package**
- there will be the tables in the database like Product, User and Order

**Repository package**
- we have the interfaces for the interaction with the database
- these classes extends the JPA  repository which help us with the predefined methods like findById, findAll and if we want other methods we have to add them in this interface 

**Service package**
- in this package will be the classes which implements the interfaces from repository and this is basically the bussiness logic of the app
- in these classes will be the the implementation of the methods defined in the repository

**Controller package**
- the controllers are responsible for handling HTTP requests related to products, users or orders like GET, POST, PUT and DELETE
- these use the bussiness logic from the service
- they offer endpoints for various operations




