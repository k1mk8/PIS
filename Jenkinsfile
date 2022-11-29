pipeline {

    agent any

    environment{
        AWS_DEFAULT_REGION='us-east-1'
    }
    options {
        gitLabConnection('pis-project')
    }

    post {
      failure {
        updateGitlabCommitStatus name: 'jenkins-pipeline', state: 'failed'
      }
      success {
        updateGitlabCommitStatus name: 'jenkins-pipeline', state: 'success'
      }
      aborted {
        updateGitlabCommitStatus name: 'jenkins-pipeline', state: 'canceled'
      }
    }

    stages {

        stage('Main Pull') {
            when{
                branch 'PPBAP-25-deployment-pipeline'
            }
            steps {
                git branch: 'PPBAP-25-deployment-pipeline', credentialsId: 'sdyszews', url: 'https://gitlab-stud.elka.pw.edu.pl/pkosmala/pis22z-projekt-baza-aktow-prawnych'
            }
        }

        stage('Develop Pull') {
            when{
                branch 'develop'
            }
            steps {
                git branch: 'develop', credentialsId: 'sdyszews', url: 'https://gitlab-stud.elka.pw.edu.pl/pkosmala/pis22z-projekt-baza-aktow-prawnych'
            }
        }

        stage('Gradle Build') {
            steps {
                updateGitlabCommitStatus name: 'jenkins-pipeline', state: 'running'
                sh '/home/pkosmala/.sdkman/candidates/gradle/current/bin/gradle wrapper build'
            }
        }


        stage('Docker') {
            steps {
                sh 'docker compose build'
                sh 'docker compose up -d'
            }
        }

        stage('Test') {
          steps {
                sh './gradlew clean test'
                sh './gradlew clean integration'
          }
        }

        stage('SonarQube Analysis') {
            steps{
                withSonarQubeEnv(installationName: 'pisproject-sq') {
                    sh './gradlew sonarqube'
                }
            }
        }

        stage('Publish snapshot to nexus') {
            when{
                branch 'develop'
            }
            steps {
                sh './gradlew incrementVersion --versionIncrementType=PATCH -Psnapshot'
                sh './gradlew publish'
            }
        }

        stage('Publish release to nexus') {
            when{
                branch 'PPBAP-25-deployment-pipeline'
            }
            steps {
                sh './gradlew incrementVersion --versionIncrementType=MINOR'
                sh './gradlew publish -Prelease'
            }
        }

        stage('Deploy to AWS EC2'){
            when{
                branch 'PPBAP-25-deployment-pipeline'
            }
            steps{
                withCredentials([aws(accessKeyVariable: 'AWS_ACCESS_KEY_ID', credentialsId: 'pisproject-aws', secretKeyVariable: 'AWS_SECRET_ACCESS_KEY')]) {
                    sh 'docker-machine ls'
                    sh 'aws ec2 start-instances --instance-ids i-03d8e699c2d78a9d9 i-08f11770857c72347'
                    sh 'docker-machine create --driver generic --generic-ip-address=44.212.197.238 pisproject'
                    sleep 60
                    sh 'docker-machine env pisproject'
                    sh 'eval $(docker-machine env pisproject)'
                    sh 'docker compose build'
                    sh 'docker compose up -d'
                }
            }
        }

        stage('Cleanup') {
          steps {
                sh 'docker compose down'
          }
        }

        stage('Deploy cleanup') {
          when{
              branch 'PPBAP-25-deployment-pipeline'
          }
          steps{
              withCredentials([aws(accessKeyVariable: 'AWS_ACCESS_KEY_ID', credentialsId: 'pisproject-aws', secretKeyVariable: 'AWS_SECRET_ACCESS_KEY')]) {
                  sh 'aws ec2 stop-instances --instance-ids i-03d8e699c2d78a9d9 i-08f11770857c72347'
              }
          }
        }
    }
}