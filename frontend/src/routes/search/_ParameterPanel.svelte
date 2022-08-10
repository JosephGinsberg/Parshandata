<script lang="ts">
	import { globalState } from '../../globalState'
	import Button from '$lib/Button.svelte'
	import Dropdown from '$lib/Dropdown.svelte'

	let searchRequest: SearchRequest
	globalState.subscribe(value => ({ searchRequest } = value))
	$: globalState.update(state => {
		state.searchRequest = searchRequest
		return state
	})

	let tanachBooks: TanachBook[] = [
		{
			bookname: 'Genesis',
			inSet: 'Torah',
			hebrew: ''
		},
		{
			bookname: 'Exodus',
			inSet: 'Torah',
			hebrew: ''
		},
		{
			bookname: 'Leviticus',
			inSet: 'Torah',
			hebrew: ''
		},
		{
			bookname: 'Numbers',
			inSet: 'Torah',
			hebrew: ''
		},
		{
			bookname: 'Deuteronomy',
			inSet: 'Torah',
			hebrew: ''
		},
		{
			bookname: 'Joshua',
			inSet: "Nevi'im",
			hebrew: ''
		},
		{
			bookname: 'Judges',
			inSet: "Nevi'im",
			hebrew: ''
		},
		{
			bookname: 'Samuel_1',
			inSet: "Nevi'im",
			hebrew: ''
		},
		{
			bookname: 'Samuel_2',
			inSet: "Nevi'im",
			hebrew: ''
		},
		{
			bookname: 'Kings_1',
			inSet: "Nevi'im",
			hebrew: ''
		},
		{
			bookname: 'Kings_2',
			inSet: "Nevi'im",
			hebrew: ''
		},
		{
			bookname: 'Isaiah',
			inSet: "Nevi'im",
			hebrew: ''
		},
		{
			bookname: 'Jeremiah',
			inSet: "Nevi'im",
			hebrew: ''
		},
		{
			bookname: 'Ezekiel',
			inSet: "Nevi'im",
			hebrew: ''
		},
		{
			bookname: 'Hosea',
			inSet: "Nevi'im",
			hebrew: ''
		},
		{
			bookname: 'Joel',
			inSet: "Nevi'im",
			hebrew: ''
		},
		{
			bookname: 'Amos',
			inSet: "Nevi'im",
			hebrew: ''
		},
		{
			bookname: 'Obadiah',
			inSet: "Nevi'im",
			hebrew: ''
		},
		{
			bookname: 'Jonah',
			inSet: "Nevi'im",
			hebrew: ''
		},
		{
			bookname: 'Micah',
			inSet: "Nevi'im",
			hebrew: ''
		},
		{
			bookname: 'Nahum',
			inSet: "Nevi'im",
			hebrew: ''
		},
		{
			bookname: 'Habakkuk',
			inSet: "Nevi'im",
			hebrew: ''
		},
		{
			bookname: 'Zephaniah',
			inSet: "Nevi'im",
			hebrew: ''
		},
		{
			bookname: 'Haggai',
			inSet: "Nevi'im",
			hebrew: ''
		},
		{
			bookname: 'Zechariah',
			inSet: "Nevi'im",
			hebrew: ''
		},
		{
			bookname: 'Malachi',
			inSet: "Nevi'im",
			hebrew: ''
		},
		{
			bookname: 'Psalms',
			inSet: 'Ketuvim',
			hebrew: ''
		},
		{
			bookname: 'Proverbs',
			inSet: 'Ketuvim',
			hebrew: ''
		},
		{
			bookname: 'Job',
			inSet: 'Ketuvim',
			hebrew: ''
		},
		{
			bookname: 'Ruth',
			inSet: 'Ketuvim',
			hebrew: ''
		},
		{
			bookname: 'Esther',
			inSet: 'Ketuvim',
			hebrew: ''
		},
		{
			bookname: 'Song_of_Songs',
			inSet: 'Ketuvim',
			hebrew: ''
		},
		{
			bookname: 'Lamentations',
			inSet: 'Ketuvim',
			hebrew: ''
		},
		{
			bookname: 'Ecclesiastes',
			inSet: 'Ketuvim',
			hebrew: ''
		},
		{
			bookname: 'Daniel',
			inSet: 'Ketuvim',
			hebrew: ''
		},
		{
			bookname: 'Ezra',
			inSet: 'Ketuvim',
			hebrew: ''
		},
		{
			bookname: 'Nehemiah',
			inSet: 'Ketuvim',
			hebrew: ''
		},
		{
			bookname: 'Chronicles_1',
			inSet: 'Ketuvim',
			hebrew: ''
		},
		{
			bookname: 'Chronicles_2',
			inSet: 'Ketuvim',
			hebrew: ''
		}
	]
	// Tanach
	// Torah
	// Nevi'im
	// Ketuvim
	// Prose books | Tanach exculding psalms, job, proverbs
	// Poetic books | psalms, job, proverbs

	const changeArray: Function = (array: string[], value: string) => {
			if (array.indexOf(value) > -1) array = array.filter(item => item !== value)
			else array.push(value)
			return array
		},
		displayBooks = (selectedBooks: string[]) => {
			let groupName: string[] = []

			selectedBooks.forEach(book => {
				const bookDetails: TanachBook = tanachBooks.filter(each => each.bookname === book)[0]
				if (bookDetails?.inSet && groupName.indexOf(bookDetails.inSet) === -1)
					groupName.push(bookDetails.inSet)
			})

			if (selectedBooks.length === tanachBooks.length) return 'Tanach'
			else if (
				groupName.length === 1 &&
				selectedBooks.length === tanachBooks.filter(each => each.inSet === groupName[0]).length
			)
				return groupName[0]
			else return selectedBooks.length !== 1 ? selectedBooks.length + ' Books' : selectedBooks[0]
		},
		updateChange = (e: any) => (searchRequest.splitBy = e.target?.value)

	let bookOptions: dropdownInput[]
	$: {
		bookOptions = []
		for (const book of tanachBooks) {
			// check user's language preference
			bookOptions.push({
				checked: searchRequest.books.indexOf(book.bookname) >= 0,
				value: book.bookname,
				text: book.bookname.replaceAll('_', ' ')
			})
		}
	}
</script>

<aside>
	<div class="option">
		<h3 class="title">Books to search</h3>
		<Dropdown
			placeholder="{displayBooks(searchRequest.books)} selected"
			options={bookOptions}
			on:change={e => (searchRequest.books = e.detail.value)}
		/>
		<!-- searchRequest.books = JSON.parse(booksSelected) -->
	</div>

	<div class="option">
		<h3 class="title">Use <i>keri</i> instead of <i>kesiv</i></h3>
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
		<h3 class="title">Use <i>taam tachton</i></h3>
		<Button
			classes="small {searchRequest.taamTachton ? 'default' : 'muted'}"
			text="Yes"
			on:click={() => (searchRequest.taamTachton = true)}
		/>
		<Button
			classes="small {!searchRequest.taamTachton ? 'default' : 'muted'}"
			text="No"
			on:click={() => (searchRequest.taamTachton = false)}
		/>
	</div>

	<!-- <div class="option">
		<h3 class="title">Exclude chachter group(s) from search</h3>
		{#each ['letter', 'nekudah', 'trop', 'other'] as removeOption}
			<Button classes='small {search.remove.indexOf(removeOption) >= 0? 'default': 'muted'}' style='text-transform: capitalize;margin-inline-end: .3rem;' text={removeOption} on:click={() => searchRequest.remove = changeArray(searchRequest.remove, removeOption)} />
		{/each}
		{JSON.stringify(searchRequest.remove)}
	</div> -->

	<div class="option">
		<h3 class="title">Display</h3>
		<Dropdown placeholder={searchRequest.display} options={[]} />
	</div>

	<!-- <div class="option">
		<h3 class="title">Split results by</h3>
		<select
			placeholder={searchRequest.splitBy}
			bind:value={searchRequest.splitBy}
			on:change={updateChange}
		>
			<option value="pasuk">Pasuk</option>
			<option value="word">Word</option>
			<option value="tropword">Tropword</option>
		</select>
	</div> -->
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
