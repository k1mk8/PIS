pipeline {

    agent any

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

        stage('Cleanup') {
          steps {
                sh 'docker compose down'
          }
        }
    }
}