<script lang="ts">
	import Button from '$lib/Button.svelte'
	import Dropdown from '$lib/Dropdown.svelte'
	import { each, loop_guard } from 'svelte/internal'

	export let search: searchRequest, updateSearchByKey: any

	let tanachBooks: tanachBook[] = [
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
				const bookDetails: tanachBook = tanachBooks.filter(each => each.bookname === book)[0]
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
		}

	let bookOptions: dropdownInput[]
	$: {
		bookOptions = []
		for (const book of tanachBooks) {
			// check user's language preference
			bookOptions.push({
				checked: search.books.indexOf(book.bookname) >= 0,
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
			placeholder="{displayBooks(search.books)} selected"
			options={bookOptions}
			on:change={e => updateSearchByKey('books', e.detail.value)}
		/>
		<!-- updateSearchByKey('books', JSON.parse(booksSelected)) -->
		<!-- {JSON.stringify(search.books)} -->
	</div>

	<div class="option">
		<h3 class="title">Use <i>keri</i> instead of <i>kesiv</i></h3>
		<Button
			classes="small {search.useKeri ? 'default' : 'muted'}"
			text="Yes"
			on:click={() => updateSearchByKey('useKeri', true)}
		/>
		<Button
			classes="small {!search.useKeri ? 'default' : 'muted'}"
			text="No"
			on:click={() => updateSearchByKey('useKeri', false)}
		/>
		<!-- {search.useKeri} -->
	</div>

	<div class="option">
		<h3 class="title">Use <i>taam tachton</i></h3>
		<Button
			classes="small {search.taamTachton ? 'default' : 'muted'}"
			text="Yes"
			on:click={() => updateSearchByKey('taamTachton', true)}
		/>
		<Button
			classes="small {!search.taamTachton ? 'default' : 'muted'}"
			text="No"
			on:click={() => updateSearchByKey('taamTachton', false)}
		/>
		<!-- {search.taamTachton} -->
	</div>

	<!-- <div class="option">
		<h3 class="title">Exclude chachter group(s) from search</h3>
		{#each ['letter', 'nekudah', 'trop', 'other'] as removeOption}
			<Button classes='small {search.remove.indexOf(removeOption) >= 0? 'default': 'muted'}' style='text-transform: capitalize;margin-inline-end: .3rem;' text={removeOption} on:click={updateSearchByKey('remove', changeArray(search.remove, removeOption))} />
		{/each}
		{JSON.stringify(search.remove)}
	</div> -->

	<div class="option">
		<h3 class="title">Display</h3>
		<Dropdown placeholder={search.display} />
	</div>

	<div class="option">
		<h3 class="title">Split results by</h3>
		<select
			bind:value={search.splitBy}
			placeholder={search.splitBy}
			on:change={e => updateSearchByKey('splitBy', e.target?.value)}
		>
			<option value="pasuk">Pasuk</option>
			<option value="perek">Perek</option>
		</select>
		<!-- <Dropdown
			placeholder={search.splitBy}
			options={[{checked: true, value: 'Pasuk'}, {checked: false, value: 'Perek'}]}
			on:change={e => updateSearchByKey('splitBy', e.detail.value.toLowerCase())}
		/> -->
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
