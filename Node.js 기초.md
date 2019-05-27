Install latest npm: command "npm install -g npm"
Install packages: command "npm install [package]"
Install packages according to package.json: "npm install"
Install development packages: command "npm install --save-dev [package]"
> Install packages only used for development, not for production

Install packages globally: command "npm install --global [package]"
> The package is not installed in the current directory's node_modules, but in the directory where npm is installed. <br>
Packages installed globally can be accessed by simple terminal commands. <br>
>> ex) rimraf node_modules (deletes node_modules directory in current location)

Alternative to global installation
>> Globally installed packages don't show up in package.json, so may cause confusion in production. <br>
Instead, install as a development package, then access by adding "npx"
>>> ex) npx rimraf node_modules

Abbreviations
> npm install => npm i <br>
--save-dev => -D <br>
--global -g <br>