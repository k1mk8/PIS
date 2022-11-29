pipeline {

    agent any

    options {
        gitLabConnection('pis-project')
        gitlabBuilds(builds: ['jenkins-pipeline'])
    }

    post {
      success {
        gitlabCommitStatus name: 'jenkins-pipeline', connection: gitLabConnection('pis-project')
      }
    }

    stages {

        stage('Develop Pull') {
            when{
                branch 'PPBAP-23-development-pipeline'
            }
            steps {
                git branch: 'PPBAP-23-development-pipeline', credentialsId: 'sdyszews', url: 'https://gitlab-stud.elka.pw.edu.pl/pkosmala/pis22z-projekt-baza-aktow-prawnych'
            }
        }

        stage('Gradle Build') {
            steps {
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

        stage('Cleanup') {
          steps {
                sh 'docker compose down'
          }
        }
    }
}