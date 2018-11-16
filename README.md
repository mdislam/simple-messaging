# Required programs
 * docker 
 * docker-compose


# First run
    $ docker-compose build

*  Also needed if **Dockerfile** is modified

# How to start the server: 
    $ docker-compose up -d

# How to shutdown the server:
    $ docker-compose down

# How to shut down the server and erase the database:
    $ docker-compose down -v

# If you are interested about application logs of the running containers, then run following in another terminal::
    $ docker-composer logs -f

# How to change the port the application runs in:

By default the app is running in http://localhost:80. The port is specified in **docker-compose.yml:**

    services:
      web:
        ports:
          - "80:8080"

The first number is the port that is exposed to your system. If you for example want to run the application in port 9876, modify it as follows:

    services:
      web:
        ports:
          - "9876:8080"
          
Answer: 1) update docker-compose.yml file with new port number 
--> 2) If setup is started, container need to stop/remove
--> 3) restart the one container         

# How to use the application:

The application is a simple chat server. By default you can connect to it by opening http://localhost:80 in your browser of choice.
If you modified the application port, you must append that to the localhost "url". For example, if the above example modification was done, open following address in your browser:  

    http://localhost:9876

Once you have opened the application in your browser, you are presented with a login screen with registration link ("Don't have an account yet?"). Click this link, and type in a user name and a password. You can then use these to log in to the application. Once logged in, you are presented with a simple chat room, where you can write messages. 

If you want to check how multiple users communicate via this chat, then you need to open the application from different browser and log in using different user account. Send message from the browsers and you will see the communication among different users.

# How to run the test suite:

You can open shell inside the docker container by running the following command:

    $ sudo docker exec -it <DOCKER_CONTAINER_ID> /bin/bash

and once the shell opens, running command

    $ mvn test

You can detach from the container by pressing CTRL+D or writing `exit` in the terminal

You can find the docker container id by running the following command:

    $ docker ps

it outputs information on running containers, for example: 

    
```

|CONTAINER ID | IMAGE           |   COMMAND               |  CREATED         |  STATUS         |  PORTS                  |   NAMES                     |
|-------------|-----------------|-------------------------|------------------|-----------------|-------------------------|-----------------------------|
|6cd048de5d86 | assignment_web  |  "./run_webapp.sh"      |  22 minutes ago  |  Up 22 minutes  |  0.0.0.0:9876->8080/tcp |   assignment_web_1          |
|de554ac4da66 | mysql:5.7       |  "docker-entrypoint.s…" |  27 minutes ago  |  Up 27 minutes  |  3306/tcp, 33060/tcp    |   assignment_mysql-server_1 |

```

the container id is for the image `assignment_web`, so 6cd048de5d86 in this case
