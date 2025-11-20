pipeline {
    agent any

    environment {
        APP_PORT = "9000"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo 'Compilando proyecto...'
                sh 'mvn -B clean package -DskipTests=false'
                sh 'echo "Contenido de target:" && ls -la target || true'
            }
        }

        stage('Archive artifact') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }

        stage('Run jar (background)') {
            steps {
                echo "Arrancando el JAR en background en el puerto ${env.APP_PORT}..."
                sh '''
                  JAR=$(ls target/*.jar | head -n1)
                  if [ -z "$JAR" ]; then
                    echo "ERROR: No se encontró JAR en target/*.jar"
                    exit 1
                  fi

                  # Si ya hay un app.pid lo paramos primero (seguro)
                  if [ -f app.pid ]; then
                    OLDPID=$(cat app.pid) || true
                    if [ -n "$OLDPID" ]; then
                      echo "Matando proceso previo $OLDPID"
                      kill $OLDPID || true
                      sleep 1
                      kill -9 $OLDPID || true
                    fi
                    rm -f app.pid
                  fi

                  # Ejecutar el jar en puerto 9000 (override de spring)
                  nohup java -jar "$JAR" --server.port=${APP_PORT} > app.log 2>&1 & echo $! > app.pid

                  echo "PID escrito en app.pid:"
                  cat app.pid || true

                  # mostrar primeras líneas de log para confirmar arranque inicial (no esperamos readiness)
                  sleep 2
                  echo "Últimas líneas de app.log (inicio):"
                  tail -n 80 app.log || true
                '''
            }
        }

        stage('Deploy (info)') {
            steps {
                echo "La aplicación debería estar corriendo ahora. Abre en tu navegador:"
                echo "http://localhost:${env.APP_PORT}/api/v1/greetings/name/Elizabeth"
                echo "(sustituye <HOST> por localhost si Jenkins está expuesto en tu máquina)"
            }
        }
    }

    post {
        always {
            echo "Post: puedes detener la app manualmente desde el workspace (ver app.pid)"
            sh 'echo "app.pid content:" && cat app.pid || true'
            sh 'echo "tail app.log (últimas 200 líneas):" && tail -n 200 app.log || true'
        }
    }
}
