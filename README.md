Pre-requisites:
--------
    - Install Git
    - Install Maven
    - Install Docker
    
Clone code from github:
-------
    git clone https://github.com/njokuifeanyigerald/cicd-deploy-to-EC2.git
    
Build Maven Artifact:
-------
    mvn clean install
 
Build Docker image for Springboot Application
--------------
    docker build -t bopgeek/kubernetes-configmap-reload .
  
Docker login
-------------
    docker login
    
Push docker image to dockerhub
-----------
    docker push bopgeek/kubernetes-configmap-reload
    

----------------------
SSH-AGENT


- Download a plugin called ssh-agent
- go to credentials in your jenkins, in the credential typee - choose ssh-key, paste the <>.pem key in it, choose a username(example if you re using an ubuntu image, write ubuntu as the username)
  ![image](https://github.com/njokuifeanyigerald/cicd-deploy-to-EC2/assets/46121207/129438dd-32df-4bfb-a667-84bded19d131)
- click `Enter directly` then paste your <>.pem file used for ssh in the specific server
  ![image](https://github.com/njokuifeanyigerald/cicd-deploy-to-EC2/assets/46121207/b5536ea3-aa46-4ab8-a3a8-23ae2d0e0f87)



