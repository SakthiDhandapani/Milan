
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
		
		stage("Quality Gate"){
			steps{
		sleep(60)
          timeout(time: 1, unit: 'MINUTES') {
              def qg = waitForQualityGate()
              if (qg.status != 'OK') {
                  emailext body: 'Your code was failed due to sonarqube quality gate', subject: 'Jenkins Failed Report', to: 'haeronsakthi@gmail.com'
                  error "Pipeline aborted due to quality gate failure: ${qg.status}"

              }
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
