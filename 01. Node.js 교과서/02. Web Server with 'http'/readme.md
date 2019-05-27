## Web Server with 'http' Module

### Contents
1. XXX
2. 
3. 
4. 'cluster' module
> Node.js uses a single thread, but the 'cluster' module allows the creation of multiple processes using multiple CPU cores
>> Pros => Can handle multiple requests more quickly and efficiently
Cons => Cannot share a common session (Can be fixed with Redis)