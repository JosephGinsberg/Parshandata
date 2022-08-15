<script lang="ts">
	import { globalState } from '../../globalState'
	import Button from '$lib/Button.svelte'
	import Dropdown from '$lib/Dropdown.svelte'
	import MulitLevelDropdown from '$lib/MulitLevelDropdown.svelte'

	let searchRequest: SearchRequest
	globalState.subscribe(value => ({ searchRequest } = value))
	$: globalState.update(state => {
		state.searchRequest = searchRequest
		return state
	})

	const tanachBooks: TanachBook[] = [
			{
				bookname: 'Genesis',
				grouping: ['Tanach', 'Torah', 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Exodus',
				grouping: ['Tanach', 'Torah', 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Leviticus',
				grouping: ['Tanach', 'Torah', 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Numbers',
				grouping: ['Tanach', 'Torah', 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Deuteronomy',
				grouping: ['Tanach', 'Torah', 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Joshua',
				grouping: ['Tanach', "Nevi'im", 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Judges',
				grouping: ['Tanach', "Nevi'im", 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Samuel_1',
				grouping: ['Tanach', "Nevi'im", 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Samuel_2',
				grouping: ['Tanach', "Nevi'im", 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Kings_1',
				grouping: ['Tanach', "Nevi'im", 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Kings_2',
				grouping: ['Tanach', "Nevi'im", 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Isaiah',
				grouping: ['Tanach', "Nevi'im", 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Jeremiah',
				grouping: ['Tanach', "Nevi'im", 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Ezekiel',
				grouping: ['Tanach', "Nevi'im", 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Hosea',
				grouping: ['Tanach', "Nevi'im", 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Joel',
				grouping: ['Tanach', "Nevi'im", 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Amos',
				grouping: ['Tanach', "Nevi'im", 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Obadiah',
				grouping: ['Tanach', "Nevi'im", 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Jonah',
				grouping: ['Tanach', "Nevi'im", 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Micah',
				grouping: ['Tanach', "Nevi'im", 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Nahum',
				grouping: ['Tanach', "Nevi'im", 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Habakkuk',
				grouping: ['Tanach', "Nevi'im", 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Zephaniah',
				grouping: ['Tanach', "Nevi'im", 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Haggai',
				grouping: ['Tanach', "Nevi'im", 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Zechariah',
				grouping: ['Tanach', "Nevi'im", 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Malachi',
				grouping: ['Tanach', "Nevi'im", 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Psalms',
				grouping: ['Tanach', 'Ketuvim', 'Poetic books'],
				hebrew_name: ''
			},
			{
				bookname: 'Proverbs',
				grouping: ['Tanach', 'Ketuvim', 'Poetic books'],
				hebrew_name: ''
			},
			{
				bookname: 'Job',
				grouping: ['Tanach', 'Ketuvim', 'Poetic books'],
				hebrew_name: ''
			},
			{
				bookname: 'Ruth',
				grouping: ['Tanach', 'Ketuvim', 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Esther',
				grouping: ['Tanach', 'Ketuvim', 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Song_of_Songs',
				grouping: ['Tanach', 'Ketuvim', 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Lamentations',
				grouping: ['Tanach', 'Ketuvim', 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Ecclesiastes',
				grouping: ['Tanach', 'Ketuvim', 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Daniel',
				grouping: ['Tanach', 'Ketuvim', 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Ezra',
				grouping: ['Tanach', 'Ketuvim', 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Nehemiah',
				grouping: ['Tanach', 'Ketuvim', 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Chronicles_1',
				grouping: ['Tanach', 'Ketuvim', 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Chronicles_2',
				grouping: ['Tanach', 'Ketuvim', 'Prose books'],
				hebrew_name: ''
			}
		],
		booksByGroup = (group: string) => {
			const books: string[] = []
			tanachBooks.forEach(book => {
				if (book.grouping.indexOf(group) >= 0) books.push(book.bookname)
			})
			return books
		},
		updateBookSelection = (selectedBooks: dropdownInput[]) => {
			const exceptions: dropdownInput[] = []
			let selectedValue: string[] = []

			// add individually selected books
			selectedBooks.forEach(book => {
				if (!book.grouping?.length && book.checked !== book.originalValue) {
					exceptions.push(book)
					return
				} else if (!book.checked || book.checked === book.originalValue) return
				selectedValue.push(book.value)
			})

			// add/remove groups
			exceptions.forEach(group => {
				const bookGroup = booksByGroup(group.value)
				if (group.checked && !group.originalValue) {
					selectedValue = [...selectedValue, ...bookGroup]
				} else if (!group.checked && group.originalValue) {
					selectedValue = selectedValue.filter(singleBook => bookGroup.indexOf(singleBook) === -1)
				}
			})

			// update globalState and avoid doubles
			searchRequest.books = [...new Set(selectedValue)]
		},
		displayBookSelection = (selectedBooks: string[]) => {
			const groups = ['Tanach', 'Torah', "Nevi'im", 'Ketuvim', 'Prose books', 'Poetic books']

			if (selectedBooks.length === tanachBooks.length) return 'Tanach'

			let groupCount = 0,
				groupName = ''

			groups.forEach(group => {
				const checkerResult = checker(group)
				if (!checkerResult) return

				groupCount++
				groupName = group
			})

			if (groupCount === 1 || groupName === 'Prose books') return groupName
			else return selectedBooks.length !== 1 ? selectedBooks.length + ' Books' : selectedBooks[0]
		},
		checker = (groupName: string) => {
			const bookGroup = booksByGroup(groupName)
			const matchCount = bookGroup.filter(book => searchRequest.books.indexOf(book) >= 0)
			return matchCount.length === bookGroup.length
		}

	let bookOptions: dropdownInput[]
	$: {
		bookOptions = [
			{
				checked: checker('Tanach'),
				text: 'Tanach',
				value: 'Tanach',
				originalValue: checker('Tanach'),
				grouping: []
			},
			{
				checked: checker('Torah'),
				text: 'Torah',
				value: 'Torah',
				originalValue: checker('Torah'),
				grouping: []
			},
			{
				checked: checker("Nevi'im"),
				text: "Nevi'im",
				value: "Nevi'im",
				originalValue: checker("Nevi'im"),
				grouping: []
			},
			{
				checked: checker('Ketuvim'),
				text: 'Ketuvim',
				value: 'Ketuvim',
				originalValue: checker('Ketuvim'),
				grouping: []
			},
			{
				checked: checker('Prose books'),
				text: 'Prose books',
				value: 'Prose books',
				originalValue: checker('Prose books'),
				grouping: []
			},
			{
				checked: checker('Poetic books'),
				text: 'Poetic books',
				value: 'Poetic books',
				originalValue: checker('Poetic books'),
				grouping: []
			},
			{
				checked: false,
				text: '',
				value: ''
			}
		]
		for (const book of tanachBooks) {
			// check user's language preference

			bookOptions.push({
				checked: searchRequest.books.indexOf(book.bookname) >= 0,
				value: book.bookname,
				text: book.bookname.replaceAll('_', ' '),
				grouping: book.grouping
			})
		}
	}
</script>

<aside>
	<div class="option">
		<div class="title">Books to search</div>
		<Dropdown
			placeholder="{displayBookSelection(searchRequest.books)} selected"
			options={bookOptions}
			returnOriginal={true}
			on:change={e => updateBookSelection(e.detail.value)}
		/>
	</div>

	<div class="option">
		<div class="title">Use <i>keri</i> instead of <i>kesiv</i></div>
		<Button
			classes="small {searchRequest.useKeri ? 'default' : 'muted'}"
			text="Yes"
			on:click={() => (searchRequest.useKeri = true)}
		/>
		<Button
			classes="small {!searchRequest.useKeri ? 'default' : 'muted'}"
			text="No"
			on:click={() => (searchRequest.useKeri = false)}
		/>
	</div>

	<div class="option">
		<div class="title">Use <i>taam tachton</i></div>
		<Button
			classes="small {searchRequest.taamTachton &&
			!!searchRequest.books.join('').match(/Genesis|Exodus|Deuteronomy/)
				? 'default'
				: 'muted'}"
			text="Yes"
			on:click={() => (searchRequest.taamTachton = true)}
		/>
		<Button
			classes="small {!searchRequest.taamTachton &&
			!!searchRequest.books.join('').match(/Genesis|Exodus|Deuteronomy/)
				? 'default'
				: 'muted'}"
			text="No"
			on:click={() => (searchRequest.taamTachton = false)}
		/>
	</div>
</aside>

<style>
	aside {
		display: grid;
		grid-template-columns: 100%;
		gap: 16px;
		width: 240px;
		min-width: 240px;
		padding-inline-start: var(--sidePadding);
		/* padding-inline-end: var(--sidePadding); */
	}
	.title {
		padding-bottom: 0.5rem;
		font-weight: 500;
	}
</style>
