## Web Server with 'express' Module

### Express adds additional functions to the request and response objects used by the "http" module, not to mention adding various helpful methods. Overall, using the express web server framework is much preferred over using just the "http" module.

### Contents
1. Express-generator
> Package to easily set up express application <br>
Install globally => "npm i -g express-generator" <br>
Set up express application => Move to directory and "express [project name] --view=pug" <br>
>> "--view=pug" => express-generator installs Jade as the default template engine, but Jade has long been replaced with PUG
>>> Use "--view=ejs" to install EJS as the template engine