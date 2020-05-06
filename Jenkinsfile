parameters {
    string(defaultValue: 'My Sample Project', description: 'Project Name', name: 'myProject');
    choice(choices: 'https://github.com/mayankverma24/maven-learning.git', description: 'Git Repository', name: 'myGitRepo');
    choice(choices: 'mvn test -PSmoke', description: 'Testing Profile to be run', name: 'profile');
};

node {
    timeout(time: 61, unit: 'MINUTES') {
            error = '';
            buildStatus = '';

            stage('CHECKOUT') {
                STAGE_NAME = 'CHECKOUT';
                try {
                    git myGitRepo;
                    colorCode = '#00FF00';
                    buildStatus = 'Success';
                } catch (exc) {
                    echo 'Checkout from GIT failed';
                    colorCode = '#FF0000';
                    buildStatus = 'Checkout from GIT Failed';
                };
            };

            stage('BUILD') {
                STAGE_NAME = 'BUILD';
                try {
                        bat 'mvn clean'
                        bat 'mvn build';
                    colorCode = '#00FF00';
                    buildStatus = 'Success';
                } catch (exc) {
                    echo 'Build Failed';
                    colorCode = '#FF0000';
                    buildStatus = 'Build Failed';
                };
            };
            
            stage('TEST') {
                STAGE_NAME = 'TEST';
				try {
					bat profile;
                    colorCode = '#00FF00';
                    buildStatus = 'Success';
                    testStatus = 'Success';
                } catch (exc) {
                    echo 'Something failed while testing the application!';
                    colorCode = '#FF0000';
                    buildStatus = 'Test Failed';
                    testStatus = 'Failed';
                };
            };
         };
    };