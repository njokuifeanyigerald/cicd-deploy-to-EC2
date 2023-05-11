def dockerbuild(String project, String ImageTag, String hubUser){
    sh """
        docker image build -t ${hubUser}/${project} .
        docker image tag ${hubUser}/${project} ${hubUser}/${project}:${ImageTag}
        docker image tag ${hubUser}/${project} ${hubUser}/${project}:latest
       """
}

def dockerScan(String project, String ImageTag, String hubUser){
    sh """
        trivy image ${hubUser}/${project}:latest > scan.txt
        cat scan.txt
       """
}

def dockerPush(String project, String ImageTag, String hubUser){
    withCredentials([usernamePassword(
        credentialsId: 'dockerhub_cred', 
        passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh " docker login  -u '$USER' -p '$PASS' "
    }
    sh """
        docker image build -t ${hubUser}/${project} .
        docker image push  ${hubUser}/${project}:${ImageTag}
        docker image push ${hubUser}/${project}:latest
       """   
}
def dockerImageRemove(String project, String ImageTag, String hubUser){
    sh """
        docker rmi ${hubUser}/${project}:latest 
        docker rmi ${hubUser}/${project}:${ImageTag}
       """
}



// FOR AWS ECR

def ecrDockerBuild(String aws_account_id, String region, String ecr_repoName){
    sh """
        docker build -t ${ecr_repoName} .
        docker tag ${ecr_repoName}:latest ${aws_account_id}.dkr.ecr.${region}.amazonaws.com/${ecr_repoName}:latest
       """
}

def ecrDockerScan(String aws_account_id, String region, String ecr_repoName){
    sh """
        trivy image ${aws_account_id}.dkr.ecr.${region}.amazonaws.com/${ecr_repoName}:latest > ECR.txt
        cat ECR.txt
       """
}

def ecrDockerPush(String aws_account_id, String region, String ecr_repoName){
    // aws ecr get-login-password --region ${region} | docker login --username AWS --password-stdin ${aws_account_id}.dkr.ecr.${region}.amazonaws.com
    // sh "docker login -u AWS -p \$(aws ecr get-login-password --region ${region}) ${aws_account_id}.dkr.ecr.${region}.amazonaws.com"
    sh "aws ecr get-login-password --region ${region} | docker login --username AWS --password-stdin ${aws_account_id}.dkr.ecr.${region}.amazonaws.com"
    sh "docker push ${aws_account_id}.dkr.ecr.${region}.amazonaws.com/${ecr_repoName}:latest"
}


def ecrDockerImageRemove(String aws_account_id, String region, String ecr_repoName){
    sh """
        docker rmi ${aws_account_id}.dkr.ecr.${region}.amazonaws.com/${ecr_repoName}:latest
       """
}


return this