## npm (Node Package Manager)

##### Various JavaScript programs (modules) are uploaded as "packages" to the npm repository
##### "yarn" is an alternative package manager to npm, created by Facebook and often seen when using React or React Native
##### "package.json" is a file to manage the installed packages and their versions (create by typing "npm init" in the terminal)
> when using "npm init"
>> "package name" (package.json's "name") => name of the package <br>
"version" (package.json's "version") => version of the package <br>
"entry point" (package.json's "main") => usually the last file to use module.exports; all other files should be required in this last file, which is then itself required wherever this package is used; <br>
"test command" (package.json's "scripts / test") => commands for testing the code <br>
"git repository" (package.json's "repository") => the Git repository where the package's code is saved <br>
"keywords" (package.json's "keywords") => keywords used when searching for this pacakge on npmjs.com (npm's official website) <br>
"license" (package.json's "license") => designates license for this package <br>

package.json's "script" attribute is where npm commands are saved
> ex) "npm test" in the terminal will run the code registered for the "test" command

"--save" is unnecessary when installing packages, it is set by default from npm5
"node_modules" folder is where the installed pacakges are located
> There are multiple packages in node_modules after installing express, because while only express was installed, express itself is dependent on all the other packages