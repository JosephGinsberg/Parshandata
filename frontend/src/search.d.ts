interface SearchRequest {
	books: string[]
	useKeri: boolean
	taamTachton: boolean
	display: string
	splitBy: string
	search: SearchParam[]
}

interface SearchParam {
	param: string
	type: string
	connector: string
	value?: string
	count?: number
	counttype?: string
	matchtype?: string
	level: number
}

interface SearchResponse {
	ok: boolean
	msg: string
	runtime: number
	matches: searchMatch[]
}

interface SearchMatch {
	bookname: string
	fullverse: string
	pasuk: string
	perek: string
	splitvalue: string
}

interface TanachBook {
	bookname: string
	grouping: string[]
	hebrew_name: string
}
