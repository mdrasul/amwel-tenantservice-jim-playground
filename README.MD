# SOA Starter Automation Template

Template approach and forking.

The template branch is the main branch used for getting code down to forked repositories and is the default branch to this repository.

We do not want a platinum branch in this repository!! This will cause issues when syncing as when we fork a repository it will keep branches in sync also. Because of this we want to only submit code and branches to this repository that will not conflict with downstream repositories. If creating review or feature branches here, please ensure that we are using unique branch names.

When a change is made to the template branch that change will be sync'd to all forked repositories. Once the code is there it will be up to the developer to merge those changes to the active branches. If this is a bamboo spec it will only need to be merged to the branch of that repo that is being watched which should be platinum.n
### *For forked repositories, remove the above from your README.MD and continue below (updating/removing what is/isn't relevant to your project). Also, do NOT delete the `template` branch from your project as it will be used to communicate with the parent project...*

# SOA Functional Test Automation Starter Project
@author: chan.suom@americanwell.com

## Introduction
This project is boot-strapped to include source code classes and test classes to automate for API/services, web UI, mobile web view, and mobile native app testing. This framework exists to start your team with the tools and libraries needed to automate in a consistent manner across our organization. With the example code and example tests, you should be able to run these tests on your machine without issue as long as you have configured your system for automation.

This is a [Gradle](https://gradle.org/) project (v6.6 is currently supported) and our dependencies and tasks (build, clean, test, etc.) are managed in the `build.gradle` file. Our code is compatible with [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html). [TestNG](https://testng.org/doc/) (v7.3.0 is currently supported) is the testing framework. Test results are consumed and managed through [Report Portal](https://reportportal.io/) on our local server at http://bos-ts-rp-01:8080. [REST-Assured](http://rest-assured.io/) (v3.3.0 is currently supported) is the requests library used for API testing. [Selenium Webdriver](https://www.seleniumhq.org/projects/webdriver/) (v3.141 is currently supported) is the library used for web UI testing. [Appium](http://appium.io/) (v1.18.0 is currently supported) is the library we use for mobile automation. 

## Installation

Please review the following Wiki pages for process and system configurations:
QA Automation Guidelines: https://wiki.americanwell.com/display/QA/QA+Automation+Process
Mobile Automation Configuration: https://wiki.americanwell.com/display/QA/Mobile+Automation+Configuration
Web Automation: We have a number of Wiki articles detailing the setup for automation projects, but to be generic, make sure you install the latest Chromedriver and Geckodriver to your machine and set your system path to these executables. There are many ways to set up and I will include such links I quickly found. Note that I am not vetting these for you - I am on a Mac and use Homebrew ;) 
* Chromedriver: https://zwbetz.com/download-chromedriver-binary-and-add-to-your-path-for-automated-functional-testing/
* Firefox/Geckodriver:
    * Mac: https://medium.com/dropout-analytics/selenium-and-geckodriver-on-mac-b411dbfe61bc
    * Windows: https://stackoverflow.com/questions/42524114/how-to-install-geckodriver-on-a-windows-system

Clone the repository for testing: `ssh://git@bitbucket.americanwell.com:7999/qaaut/<test_project>.git`
[IntelliJ](https://www.jetbrains.com/idea/) is highly recommended for development and testing. Version 2019.2 of the Community Edition is the stable release for our tests. If you decide to use another IDE, do so at your own peril ;).

Open IntelliJ and open the project by the `build.gradle` file so that the dependencies will be installed and indexed for the project.

We will need to install a couple of plug-ins to get us started:
* [Lombok](https://projectlombok.org/setup/intellij) - while we are not using this plug-in to streamline getters/setters, we are using this to streamline logging with the class annotation of `@Slf4j`. Annotation processing needs to be configured in IntelliJ. Go to the Preferences -> Build, Execution, Deployment -> Compiler -> Annotation Processors:
    * Enable annotation processing check box is checked
    * Obtain processors from project classpath option is selected
* [SonarLint](https://www.sonarlint.org/intellij/) - we will be using this linter to ensure we meet modern Java coding practices. You can use this tool to scan your file via the SonarLint tool in the IntelliJ editor (bottom of UI)

In addition, make sure IntelliJ's Java compiler is pointing to JDK 11. Go to the Preferences -> Build, Execution, Deployment -> Compiler -> Java Compiler:
* Set project bytecode version to 11
* Set target bytecode version to 11 for both the main and test modules of the project

Lastly, set the IntelliJ Gradle JVM to JDK 11 also. Go to the Preferences -> Build, Execution, Deployment -> Build Tools -> Gradle:
* set Gradle JVM to 11

### Mobile Testing
The framework supports real physical devices (Android, iOS). Either platform device must be provisioned by IT. Get the Android and iOS device name and UDID values and add these devices to the config.yaml under the respective devices list section. We will not support issues with personal devices. Use these on your own volition. Virtual devices are supported also and will be described further down.

You can get the Android details from installing Android Studio (instructions below) and using this [ADB guide](https://developer.android.com/studio/command-line/adb) to getting the information from your attached Android device.

For iOS devices, you can use iTunes to get the device name and UDID values.

**Appium Server**
We are using Appium for local device testing at the moment. You may use either the [Appium Server](http://appium.io/docs/en/about-appium/getting-started/) itself or the [Appium Desktop GUI](https://github.com/appium/appium-desktop/releases). The supported use is to run one instance of the server as the server can handle multiple devices through the use of ports (which this framework is already configured to do). Should be introduce local mobile device testing through Selenium Grid, you would only have to update the config.yaml and Config.java to call on the Selenium Grid config key/value pair and the Config class method when setting your capabilities for the server URL.

**Android Virtual Devices**
Download and install [Android Studio](https://developer.android.com/studio/install). Configure it as needed and create virtual devices with the [AVD Manager](https://developer.android.com/studio/run/managing-avds). You will then need to get the device name and UDID from the virtual device and add that to the config.yaml under the Android devices list.

**iOS Virtual Devices**
Download and install Xcode. Either get the latest Xcode from the App Store or get a [compatible version](https://developer.apple.com/download/more/?=xcode) if you are on an older version of Mac OS(X) - note that you will need an Apple Id account for the later option. Once Xcode is installed, simply launch [Simulator](https://developer.apple.com/library/archive/documentation/IDEs/Conceptual/iOS_Simulator_Guide/GettingStartedwithiOSSimulator/GettingStartedwithiOSSimulator.html) and start your desired virtual device. You will then need to get the device name and UDID from the virtual device and add that to the config.yaml under the iOS devices list.

#### Create a Report Portal Account
You will need a Report Portal account. Contact christopher.roberts@americanwell.com. Once you are set up, you can copy the properties from your Report Portal profile into the `reportportal.properties` file located in `src/main/resources/reportportal.properties.sample`. Rename the file from `reportportal.properties.sample` to `reportportal.properties` for it to be recognized by the Report Portal library.


#### Setup config.yaml

For testing, ensure your `config.yaml` file is set up at `src/test/resources/config.yaml`. Copy the included `config.sample` file and rename the new file's extension to `.yaml`. Once you are confident in moving over to your team's application, remove all example-related configs and adjust the values of other settings to match your project's needs.

Point your tests to the desired service url, web app url, and mobile testing url by filling these values in with the URLs provided by the team to get started.

#### Start the Appium server for local mobile device testing
Run `appium` on the command line (or start Appium Desktop) and it should start up the Appium server on 0.0.0.0:4723. Currently this framework supports Appium Server v1.18.0.

Depending on the version of Chrome running on an Android device; your tests may fail as Appium's built-in Chromedriver may not be able to drive an older version of Chrome. For a safeguard, you can run `appium --allow-insecure chromedriver_autodownload` instead. Note that this may slow down your tests as Appium will need to check the Chrome version on your Android device and download the necessary Chromedriver version to execute the tests. 
#### Running the smoke test 
To test that everything is set up for development, run any of the Gradle tasks related to smoke tests such as `./gradlew testWebSmoke`. All the web smoke tests should run and you should see a launch under your space in Report Portal along with the results - you should have set up your RP account and your reportportal.properties file by now ;)

Do the same for the services: `./gradlew testServiceSmoke`. The tests should run against the designated service URL under the `serviceURL` config key.

Lastly, run the mobile smoke tests for both web view and native app testing with `./gradlew testMobileWebSmoke` and `./gradlew testMobileAppSmoke` relative to which type of testing you wish to conduct. I shouldn't have to say it, but run one of these at a time :|

The included APK and IPA are the Consumer Mobile SDK Sample apps. Make sure to place your desired APK and IPA file in the `apps` directory when you are ready to start testing your project's mobile apps. It is good practice to not commit these files with your work. The .gitignore file should be updated to reflect this.

## Development
### Feature Branches
All work should have an associated JIRA ticket when possible. If it is a minor fix, use the label `NOJIRA` when creating your feature branch. The format of the feature branch should be as follows:

`review/QAAUT-1234-short-description-of-work`

### GIT Flow (General)
Make sure you branch off the `platinum` branch. 
```
git checkout platinum
git checkout -b review/QAAUT-1234-my-feature-branch
```
When you are satisfied with the changes, add the modified or new files to staging (ready to commit status).

First, check the status of your changes first (do this often!) You will be presented with a list of untracked and tracked changes:
`git status`

You can add one or a few files at a time (separate by space)
`git add path/to/changed/file path/to/new/file`

Or you can add all your changes at once:
`git add -A` or `git add .`

Do a `git status` again to ensure you have all the files you intend for your commit. When you are satisfied use `git commit` which will bring up the interactive VI(M) editor. Follow this format for your commit message. Be sure to add the JIRA ticket(s) for any work included into the commit message; this will sync with the Jira ticket to provide a link to the BitBucket commit and pull request:
```
QAAUT-1234 Short Jira Title
- ExampleClass was updated due to dependencies on refactor of method Foo.bar()
- added new method doThis() in ExampleTwoClass to do a new thing
- added test cases: QAAUT-1000, QAAUT-1001, QAAUT-1002
```

After your commit message is completed. It is time to push your changes for a code review (or just to push up for safe-keeping!). Make sure to rebase your branch with `platinum` to ensure you are up to date with the latest changes from `platinum`:
```
git checkout platinum //make sure your changes are either stashed or committed before you can checkout a new branch
git pull //this gets all the latest changes from origin to your local platinum
git checkout review/QAAUT-1234-my-feature-branch
git rebase platinum //this places all the latest changes from platinum behind your changes
```
Now you are ready to send your branch upstream. You will only need to use this specific command the first time you are pushing up a new branch to origin:
`git push -u origin review/QAAUT-1234-my-feature-branch`

All new commits from this branch (after staging your changes) will use the command: `git push`
The new commit's message does not need the header as shown in the commit message example. Use the following format:
```
- fixed method Foo.boo() that was always returning true
```

If you have a typo or some insignificant change, you can just amend your previous commit after adding your changes to staging: `git commit --amend` 
This will bring up the interactive editor. You can modify your message to include the change or keep the same message. The next command is to send this amended commit upstream since you are sending up the same commit hash: `git push -f`

Outside of this, if you're havin' GIT problems, I feel bad for you, son, I got 99 problems but a commit ain't one. (Yes, that was bad) In any case, please reach out to anyone in the TestOps or Integration/Regression squad for help.

## Pull Requests
**Before you create a pull request, ensure your changes do not break any tests! 

Be sure to include the ticket number in your PR's title so that the relation between it and the Jira ticket is obvious. This also links the branch/commit/PR from BitBucket to Jira.

Please be proactive with your PR. If you get a comment, respond as soon as you can. If you are a reviewer, get used to marking the PR as "Needs Work" if you have comments/concerns on the functionality and the coding standards are not met.

When making changes to your PR, make sure they are **new** commits (refrain from `git commit --amend` unless, as stated above, it's for minor changes) so we can keep track of the changes. When you get the required number of approvals, then you must squash your commits and push the branch back up for a final review before merging.

You can use the interactive editor to squash your commits with the command `git rebase -i HEAD~n` where `n` is the number of commits from your branch that you wish to squash. 

**NOTE:** If you find that there can be improvements made to the parent framework, please create a pull request targeting the [soa_starter_automation project](https://bitbucket.americanwell.com/projects/QAAUT/repos/soa_starter_automation/browse) with the `template` branch as your target. Please add `Chan Suom` and `Christopher Roberts` to the PR. An approval from `Chan Suom` is required before any PR can be merged into the `soa_starter_automation` project.