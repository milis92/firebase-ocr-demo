<h1 align="center">
  <img src="app/src/main/ic_launcher-web.png" width="100px" />
</h1>

<p align="center">
    Boilerplate Android app
</p>

## Project structure and setup

This project is using [Gradle](https://docs.gradle.org/current/userguide/userguide.html) 
build system, and every build task should be executed using Gradle wrapper included in the root of this project.

### Gradle file structure
*Note: Standard Gradle files are not included in this list
1. common_dependencies.gradle - Contains common dependencies and plugins for 
different modules
2. test_dependencies.gradle - Contains test specific dependencies
3. test_reports.gradle - Formatting utility for test reports (used mostly for ci)
      
### Non project file structure
1. .github/scripts/cript.sh - Wrapper script around GPG that crypts/decrypts files (use this if you need to store sensitive files in the repo) 
2. .github/scripts/shahash.sh - Utility that prints converts SHA256 encoded values to base64 (use this if you need to modify facebook hashes)
3. .github/scripts/certfingerprint.sh - Utility that prints SSL certificate fingerprints for a given domain (use this for certificate pinning) 

### Project file structure:
This project is built using [dynamic-feature](https://developer.android.com/guide/app-bundle/dynamic-delivery) modules (without dynamic-delivery)

Modules:
1. app - Main application module 
2. core - Core application dependencies and domain implementation
5. home - Dynamic module containing empty examples on how to implement features

###  Application flavors:
1. Production
2. Staging 

*Note: Every gradle task will be generated for both of these variants. so for example if you want to run tests against staging BE or staging configuration you can do it as
```bash
./gradlew testStagingDebug
```
## Code quality
This project is using ktlint with official ruleset for kotlin. To add these rules to your IDE run:
```bash
ktlintApplyToIdea
``` 
If you like, you can install pre-commit hooks which will check and fix large number of lint related issues for every commit.
To install lint-check pre-commit hook run
```bash
./gradlew addKtlintCheckGitPreCommitHook
```
To install lint-apply pre-commit hook run
```bash
./gradlew addKtlintFormatGitPreCommitHook
```
### Github Actions
To customise [GithubActions](https://help.github.com/en/actions/automating-your-workflow-with-github-actions/about-github-actions), visit ./github/workflows folder.
To use default provided configuration:
1. Enable GithubActions
2. Configure [PlayStore publisher](https://github.com/Triple-T/gradle-play-publisher#quickstart-guide), and add encrypted version of playstore service account to the .github root folder
3. Configure `CRIPT_SECRET` variable in project settings (This is encrypton/decription key used to decrypt service account file (or any other sensitive file you want to keep in your repo)) 

*Note: Please dont add your service account as plain file, rather use the cript scrip and add encrypted version (and decrypt the file during the execution of the workflow).

