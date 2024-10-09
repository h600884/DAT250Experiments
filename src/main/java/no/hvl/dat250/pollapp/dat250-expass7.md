## Lab report : DAT250 experiment 7

### JPA PostgreSQL docker image

For the postgres image I used the official postgres docker image. In the documentation it stated I should use port 5432, but I was unable to do that since something that I don't know was using that port.
Because of that I had to use port 5433 to connect to the database.

#### Screenshot on how to bootstrap the database:
<img width="1291" alt="Skjermbilde 2024-10-07 kl  13 18 49" src="https://github.com/user-attachments/assets/c94c2b43-2dc2-430e-b10e-dc50791c96be">

#### Screenshot of connected database:
<img width="390" alt="Skjermbilde 2024-10-07 kl  13 16 22" src="https://github.com/user-attachments/assets/fab2afce-67ff-4e20-bfde-da4f4071d84d">

#### Screenshot of docker image and docker container
Image that is pulled using the premade Postgres image:
<img width="989" alt="Skjermbilde 2024-10-09 kl  10 39 25" src="https://github.com/user-attachments/assets/fdfc8a9d-9d05-40a8-8b0c-8cbe4e00c20b">

Container that is created using the image:
<img width="986" alt="Skjermbilde 2024-10-09 kl  10 39 17" src="https://github.com/user-attachments/assets/d7ae6757-76b3-4c21-95a5-c77dae101d78">

### My own docker image for the poll application
The dockerfile can be found here: https://github.com/h600884/DAT250Experiments/blob/main/Dockerfile

#### Screenshot of docker image and docker container for the poll application:
Image:
<img width="986" alt="Skjermbilde 2024-10-09 kl  10 44 36" src="https://github.com/user-attachments/assets/820d64fc-cb35-46c1-b1a5-ee2565f773b3">

Container:
<img width="983" alt="Skjermbilde 2024-10-09 kl  10 44 26" src="https://github.com/user-attachments/assets/365e7620-3edd-4e97-b0e6-61246a90359d">


#### Screenshot of the docker commands for building an image and creating a container that is running the image:
Building an image using the dockerfile:

<img width="458" alt="Skjermbilde 2024-10-09 kl  10 45 55" src="https://github.com/user-attachments/assets/71eef067-1c1a-419c-9008-0f7d79f1f237">

Creating a container using the image:

<img width="446" alt="Skjermbilde 2024-10-09 kl  10 46 13" src="https://github.com/user-attachments/assets/f8b09eaa-cc96-4fe6-983e-ce034e72c711">
