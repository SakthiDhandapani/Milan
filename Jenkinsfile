
pipeline {
	environment{
		BRANCH_NAME= "${env.BRANCH_NAME}"
		APPROVER = readFile(file:"/var/lib/jenkins/users.txt")

	}
    agent any
    stages {
        stage('Clone Repo and Clean it') {
            steps {
		sh 'rm -rf Milan'                
                sh 'git clone https://github.com/SakthiDhandapani/Milan.git'
            }
        }
		stage('clean') {
            steps {
                sh "mvn clean"
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
		stage('Production') {
			when {
				expression { BRANCH_NAME =='main'}
			}
            	steps {
                	sh "mvn package"
					
					timeout(time:5, unit:'DAYS') {
					input message:'Approve deployment?', submitter: "${APPROVER}"
					}
					sh "aws s3 cp target/demo-1.0.0.jar s3://haeron-storage"
					sh '''aws lambda update-function-code --function-name myspringboot \\
					--s3-bucket haeron-storage \\
					--s3-key demo-1.0.0.jar \\
					--region ap-south-1'''

            }
				post{
					success{
						echo "Successfully deployed to Production"
					}
					failure{
						echo "Failed deploying to Production"
					}
				}
        }
		
		stage('Stagging') {
			when {
				expression { BRANCH_NAME =='test'}
			}
            	steps {
                	sh "mvn package"
					timeout(time:5, unit:'DAYS') {
					input message:'Approve deployment?', submitter: "${APPROVER}"
					}
					sh "aws s3 cp target/demo-1.0.0.jar s3://haeron-storage"
					sh '''aws lambda update-function-code --function-name myspringboot \\
					--s3-bucket haeron-storage \\
					--s3-key demo-1.0.0.jar \\
					--region ap-south-1'''

            }
				post{
					success{
						echo "Successfully deployed to Stagging"
					}
					failure{
						echo "Failed deploying to Stagging"
					}
				}
        }
		
		stage('Development') {
			when {
				expression { BRANCH_NAME =='dev' | BRANCH_NAME =='dev2'}
			}
            	steps {
                	sh "mvn package "
			timeout(time:5, unit:'DAYS') {
					input message:'Approve deployment?', submitter: 'milan'
					}
					sh "aws s3 cp target/demo-1.0.0.jar s3://haeron-storage"
					sh '''aws lambda update-function-code --function-name myspringboot \\
					--s3-bucket haeron-storage \\
					--s3-key demo-1.0.0.jar \\
					--region ap-south-1'''

            }
				post{
					success{
						echo "Successfully deployed to Development"
					}
					failure{
						echo "Failed deploying to Development"
					}
				}
        }
		
    }
}

