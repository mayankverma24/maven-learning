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

def browserStackCredentialsID = randomAccount(EmailForBrowserStackCredential);
echo "Random Number is ${browserStackCredentialsID}"

//Any OS - Browser Combo
string tempBrowserName = myBrowser.toUpperCase();
echo "Upper Case Browser Name ${tempBrowserName}";
if (tempBrowserName == 'ANY' || tempBrowserName.contains('HEADLESS')) {
    myOS = 'Linux';
    myBrowser = 'Headless Chrome';
    echo "Random OS is: ${myOS}";
    echo "Random Browser is: ${myBrowser}";
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
    myTeamsWebHookURL = 'https://outlook.office.com/webhook/8ba9d2aa-3920-4ffe-9efe-c8ec375ed92b@45e6435d-dc1c-42e3-89be-e72e6c8f072d/IncomingWebhook/d93df05bd32044128aa926682c738648/37239c8c-9a64-43f4-9568-6c7ae252ee24';
} else if (params.myEnvironment.contains('stg') || params.myEnvironment.contains('si')) {
    myTeamsWebHookChannel = 'DVL';
    myTeamsWebHookURL = 'https://outlook.office.com/webhook/8ba9d2aa-3920-4ffe-9efe-c8ec375ed92b@45e6435d-dc1c-42e3-89be-e72e6c8f072d/IncomingWebhook/d93df05bd32044128aa926682c738648/37239c8c-9a64-43f4-9568-6c7ae252ee24';
} else if (params.myEnvironment.contains('qat') || params.myEnvironment.contains('st')) {
    myTeamsWebHookChannel = 'DVL';
    myTeamsWebHookURL = 'https://outlook.office.com/webhook/8ba9d2aa-3920-4ffe-9efe-c8ec375ed92b@45e6435d-dc1c-42e3-89be-e72e6c8f072d/IncomingWebhook/d93df05bd32044128aa926682c738648/37239c8c-9a64-43f4-9568-6c7ae252ee24';
} else if (params.myEnvironment.contains('www') || params.myEnvironment.contains('prd')) {
    myTeamsWebHookChannel = 'DVL';
    myTeamsWebHookURL = 'https://outlook.office.com/webhook/8ba9d2aa-3920-4ffe-9efe-c8ec375ed92b@45e6435d-dc1c-42e3-89be-e72e6c8f072d/IncomingWebhook/d93df05bd32044128aa926682c738648/37239c8c-9a64-43f4-9568-6c7ae252ee24';
};

node(myNode) {
    timeout(time: 61, unit: 'MINUTES') {
        wrap([$class: 'AnsiColorBuildWrapper', 'colorMapName': 'xterm']) {
            error = '';
            buildStatus = '';

            stage('CHECKOUT') {
                STAGE_NAME = 'CHECKOUT';
                try {
                    checkout([$class: 'GitSCM', branches: scm.branches, extensions: scm.extensions, userRemoteConfigs: [[credentialsId: 'none', url: 'git@git.delta.com:dcom/OmniChannel-Automation.git']]]);
                    colorCode = '#00FF00';
                    buildStatus = 'Success';
                } catch (exc) {
                    echo 'Checkout from GIT failed';
                    colorCode = '#FF0000';
                    buildStatus = 'Checkout from GIT Failed';
                };
            };

            stage('CONFIGURE') {
                STAGE_NAME = 'CONFIGURE';
                try {
                    if (fileExists('reports')) {
                        if (myNode == 'OmniChannel_MAC' || myNode == 'OmniChannel_VM') {
                            sh 'RMDIR "reports" /S /Q';
                        } else {
                            bat 'RMDIR "reports" /S /Q';
                        };
                    };
                    if (myNode == 'OmniChannel_MAC' || myNode == 'OmniChannel_VM') {
                        sh 'mkdir "reports"';
                        sh 'npm cache clean --force';
                    } else {
                        bat 'MKDIR "reports"';
                        bat 'npm cache clean --force';
                    };
                    if (fileExists('node_modules')) {
                        if (myNode == 'OmniChannel_MAC' || myNode == 'OmniChannel_VM') {
                            sh 'RMDIR "node_modules" /S /Q';
                        } else {
                            bat 'RMDIR "node_modules" /S /Q';
                        };
                    };
                    colorCode = '#00FF00';
                    buildStatus = 'Success';
                } catch (exc) {
                    echo 'Configuration Failed';
                    colorCode = '#FF0000';
                    buildStatus = 'Configuration Failed';
                };
            };

            stage('BUILD') {
                STAGE_NAME = 'BUILD';
                try {
                    if (fileExists('typeScript')) {
                        if (myNode == 'OmniChannel_MAC' || myNode == 'OmniChannel_VM') {
                            sh 'npm run clean';
                        } else {
                            bat 'npm run clean';
                        };
                    };
                    if (myNode == 'OmniChannel_MAC' || myNode == 'OmniChannel_VM') {
                        sh 'npm install';
                        sh 'npm run tsc';
                    } else {
                        bat 'npm install'
                        bat 'npm run tsc';
                    };
                    colorCode = '#00FF00';
                    buildStatus = 'Success';
                } catch (exc) {
                    echo 'Build Failed';
                    colorCode = '#FF0000';
                    buildStatus = 'Build Failed';
                };
            };

            stage('WEBDRIVER') {
                STAGE_NAME = 'WEBDRIVER';
                try { //ONLY FOR HEADLESS
                    if ((myExecutionPlatform.contains('BrowserStack-SingleBrowser') || myExecutionPlatform.contains('BrowserStack-TestSuites')) && tempBrowserName.contains("HEADLESS")) {
                    echo "Local Webdriver update for Jenkins"
                    sh 'npm run webdriver-update-jenkins';
                    echo "Webdriver update - complete"
                    }
                    colorCode = '#00FF00';
                    buildStatus = 'Success';
                } catch (exc) {
                    echo 'Webdriver Failed';
                    colorCode = '#FF0000';
                    buildStatus = 'Webdriver Failed';
                };
            };

            stage('TEST') {
                STAGE_NAME = 'TEST';

                string jenkinsUrl = env.BUILD_URL;
                //echo "Test Variables Definition - OS and Device Info"
                if (myOS.contains('iOS') || myOS.contains('Android')) {
                    string myOSname = myOS.split('-')[0];
                    string myOSversion = '';
                    string myDevicename = myOS.split('-')[1];
                }
                else if (myOS.contains('-')) {
                    string myOSname = myOS.split('-')[0];
                    string myOSversion = myOS.split('-')[1];
                    string myDevicename = '';
                } else {
                    string myOSname = myOS;
                    string myOSversion = '';
                    string myDevicename = '';
                };
                //echo "Test Variables Definition - Browser Info"
                if (myBrowser.contains('-')) {
                    string myBrowsername = myBrowser.split('-')[0];
                    string myBrowserversion = myBrowser.split('-')[1];
                } else {
                    string myBrowsername = myBrowser;
                    string myBrowserversion = '';
                };
                //echo "Test Name: OS - Browser Info"
                if (myOS.contains('iOS') || myOS.contains('Android')) {
                    string myTestname = myOS;
                } else {
                    string myTestname = myOS + ' : ' + myBrowser;
                };
                //echo "Test Variables Definition - Build Name for Browserstack"
                string myBuild = "${env.JOB_BASE_NAME}: Build_${env.BUILD_NUMBER}"
                string tempBrowserName = myBrowsername.toUpperCase();
                echo "Upper Case Browser Name ${tempBrowserName}";
                echo "Parameters for this test execution job: \nProject Name: ${myProject}, \nBuild Name: ${myBuild}, \nTest Environment: ${myEnvironment}, \nTest Platform: ${myExecutionPlatform}, \nOS Name: ${myOSname}, \nOS Version: ${myOSversion}, \nDevice Name: ${myDevicename}, \nBrowser Name: ${myBrowsername}, \nBrowser Version: ${myBrowserversion}, \nTest Name: ${myTestname}, \nSuites Name: ${mySuite}, \nTag Name: ${myTags}, \nMSTeamsChannel: ${myTeamsWebHookChannel}, \nEmail list: ${myDefaultEmail},";
                try {
                    if (myExecutionPlatform.contains("Remote")) {
                        if (myBrowser.contains("All") || myOS.contains("Multi")) {
                            bat 'protractor typeScript/config/Local/configLocalMulti.js --baseUrl=' + myEnvironment + ' --cucumberOpts.tags="' + myTags + '"';
                        } else if (myBrowser.contains("Any")) {
                            bat 'protractor typeScript/config/config.js --baseUrl=' + myEnvironment + ' --cucumberOpts.tags="' + myTags + '"';
                        } else if (myBrowser.contains("chrome")) {
                            bat 'protractor typeScript/config/Local/configLocalChrome.js --baseUrl=' + myEnvironment + ' --cucumberOpts.tags="' + myTags + '"';
                        } else if (myBrowser.contains("internet explorer")) {
                            bat 'protractor typeScript/config/Local/configLocalIE.js --baseUrl=' + myEnvironment + ' --cucumberOpts.tags="' + myTags + '"';
                        } else if (myBrowser.contains("firefox")) {
                            bat 'protractor typeScript/config/Local/configLocalFirefox.js --baseUrl=' + myEnvironment + ' --cucumberOpts.tags="' + myTags + '"';
                        } else if (myBrowser.contains("MicrosoftEdge")) {
                            bat 'protractor typeScript/config/Local/configLocalEdge.js --baseUrl=' + myEnvironment + ' --cucumberOpts.tags="' + myTags + '"';
                        } else if (myBrowser.contains("safari")) {
                            bat 'protractor typeScript/config/Local/configLocalMacSafari.js --baseUrl=' + myEnvironment + ' --cucumberOpts.tags="' + myTags + '"';
                        } else {
                            echo 'Something failed on Remote...!'
                        };
                    } else if (myExecutionPlatform.contains('BrowserStack-SingleBrowser') && tempBrowserName.contains("HEADLESS FIREFOX")) {
                        sh 'protractor typeScript/config/JenkinsJobsHeadless/configJenkinsFirefox.js --baseUrl=' + myEnvironment + ' --cucumberOpts.tags="' + myTags + '" --capabilities.project="' + myProject + '" --capabilities.build="' + myBuild + '" ';
                    } else if (myExecutionPlatform.contains('BrowserStack-TestSuites') && tempBrowserName.contains("HEADLESS FIREFOX")) {
                        sh 'protractor typeScript/config/JenkinsJobsHeadless/configJenkinsFirefoxSuites.js --baseUrl=' + myEnvironment + ' --suite="' + mySuite + '" --cucumberOpts.tags="' + myTags + '" --capabilities.project="' + myProject + '" --capabilities.build="' + myBuild + '" ';
                    } else if (myExecutionPlatform.contains('BrowserStack-SingleBrowser') && tempBrowserName.contains('HEADLESS')) {
                        sh 'protractor typeScript/config/JenkinsJobsHeadless/configJenkinsChrome.js --baseUrl=' + myEnvironment + ' --cucumberOpts.tags="' + myTags + '" --capabilities.project="' + myProject + '" --capabilities.build="' + myBuild + '" ';
                    } else if (myExecutionPlatform.contains('BrowserStack-TestSuites') && tempBrowserName.contains('HEADLESS')) {
                        sh 'protractor typeScript/config/JenkinsJobsHeadless/configJenkinsChromeSuites.js --baseUrl=' + myEnvironment + ' --suite="' + mySuite + '" --cucumberOpts.tags="' + myTags + '" --capabilities.project="' + myProject + '" --capabilities.build="' + myBuild + '" ';
                    } else if (myExecutionPlatform.contains("BrowserStack-ParallelBrowsers")) {
                        browserstack(credentialsId: "${browserStackCredentialsID}", localConfig: [localOptions: '', localPath: '']) {
                            if (myNode == 'OmniChannel_MAC' || myNode == 'OmniChannel_VM') {
                                sh 'protractor typeScript/config/JenkinsJobs/configParallel.js --baseUrl=' + myEnvironment + ' --cucumberOpts.tags="' + myTags + '" --capabilities.project="' + myProject + '" --capabilities.build="' + myBuild + '" ';
                            } else {
                                bat 'protractor typeScript/config/JenkinsJobs/configParallel.js --baseUrl=' + myEnvironment + ' --cucumberOpts.tags="' + myTags + '" --capabilities.project="' + myProject + '" --capabilities.build="' + myBuild + '" ';
                            };
                        };
                    } else if (myExecutionPlatform.contains("BrowserStack-SingleBrowser")) {
                        browserstack(credentialsId: "${browserStackCredentialsID}", localConfig: [localOptions: '', localPath: '']) {
                            if (myNode == 'OmniChannel_MAC' || myNode == 'OmniChannel_VM') {
                                sh 'protractor typeScript/config/JenkinsJobs/configSimple.js --baseUrl=' + myEnvironment + ' --cucumberOpts.tags="' + myTags + '" --capabilities.project="' + myProject + '" --capabilities.build="' + myBuild + '" --capabilities.name="' + myTestname + '" --capabilities.os="' + myOSname + '" --capabilities.os_version="' + myOSversion + '" --capabilities.browserName=' + myBrowsername + ' --capabilities.browser_version="' + myBrowserversion + '" --capabilities.device="' + myDevicename + '" ';
                            } else {
                                bat 'protractor typeScript/config/JenkinsJobs/configSimple.js --baseUrl=' + myEnvironment + ' --cucumberOpts.tags="' + myTags + '" --capabilities.project="' + myProject + '" --capabilities.build="' + myBuild + '" --capabilities.name="' + myTestname + '" --capabilities.os="' + myOSname + '" --capabilities.os_version="' + myOSversion + '" --capabilities.browserName=' + myBrowsername + ' --capabilities.browser_version="' + myBrowserversion + '" --capabilities.device="' + myDevicename + '" ';
                            };
                        };
                    } else if (myExecutionPlatform.contains("BrowserStack-TestSuites")) {
                        browserstack(credentialsId: "${browserStackCredentialsID}", localConfig: [localOptions: '', localPath: '']) {
                            if (myNode == 'OmniChannel_MAC' || myNode == 'OmniChannel_VM') {
                                sh 'protractor typeScript/config/JenkinsJobs/configSuites.js --baseUrl=' + myEnvironment + ' --suite="' + mySuite + '" --cucumberOpts.tags="' + myTags + '" --capabilities.project="' + myProject + '" --capabilities.build="' + myBuild + '" --capabilities.name="' + myTestname + '" --capabilities.os="' + myOSname + '" --capabilities.os_version="' + myOSversion + '" --capabilities.browserName=' + myBrowsername + ' --capabilities.browser_version="' + myBrowserversion + '" --capabilities.device="' + myDevicename + '" ';
                            } else {
                                bat 'protractor typeScript/config/JenkinsJobs/configSuites.js --baseUrl=' + myEnvironment + ' --suite="' + mySuite + '" --cucumberOpts.tags="' + myTags + '" --capabilities.project="' + myProject + '" --capabilities.build="' + myBuild + '" --capabilities.name="' + myTestname + '" --capabilities.os="' + myOSname + '" --capabilities.os_version="' + myOSversion + '" --capabilities.browserName=' + myBrowsername + ' --capabilities.browser_version="' + myBrowserversion + '" --capabilities.device="' + myDevicename + '" ';
                            };
                        };
                    };
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

            stage('REPORT') {
                STAGE_NAME = 'REPORT';
                if (buildStatus == '' || buildStatus == 'Test Failed' || buildStatus == 'Success') {
                    try {
                        if (myNode == 'OmniChannel_MAC' || myNode == 'OmniChannel_VM') {
                            archiveArtifacts "reports/html/**/*.*"
                        } else {
                            archiveArtifacts '/reports/**'
                        };
                        publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'reports/html/', reportFiles: 'AutomationReport.html', reportName: myProject + ' - Automation Report'])
                            // Report Copying to Cloud
                            echo "BUILD Job Name is : ${env.JOB_NAME}";
                            echo "BUILD Job Base Name is : ${env.JOB_BASE_NAME}";
                            sh "ssh -o StrictHostKeyChecking=no httpuser@channels-ui.delta.com 'mkdir -p \"UIAutomationTestReports/${env.JOB_BASE_NAME}/${env.BUILD_NUMBER}\"' "
                            sh "scp -o StrictHostKeyChecking=no -r \"/products/jenkins/workspace/${env.JOB_NAME}/reports/html\" httpuser@channels-ui.delta.com:\"~/UIAutomationTestReports/${env.JOB_BASE_NAME}/${env.BUILD_NUMBER}/\""
                            colorCode = '#00FF00';
                            buildStatus = 'Success';
                    } catch (exc) {
                        echo 'Something failed while Archiving report...!';
                        colorCode = '#FF0000';
                        buildStatus = 'Report Archiving Failed';
                    };
                } else {
                    echo 'Unknown build Status'
                };
            };

            stage('NOTIFICATION') {
                STAGE_NAME = 'NOTIFICATION';
                if (buildStatus == '' || buildStatus == 'Test Failed' || buildStatus == 'Success') {
                    try {
                        echo error;
                        if (error == '') {
                            script {
                                def payload = """
                                {
                                    "type": "MessageCard",
                                    "context": "https://schema.org/extensions",
                                    "summary": "${myProject}",
                                    "themeColor": "${colorCode}",
                                    "sections": [
                                        {
                                            "activityTitle": "Automation Build Notification - ${myProject}",
                                            "activitySubtitle": "Build: ${env.BUILD_NUMBER}",
                                            "activityImage": "https://www.sharoncarrtravel.com/wp-content/uploads/2016/06/Delta-Circle.png",
                                            "facts": [
                                                {
                                                    "name": "Project:",
                                                    "value": "${myProject}",
                                                },
                                                {
                                                    "name": "Environment:",
                                                    "value": "${myEnvironment}"
                                                },
                                                {
                                                    "name": "Browser:",
                                                    "value": "${myBrowser}"
                                                },
                                                {
                                                    "name": "OS:",
                                                    "value": "${myOSname}"
                                                },
                                                {
                                                    "name": "Status:",
                                                    "value": "${testStatus}"
                                                }
                                            ],
                                            "markdown": true
                                        }
                                    ],
                                    "potentialAction": [
                                        {
                                            "@type": "OpenUri",
                                            "name": "View Report",
                                            "targets": [
                                                {
                                                    "os": "default",
                                                    "uri": "http://channels-ui.delta.com/UIAutomationTestReports/${env.JOB_BASE_NAME}/${env.BUILD_NUMBER}/html/AutomationReport.html"
                                                }
                                            ]
                                        },
                                        {
                                            "@type": "OpenUri",
                                            "name": "View Console Logs",
                                            "targets": [
                                                {
                                                    "os": "default",
                                                    "uri": "${env.BUILD_URL}consoleFull"
                                                }
                                            ]
                                        }
                                    ]
                                } """
                                if (notifyReportOnMSTeams) {
                                    httpRequest httpMode: "POST", acceptType: "APPLICATION_JSON", contentType: "APPLICATION_JSON", url: "${myTeamsWebHookURL}", requestBody: "${payload}"
                                    // office365ConnectorSend color: colorCode, message: "PROJECT - ${myProject} <br/> Test Execution Report on Jenkins - <${env.BUILD_URL}artifact/reports/html/AutomationReport.html>  <br/> Test Execution Report on CLOUD - <http://channels-ui.delta.com/UIAutomationTestReports/${env.JOB_BASE_NAME}/${env.BUILD_NUMBER}/html/AutomationReport.html>", status: "SUCCESS", webhookUrl: myTeamsWebHookURL;
                                }; 
                            };
                        } else {
                            if (notifyReportOnMSTeams) {
                                //Need to compose Error object
                                office365ConnectorSend color: colorCode, message: "PROJECT - ${myProject} <br/> Test Execution Report on Jenkins - <${env.BUILD_URL}artifact/reports/html/AutomationReport.html>  <br/> Test Execution Report on CLOUD - <http://channels-ui.delta.com/UIAutomationTestReports/${env.JOB_BASE_NAME}/${env.BUILD_NUMBER}/html/AutomationReport.html>", status: "SUCCESS", webhookUrl: myTeamsWebHookURL;
                            };
                        };

                        if (params.myDefaultEmail != null) {
                            emailext body: myProject + " - Full Test Automation Report(CLOUD): http://channels-ui.delta.com/UIAutomationTestReports/${env.JOB_BASE_NAME}/${env.BUILD_NUMBER}/html/AutomationReport.html \n" + myProject + " - Full Test Automation Report: " + jenkinsUrl + "artifact/reports/html/AutomationReport.html", subject: myProject + ' - Automation Report', to: myDefaultEmail;
                        } else {
                            emailext attachmentsPattern: 'reports/html/AutomationReport.html', body: myProject + " - Full Test Automation Report: " + jenkinsUrl + "artifact/reports/html/AutomationReport.html", subject: myProject + ' - Automation Report', to: 'thulasiraman.krishnan@delta.com';
                        }

                        if (myTags.contains("sitespeed")) {
                            if (myNode == 'OmniChannel_MAC' || myNode == 'OmniChannel_VM') {
                                archiveArtifacts "reports_sitespeed/**/*.*"
                            } else {
                                archiveArtifacts '/reports_sitespeed/**'
                            };
                            // slackSend channel: mySlackChannel, color: colorCode, message: myProject + " - SITESPEED AUTOMATION REPORT: " + jenkinsUrl + "artifact/reports_sitespeed/";
                        };
                        if (myTags.contains("TENON")) {
                            // slackSend channel: mySlackChannel, color: colorCode, message: myProject + " - TENON ACCESSIBILITY AUTOMATION REPORT: " + jenkinsUrl + "artifact/reports/tenonReports/";
                            // slackSend channel: "#tenon-accessibility", message: "Accessibility testing with Tenon was done on URL: " + myEnvironment;
                            emailext attachmentsPattern: 'reports/tenonReports/TenonReport*', body: myProject + " - TENON ACCESSIBILITY AUTOMATION REPORT: " + jenkinsUrl + "artifact/reports/tenonReports/", subject: 'Tenon Reports', to: myDefaultEmail;
                        };
                        // slackSend channel: mySlackChannel, color: '#00FF00', message: "Reporting Completed"
                    } catch (exc) {
                        echo 'Something failed while sending Notifications...!';
                        // slackSend channel: mySlackChannel, color: '#FF0000', message: "${myProject} ${STAGE_NAME} FAILED: JOB: ${env.JOB_NAME} [${env.BUILD_NUMBER}] BUILD URL: ${env.BUILD_URL}, EXCEPTION: $exc"
                        // if (params.notifyReportOnMSTeams) {
                    };
                } else {
                    echo 'Unknown build Status'
                    script {
                        def payloadForException = """
                        {
                            "type": "MessageCard",
                            "context": "https://schema.org/extensions",
                            "summary": "${myProject}",
                            "themeColor": "${colorCode}",
                            "sections": [
                                {
                                    "activityTitle": "Automation Build Notification - ${myProject}",
                                    "activitySubtitle": "Build: ${env.BUILD_NUMBER}",
                                    "activityImage": "https://www.sharoncarrtravel.com/wp-content/uploads/2016/06/Delta-Circle.png",
                                    "facts": [
                                        {
                                            "name": "Project:",
                                            "value": "${myProject}",
                                        },
                                        {
                                            "name": "Environment:",
                                            "value": "${myEnvironment}"
                                        },
                                        {
                                            "name": "Browser:",
                                            "value": "${myBrowser}"
                                        },
                                        {
                                            "name": "OS:",
                                            "value": "${myOSname}"
                                        },
                                        {
                                            "name": "Status:",
                                            "value": "Some Exception occured and - ${buildStatus}"
                                        }
                                    ],
                                    "markdown": true
                                }
                            ],
                            "potentialAction": [
                                {
                                    "@type": "OpenUri",
                                    "name": "View Console Logs",
                                    "targets": [
                                        {
                                            "os": "default",
                                            "uri": "${env.BUILD_URL}consoleFull"
                                        }
                                    ]
                                }
                            ]
                        } """
                        if (notifyReportOnMSTeams) {
                            httpRequest httpMode: "POST", acceptType: "APPLICATION_JSON", contentType: "APPLICATION_JSON", url: "${myTeamsWebHookURL}", requestBody: "${payloadForException}"
                            // office365ConnectorSend color: colorCode, message: "PROJECT - ${myProject} <br/> Test Execution Report on Jenkins - <${env.BUILD_URL}artifact/reports/html/AutomationReport.html>  <br/> Test Execution Report on CLOUD - <http://channels-ui.delta.com/UIAutomationTestReports/${env.JOB_BASE_NAME}/${env.BUILD_NUMBER}/html/AutomationReport.html>", status: "SUCCESS", webhookUrl: myTeamsWebHookURL;
                        };
                    };
                };
            };
        };
    };
};