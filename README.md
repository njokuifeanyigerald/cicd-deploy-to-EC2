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
- go to credentials in your jenkins, in the credential typee - choose ssh-key, paste the <>.pem key in it, choose a username 

