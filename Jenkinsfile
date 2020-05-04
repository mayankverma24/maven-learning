import groovy.json.JsonBuilder;
import java.util.Random;

parameters {
    string(defaultValue: 'Delta.com UI Automation', description: 'Project Name', name: 'myProject');
    choice(choices: 'https://www.delta.com', description: 'Test Environment URL', name: 'myEnvironment');
    choice(choices: 'BrowserStack-SingleBrowser', description: 'Execute Tests via', name: 'myExecutionPlatform');
    choice(choices: 'Windows-10', description: 'Run tests on OS', name: 'myOS');
    string(defaultValue: 'chrome', choices: 'chrome-70', description: 'Run tests on Browser', name: 'myBrowser');
    string(defaultValue: 'e2eBook', choices: 'e2eBook', description: 'Multiple Values can be passed by comma seperation', name: 'mySuite');
    string(defaultValue: '@booke2e', choices: '@booke2e', description: 'Run tests tagged as', name: 'myTags');
    booleanParam(name: 'notifyReportOnMSTeams', defaultValue: true, description: 'Enable this option default value to notify the report in Microsoft Teams');
    choice(choices: 'DotComAutomation.Delta@delta.com', description: 'Publish reports in email', name: 'myDefaultEmail');
    string(defaultValue: '', description: 'Run tests on Browser', name: 'EmailForBrowserStackCredential');
};

//Any OS - Browser Combo
string tempBrowserName = myBrowser.toUpperCase();
echo "Upper Case Browser Name ${tempBrowserName}";
if (tempBrowserName == 'ANY' || tempBrowserName.contains('HEADLESS')) {
    myOS = 'Linux';
    myBrowser = 'Headless Chrome';
    //echo "Random OS is: ${myOS}";
    //echo "Random Browser is: ${myBrowser}";
}

if (params.myExecutionPlatform == 'Remote-Windows7') {
    myNode = 'OmniChannel_Win7';
} else if (params.myExecutionPlatform == 'Remote-Windows10') {
    myNode = 'OmniChannel_Win10';
} else if (params.myExecutionPlatform == 'Remote-OSXMojave') {
    myNode = 'OmniChannel_MAC';
} else {
    myNode = 'OmniChannel_VM';
};

echo "My Node Identified for this job: ${myNode}";
//Teams - Webhook URL:
boolean notifyReportOnMSTeams = true;
if (params.myEnvironment.contains('dvl')) {
    myTeamsWebHookChannel = 'DVL';
    myTeamsWebHookURL = 'https://outlook.office.com/';
} else if (params.myEnvironment.contains('stg') || params.myEnvironment.contains('si')) {
    myTeamsWebHookChannel = 'DVL';
	myTeamsWebHookURL = 'https://outlook.office.com/';
};

node(myNode) {
    timeout(time: 61, unit: 'MINUTES') {
        wrap([$class: 'AnsiColorBuildWrapper', 'colorMapName': 'xterm']) {
            error = '';
            buildStatus = '';

            stage('CHECKOUT') {
                STAGE_NAME = 'CHECKOUT';
                try {
                    checkout([$class: 'GitSCM', branches: scm.branches, extensions: scm.extensions, userRemoteConfigs: [[credentialsId: 'none', url: 'https://github.com/mayankverma24/maven-learning.git']]]);
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
                    if (myNode == 'OmniChannel_MAC' || myNode == 'OmniChannel_VM') {
                        sh 'mvn clean'
                        sh 'mvn build';
                    } else {
                        bat 'mvn clean'
                        bat 'mvn build';
                    };
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
					bat 'mvn test -PSmoke';
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
};