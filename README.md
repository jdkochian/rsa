# rsa
Simple RSA cryptography algorithm in Java

See link for more info on the algorithm: https://en.wikipedia.org/wiki/RSA_(cryptosystem)
This code is by no means optimized. Working with large numbers in Java rarely is, hence the values being stored as longs to deal with overflow. 
fastExpo(long, int, int) uses the method of binary fast exponentiation.
