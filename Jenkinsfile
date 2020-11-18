pipeline {
    agent any
    stages {
        stage('Clone Repo and Clean it') {
            steps {
                // Get some code from a GitHub repository
                sh 'rm -rf Milan'
                
                sh 'git clone https://github.com/SakthiDhandapani/Milan.git'

                // Run Maven on a Unix agent.
                sh "mvn clean package"
            }
        }
    }
}
}
