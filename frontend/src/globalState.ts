import { writable } from 'svelte/store'

let books: string[] = ['Genesis', 'Exodus'],
	searchJSON: SearchParam[] = [
		{
			param: 'input',
			type: 'letter',
			value: 'י',
			matchtype: 'contains',
			count: 3,
			counttype: 'equal',
			connector: 'none',
			level: 1
		}
	],
	searchRequest: SearchRequest = {
		books,
		useKeri: true,
		taamTachton: true,
		display: 'every',
		splitBy: 'word',
		search: searchJSON
	}

export const globalState = writable({
	searchRequest
})
