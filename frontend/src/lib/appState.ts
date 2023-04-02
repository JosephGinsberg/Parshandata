import storage from './store';

interface Search {
	books: string[]; // change to book[]
	useKeri: boolean;
	taamTachton: boolean;
	display: string; // change to the possible options
	splitBy: string; // change to the possible options
	search: SearchParam[]; // rename to params
}

export const search = storage<Search>('search', {
	books: ['Genesis', 'Exodus'],
	useKeri: true,
	taamTachton: true,
	display: 'every',
	splitBy: 'word',
	search: [
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
	]
});

interface General {
	lang: 'en' | 'he';
	isDarkMode: boolean;
	isDevMode: boolean;
}

export const general = storage<General>('general', {
	lang: 'en',
	isDarkMode: false,
	isDevMode: false
});
