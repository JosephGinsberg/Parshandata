<script lang="ts">
	import { onMount } from 'svelte'
	import { globalState } from '../../globalState'
	import Button from '$lib/Button.svelte'
	import Dropdown from '$lib/Dropdown.svelte'

	let searchRequest: SearchRequest
	globalState.subscribe(value => ({ searchRequest } = value))
	$: globalState.update(state => {
		state.searchRequest = searchRequest
		return state
	})

	const TanakhBooks: TanachBook[] = [
			{
				bookname: 'Genesis',
				grouping: ['Tanakh', 'Torah', 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Exodus',
				grouping: ['Tanakh', 'Torah', 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Leviticus',
				grouping: ['Tanakh', 'Torah', 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Numbers',
				grouping: ['Tanakh', 'Torah', 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Deuteronomy',
				grouping: ['Tanakh', 'Torah', 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Joshua',
				grouping: ['Tanakh', 'Prophets', 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Judges',
				grouping: ['Tanakh', 'Prophets', 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Samuel_1',
				grouping: ['Tanakh', 'Prophets', 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Samuel_2',
				grouping: ['Tanakh', 'Prophets', 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Kings_1',
				grouping: ['Tanakh', 'Prophets', 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Kings_2',
				grouping: ['Tanakh', 'Prophets', 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Isaiah',
				grouping: ['Tanakh', 'Prophets', 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Jeremiah',
				grouping: ['Tanakh', 'Prophets', 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Ezekiel',
				grouping: ['Tanakh', 'Prophets', 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Hosea',
				grouping: ['Tanakh', 'Prophets', 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Joel',
				grouping: ['Tanakh', 'Prophets', 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Amos',
				grouping: ['Tanakh', 'Prophets', 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Obadiah',
				grouping: ['Tanakh', 'Prophets', 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Jonah',
				grouping: ['Tanakh', 'Prophets', 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Micah',
				grouping: ['Tanakh', 'Prophets', 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Nahum',
				grouping: ['Tanakh', 'Prophets', 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Habakkuk',
				grouping: ['Tanakh', 'Prophets', 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Zephaniah',
				grouping: ['Tanakh', 'Prophets', 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Haggai',
				grouping: ['Tanakh', 'Prophets', 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Zechariah',
				grouping: ['Tanakh', 'Prophets', 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Malachi',
				grouping: ['Tanakh', 'Prophets', 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Psalms',
				grouping: ['Tanakh', 'Writings', 'Poetic books'],
				hebrew_name: ''
			},
			{
				bookname: 'Proverbs',
				grouping: ['Tanakh', 'Writings', 'Poetic books'],
				hebrew_name: ''
			},
			{
				bookname: 'Job',
				grouping: ['Tanakh', 'Writings', 'Poetic books'],
				hebrew_name: ''
			},
			{
				bookname: 'Ruth',
				grouping: ['Tanakh', 'Writings', 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Esther',
				grouping: ['Tanakh', 'Writings', 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Song_of_Songs',
				grouping: ['Tanakh', 'Writings', 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Lamentations',
				grouping: ['Tanakh', 'Writings', 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Ecclesiastes',
				grouping: ['Tanakh', 'Writings', 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Daniel',
				grouping: ['Tanakh', 'Writings', 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Ezra',
				grouping: ['Tanakh', 'Writings', 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Nehemiah',
				grouping: ['Tanakh', 'Writings', 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Chronicles_1',
				grouping: ['Tanakh', 'Writings', 'Prose books'],
				hebrew_name: ''
			},
			{
				bookname: 'Chronicles_2',
				grouping: ['Tanakh', 'Writings', 'Prose books'],
				hebrew_name: ''
			}
		],
		booksByGroup = (group: string) => {
			const books: string[] = []
			TanakhBooks.forEach(book => {
				if (book.grouping.indexOf(group) >= 0) books.push(book.bookname)
			})
			return books
		},
		compareGroups = (a: any, b: any) => {
			a = JSON.stringify(a.sort())
			b = JSON.stringify(b.sort())
			return a === b
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
			const groups = ['Tanakh', 'Torah', 'Prophets', 'Prose books', 'Writings', 'Poetic books']

			if (selectedBooks.length === TanakhBooks.length) return 'Tanakh'

			let groupCount = 0,
				groupName = ''

			groups.forEach(group => {
				const checkerResult = checker(group)
				if (!checkerResult) return

				groupCount++
				groupName = group
			})

			if (groupCount === 2 && compareGroups(selectedBooks, booksByGroup('Writings')))
				return 'Writings'
			else if (
				groupCount === 1 ||
				(groupCount && compareGroups(selectedBooks, booksByGroup(groupName)))
			)
				return groupName
			else return selectedBooks.length !== 1 ? selectedBooks.length + ' Books' : selectedBooks[0]
		},
		checker = (groupName: string) => {
			const bookGroup = booksByGroup(groupName)
			const matchCount = bookGroup.filter(book => searchRequest.books.indexOf(book) >= 0)
			return matchCount.length === bookGroup.length
		}

	let bookOptions: dropdownInput[],
		isDarkMode = false
		onMount(() => isDarkMode = document?.documentElement.classList.contains('dark'))
	$: {
		bookOptions = [
			{
				checked: checker('Tanakh'),
				text: 'Tanakh',
				value: 'Tanakh',
				originalValue: checker('Tanakh'),
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
				checked: checker('Prophets'),
				text: 'Prophets',
				value: 'Prophets',
				originalValue: checker('Prophets'),
				grouping: []
			},
			{
				checked: checker('Writings'),
				text: 'Writings',
				value: 'Writings',
				originalValue: checker('Writings'),
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
		for (const book of TanakhBooks) {
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
			search={true}
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

	<div id="bottom">
		<Button
			classes="muted small"
			icon={isDarkMode ? 'lightmode' : 'darkmode'}
			style="position: absolute;bottom: 0px;right: 0px;"
			on:click={() => {
				document.documentElement.classList.contains('dark')
					? document.documentElement.classList.remove('dark')
					: document.documentElement.classList.add('dark')
				isDarkMode = !isDarkMode
			}}
		/>
	</div>
</aside>

<style>
	aside {
		position: relative;
		display: flex;
		flex-direction: column;
		width: 240px;
		min-width: 240px;
		height: 100%;
		padding-inline-start: var(--sidePadding);
	}
	.title {
		padding-bottom: 0.5rem;
		font-weight: 500;
	}
	.option {
		padding-bottom: 1rem;
	}
	#bottom {
		position: absolute;
		bottom: 0;
		right: 0px;
	}
</style>
