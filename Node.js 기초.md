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

Package versions (SemVer, Semantic Versioning)
> A.B.C (ex) 1.5.2) <br>
A => major version (major update, not compatible with earlier versions) <br>
B => minor version (minor update, backwards-compatible with earlier versions) <br>
C => patch version (very minor update, i.e. fixing a bug) <br>

> Related package.json icons
"^" => install/update without changing the major version (ex) ^1.1.1 => 1.1.1 <= version < 2.0.0) <br>
"~" => install/update without changing the minor version (ex) ~1.1.1 => 1.1.1 <= version < 1.2.0) <br>
">", "<", ">=", "<=", "=" => greater than, lesser than, equal too, etc. (ex) >1.1.1 => version higher than 1.1.1) <br>
"@latest" OR "@x" => always installs the latest version of the package

Other npm commands
npm outdated => checks whether there are updatable packages
npm update [package] => updates the designated package
npm update => updates all updatable packages
npm uninstall [package] OR npm rm [package] => uninstalls the designated package (disappears from node_modules & package.json)
npm search [query] => searches npm packages for the query (keywords attribute in package.json also show up)
npm info [package] => shows info about the designated package, the contents of package.json, dependencies, installable versions, etc.
npm adduser => used for logging in on npm (used to publish packages)
npm whoami => if logged in, shows WHO is logged in
npm logout => logs out (reverse of npm adduser)
npm version [version] => updates version of package.json
npm deprecate [package][version] [message] => when someone installs the designated version of the package, a deprecated warning shows up
npm publish => used to publish my packages
npm unpublish => used to withdraw my published packages (only works within 24 hours of publication)