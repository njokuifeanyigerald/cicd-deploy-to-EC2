def gv
pipeline{
    agent any

    parameters {
        choice(name: 'action', choices: 'create\ndelete', description: 'choose create or destroy')
        string(name: 'ImageName', description: 'name of the docker build', defaultValue:"javaapp")
        string(name: 'ImageTag', description: 'tag of the docker build', defaultValue: "v1")
        string(name: "DockerHubUser", description: "name of the Application", defaultValue: "bopgeek")
    }

    stages{
        stage("init"){
            when{expression { params.action == 'create'} }
            steps{
                script{
                    gv  = load "script.groovy"
                }   
            }
        }
        stage("Git checkout"){
            when{expression { params.action == 'create'} }
            steps{
                echo "========executing Git checkout========"
                git 'https://github.com/njokuifeanyigerald/cicd-deploy-to-EC2.git'
            }
            post{
                success{
                    echo "========A executed successfully========"
                }
                failure{
                    echo "========A execution failed========"
                }
            }
        }
        stage("mvn testing"){
            when{expression { params.action == 'create'} }
            steps{
                echo "====++++executing mvn testing++++===="
                sh 'mvn test'
            }
            post{
                success{
                    echo "====++++mvn testing executed successfully++++===="
                }
                failure{
                    echo "====++++mvn testing execution failed++++===="
                }
        
            }
        }
        stage("Integration testing"){
            when{expression { params.action == 'create'} }
            steps{
                echo "====++++executing Integration testing++++===="
                sh 'mvn verify -DskipUnitTests'
            }
            post{
                success{
                    echo "====++++Integration testing executed successfully++++===="
                }
                failure{
                    echo "====++++Integration testing execution failed++++===="
                }
        
            }
        }
        


        stage("Maven Build"){
            when{expression { params.action == 'create'} }
            steps{
                echo "====++++executing Maven Build++++===="
                sh 'mvn clean install '
            }
            post{
                success{
                    echo "====++++Maven Build executed successfully++++===="
                }
                failure{
                    echo "====++++Maven Build execution failed++++===="
                }
        
            }
        }
        stage("docker build"){
            when{expression { params.action == 'create'} }
            steps{
                echo "====++++executing docker build++++===="
                script{
                    gv.dockerbuild("${params.ImageName}", "${params.ImageTag}", "${params.DockerHubUser}")
                }

            }
            post{
                success{
                    echo "====++++docker build executed successfully++++===="
                }
                failure{
                    echo "====++++docker build execution failed++++===="
                }
            }
        }
        stage("docker image scan"){
            when{expression { params.action == 'create'} }
            steps{
                echo "====++++executing docke image scan++++===="
                script{
                    gv.dockerScan("${params.ImageName}", "${params.ImageTag}", "${params.DockerHubUser}")
                }
            }
            post{
                success{
                    echo "====++++docke image scan executed successfully++++===="
                }
                failure{
                    echo "====++++docke image scan execution failed++++===="
                }
            }
        }
        stage("docker image push"){
            when{expression { params.action == 'create'} }
            steps{
                echo "====++++executing docke image scan++++===="
                script{
                    gv.dockerPush("${params.ImageName}", "${params.ImageTag}", "${params.DockerHubUser}")
                }
                
            }
            post{
                success{
                    echo "====++++docke image push executed successfully++++===="
                }
                failure{
                    echo "====++++docke image push execution failed++++===="
                }
            }
        }
        stage("push to EC2 server"){
            when{expression { params.action == 'create'} }
            steps{
                echo "====++++executing push to EC2 server++++===="
            }
            post{
                always{
                    echo "====++++always++++===="
                }
                success{
                    echo "====++++push to EC2 server executed successfully++++===="
                }
                failure{
                    echo "====++++push to EC2 server execution failed++++===="
                }
        
            }
        }
        stage("docker image removal"){
            when{expression { params.action == 'create'} }
            steps{
                echo "====++++executing docke image scan++++===="
                script{
                    gv.dockerImageRemove("${params.ImageName}", "${params.ImageTag}", "${params.DockerHubUser}")
                }
                
            }
            post{
                success{
                    echo "====++++docke image push executed successfully++++===="
                }
                failure{
                    echo "====++++docke image push execution failed++++===="
                }
            }
        }
    }
    post{
        success{
            echo "========pipeline executed successfully ========"
        }
        failure{
            echo "========pipeline execution failed========"
        }
    }

    
}