## Lab report : DAT250 experiment 5

#### Technical problems:

I did not have any problems when doing experiment 1 (CRUD-operations). But when I started working with experiment 2 I had a few problems which was related to each other.

Problem: MapReduce function in Java is deprecated, and I was not allowed to actually run the class. 
Solution: I had to adjust my mongodb-driver to a lower level to be able to run it. 

Problem: When doing the Map-Reduce function, I got a problem after I had ran the class one time that I had a duplicate key. I struggled a long time to find out what the problems actually was, but after some time and a lot of debugging I managed to fix it.
Solution: I had to use the delete crud to delete the collection after each time I ran the class.

#### Screenshots:

Thecorrect validation of the installation package:
<img width="533" alt="Skjermbilde 2024-09-23 kl  12 50 20" src="https://github.com/user-attachments/assets/cb14f108-24f7-4080-a20e-f250b0679e1b">

Insert CRUD:
<img width="1236" alt="Skjermbilde 2024-09-24 kl  18 57 27" src="https://github.com/user-attachments/assets/38e8557c-0647-49fa-b9a1-dab53671fd31">

Query CRUD:
<img width="1146" alt="Skjermbilde 2024-09-24 kl  18 57 50" src="https://github.com/user-attachments/assets/541f4733-145e-427d-b73d-12724d252ffb">

Update CRUD:
<img width="1276" alt="Skjermbilde 2024-09-24 kl  18 58 55" src="https://github.com/user-attachments/assets/dd07240f-b001-4cde-868c-9fe533933075">

Delete CRUD:
<img width="1070" alt="Skjermbilde 2024-09-24 kl  18 59 41" src="https://github.com/user-attachments/assets/16fd048a-5dfe-4d35-96c9-82599bc3af2f">

Map-reduce operation:
<img width="299" alt="Skjermbilde 2024-09-24 kl  18 41 42" src="https://github.com/user-attachments/assets/497e22ea-23a9-4516-a36b-59680add33ad">

Additional map-reduce operation:
<img width="595" alt="Skjermbilde 2024-09-24 kl  18 42 23" src="https://github.com/user-attachments/assets/4bbd58cc-7532-4510-a6b3-ab1bc6b8d76a">

#### Reason about why your implemented Map-reduce operation is useful and interpret the collection obtained.

The implemented Map-Reduce operation is useful because it can in this case aggregate data by summing the total order value for each customer and retrieving the most recent order date. This gives an overview of both the customer’s total purchase value and their latest activity, which can be used to for example analyze buying patterns.

#### Pending issues:

As of now there are no issues with the code and everything works as expected.
