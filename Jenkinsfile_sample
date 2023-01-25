pipeline{
    
    agent any
    
    stages{
        stage("build"){
            steps{
                echo("build project")
            }
        }
        
        stage("deploy to dev"){
            steps{
                echo("deploy to dev")
            }
        }
        stage("Run Ut's"){
            steps{
                echo("run Unit test cases")
            }
        }
          stage("deploy to QA"){
            steps{
                echo("deploy to QA")
            }
        }
        stage("Run Automation test cases"){
            steps{
                echo("run Automation test cases")
            }
        }
         stage("deploy to stage"){
            steps{
                echo("deploy to stage")
            }
        }
        stage("Run sanity test cases"){
            steps{
                echo("run sanity test cases")
            }
        }
         stage("deploy to Prod"){
            steps{
                echo("deploy to Prod")
            }
        }
        
        
        
    }
}

