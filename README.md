# Hash_Cracker

To compile the project use `./build` or you can compile it yourself.

Usage to create hash-password dictionary (by convention the output file should have the .ser extension): `java HashCracker i [md5/sha1/...] [wordListPath] [outputFile]`

Usage to crack a list of hashes using a precomputed hash-password dictionary: `java c [dictionaryFile] [inputFile] [outputFile]`

An example wordlist can be found in the `data/wordlists/` directory and more can be found in this [repository](https://github.com/danielmiessler/SecLists/tree/master/Passwords) (where I found the example list). An example precomputed dictionary can be found in the `data/md5/` directory. An example list of hashes can be found in `hashes.txt`
