
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
                sh "mvn clean install"
				timeout(time:5, unit:'DAYS') {
    			input message:'Approve deployment?', submitter: 'milan'
			}

            }
        }
		
		stage('SonarQube analysis') {
			steps {
				withSonarQubeEnv('SonarQube') {
				sh 'mvn clean package sonar:sonar'
			}
		}
	}

	    stage("Quality Gate") {
            steps {
		    sleep(10)
                timeout(time: 1, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
		stage('>>>package<<<') {
            steps {
                sh "mvn package"
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
