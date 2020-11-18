
pipeline {
    agent any
    stages {
        stage('Clone Repo and Clean it') {
            steps {
                // Get some code from a GitHub repository
                sh 'rm -rf Milan'                
                sh 'git clone https://github.com/SakthiDhandapani/Milan.git'
            }
        }
		stage('>>>clean<<<') {
            steps {
                sh "mvn clean"
            }
        }
		stage('>>>SonarQube analysis <<<') {
			steps{
			withSonarQubeEnv('sonarqube') {
				sh sh "mvn test sonar:sonar -Dsonar.host.url=http://13.235.51.178:9000"
				}
			}
		}
		
		stage('SonarQube analysis') {
         steps {
        withSonarQubeEnv('SonarQube Scanner') {
          sh 'sonar-scanner'
			}
		}
		}
		
		
		stage('>>>package<<<') {
            steps {
                sh "mvn package"
            }
        }
		stage('>>>Deploy into S3<<<') {
            steps {
                sh "aws s3 cp target/demo-1.0.0.jar s3://haeron-storage"
            }
        }
		stage('>>>Update Lambda<<<') {
            steps {
                sh '''aws lambda update-function-code --function-name myspringboot \\
                --s3-bucket haeron-storage \\
                --s3-key demo-1.0.0.jar \\
                --region ap-south-1'''
            }
        }
    }
}
