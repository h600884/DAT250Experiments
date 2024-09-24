## Lab report : DAT250 experiment 5

#### Technical problems:

I did not have any problems when doing experiment 1 (CRUD-operations). But when I started working with experiment 2 I had a few problems which was related to each other.

Problem: MapReduce function in Java is deprecated, and I was not allowed to actually run the class. 
Solution: I had to adjust my mongodb-driver to a lower level to be able to run it. 

Problem: When doing the Map-Reduce function, I got a problem after I had ran the class one time that I had a duplicate key. I struggled a long time to find out what the problems actually was, but after some time and a lot of debugging I managed to fix it.
Solution: I had to use the delete crud to delete the collection after each time I ran the class.

#### Screenshots:

The correct validation of the installation package:

Insert CRUD:

Query CRUD: 

Update CRUD:

Delete CRUD:

#### Reason about why your implemented Map-reduce operation is useful and interpret the collection obtained.

The implemented Map-Reduce operation is useful because it can in this case aggregate data by summing the total order value for each customer and retrieving the most recent order date. This gives an overview of both the customer’s total purchase value and their latest activity, which can be used to for example analyze buying patterns.

#### Pending issues:

As of now there are no issues with the code and everything works as expected.