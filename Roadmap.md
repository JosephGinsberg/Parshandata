# Parshandata
An advanced Tanach search engine, allowing for complex, layered searches of the Hebrew Bible through a user-friendly interface

## Backend

### Near future
* Trop implementation 
>	Implementation in function | done
>	Legarmeh and other exceptions (Tropless words)  
>	Nekudot lists and letter lists for Shin and Sin (which means modifying seperate letters) | done
>	Ta'amei Emes  

* Before/after (with distance) | done
* Begins with/ends with | done
* searching for just letters (fontend and backend json) | done
* before/after - must be able to specify before what type: chars? letters? etc. | uneeded?
* is/is not and contains/does not contain parameter added to input object | done
* seperate trop and other searches | done (must have toggle on frontned to switch between word and tropword)
* make the shin/sin dot a letter for just letter searches and check if this has consequences | done

### Eventual
* Statistics
* Ranges of Pasukim
* Gemmatriah/Ciphers
* write-up documentation

## Frontnend

### Near Future
* Build _paramPanel on interface
* Design/Build input options to manipuate search on interface
* Have interface manipulate json properly
* Serialize json input on spring boot server

### Eventual
* add apache/nginx in front of app for static frontend files
* Hyperlink results location to source (sefaria)
* More features coming soon