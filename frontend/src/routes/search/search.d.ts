interface searchRequest{
	books: string[]
	keriUkesiv: boolean
	taamTachton: boolean
	display: string
	splitBy: string
	onlyinclude: string
	remove: string[]
	search: searchParam[]
}

interface searchParam{
	param: string
	type: string
	connector: string
	value?: string
	count?: number
	level: number
}

interface searchResponse{
	ok: boolean
	msg: string
	runtime: number
	matches: searchMatch[]
}

interface searchMatch{
	bookname: string
	fullverse: string
	pasuk: string
	perek: string
	splitvalue: string
}