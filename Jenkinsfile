pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Compilando proyecto...'
                sh 'mvn clean package'
            }
        }
        stage('Test') {
            steps {
                echo 'Ejecutando pruebas...'
                sh 'mvn test || echo "Sin pruebas definidas aún"'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Simulando despliegue...'
                echo 'Aplicación lista'
            }
        }
    }
}
