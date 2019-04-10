/*
How to execute files with Node.js
    Type "node + [file name]" into the terminal/console and node.exe runs the designated file
    Type just "node" to enter the node shell, where I can directly type JS code and run it by hitting "enter";
        exit the node shell by typing "ctrl + C" twice
*/

console.log('Hello world, brought to you by Node.js');
    /* "console" is a global object, and as such can be used anywhere
        when console.log is used, "undefined" is also printed on the console,
            this is because all JS functions MUST return something, but console.log does not return anything
            thus, "undefined" meaning there's nothing (undefined != null)

        Important Node.js global objects
            console : to show data on the console
            process : to access information about the current process
            exports : to handle modules
    */