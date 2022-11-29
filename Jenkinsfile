pipeline {

    agent any

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

        stage('Develop Pull') {
            when{
                branch 'PPBAP-23-development-pipeline'
            }
            steps {
                git branch: gitlabSourceBranch, credentialsId: 'sdyszews', url: 'https://gitlab-stud.elka.pw.edu.pl/pkosmala/pis22z-projekt-baza-aktow-prawnych'
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
                branch 'PPBAP-23-development-pipeline'
            }
            steps {
                sh './gradlew incrementVersion --versionIncrementType=PATCH -Psnapshot'
                sh './gradlew publish'
            }
        }

        stage("Update version number in repo"){
            withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'sdyszews', usernameVariable: 'GIT_USERNAME', passwordVariable: 'GIT_PASSWORD']]) {
                sh('git commit -am "Aktualizacja numeru wersji"')
                sh('git push https://${GIT_USERNAME}:${GIT_PASSWORD}@https://gitlab-stud.elka.pw.edu.pl/pkosmala/pis22z-projekt-baza-aktow-prawnych')
            }
        }

        stage('Cleanup') {
          steps {
                sh 'docker-compose down'
          }
        }
    }
}