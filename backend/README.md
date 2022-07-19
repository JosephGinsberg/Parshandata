## Backend
This is a reference 

Levels


Param

#### condition
```json
{
	"param": "condition",
	"type": "contains | does not contain",
	"connector": "none | and | or | distance",
	"level": 1 //, 2, 3 ...
}
```

#### abstract
```json
{
	"param": "abstract",
	"type": "letter | word | tropword",
	"count": 0, //, 1, 2 ...
	"counttype": "equal | greater | less",
	"connector": "none | and | or | distance",
	"level": 1 //, 2, 3 ...
}
```

#### distance
```json
{
	"param": "distance",
	"type": "before | after",
	"distancetype": "equal | greater | less",
	"distance": 1, //, 2, 3 ...
	"connector": "none | and | or",
	"level": 1 //, 2, 3 ...
}
```

#### input
```json
{
	"param": "input",
	"type": "value | letter | nekudah | trop",
	"value": "string (by trop it's the name)",
	"matchtype": "contains | is",
	"count": 0,  //, 1, 2 ...
	"counttype": "equal | greater | less",
	"connector": "none | and | or | distance",
	"level": 1 //, 2, 3 ...
}
```