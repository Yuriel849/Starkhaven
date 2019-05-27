## npm (Node Package Manager)

Various JavaScript programs (modules) are uploaded as "packages" to the npm repository
"yarn" is an alternative package manager to npm, created by Facebook and often seen when using React or React Native
"package.json" is a file to manage the installed packages and their versions (create by typing "npm init" in the terminal)
> when using "npm init"
>> "package name" (package.json's "name" attribute) => name of the package <br>
"version" (package.json's "version" attribute) => version of the package <br>
"entry point" (package.json's "main" attribute) => usually the last file to use module.exports; all other files should be required in this last file, which is then itself required wherever this package is used; <br>
"test command" (package.json's "scripts / test" attribute) => commands for testing the code <br>
"git repository" (package.json's "repository" attribute) => the Git repository where the package's code is saved <br>
"keywords" (package.json's "keywords" attribute) => keywords used when searching for this pacakge on npmjs.com (npm's official website) <br>
"license" (package.json's "license" attribute) => designates license for this package <br>

"--save" is unnecessary when installing packages, it is set by default from npm5