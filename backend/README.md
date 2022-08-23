# API reference
This is a reference guide for what the backend expects with a request. Each header is another expected value, with the value expected described in its section.  

## books
An array of the books you would like to search. The following example allows the user to search the Torah.

```json
"books": [
	"Genesis",
	"Exodus",
	"Leviticus",
	"Numbers",
	"Deuteronomy"
]
```

## useKeri
Allows the user to define to use the _keri_ instead of the _kesiv_.

```json
"useKeri": true
```

## taamTachton
Allows the user to define to swap out _trop_ of the first commandments for the second set.

```json
"taamTachton": true
```

## display
A reference for this is parameter is currently unavailable

```json
"display": "every"
```
## splitBy
A reference for this is parameter is currently unavailable

```json
"splitBy": "pasuk"
```

## search
### General info
Connector
Looks connects to the next block of the same type on the same level (checks both for conditions set)

Levels
Enables over-riding the next block of the same type, similar to parenthesis in math used to force grouping. 

### Blocks

#### condition
A general wrapper that enables you to search different types of blocks together. Example: Return every pasuk with a ``word`` with 2 alephs and a the pasuk ``also contains`` a lamed. (Optional for standard search)

```json
{
	"param": "condition",
	"connector": "none | and | or | distance",
	"level": 1 //, 2, 3 ...
}
```

#### input
The user's input of what they would like to search for, supports multiple charachters.

```json
{
	"param": "input",
	"type": "value | letter | nekudah | trop",
	"value": "String (by trop it's the name)",
	"matchtype": "contains | does not contain | is | is not | begins | ends",
	"count": 0,  //, 1, 2 ...
	"counttype": "equal | greater | less",
	"connector": "none | and | or | distance",
	"level": 1 //, 2, 3 ...
}
```

#### abstract
Used to narrow down the general scope of your search to a more specific domain. Example: Every pasuk that ``contains a word`` with 2 alephs instead of Every pasuk that ``contains`` 2 alephs.

```json
{
	"param": "abstract",
	"type": "letter | word | tropword",
	"count": 0, //, 1, 2 ...
	"counttype": "equal | greater | less",
	"matchtype":"contains | does not contain | begins | ends",
	"connector": "none | and | or | distance",
	"level": 1 //, 2, 3 ...
}
```

#### distance
Gives the user the greater ability to target the location of the match. The distance block will always follow after a block with the ``connector: distance``.

```json
{
	"param": "distance",
	"type": "before | after",
	"distancetype": "equal | greater | less",
	"count": 1, //, 2, 3 ...
	"connector": "none | and | or",
	"level": 1 //, 2, 3 ...
}
```