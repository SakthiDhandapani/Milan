pipeline {
    agent none
    stages {
        stage("build & SonarQube Scan") {
            agent any
            steps {
              git changelog: false, poll: false, url: ' https://github.com/SakthiDhandapani/Milan.git/'
              script {                  
                    withSonarQubeEnv('localhost') {
                       sh 'mvn clean package sonar:sonar -DskipTests'
                    }                   
              }
            }
        }
        
        stage("Quality Gate") {
            steps {
                timeout(time: 1, unit: 'HOURS') {
                    script  {
                        def qg = waitForQualityGate()
                        if (qg.status != 'OK') {
                            error "Pipeline aborted due to quality gate failure: ${qg.status}"
                        }
                    }
                }
            }
        }
    }
}
